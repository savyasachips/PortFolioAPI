package com.test.savya.service;

import com.test.savya.model.Stocks;
import com.test.savya.model.Trades;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PFService {
    List<Stocks> getAllTransactions();
    Stocks addTrans(Stocks stocks);
    Trades addStocks(Trades trades);
    boolean updateTrades(Trades trades);
    boolean deleteTrades(Trades trades);
    Map<String, Object> getHoldings(Map<String, Object> map);
    Map<String, Object> getPortFolio(Map<String, Object> map);
    Map<String, Object> getCumulativeReturns(Map<String, Object> map);
}
