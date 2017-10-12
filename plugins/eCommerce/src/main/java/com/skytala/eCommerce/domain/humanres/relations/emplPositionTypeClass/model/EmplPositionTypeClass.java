package com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.mapper.EmplPositionTypeClassMapper;

public class EmplPositionTypeClass implements Serializable{

private static final long serialVersionUID = 1L;
private String emplPositionTypeId;
private String emplPositionClassTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private BigDecimal standardHoursPerWeek;

public String getEmplPositionTypeId() {
return emplPositionTypeId;
}

public void setEmplPositionTypeId(String  emplPositionTypeId) {
this.emplPositionTypeId = emplPositionTypeId;
}

public String getEmplPositionClassTypeId() {
return emplPositionClassTypeId;
}

public void setEmplPositionClassTypeId(String  emplPositionClassTypeId) {
this.emplPositionClassTypeId = emplPositionClassTypeId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}

public BigDecimal getStandardHoursPerWeek() {
return standardHoursPerWeek;
}

public void setStandardHoursPerWeek(BigDecimal  standardHoursPerWeek) {
this.standardHoursPerWeek = standardHoursPerWeek;
}


public Map<String, Object> mapAttributeField() {
return EmplPositionTypeClassMapper.map(this);
}
}
