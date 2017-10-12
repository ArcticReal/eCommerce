package com.skytala.eCommerce.domain.shipment.relations.picklistRole.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.picklistRole.mapper.PicklistRoleMapper;

public class PicklistRole implements Serializable{

private static final long serialVersionUID = 1L;
private String picklistId;
private String partyId;
private String roleTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private String createdByUserLogin;
private String lastModifiedByUserLogin;

public String getPicklistId() {
return picklistId;
}

public void setPicklistId(String  picklistId) {
this.picklistId = picklistId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
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

public String getCreatedByUserLogin() {
return createdByUserLogin;
}

public void setCreatedByUserLogin(String  createdByUserLogin) {
this.createdByUserLogin = createdByUserLogin;
}

public String getLastModifiedByUserLogin() {
return lastModifiedByUserLogin;
}

public void setLastModifiedByUserLogin(String  lastModifiedByUserLogin) {
this.lastModifiedByUserLogin = lastModifiedByUserLogin;
}


public Map<String, Object> mapAttributeField() {
return PicklistRoleMapper.map(this);
}
}
