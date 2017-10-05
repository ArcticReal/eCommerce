package com.skytala.eCommerce.domain.marketingCampaign.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketingCampaign.model.MarketingCampaign;

public class MarketingCampaignMapper  {


	public static Map<String, Object> map(MarketingCampaign marketingcampaign) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(marketingcampaign.getMarketingCampaignId() != null ){
			returnVal.put("marketingCampaignId",marketingcampaign.getMarketingCampaignId());
}

		if(marketingcampaign.getParentCampaignId() != null ){
			returnVal.put("parentCampaignId",marketingcampaign.getParentCampaignId());
}

		if(marketingcampaign.getStatusId() != null ){
			returnVal.put("statusId",marketingcampaign.getStatusId());
}

		if(marketingcampaign.getCampaignName() != null ){
			returnVal.put("campaignName",marketingcampaign.getCampaignName());
}

		if(marketingcampaign.getCampaignSummary() != null ){
			returnVal.put("campaignSummary",marketingcampaign.getCampaignSummary());
}

		if(marketingcampaign.getBudgetedCost() != null ){
			returnVal.put("budgetedCost",marketingcampaign.getBudgetedCost());
}

		if(marketingcampaign.getActualCost() != null ){
			returnVal.put("actualCost",marketingcampaign.getActualCost());
}

		if(marketingcampaign.getEstimatedCost() != null ){
			returnVal.put("estimatedCost",marketingcampaign.getEstimatedCost());
}

		if(marketingcampaign.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",marketingcampaign.getCurrencyUomId());
}

		if(marketingcampaign.getFromDate() != null ){
			returnVal.put("fromDate",marketingcampaign.getFromDate());
}

		if(marketingcampaign.getThruDate() != null ){
			returnVal.put("thruDate",marketingcampaign.getThruDate());
}

		if(marketingcampaign.getIsActive() != null ){
			returnVal.put("isActive",marketingcampaign.getIsActive());
}

		if(marketingcampaign.getConvertedLeads() != null ){
			returnVal.put("convertedLeads",marketingcampaign.getConvertedLeads());
}

		if(marketingcampaign.getExpectedResponsePercent() != null ){
			returnVal.put("expectedResponsePercent",marketingcampaign.getExpectedResponsePercent());
}

		if(marketingcampaign.getExpectedRevenue() != null ){
			returnVal.put("expectedRevenue",marketingcampaign.getExpectedRevenue());
}

		if(marketingcampaign.getNumSent() != null ){
			returnVal.put("numSent",marketingcampaign.getNumSent());
}

		if(marketingcampaign.getStartDate() != null ){
			returnVal.put("startDate",marketingcampaign.getStartDate());
}

		if(marketingcampaign.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",marketingcampaign.getCreatedByUserLogin());
}

		if(marketingcampaign.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",marketingcampaign.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static MarketingCampaign map(Map<String, Object> fields) {

		MarketingCampaign returnVal = new MarketingCampaign();

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("parentCampaignId") != null) {
			returnVal.setParentCampaignId((String) fields.get("parentCampaignId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("campaignName") != null) {
			returnVal.setCampaignName((String) fields.get("campaignName"));
}

		if(fields.get("campaignSummary") != null) {
			returnVal.setCampaignSummary((String) fields.get("campaignSummary"));
}

		if(fields.get("budgetedCost") != null) {
			returnVal.setBudgetedCost((BigDecimal) fields.get("budgetedCost"));
}

		if(fields.get("actualCost") != null) {
			returnVal.setActualCost((BigDecimal) fields.get("actualCost"));
}

		if(fields.get("estimatedCost") != null) {
			returnVal.setEstimatedCost((BigDecimal) fields.get("estimatedCost"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("isActive") != null) {
			returnVal.setIsActive((boolean) fields.get("isActive"));
}

		if(fields.get("convertedLeads") != null) {
			returnVal.setConvertedLeads((String) fields.get("convertedLeads"));
}

		if(fields.get("expectedResponsePercent") != null) {
			returnVal.setExpectedResponsePercent((BigDecimal) fields.get("expectedResponsePercent"));
}

		if(fields.get("expectedRevenue") != null) {
			returnVal.setExpectedRevenue((BigDecimal) fields.get("expectedRevenue"));
}

		if(fields.get("numSent") != null) {
			returnVal.setNumSent((long) fields.get("numSent"));
}

		if(fields.get("startDate") != null) {
			returnVal.setStartDate((Timestamp) fields.get("startDate"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static MarketingCampaign mapstrstr(Map<String, String> fields) throws Exception {

		MarketingCampaign returnVal = new MarketingCampaign();

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("parentCampaignId") != null) {
			returnVal.setParentCampaignId((String) fields.get("parentCampaignId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("campaignName") != null) {
			returnVal.setCampaignName((String) fields.get("campaignName"));
}

		if(fields.get("campaignSummary") != null) {
			returnVal.setCampaignSummary((String) fields.get("campaignSummary"));
}

		if(fields.get("budgetedCost") != null) {
String buf;
buf = fields.get("budgetedCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBudgetedCost(bd);
}

		if(fields.get("actualCost") != null) {
String buf;
buf = fields.get("actualCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualCost(bd);
}

		if(fields.get("estimatedCost") != null) {
String buf;
buf = fields.get("estimatedCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedCost(bd);
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
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

		if(fields.get("isActive") != null) {
String buf;
buf = fields.get("isActive");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsActive(ibuf);
}

		if(fields.get("convertedLeads") != null) {
			returnVal.setConvertedLeads((String) fields.get("convertedLeads"));
}

		if(fields.get("expectedResponsePercent") != null) {
String buf;
buf = fields.get("expectedResponsePercent");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setExpectedResponsePercent(bd);
}

		if(fields.get("expectedRevenue") != null) {
String buf;
buf = fields.get("expectedRevenue");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setExpectedRevenue(bd);
}

		if(fields.get("numSent") != null) {
String buf;
buf = fields.get("numSent");
long ibuf = Long.parseLong(buf);
			returnVal.setNumSent(ibuf);
}

		if(fields.get("startDate") != null) {
String buf = fields.get("startDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStartDate(ibuf);
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static MarketingCampaign map(GenericValue val) {

MarketingCampaign returnVal = new MarketingCampaign();
		returnVal.setMarketingCampaignId(val.getString("marketingCampaignId"));
		returnVal.setParentCampaignId(val.getString("parentCampaignId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setCampaignName(val.getString("campaignName"));
		returnVal.setCampaignSummary(val.getString("campaignSummary"));
		returnVal.setBudgetedCost(val.getBigDecimal("budgetedCost"));
		returnVal.setActualCost(val.getBigDecimal("actualCost"));
		returnVal.setEstimatedCost(val.getBigDecimal("estimatedCost"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setIsActive(val.getBoolean("isActive"));
		returnVal.setConvertedLeads(val.getString("convertedLeads"));
		returnVal.setExpectedResponsePercent(val.getBigDecimal("expectedResponsePercent"));
		returnVal.setExpectedRevenue(val.getBigDecimal("expectedRevenue"));
		returnVal.setNumSent(val.getLong("numSent"));
		returnVal.setStartDate(val.getTimestamp("startDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static MarketingCampaign map(HttpServletRequest request) throws Exception {

		MarketingCampaign returnVal = new MarketingCampaign();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("marketingCampaignId")) {
returnVal.setMarketingCampaignId(request.getParameter("marketingCampaignId"));
}

		if(paramMap.containsKey("parentCampaignId"))  {
returnVal.setParentCampaignId(request.getParameter("parentCampaignId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("campaignName"))  {
returnVal.setCampaignName(request.getParameter("campaignName"));
}
		if(paramMap.containsKey("campaignSummary"))  {
returnVal.setCampaignSummary(request.getParameter("campaignSummary"));
}
		if(paramMap.containsKey("budgetedCost"))  {
String buf = request.getParameter("budgetedCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBudgetedCost(bd);
}
		if(paramMap.containsKey("actualCost"))  {
String buf = request.getParameter("actualCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualCost(bd);
}
		if(paramMap.containsKey("estimatedCost"))  {
String buf = request.getParameter("estimatedCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedCost(bd);
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
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
		if(paramMap.containsKey("isActive"))  {
String buf = request.getParameter("isActive");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsActive(ibuf);
}
		if(paramMap.containsKey("convertedLeads"))  {
returnVal.setConvertedLeads(request.getParameter("convertedLeads"));
}
		if(paramMap.containsKey("expectedResponsePercent"))  {
String buf = request.getParameter("expectedResponsePercent");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setExpectedResponsePercent(bd);
}
		if(paramMap.containsKey("expectedRevenue"))  {
String buf = request.getParameter("expectedRevenue");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setExpectedRevenue(bd);
}
		if(paramMap.containsKey("numSent"))  {
String buf = request.getParameter("numSent");
Long ibuf = Long.parseLong(buf);
returnVal.setNumSent(ibuf);
}
		if(paramMap.containsKey("startDate"))  {
String buf = request.getParameter("startDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStartDate(ibuf);
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
return returnVal;

}
}
