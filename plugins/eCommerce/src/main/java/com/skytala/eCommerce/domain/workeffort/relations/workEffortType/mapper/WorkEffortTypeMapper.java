package com.skytala.eCommerce.domain.workeffort.relations.workEffortType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.model.WorkEffortType;

public class WorkEffortTypeMapper  {


	public static Map<String, Object> map(WorkEffortType workefforttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workefforttype.getWorkEffortTypeId() != null ){
			returnVal.put("workEffortTypeId",workefforttype.getWorkEffortTypeId());
}

		if(workefforttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",workefforttype.getParentTypeId());
}

		if(workefforttype.getHasTable() != null ){
			returnVal.put("hasTable",workefforttype.getHasTable());
}

		if(workefforttype.getDescription() != null ){
			returnVal.put("description",workefforttype.getDescription());
}

		return returnVal;
}


	public static WorkEffortType map(Map<String, Object> fields) {

		WorkEffortType returnVal = new WorkEffortType();

		if(fields.get("workEffortTypeId") != null) {
			returnVal.setWorkEffortTypeId((String) fields.get("workEffortTypeId"));
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
	public static WorkEffortType mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortType returnVal = new WorkEffortType();

		if(fields.get("workEffortTypeId") != null) {
			returnVal.setWorkEffortTypeId((String) fields.get("workEffortTypeId"));
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
	public static WorkEffortType map(GenericValue val) {

WorkEffortType returnVal = new WorkEffortType();
		returnVal.setWorkEffortTypeId(val.getString("workEffortTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static WorkEffortType map(HttpServletRequest request) throws Exception {

		WorkEffortType returnVal = new WorkEffortType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortTypeId")) {
returnVal.setWorkEffortTypeId(request.getParameter("workEffortTypeId"));
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
