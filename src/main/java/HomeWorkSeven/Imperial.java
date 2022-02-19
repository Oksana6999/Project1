package HomeWorkSeven;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Imperial {
    @Override
    public String toString () {
        return "Imperial {" +
                "Unit " + '\'' +
                ", UnitType " + unitType +
                ", Value " + value +
                "}";
    }
    @JsonProperty(value = "Unit")
    private String Unit;

    @JsonIgnore
    private String unit;

    @JsonProperty(value = "UnitType")
    private float UnitType;

    @JsonIgnore
    private float unitType;

    @JsonProperty(value = "Value")
    private float Value;

    @JsonIgnore
    private float value;
}
