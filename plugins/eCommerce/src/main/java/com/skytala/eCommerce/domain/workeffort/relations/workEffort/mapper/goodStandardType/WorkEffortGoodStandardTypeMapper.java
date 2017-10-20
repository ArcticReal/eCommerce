package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.goodStandardType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.goodStandardType.WorkEffortGoodStandardType;

public class WorkEffortGoodStandardTypeMapper  {


	public static Map<String, Object> map(WorkEffortGoodStandardType workeffortgoodstandardtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortgoodstandardtype.getWorkEffortGoodStdTypeId() != null ){
			returnVal.put("workEffortGoodStdTypeId",workeffortgoodstandardtype.getWorkEffortGoodStdTypeId());
}

		if(workeffortgoodstandardtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",workeffortgoodstandardtype.getParentTypeId());
}

		if(workeffortgoodstandardtype.getHasTable() != null ){
			returnVal.put("hasTable",workeffortgoodstandardtype.getHasTable());
}

		if(workeffortgoodstandardtype.getDescription() != null ){
			returnVal.put("description",workeffortgoodstandardtype.getDescription());
}

		return returnVal;
}


	public static WorkEffortGoodStandardType map(Map<String, Object> fields) {

		WorkEffortGoodStandardType returnVal = new WorkEffortGoodStandardType();

		if(fields.get("workEffortGoodStdTypeId") != null) {
			returnVal.setWorkEffortGoodStdTypeId((String) fields.get("workEffortGoodStdTypeId"));
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
	public static WorkEffortGoodStandardType mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortGoodStandardType returnVal = new WorkEffortGoodStandardType();

		if(fields.get("workEffortGoodStdTypeId") != null) {
			returnVal.setWorkEffortGoodStdTypeId((String) fields.get("workEffortGoodStdTypeId"));
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
	public static WorkEffortGoodStandardType map(GenericValue val) {

WorkEffortGoodStandardType returnVal = new WorkEffortGoodStandardType();
		returnVal.setWorkEffortGoodStdTypeId(val.getString("workEffortGoodStdTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static WorkEffortGoodStandardType map(HttpServletRequest request) throws Exception {

		WorkEffortGoodStandardType returnVal = new WorkEffortGoodStandardType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortGoodStdTypeId")) {
returnVal.setWorkEffortGoodStdTypeId(request.getParameter("workEffortGoodStdTypeId"));
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
