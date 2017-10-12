package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcDay.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcDay.mapper.TechDataCalendarExcDayMapper;

public class TechDataCalendarExcDay implements Serializable{

private static final long serialVersionUID = 1L;
private String calendarId;
private Timestamp exceptionDateStartTime;
private BigDecimal exceptionCapacity;
private BigDecimal usedCapacity;
private String description;

public String getCalendarId() {
return calendarId;
}

public void setCalendarId(String  calendarId) {
this.calendarId = calendarId;
}

public Timestamp getExceptionDateStartTime() {
return exceptionDateStartTime;
}

public void setExceptionDateStartTime(Timestamp  exceptionDateStartTime) {
this.exceptionDateStartTime = exceptionDateStartTime;
}

public BigDecimal getExceptionCapacity() {
return exceptionCapacity;
}

public void setExceptionCapacity(BigDecimal  exceptionCapacity) {
this.exceptionCapacity = exceptionCapacity;
}

public BigDecimal getUsedCapacity() {
return usedCapacity;
}

public void setUsedCapacity(BigDecimal  usedCapacity) {
this.usedCapacity = usedCapacity;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return TechDataCalendarExcDayMapper.map(this);
}
}
