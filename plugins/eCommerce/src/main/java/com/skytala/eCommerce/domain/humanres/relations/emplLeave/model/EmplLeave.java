package com.skytala.eCommerce.domain.humanres.relations.emplLeave.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.mapper.EmplLeaveMapper;

public class EmplLeave implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String leaveTypeId;
private String emplLeaveReasonTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private String approverPartyId;
private String leaveStatus;
private String description;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getLeaveTypeId() {
return leaveTypeId;
}

public void setLeaveTypeId(String  leaveTypeId) {
this.leaveTypeId = leaveTypeId;
}

public String getEmplLeaveReasonTypeId() {
return emplLeaveReasonTypeId;
}

public void setEmplLeaveReasonTypeId(String  emplLeaveReasonTypeId) {
this.emplLeaveReasonTypeId = emplLeaveReasonTypeId;
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

public String getApproverPartyId() {
return approverPartyId;
}

public void setApproverPartyId(String  approverPartyId) {
this.approverPartyId = approverPartyId;
}

public String getLeaveStatus() {
return leaveStatus;
}

public void setLeaveStatus(String  leaveStatus) {
this.leaveStatus = leaveStatus;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return EmplLeaveMapper.map(this);
}
}
