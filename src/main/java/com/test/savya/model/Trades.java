package com.test.savya.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="Trades")
@Data
public class Trades implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="Type")
    private String type;

    @Column(name="StockPrice")
    private int stockPrice;

    @Column(name = "TransactionDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private LocalDateTime date;

    //@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    //@JsonIdentityReference
    //@JsonProperty("id")
    @ManyToOne /*(cascade = CascadeType.ALL, targetEntity = Stocks.class)*/
    @JoinColumn(name = "stockId")
    private Stocks stocks;
}
