package com.skytala.eCommerce.domain.humanres.relations.jobInterview.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.model.type.JobInterviewType;

public class JobInterviewTypeMapper  {


	public static Map<String, Object> map(JobInterviewType jobinterviewtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(jobinterviewtype.getJobInterviewTypeId() != null ){
			returnVal.put("jobInterviewTypeId",jobinterviewtype.getJobInterviewTypeId());
}

		if(jobinterviewtype.getDescription() != null ){
			returnVal.put("description",jobinterviewtype.getDescription());
}

		return returnVal;
}


	public static JobInterviewType map(Map<String, Object> fields) {

		JobInterviewType returnVal = new JobInterviewType();

		if(fields.get("jobInterviewTypeId") != null) {
			returnVal.setJobInterviewTypeId((String) fields.get("jobInterviewTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static JobInterviewType mapstrstr(Map<String, String> fields) throws Exception {

		JobInterviewType returnVal = new JobInterviewType();

		if(fields.get("jobInterviewTypeId") != null) {
			returnVal.setJobInterviewTypeId((String) fields.get("jobInterviewTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static JobInterviewType map(GenericValue val) {

JobInterviewType returnVal = new JobInterviewType();
		returnVal.setJobInterviewTypeId(val.getString("jobInterviewTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static JobInterviewType map(HttpServletRequest request) throws Exception {

		JobInterviewType returnVal = new JobInterviewType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("jobInterviewTypeId")) {
returnVal.setJobInterviewTypeId(request.getParameter("jobInterviewTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
