package com.cg.creditcardpayment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.creditcardpayment.entity.CreditCardEntity;

@Repository
public interface ICreditCardRepository extends JpaRepository<CreditCardEntity, String>{

}
