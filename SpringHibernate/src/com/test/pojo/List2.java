
package com.test.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "List2")
public class List2 {

	@Id
	@Column(name = "dt")
    private Integer dt;
	@Column(name = "main_humidity")
    private Integer main_humidity;
	@Column(name = "main_pressure")
    private Double main_pressure;
	@Column(name = "main_temp")
    private Double main_temp;
	@Column(name = "wind_speed")
    private Double wind_speed;
	@Column(name = "weather")
    private String weather;
    
    @ManyToOne
    private Base2 cityId;
    
    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

	public Integer getMain_humidity() {
		return main_humidity;
	}

	public void setMain_humidity(Integer main_humidity) {
		this.main_humidity = main_humidity;
	}

	public Double getMain_pressure() {
		return main_pressure;
	}

	public void setMain_pressure(Double main_pressure) {
		this.main_pressure = main_pressure;
	}

	public Double getMain_temp() {
		return main_temp;
	}

	public void setMain_temp(Double main_temp) {
		this.main_temp = main_temp;
	}

	public Double getWind_speed() {
		return wind_speed;
	}

	public void setWind_speed(Double wind_speed) {
		this.wind_speed = wind_speed;
	}

	public Base2 getCityId() {
		return cityId;
	}

	public void setCityId(Base2 cityId) {
		this.cityId = cityId;
	}

}
