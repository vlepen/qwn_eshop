package com.garwan.eshop.controller;

import com.garwan.eshop.model.CreateOrderRequest;
import com.garwan.eshop.model.EshopUser;
import com.garwan.eshop.model.Order;
import com.garwan.eshop.service.OrderService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
@Validated
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Order> create(
        @NotNull @Valid @RequestBody CreateOrderRequest createOrderRequest,
        @AuthenticationPrincipal EshopUser eshopUser
    )
        throws URISyntaxException {
        Order order = orderService.create(createOrderRequest, eshopUser);
        return ResponseEntity.created(new URI("/order/" + order.getId())).body(order);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Order>> getAll(@AuthenticationPrincipal EshopUser eshopUser) {
        return ResponseEntity.ok(orderService.findAllForUser(eshopUser.getId()));
    }
}
