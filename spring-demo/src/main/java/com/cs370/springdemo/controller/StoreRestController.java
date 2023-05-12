package com.cs370.springdemo.controller;

import com.cs370.springdemo.model.Store;
import com.cs370.springdemo.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "basicAuth")
public class StoreRestController {

    @Autowired
    StoreService storeService;

    @Operation(summary = "Retrieve All Stores", tags = {"stores"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Store.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no Stores", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/stores")
    public Iterable<Store> getStoreList() {
        return storeService.getAll();
    }

    @Operation(summary = "Create Stores", tags = {"stores"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Store.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/stores")
    public Store createStore(@RequestBody Store store) {

        return storeService.insert(store);
    }

    @Operation(summary = "Retrieve Store By Id", tags = {"stores"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Store.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There is no store with such id", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/stores/{id}")
    public Store getStoreById(@PathVariable String id) {
        return storeService.getById(id);
    }

    @Operation(summary = "Update Store By Id", tags = {"stores"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Store.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There is no store with such id", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/stores/{id}")
    public Store updateStores(@RequestBody Store store, @PathVariable String id) {

        return storeService.update(store, id);
    }

    @Operation(summary = "Delete Store By Id", tags = {"stores"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Store.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There is no store with such id", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/stores/{id}")
    public void deleteEmployee(@PathVariable String id) {
        storeService.delete(id);
    }
}
