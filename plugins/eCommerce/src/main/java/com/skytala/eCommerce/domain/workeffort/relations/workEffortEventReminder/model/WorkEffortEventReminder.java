package com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.mapper.WorkEffortEventReminderMapper;

public class WorkEffortEventReminder implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String sequenceId;
private String contactMechId;
private String partyId;
private Timestamp reminderDateTime;
private Long repeatCount;
private Long repeatInterval;
private Long currentCount;
private Long reminderOffset;
private String localeId;
private String timeZoneId;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getSequenceId() {
return sequenceId;
}

public void setSequenceId(String  sequenceId) {
this.sequenceId = sequenceId;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public Timestamp getReminderDateTime() {
return reminderDateTime;
}

public void setReminderDateTime(Timestamp  reminderDateTime) {
this.reminderDateTime = reminderDateTime;
}

public Long getRepeatCount() {
return repeatCount;
}

public void setRepeatCount(Long  repeatCount) {
this.repeatCount = repeatCount;
}

public Long getRepeatInterval() {
return repeatInterval;
}

public void setRepeatInterval(Long  repeatInterval) {
this.repeatInterval = repeatInterval;
}

public Long getCurrentCount() {
return currentCount;
}

public void setCurrentCount(Long  currentCount) {
this.currentCount = currentCount;
}

public Long getReminderOffset() {
return reminderOffset;
}

public void setReminderOffset(Long  reminderOffset) {
this.reminderOffset = reminderOffset;
}

public String getLocaleId() {
return localeId;
}

public void setLocaleId(String  localeId) {
this.localeId = localeId;
}

public String getTimeZoneId() {
return timeZoneId;
}

public void setTimeZoneId(String  timeZoneId) {
this.timeZoneId = timeZoneId;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortEventReminderMapper.map(this);
}
}
