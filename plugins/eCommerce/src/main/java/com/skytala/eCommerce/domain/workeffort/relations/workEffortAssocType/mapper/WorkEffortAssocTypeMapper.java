package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocType.model.WorkEffortAssocType;

public class WorkEffortAssocTypeMapper  {


	public static Map<String, Object> map(WorkEffortAssocType workeffortassoctype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortassoctype.getWorkEffortAssocTypeId() != null ){
			returnVal.put("workEffortAssocTypeId",workeffortassoctype.getWorkEffortAssocTypeId());
}

		if(workeffortassoctype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",workeffortassoctype.getParentTypeId());
}

		if(workeffortassoctype.getHasTable() != null ){
			returnVal.put("hasTable",workeffortassoctype.getHasTable());
}

		if(workeffortassoctype.getDescription() != null ){
			returnVal.put("description",workeffortassoctype.getDescription());
}

		return returnVal;
}


	public static WorkEffortAssocType map(Map<String, Object> fields) {

		WorkEffortAssocType returnVal = new WorkEffortAssocType();

		if(fields.get("workEffortAssocTypeId") != null) {
			returnVal.setWorkEffortAssocTypeId((String) fields.get("workEffortAssocTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static WorkEffortAssocType mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortAssocType returnVal = new WorkEffortAssocType();

		if(fields.get("workEffortAssocTypeId") != null) {
			returnVal.setWorkEffortAssocTypeId((String) fields.get("workEffortAssocTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static WorkEffortAssocType map(GenericValue val) {

WorkEffortAssocType returnVal = new WorkEffortAssocType();
		returnVal.setWorkEffortAssocTypeId(val.getString("workEffortAssocTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static WorkEffortAssocType map(HttpServletRequest request) throws Exception {

		WorkEffortAssocType returnVal = new WorkEffortAssocType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortAssocTypeId")) {
returnVal.setWorkEffortAssocTypeId(request.getParameter("workEffortAssocTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
