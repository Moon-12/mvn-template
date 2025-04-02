package com.ashwija.mvn.model;

import java.util.List;

public abstract class AppEntity {
    public abstract String getHeader();

    public String formatHeader(List<String> headers) {
        StringBuilder headerFormat = new StringBuilder();

        for (int i = 0; i < headers.size(); i++) {
            headerFormat.append("%-12s| "); // Adjust width (12) as needed
        }
        // Remove trailing "| "
        if (headerFormat.length() > 0) {
            headerFormat.setLength(headerFormat.length() - 2);
        }
        // Format with all headers
        return String.format(headerFormat.toString(), headers.toArray());
    }
}
