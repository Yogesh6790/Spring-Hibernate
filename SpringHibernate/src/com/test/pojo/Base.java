
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
    "calctime",
    "city_id",
    "cnt",
    "cod",
    "list",
    "message"
})
public class Base {

    @JsonProperty("calctime")
    private Double calctime;
    @JsonProperty("city_id")
    private Integer city_id;
    @JsonProperty("cnt")
    private Integer cnt;
    @JsonProperty("cod")
    private String cod;
    @JsonProperty("list")
    private java.util.List<com.test.pojo.List> list = null;
    @JsonProperty("message")
    private String message;
    @JsonProperty("calctime")
    public Double getCalctime() {
        return calctime;
    }
    private String name;

    @JsonProperty("calctime")
    public void setCalctime(Double calctime) {
        this.calctime = calctime;
    }

    @JsonProperty("city_id")
    public Integer getCity_id() {
        return city_id;
    }

    @JsonProperty("city_id")
    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    @JsonProperty("cnt")
    public Integer getCnt() {
        return cnt;
    }

    @JsonProperty("cnt")
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    @JsonProperty("cod")
    public String getCod() {
        return cod;
    }

    @JsonProperty("cod")
    public void setCod(String cod) {
        this.cod = cod;
    }

    @JsonProperty("list")
    public java.util.List<com.test.pojo.List> getList() {
        return list;
    }

    @JsonProperty("list")
    public void setList(java.util.List<com.test.pojo.List> list) {
        this.list = list;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
