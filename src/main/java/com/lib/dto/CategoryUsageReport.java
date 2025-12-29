package com.lib.dto;

import lombok.Data;

@Data
public class CategoryUsageReport {

    private String category;
    private Number usersCount;
    private Number percentage;

    public CategoryUsageReport(String category,
                               Number usersCount,
                               Number percentage) {
        this.category = category;
        this.usersCount = usersCount;
        this.percentage = percentage;
    }
}
