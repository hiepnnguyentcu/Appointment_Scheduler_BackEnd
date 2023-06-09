package edu.tcu.cs.superfrogscheduler.paymentform;

import edu.tcu.cs.superfrogscheduler.paymentform.dto.RequestIds;
import edu.tcu.cs.superfrogscheduler.paymentform.util.Period;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentFormController {
    private PaymentFormService paymentService;

    public PaymentFormController(PaymentFormService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/api/v1/payment-forms")
    public Result generatePaymentForms(@RequestBody RequestIds requestIds) {
        List<Integer> selectedIds = requestIds.getRequestIds();

        Period periodRange = requestIds.getPeriodRange();

        List<PaymentForm> paymentForms = this.paymentService.generatePaymentForms(selectedIds, periodRange);

        return new Result(true, StatusCode.SUCCESS, "Payment forms are generated successfully.", paymentForms);
    }
}
