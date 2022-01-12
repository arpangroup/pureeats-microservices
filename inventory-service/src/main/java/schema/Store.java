package schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Store implements Serializable {
    private static final long serialVersionUID = 1369279224740734441L;
    private Integer id;
    private String name;
}
