package rs.ac.singidunum.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.customer.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
