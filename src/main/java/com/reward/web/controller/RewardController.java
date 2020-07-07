package com.reward.web.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.reward.web.controller.dto.*;
import com.reward.web.controller.response.RewardResponse;
import com.reward.web.controller.response.ServiceError;
import com.reward.web.controller.response.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.reward.web.controller.service.CustomerService;
import com.reward.web.controller.service.TransactionService;

import javax.validation.Valid;

/**
 * The Class RewardController.
 */
@SpringBootApplication
@RestController
@RequestMapping("service")
public class RewardController {
    /** The logger. */
    private static Logger LOGGER = LoggerFactory.getLogger(RewardController.class);
    /** The customer service. */
    @Autowired
    private CustomerService customerService;
    
    /** The transaction service. */
    @Autowired
    private TransactionService transactionService;
    
    /**
     * Adds the customer.
     *
     * @param customerDTO the customer DTO
     * @return the customer DTO
     */
    @PostMapping("/customer")
    public RewardResponse<CustomerDTO> addCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        LOGGER.debug("Adding customer - input : {} ",customerDTO );
        RewardResponse<CustomerDTO> response = customerService.createCustomer(customerDTO);
        LOGGER.debug("addCustomer response : {} ",response );
        return response;
    }
    
    @PutMapping("/customer")
    public RewardResponse<CustomerDTO> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        LOGGER.debug("Update customer - input : {} ",customerDTO );
        RewardResponse<CustomerDTO> response = customerService.updateCustomer(customerDTO);
        LOGGER.debug("Update customer response : {} ",response );
        return response;
    }
    
    /**
     * Get the customer.
     *
     * @param email the email
     * @return the customer DTO
     */
    @GetMapping("/customer/{email}")
    public RewardResponse<CustomerDTO> getCustomer( @Valid @PathVariable(value = "email") String email) {
        LOGGER.debug("Update customer - input : {} ",email );
        RewardResponse<CustomerDTO> response = customerService.findCustomerDtoById(email);
        LOGGER.debug("Get customer response : {} ",response );
        return response;
    }
    
    /**
     * Adds the customers.
     *
     * @return the customer list
     */
    @GetMapping("/customers")
    public RewardResponse< List<CustomerDTO>> getAllCustomers() {
        LOGGER.debug("Service#Get all customers" );
        return customerService.findAllCustomerDtos();
        
    }
    
    /**
     * Gets the Customer transactions for the month.
     *
     * @param rewardDTO the rewardDTO
     * @return the reward history
     */
    @PostMapping("/customer/reward")
    public RewardResponse<CustomerDTO> getRewardsHistoryForCustomer(@Valid @RequestBody RewardDTO rewardDTO ) {
        LOGGER.debug("Get customer rewards - input : {} ",rewardDTO );
        try{
            RewardResponse<CustomerDTO> response =   transactionService.getAllTransactionsForCustomer(customerService.findCustomerById(rewardDTO.getEmail()), rewardDTO);
            LOGGER.debug("Get customer reward response : {} ",response );
            return  response;
        } catch (Exception e) {
            LOGGER.error("Get customer reward response : {} ",e.getMessage());
            RewardResponse<CustomerDTO> response = new RewardResponse<CustomerDTO>();
            response.setRequestId(UUID.randomUUID().toString());
            response.setStatus(StatusEnum.FAILURE.name());
            ServiceError error = new ServiceError("C004", e.getMessage());
            response.setError(Optional.of(error));
            LOGGER.debug("Get customer reward response : {} ",response );
            return response;
        }
        
    }
    
    /**
     * Adds the transaction.
     *
     * @param transactionDTO the transaction DTO
     * @return the transaction DTO
     */
    @PostMapping("/transaction")
    public RewardResponse<TransactionDTO> saveTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
    
        LOGGER.debug("Save transaction - input : {} ",transactionDTO );
        try{
            RewardResponse<TransactionDTO> response = transactionService.createTransaction(customerService.findCustomerById(transactionDTO.getEmail()), transactionDTO);
            LOGGER.debug("Save transaction response : {} ",response );
            return  response;
        } catch (Exception e) {
            LOGGER.error("Error occured while saving  customer transaction : {} ",e.getMessage());
            RewardResponse<TransactionDTO> response = new RewardResponse<TransactionDTO>();
            response.setRequestId(UUID.randomUUID().toString());
            response.setStatus(StatusEnum.FAILURE.name());
            ServiceError error = new ServiceError("T002", e.getMessage());
            response.setError(Optional.of(error));
            LOGGER.error("Save transaction response : {} ",response );
            return response;
        }
    }
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(RewardController.class, args);
    }
}
