package com.test.savya.repository;

import com.test.savya.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksRepo extends JpaRepository<Stocks, Integer> {
}

