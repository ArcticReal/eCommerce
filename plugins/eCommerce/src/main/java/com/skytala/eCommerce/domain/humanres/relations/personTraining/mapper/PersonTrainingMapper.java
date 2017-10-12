package com.skytala.eCommerce.domain.humanres.relations.personTraining.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.model.PersonTraining;

public class PersonTrainingMapper  {


	public static Map<String, Object> map(PersonTraining persontraining) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(persontraining.getPartyId() != null ){
			returnVal.put("partyId",persontraining.getPartyId());
}

		if(persontraining.getTrainingRequestId() != null ){
			returnVal.put("trainingRequestId",persontraining.getTrainingRequestId());
}

		if(persontraining.getTrainingClassTypeId() != null ){
			returnVal.put("trainingClassTypeId",persontraining.getTrainingClassTypeId());
}

		if(persontraining.getWorkEffortId() != null ){
			returnVal.put("workEffortId",persontraining.getWorkEffortId());
}

		if(persontraining.getFromDate() != null ){
			returnVal.put("fromDate",persontraining.getFromDate());
}

		if(persontraining.getThruDate() != null ){
			returnVal.put("thruDate",persontraining.getThruDate());
}

		if(persontraining.getApproverId() != null ){
			returnVal.put("approverId",persontraining.getApproverId());
}

		if(persontraining.getApprovalStatus() != null ){
			returnVal.put("approvalStatus",persontraining.getApprovalStatus());
}

		if(persontraining.getReason() != null ){
			returnVal.put("reason",persontraining.getReason());
}

		return returnVal;
}


	public static PersonTraining map(Map<String, Object> fields) {

		PersonTraining returnVal = new PersonTraining();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("trainingRequestId") != null) {
			returnVal.setTrainingRequestId((String) fields.get("trainingRequestId"));
}

		if(fields.get("trainingClassTypeId") != null) {
			returnVal.setTrainingClassTypeId((String) fields.get("trainingClassTypeId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("approverId") != null) {
			returnVal.setApproverId((String) fields.get("approverId"));
}

		if(fields.get("approvalStatus") != null) {
			returnVal.setApprovalStatus((String) fields.get("approvalStatus"));
}

		if(fields.get("reason") != null) {
			returnVal.setReason((String) fields.get("reason"));
}


		return returnVal;
 } 
	public static PersonTraining mapstrstr(Map<String, String> fields) throws Exception {

		PersonTraining returnVal = new PersonTraining();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("trainingRequestId") != null) {
			returnVal.setTrainingRequestId((String) fields.get("trainingRequestId"));
}

		if(fields.get("trainingClassTypeId") != null) {
			returnVal.setTrainingClassTypeId((String) fields.get("trainingClassTypeId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
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

		if(fields.get("approverId") != null) {
			returnVal.setApproverId((String) fields.get("approverId"));
}

		if(fields.get("approvalStatus") != null) {
			returnVal.setApprovalStatus((String) fields.get("approvalStatus"));
}

		if(fields.get("reason") != null) {
			returnVal.setReason((String) fields.get("reason"));
}


		return returnVal;
 } 
	public static PersonTraining map(GenericValue val) {

PersonTraining returnVal = new PersonTraining();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setTrainingRequestId(val.getString("trainingRequestId"));
		returnVal.setTrainingClassTypeId(val.getString("trainingClassTypeId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setApproverId(val.getString("approverId"));
		returnVal.setApprovalStatus(val.getString("approvalStatus"));
		returnVal.setReason(val.getString("reason"));


return returnVal;

}

public static PersonTraining map(HttpServletRequest request) throws Exception {

		PersonTraining returnVal = new PersonTraining();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("trainingRequestId"))  {
returnVal.setTrainingRequestId(request.getParameter("trainingRequestId"));
}
		if(paramMap.containsKey("trainingClassTypeId"))  {
returnVal.setTrainingClassTypeId(request.getParameter("trainingClassTypeId"));
}
		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
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
		if(paramMap.containsKey("approverId"))  {
returnVal.setApproverId(request.getParameter("approverId"));
}
		if(paramMap.containsKey("approvalStatus"))  {
returnVal.setApprovalStatus(request.getParameter("approvalStatus"));
}
		if(paramMap.containsKey("reason"))  {
returnVal.setReason(request.getParameter("reason"));
}
return returnVal;

}
}
