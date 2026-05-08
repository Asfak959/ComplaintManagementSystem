package com.example.BloggingProject.Controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class RazorpayService {

    @Autowired
    private RazorpayClient client;

    @GetMapping("/createOrder")
    public String createOrder(@RequestParam int amount) throws Exception {
        System.out.println("API Hit Ho Gayi");
        JSONObject options = new JSONObject();

        options.put("amount", amount * 100);
        options.put("currency", "INR");
        options.put("receipt", "order_" + System.currentTimeMillis());

        Order order = client.orders.create(options);

        return order.toString();
    }
}