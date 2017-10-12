package com.skytala.eCommerce.domain.workeffort.relations.timeEntry.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.timeEntry.mapper.TimeEntryMapper;

public class TimeEntry implements Serializable{

private static final long serialVersionUID = 1L;
private String timeEntryId;
private String partyId;
private Timestamp fromDate;
private Timestamp thruDate;
private String rateTypeId;
private String workEffortId;
private String timesheetId;
private String invoiceId;
private String invoiceItemSeqId;
private BigDecimal hours;
private String comments;

public String getTimeEntryId() {
return timeEntryId;
}

public void setTimeEntryId(String  timeEntryId) {
this.timeEntryId = timeEntryId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
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

public String getRateTypeId() {
return rateTypeId;
}

public void setRateTypeId(String  rateTypeId) {
this.rateTypeId = rateTypeId;
}

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getTimesheetId() {
return timesheetId;
}

public void setTimesheetId(String  timesheetId) {
this.timesheetId = timesheetId;
}

public String getInvoiceId() {
return invoiceId;
}

public void setInvoiceId(String  invoiceId) {
this.invoiceId = invoiceId;
}

public String getInvoiceItemSeqId() {
return invoiceItemSeqId;
}

public void setInvoiceItemSeqId(String  invoiceItemSeqId) {
this.invoiceItemSeqId = invoiceItemSeqId;
}

public BigDecimal getHours() {
return hours;
}

public void setHours(BigDecimal  hours) {
this.hours = hours;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return TimeEntryMapper.map(this);
}
}
