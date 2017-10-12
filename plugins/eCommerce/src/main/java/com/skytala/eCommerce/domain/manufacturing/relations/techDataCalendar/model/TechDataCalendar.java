package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper.TechDataCalendarMapper;

public class TechDataCalendar implements Serializable{

private static final long serialVersionUID = 1L;
private String calendarId;
private String description;
private String calendarWeekId;

public String getCalendarId() {
return calendarId;
}

public void setCalendarId(String  calendarId) {
this.calendarId = calendarId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getCalendarWeekId() {
return calendarWeekId;
}

public void setCalendarWeekId(String  calendarWeekId) {
this.calendarWeekId = calendarWeekId;
}


public Map<String, Object> mapAttributeField() {
return TechDataCalendarMapper.map(this);
}
}
