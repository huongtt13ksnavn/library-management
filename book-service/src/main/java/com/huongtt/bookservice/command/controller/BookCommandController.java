package com.huongtt.bookservice.command.controller;

import com.huongtt.bookservice.command.command.CreateBookCommand;
import com.huongtt.bookservice.command.command.DeleteBookCommand;
import com.huongtt.bookservice.command.command.UpdateBookCommand;
import com.huongtt.bookservice.command.model.BookRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/books")
public class BookCommandController {
    @Autowired
    CommandGateway commandGateway;

    @PostMapping
    public String addBook(@RequestBody BookRequestModel bookRequestModel) {
        CreateBookCommand createBookCommand = new CreateBookCommand(UUID.randomUUID().toString(),
                bookRequestModel.getName(), bookRequestModel.getAuthor(), bookRequestModel.getIsReady());
        commandGateway.sendAndWait(createBookCommand);
        return "added book";
    }

    @PutMapping
    public String updateBook(@RequestBody BookRequestModel bookRequestModel) {
        UpdateBookCommand updateBookCommand = new UpdateBookCommand(bookRequestModel.getId(),
                bookRequestModel.getName(), bookRequestModel.getAuthor(), bookRequestModel.getIsReady());
        commandGateway.sendAndWait(updateBookCommand);
        return "updated book";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteBook(@PathVariable String id) {
        DeleteBookCommand deleteBookCommand = new DeleteBookCommand(id);
        commandGateway.sendAndWait(deleteBookCommand);
        return "deleted book";
    }
}
