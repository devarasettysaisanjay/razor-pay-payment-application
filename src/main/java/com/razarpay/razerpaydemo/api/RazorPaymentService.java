package com.razarpay.razerpaydemo.api;


import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;

@Service
public class RazorPaymentService {
	
	  @Value("${razorpay.key.id}")
	    private String keyId;

	    @Value("${razorpay.key.secret}")
	    private String keySecret;
	
	
	 // CREATE RAZORPAY ORDER
  public String createOrder(CreateOrderRequest request) throws Exception {

      RazorpayClient razorpayClient =
              new RazorpayClient(keyId, keySecret);

      JSONObject orderRequest = new JSONObject();

      orderRequest.put(
              "amount",
              request.getAmount()
      );

      orderRequest.put(
              "currency",
              request.getCurrency()
      );

      orderRequest.put(
              "receipt",
              "receipt_" + System.currentTimeMillis()
      );

      Order order =
              razorpayClient.orders.create(orderRequest);

      return order.toString();
  }


  // VERIFY PAYMENT
  public boolean verifyPayment(
          PaymentVerificationRequest request) throws Exception {

      JSONObject options = new JSONObject();

      options.put(
              "razorpay_order_id",
              request.getRazorpayOrderId()
      );

      options.put(
              "razorpay_payment_id",
              request.getRazorpayPaymentId()
      );

      options.put(
              "razorpay_signature",
              request.getRazorpaySignature()
      );

      return Utils.verifyPaymentSignature(
              options,
              keySecret
      );
  }
  
  @Scheduled(fixedRate = 600000)
  public void healthCheck() {

      System.out.println(
          "Health scheduler running at: ");
  }

  
  

}
