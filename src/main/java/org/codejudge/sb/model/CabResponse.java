package org.codejudge.sb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TAYYAB
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabResponse {
    @JsonProperty("available_cabs")
    List<CabDetails> cabs = new ArrayList<>();

}
