package com.FoodCart.Controllers;

import com.FoodCart.Services.Interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/payment/success/{orderId}")
    public String paymentSuccess(@PathVariable Long orderId) {
        return "Payment successful for order " + orderId;
    }
}
