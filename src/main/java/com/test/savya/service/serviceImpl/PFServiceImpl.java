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

import java.util.List;
import java.util.Optional;

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
        /*Set<Trades> t = stocks.getTrades();
        t.forEach(o -> o.setStocks(stocks));
        stocks.setTrades(t);*/
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


}
