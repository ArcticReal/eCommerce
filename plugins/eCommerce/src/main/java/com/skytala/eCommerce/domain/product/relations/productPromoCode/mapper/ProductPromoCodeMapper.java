package com.skytala.eCommerce.domain.product.relations.productPromoCode.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPromoCode.model.ProductPromoCode;

public class ProductPromoCodeMapper  {


	public static Map<String, Object> map(ProductPromoCode productpromocode) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpromocode.getProductPromoCodeId() != null ){
			returnVal.put("productPromoCodeId",productpromocode.getProductPromoCodeId());
}

		if(productpromocode.getProductPromoId() != null ){
			returnVal.put("productPromoId",productpromocode.getProductPromoId());
}

		if(productpromocode.getUserEntered() != null ){
			returnVal.put("userEntered",productpromocode.getUserEntered());
}

		if(productpromocode.getRequireEmailOrParty() != null ){
			returnVal.put("requireEmailOrParty",productpromocode.getRequireEmailOrParty());
}

		if(productpromocode.getUseLimitPerCode() != null ){
			returnVal.put("useLimitPerCode",productpromocode.getUseLimitPerCode());
}

		if(productpromocode.getUseLimitPerCustomer() != null ){
			returnVal.put("useLimitPerCustomer",productpromocode.getUseLimitPerCustomer());
}

		if(productpromocode.getFromDate() != null ){
			returnVal.put("fromDate",productpromocode.getFromDate());
}

		if(productpromocode.getThruDate() != null ){
			returnVal.put("thruDate",productpromocode.getThruDate());
}

		if(productpromocode.getCreatedDate() != null ){
			returnVal.put("createdDate",productpromocode.getCreatedDate());
}

		if(productpromocode.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",productpromocode.getCreatedByUserLogin());
}

		if(productpromocode.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",productpromocode.getLastModifiedDate());
}

		if(productpromocode.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",productpromocode.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static ProductPromoCode map(Map<String, Object> fields) {

		ProductPromoCode returnVal = new ProductPromoCode();

		if(fields.get("productPromoCodeId") != null) {
			returnVal.setProductPromoCodeId((String) fields.get("productPromoCodeId"));
}

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("userEntered") != null) {
			returnVal.setUserEntered((boolean) fields.get("userEntered"));
}

		if(fields.get("requireEmailOrParty") != null) {
			returnVal.setRequireEmailOrParty((boolean) fields.get("requireEmailOrParty"));
}

		if(fields.get("useLimitPerCode") != null) {
			returnVal.setUseLimitPerCode((long) fields.get("useLimitPerCode"));
}

		if(fields.get("useLimitPerCustomer") != null) {
			returnVal.setUseLimitPerCustomer((long) fields.get("useLimitPerCustomer"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("createdDate") != null) {
			returnVal.setCreatedDate((Timestamp) fields.get("createdDate"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
			returnVal.setLastModifiedDate((Timestamp) fields.get("lastModifiedDate"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static ProductPromoCode mapstrstr(Map<String, String> fields) throws Exception {

		ProductPromoCode returnVal = new ProductPromoCode();

		if(fields.get("productPromoCodeId") != null) {
			returnVal.setProductPromoCodeId((String) fields.get("productPromoCodeId"));
}

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("userEntered") != null) {
String buf;
buf = fields.get("userEntered");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setUserEntered(ibuf);
}

		if(fields.get("requireEmailOrParty") != null) {
String buf;
buf = fields.get("requireEmailOrParty");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequireEmailOrParty(ibuf);
}

		if(fields.get("useLimitPerCode") != null) {
String buf;
buf = fields.get("useLimitPerCode");
long ibuf = Long.parseLong(buf);
			returnVal.setUseLimitPerCode(ibuf);
}

		if(fields.get("useLimitPerCustomer") != null) {
String buf;
buf = fields.get("useLimitPerCustomer");
long ibuf = Long.parseLong(buf);
			returnVal.setUseLimitPerCustomer(ibuf);
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

		if(fields.get("createdDate") != null) {
String buf = fields.get("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
String buf = fields.get("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static ProductPromoCode map(GenericValue val) {

ProductPromoCode returnVal = new ProductPromoCode();
		returnVal.setProductPromoCodeId(val.getString("productPromoCodeId"));
		returnVal.setProductPromoId(val.getString("productPromoId"));
		returnVal.setUserEntered(val.getBoolean("userEntered"));
		returnVal.setRequireEmailOrParty(val.getBoolean("requireEmailOrParty"));
		returnVal.setUseLimitPerCode(val.getLong("useLimitPerCode"));
		returnVal.setUseLimitPerCustomer(val.getLong("useLimitPerCustomer"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static ProductPromoCode map(HttpServletRequest request) throws Exception {

		ProductPromoCode returnVal = new ProductPromoCode();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPromoCodeId")) {
returnVal.setProductPromoCodeId(request.getParameter("productPromoCodeId"));
}

		if(paramMap.containsKey("productPromoId"))  {
returnVal.setProductPromoId(request.getParameter("productPromoId"));
}
		if(paramMap.containsKey("userEntered"))  {
String buf = request.getParameter("userEntered");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setUserEntered(ibuf);
}
		if(paramMap.containsKey("requireEmailOrParty"))  {
String buf = request.getParameter("requireEmailOrParty");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setRequireEmailOrParty(ibuf);
}
		if(paramMap.containsKey("useLimitPerCode"))  {
String buf = request.getParameter("useLimitPerCode");
Long ibuf = Long.parseLong(buf);
returnVal.setUseLimitPerCode(ibuf);
}
		if(paramMap.containsKey("useLimitPerCustomer"))  {
String buf = request.getParameter("useLimitPerCustomer");
Long ibuf = Long.parseLong(buf);
returnVal.setUseLimitPerCustomer(ibuf);
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
		if(paramMap.containsKey("createdDate"))  {
String buf = request.getParameter("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCreatedDate(ibuf);
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedDate"))  {
String buf = request.getParameter("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastModifiedDate(ibuf);
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
return returnVal;

}
}
