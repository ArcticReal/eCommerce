package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.partyAssignment;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.partyAssignment.PartyFixedAssetAssignmentMapper;

public class PartyFixedAssetAssignment implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String roleTypeId;
private String fixedAssetId;
private Timestamp fromDate;
private Timestamp thruDate;
private Timestamp allocatedDate;
private String statusId;
private String comments;

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

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
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

public Timestamp getAllocatedDate() {
return allocatedDate;
}

public void setAllocatedDate(Timestamp  allocatedDate) {
this.allocatedDate = allocatedDate;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return PartyFixedAssetAssignmentMapper.map(this);
}
}
