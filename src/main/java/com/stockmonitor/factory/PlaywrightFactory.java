package com.stockmonitor.factory;

import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class PlaywrightFactory {

    public static BrowserContext create() {
        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(true) // now can run headless
        );

        // 🔥 USE SAVED SESSION HERE
        BrowserContext context = browser.newContext(
            new Browser.NewContextOptions()
                .setStorageStatePath(Paths.get("rudrapur-state.json"))
        );

        return context;
    }
}