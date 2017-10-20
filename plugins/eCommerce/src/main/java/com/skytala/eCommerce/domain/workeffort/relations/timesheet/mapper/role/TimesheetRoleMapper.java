package com.skytala.eCommerce.domain.workeffort.relations.timesheet.mapper.role;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.model.role.TimesheetRole;

public class TimesheetRoleMapper  {


	public static Map<String, Object> map(TimesheetRole timesheetrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(timesheetrole.getTimesheetId() != null ){
			returnVal.put("timesheetId",timesheetrole.getTimesheetId());
}

		if(timesheetrole.getPartyId() != null ){
			returnVal.put("partyId",timesheetrole.getPartyId());
}

		if(timesheetrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",timesheetrole.getRoleTypeId());
}

		return returnVal;
}


	public static TimesheetRole map(Map<String, Object> fields) {

		TimesheetRole returnVal = new TimesheetRole();

		if(fields.get("timesheetId") != null) {
			returnVal.setTimesheetId((String) fields.get("timesheetId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static TimesheetRole mapstrstr(Map<String, String> fields) throws Exception {

		TimesheetRole returnVal = new TimesheetRole();

		if(fields.get("timesheetId") != null) {
			returnVal.setTimesheetId((String) fields.get("timesheetId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static TimesheetRole map(GenericValue val) {

TimesheetRole returnVal = new TimesheetRole();
		returnVal.setTimesheetId(val.getString("timesheetId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));


return returnVal;

}

public static TimesheetRole map(HttpServletRequest request) throws Exception {

		TimesheetRole returnVal = new TimesheetRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("timesheetId")) {
returnVal.setTimesheetId(request.getParameter("timesheetId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
return returnVal;

}
}
