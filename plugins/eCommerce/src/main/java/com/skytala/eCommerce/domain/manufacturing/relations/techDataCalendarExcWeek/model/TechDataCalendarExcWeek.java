package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.mapper.TechDataCalendarExcWeekMapper;

public class TechDataCalendarExcWeek implements Serializable{

private static final long serialVersionUID = 1L;
private String calendarId;
private Timestamp exceptionDateStart;
private String calendarWeekId;
private String description;

public String getCalendarId() {
return calendarId;
}

public void setCalendarId(String  calendarId) {
this.calendarId = calendarId;
}

public Timestamp getExceptionDateStart() {
return exceptionDateStart;
}

public void setExceptionDateStart(Timestamp  exceptionDateStart) {
this.exceptionDateStart = exceptionDateStart;
}

public String getCalendarWeekId() {
return calendarWeekId;
}

public void setCalendarWeekId(String  calendarWeekId) {
this.calendarWeekId = calendarWeekId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return TechDataCalendarExcWeekMapper.map(this);
}
}
