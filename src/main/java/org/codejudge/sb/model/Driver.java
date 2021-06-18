package org.codejudge.sb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * @author TAYYAB
 */
@Data
@NoArgsConstructor
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "the name field is missing")
    private String name;

    @Column(unique=true)
    @NotBlank(message = "the email field is missing")
    private String email;

    @Length(min = 10, max = 10)
    @Column(unique=true)
    @NotNull(message = "the phone_number field is missing")
    @JsonProperty("phone_number")
    private String phone;

    @Column(unique=true)
    @NotBlank(message = "the license_number field is missing")
    @JsonProperty("license_number")
    private String licenseNumber;

    @Column(unique=true)
    @NotBlank(message = "the car_number field is missing")
    @JsonProperty("car_number")
    private String carNumber;

}
