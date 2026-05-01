package com.stockmonitor.service;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

public class StockChecker {

    public boolean isInStock(Page page) {
        try {
            // wait for network to settle
            page.waitForLoadState(LoadState.NETWORKIDLE);

            // extra buffer for UI
            page.waitForTimeout(5000);

            Locator btn = page.locator(
                "button:has-text('Add to Cart'), button:has-text('ADD TO CART')"
            );

            if (btn.count() > 0 && btn.first().isVisible()) {
                System.out.println("✅ IN STOCK detected");
                return true;
            }

        } catch (Exception e) {
            System.out.println("⚠️ Error detecting stock: " + e.getMessage());
        }

        System.out.println("❌ OUT OF STOCK");
        return false;
    }
}