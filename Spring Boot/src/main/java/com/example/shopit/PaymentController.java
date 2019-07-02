package com.example.shopit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(path = "/payment-details")
    public @ResponseBody
    PaymentDetail proceedPayment(@RequestBody PaymentDetail paymentDetail){
        return paymentService.proceedPayment(paymentDetail);
    }

    @RequestMapping(path = "/payment-response", method = RequestMethod.POST)
    public @ResponseBody String payuCallback(@RequestParam String mihpayid, @RequestParam String status, @RequestParam PaymentMode mode, @RequestParam String txnid, @RequestParam String hash){
        PaymentCallback paymentCallback = new PaymentCallback();
        paymentCallback.setMihpayid(mihpayid);
        paymentCallback.setTxnid(txnid);
        paymentCallback.setMode(mode);
        paymentCallback.setHash(hash);
        paymentCallback.setStatus(status);
        return paymentService.payuCallback(paymentCallback);
    }
}
