package com.FoodCart.Services.Interfaces;

import com.FoodCart.Entities.Order;
import com.FoodCart.Response.PaymentResponseDTO;
import com.stripe.exception.StripeException;

public interface PaymentService {
    public PaymentResponseDTO generatePaymentLink(Order order) throws StripeException;
}
