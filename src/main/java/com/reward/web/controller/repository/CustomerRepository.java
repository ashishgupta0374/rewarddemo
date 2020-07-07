package com.reward.web.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reward.web.controller.entity.Customer;

/**
 * The Interface CustomerRepository.
 */
@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, String> {


}
