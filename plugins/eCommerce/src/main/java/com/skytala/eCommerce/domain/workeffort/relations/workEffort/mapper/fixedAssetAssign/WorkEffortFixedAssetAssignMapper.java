package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.fixedAssetAssign;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.fixedAssetAssign.WorkEffortFixedAssetAssign;

public class WorkEffortFixedAssetAssignMapper  {


	public static Map<String, Object> map(WorkEffortFixedAssetAssign workeffortfixedassetassign) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortfixedassetassign.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortfixedassetassign.getWorkEffortId());
}

		if(workeffortfixedassetassign.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",workeffortfixedassetassign.getFixedAssetId());
}

		if(workeffortfixedassetassign.getStatusId() != null ){
			returnVal.put("statusId",workeffortfixedassetassign.getStatusId());
}

		if(workeffortfixedassetassign.getFromDate() != null ){
			returnVal.put("fromDate",workeffortfixedassetassign.getFromDate());
}

		if(workeffortfixedassetassign.getThruDate() != null ){
			returnVal.put("thruDate",workeffortfixedassetassign.getThruDate());
}

		if(workeffortfixedassetassign.getAvailabilityStatusId() != null ){
			returnVal.put("availabilityStatusId",workeffortfixedassetassign.getAvailabilityStatusId());
}

		if(workeffortfixedassetassign.getAllocatedCost() != null ){
			returnVal.put("allocatedCost",workeffortfixedassetassign.getAllocatedCost());
}

		if(workeffortfixedassetassign.getComments() != null ){
			returnVal.put("comments",workeffortfixedassetassign.getComments());
}

		return returnVal;
}


	public static WorkEffortFixedAssetAssign map(Map<String, Object> fields) {

		WorkEffortFixedAssetAssign returnVal = new WorkEffortFixedAssetAssign();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("availabilityStatusId") != null) {
			returnVal.setAvailabilityStatusId((String) fields.get("availabilityStatusId"));
}

		if(fields.get("allocatedCost") != null) {
			returnVal.setAllocatedCost((BigDecimal) fields.get("allocatedCost"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static WorkEffortFixedAssetAssign mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortFixedAssetAssign returnVal = new WorkEffortFixedAssetAssign();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
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

		if(fields.get("availabilityStatusId") != null) {
			returnVal.setAvailabilityStatusId((String) fields.get("availabilityStatusId"));
}

		if(fields.get("allocatedCost") != null) {
String buf;
buf = fields.get("allocatedCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAllocatedCost(bd);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static WorkEffortFixedAssetAssign map(GenericValue val) {

WorkEffortFixedAssetAssign returnVal = new WorkEffortFixedAssetAssign();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setAvailabilityStatusId(val.getString("availabilityStatusId"));
		returnVal.setAllocatedCost(val.getBigDecimal("allocatedCost"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static WorkEffortFixedAssetAssign map(HttpServletRequest request) throws Exception {

		WorkEffortFixedAssetAssign returnVal = new WorkEffortFixedAssetAssign();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("fixedAssetId"))  {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
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
		if(paramMap.containsKey("availabilityStatusId"))  {
returnVal.setAvailabilityStatusId(request.getParameter("availabilityStatusId"));
}
		if(paramMap.containsKey("allocatedCost"))  {
String buf = request.getParameter("allocatedCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAllocatedCost(bd);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
