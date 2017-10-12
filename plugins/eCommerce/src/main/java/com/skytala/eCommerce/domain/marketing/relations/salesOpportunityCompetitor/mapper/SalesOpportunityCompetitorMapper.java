package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityCompetitor.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityCompetitor.model.SalesOpportunityCompetitor;

public class SalesOpportunityCompetitorMapper  {


	public static Map<String, Object> map(SalesOpportunityCompetitor salesopportunitycompetitor) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(salesopportunitycompetitor.getSalesOpportunityId() != null ){
			returnVal.put("salesOpportunityId",salesopportunitycompetitor.getSalesOpportunityId());
}

		if(salesopportunitycompetitor.getCompetitorPartyId() != null ){
			returnVal.put("competitorPartyId",salesopportunitycompetitor.getCompetitorPartyId());
}

		if(salesopportunitycompetitor.getPositionEnumId() != null ){
			returnVal.put("positionEnumId",salesopportunitycompetitor.getPositionEnumId());
}

		if(salesopportunitycompetitor.getStrengths() != null ){
			returnVal.put("strengths",salesopportunitycompetitor.getStrengths());
}

		if(salesopportunitycompetitor.getWeaknesses() != null ){
			returnVal.put("weaknesses",salesopportunitycompetitor.getWeaknesses());
}

		return returnVal;
}


	public static SalesOpportunityCompetitor map(Map<String, Object> fields) {

		SalesOpportunityCompetitor returnVal = new SalesOpportunityCompetitor();

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}

		if(fields.get("competitorPartyId") != null) {
			returnVal.setCompetitorPartyId((String) fields.get("competitorPartyId"));
}

		if(fields.get("positionEnumId") != null) {
			returnVal.setPositionEnumId((String) fields.get("positionEnumId"));
}

		if(fields.get("strengths") != null) {
			returnVal.setStrengths((String) fields.get("strengths"));
}

		if(fields.get("weaknesses") != null) {
			returnVal.setWeaknesses((String) fields.get("weaknesses"));
}


		return returnVal;
 } 
	public static SalesOpportunityCompetitor mapstrstr(Map<String, String> fields) throws Exception {

		SalesOpportunityCompetitor returnVal = new SalesOpportunityCompetitor();

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}

		if(fields.get("competitorPartyId") != null) {
			returnVal.setCompetitorPartyId((String) fields.get("competitorPartyId"));
}

		if(fields.get("positionEnumId") != null) {
			returnVal.setPositionEnumId((String) fields.get("positionEnumId"));
}

		if(fields.get("strengths") != null) {
			returnVal.setStrengths((String) fields.get("strengths"));
}

		if(fields.get("weaknesses") != null) {
			returnVal.setWeaknesses((String) fields.get("weaknesses"));
}


		return returnVal;
 } 
	public static SalesOpportunityCompetitor map(GenericValue val) {

SalesOpportunityCompetitor returnVal = new SalesOpportunityCompetitor();
		returnVal.setSalesOpportunityId(val.getString("salesOpportunityId"));
		returnVal.setCompetitorPartyId(val.getString("competitorPartyId"));
		returnVal.setPositionEnumId(val.getString("positionEnumId"));
		returnVal.setStrengths(val.getString("strengths"));
		returnVal.setWeaknesses(val.getString("weaknesses"));


return returnVal;

}

public static SalesOpportunityCompetitor map(HttpServletRequest request) throws Exception {

		SalesOpportunityCompetitor returnVal = new SalesOpportunityCompetitor();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("salesOpportunityId")) {
returnVal.setSalesOpportunityId(request.getParameter("salesOpportunityId"));
}

		if(paramMap.containsKey("competitorPartyId"))  {
returnVal.setCompetitorPartyId(request.getParameter("competitorPartyId"));
}
		if(paramMap.containsKey("positionEnumId"))  {
returnVal.setPositionEnumId(request.getParameter("positionEnumId"));
}
		if(paramMap.containsKey("strengths"))  {
returnVal.setStrengths(request.getParameter("strengths"));
}
		if(paramMap.containsKey("weaknesses"))  {
returnVal.setWeaknesses(request.getParameter("weaknesses"));
}
return returnVal;

}
}
