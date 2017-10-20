package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.week;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper.week.TechDataCalendarWeekMapper;

public class TechDataCalendarWeek implements Serializable{

private static final long serialVersionUID = 1L;
private String calendarWeekId;
private String description;
private Timestamp mondayStartTime;
private BigDecimal mondayCapacity;
private Timestamp tuesdayStartTime;
private BigDecimal tuesdayCapacity;
private Timestamp wednesdayStartTime;
private BigDecimal wednesdayCapacity;
private Timestamp thursdayStartTime;
private BigDecimal thursdayCapacity;
private Timestamp fridayStartTime;
private BigDecimal fridayCapacity;
private Timestamp saturdayStartTime;
private BigDecimal saturdayCapacity;
private Timestamp sundayStartTime;
private BigDecimal sundayCapacity;

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

public Timestamp getMondayStartTime() {
return mondayStartTime;
}

public void setMondayStartTime(Timestamp  mondayStartTime) {
this.mondayStartTime = mondayStartTime;
}

public BigDecimal getMondayCapacity() {
return mondayCapacity;
}

public void setMondayCapacity(BigDecimal  mondayCapacity) {
this.mondayCapacity = mondayCapacity;
}

public Timestamp getTuesdayStartTime() {
return tuesdayStartTime;
}

public void setTuesdayStartTime(Timestamp  tuesdayStartTime) {
this.tuesdayStartTime = tuesdayStartTime;
}

public BigDecimal getTuesdayCapacity() {
return tuesdayCapacity;
}

public void setTuesdayCapacity(BigDecimal  tuesdayCapacity) {
this.tuesdayCapacity = tuesdayCapacity;
}

public Timestamp getWednesdayStartTime() {
return wednesdayStartTime;
}

public void setWednesdayStartTime(Timestamp  wednesdayStartTime) {
this.wednesdayStartTime = wednesdayStartTime;
}

public BigDecimal getWednesdayCapacity() {
return wednesdayCapacity;
}

public void setWednesdayCapacity(BigDecimal  wednesdayCapacity) {
this.wednesdayCapacity = wednesdayCapacity;
}

public Timestamp getThursdayStartTime() {
return thursdayStartTime;
}

public void setThursdayStartTime(Timestamp  thursdayStartTime) {
this.thursdayStartTime = thursdayStartTime;
}

public BigDecimal getThursdayCapacity() {
return thursdayCapacity;
}

public void setThursdayCapacity(BigDecimal  thursdayCapacity) {
this.thursdayCapacity = thursdayCapacity;
}

public Timestamp getFridayStartTime() {
return fridayStartTime;
}

public void setFridayStartTime(Timestamp  fridayStartTime) {
this.fridayStartTime = fridayStartTime;
}

public BigDecimal getFridayCapacity() {
return fridayCapacity;
}

public void setFridayCapacity(BigDecimal  fridayCapacity) {
this.fridayCapacity = fridayCapacity;
}

public Timestamp getSaturdayStartTime() {
return saturdayStartTime;
}

public void setSaturdayStartTime(Timestamp  saturdayStartTime) {
this.saturdayStartTime = saturdayStartTime;
}

public BigDecimal getSaturdayCapacity() {
return saturdayCapacity;
}

public void setSaturdayCapacity(BigDecimal  saturdayCapacity) {
this.saturdayCapacity = saturdayCapacity;
}

public Timestamp getSundayStartTime() {
return sundayStartTime;
}

public void setSundayStartTime(Timestamp  sundayStartTime) {
this.sundayStartTime = sundayStartTime;
}

public BigDecimal getSundayCapacity() {
return sundayCapacity;
}

public void setSundayCapacity(BigDecimal  sundayCapacity) {
this.sundayCapacity = sundayCapacity;
}


public Map<String, Object> mapAttributeField() {
return TechDataCalendarWeekMapper.map(this);
}
}
