package com.skytala.eCommerce.domain.workeffort.relations.workEffortAttribute.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAttribute.model.WorkEffortAttribute;

public class WorkEffortAttributeMapper  {


	public static Map<String, Object> map(WorkEffortAttribute workeffortattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortattribute.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortattribute.getWorkEffortId());
}

		if(workeffortattribute.getAttrName() != null ){
			returnVal.put("attrName",workeffortattribute.getAttrName());
}

		if(workeffortattribute.getAttrValue() != null ){
			returnVal.put("attrValue",workeffortattribute.getAttrValue());
}

		if(workeffortattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",workeffortattribute.getAttrDescription());
}

		return returnVal;
}


	public static WorkEffortAttribute map(Map<String, Object> fields) {

		WorkEffortAttribute returnVal = new WorkEffortAttribute();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static WorkEffortAttribute mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortAttribute returnVal = new WorkEffortAttribute();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
String buf;
buf = fields.get("attrValue");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrValue(ibuf);
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static WorkEffortAttribute map(GenericValue val) {

WorkEffortAttribute returnVal = new WorkEffortAttribute();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static WorkEffortAttribute map(HttpServletRequest request) throws Exception {

		WorkEffortAttribute returnVal = new WorkEffortAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
