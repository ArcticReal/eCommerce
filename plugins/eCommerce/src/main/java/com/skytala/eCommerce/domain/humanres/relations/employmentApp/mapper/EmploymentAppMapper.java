package com.skytala.eCommerce.domain.humanres.relations.employmentApp.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.employmentApp.model.EmploymentApp;

public class EmploymentAppMapper  {


	public static Map<String, Object> map(EmploymentApp employmentapp) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(employmentapp.getApplicationId() != null ){
			returnVal.put("applicationId",employmentapp.getApplicationId());
}

		if(employmentapp.getEmplPositionId() != null ){
			returnVal.put("emplPositionId",employmentapp.getEmplPositionId());
}

		if(employmentapp.getStatusId() != null ){
			returnVal.put("statusId",employmentapp.getStatusId());
}

		if(employmentapp.getEmploymentAppSourceTypeId() != null ){
			returnVal.put("employmentAppSourceTypeId",employmentapp.getEmploymentAppSourceTypeId());
}

		if(employmentapp.getApplyingPartyId() != null ){
			returnVal.put("applyingPartyId",employmentapp.getApplyingPartyId());
}

		if(employmentapp.getReferredByPartyId() != null ){
			returnVal.put("referredByPartyId",employmentapp.getReferredByPartyId());
}

		if(employmentapp.getApplicationDate() != null ){
			returnVal.put("applicationDate",employmentapp.getApplicationDate());
}

		if(employmentapp.getApproverPartyId() != null ){
			returnVal.put("approverPartyId",employmentapp.getApproverPartyId());
}

		if(employmentapp.getJobRequisitionId() != null ){
			returnVal.put("jobRequisitionId",employmentapp.getJobRequisitionId());
}

		return returnVal;
}


	public static EmploymentApp map(Map<String, Object> fields) {

		EmploymentApp returnVal = new EmploymentApp();

		if(fields.get("applicationId") != null) {
			returnVal.setApplicationId((String) fields.get("applicationId"));
}

		if(fields.get("emplPositionId") != null) {
			returnVal.setEmplPositionId((String) fields.get("emplPositionId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("employmentAppSourceTypeId") != null) {
			returnVal.setEmploymentAppSourceTypeId((String) fields.get("employmentAppSourceTypeId"));
}

		if(fields.get("applyingPartyId") != null) {
			returnVal.setApplyingPartyId((String) fields.get("applyingPartyId"));
}

		if(fields.get("referredByPartyId") != null) {
			returnVal.setReferredByPartyId((String) fields.get("referredByPartyId"));
}

		if(fields.get("applicationDate") != null) {
			returnVal.setApplicationDate((Timestamp) fields.get("applicationDate"));
}

		if(fields.get("approverPartyId") != null) {
			returnVal.setApproverPartyId((String) fields.get("approverPartyId"));
}

		if(fields.get("jobRequisitionId") != null) {
			returnVal.setJobRequisitionId((String) fields.get("jobRequisitionId"));
}


		return returnVal;
 } 
	public static EmploymentApp mapstrstr(Map<String, String> fields) throws Exception {

		EmploymentApp returnVal = new EmploymentApp();

		if(fields.get("applicationId") != null) {
			returnVal.setApplicationId((String) fields.get("applicationId"));
}

		if(fields.get("emplPositionId") != null) {
			returnVal.setEmplPositionId((String) fields.get("emplPositionId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("employmentAppSourceTypeId") != null) {
			returnVal.setEmploymentAppSourceTypeId((String) fields.get("employmentAppSourceTypeId"));
}

		if(fields.get("applyingPartyId") != null) {
			returnVal.setApplyingPartyId((String) fields.get("applyingPartyId"));
}

		if(fields.get("referredByPartyId") != null) {
			returnVal.setReferredByPartyId((String) fields.get("referredByPartyId"));
}

		if(fields.get("applicationDate") != null) {
String buf = fields.get("applicationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setApplicationDate(ibuf);
}

		if(fields.get("approverPartyId") != null) {
			returnVal.setApproverPartyId((String) fields.get("approverPartyId"));
}

		if(fields.get("jobRequisitionId") != null) {
			returnVal.setJobRequisitionId((String) fields.get("jobRequisitionId"));
}


		return returnVal;
 } 
	public static EmploymentApp map(GenericValue val) {

EmploymentApp returnVal = new EmploymentApp();
		returnVal.setApplicationId(val.getString("applicationId"));
		returnVal.setEmplPositionId(val.getString("emplPositionId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setEmploymentAppSourceTypeId(val.getString("employmentAppSourceTypeId"));
		returnVal.setApplyingPartyId(val.getString("applyingPartyId"));
		returnVal.setReferredByPartyId(val.getString("referredByPartyId"));
		returnVal.setApplicationDate(val.getTimestamp("applicationDate"));
		returnVal.setApproverPartyId(val.getString("approverPartyId"));
		returnVal.setJobRequisitionId(val.getString("jobRequisitionId"));


return returnVal;

}

public static EmploymentApp map(HttpServletRequest request) throws Exception {

		EmploymentApp returnVal = new EmploymentApp();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("applicationId")) {
returnVal.setApplicationId(request.getParameter("applicationId"));
}

		if(paramMap.containsKey("emplPositionId"))  {
returnVal.setEmplPositionId(request.getParameter("emplPositionId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("employmentAppSourceTypeId"))  {
returnVal.setEmploymentAppSourceTypeId(request.getParameter("employmentAppSourceTypeId"));
}
		if(paramMap.containsKey("applyingPartyId"))  {
returnVal.setApplyingPartyId(request.getParameter("applyingPartyId"));
}
		if(paramMap.containsKey("referredByPartyId"))  {
returnVal.setReferredByPartyId(request.getParameter("referredByPartyId"));
}
		if(paramMap.containsKey("applicationDate"))  {
String buf = request.getParameter("applicationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setApplicationDate(ibuf);
}
		if(paramMap.containsKey("approverPartyId"))  {
returnVal.setApproverPartyId(request.getParameter("approverPartyId"));
}
		if(paramMap.containsKey("jobRequisitionId"))  {
returnVal.setJobRequisitionId(request.getParameter("jobRequisitionId"));
}
return returnVal;

}
}
