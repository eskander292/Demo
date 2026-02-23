package com.example.demo.controller;

import com.example.demo.dto.ApiError;
import com.example.demo.dto.ItemRequest;
import com.example.demo.model.Item;
import com.example.demo.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Items", description = "CRUD operations for items")
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @Operation(summary = "Get all items", description = "Returns all items sorted by id.")
    @ApiResponse(responseCode = "200", description = "List of items")
    @GetMapping
    public List<Item> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get item by id", description = "Returns a single item by its id.")
    @ApiResponse(responseCode = "200", description = "Item found")
    @ApiResponse(
            responseCode = "404",
            description = "Item not found",
            content = @Content(schema = @Schema(implementation = ApiError.class))
    )
    @GetMapping("/{id}")
    public Item getById(
            @Parameter(description = "Item id", example = "1")
            @PathVariable Long id
    ) {
        return service.getById(id);
    }

    @Operation(summary = "Create a new item", description = "Creates an item and returns it.")
    @ApiResponse(responseCode = "201", description = "Item created")
    @ApiResponse(
            responseCode = "400",
            description = "Validation error",
            content = @Content(schema = @Schema(implementation = ApiError.class))
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@Valid @RequestBody ItemRequest req) {
        return service.create(req);
    }

    @Operation(summary = "Update an item", description = "Updates an existing item by id.")
    @ApiResponse(responseCode = "200", description = "Item updated")
    @ApiResponse(
            responseCode = "400",
            description = "Validation error",
            content = @Content(schema = @Schema(implementation = ApiError.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Item not found",
            content = @Content(schema = @Schema(implementation = ApiError.class))
    )
    @PutMapping("/{id}")
    public Item update(
            @Parameter(description = "Item id", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ItemRequest req
    ) {
        return service.update(id, req);
    }

    @Operation(summary = "Delete an item", description = "Deletes an item by id.")
    @ApiResponse(responseCode = "204", description = "Item deleted")
    @ApiResponse(
            responseCode = "404",
            description = "Item not found",
            content = @Content(schema = @Schema(implementation = ApiError.class))
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(description = "Item id", example = "1")
            @PathVariable Long id
    ) {
        service.delete(id);
    }
}