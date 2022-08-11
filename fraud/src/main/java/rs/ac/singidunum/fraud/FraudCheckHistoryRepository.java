package rs.ac.singidunum.fraud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Integer> {

    Optional<FraudCheckHistory> findByCustomerId(Integer customerId);

}
