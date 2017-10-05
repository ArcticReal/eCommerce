package com.skytala.eCommerce.domain.unemploymentClaim.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.unemploymentClaim.mapper.UnemploymentClaimMapper;

public class UnemploymentClaim implements Serializable{

private static final long serialVersionUID = 1L;
private String unemploymentClaimId;
private Timestamp unemploymentClaimDate;
private String description;
private String statusId;
private String partyIdFrom;
private String partyIdTo;
private String roleTypeIdFrom;
private String roleTypeIdTo;
private Timestamp fromDate;
private Timestamp thruDate;

public String getUnemploymentClaimId() {
return unemploymentClaimId;
}

public void setUnemploymentClaimId(String  unemploymentClaimId) {
this.unemploymentClaimId = unemploymentClaimId;
}

public Timestamp getUnemploymentClaimDate() {
return unemploymentClaimDate;
}

public void setUnemploymentClaimDate(Timestamp  unemploymentClaimDate) {
this.unemploymentClaimDate = unemploymentClaimDate;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
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


public Map<String, Object> mapAttributeField() {
return UnemploymentClaimMapper.map(this);
}
}
