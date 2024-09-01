package com.FoodCart.Services.IMPL;

import com.FoodCart.Entities.Order;
import com.FoodCart.Entities.PaymentResponseDTO;
import com.FoodCart.Services.Interfaces.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImplementation implements PaymentService {

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Override
    public PaymentResponseDTO generatePaymentLink(Order order) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("successUrl will be added" + order.getId()) // TODO
                .setCancelUrl("cancelUrl will be added" + order.getId()) // TODO
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount((long) order.getTotalAmount() * 100) // Specify the order amount in cents
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("pizza burger")
                                        .build())
                                .build())
                        .build())
                .build();

        Session session = Session.create(params);
        System.out.println("session _____ " + session);

        PaymentResponseDTO res = new PaymentResponseDTO();
        res.setPayment_url(session.getUrl());

        return res;
    }
}

