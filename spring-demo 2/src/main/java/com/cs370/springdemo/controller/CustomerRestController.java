package com.cs370.springdemo.controller;

import com.cs370.springdemo.model.Customer;
import com.cs370.springdemo.service.CustomerService;
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
public class CustomerRestController {

    @Autowired
    CustomerService customerService;

    @Operation(summary = "Retrieve All Customers", tags = {"customers"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no customers", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/customers")
    public Iterable<Customer> getCustomerList() {
        return customerService.getAll();
    }

    @Operation(summary = "Create Customers", tags = {"customers"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {

        return customerService.insert(customer);
    }

    @Operation(summary = "Retrieve Customer By Id", tags = {"customers"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There is no customer with such id", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.getById(id);
    }

    @Operation(summary = "Update Customer By Id", tags = {"customers"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There is no customer with such id", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/customers/{id}")
    public Customer updateCustomers(@RequestBody Customer customer, @PathVariable String id) {

        return customerService.update(customer, id);
    }

    @Operation(summary = "Delete Customer By Id", tags = {"customers"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There is no customer with such id", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/customers/{id}")
    public void deleteEmployee(@PathVariable String id) {
        customerService.delete(id);
    }
}
