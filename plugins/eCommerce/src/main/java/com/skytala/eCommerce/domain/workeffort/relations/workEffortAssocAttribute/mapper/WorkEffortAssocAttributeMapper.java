package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocAttribute.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocAttribute.model.WorkEffortAssocAttribute;

public class WorkEffortAssocAttributeMapper  {


	public static Map<String, Object> map(WorkEffortAssocAttribute workeffortassocattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortassocattribute.getWorkEffortIdFrom() != null ){
			returnVal.put("workEffortIdFrom",workeffortassocattribute.getWorkEffortIdFrom());
}

		if(workeffortassocattribute.getWorkEffortIdTo() != null ){
			returnVal.put("workEffortIdTo",workeffortassocattribute.getWorkEffortIdTo());
}

		if(workeffortassocattribute.getWorkEffortAssocTypeId() != null ){
			returnVal.put("workEffortAssocTypeId",workeffortassocattribute.getWorkEffortAssocTypeId());
}

		if(workeffortassocattribute.getFromDate() != null ){
			returnVal.put("fromDate",workeffortassocattribute.getFromDate());
}

		if(workeffortassocattribute.getAttrName() != null ){
			returnVal.put("attrName",workeffortassocattribute.getAttrName());
}

		if(workeffortassocattribute.getAttrValue() != null ){
			returnVal.put("attrValue",workeffortassocattribute.getAttrValue());
}

		if(workeffortassocattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",workeffortassocattribute.getAttrDescription());
}

		return returnVal;
}


	public static WorkEffortAssocAttribute map(Map<String, Object> fields) {

		WorkEffortAssocAttribute returnVal = new WorkEffortAssocAttribute();

		if(fields.get("workEffortIdFrom") != null) {
			returnVal.setWorkEffortIdFrom((String) fields.get("workEffortIdFrom"));
}

		if(fields.get("workEffortIdTo") != null) {
			returnVal.setWorkEffortIdTo((String) fields.get("workEffortIdTo"));
}

		if(fields.get("workEffortAssocTypeId") != null) {
			returnVal.setWorkEffortAssocTypeId((String) fields.get("workEffortAssocTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
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
	public static WorkEffortAssocAttribute mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortAssocAttribute returnVal = new WorkEffortAssocAttribute();

		if(fields.get("workEffortIdFrom") != null) {
			returnVal.setWorkEffortIdFrom((String) fields.get("workEffortIdFrom"));
}

		if(fields.get("workEffortIdTo") != null) {
			returnVal.setWorkEffortIdTo((String) fields.get("workEffortIdTo"));
}

		if(fields.get("workEffortAssocTypeId") != null) {
			returnVal.setWorkEffortAssocTypeId((String) fields.get("workEffortAssocTypeId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
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
	public static WorkEffortAssocAttribute map(GenericValue val) {

WorkEffortAssocAttribute returnVal = new WorkEffortAssocAttribute();
		returnVal.setWorkEffortIdFrom(val.getString("workEffortIdFrom"));
		returnVal.setWorkEffortIdTo(val.getString("workEffortIdTo"));
		returnVal.setWorkEffortAssocTypeId(val.getString("workEffortAssocTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static WorkEffortAssocAttribute map(HttpServletRequest request) throws Exception {

		WorkEffortAssocAttribute returnVal = new WorkEffortAssocAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortIdFrom")) {
returnVal.setWorkEffortIdFrom(request.getParameter("workEffortIdFrom"));
}

		if(paramMap.containsKey("workEffortIdTo"))  {
returnVal.setWorkEffortIdTo(request.getParameter("workEffortIdTo"));
}
		if(paramMap.containsKey("workEffortAssocTypeId"))  {
returnVal.setWorkEffortAssocTypeId(request.getParameter("workEffortAssocTypeId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
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
