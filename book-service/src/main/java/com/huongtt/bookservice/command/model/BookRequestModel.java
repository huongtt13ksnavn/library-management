package com.huongtt.bookservice.command.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestModel {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
