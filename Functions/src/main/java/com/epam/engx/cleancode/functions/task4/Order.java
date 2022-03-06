package com.epam.engx.cleancode.functions.task4;

import com.epam.engx.cleancode.functions.task4.thirdpartyjar.Product;

import java.util.Iterator;
import java.util.List;

public class Order {

    private List<Product> products;

    public Double getPriceOfAvailableProducts() {
        removeUnavailableProducts(products);
        return calculatePrice(products);
    }

    private void removeUnavailableProducts(List<Product> products){
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (isUnavailableProducts(p))
                iterator.remove();
        }
    }

    private boolean isUnavailableProducts(Product product){
        return !product.isAvailable();
    }

    private double calculatePrice(List<Product> products){
        double orderPrice = 0.0;
        for (Product p : products)
            orderPrice += p.getProductPrice();
        return orderPrice;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
