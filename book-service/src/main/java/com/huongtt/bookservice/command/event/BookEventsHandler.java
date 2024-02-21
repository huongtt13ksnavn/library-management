package com.huongtt.bookservice.command.event;

import com.huongtt.bookservice.command.command.DeleteBookCommand;
import com.huongtt.bookservice.command.data.Book;
import com.huongtt.bookservice.command.data.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookEventsHandler {
    @Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void on(BookCreatedEvent event) {
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookUpdatedEvent event) {
        try {
            Book book = bookRepository.findById(event.getId()).orElseThrow(() -> new Exception("Not found book id"));
            book.setAuthor(event.getAuthor());
            book.setName(event.getName());
            book.setIsReady(event.getIsReady());
            bookRepository.save(book);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @EventHandler
    public void on(BookDeletedEvent event) {
        bookRepository.deleteById(event.getId());
    }
}
