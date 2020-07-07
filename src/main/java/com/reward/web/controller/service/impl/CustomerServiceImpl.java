package com.reward.web.controller.service.impl;

import java.util.*;

import javax.transaction.Transactional;

import com.reward.web.controller.RewardController;
import com.reward.web.controller.response.RewardResponse;
import com.reward.web.controller.response.ServiceError;
import com.reward.web.controller.response.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reward.web.controller.dto.CustomerDTO;
import com.reward.web.controller.entity.Customer;
import com.reward.web.controller.exception.ResourceNotFoundException;
import com.reward.web.controller.exception.ValidationException;
import com.reward.web.controller.repository.CustomerRepository;
import com.reward.web.controller.service.CustomerService;
import com.reward.web.controller.utils.RewardUtil;
import org.springframework.util.StopWatch;

/**
 * The Class CustomerServiceImpl.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	/** The logger. */
	private static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	/** The repository. */
	@Autowired
	CustomerRepository repository;

	/**
	 * Find customer by id.
	 *
	 * @param email the customer email
	 * @return the customer
	 */
	@Override
	public Customer findCustomerById(String email) {
		LOGGER.debug("Inside method findCustomerById");
		Customer customer = repository.findById(email)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "email", email));
		return customer;
	}

	/**
	 * Find all customer dtos.
	 *
	 * @return the list
	 */
	@Override
	public RewardResponse<List<CustomerDTO>> findAllCustomerDtos() {
		LOGGER.debug("Inside method findAllCustomerDtos");
		RewardResponse<List<CustomerDTO>> response = new RewardResponse<List<CustomerDTO>>();
		response.setRequestId(UUID.randomUUID().toString());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {
			List<Customer> customers = repository.findAll();
			List<CustomerDTO> customerDTOS = new ArrayList<>();
			customers.forEach(customer -> {
				customerDTOS.add(RewardUtil.getCustomerDtoWithOutTransaction(customer));
			});

			response.setStatus(StatusEnum.SUCCESS.name());
			response.setResult(Optional.of(customerDTOS));

		} catch (Exception e) {
			LOGGER.error("Exception  occured in Get all customers (dto) : {} ",e.getMessage());
			response.setStatus(StatusEnum.FAILURE.name());
			ServiceError error = new ServiceError("C001", e.getMessage());
			response.setError(Optional.of(error));
		}
		stopWatch.stop();
		Map<String, Object> meta = new HashMap<String, Object>();
		meta.put("ServiceExecTime", stopWatch.getTotalTimeMillis());
		response.setMeta(meta);
		
		LOGGER.debug("Get all customers (dto) response : {} ",response);
		return response;

	}

	/**
	 * Find customer dto by id.
	 *
	 * @param email the customer email
	 * @return the customer DTO
	 */
	@Override
	public RewardResponse<CustomerDTO> findCustomerDtoById(String email) {
		LOGGER.debug("Inside method findCustomerDtoById");
		RewardResponse<CustomerDTO> response = new RewardResponse<CustomerDTO>();
		response.setRequestId(UUID.randomUUID().toString());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {
			Customer customer = repository.findById(email)
					.orElseThrow(() -> new ResourceNotFoundException("Customer", "email", email));

			response.setStatus(StatusEnum.SUCCESS.name());
			response.setResult(Optional.of(RewardUtil.getCustomerDtoWithOutTransaction(customer)));

		} catch (Exception e) {
			LOGGER.error("Exception  occured in findCustomerDtoById : {} ",e.getMessage());
			response.setStatus(StatusEnum.FAILURE.name());
			ServiceError error = new ServiceError("C002", e.getMessage());
			response.setError(Optional.of(error));
		}
		stopWatch.stop();
		Map<String, Object> meta = new HashMap<String, Object>();
		meta.put("ServiceExecTime", stopWatch.getTotalTimeMillis());
		response.setMeta(meta);
		LOGGER.debug("Get customer (dto) by ID response : {} ",response);
		return response;

	}

	/**
	 * Creates the customer.
	 *
	 * @param dto the dto
	 * @return the customer DTO
	 */
	@Override
	public RewardResponse<CustomerDTO> createCustomer(CustomerDTO dto) {
		LOGGER.debug("Inside method createCustomer");
		RewardResponse<CustomerDTO> response = new RewardResponse<CustomerDTO>();
		response.setRequestId(UUID.randomUUID().toString());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {
			Optional<Customer> opCustomer = repository.findById(dto.getEmail());
			if (opCustomer.isPresent()) {
				throw new ValidationException("Customer email already present : ", dto.getEmail());
			}
			Customer customer = new Customer(dto.getFirstName(), dto.getLastName(), dto.getEmail());
			repository.save(customer);

			response.setStatus(StatusEnum.SUCCESS.name());
			response.setResult(Optional.of(dto));

		} catch (Exception e) {
			response.setStatus(StatusEnum.FAILURE.name());
			ServiceError error = new ServiceError("C003", e.getMessage());
			error.setMessage(e.getMessage());
			response.setError(Optional.of(error));
		}

		stopWatch.stop();
		Map<String, Object> meta = new HashMap<String, Object>();
		meta.put("ServiceExecTime", stopWatch.getTotalTimeMillis());
		response.setMeta(meta);
		LOGGER.debug("Create customer response : {} ",response);
		return response;

	}

	/**
	 * Update customer.
	 *
	 * @param dto the dto
	 * @return the customer DTO
	 */
	@Override
	public RewardResponse<CustomerDTO> updateCustomer(CustomerDTO dto) {
		LOGGER.debug("Inside method updateCustomer");
		RewardResponse<CustomerDTO> response = new RewardResponse<CustomerDTO>();
		response.setRequestId(UUID.randomUUID().toString());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {
			Customer customer = repository.findById(dto.getEmail())
					.orElseThrow(() -> new ResourceNotFoundException("Customer", "email", dto.getEmail()));
			customer.setFirstName(dto.getFirstName());
			customer.setLastName(dto.getLastName());
			repository.save(customer);

			response.setStatus(StatusEnum.SUCCESS.name());
			response.setResult(Optional.of(dto));

		} catch (Exception e) {
			response.setStatus(StatusEnum.FAILURE.name());
			ServiceError error = new ServiceError("C004", e.getMessage());
			error.setMessage(e.getMessage());
			response.setError(Optional.of(error));
		}

		stopWatch.stop();
		Map<String, Object> meta = new HashMap<String, Object>();
		meta.put("ServiceExecTime", stopWatch.getTotalTimeMillis());
		response.setMeta(meta);
		LOGGER.debug("Update customer response : {} ",response);
		return response;
	}

}
