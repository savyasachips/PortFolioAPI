package com.test.savya.repository;

import com.test.savya.model.Stocks;
import com.test.savya.model.Trades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradesRepo extends JpaRepository<Trades, Integer> {
}
