package com.stockmonitor;
import com.stockmonitor.service.MonitorService;

public class MainRunner {
    public static void main(String[] args) throws Exception {
        new MonitorService().start();
    }
}