package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.purposeType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.purposeType.WorkEffortPurposeType;

public class WorkEffortPurposeTypeMapper  {


	public static Map<String, Object> map(WorkEffortPurposeType workeffortpurposetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortpurposetype.getWorkEffortPurposeTypeId() != null ){
			returnVal.put("workEffortPurposeTypeId",workeffortpurposetype.getWorkEffortPurposeTypeId());
}

		if(workeffortpurposetype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",workeffortpurposetype.getParentTypeId());
}

		if(workeffortpurposetype.getDescription() != null ){
			returnVal.put("description",workeffortpurposetype.getDescription());
}

		return returnVal;
}


	public static WorkEffortPurposeType map(Map<String, Object> fields) {

		WorkEffortPurposeType returnVal = new WorkEffortPurposeType();

		if(fields.get("workEffortPurposeTypeId") != null) {
			returnVal.setWorkEffortPurposeTypeId((String) fields.get("workEffortPurposeTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static WorkEffortPurposeType mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortPurposeType returnVal = new WorkEffortPurposeType();

		if(fields.get("workEffortPurposeTypeId") != null) {
			returnVal.setWorkEffortPurposeTypeId((String) fields.get("workEffortPurposeTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static WorkEffortPurposeType map(GenericValue val) {

WorkEffortPurposeType returnVal = new WorkEffortPurposeType();
		returnVal.setWorkEffortPurposeTypeId(val.getString("workEffortPurposeTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static WorkEffortPurposeType map(HttpServletRequest request) throws Exception {

		WorkEffortPurposeType returnVal = new WorkEffortPurposeType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortPurposeTypeId")) {
returnVal.setWorkEffortPurposeTypeId(request.getParameter("workEffortPurposeTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
