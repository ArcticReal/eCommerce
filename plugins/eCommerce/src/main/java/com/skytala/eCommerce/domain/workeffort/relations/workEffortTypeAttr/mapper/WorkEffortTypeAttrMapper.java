package com.skytala.eCommerce.domain.workeffort.relations.workEffortTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortTypeAttr.model.WorkEffortTypeAttr;

public class WorkEffortTypeAttrMapper  {


	public static Map<String, Object> map(WorkEffortTypeAttr workefforttypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workefforttypeattr.getWorkEffortTypeId() != null ){
			returnVal.put("workEffortTypeId",workefforttypeattr.getWorkEffortTypeId());
}

		if(workefforttypeattr.getAttrName() != null ){
			returnVal.put("attrName",workefforttypeattr.getAttrName());
}

		if(workefforttypeattr.getDescription() != null ){
			returnVal.put("description",workefforttypeattr.getDescription());
}

		return returnVal;
}


	public static WorkEffortTypeAttr map(Map<String, Object> fields) {

		WorkEffortTypeAttr returnVal = new WorkEffortTypeAttr();

		if(fields.get("workEffortTypeId") != null) {
			returnVal.setWorkEffortTypeId((String) fields.get("workEffortTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static WorkEffortTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortTypeAttr returnVal = new WorkEffortTypeAttr();

		if(fields.get("workEffortTypeId") != null) {
			returnVal.setWorkEffortTypeId((String) fields.get("workEffortTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static WorkEffortTypeAttr map(GenericValue val) {

WorkEffortTypeAttr returnVal = new WorkEffortTypeAttr();
		returnVal.setWorkEffortTypeId(val.getString("workEffortTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static WorkEffortTypeAttr map(HttpServletRequest request) throws Exception {

		WorkEffortTypeAttr returnVal = new WorkEffortTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortTypeId")) {
returnVal.setWorkEffortTypeId(request.getParameter("workEffortTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
