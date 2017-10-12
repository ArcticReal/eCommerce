package com.skytala.eCommerce.domain.accounting.relations.rateAmount.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.model.RateAmount;

public class RateAmountMapper  {


	public static Map<String, Object> map(RateAmount rateamount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(rateamount.getRateTypeId() != null ){
			returnVal.put("rateTypeId",rateamount.getRateTypeId());
}

		if(rateamount.getRateCurrencyUomId() != null ){
			returnVal.put("rateCurrencyUomId",rateamount.getRateCurrencyUomId());
}

		if(rateamount.getPeriodTypeId() != null ){
			returnVal.put("periodTypeId",rateamount.getPeriodTypeId());
}

		if(rateamount.getWorkEffortId() != null ){
			returnVal.put("workEffortId",rateamount.getWorkEffortId());
}

		if(rateamount.getPartyId() != null ){
			returnVal.put("partyId",rateamount.getPartyId());
}

		if(rateamount.getEmplPositionTypeId() != null ){
			returnVal.put("emplPositionTypeId",rateamount.getEmplPositionTypeId());
}

		if(rateamount.getFromDate() != null ){
			returnVal.put("fromDate",rateamount.getFromDate());
}

		if(rateamount.getThruDate() != null ){
			returnVal.put("thruDate",rateamount.getThruDate());
}

		if(rateamount.getRateAmount() != null ){
			returnVal.put("rateAmount",rateamount.getRateAmount());
}

		return returnVal;
}


	public static RateAmount map(Map<String, Object> fields) {

		RateAmount returnVal = new RateAmount();

		if(fields.get("rateTypeId") != null) {
			returnVal.setRateTypeId((String) fields.get("rateTypeId"));
}

		if(fields.get("rateCurrencyUomId") != null) {
			returnVal.setRateCurrencyUomId((String) fields.get("rateCurrencyUomId"));
}

		if(fields.get("periodTypeId") != null) {
			returnVal.setPeriodTypeId((String) fields.get("periodTypeId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("emplPositionTypeId") != null) {
			returnVal.setEmplPositionTypeId((String) fields.get("emplPositionTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("rateAmount") != null) {
			returnVal.setRateAmount((BigDecimal) fields.get("rateAmount"));
}


		return returnVal;
 } 
	public static RateAmount mapstrstr(Map<String, String> fields) throws Exception {

		RateAmount returnVal = new RateAmount();

		if(fields.get("rateTypeId") != null) {
			returnVal.setRateTypeId((String) fields.get("rateTypeId"));
}

		if(fields.get("rateCurrencyUomId") != null) {
			returnVal.setRateCurrencyUomId((String) fields.get("rateCurrencyUomId"));
}

		if(fields.get("periodTypeId") != null) {
			returnVal.setPeriodTypeId((String) fields.get("periodTypeId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("emplPositionTypeId") != null) {
			returnVal.setEmplPositionTypeId((String) fields.get("emplPositionTypeId"));
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

		if(fields.get("rateAmount") != null) {
String buf;
buf = fields.get("rateAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setRateAmount(bd);
}


		return returnVal;
 } 
	public static RateAmount map(GenericValue val) {

RateAmount returnVal = new RateAmount();
		returnVal.setRateTypeId(val.getString("rateTypeId"));
		returnVal.setRateCurrencyUomId(val.getString("rateCurrencyUomId"));
		returnVal.setPeriodTypeId(val.getString("periodTypeId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setEmplPositionTypeId(val.getString("emplPositionTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setRateAmount(val.getBigDecimal("rateAmount"));


return returnVal;

}

public static RateAmount map(HttpServletRequest request) throws Exception {

		RateAmount returnVal = new RateAmount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("rateTypeId")) {
returnVal.setRateTypeId(request.getParameter("rateTypeId"));
}

		if(paramMap.containsKey("rateCurrencyUomId"))  {
returnVal.setRateCurrencyUomId(request.getParameter("rateCurrencyUomId"));
}
		if(paramMap.containsKey("periodTypeId"))  {
returnVal.setPeriodTypeId(request.getParameter("periodTypeId"));
}
		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("emplPositionTypeId"))  {
returnVal.setEmplPositionTypeId(request.getParameter("emplPositionTypeId"));
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
		if(paramMap.containsKey("rateAmount"))  {
String buf = request.getParameter("rateAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setRateAmount(bd);
}
return returnVal;

}
}
