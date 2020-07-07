package com.reward.web.controller.service;

import com.reward.web.controller.dto.CustomerDTO;
import com.reward.web.controller.dto.RewardDTO;
import com.reward.web.controller.response.RewardResponse;
import com.reward.web.controller.dto.TransactionDTO;
import com.reward.web.controller.entity.Customer;

/**
 * The Interface TransactionService.
 */
public interface TransactionService {

	/**
	 * Creates the transaction.
	 *
	 * @param customer the customer
	 * @param dto      the dto
	 * @return the transaction DTO
	 */
	RewardResponse<TransactionDTO> createTransaction(Customer customer, TransactionDTO dto);

	/**
	 * Gets the all transactions for customer.
	 *
	 * @param customer  the customer
	 * @param rewardDTO the rewardDTO
	 * @return the all transactions for customer
	 */
	public RewardResponse<CustomerDTO> getAllTransactionsForCustomer(Customer customer, RewardDTO rewardDTO);
}
