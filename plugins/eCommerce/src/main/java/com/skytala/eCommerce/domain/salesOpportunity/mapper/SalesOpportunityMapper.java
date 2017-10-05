package com.skytala.eCommerce.domain.salesOpportunity.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.salesOpportunity.model.SalesOpportunity;

public class SalesOpportunityMapper  {


	public static Map<String, Object> map(SalesOpportunity salesopportunity) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(salesopportunity.getSalesOpportunityId() != null ){
			returnVal.put("salesOpportunityId",salesopportunity.getSalesOpportunityId());
}

		if(salesopportunity.getOpportunityName() != null ){
			returnVal.put("opportunityName",salesopportunity.getOpportunityName());
}

		if(salesopportunity.getDescription() != null ){
			returnVal.put("description",salesopportunity.getDescription());
}

		if(salesopportunity.getNextStep() != null ){
			returnVal.put("nextStep",salesopportunity.getNextStep());
}

		if(salesopportunity.getNextStepDate() != null ){
			returnVal.put("nextStepDate",salesopportunity.getNextStepDate());
}

		if(salesopportunity.getEstimatedAmount() != null ){
			returnVal.put("estimatedAmount",salesopportunity.getEstimatedAmount());
}

		if(salesopportunity.getEstimatedProbability() != null ){
			returnVal.put("estimatedProbability",salesopportunity.getEstimatedProbability());
}

		if(salesopportunity.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",salesopportunity.getCurrencyUomId());
}

		if(salesopportunity.getMarketingCampaignId() != null ){
			returnVal.put("marketingCampaignId",salesopportunity.getMarketingCampaignId());
}

		if(salesopportunity.getDataSourceId() != null ){
			returnVal.put("dataSourceId",salesopportunity.getDataSourceId());
}

		if(salesopportunity.getEstimatedCloseDate() != null ){
			returnVal.put("estimatedCloseDate",salesopportunity.getEstimatedCloseDate());
}

		if(salesopportunity.getOpportunityStageId() != null ){
			returnVal.put("opportunityStageId",salesopportunity.getOpportunityStageId());
}

		if(salesopportunity.getTypeEnumId() != null ){
			returnVal.put("typeEnumId",salesopportunity.getTypeEnumId());
}

		if(salesopportunity.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",salesopportunity.getCreatedByUserLogin());
}

		return returnVal;
}


	public static SalesOpportunity map(Map<String, Object> fields) {

		SalesOpportunity returnVal = new SalesOpportunity();

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}

		if(fields.get("opportunityName") != null) {
			returnVal.setOpportunityName((String) fields.get("opportunityName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("nextStep") != null) {
			returnVal.setNextStep((String) fields.get("nextStep"));
}

		if(fields.get("nextStepDate") != null) {
			returnVal.setNextStepDate((Timestamp) fields.get("nextStepDate"));
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

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("dataSourceId") != null) {
			returnVal.setDataSourceId((String) fields.get("dataSourceId"));
}

		if(fields.get("estimatedCloseDate") != null) {
			returnVal.setEstimatedCloseDate((Timestamp) fields.get("estimatedCloseDate"));
}

		if(fields.get("opportunityStageId") != null) {
			returnVal.setOpportunityStageId((String) fields.get("opportunityStageId"));
}

		if(fields.get("typeEnumId") != null) {
			returnVal.setTypeEnumId((String) fields.get("typeEnumId"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}


		return returnVal;
 } 
	public static SalesOpportunity mapstrstr(Map<String, String> fields) throws Exception {

		SalesOpportunity returnVal = new SalesOpportunity();

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}

		if(fields.get("opportunityName") != null) {
			returnVal.setOpportunityName((String) fields.get("opportunityName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("nextStep") != null) {
			returnVal.setNextStep((String) fields.get("nextStep"));
}

		if(fields.get("nextStepDate") != null) {
String buf = fields.get("nextStepDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setNextStepDate(ibuf);
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

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("dataSourceId") != null) {
			returnVal.setDataSourceId((String) fields.get("dataSourceId"));
}

		if(fields.get("estimatedCloseDate") != null) {
String buf = fields.get("estimatedCloseDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedCloseDate(ibuf);
}

		if(fields.get("opportunityStageId") != null) {
			returnVal.setOpportunityStageId((String) fields.get("opportunityStageId"));
}

		if(fields.get("typeEnumId") != null) {
			returnVal.setTypeEnumId((String) fields.get("typeEnumId"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}


		return returnVal;
 } 
	public static SalesOpportunity map(GenericValue val) {

SalesOpportunity returnVal = new SalesOpportunity();
		returnVal.setSalesOpportunityId(val.getString("salesOpportunityId"));
		returnVal.setOpportunityName(val.getString("opportunityName"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setNextStep(val.getString("nextStep"));
		returnVal.setNextStepDate(val.getTimestamp("nextStepDate"));
		returnVal.setEstimatedAmount(val.getBigDecimal("estimatedAmount"));
		returnVal.setEstimatedProbability(val.getBigDecimal("estimatedProbability"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setMarketingCampaignId(val.getString("marketingCampaignId"));
		returnVal.setDataSourceId(val.getString("dataSourceId"));
		returnVal.setEstimatedCloseDate(val.getTimestamp("estimatedCloseDate"));
		returnVal.setOpportunityStageId(val.getString("opportunityStageId"));
		returnVal.setTypeEnumId(val.getString("typeEnumId"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));


return returnVal;

}

public static SalesOpportunity map(HttpServletRequest request) throws Exception {

		SalesOpportunity returnVal = new SalesOpportunity();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("salesOpportunityId")) {
returnVal.setSalesOpportunityId(request.getParameter("salesOpportunityId"));
}

		if(paramMap.containsKey("opportunityName"))  {
returnVal.setOpportunityName(request.getParameter("opportunityName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("nextStep"))  {
returnVal.setNextStep(request.getParameter("nextStep"));
}
		if(paramMap.containsKey("nextStepDate"))  {
String buf = request.getParameter("nextStepDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setNextStepDate(ibuf);
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
		if(paramMap.containsKey("marketingCampaignId"))  {
returnVal.setMarketingCampaignId(request.getParameter("marketingCampaignId"));
}
		if(paramMap.containsKey("dataSourceId"))  {
returnVal.setDataSourceId(request.getParameter("dataSourceId"));
}
		if(paramMap.containsKey("estimatedCloseDate"))  {
String buf = request.getParameter("estimatedCloseDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEstimatedCloseDate(ibuf);
}
		if(paramMap.containsKey("opportunityStageId"))  {
returnVal.setOpportunityStageId(request.getParameter("opportunityStageId"));
}
		if(paramMap.containsKey("typeEnumId"))  {
returnVal.setTypeEnumId(request.getParameter("typeEnumId"));
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
return returnVal;

}
}
