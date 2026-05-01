package com.stockmonitor.service;

import com.microsoft.playwright.*;
import com.stockmonitor.factory.PlaywrightFactory;
import com.stockmonitor.model.Product;
import com.stockmonitor.util.CsvUtil;

import java.util.*;
import java.util.concurrent.*;

public class MonitorService {

    public void start() throws Exception {
        while (true) {
            List<Product> products = CsvUtil.read();

            ExecutorService executor = Executors.newFixedThreadPool(5);

            for (Product p : products) {
                executor.submit(() -> process(p));
            }

            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.MINUTES);

            CsvUtil.write(products);

            Thread.sleep(120000);
        }
    }

    private void process(Product product) {
    BrowserContext context = null;

    try {
        System.out.println("\n----------------------------");
        System.out.println("Checking: " + product.getName());

        context = PlaywrightFactory.create();
        Page page = context.newPage();

        page.navigate(product.getUrl());
        page.waitForLoadState();

        boolean inStock = new StockChecker().isInStock(page);
        boolean wasOut = "OUT".equals(product.getLastStatus());

        System.out.println("Previous: " + product.getLastStatus());
        System.out.println("Current: " + (inStock ? "IN" : "OUT"));

        if (inStock && wasOut) {
            System.out.println("🚀 ALERT TRIGGERED!");
            TelegramService.send("🔥 BACK IN STOCK\n" + product.getName());
        }

        String newStatus = inStock ? "IN" : "OUT";

if (!newStatus.equals(product.getLastStatus())) {
    System.out.println("✏️ Updating CSV status: " + product.getLastStatus() + " → " + newStatus);
    product.setLastStatus(newStatus);
}

        Thread.sleep(2000);

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (context != null) context.close();
    }
}
}
