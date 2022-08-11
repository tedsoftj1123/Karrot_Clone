package com.example.karrotmarket.domain.controller;

import com.example.karrotmarket.domain.controller.dto.req.ItemRequest;
import com.example.karrotmarket.domain.controller.dto.res.AddItemResponse;
import com.example.karrotmarket.domain.controller.dto.res.ItemDetailResponse;
import com.example.karrotmarket.domain.controller.dto.res.ShowAllItemsResponse;
import com.example.karrotmarket.domain.entity.Member;
import com.example.karrotmarket.domain.facade.MemberFacade;
import com.example.karrotmarket.domain.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;
    private final MemberFacade memberFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddItemResponse addItem(@Valid @RequestBody ItemRequest itemRequest) {
        return itemService.addItem(itemRequest);
    }
    @GetMapping
    public List<ShowAllItemsResponse> main() {
        Member currentMember = memberFacade.getCurrentMember();
        return itemService.main(currentMember);
    }
    @GetMapping("/{itemId}")
    public ItemDetailResponse itemDetail(@PathVariable Long itemId) {
        return itemService.itemDetail(itemId);
    }
}
