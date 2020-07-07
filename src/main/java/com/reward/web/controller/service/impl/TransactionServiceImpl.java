package com.reward.web.controller.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.reward.web.controller.dto.CustomerDTO;
import com.reward.web.controller.dto.RewardDTO;
import com.reward.web.controller.response.RewardResponse;
import com.reward.web.controller.response.ServiceError;
import com.reward.web.controller.response.StatusEnum;
import com.reward.web.controller.dto.TransactionDTO;
import com.reward.web.controller.entity.Customer;
import com.reward.web.controller.entity.Transaction;
import com.reward.web.controller.exception.ValidationException;
import com.reward.web.controller.repository.TransactionRepository;
import com.reward.web.controller.service.TransactionService;
import com.reward.web.controller.utils.RewardUtil;

/**
 * The Class TransactionServiceImpl.
 */
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	
	/** The logger. */
	private static Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	/** The repository. */
	@Autowired
	TransactionRepository repository;

	/**
	 * Creates the transaction.
	 *
	 * @param customer the customer
	 * @param dto      the dto
	 * @return the transaction DTO
	 */
	@Override
	public RewardResponse<TransactionDTO> createTransaction(Customer customer, TransactionDTO dto) {
		LOGGER.debug("Inside method createTransaction");
		RewardResponse<TransactionDTO> response = new RewardResponse<TransactionDTO>();
		response.setRequestId(UUID.randomUUID().toString());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {
			Calendar cal = Calendar.getInstance();
			Transaction transaction = new Transaction(customer, dto.getAmount(), cal.getTime());
			repository.save(transaction);
			dto.setReward(transaction.getRewardPoint());
			
			response.setStatus(StatusEnum.SUCCESS.name());
			response.setResult(Optional.of(dto));
		} catch (Exception e) {
			response.setStatus(StatusEnum.FAILURE.name());
			ServiceError error = new ServiceError("T001", e.getMessage());
			error.setMessage(e.getMessage());
			response.setError(Optional.of(error));
			
			LOGGER.debug("Error occured while creating transaction  : {} ",e.getMessage());
		}
		stopWatch.stop();
		Map<String, Object> meta = new HashMap<String, Object>();
		meta.put("ServiceExecTime", stopWatch.getTotalTimeMillis());
		response.setMeta(meta);
		
		LOGGER.debug("Createtransaction response : {} ",response);
		return response;
	}

	/**
	 * Gets the all transactions for customer.
	 *
	 * @param customer  the customer
	 * @param rewardDTO the rewardDTO
	 * @return the all transactions for customer
	 */
	@Override
	public RewardResponse<CustomerDTO> getAllTransactionsForCustomer(Customer customer, RewardDTO rewardDTO) {
		LOGGER.debug("Inside method getAllTransactionsForCustomer");
		RewardResponse<CustomerDTO> response = new RewardResponse<CustomerDTO>();
		response.setRequestId(UUID.randomUUID().toString());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {
			// Validate input
			validateRewardInput(rewardDTO);
			// Update Calender to get transaction history based on input
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) - rewardDTO.getMonths()));
			cal.set(Calendar.DATE, (cal.get(Calendar.DATE) - rewardDTO.getDays()));
			// List<Transaction > transactions =
			// repository.findAllByDateGreaterThanEqual(cal.getTime());
			List<Transaction> transactions = repository.findAllByCustomerAndDateGreaterThanEqual(customer,
					cal.getTime());
			CustomerDTO customerDto = RewardUtil.getCustomerDto(customer);
			
			setTransactionDto(customerDto,transactions );
			response.setStatus(StatusEnum.SUCCESS.name());
			response.setResult(Optional.of(customerDto));

		} catch (Exception e) {
			response.setStatus(StatusEnum.FAILURE.name());
			ServiceError error = new ServiceError("T002", e.getMessage());
			error.setMessage(e.getMessage());
			response.setError(Optional.of(error));
			LOGGER.debug("Error occured in get all transaction  : {} ",e.getMessage());
		}
		stopWatch.stop();

		Map<String, Object> meta = new HashMap<String, Object>();
		meta.put("ServiceExecTime", stopWatch.getTotalTimeMillis());
		response.setMeta(meta);
		LOGGER.debug("get all transaction for customer response : {} ",response);
		return response;
	}

	private void validateRewardInput(RewardDTO rewardDTO) {
		LOGGER.debug("Inside method validateRewardInput");
		if (rewardDTO.getMonths() < 0) {
			throw new ValidationException("Invalid input !!!. MONTHS must be valid positive number");
		}
		if (rewardDTO.getDays() < 0) {
			throw new ValidationException("Invalid input !!!. DAYS must be valid positive number");
		}
	}

	/**
	 * Gets the transaction dto from transaction
	 *
	 * @param transactions the transactions
	 * @return the customer dto
	 */
	private void setTransactionDto(CustomerDTO customerDto, List<Transaction> transactions) {
		LOGGER.debug("Inside method setTransactionDto");
		Set<TransactionDTO> transactionDTOS = new HashSet<>();
		Iterator<Transaction> itr = transactions.iterator();
		int rewardPoints = 0;
		while (itr.hasNext()) {
			Transaction tr = itr.next();
			transactionDTOS.add(new TransactionDTO(tr.getAmount(), tr.getRewardPoint(), tr.getDate()));
			rewardPoints += tr.getRewardPoint();
		}
		customerDto.setTotalReward(rewardPoints);
		customerDto.setTransactions(transactionDTOS);
	}
}
