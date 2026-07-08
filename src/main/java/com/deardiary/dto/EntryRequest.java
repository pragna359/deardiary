package com.deardiary.dto;

import lombok.Data;

@Data
public class EntryRequest {
    private String title;
    private String content;
    private String mood;
}