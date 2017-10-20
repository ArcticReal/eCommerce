package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.assocTypeAttr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocTypeAttr.WorkEffortAssocTypeAttr;

public class WorkEffortAssocTypeAttrMapper  {


	public static Map<String, Object> map(WorkEffortAssocTypeAttr workeffortassoctypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortassoctypeattr.getWorkEffortAssocTypeId() != null ){
			returnVal.put("workEffortAssocTypeId",workeffortassoctypeattr.getWorkEffortAssocTypeId());
}

		if(workeffortassoctypeattr.getAttrName() != null ){
			returnVal.put("attrName",workeffortassoctypeattr.getAttrName());
}

		if(workeffortassoctypeattr.getDescription() != null ){
			returnVal.put("description",workeffortassoctypeattr.getDescription());
}

		return returnVal;
}


	public static WorkEffortAssocTypeAttr map(Map<String, Object> fields) {

		WorkEffortAssocTypeAttr returnVal = new WorkEffortAssocTypeAttr();

		if(fields.get("workEffortAssocTypeId") != null) {
			returnVal.setWorkEffortAssocTypeId((String) fields.get("workEffortAssocTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static WorkEffortAssocTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortAssocTypeAttr returnVal = new WorkEffortAssocTypeAttr();

		if(fields.get("workEffortAssocTypeId") != null) {
			returnVal.setWorkEffortAssocTypeId((String) fields.get("workEffortAssocTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static WorkEffortAssocTypeAttr map(GenericValue val) {

WorkEffortAssocTypeAttr returnVal = new WorkEffortAssocTypeAttr();
		returnVal.setWorkEffortAssocTypeId(val.getString("workEffortAssocTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static WorkEffortAssocTypeAttr map(HttpServletRequest request) throws Exception {

		WorkEffortAssocTypeAttr returnVal = new WorkEffortAssocTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortAssocTypeId")) {
returnVal.setWorkEffortAssocTypeId(request.getParameter("workEffortAssocTypeId"));
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
