package com.example.karrotmarket.controller;

import com.example.karrotmarket.controller.dto.req.ItemRequest;
import com.example.karrotmarket.controller.dto.res.AddItemResponse;
import com.example.karrotmarket.controller.dto.res.ShowAllItemsResponse;
import com.example.karrotmarket.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public AddItemResponse addItem(@RequestBody ItemRequest itemRequest) {
        return itemService.addItem(itemRequest);
    }
    @GetMapping
    public List<ShowAllItemsResponse> main() {
        return itemService.main();
    }
}
