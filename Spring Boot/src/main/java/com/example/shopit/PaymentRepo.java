package com.example.shopit;

import org.springframework.data.repository.CrudRepository;

public interface PaymentRepo extends CrudRepository<Payment, Integer> {

    Payment findByTxnId(String txnId);
}
