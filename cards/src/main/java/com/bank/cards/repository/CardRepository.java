package com.bank.cards.repository;

import com.bank.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Cards,Integer> {


    List<Cards> findByCustomerId(Integer customerId);
}
