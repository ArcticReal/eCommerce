package com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.EmplPosition;

public class EmplPositionMapper  {


	public static Map<String, Object> map(EmplPosition emplposition) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(emplposition.getEmplPositionId() != null ){
			returnVal.put("emplPositionId",emplposition.getEmplPositionId());
}

		if(emplposition.getStatusId() != null ){
			returnVal.put("statusId",emplposition.getStatusId());
}

		if(emplposition.getPartyId() != null ){
			returnVal.put("partyId",emplposition.getPartyId());
}

		if(emplposition.getBudgetId() != null ){
			returnVal.put("budgetId",emplposition.getBudgetId());
}

		if(emplposition.getBudgetItemSeqId() != null ){
			returnVal.put("budgetItemSeqId",emplposition.getBudgetItemSeqId());
}

		if(emplposition.getEmplPositionTypeId() != null ){
			returnVal.put("emplPositionTypeId",emplposition.getEmplPositionTypeId());
}

		if(emplposition.getEstimatedFromDate() != null ){
			returnVal.put("estimatedFromDate",emplposition.getEstimatedFromDate());
}

		if(emplposition.getEstimatedThruDate() != null ){
			returnVal.put("estimatedThruDate",emplposition.getEstimatedThruDate());
}

		if(emplposition.getSalaryFlag() != null ){
			returnVal.put("salaryFlag",emplposition.getSalaryFlag());
}

		if(emplposition.getExemptFlag() != null ){
			returnVal.put("exemptFlag",emplposition.getExemptFlag());
}

		if(emplposition.getFulltimeFlag() != null ){
			returnVal.put("fulltimeFlag",emplposition.getFulltimeFlag());
}

		if(emplposition.getTemporaryFlag() != null ){
			returnVal.put("temporaryFlag",emplposition.getTemporaryFlag());
}

		if(emplposition.getActualFromDate() != null ){
			returnVal.put("actualFromDate",emplposition.getActualFromDate());
}

		if(emplposition.getActualThruDate() != null ){
			returnVal.put("actualThruDate",emplposition.getActualThruDate());
}

		return returnVal;
}


	public static EmplPosition map(Map<String, Object> fields) {

		EmplPosition returnVal = new EmplPosition();

		if(fields.get("emplPositionId") != null) {
			returnVal.setEmplPositionId((String) fields.get("emplPositionId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
}

		if(fields.get("emplPositionTypeId") != null) {
			returnVal.setEmplPositionTypeId((String) fields.get("emplPositionTypeId"));
}

		if(fields.get("estimatedFromDate") != null) {
			returnVal.setEstimatedFromDate((Timestamp) fields.get("estimatedFromDate"));
}

		if(fields.get("estimatedThruDate") != null) {
			returnVal.setEstimatedThruDate((Timestamp) fields.get("estimatedThruDate"));
}

		if(fields.get("salaryFlag") != null) {
			returnVal.setSalaryFlag((boolean) fields.get("salaryFlag"));
}

		if(fields.get("exemptFlag") != null) {
			returnVal.setExemptFlag((boolean) fields.get("exemptFlag"));
}

		if(fields.get("fulltimeFlag") != null) {
			returnVal.setFulltimeFlag((boolean) fields.get("fulltimeFlag"));
}

		if(fields.get("temporaryFlag") != null) {
			returnVal.setTemporaryFlag((boolean) fields.get("temporaryFlag"));
}

		if(fields.get("actualFromDate") != null) {
			returnVal.setActualFromDate((Timestamp) fields.get("actualFromDate"));
}

		if(fields.get("actualThruDate") != null) {
			returnVal.setActualThruDate((Timestamp) fields.get("actualThruDate"));
}


		return returnVal;
 } 
	public static EmplPosition mapstrstr(Map<String, String> fields) throws Exception {

		EmplPosition returnVal = new EmplPosition();

		if(fields.get("emplPositionId") != null) {
			returnVal.setEmplPositionId((String) fields.get("emplPositionId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
}

		if(fields.get("emplPositionTypeId") != null) {
			returnVal.setEmplPositionTypeId((String) fields.get("emplPositionTypeId"));
}

		if(fields.get("estimatedFromDate") != null) {
String buf = fields.get("estimatedFromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedFromDate(ibuf);
}

		if(fields.get("estimatedThruDate") != null) {
String buf = fields.get("estimatedThruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedThruDate(ibuf);
}

		if(fields.get("salaryFlag") != null) {
String buf;
buf = fields.get("salaryFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setSalaryFlag(ibuf);
}

		if(fields.get("exemptFlag") != null) {
String buf;
buf = fields.get("exemptFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setExemptFlag(ibuf);
}

		if(fields.get("fulltimeFlag") != null) {
String buf;
buf = fields.get("fulltimeFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setFulltimeFlag(ibuf);
}

		if(fields.get("temporaryFlag") != null) {
String buf;
buf = fields.get("temporaryFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setTemporaryFlag(ibuf);
}

		if(fields.get("actualFromDate") != null) {
String buf = fields.get("actualFromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setActualFromDate(ibuf);
}

		if(fields.get("actualThruDate") != null) {
String buf = fields.get("actualThruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setActualThruDate(ibuf);
}


		return returnVal;
 } 
	public static EmplPosition map(GenericValue val) {

EmplPosition returnVal = new EmplPosition();
		returnVal.setEmplPositionId(val.getString("emplPositionId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setBudgetId(val.getString("budgetId"));
		returnVal.setBudgetItemSeqId(val.getString("budgetItemSeqId"));
		returnVal.setEmplPositionTypeId(val.getString("emplPositionTypeId"));
		returnVal.setEstimatedFromDate(val.getTimestamp("estimatedFromDate"));
		returnVal.setEstimatedThruDate(val.getTimestamp("estimatedThruDate"));
		returnVal.setSalaryFlag(val.getBoolean("salaryFlag"));
		returnVal.setExemptFlag(val.getBoolean("exemptFlag"));
		returnVal.setFulltimeFlag(val.getBoolean("fulltimeFlag"));
		returnVal.setTemporaryFlag(val.getBoolean("temporaryFlag"));
		returnVal.setActualFromDate(val.getTimestamp("actualFromDate"));
		returnVal.setActualThruDate(val.getTimestamp("actualThruDate"));


return returnVal;

}

public static EmplPosition map(HttpServletRequest request) throws Exception {

		EmplPosition returnVal = new EmplPosition();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("emplPositionId")) {
returnVal.setEmplPositionId(request.getParameter("emplPositionId"));
}

		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("budgetId"))  {
returnVal.setBudgetId(request.getParameter("budgetId"));
}
		if(paramMap.containsKey("budgetItemSeqId"))  {
returnVal.setBudgetItemSeqId(request.getParameter("budgetItemSeqId"));
}
		if(paramMap.containsKey("emplPositionTypeId"))  {
returnVal.setEmplPositionTypeId(request.getParameter("emplPositionTypeId"));
}
		if(paramMap.containsKey("estimatedFromDate"))  {
String buf = request.getParameter("estimatedFromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEstimatedFromDate(ibuf);
}
		if(paramMap.containsKey("estimatedThruDate"))  {
String buf = request.getParameter("estimatedThruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEstimatedThruDate(ibuf);
}
		if(paramMap.containsKey("salaryFlag"))  {
String buf = request.getParameter("salaryFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setSalaryFlag(ibuf);
}
		if(paramMap.containsKey("exemptFlag"))  {
String buf = request.getParameter("exemptFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setExemptFlag(ibuf);
}
		if(paramMap.containsKey("fulltimeFlag"))  {
String buf = request.getParameter("fulltimeFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setFulltimeFlag(ibuf);
}
		if(paramMap.containsKey("temporaryFlag"))  {
String buf = request.getParameter("temporaryFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setTemporaryFlag(ibuf);
}
		if(paramMap.containsKey("actualFromDate"))  {
String buf = request.getParameter("actualFromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setActualFromDate(ibuf);
}
		if(paramMap.containsKey("actualThruDate"))  {
String buf = request.getParameter("actualThruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setActualThruDate(ibuf);
}
return returnVal;

}
}
