package com.skytala.eCommerce.domain.workeffort.relations.timesheet.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.mapper.TimesheetMapper;

public class Timesheet implements Serializable{

private static final long serialVersionUID = 1L;
private String timesheetId;
private String partyId;
private String clientPartyId;
private Timestamp fromDate;
private Timestamp thruDate;
private String statusId;
private String approvedByUserLoginId;
private String comments;

public String getTimesheetId() {
return timesheetId;
}

public void setTimesheetId(String  timesheetId) {
this.timesheetId = timesheetId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getClientPartyId() {
return clientPartyId;
}

public void setClientPartyId(String  clientPartyId) {
this.clientPartyId = clientPartyId;
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

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getApprovedByUserLoginId() {
return approvedByUserLoginId;
}

public void setApprovedByUserLoginId(String  approvedByUserLoginId) {
this.approvedByUserLoginId = approvedByUserLoginId;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return TimesheetMapper.map(this);
}
}
