package com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.reportingStruct;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.reportingStruct.EmplPositionReportingStruct;

public class EmplPositionReportingStructMapper  {


	public static Map<String, Object> map(EmplPositionReportingStruct emplpositionreportingstruct) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(emplpositionreportingstruct.getEmplPositionIdReportingTo() != null ){
			returnVal.put("emplPositionIdReportingTo",emplpositionreportingstruct.getEmplPositionIdReportingTo());
}

		if(emplpositionreportingstruct.getEmplPositionIdManagedBy() != null ){
			returnVal.put("emplPositionIdManagedBy",emplpositionreportingstruct.getEmplPositionIdManagedBy());
}

		if(emplpositionreportingstruct.getFromDate() != null ){
			returnVal.put("fromDate",emplpositionreportingstruct.getFromDate());
}

		if(emplpositionreportingstruct.getThruDate() != null ){
			returnVal.put("thruDate",emplpositionreportingstruct.getThruDate());
}

		if(emplpositionreportingstruct.getComments() != null ){
			returnVal.put("comments",emplpositionreportingstruct.getComments());
}

		if(emplpositionreportingstruct.getPrimaryFlag() != null ){
			returnVal.put("primaryFlag",emplpositionreportingstruct.getPrimaryFlag());
}

		return returnVal;
}


	public static EmplPositionReportingStruct map(Map<String, Object> fields) {

		EmplPositionReportingStruct returnVal = new EmplPositionReportingStruct();

		if(fields.get("emplPositionIdReportingTo") != null) {
			returnVal.setEmplPositionIdReportingTo((String) fields.get("emplPositionIdReportingTo"));
}

		if(fields.get("emplPositionIdManagedBy") != null) {
			returnVal.setEmplPositionIdManagedBy((String) fields.get("emplPositionIdManagedBy"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("primaryFlag") != null) {
			returnVal.setPrimaryFlag((boolean) fields.get("primaryFlag"));
}


		return returnVal;
 } 
	public static EmplPositionReportingStruct mapstrstr(Map<String, String> fields) throws Exception {

		EmplPositionReportingStruct returnVal = new EmplPositionReportingStruct();

		if(fields.get("emplPositionIdReportingTo") != null) {
			returnVal.setEmplPositionIdReportingTo((String) fields.get("emplPositionIdReportingTo"));
}

		if(fields.get("emplPositionIdManagedBy") != null) {
			returnVal.setEmplPositionIdManagedBy((String) fields.get("emplPositionIdManagedBy"));
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

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("primaryFlag") != null) {
String buf;
buf = fields.get("primaryFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setPrimaryFlag(ibuf);
}


		return returnVal;
 } 
	public static EmplPositionReportingStruct map(GenericValue val) {

EmplPositionReportingStruct returnVal = new EmplPositionReportingStruct();
		returnVal.setEmplPositionIdReportingTo(val.getString("emplPositionIdReportingTo"));
		returnVal.setEmplPositionIdManagedBy(val.getString("emplPositionIdManagedBy"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setPrimaryFlag(val.getBoolean("primaryFlag"));


return returnVal;

}

public static EmplPositionReportingStruct map(HttpServletRequest request) throws Exception {

		EmplPositionReportingStruct returnVal = new EmplPositionReportingStruct();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("emplPositionIdReportingTo")) {
returnVal.setEmplPositionIdReportingTo(request.getParameter("emplPositionIdReportingTo"));
}

		if(paramMap.containsKey("emplPositionIdManagedBy"))  {
returnVal.setEmplPositionIdManagedBy(request.getParameter("emplPositionIdManagedBy"));
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
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("primaryFlag"))  {
String buf = request.getParameter("primaryFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setPrimaryFlag(ibuf);
}
return returnVal;

}
}
