package com.skytala.eCommerce.domain.humanres.relations.jobRequisition.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.model.JobRequisition;

public class JobRequisitionMapper  {


	public static Map<String, Object> map(JobRequisition jobrequisition) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(jobrequisition.getJobRequisitionId() != null ){
			returnVal.put("jobRequisitionId",jobrequisition.getJobRequisitionId());
}

		if(jobrequisition.getDurationMonths() != null ){
			returnVal.put("durationMonths",jobrequisition.getDurationMonths());
}

		if(jobrequisition.getAge() != null ){
			returnVal.put("age",jobrequisition.getAge());
}

		if(jobrequisition.getGender() != null ){
			returnVal.put("gender",jobrequisition.getGender());
}

		if(jobrequisition.getExperienceMonths() != null ){
			returnVal.put("experienceMonths",jobrequisition.getExperienceMonths());
}

		if(jobrequisition.getExperienceYears() != null ){
			returnVal.put("experienceYears",jobrequisition.getExperienceYears());
}

		if(jobrequisition.getQualification() != null ){
			returnVal.put("qualification",jobrequisition.getQualification());
}

		if(jobrequisition.getJobLocation() != null ){
			returnVal.put("jobLocation",jobrequisition.getJobLocation());
}

		if(jobrequisition.getSkillTypeId() != null ){
			returnVal.put("skillTypeId",jobrequisition.getSkillTypeId());
}

		if(jobrequisition.getNoOfResources() != null ){
			returnVal.put("noOfResources",jobrequisition.getNoOfResources());
}

		if(jobrequisition.getJobPostingTypeEnumId() != null ){
			returnVal.put("jobPostingTypeEnumId",jobrequisition.getJobPostingTypeEnumId());
}

		if(jobrequisition.getJobRequisitionDate() != null ){
			returnVal.put("jobRequisitionDate",jobrequisition.getJobRequisitionDate());
}

		if(jobrequisition.getExamTypeEnumId() != null ){
			returnVal.put("examTypeEnumId",jobrequisition.getExamTypeEnumId());
}

		if(jobrequisition.getRequiredOnDate() != null ){
			returnVal.put("requiredOnDate",jobrequisition.getRequiredOnDate());
}

		return returnVal;
}


	public static JobRequisition map(Map<String, Object> fields) {

		JobRequisition returnVal = new JobRequisition();

		if(fields.get("jobRequisitionId") != null) {
			returnVal.setJobRequisitionId((String) fields.get("jobRequisitionId"));
}

		if(fields.get("durationMonths") != null) {
			returnVal.setDurationMonths((long) fields.get("durationMonths"));
}

		if(fields.get("age") != null) {
			returnVal.setAge((long) fields.get("age"));
}

		if(fields.get("gender") != null) {
			returnVal.setGender((boolean) fields.get("gender"));
}

		if(fields.get("experienceMonths") != null) {
			returnVal.setExperienceMonths((long) fields.get("experienceMonths"));
}

		if(fields.get("experienceYears") != null) {
			returnVal.setExperienceYears((long) fields.get("experienceYears"));
}

		if(fields.get("qualification") != null) {
			returnVal.setQualification((String) fields.get("qualification"));
}

		if(fields.get("jobLocation") != null) {
			returnVal.setJobLocation((String) fields.get("jobLocation"));
}

		if(fields.get("skillTypeId") != null) {
			returnVal.setSkillTypeId((String) fields.get("skillTypeId"));
}

		if(fields.get("noOfResources") != null) {
			returnVal.setNoOfResources((long) fields.get("noOfResources"));
}

		if(fields.get("jobPostingTypeEnumId") != null) {
			returnVal.setJobPostingTypeEnumId((String) fields.get("jobPostingTypeEnumId"));
}

		if(fields.get("jobRequisitionDate") != null) {
			returnVal.setJobRequisitionDate((Timestamp) fields.get("jobRequisitionDate"));
}

		if(fields.get("examTypeEnumId") != null) {
			returnVal.setExamTypeEnumId((String) fields.get("examTypeEnumId"));
}

		if(fields.get("requiredOnDate") != null) {
			returnVal.setRequiredOnDate((Timestamp) fields.get("requiredOnDate"));
}


		return returnVal;
 } 
	public static JobRequisition mapstrstr(Map<String, String> fields) throws Exception {

		JobRequisition returnVal = new JobRequisition();

		if(fields.get("jobRequisitionId") != null) {
			returnVal.setJobRequisitionId((String) fields.get("jobRequisitionId"));
}

		if(fields.get("durationMonths") != null) {
String buf;
buf = fields.get("durationMonths");
long ibuf = Long.parseLong(buf);
			returnVal.setDurationMonths(ibuf);
}

		if(fields.get("age") != null) {
String buf;
buf = fields.get("age");
long ibuf = Long.parseLong(buf);
			returnVal.setAge(ibuf);
}

		if(fields.get("gender") != null) {
String buf;
buf = fields.get("gender");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setGender(ibuf);
}

		if(fields.get("experienceMonths") != null) {
String buf;
buf = fields.get("experienceMonths");
long ibuf = Long.parseLong(buf);
			returnVal.setExperienceMonths(ibuf);
}

		if(fields.get("experienceYears") != null) {
String buf;
buf = fields.get("experienceYears");
long ibuf = Long.parseLong(buf);
			returnVal.setExperienceYears(ibuf);
}

		if(fields.get("qualification") != null) {
			returnVal.setQualification((String) fields.get("qualification"));
}

		if(fields.get("jobLocation") != null) {
			returnVal.setJobLocation((String) fields.get("jobLocation"));
}

		if(fields.get("skillTypeId") != null) {
			returnVal.setSkillTypeId((String) fields.get("skillTypeId"));
}

		if(fields.get("noOfResources") != null) {
String buf;
buf = fields.get("noOfResources");
long ibuf = Long.parseLong(buf);
			returnVal.setNoOfResources(ibuf);
}

		if(fields.get("jobPostingTypeEnumId") != null) {
			returnVal.setJobPostingTypeEnumId((String) fields.get("jobPostingTypeEnumId"));
}

		if(fields.get("jobRequisitionDate") != null) {
String buf = fields.get("jobRequisitionDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setJobRequisitionDate(ibuf);
}

		if(fields.get("examTypeEnumId") != null) {
			returnVal.setExamTypeEnumId((String) fields.get("examTypeEnumId"));
}

		if(fields.get("requiredOnDate") != null) {
String buf = fields.get("requiredOnDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setRequiredOnDate(ibuf);
}


		return returnVal;
 } 
	public static JobRequisition map(GenericValue val) {

JobRequisition returnVal = new JobRequisition();
		returnVal.setJobRequisitionId(val.getString("jobRequisitionId"));
		returnVal.setDurationMonths(val.getLong("durationMonths"));
		returnVal.setAge(val.getLong("age"));
		returnVal.setGender(val.getBoolean("gender"));
		returnVal.setExperienceMonths(val.getLong("experienceMonths"));
		returnVal.setExperienceYears(val.getLong("experienceYears"));
		returnVal.setQualification(val.getString("qualification"));
		returnVal.setJobLocation(val.getString("jobLocation"));
		returnVal.setSkillTypeId(val.getString("skillTypeId"));
		returnVal.setNoOfResources(val.getLong("noOfResources"));
		returnVal.setJobPostingTypeEnumId(val.getString("jobPostingTypeEnumId"));
		returnVal.setJobRequisitionDate(val.getTimestamp("jobRequisitionDate"));
		returnVal.setExamTypeEnumId(val.getString("examTypeEnumId"));
		returnVal.setRequiredOnDate(val.getTimestamp("requiredOnDate"));


return returnVal;

}

public static JobRequisition map(HttpServletRequest request) throws Exception {

		JobRequisition returnVal = new JobRequisition();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("jobRequisitionId")) {
returnVal.setJobRequisitionId(request.getParameter("jobRequisitionId"));
}

		if(paramMap.containsKey("durationMonths"))  {
String buf = request.getParameter("durationMonths");
Long ibuf = Long.parseLong(buf);
returnVal.setDurationMonths(ibuf);
}
		if(paramMap.containsKey("age"))  {
String buf = request.getParameter("age");
Long ibuf = Long.parseLong(buf);
returnVal.setAge(ibuf);
}
		if(paramMap.containsKey("gender"))  {
String buf = request.getParameter("gender");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setGender(ibuf);
}
		if(paramMap.containsKey("experienceMonths"))  {
String buf = request.getParameter("experienceMonths");
Long ibuf = Long.parseLong(buf);
returnVal.setExperienceMonths(ibuf);
}
		if(paramMap.containsKey("experienceYears"))  {
String buf = request.getParameter("experienceYears");
Long ibuf = Long.parseLong(buf);
returnVal.setExperienceYears(ibuf);
}
		if(paramMap.containsKey("qualification"))  {
returnVal.setQualification(request.getParameter("qualification"));
}
		if(paramMap.containsKey("jobLocation"))  {
returnVal.setJobLocation(request.getParameter("jobLocation"));
}
		if(paramMap.containsKey("skillTypeId"))  {
returnVal.setSkillTypeId(request.getParameter("skillTypeId"));
}
		if(paramMap.containsKey("noOfResources"))  {
String buf = request.getParameter("noOfResources");
Long ibuf = Long.parseLong(buf);
returnVal.setNoOfResources(ibuf);
}
		if(paramMap.containsKey("jobPostingTypeEnumId"))  {
returnVal.setJobPostingTypeEnumId(request.getParameter("jobPostingTypeEnumId"));
}
		if(paramMap.containsKey("jobRequisitionDate"))  {
String buf = request.getParameter("jobRequisitionDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setJobRequisitionDate(ibuf);
}
		if(paramMap.containsKey("examTypeEnumId"))  {
returnVal.setExamTypeEnumId(request.getParameter("examTypeEnumId"));
}
		if(paramMap.containsKey("requiredOnDate"))  {
String buf = request.getParameter("requiredOnDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setRequiredOnDate(ibuf);
}
return returnVal;

}
}
