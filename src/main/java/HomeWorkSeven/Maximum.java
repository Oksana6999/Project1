package HomeWorkSeven;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Maximum {
    @Override
    public String toString() {
        return "Maximum {" +
                "Unit " + unit + '\'' +
                ", UnitType" + unitType +
                ", Value " + value +
                "}";

    }

    @JsonProperty(value = "Unit")
    private String unit;

    @JsonProperty (value = "UnitType")
    private String unitType;

    @JsonProperty (value = "Value")
    private String value;
}
