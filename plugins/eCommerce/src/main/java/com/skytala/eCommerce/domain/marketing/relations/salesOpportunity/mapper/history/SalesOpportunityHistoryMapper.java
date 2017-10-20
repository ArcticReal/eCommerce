package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.history;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.history.SalesOpportunityHistory;

public class SalesOpportunityHistoryMapper  {


	public static Map<String, Object> map(SalesOpportunityHistory salesopportunityhistory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(salesopportunityhistory.getSalesOpportunityHistoryId() != null ){
			returnVal.put("salesOpportunityHistoryId",salesopportunityhistory.getSalesOpportunityHistoryId());
}

		if(salesopportunityhistory.getSalesOpportunityId() != null ){
			returnVal.put("salesOpportunityId",salesopportunityhistory.getSalesOpportunityId());
}

		if(salesopportunityhistory.getDescription() != null ){
			returnVal.put("description",salesopportunityhistory.getDescription());
}

		if(salesopportunityhistory.getNextStep() != null ){
			returnVal.put("nextStep",salesopportunityhistory.getNextStep());
}

		if(salesopportunityhistory.getEstimatedAmount() != null ){
			returnVal.put("estimatedAmount",salesopportunityhistory.getEstimatedAmount());
}

		if(salesopportunityhistory.getEstimatedProbability() != null ){
			returnVal.put("estimatedProbability",salesopportunityhistory.getEstimatedProbability());
}

		if(salesopportunityhistory.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",salesopportunityhistory.getCurrencyUomId());
}

		if(salesopportunityhistory.getEstimatedCloseDate() != null ){
			returnVal.put("estimatedCloseDate",salesopportunityhistory.getEstimatedCloseDate());
}

		if(salesopportunityhistory.getOpportunityStageId() != null ){
			returnVal.put("opportunityStageId",salesopportunityhistory.getOpportunityStageId());
}

		if(salesopportunityhistory.getChangeNote() != null ){
			returnVal.put("changeNote",salesopportunityhistory.getChangeNote());
}

		if(salesopportunityhistory.getModifiedByUserLogin() != null ){
			returnVal.put("modifiedByUserLogin",salesopportunityhistory.getModifiedByUserLogin());
}

		if(salesopportunityhistory.getModifiedTimestamp() != null ){
			returnVal.put("modifiedTimestamp",salesopportunityhistory.getModifiedTimestamp());
}

		return returnVal;
}


	public static SalesOpportunityHistory map(Map<String, Object> fields) {

		SalesOpportunityHistory returnVal = new SalesOpportunityHistory();

		if(fields.get("salesOpportunityHistoryId") != null) {
			returnVal.setSalesOpportunityHistoryId((String) fields.get("salesOpportunityHistoryId"));
}

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("nextStep") != null) {
			returnVal.setNextStep((String) fields.get("nextStep"));
}

		if(fields.get("estimatedAmount") != null) {
			returnVal.setEstimatedAmount((BigDecimal) fields.get("estimatedAmount"));
}

		if(fields.get("estimatedProbability") != null) {
			returnVal.setEstimatedProbability((BigDecimal) fields.get("estimatedProbability"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("estimatedCloseDate") != null) {
			returnVal.setEstimatedCloseDate((Timestamp) fields.get("estimatedCloseDate"));
}

		if(fields.get("opportunityStageId") != null) {
			returnVal.setOpportunityStageId((String) fields.get("opportunityStageId"));
}

		if(fields.get("changeNote") != null) {
			returnVal.setChangeNote((String) fields.get("changeNote"));
}

		if(fields.get("modifiedByUserLogin") != null) {
			returnVal.setModifiedByUserLogin((String) fields.get("modifiedByUserLogin"));
}

		if(fields.get("modifiedTimestamp") != null) {
			returnVal.setModifiedTimestamp((Timestamp) fields.get("modifiedTimestamp"));
}


		return returnVal;
 } 
	public static SalesOpportunityHistory mapstrstr(Map<String, String> fields) throws Exception {

		SalesOpportunityHistory returnVal = new SalesOpportunityHistory();

		if(fields.get("salesOpportunityHistoryId") != null) {
			returnVal.setSalesOpportunityHistoryId((String) fields.get("salesOpportunityHistoryId"));
}

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("nextStep") != null) {
			returnVal.setNextStep((String) fields.get("nextStep"));
}

		if(fields.get("estimatedAmount") != null) {
String buf;
buf = fields.get("estimatedAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedAmount(bd);
}

		if(fields.get("estimatedProbability") != null) {
String buf;
buf = fields.get("estimatedProbability");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedProbability(bd);
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("estimatedCloseDate") != null) {
String buf = fields.get("estimatedCloseDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedCloseDate(ibuf);
}

		if(fields.get("opportunityStageId") != null) {
			returnVal.setOpportunityStageId((String) fields.get("opportunityStageId"));
}

		if(fields.get("changeNote") != null) {
			returnVal.setChangeNote((String) fields.get("changeNote"));
}

		if(fields.get("modifiedByUserLogin") != null) {
			returnVal.setModifiedByUserLogin((String) fields.get("modifiedByUserLogin"));
}

		if(fields.get("modifiedTimestamp") != null) {
String buf = fields.get("modifiedTimestamp");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setModifiedTimestamp(ibuf);
}


		return returnVal;
 } 
	public static SalesOpportunityHistory map(GenericValue val) {

SalesOpportunityHistory returnVal = new SalesOpportunityHistory();
		returnVal.setSalesOpportunityHistoryId(val.getString("salesOpportunityHistoryId"));
		returnVal.setSalesOpportunityId(val.getString("salesOpportunityId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setNextStep(val.getString("nextStep"));
		returnVal.setEstimatedAmount(val.getBigDecimal("estimatedAmount"));
		returnVal.setEstimatedProbability(val.getBigDecimal("estimatedProbability"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setEstimatedCloseDate(val.getTimestamp("estimatedCloseDate"));
		returnVal.setOpportunityStageId(val.getString("opportunityStageId"));
		returnVal.setChangeNote(val.getString("changeNote"));
		returnVal.setModifiedByUserLogin(val.getString("modifiedByUserLogin"));
		returnVal.setModifiedTimestamp(val.getTimestamp("modifiedTimestamp"));


return returnVal;

}

public static SalesOpportunityHistory map(HttpServletRequest request) throws Exception {

		SalesOpportunityHistory returnVal = new SalesOpportunityHistory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("salesOpportunityHistoryId")) {
returnVal.setSalesOpportunityHistoryId(request.getParameter("salesOpportunityHistoryId"));
}

		if(paramMap.containsKey("salesOpportunityId"))  {
returnVal.setSalesOpportunityId(request.getParameter("salesOpportunityId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("nextStep"))  {
returnVal.setNextStep(request.getParameter("nextStep"));
}
		if(paramMap.containsKey("estimatedAmount"))  {
String buf = request.getParameter("estimatedAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedAmount(bd);
}
		if(paramMap.containsKey("estimatedProbability"))  {
String buf = request.getParameter("estimatedProbability");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedProbability(bd);
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("estimatedCloseDate"))  {
String buf = request.getParameter("estimatedCloseDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEstimatedCloseDate(ibuf);
}
		if(paramMap.containsKey("opportunityStageId"))  {
returnVal.setOpportunityStageId(request.getParameter("opportunityStageId"));
}
		if(paramMap.containsKey("changeNote"))  {
returnVal.setChangeNote(request.getParameter("changeNote"));
}
		if(paramMap.containsKey("modifiedByUserLogin"))  {
returnVal.setModifiedByUserLogin(request.getParameter("modifiedByUserLogin"));
}
		if(paramMap.containsKey("modifiedTimestamp"))  {
String buf = request.getParameter("modifiedTimestamp");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setModifiedTimestamp(ibuf);
}
return returnVal;

}
}
