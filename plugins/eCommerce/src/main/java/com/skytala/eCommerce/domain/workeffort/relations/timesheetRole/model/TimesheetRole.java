package com.skytala.eCommerce.domain.workeffort.relations.timesheetRole.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.timesheetRole.mapper.TimesheetRoleMapper;

public class TimesheetRole implements Serializable{

private static final long serialVersionUID = 1L;
private String timesheetId;
private String partyId;
private String roleTypeId;

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

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}


public Map<String, Object> mapAttributeField() {
return TimesheetRoleMapper.map(this);
}
}
