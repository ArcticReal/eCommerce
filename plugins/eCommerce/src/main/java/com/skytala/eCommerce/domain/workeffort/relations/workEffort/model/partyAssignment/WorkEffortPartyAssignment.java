package com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.partyAssignment;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.partyAssignment.WorkEffortPartyAssignmentMapper;

public class WorkEffortPartyAssignment implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String partyId;
private String roleTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private String assignedByUserLoginId;
private String statusId;
private Timestamp statusDateTime;
private String expectationEnumId;
private String delegateReasonEnumId;
private String facilityId;
private String comments;
private Boolean mustRsvp;
private String availabilityStatusId;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
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

public String getAssignedByUserLoginId() {
return assignedByUserLoginId;
}

public void setAssignedByUserLoginId(String  assignedByUserLoginId) {
this.assignedByUserLoginId = assignedByUserLoginId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public Timestamp getStatusDateTime() {
return statusDateTime;
}

public void setStatusDateTime(Timestamp  statusDateTime) {
this.statusDateTime = statusDateTime;
}

public String getExpectationEnumId() {
return expectationEnumId;
}

public void setExpectationEnumId(String  expectationEnumId) {
this.expectationEnumId = expectationEnumId;
}

public String getDelegateReasonEnumId() {
return delegateReasonEnumId;
}

public void setDelegateReasonEnumId(String  delegateReasonEnumId) {
this.delegateReasonEnumId = delegateReasonEnumId;
}

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public Boolean getMustRsvp() {
return mustRsvp;
}

public void setMustRsvp(Boolean  mustRsvp) {
this.mustRsvp = mustRsvp;
}

public String getAvailabilityStatusId() {
return availabilityStatusId;
}

public void setAvailabilityStatusId(String  availabilityStatusId) {
this.availabilityStatusId = availabilityStatusId;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortPartyAssignmentMapper.map(this);
}
}
