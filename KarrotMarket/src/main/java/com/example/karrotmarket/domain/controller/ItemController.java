package com.example.karrotmarket.domain.controller;

import com.example.karrotmarket.domain.controller.dto.req.ItemRequest;
import com.example.karrotmarket.domain.controller.dto.res.AddItemResponse;
import com.example.karrotmarket.domain.controller.dto.res.ItemDetailResponse;
import com.example.karrotmarket.domain.controller.dto.res.ShowAllItemsResponse;
import com.example.karrotmarket.domain.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddItemResponse addItem(@RequestBody ItemRequest itemRequest) {
        return itemService.addItem(itemRequest);
    }
    @GetMapping
    public List<ShowAllItemsResponse> main() {
        return itemService.main();
    }
    @GetMapping("/{itemId}")
    public ItemDetailResponse itemDetail(@PathVariable Long itemId) {
        return itemService.itemDetail(itemId);
    }
}
