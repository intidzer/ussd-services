package rs.ac.singidunum.fraud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public record FraudCheckService(FraudCheckHistoryRepository repository) {

    public boolean isFraudulentCustomer(Integer customerId) {
        Optional<FraudCheckHistory> result = repository.findByCustomerId(customerId);

        if (result.isPresent()) {
            return result.get().getIsFraudster();
        } else {
            return false;
        }
    }

    public void recordFraudster(FraudCheckHistory fraudCheckHistory) {
        repository.save(fraudCheckHistory);
    }
}
