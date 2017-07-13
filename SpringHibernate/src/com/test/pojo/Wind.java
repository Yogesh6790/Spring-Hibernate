
package com.test.pojo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "deg",
    "speed"
})
public class Wind {

    @JsonProperty("deg")
    private Double deg;
    @JsonProperty("speed")
    private Double speed;

    @JsonProperty("deg")
    public Double getDeg() {
        return deg;
    }

    @JsonProperty("deg")
    public void setDeg(Double deg) {
        this.deg = deg;
    }

    @JsonProperty("speed")
    public Double getSpeed() {
        return speed;
    }

    @JsonProperty("speed")
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

}
