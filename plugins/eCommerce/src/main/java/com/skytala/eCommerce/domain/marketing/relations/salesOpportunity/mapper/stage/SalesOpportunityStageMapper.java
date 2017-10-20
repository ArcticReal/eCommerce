package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.stage;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.stage.SalesOpportunityStage;

public class SalesOpportunityStageMapper  {


	public static Map<String, Object> map(SalesOpportunityStage salesopportunitystage) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(salesopportunitystage.getOpportunityStageId() != null ){
			returnVal.put("opportunityStageId",salesopportunitystage.getOpportunityStageId());
}

		if(salesopportunitystage.getDescription() != null ){
			returnVal.put("description",salesopportunitystage.getDescription());
}

		if(salesopportunitystage.getDefaultProbability() != null ){
			returnVal.put("defaultProbability",salesopportunitystage.getDefaultProbability());
}

		if(salesopportunitystage.getSequenceNum() != null ){
			returnVal.put("sequenceNum",salesopportunitystage.getSequenceNum());
}

		return returnVal;
}


	public static SalesOpportunityStage map(Map<String, Object> fields) {

		SalesOpportunityStage returnVal = new SalesOpportunityStage();

		if(fields.get("opportunityStageId") != null) {
			returnVal.setOpportunityStageId((String) fields.get("opportunityStageId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("defaultProbability") != null) {
			returnVal.setDefaultProbability((BigDecimal) fields.get("defaultProbability"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static SalesOpportunityStage mapstrstr(Map<String, String> fields) throws Exception {

		SalesOpportunityStage returnVal = new SalesOpportunityStage();

		if(fields.get("opportunityStageId") != null) {
			returnVal.setOpportunityStageId((String) fields.get("opportunityStageId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("defaultProbability") != null) {
String buf;
buf = fields.get("defaultProbability");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setDefaultProbability(bd);
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static SalesOpportunityStage map(GenericValue val) {

SalesOpportunityStage returnVal = new SalesOpportunityStage();
		returnVal.setOpportunityStageId(val.getString("opportunityStageId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setDefaultProbability(val.getBigDecimal("defaultProbability"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static SalesOpportunityStage map(HttpServletRequest request) throws Exception {

		SalesOpportunityStage returnVal = new SalesOpportunityStage();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("opportunityStageId")) {
returnVal.setOpportunityStageId(request.getParameter("opportunityStageId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("defaultProbability"))  {
String buf = request.getParameter("defaultProbability");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setDefaultProbability(bd);
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
