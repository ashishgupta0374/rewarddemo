package com.reward.web.controller.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reward.web.controller.entity.Customer;
import com.reward.web.controller.entity.Transaction;


/**
 * The Interface TransactionRepository.
 */
@Repository("transactionRepository")
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    /**
     * Find all by date greater than equal.
     *
     * @param date the date
     * @return the list
     */
    List<Transaction> findAllByDateGreaterThanEqual(Date date);
    
    /**
     * Find all by customer and date greater than equal.
     *
     * @param customer the customer
     * @param date the date
     * @return the list
     */
    List<Transaction> findAllByCustomerAndDateGreaterThanEqual(Customer customer, Date date);
}