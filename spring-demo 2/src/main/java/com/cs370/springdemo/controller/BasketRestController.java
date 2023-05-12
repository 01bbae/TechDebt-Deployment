package com.cs370.springdemo.controller;

import com.cs370.springdemo.model.Basket;
import com.cs370.springdemo.service.BasketService;
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
public class BasketRestController {

    @Autowired
    BasketService basketService;

    @Operation(summary = "Retrieve All Baskets", tags = {"baskets"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Basket.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no baskets", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/baskets")
    public Iterable<Basket> getBasketList() {
        return basketService.getAll();
    }

    @Operation(summary = "Create Baskets", tags = {"baskets"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Basket.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/baskets")
    public Basket createBasket(@RequestBody Basket basket) {

        return basketService.insert(basket);
    }

    @Operation(summary = "Retrieve Basket By Id", tags = {"baskets"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Basket.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There is no basket with such id", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/baskets/{id}")
    public Basket getBasketById(@PathVariable String id) {
        return basketService.getById(id);
    }

    @Operation(summary = "Update Basket By Id", tags = {"baskets"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Basket.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There is no basket with such id", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/baskets/{id}")
    public Basket updateBaskets(@RequestBody Basket basket, @PathVariable String id) {

        return basketService.update(basket, id);
    }

    @Operation(summary = "Delete Basket By Id", tags = {"baskets"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Basket.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There is no basket with such id", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/baskets/{id}")
    public void deleteEmployee(@PathVariable String id) {
        basketService.delete(id);
    }
}
