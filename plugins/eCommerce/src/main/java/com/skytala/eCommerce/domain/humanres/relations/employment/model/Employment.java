package com.skytala.eCommerce.domain.humanres.relations.employment.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.employment.mapper.EmploymentMapper;

public class Employment implements Serializable{

private static final long serialVersionUID = 1L;
private String roleTypeIdFrom;
private String roleTypeIdTo;
private String partyIdFrom;
private String partyIdTo;
private Timestamp fromDate;
private Timestamp thruDate;
private String terminationReasonId;
private String terminationTypeId;

public String getRoleTypeIdFrom() {
return roleTypeIdFrom;
}

public void setRoleTypeIdFrom(String  roleTypeIdFrom) {
this.roleTypeIdFrom = roleTypeIdFrom;
}

public String getRoleTypeIdTo() {
return roleTypeIdTo;
}

public void setRoleTypeIdTo(String  roleTypeIdTo) {
this.roleTypeIdTo = roleTypeIdTo;
}

public String getPartyIdFrom() {
return partyIdFrom;
}

public void setPartyIdFrom(String  partyIdFrom) {
this.partyIdFrom = partyIdFrom;
}

public String getPartyIdTo() {
return partyIdTo;
}

public void setPartyIdTo(String  partyIdTo) {
this.partyIdTo = partyIdTo;
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

public String getTerminationReasonId() {
return terminationReasonId;
}

public void setTerminationReasonId(String  terminationReasonId) {
this.terminationReasonId = terminationReasonId;
}

public String getTerminationTypeId() {
return terminationTypeId;
}

public void setTerminationTypeId(String  terminationTypeId) {
this.terminationTypeId = terminationTypeId;
}


public Map<String, Object> mapAttributeField() {
return EmploymentMapper.map(this);
}
}
