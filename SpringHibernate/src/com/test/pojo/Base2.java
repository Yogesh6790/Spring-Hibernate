
package com.test.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Base2", uniqueConstraints = @UniqueConstraint(columnNames = "cityId"))
public class Base2 {

	@Id
	@Column(name = "cityId")
    private Integer cityId;
	
	@Column(name = "name")
    private String name;
    
	@OneToMany(targetEntity = List2.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cityId")
    private java.util.List<com.test.pojo.List2> list = null;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    public java.util.List<com.test.pojo.List2> getList() {
        return list;
    }

    public void setList(java.util.List<com.test.pojo.List2> list) {
        this.list = list;
    }

}
