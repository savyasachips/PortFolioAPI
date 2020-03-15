package com.test.savya.controller;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.test.savya.model.Stocks;
import com.test.savya.model.Trades;
import com.test.savya.service.PFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class PFController {

    @Autowired
    private PFService pFService;

    @GetMapping("/all")
    public List<Stocks> getAllTrans(){
        return pFService.getAllTransactions();
    }

    @PostMapping("/stocks/new")
    public Stocks addNew(@RequestBody Stocks stocks){
        return pFService.addTrans(stocks);
    }

    @PostMapping("/trades/addTrade")
    public Trades addNew(@RequestBody Trades trades){
        return pFService.addStocks(trades);
    }

    @PostMapping("/trades/updateTrade")
    public Map<String,Object> update(@Valid @RequestBody Trades trades){
        Map<String,Object> map = new HashMap<>();
        map.put("success", pFService.updateTrades(trades));
        map.put("data",trades);
        return map;
    }

    @PostMapping("/trades/removeTrade")
    public Map<String,Object> delete(@Valid @RequestBody Trades trades){
        Map<String,Object> map = new HashMap<>();
        map.put("success", pFService.deleteTrades(trades));
        map.put("data",trades);
        return map;
    }

    @GetMapping("/fetch")
    public ResponseEntity<Object> fetchUpdates() throws IOException {
        OkHttpClient client = new OkHttpClient();
        try {
            /*Request request = new Request.Builder()
                    .url("https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-quotes?region=US&lang=en&symbols=BAC%252CKC%253DF%252C002210.KS%252CIWM%252CAMECX")
                    .get()
                    .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "d1bd66f33emshdb10c9e47ec878dp156084jsn7a7bb7df2910")
                    .build();*/
            Request request = new Request.Builder()
                    .url("https://enclout-yahoo-finance.p.rapidapi.com/show.json?text=AAPL%252C%20MSFT%252C%20GOOG")
                    .get()
                    .addHeader("x-rapidapi-host", "enclout-yahoo-finance.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "d1bd66f33emshdb10c9e47ec878dp156084jsn7a7bb7df2910")
                    .build();

            Response response = client.newCall(request).execute();
            Map<String,Double> map = new HashMap<>();
            String s = response.body().string();
            System.out.println(s);
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
