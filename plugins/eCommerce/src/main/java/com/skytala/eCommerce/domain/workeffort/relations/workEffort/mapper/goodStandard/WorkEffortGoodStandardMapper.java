package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.goodStandard;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.goodStandard.WorkEffortGoodStandard;

public class WorkEffortGoodStandardMapper  {


	public static Map<String, Object> map(WorkEffortGoodStandard workeffortgoodstandard) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortgoodstandard.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortgoodstandard.getWorkEffortId());
}

		if(workeffortgoodstandard.getProductId() != null ){
			returnVal.put("productId",workeffortgoodstandard.getProductId());
}

		if(workeffortgoodstandard.getWorkEffortGoodStdTypeId() != null ){
			returnVal.put("workEffortGoodStdTypeId",workeffortgoodstandard.getWorkEffortGoodStdTypeId());
}

		if(workeffortgoodstandard.getFromDate() != null ){
			returnVal.put("fromDate",workeffortgoodstandard.getFromDate());
}

		if(workeffortgoodstandard.getThruDate() != null ){
			returnVal.put("thruDate",workeffortgoodstandard.getThruDate());
}

		if(workeffortgoodstandard.getStatusId() != null ){
			returnVal.put("statusId",workeffortgoodstandard.getStatusId());
}

		if(workeffortgoodstandard.getEstimatedQuantity() != null ){
			returnVal.put("estimatedQuantity",workeffortgoodstandard.getEstimatedQuantity());
}

		if(workeffortgoodstandard.getEstimatedCost() != null ){
			returnVal.put("estimatedCost",workeffortgoodstandard.getEstimatedCost());
}

		return returnVal;
}


	public static WorkEffortGoodStandard map(Map<String, Object> fields) {

		WorkEffortGoodStandard returnVal = new WorkEffortGoodStandard();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("workEffortGoodStdTypeId") != null) {
			returnVal.setWorkEffortGoodStdTypeId((String) fields.get("workEffortGoodStdTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("estimatedQuantity") != null) {
			returnVal.setEstimatedQuantity((BigDecimal) fields.get("estimatedQuantity"));
}

		if(fields.get("estimatedCost") != null) {
			returnVal.setEstimatedCost((BigDecimal) fields.get("estimatedCost"));
}


		return returnVal;
 } 
	public static WorkEffortGoodStandard mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortGoodStandard returnVal = new WorkEffortGoodStandard();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("workEffortGoodStdTypeId") != null) {
			returnVal.setWorkEffortGoodStdTypeId((String) fields.get("workEffortGoodStdTypeId"));
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

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("estimatedQuantity") != null) {
String buf;
buf = fields.get("estimatedQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedQuantity(bd);
}

		if(fields.get("estimatedCost") != null) {
String buf;
buf = fields.get("estimatedCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedCost(bd);
}


		return returnVal;
 } 
	public static WorkEffortGoodStandard map(GenericValue val) {

WorkEffortGoodStandard returnVal = new WorkEffortGoodStandard();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setWorkEffortGoodStdTypeId(val.getString("workEffortGoodStdTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setEstimatedQuantity(val.getBigDecimal("estimatedQuantity"));
		returnVal.setEstimatedCost(val.getBigDecimal("estimatedCost"));


return returnVal;

}

public static WorkEffortGoodStandard map(HttpServletRequest request) throws Exception {

		WorkEffortGoodStandard returnVal = new WorkEffortGoodStandard();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("workEffortGoodStdTypeId"))  {
returnVal.setWorkEffortGoodStdTypeId(request.getParameter("workEffortGoodStdTypeId"));
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
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("estimatedQuantity"))  {
String buf = request.getParameter("estimatedQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedQuantity(bd);
}
		if(paramMap.containsKey("estimatedCost"))  {
String buf = request.getParameter("estimatedCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedCost(bd);
}
return returnVal;

}
}
