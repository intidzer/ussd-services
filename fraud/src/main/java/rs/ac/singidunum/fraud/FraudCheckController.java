package rs.ac.singidunum.fraud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.clients.fraud.FraudCheckResponse;

import javax.persistence.PostUpdate;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
public record FraudCheckController(FraudCheckService fraudCheckService) {

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        log.info("Fraud check for customer {}", customerId);
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }

    @PostMapping
    public void recordFraudster(@RequestBody FraudCheckHistory fraudCheckHistory) {
        fraudCheckService.recordFraudster(fraudCheckHistory);
    }

    @PostUpdate
    public void updateFraudster(@RequestBody FraudCheckHistory fraudCheckHistory) {
        // TODO: 8/11/2022 update fraud
    }

}
