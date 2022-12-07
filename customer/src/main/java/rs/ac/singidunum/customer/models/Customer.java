package rs.ac.singidunum.customer.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "email_unique_constraint", columnNames = "email")})
public class Customer {

    @Id
    @SequenceGenerator(
        name = "customer_id_sequence",
        sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "customer_id_sequence"
    )
    private Integer id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    @Email(message = "Invalid email")
    @NotEmpty(message = "Email is empty")
    private String email;

}
