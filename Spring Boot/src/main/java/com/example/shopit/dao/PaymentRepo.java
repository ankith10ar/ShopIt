package com.example.shopit.dao;

import com.example.shopit.payment.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepo extends CrudRepository<Payment, Integer> {

    Payment findByTxnId(String txnId);
}
