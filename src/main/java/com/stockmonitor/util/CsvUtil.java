package com.stockmonitor.util;

import com.opencsv.CSVReader;
import com.stockmonitor.model.Product;

import java.io.*;
import java.util.*;

public class CsvUtil {

    public static List<Product> read() throws Exception {
        List<Product> list = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader("products.csv"));
        List<String[]> rows = reader.readAll();

        for (int i = 1; i < rows.size(); i++) {
            String[] r = rows.get(i);
            Product p = new Product();
            p.setName(r[0]);
            p.setUrl(r[1]);
            p.setLastStatus(r[2]);
            list.add(p);
        }
        return list;
    }

    public static void write(List<Product> products) throws Exception {
        FileWriter writer = new FileWriter("products.csv");
        writer.append("name,url,last_status\n");

        for (Product p : products) {
            writer.append(p.getName()).append(",")
                  .append(p.getUrl()).append(",")
                  .append(p.getLastStatus()).append("\n");
        }
        writer.close();
    }
}