package com.example.springbootsecuritywithjwt.controllers;

import com.example.springbootsecuritywithjwt.models.ItemInfo;
import com.example.springbootsecuritywithjwt.models.ItemRequest;
import com.example.springbootsecuritywithjwt.models.ItemResponse;
import com.example.springbootsecuritywithjwt.services.item.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "Item", description = "API for item.")
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Operation(summary = "All Items", description = "Find all items.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ItemResponse.class)))) })
    @GetMapping("/item/all")
    public ResponseEntity<?> index() {
        final List<ItemInfo> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @Operation(summary = "Find Item", description = "Find item by name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = @Content(schema = @Schema(implementation = ItemResponse.class))) })
    @GetMapping("/item/{id}")
    public ResponseEntity<?> searchItem(@PathVariable int id) {
        final ItemInfo item = itemService.getItem(id);
        return ResponseEntity.ok(new ItemResponse(item.getId(), item.getItemName()));
    }

    @Operation(summary = "Create Item", description = "Create a new item.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation.",
                    content = @Content(schema = @Schema(implementation = ItemResponse.class))) })
    @PostMapping("/item")
    public ResponseEntity<?> createItem(@RequestBody ItemRequest itemRequest) {
        final ItemInfo newItem = itemService.createItem(itemRequest.getItemName());
        return new ResponseEntity(new ItemResponse(newItem.getId(), newItem.getItemName()), HttpStatus.CREATED);
    }

    @Operation(summary = "Update Item", description = "Update an item.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation.",
                    content = @Content(schema = @Schema(implementation = ItemResponse.class))) })
    @PutMapping("/item/{id}/update")
    public ResponseEntity<?> updateItem(@PathVariable int id, @RequestBody ItemRequest itemRequest) {
        final ItemInfo itemToUpdate = itemService.updateItem(id, itemRequest.getItemName());
        return new ResponseEntity(new ItemResponse(itemToUpdate.getId(), itemToUpdate.getItemName()), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete Item", description = "Delete an item.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation.",
                    content = @Content(schema = @Schema(implementation = ItemResponse.class))) })
    @DeleteMapping("/item/{id}/delete")
    public ResponseEntity<?> deleteItem(@PathVariable int id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
