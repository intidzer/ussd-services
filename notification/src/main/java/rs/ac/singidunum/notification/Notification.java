package rs.ac.singidunum.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "notification_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Integer id;
    private Integer toCustomerId;
    private String toCustomerEmail;
    private String message;
    private String sender;
    private LocalDateTime sentAt;

}
