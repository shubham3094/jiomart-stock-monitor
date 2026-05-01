package com.stockmonitor;
import java.time.LocalTime;
import java.time.ZoneId;
import com.stockmonitor.service.MonitorService;

public class MainRunner {
    public static void main(String[] args) throws Exception {

        LocalTime now = LocalTime.now(ZoneId.of("Asia/Kolkata"));

        if (now.isBefore(LocalTime.of(6, 0)) || now.isAfter(LocalTime.of(22, 0))) {
            System.out.println("⏸ Outside 6AM–10PM window. Skipping run.");
            return;
        }

        new MonitorService().start(); // or runOnce()
    }
}