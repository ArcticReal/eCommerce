package com.skytala.eCommerce.domain.product.relations.product.mapper.featureAppl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.featureAppl.ProductFeatureAppl;

public class ProductFeatureApplMapper  {


	public static Map<String, Object> map(ProductFeatureAppl productfeatureappl) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfeatureappl.getProductId() != null ){
			returnVal.put("productId",productfeatureappl.getProductId());
}

		if(productfeatureappl.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",productfeatureappl.getProductFeatureId());
}

		if(productfeatureappl.getProductFeatureApplTypeId() != null ){
			returnVal.put("productFeatureApplTypeId",productfeatureappl.getProductFeatureApplTypeId());
}

		if(productfeatureappl.getFromDate() != null ){
			returnVal.put("fromDate",productfeatureappl.getFromDate());
}

		if(productfeatureappl.getThruDate() != null ){
			returnVal.put("thruDate",productfeatureappl.getThruDate());
}

		if(productfeatureappl.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productfeatureappl.getSequenceNum());
}

		if(productfeatureappl.getAmount() != null ){
			returnVal.put("amount",productfeatureappl.getAmount());
}

		if(productfeatureappl.getRecurringAmount() != null ){
			returnVal.put("recurringAmount",productfeatureappl.getRecurringAmount());
}

		return returnVal;
}


	public static ProductFeatureAppl map(Map<String, Object> fields) {

		ProductFeatureAppl returnVal = new ProductFeatureAppl();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("productFeatureApplTypeId") != null) {
			returnVal.setProductFeatureApplTypeId((String) fields.get("productFeatureApplTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}

		if(fields.get("recurringAmount") != null) {
			returnVal.setRecurringAmount((BigDecimal) fields.get("recurringAmount"));
}


		return returnVal;
 } 
	public static ProductFeatureAppl mapstrstr(Map<String, String> fields) throws Exception {

		ProductFeatureAppl returnVal = new ProductFeatureAppl();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("productFeatureApplTypeId") != null) {
			returnVal.setProductFeatureApplTypeId((String) fields.get("productFeatureApplTypeId"));
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

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}

		if(fields.get("recurringAmount") != null) {
String buf;
buf = fields.get("recurringAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setRecurringAmount(bd);
}


		return returnVal;
 } 
	public static ProductFeatureAppl map(GenericValue val) {

ProductFeatureAppl returnVal = new ProductFeatureAppl();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductFeatureId(val.getString("productFeatureId"));
		returnVal.setProductFeatureApplTypeId(val.getString("productFeatureApplTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setRecurringAmount(val.getBigDecimal("recurringAmount"));


return returnVal;

}

public static ProductFeatureAppl map(HttpServletRequest request) throws Exception {

		ProductFeatureAppl returnVal = new ProductFeatureAppl();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("productFeatureId"))  {
returnVal.setProductFeatureId(request.getParameter("productFeatureId"));
}
		if(paramMap.containsKey("productFeatureApplTypeId"))  {
returnVal.setProductFeatureApplTypeId(request.getParameter("productFeatureApplTypeId"));
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
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
		if(paramMap.containsKey("recurringAmount"))  {
String buf = request.getParameter("recurringAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setRecurringAmount(bd);
}
return returnVal;

}
}
