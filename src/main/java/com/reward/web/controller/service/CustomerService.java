package com.reward.web.controller.service;

import java.util.List;

import com.reward.web.controller.dto.CustomerDTO;
import com.reward.web.controller.response.RewardResponse;
import com.reward.web.controller.entity.Customer;

/**
 * The Interface CustomerService.
 */
public interface CustomerService {

	/**
	 * Find customer by id.
	 *
	 * @param email the cust email
	 * @return the customer
	 */
	Customer findCustomerById(String email);

	/**
	 * Find customer dto by id.
	 *
	 * @param email the email
	 * @return the customer DTO
	 */
	RewardResponse<CustomerDTO> findCustomerDtoById(String email);

	/**
	 * Creates the customer.
	 *
	 * @param dto the dto
	 * @return the customer DTO
	 */
	RewardResponse<CustomerDTO> createCustomer(CustomerDTO dto);

	/**
	 * Update customer.
	 *
	 * @param dto the dto
	 * @return the customer DTO
	 */
	RewardResponse<CustomerDTO> updateCustomer(CustomerDTO dto);

	/**
	 * Find all customer dtos.
	 *
	 * @return the list
	 */
	RewardResponse<List<CustomerDTO>> findAllCustomerDtos();

}
