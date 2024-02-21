package com.huongtt.bookservice.command.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
