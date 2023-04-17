package com.cs370.springdemo;

import com.cs370.springdemo.model.Message;
import com.cs370.springdemo.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@SecurityRequirement(name = "basicAuth")
public class MessageRestController {

    @Autowired
    MessageService messageService;

    @Operation(summary = "Retrieve All Messages", tags = {"messages"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Message.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no Messages", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/messages")
    public Iterable<Message> getMessageList() {
        return messageService.getAll();
    }

    @Operation(summary = "Create Message", tags = {"messages"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Message.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/messages")
    public Message createMessage(@RequestBody Message message) {

        return messageService.insert(message);
    }

    @Operation(summary = "Retrieve Message By Id", tags = {"messages"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Message.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There is no message with such id", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/messages/{id}")
    public Message getMessageById(@PathVariable Long id) {
        return messageService.getById(id);
    }

    @Operation(summary = "Update Message By Id", tags = {"messages"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Message.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There is no message with such id", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/messages/{id}")
    public Message updateMessage(@RequestBody Message message, @PathVariable Long id) {

        return messageService.update(message, id);
    }

    @Operation(summary = "Delete Message By Id", tags = {"messages"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Message.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There is no message with such id", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/messages/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        messageService.delete(id);
    }
}
