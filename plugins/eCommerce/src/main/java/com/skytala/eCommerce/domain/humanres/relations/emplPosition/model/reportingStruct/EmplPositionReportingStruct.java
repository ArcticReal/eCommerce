package com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.reportingStruct;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.reportingStruct.EmplPositionReportingStructMapper;

public class EmplPositionReportingStruct implements Serializable{

private static final long serialVersionUID = 1L;
private String emplPositionIdReportingTo;
private String emplPositionIdManagedBy;
private Timestamp fromDate;
private Timestamp thruDate;
private String comments;
private Boolean primaryFlag;

public String getEmplPositionIdReportingTo() {
return emplPositionIdReportingTo;
}

public void setEmplPositionIdReportingTo(String  emplPositionIdReportingTo) {
this.emplPositionIdReportingTo = emplPositionIdReportingTo;
}

public String getEmplPositionIdManagedBy() {
return emplPositionIdManagedBy;
}

public void setEmplPositionIdManagedBy(String  emplPositionIdManagedBy) {
this.emplPositionIdManagedBy = emplPositionIdManagedBy;
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

public Boolean getPrimaryFlag() {
return primaryFlag;
}

public void setPrimaryFlag(Boolean  primaryFlag) {
this.primaryFlag = primaryFlag;
}


public Map<String, Object> mapAttributeField() {
return EmplPositionReportingStructMapper.map(this);
}
}
