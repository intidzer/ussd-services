package rs.ac.singidunum.customer.models;

public record CustomerRegistrationRequest(
                String firstName,
                String lastName,
                String email,
                String mobileNumber) {

}
