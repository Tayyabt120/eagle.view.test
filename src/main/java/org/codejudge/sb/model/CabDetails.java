package org.codejudge.sb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TAYYAB
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabDetails {
    private String name;
    @JsonProperty("car_number")
    private String carNumber;
    @JsonProperty("phone_number")
    private String phone;
}