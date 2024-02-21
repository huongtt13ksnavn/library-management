package com.huongtt.bookservice.command.aggregate;

import com.huongtt.bookservice.command.command.CreateBookCommand;
import com.huongtt.bookservice.command.command.DeleteBookCommand;
import com.huongtt.bookservice.command.command.UpdateBookCommand;
import com.huongtt.bookservice.command.event.BookCreatedEvent;
import com.huongtt.bookservice.command.event.BookDeletedEvent;
import com.huongtt.bookservice.command.event.BookUpdatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Getter
@Setter
@NoArgsConstructor
public class BookAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String author;
    private Boolean isReady;

    @CommandHandler
    public BookAggregate(CreateBookCommand createBookCommand) {
        BookCreatedEvent bookCreatedEvent = new BookCreatedEvent();
        BeanUtils.copyProperties(createBookCommand, bookCreatedEvent);
        AggregateLifecycle.apply(bookCreatedEvent);
    }

    @CommandHandler
    public void handle(UpdateBookCommand updateBookCommand) {
        BookUpdatedEvent bookUpdatedEvent = new BookUpdatedEvent();
        BeanUtils.copyProperties(updateBookCommand, bookUpdatedEvent);
        AggregateLifecycle.apply(bookUpdatedEvent);
    }

    @CommandHandler
    public void handle(DeleteBookCommand deleteBookCommand) {
        BookDeletedEvent bookDeletedEvent = new BookDeletedEvent();
        bookDeletedEvent.setId(deleteBookCommand.getId());
        AggregateLifecycle.apply(bookDeletedEvent);
    }

    @EventSourcingHandler
    public void on(BookCreatedEvent bookCreatedEvent) {
        this.id = bookCreatedEvent.getId();
        this.name = bookCreatedEvent.getName();
        this.author = bookCreatedEvent.getAuthor();
        this.isReady = bookCreatedEvent.getIsReady();
    }

    @EventSourcingHandler
    public void on(BookUpdatedEvent bookUpdatedEvent) {
        this.id = bookUpdatedEvent.getId();
        this.name = bookUpdatedEvent.getName();
        this.author = bookUpdatedEvent.getAuthor();
        this.isReady = bookUpdatedEvent.getIsReady();
    }

    @EventSourcingHandler
    public void on(BookDeletedEvent bookDeletedEvent) {
        this.id = bookDeletedEvent.getId();
    }

}
