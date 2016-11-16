package com.lancep.service;

import org.springframework.stereotype.Component;

@Component
public class MarketService {
    public String getInvoiceTotal(String productList) {
        return String.format("You have %d items to purchase", productList.length());
    }
}
