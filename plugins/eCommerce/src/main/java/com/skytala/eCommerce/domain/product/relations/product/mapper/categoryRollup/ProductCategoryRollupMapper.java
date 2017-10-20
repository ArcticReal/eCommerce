package com.skytala.eCommerce.domain.product.relations.product.mapper.categoryRollup;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryRollup.ProductCategoryRollup;

public class ProductCategoryRollupMapper  {


	public static Map<String, Object> map(ProductCategoryRollup productcategoryrollup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productcategoryrollup.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",productcategoryrollup.getProductCategoryId());
}

		if(productcategoryrollup.getParentProductCategoryId() != null ){
			returnVal.put("parentProductCategoryId",productcategoryrollup.getParentProductCategoryId());
}

		if(productcategoryrollup.getFromDate() != null ){
			returnVal.put("fromDate",productcategoryrollup.getFromDate());
}

		if(productcategoryrollup.getThruDate() != null ){
			returnVal.put("thruDate",productcategoryrollup.getThruDate());
}

		if(productcategoryrollup.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productcategoryrollup.getSequenceNum());
}

		return returnVal;
}


	public static ProductCategoryRollup map(Map<String, Object> fields) {

		ProductCategoryRollup returnVal = new ProductCategoryRollup();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("parentProductCategoryId") != null) {
			returnVal.setParentProductCategoryId((String) fields.get("parentProductCategoryId"));
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


		return returnVal;
 } 
	public static ProductCategoryRollup mapstrstr(Map<String, String> fields) throws Exception {

		ProductCategoryRollup returnVal = new ProductCategoryRollup();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("parentProductCategoryId") != null) {
			returnVal.setParentProductCategoryId((String) fields.get("parentProductCategoryId"));
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


		return returnVal;
 } 
	public static ProductCategoryRollup map(GenericValue val) {

ProductCategoryRollup returnVal = new ProductCategoryRollup();
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setParentProductCategoryId(val.getString("parentProductCategoryId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ProductCategoryRollup map(HttpServletRequest request) throws Exception {

		ProductCategoryRollup returnVal = new ProductCategoryRollup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productCategoryId")) {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}

		if(paramMap.containsKey("parentProductCategoryId"))  {
returnVal.setParentProductCategoryId(request.getParameter("parentProductCategoryId"));
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
return returnVal;

}
}
