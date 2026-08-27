package com.razarpay.razerpaydemo.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin(origins = "https://java-consultancy-website.vercel.app")
@RequestMapping("/razor-pay")
public class RazorPayController {
	
	
	@Autowired
	RazorPaymentService paymentService;
	
	
	@GetMapping("/test")
	public String test() {
		return "Hi";
	}
	
	  // CREATE ORDER
    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(
            @RequestBody CreateOrderRequest request) {

        try {

            String order =
                    paymentService.createOrder(request);

            return ResponseEntity.ok(order);

        } catch (Exception e) {

            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }


    // VERIFY PAYMENT
    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(
            @RequestBody PaymentVerificationRequest request) {

        try {

            boolean verified =
                    paymentService.verifyPayment(request);

            Map<String, Object> response =
                    new HashMap<>();

            if (verified) {

                response.put(
                        "status",
                        "SUCCESS"
                );

                response.put(
                        "message",
                        "Payment verified successfully"
                );

                return ResponseEntity.ok(response);

            } else {

                response.put(
                        "status",
                        "FAILED"
                );

                response.put(
                        "message",
                        "Payment verification failed"
                );

                return ResponseEntity
                        .badRequest()
                        .body(response);
            }

        } catch (Exception e) {

            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

}
