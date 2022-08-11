package rs.ac.singidunum.fraud;

import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;


@RestController
@RequestMapping("api/v1/fraud-check")
public record FraudCheckController(FraudCheckService fraudCheckService) {

    @GetMapping(path = "{customerId}")
    public FraudsterResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudsterResponse(isFraudulentCustomer);
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
