package com.example.shopit.payment;

import com.example.shopit.dao.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepository;

    @Override
    public PaymentDetail proceedPayment(PaymentDetail paymentDetail) {
        PaymentUtil paymentUtil = new PaymentUtil();
        paymentDetail = paymentUtil.populatePaymentDetail(paymentDetail);
        savePaymentDetail(paymentDetail);
        return paymentDetail;
    }

    @Override
    public String payuCallback(PaymentCallback paymentResponse) {
        String msg = "Transaction failed.";
        Payment payment = paymentRepository.findByTxnId(paymentResponse.getTxnid());
        if(payment != null) {
            //TODO validate the hash
            PaymentStatus paymentStatus = null;
            if(paymentResponse.getStatus().equals("failure")){
                paymentStatus = PaymentStatus.Failed;
            }else if(paymentResponse.getStatus().equals("success")) {
                paymentStatus = PaymentStatus.Success;
                msg = "Transaction success";
            }
            payment.setPaymentStatus(paymentStatus);
            payment.setMihpayId(paymentResponse.getMihpayid());
            payment.setMode(paymentResponse.getMode());
            paymentRepository.save(payment);
        }
        return msg;
    }

    private void savePaymentDetail(PaymentDetail paymentDetail) {
        Payment payment = new Payment();
        payment.setAmount(Double.parseDouble(paymentDetail.getAmount()));
        payment.setEmail(paymentDetail.getEmail());
        payment.setName(paymentDetail.getName());
        payment.setPaymentDate(new Date());
        payment.setPaymentStatus(PaymentStatus.Pending);
        payment.setPhone(paymentDetail.getPhone());
        payment.setProductInfo(paymentDetail.getProductInfo());
        payment.setTxnId(paymentDetail.getTxnId());
        paymentRepository.save(payment);
    }

}
