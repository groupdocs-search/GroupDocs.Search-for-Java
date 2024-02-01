package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.common.ILogger;

public class ConsoleLogger implements ILogger {
    public ConsoleLogger() {
    }

    @Override
    public void error(String message) {
        System.out.println("Error: " + message);
    }

    @Override
    public void trace(String message) {
        System.out.println(message);
    }
}
