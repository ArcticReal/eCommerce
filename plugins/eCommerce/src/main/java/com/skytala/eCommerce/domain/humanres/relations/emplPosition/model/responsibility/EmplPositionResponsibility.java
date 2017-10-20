package com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.responsibility;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.responsibility.EmplPositionResponsibilityMapper;

public class EmplPositionResponsibility implements Serializable{

private static final long serialVersionUID = 1L;
private String emplPositionId;
private String responsibilityTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private String comments;

public String getEmplPositionId() {
return emplPositionId;
}

public void setEmplPositionId(String  emplPositionId) {
this.emplPositionId = emplPositionId;
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
return EmplPositionResponsibilityMapper.map(this);
}
}
