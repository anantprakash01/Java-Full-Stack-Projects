package com.pritam.fabrication.ecommerce.controller;


import com.pritam.fabrication.ecommerce.exception.OrderException;
import com.pritam.fabrication.ecommerce.model.Order;
import com.pritam.fabrication.ecommerce.repository.OrderRepository;
import com.pritam.fabrication.ecommerce.response.ApiResponse;
import com.pritam.fabrication.ecommerce.response.PaymentLinkResponse;
import com.pritam.fabrication.ecommerce.service.OrderService;
import com.pritam.fabrication.ecommerce.service.UserService;
import com.pritam.fabrication.ecommerce.user.domain.OrderStatus;
import com.pritam.fabrication.ecommerce.user.domain.PaymentStatus;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Value("${razorpay.api.key}")
    String apiKey;

    @Value("${razorpay.api.secret}")
    String apiSecrete;


    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;


    public PaymentController(OrderService orderService, UserService userService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.userService = userService;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/payments/{orderId}")
    public ResponseEntity<PaymentLinkResponse>createPaymentLink(@PathVariable Long orderId,
                                                                @RequestHeader("Authorization") String jwt) throws OrderException,RazorpayException {

        Order order = orderService.findOrderById(orderId);

        try {
            RazorpayClient razorpay = new RazorpayClient(apiKey,apiSecrete);
            JSONObject paymentLinkRequest = new JSONObject();

            paymentLinkRequest.put("amount",order.getTotalPrice());
            paymentLinkRequest.put("currency","INR");

            JSONObject customer = new JSONObject();

            customer.put("name",order.getUser());
            customer.put("email",order.getUser().getEmail());
            paymentLinkRequest.put("customer",customer);

            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);
            paymentLinkRequest.put("notify",notify);

            paymentLinkRequest.put("callback_url","http://localhost:3000/payment/"+orderId);
            paymentLinkRequest.put("callback_method","get");

            PaymentLink paymentLink = razorpay.paymentLink.create(paymentLinkRequest);

            String paymentLinkId = paymentLink.get("id");
            String paymentLinkUrl = paymentLink.get("short_url");

            PaymentLinkResponse res = new PaymentLinkResponse();

            res.setPayment_link_id(paymentLinkId);
            res.setPayment_link_url(paymentLinkUrl);
            return new ResponseEntity<PaymentLinkResponse>(res, HttpStatus.CREATED);

        } catch (RazorpayException e) {
            throw new RazorpayException(e.getMessage());
        }

    }

    @GetMapping("/payments")
    public ResponseEntity<ApiResponse>redirect(@RequestParam(name="payment_id") String paymentId, @RequestParam(name="order_id")Long orderId) throws OrderException, RazorpayException {
        Order order = orderService.findOrderById(orderId);
        RazorpayClient razorpay = new RazorpayClient(apiKey,apiSecrete);

        System.out.println("paymentId "+paymentId+" orderId "+orderId);
        try {
            Payment payment = razorpay.payments.fetch(paymentId);
            if(payment.get("status").equals("captured")){
                System.out.println("Yes Its Working");
                order.getPaymentDetails().setPaymentId(paymentId);
                order.getPaymentDetails().setStatus(PaymentStatus.COMPLETED);
                order.setOrderStatus(OrderStatus.PLACED);
                orderRepository.save(order);
                System.out.println(order.getPaymentDetails().getStatus());
                System.out.println(order.getOrderStatus());


            }
            ApiResponse res = new ApiResponse();
            res.setMessage("Your order get placed");
            res.setStatus(true);

            return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
        }catch (Exception e){
            throw new RazorpayException(e.getMessage());
        }
    }
}
