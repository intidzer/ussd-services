package rs.ac.singidunum.fraud;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record FraudCheckService(FraudCheckHistoryRepository repository) {

    public boolean isFraudulentCustomer(Integer customerId) {
        Optional<FraudCheckHistory> result = Optional.of(repository.getReferenceById(customerId));

        if (result.isPresent())
            return true;
        else
            return false;
    }

    public void recordFraudster(FraudCheckHistory fraudCheckHistory) {
        repository.save(fraudCheckHistory);
    }
}
