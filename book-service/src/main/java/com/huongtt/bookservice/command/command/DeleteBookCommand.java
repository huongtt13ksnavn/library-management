package com.huongtt.bookservice.command.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class DeleteBookCommand implements Serializable {
    @TargetAggregateIdentifier
    private String id;
}
