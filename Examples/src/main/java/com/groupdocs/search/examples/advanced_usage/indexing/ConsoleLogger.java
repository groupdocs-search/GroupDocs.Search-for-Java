package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.common.ILogger;

public class ConsoleLogger implements ILogger
{
    public ConsoleLogger() {
    }

    public void error(String message) {
        System.out.println("Error: " + message);
    }

    public void trace(String message) {
        System.out.println(message);
    }
}