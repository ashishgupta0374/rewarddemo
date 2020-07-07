package com.reward.web.controller.utils;

import com.reward.web.controller.dto.CustomerDTO;
import com.reward.web.controller.dto.TransactionDTO;
import com.reward.web.controller.entity.Customer;
import com.reward.web.controller.entity.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * The Class RewardUtil.
 */
public class RewardUtil {
    
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
    /**
     * Gets the reward.
     *
     * @param amount the amount
     * @return the reward
     */
    public static int getReward(int amount){
        int reward = 0;
        if (amount > 100) {
            reward +=  2*(amount-100);
        }
        if (amount > 50) {
            reward +=  50;
        }
        return reward;
    }
    
    /**
     * Gets the customer dto.
     *
     * @param customer the customer
     * @return the customer dto
     */
    public static CustomerDTO getCustomerDto (Customer customer) {
        CustomerDTO customerDto = new CustomerDTO(customer.getEmail(),customer.getFirstName(), customer.getLastName() );
        setTransactionDto(customerDto, customer.getTransactions());
        return customerDto;
    }
    
    public static CustomerDTO getCustomerDtoWithOutTransaction (Customer customer) {
        CustomerDTO customerDto = new CustomerDTO(customer.getEmail(),customer.getFirstName(), customer.getLastName() );
        return customerDto;
    }
    
    /**
     * Sets the transaction dto.
     *
     * @param customerDto the customer dto
     * @param transactions the transactions
     */
    private static void setTransactionDto(CustomerDTO customerDto, Set<Transaction> transactions) {
        Set<TransactionDTO> transactionDtos = new HashSet<>();
        int totalRewards = 0;
        
        Iterator<Transaction> itr = transactions.iterator();
        while (itr.hasNext()){
            Transaction transaction  = itr.next();
            TransactionDTO transactionDTO = new TransactionDTO(transaction.getAmount(), transaction.getRewardPoint(), transaction.getDate());
            totalRewards += transactionDTO.getReward();
            transactionDtos.add(transactionDTO);
        }

        
        customerDto.setTransactions(transactionDtos);
        customerDto.setTotalReward(totalRewards);
    }
    
    public static String getFormattedDate(Date date) {
        return dateFormat.format(date);
    }
}
