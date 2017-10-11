package com.skytala.eCommerce.domain.product.relations.marketInterest.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.marketInterest.model.MarketInterest;

public class MarketInterestMapper  {


	public static Map<String, Object> map(MarketInterest marketinterest) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(marketinterest.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",marketinterest.getProductCategoryId());
}

		if(marketinterest.getPartyClassificationGroupId() != null ){
			returnVal.put("partyClassificationGroupId",marketinterest.getPartyClassificationGroupId());
}

		if(marketinterest.getFromDate() != null ){
			returnVal.put("fromDate",marketinterest.getFromDate());
}

		if(marketinterest.getThruDate() != null ){
			returnVal.put("thruDate",marketinterest.getThruDate());
}

		return returnVal;
}


	public static MarketInterest map(Map<String, Object> fields) {

		MarketInterest returnVal = new MarketInterest();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("partyClassificationGroupId") != null) {
			returnVal.setPartyClassificationGroupId((String) fields.get("partyClassificationGroupId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static MarketInterest mapstrstr(Map<String, String> fields) throws Exception {

		MarketInterest returnVal = new MarketInterest();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("partyClassificationGroupId") != null) {
			returnVal.setPartyClassificationGroupId((String) fields.get("partyClassificationGroupId"));
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


		return returnVal;
 } 
	public static MarketInterest map(GenericValue val) {

MarketInterest returnVal = new MarketInterest();
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setPartyClassificationGroupId(val.getString("partyClassificationGroupId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static MarketInterest map(HttpServletRequest request) throws Exception {

		MarketInterest returnVal = new MarketInterest();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productCategoryId")) {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}

		if(paramMap.containsKey("partyClassificationGroupId"))  {
returnVal.setPartyClassificationGroupId(request.getParameter("partyClassificationGroupId"));
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
return returnVal;

}
}
