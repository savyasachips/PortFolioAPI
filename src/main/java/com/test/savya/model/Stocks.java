package com.test.savya.model;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Stocks")
@Data
public class Stocks implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    @Column(name="stockId", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;

    @Column(name = "stockName", unique = true, nullable = false)
    private String stockName;

    @Column(name = "CurrentPrice")
    private Double currentPrice;

    /*@OneToMany(fetch = FetchType.EAGER, mappedBy = "stocks", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Trades> trades;*/
}
