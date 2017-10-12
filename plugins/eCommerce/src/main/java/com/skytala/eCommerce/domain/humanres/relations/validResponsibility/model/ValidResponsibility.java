package com.skytala.eCommerce.domain.humanres.relations.validResponsibility.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.mapper.ValidResponsibilityMapper;

public class ValidResponsibility implements Serializable{

private static final long serialVersionUID = 1L;
private String emplPositionTypeId;
private String responsibilityTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private String comments;

public String getEmplPositionTypeId() {
return emplPositionTypeId;
}

public void setEmplPositionTypeId(String  emplPositionTypeId) {
this.emplPositionTypeId = emplPositionTypeId;
}

public String getResponsibilityTypeId() {
return responsibilityTypeId;
}

public void setResponsibilityTypeId(String  responsibilityTypeId) {
this.responsibilityTypeId = responsibilityTypeId;
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

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return ValidResponsibilityMapper.map(this);
}
}
