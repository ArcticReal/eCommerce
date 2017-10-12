package com.skytala.eCommerce.domain.workeffort.relations.workEffortSkillStandard.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSkillStandard.model.WorkEffortSkillStandard;

public class WorkEffortSkillStandardMapper  {


	public static Map<String, Object> map(WorkEffortSkillStandard workeffortskillstandard) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortskillstandard.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortskillstandard.getWorkEffortId());
}

		if(workeffortskillstandard.getSkillTypeId() != null ){
			returnVal.put("skillTypeId",workeffortskillstandard.getSkillTypeId());
}

		if(workeffortskillstandard.getEstimatedNumPeople() != null ){
			returnVal.put("estimatedNumPeople",workeffortskillstandard.getEstimatedNumPeople());
}

		if(workeffortskillstandard.getEstimatedDuration() != null ){
			returnVal.put("estimatedDuration",workeffortskillstandard.getEstimatedDuration());
}

		if(workeffortskillstandard.getEstimatedCost() != null ){
			returnVal.put("estimatedCost",workeffortskillstandard.getEstimatedCost());
}

		return returnVal;
}


	public static WorkEffortSkillStandard map(Map<String, Object> fields) {

		WorkEffortSkillStandard returnVal = new WorkEffortSkillStandard();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("skillTypeId") != null) {
			returnVal.setSkillTypeId((String) fields.get("skillTypeId"));
}

		if(fields.get("estimatedNumPeople") != null) {
			returnVal.setEstimatedNumPeople((BigDecimal) fields.get("estimatedNumPeople"));
}

		if(fields.get("estimatedDuration") != null) {
			returnVal.setEstimatedDuration((BigDecimal) fields.get("estimatedDuration"));
}

		if(fields.get("estimatedCost") != null) {
			returnVal.setEstimatedCost((BigDecimal) fields.get("estimatedCost"));
}


		return returnVal;
 } 
	public static WorkEffortSkillStandard mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortSkillStandard returnVal = new WorkEffortSkillStandard();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("skillTypeId") != null) {
			returnVal.setSkillTypeId((String) fields.get("skillTypeId"));
}

		if(fields.get("estimatedNumPeople") != null) {
String buf;
buf = fields.get("estimatedNumPeople");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedNumPeople(bd);
}

		if(fields.get("estimatedDuration") != null) {
String buf;
buf = fields.get("estimatedDuration");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedDuration(bd);
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
	public static WorkEffortSkillStandard map(GenericValue val) {

WorkEffortSkillStandard returnVal = new WorkEffortSkillStandard();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setSkillTypeId(val.getString("skillTypeId"));
		returnVal.setEstimatedNumPeople(val.getBigDecimal("estimatedNumPeople"));
		returnVal.setEstimatedDuration(val.getBigDecimal("estimatedDuration"));
		returnVal.setEstimatedCost(val.getBigDecimal("estimatedCost"));


return returnVal;

}

public static WorkEffortSkillStandard map(HttpServletRequest request) throws Exception {

		WorkEffortSkillStandard returnVal = new WorkEffortSkillStandard();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("skillTypeId"))  {
returnVal.setSkillTypeId(request.getParameter("skillTypeId"));
}
		if(paramMap.containsKey("estimatedNumPeople"))  {
String buf = request.getParameter("estimatedNumPeople");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedNumPeople(bd);
}
		if(paramMap.containsKey("estimatedDuration"))  {
String buf = request.getParameter("estimatedDuration");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedDuration(bd);
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
