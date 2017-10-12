package com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.model.SalesForecastHistory;

public class SalesForecastHistoryMapper  {


	public static Map<String, Object> map(SalesForecastHistory salesforecasthistory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(salesforecasthistory.getSalesForecastHistoryId() != null ){
			returnVal.put("salesForecastHistoryId",salesforecasthistory.getSalesForecastHistoryId());
}

		if(salesforecasthistory.getSalesForecastId() != null ){
			returnVal.put("salesForecastId",salesforecasthistory.getSalesForecastId());
}

		if(salesforecasthistory.getParentSalesForecastId() != null ){
			returnVal.put("parentSalesForecastId",salesforecasthistory.getParentSalesForecastId());
}

		if(salesforecasthistory.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",salesforecasthistory.getOrganizationPartyId());
}

		if(salesforecasthistory.getInternalPartyId() != null ){
			returnVal.put("internalPartyId",salesforecasthistory.getInternalPartyId());
}

		if(salesforecasthistory.getCustomTimePeriodId() != null ){
			returnVal.put("customTimePeriodId",salesforecasthistory.getCustomTimePeriodId());
}

		if(salesforecasthistory.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",salesforecasthistory.getCurrencyUomId());
}

		if(salesforecasthistory.getQuotaAmount() != null ){
			returnVal.put("quotaAmount",salesforecasthistory.getQuotaAmount());
}

		if(salesforecasthistory.getForecastAmount() != null ){
			returnVal.put("forecastAmount",salesforecasthistory.getForecastAmount());
}

		if(salesforecasthistory.getBestCaseAmount() != null ){
			returnVal.put("bestCaseAmount",salesforecasthistory.getBestCaseAmount());
}

		if(salesforecasthistory.getClosedAmount() != null ){
			returnVal.put("closedAmount",salesforecasthistory.getClosedAmount());
}

		if(salesforecasthistory.getPercentOfQuotaForecast() != null ){
			returnVal.put("percentOfQuotaForecast",salesforecasthistory.getPercentOfQuotaForecast());
}

		if(salesforecasthistory.getPercentOfQuotaClosed() != null ){
			returnVal.put("percentOfQuotaClosed",salesforecasthistory.getPercentOfQuotaClosed());
}

		if(salesforecasthistory.getChangeNote() != null ){
			returnVal.put("changeNote",salesforecasthistory.getChangeNote());
}

		if(salesforecasthistory.getModifiedByUserLoginId() != null ){
			returnVal.put("modifiedByUserLoginId",salesforecasthistory.getModifiedByUserLoginId());
}

		if(salesforecasthistory.getModifiedTimestamp() != null ){
			returnVal.put("modifiedTimestamp",salesforecasthistory.getModifiedTimestamp());
}

		return returnVal;
}


	public static SalesForecastHistory map(Map<String, Object> fields) {

		SalesForecastHistory returnVal = new SalesForecastHistory();

		if(fields.get("salesForecastHistoryId") != null) {
			returnVal.setSalesForecastHistoryId((String) fields.get("salesForecastHistoryId"));
}

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

		if(fields.get("changeNote") != null) {
			returnVal.setChangeNote((String) fields.get("changeNote"));
}

		if(fields.get("modifiedByUserLoginId") != null) {
			returnVal.setModifiedByUserLoginId((String) fields.get("modifiedByUserLoginId"));
}

		if(fields.get("modifiedTimestamp") != null) {
			returnVal.setModifiedTimestamp((Timestamp) fields.get("modifiedTimestamp"));
}


		return returnVal;
 } 
	public static SalesForecastHistory mapstrstr(Map<String, String> fields) throws Exception {

		SalesForecastHistory returnVal = new SalesForecastHistory();

		if(fields.get("salesForecastHistoryId") != null) {
			returnVal.setSalesForecastHistoryId((String) fields.get("salesForecastHistoryId"));
}

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

		if(fields.get("changeNote") != null) {
			returnVal.setChangeNote((String) fields.get("changeNote"));
}

		if(fields.get("modifiedByUserLoginId") != null) {
			returnVal.setModifiedByUserLoginId((String) fields.get("modifiedByUserLoginId"));
}

		if(fields.get("modifiedTimestamp") != null) {
String buf = fields.get("modifiedTimestamp");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setModifiedTimestamp(ibuf);
}


		return returnVal;
 } 
	public static SalesForecastHistory map(GenericValue val) {

SalesForecastHistory returnVal = new SalesForecastHistory();
		returnVal.setSalesForecastHistoryId(val.getString("salesForecastHistoryId"));
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
		returnVal.setChangeNote(val.getString("changeNote"));
		returnVal.setModifiedByUserLoginId(val.getString("modifiedByUserLoginId"));
		returnVal.setModifiedTimestamp(val.getTimestamp("modifiedTimestamp"));


return returnVal;

}

public static SalesForecastHistory map(HttpServletRequest request) throws Exception {

		SalesForecastHistory returnVal = new SalesForecastHistory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("salesForecastHistoryId")) {
returnVal.setSalesForecastHistoryId(request.getParameter("salesForecastHistoryId"));
}

		if(paramMap.containsKey("salesForecastId"))  {
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
		if(paramMap.containsKey("changeNote"))  {
returnVal.setChangeNote(request.getParameter("changeNote"));
}
		if(paramMap.containsKey("modifiedByUserLoginId"))  {
returnVal.setModifiedByUserLoginId(request.getParameter("modifiedByUserLoginId"));
}
		if(paramMap.containsKey("modifiedTimestamp"))  {
String buf = request.getParameter("modifiedTimestamp");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setModifiedTimestamp(ibuf);
}
return returnVal;

}
}
