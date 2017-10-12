package com.skytala.eCommerce.domain.marketing.relations.salesForecast.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.SalesForecast;

public class SalesForecastMapper  {


	public static Map<String, Object> map(SalesForecast salesforecast) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(salesforecast.getSalesForecastId() != null ){
			returnVal.put("salesForecastId",salesforecast.getSalesForecastId());
}

		if(salesforecast.getParentSalesForecastId() != null ){
			returnVal.put("parentSalesForecastId",salesforecast.getParentSalesForecastId());
}

		if(salesforecast.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",salesforecast.getOrganizationPartyId());
}

		if(salesforecast.getInternalPartyId() != null ){
			returnVal.put("internalPartyId",salesforecast.getInternalPartyId());
}

		if(salesforecast.getCustomTimePeriodId() != null ){
			returnVal.put("customTimePeriodId",salesforecast.getCustomTimePeriodId());
}

		if(salesforecast.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",salesforecast.getCurrencyUomId());
}

		if(salesforecast.getQuotaAmount() != null ){
			returnVal.put("quotaAmount",salesforecast.getQuotaAmount());
}

		if(salesforecast.getForecastAmount() != null ){
			returnVal.put("forecastAmount",salesforecast.getForecastAmount());
}

		if(salesforecast.getBestCaseAmount() != null ){
			returnVal.put("bestCaseAmount",salesforecast.getBestCaseAmount());
}

		if(salesforecast.getClosedAmount() != null ){
			returnVal.put("closedAmount",salesforecast.getClosedAmount());
}

		if(salesforecast.getPercentOfQuotaForecast() != null ){
			returnVal.put("percentOfQuotaForecast",salesforecast.getPercentOfQuotaForecast());
}

		if(salesforecast.getPercentOfQuotaClosed() != null ){
			returnVal.put("percentOfQuotaClosed",salesforecast.getPercentOfQuotaClosed());
}

		if(salesforecast.getPipelineAmount() != null ){
			returnVal.put("pipelineAmount",salesforecast.getPipelineAmount());
}

		if(salesforecast.getCreatedByUserLoginId() != null ){
			returnVal.put("createdByUserLoginId",salesforecast.getCreatedByUserLoginId());
}

		if(salesforecast.getModifiedByUserLoginId() != null ){
			returnVal.put("modifiedByUserLoginId",salesforecast.getModifiedByUserLoginId());
}

		return returnVal;
}


	public static SalesForecast map(Map<String, Object> fields) {

		SalesForecast returnVal = new SalesForecast();

		if(fields.get("salesForecastId") != null) {
			returnVal.setSalesForecastId((String) fields.get("salesForecastId"));
}

		if(fields.get("parentSalesForecastId") != null) {
			returnVal.setParentSalesForecastId((String) fields.get("parentSalesForecastId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("internalPartyId") != null) {
			returnVal.setInternalPartyId((String) fields.get("internalPartyId"));
}

		if(fields.get("customTimePeriodId") != null) {
			returnVal.setCustomTimePeriodId((String) fields.get("customTimePeriodId"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("quotaAmount") != null) {
			returnVal.setQuotaAmount((BigDecimal) fields.get("quotaAmount"));
}

		if(fields.get("forecastAmount") != null) {
			returnVal.setForecastAmount((BigDecimal) fields.get("forecastAmount"));
}

		if(fields.get("bestCaseAmount") != null) {
			returnVal.setBestCaseAmount((BigDecimal) fields.get("bestCaseAmount"));
}

		if(fields.get("closedAmount") != null) {
			returnVal.setClosedAmount((BigDecimal) fields.get("closedAmount"));
}

		if(fields.get("percentOfQuotaForecast") != null) {
			returnVal.setPercentOfQuotaForecast((BigDecimal) fields.get("percentOfQuotaForecast"));
}

		if(fields.get("percentOfQuotaClosed") != null) {
			returnVal.setPercentOfQuotaClosed((BigDecimal) fields.get("percentOfQuotaClosed"));
}

		if(fields.get("pipelineAmount") != null) {
			returnVal.setPipelineAmount((BigDecimal) fields.get("pipelineAmount"));
}

		if(fields.get("createdByUserLoginId") != null) {
			returnVal.setCreatedByUserLoginId((String) fields.get("createdByUserLoginId"));
}

		if(fields.get("modifiedByUserLoginId") != null) {
			returnVal.setModifiedByUserLoginId((String) fields.get("modifiedByUserLoginId"));
}


		return returnVal;
 } 
	public static SalesForecast mapstrstr(Map<String, String> fields) throws Exception {

		SalesForecast returnVal = new SalesForecast();

		if(fields.get("salesForecastId") != null) {
			returnVal.setSalesForecastId((String) fields.get("salesForecastId"));
}

		if(fields.get("parentSalesForecastId") != null) {
			returnVal.setParentSalesForecastId((String) fields.get("parentSalesForecastId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("internalPartyId") != null) {
			returnVal.setInternalPartyId((String) fields.get("internalPartyId"));
}

		if(fields.get("customTimePeriodId") != null) {
			returnVal.setCustomTimePeriodId((String) fields.get("customTimePeriodId"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("quotaAmount") != null) {
String buf;
buf = fields.get("quotaAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuotaAmount(bd);
}

		if(fields.get("forecastAmount") != null) {
String buf;
buf = fields.get("forecastAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setForecastAmount(bd);
}

		if(fields.get("bestCaseAmount") != null) {
String buf;
buf = fields.get("bestCaseAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBestCaseAmount(bd);
}

		if(fields.get("closedAmount") != null) {
String buf;
buf = fields.get("closedAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setClosedAmount(bd);
}

		if(fields.get("percentOfQuotaForecast") != null) {
String buf;
buf = fields.get("percentOfQuotaForecast");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPercentOfQuotaForecast(bd);
}

		if(fields.get("percentOfQuotaClosed") != null) {
String buf;
buf = fields.get("percentOfQuotaClosed");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPercentOfQuotaClosed(bd);
}

		if(fields.get("pipelineAmount") != null) {
String buf;
buf = fields.get("pipelineAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPipelineAmount(bd);
}

		if(fields.get("createdByUserLoginId") != null) {
			returnVal.setCreatedByUserLoginId((String) fields.get("createdByUserLoginId"));
}

		if(fields.get("modifiedByUserLoginId") != null) {
			returnVal.setModifiedByUserLoginId((String) fields.get("modifiedByUserLoginId"));
}


		return returnVal;
 } 
	public static SalesForecast map(GenericValue val) {

SalesForecast returnVal = new SalesForecast();
		returnVal.setSalesForecastId(val.getString("salesForecastId"));
		returnVal.setParentSalesForecastId(val.getString("parentSalesForecastId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setInternalPartyId(val.getString("internalPartyId"));
		returnVal.setCustomTimePeriodId(val.getString("customTimePeriodId"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setQuotaAmount(val.getBigDecimal("quotaAmount"));
		returnVal.setForecastAmount(val.getBigDecimal("forecastAmount"));
		returnVal.setBestCaseAmount(val.getBigDecimal("bestCaseAmount"));
		returnVal.setClosedAmount(val.getBigDecimal("closedAmount"));
		returnVal.setPercentOfQuotaForecast(val.getBigDecimal("percentOfQuotaForecast"));
		returnVal.setPercentOfQuotaClosed(val.getBigDecimal("percentOfQuotaClosed"));
		returnVal.setPipelineAmount(val.getBigDecimal("pipelineAmount"));
		returnVal.setCreatedByUserLoginId(val.getString("createdByUserLoginId"));
		returnVal.setModifiedByUserLoginId(val.getString("modifiedByUserLoginId"));


return returnVal;

}

public static SalesForecast map(HttpServletRequest request) throws Exception {

		SalesForecast returnVal = new SalesForecast();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("salesForecastId")) {
returnVal.setSalesForecastId(request.getParameter("salesForecastId"));
}

		if(paramMap.containsKey("parentSalesForecastId"))  {
returnVal.setParentSalesForecastId(request.getParameter("parentSalesForecastId"));
}
		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}
		if(paramMap.containsKey("internalPartyId"))  {
returnVal.setInternalPartyId(request.getParameter("internalPartyId"));
}
		if(paramMap.containsKey("customTimePeriodId"))  {
returnVal.setCustomTimePeriodId(request.getParameter("customTimePeriodId"));
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("quotaAmount"))  {
String buf = request.getParameter("quotaAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuotaAmount(bd);
}
		if(paramMap.containsKey("forecastAmount"))  {
String buf = request.getParameter("forecastAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setForecastAmount(bd);
}
		if(paramMap.containsKey("bestCaseAmount"))  {
String buf = request.getParameter("bestCaseAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBestCaseAmount(bd);
}
		if(paramMap.containsKey("closedAmount"))  {
String buf = request.getParameter("closedAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setClosedAmount(bd);
}
		if(paramMap.containsKey("percentOfQuotaForecast"))  {
String buf = request.getParameter("percentOfQuotaForecast");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPercentOfQuotaForecast(bd);
}
		if(paramMap.containsKey("percentOfQuotaClosed"))  {
String buf = request.getParameter("percentOfQuotaClosed");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPercentOfQuotaClosed(bd);
}
		if(paramMap.containsKey("pipelineAmount"))  {
String buf = request.getParameter("pipelineAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPipelineAmount(bd);
}
		if(paramMap.containsKey("createdByUserLoginId"))  {
returnVal.setCreatedByUserLoginId(request.getParameter("createdByUserLoginId"));
}
		if(paramMap.containsKey("modifiedByUserLoginId"))  {
returnVal.setModifiedByUserLoginId(request.getParameter("modifiedByUserLoginId"));
}
return returnVal;

}
}
