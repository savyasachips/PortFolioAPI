package com.test.savya.service.serviceImpl;

import com.test.savya.model.Stocks;
import com.test.savya.model.Trades;
import com.test.savya.repository.StocksRepo;
import com.test.savya.repository.TradesRepo;
import com.test.savya.service.PFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PFServiceImpl implements PFService {

    @Autowired
    private TradesRepo tradesRepo;

    @Autowired
    private StocksRepo stocksRepo;

    @Override
    public List<Stocks> getAllTransactions(){
        return stocksRepo.findAll();
    }

    @Override
    public Stocks addTrans(Stocks stocks){
        return stocksRepo.save(stocks);
    }

    @Override
    public Trades addStocks(Trades trades) {
        return tradesRepo.save(trades);
    }

    @Override
    public boolean updateTrades(Trades trades) {
        if(tradesRepo.findById(trades.getId()).isPresent()){
            tradesRepo.save(trades);
            return true;}
        else
            return false;
    }

    @Override
    public boolean deleteTrades(Trades trades) {
        if(tradesRepo.findById(trades.getId()).isPresent())
          {
              tradesRepo.delete(trades);
              return true;
          }

        else
            return false;
    }

    @Override
    public Map<String, Object> getHoldings(Map<String, Object> map) {
        List<Stocks> allStocks = stocksRepo.findAll();
        allStocks.forEach(o ->
                {
                    Set<Trades> tradesSet=tradesRepo.findByStocks(o);
                    if(tradesSet.size()>0) {
                        int buy = tradesSet.stream().filter(s->s.getType().equals("Buy")).mapToInt(c->c.getStockCount()).sum();
                        int sell = tradesSet.stream().filter(s->s.getType().equals("Sell")).mapToInt(c->c.getStockCount()).sum();
                        map.put(o.getStockName(), "Total Stocks available: "+Math.subtractExact(buy, sell));
                    }
                }
                );
        return map;
    }

    @Override
    public Map<String, Object> getPortFolio(Map<String, Object> map) {
        List<Stocks> allStocks = stocksRepo.findAll();
        allStocks.forEach(o ->
                {
                    Set<Trades> tradesSet=tradesRepo.findByStocks(o);
                    if(tradesSet.size()>0)
                        map.put(o.getStockName(), tradesSet.stream().map(s->
                                (s.getType().toUpperCase()+" "+s.getStockCount()+"@"+s.getStockPrice()+" "+s.getDate().toString())
                        ).collect(Collectors.toSet()));
                }
        );
        return map;
    }

    @Override
    public Map<String, Object> getCumulativeReturns(Map<String, Object> map) {
        List<Stocks> allStocks = stocksRepo.findAll();
        allStocks.forEach(o ->
                {
                    Set<Trades> tradesSet=tradesRepo.findByStocks(o);
                    if(tradesSet.size()>0) {
                        int buyCount = (int)tradesSet.stream().filter(s->s.getType().equals("Buy")).mapToInt(c->c.getStockCount()).sum();
                        int buy = tradesSet.stream().filter(s->s.getType().equals("Buy")).mapToInt(c->c.getStockPrice()*c.getStockCount()).sum();
                        Double avgPrice = Double.valueOf(buy)/Double.valueOf(buyCount);
                        map.put(o.getStockName(), "Initial buy price: "+String.format("%.2f", avgPrice)+ " And "
                                 +"Current price: "+o.getCurrentPrice());
                    }
                }
        );
        return map;
    }


}
