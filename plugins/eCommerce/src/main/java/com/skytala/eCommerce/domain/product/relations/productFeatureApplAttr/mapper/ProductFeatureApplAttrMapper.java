package com.skytala.eCommerce.domain.product.relations.productFeatureApplAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFeatureApplAttr.model.ProductFeatureApplAttr;

public class ProductFeatureApplAttrMapper  {


	public static Map<String, Object> map(ProductFeatureApplAttr productfeatureapplattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfeatureapplattr.getProductId() != null ){
			returnVal.put("productId",productfeatureapplattr.getProductId());
}

		if(productfeatureapplattr.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",productfeatureapplattr.getProductFeatureId());
}

		if(productfeatureapplattr.getFromDate() != null ){
			returnVal.put("fromDate",productfeatureapplattr.getFromDate());
}

		if(productfeatureapplattr.getAttrName() != null ){
			returnVal.put("attrName",productfeatureapplattr.getAttrName());
}

		if(productfeatureapplattr.getAttrValue() != null ){
			returnVal.put("attrValue",productfeatureapplattr.getAttrValue());
}

		return returnVal;
}


	public static ProductFeatureApplAttr map(Map<String, Object> fields) {

		ProductFeatureApplAttr returnVal = new ProductFeatureApplAttr();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}


		return returnVal;
 } 
	public static ProductFeatureApplAttr mapstrstr(Map<String, String> fields) throws Exception {

		ProductFeatureApplAttr returnVal = new ProductFeatureApplAttr();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
String buf;
buf = fields.get("attrValue");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrValue(ibuf);
}


		return returnVal;
 } 
	public static ProductFeatureApplAttr map(GenericValue val) {

ProductFeatureApplAttr returnVal = new ProductFeatureApplAttr();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductFeatureId(val.getString("productFeatureId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));


return returnVal;

}

public static ProductFeatureApplAttr map(HttpServletRequest request) throws Exception {

		ProductFeatureApplAttr returnVal = new ProductFeatureApplAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("productFeatureId"))  {
returnVal.setProductFeatureId(request.getParameter("productFeatureId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
return returnVal;

}
}
