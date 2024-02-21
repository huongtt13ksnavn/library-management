package com.huongtt.bookservice.command.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdatedEvent {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
