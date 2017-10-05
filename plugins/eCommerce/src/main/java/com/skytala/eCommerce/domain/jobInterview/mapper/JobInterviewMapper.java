package com.skytala.eCommerce.domain.jobInterview.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.jobInterview.model.JobInterview;

public class JobInterviewMapper  {


	public static Map<String, Object> map(JobInterview jobinterview) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(jobinterview.getJobInterviewId() != null ){
			returnVal.put("jobInterviewId",jobinterview.getJobInterviewId());
}

		if(jobinterview.getJobIntervieweePartyId() != null ){
			returnVal.put("jobIntervieweePartyId",jobinterview.getJobIntervieweePartyId());
}

		if(jobinterview.getJobRequisitionId() != null ){
			returnVal.put("jobRequisitionId",jobinterview.getJobRequisitionId());
}

		if(jobinterview.getJobInterviewerPartyId() != null ){
			returnVal.put("jobInterviewerPartyId",jobinterview.getJobInterviewerPartyId());
}

		if(jobinterview.getJobInterviewTypeId() != null ){
			returnVal.put("jobInterviewTypeId",jobinterview.getJobInterviewTypeId());
}

		if(jobinterview.getGradeSecuredEnumId() != null ){
			returnVal.put("gradeSecuredEnumId",jobinterview.getGradeSecuredEnumId());
}

		if(jobinterview.getJobInterviewResult() != null ){
			returnVal.put("jobInterviewResult",jobinterview.getJobInterviewResult());
}

		if(jobinterview.getJobInterviewDate() != null ){
			returnVal.put("jobInterviewDate",jobinterview.getJobInterviewDate());
}

		return returnVal;
}


	public static JobInterview map(Map<String, Object> fields) {

		JobInterview returnVal = new JobInterview();

		if(fields.get("jobInterviewId") != null) {
			returnVal.setJobInterviewId((String) fields.get("jobInterviewId"));
}

		if(fields.get("jobIntervieweePartyId") != null) {
			returnVal.setJobIntervieweePartyId((String) fields.get("jobIntervieweePartyId"));
}

		if(fields.get("jobRequisitionId") != null) {
			returnVal.setJobRequisitionId((String) fields.get("jobRequisitionId"));
}

		if(fields.get("jobInterviewerPartyId") != null) {
			returnVal.setJobInterviewerPartyId((String) fields.get("jobInterviewerPartyId"));
}

		if(fields.get("jobInterviewTypeId") != null) {
			returnVal.setJobInterviewTypeId((String) fields.get("jobInterviewTypeId"));
}

		if(fields.get("gradeSecuredEnumId") != null) {
			returnVal.setGradeSecuredEnumId((String) fields.get("gradeSecuredEnumId"));
}

		if(fields.get("jobInterviewResult") != null) {
			returnVal.setJobInterviewResult((String) fields.get("jobInterviewResult"));
}

		if(fields.get("jobInterviewDate") != null) {
			returnVal.setJobInterviewDate((Timestamp) fields.get("jobInterviewDate"));
}


		return returnVal;
 } 
	public static JobInterview mapstrstr(Map<String, String> fields) throws Exception {

		JobInterview returnVal = new JobInterview();

		if(fields.get("jobInterviewId") != null) {
			returnVal.setJobInterviewId((String) fields.get("jobInterviewId"));
}

		if(fields.get("jobIntervieweePartyId") != null) {
			returnVal.setJobIntervieweePartyId((String) fields.get("jobIntervieweePartyId"));
}

		if(fields.get("jobRequisitionId") != null) {
			returnVal.setJobRequisitionId((String) fields.get("jobRequisitionId"));
}

		if(fields.get("jobInterviewerPartyId") != null) {
			returnVal.setJobInterviewerPartyId((String) fields.get("jobInterviewerPartyId"));
}

		if(fields.get("jobInterviewTypeId") != null) {
			returnVal.setJobInterviewTypeId((String) fields.get("jobInterviewTypeId"));
}

		if(fields.get("gradeSecuredEnumId") != null) {
			returnVal.setGradeSecuredEnumId((String) fields.get("gradeSecuredEnumId"));
}

		if(fields.get("jobInterviewResult") != null) {
			returnVal.setJobInterviewResult((String) fields.get("jobInterviewResult"));
}

		if(fields.get("jobInterviewDate") != null) {
String buf = fields.get("jobInterviewDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setJobInterviewDate(ibuf);
}


		return returnVal;
 } 
	public static JobInterview map(GenericValue val) {

JobInterview returnVal = new JobInterview();
		returnVal.setJobInterviewId(val.getString("jobInterviewId"));
		returnVal.setJobIntervieweePartyId(val.getString("jobIntervieweePartyId"));
		returnVal.setJobRequisitionId(val.getString("jobRequisitionId"));
		returnVal.setJobInterviewerPartyId(val.getString("jobInterviewerPartyId"));
		returnVal.setJobInterviewTypeId(val.getString("jobInterviewTypeId"));
		returnVal.setGradeSecuredEnumId(val.getString("gradeSecuredEnumId"));
		returnVal.setJobInterviewResult(val.getString("jobInterviewResult"));
		returnVal.setJobInterviewDate(val.getTimestamp("jobInterviewDate"));


return returnVal;

}

public static JobInterview map(HttpServletRequest request) throws Exception {

		JobInterview returnVal = new JobInterview();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("jobInterviewId")) {
returnVal.setJobInterviewId(request.getParameter("jobInterviewId"));
}

		if(paramMap.containsKey("jobIntervieweePartyId"))  {
returnVal.setJobIntervieweePartyId(request.getParameter("jobIntervieweePartyId"));
}
		if(paramMap.containsKey("jobRequisitionId"))  {
returnVal.setJobRequisitionId(request.getParameter("jobRequisitionId"));
}
		if(paramMap.containsKey("jobInterviewerPartyId"))  {
returnVal.setJobInterviewerPartyId(request.getParameter("jobInterviewerPartyId"));
}
		if(paramMap.containsKey("jobInterviewTypeId"))  {
returnVal.setJobInterviewTypeId(request.getParameter("jobInterviewTypeId"));
}
		if(paramMap.containsKey("gradeSecuredEnumId"))  {
returnVal.setGradeSecuredEnumId(request.getParameter("gradeSecuredEnumId"));
}
		if(paramMap.containsKey("jobInterviewResult"))  {
returnVal.setJobInterviewResult(request.getParameter("jobInterviewResult"));
}
		if(paramMap.containsKey("jobInterviewDate"))  {
String buf = request.getParameter("jobInterviewDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setJobInterviewDate(ibuf);
}
return returnVal;

}
}
