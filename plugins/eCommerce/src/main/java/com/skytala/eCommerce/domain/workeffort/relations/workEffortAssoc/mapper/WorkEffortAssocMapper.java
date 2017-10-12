package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.model.WorkEffortAssoc;

public class WorkEffortAssocMapper  {


	public static Map<String, Object> map(WorkEffortAssoc workeffortassoc) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortassoc.getWorkEffortIdFrom() != null ){
			returnVal.put("workEffortIdFrom",workeffortassoc.getWorkEffortIdFrom());
}

		if(workeffortassoc.getWorkEffortIdTo() != null ){
			returnVal.put("workEffortIdTo",workeffortassoc.getWorkEffortIdTo());
}

		if(workeffortassoc.getWorkEffortAssocTypeId() != null ){
			returnVal.put("workEffortAssocTypeId",workeffortassoc.getWorkEffortAssocTypeId());
}

		if(workeffortassoc.getSequenceNum() != null ){
			returnVal.put("sequenceNum",workeffortassoc.getSequenceNum());
}

		if(workeffortassoc.getFromDate() != null ){
			returnVal.put("fromDate",workeffortassoc.getFromDate());
}

		if(workeffortassoc.getThruDate() != null ){
			returnVal.put("thruDate",workeffortassoc.getThruDate());
}

		return returnVal;
}


	public static WorkEffortAssoc map(Map<String, Object> fields) {

		WorkEffortAssoc returnVal = new WorkEffortAssoc();

		if(fields.get("workEffortIdFrom") != null) {
			returnVal.setWorkEffortIdFrom((String) fields.get("workEffortIdFrom"));
}

		if(fields.get("workEffortIdTo") != null) {
			returnVal.setWorkEffortIdTo((String) fields.get("workEffortIdTo"));
}

		if(fields.get("workEffortAssocTypeId") != null) {
			returnVal.setWorkEffortAssocTypeId((String) fields.get("workEffortAssocTypeId"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static WorkEffortAssoc mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortAssoc returnVal = new WorkEffortAssoc();

		if(fields.get("workEffortIdFrom") != null) {
			returnVal.setWorkEffortIdFrom((String) fields.get("workEffortIdFrom"));
}

		if(fields.get("workEffortIdTo") != null) {
			returnVal.setWorkEffortIdTo((String) fields.get("workEffortIdTo"));
}

		if(fields.get("workEffortAssocTypeId") != null) {
			returnVal.setWorkEffortAssocTypeId((String) fields.get("workEffortAssocTypeId"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}


		return returnVal;
 } 
	public static WorkEffortAssoc map(GenericValue val) {

WorkEffortAssoc returnVal = new WorkEffortAssoc();
		returnVal.setWorkEffortIdFrom(val.getString("workEffortIdFrom"));
		returnVal.setWorkEffortIdTo(val.getString("workEffortIdTo"));
		returnVal.setWorkEffortAssocTypeId(val.getString("workEffortAssocTypeId"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static WorkEffortAssoc map(HttpServletRequest request) throws Exception {

		WorkEffortAssoc returnVal = new WorkEffortAssoc();

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
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
return returnVal;

}
}
