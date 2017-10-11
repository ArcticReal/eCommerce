package com.skytala.eCommerce.domain.product.relations.productConfig.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productConfig.model.ProductConfig;

public class ProductConfigMapper  {


	public static Map<String, Object> map(ProductConfig productconfig) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productconfig.getProductId() != null ){
			returnVal.put("productId",productconfig.getProductId());
}

		if(productconfig.getConfigItemId() != null ){
			returnVal.put("configItemId",productconfig.getConfigItemId());
}

		if(productconfig.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productconfig.getSequenceNum());
}

		if(productconfig.getFromDate() != null ){
			returnVal.put("fromDate",productconfig.getFromDate());
}

		if(productconfig.getDescription() != null ){
			returnVal.put("description",productconfig.getDescription());
}

		if(productconfig.getLongDescription() != null ){
			returnVal.put("longDescription",productconfig.getLongDescription());
}

		if(productconfig.getConfigTypeId() != null ){
			returnVal.put("configTypeId",productconfig.getConfigTypeId());
}

		if(productconfig.getDefaultConfigOptionId() != null ){
			returnVal.put("defaultConfigOptionId",productconfig.getDefaultConfigOptionId());
}

		if(productconfig.getThruDate() != null ){
			returnVal.put("thruDate",productconfig.getThruDate());
}

		if(productconfig.getIsMandatory() != null ){
			returnVal.put("isMandatory",productconfig.getIsMandatory());
}

		return returnVal;
}


	public static ProductConfig map(Map<String, Object> fields) {

		ProductConfig returnVal = new ProductConfig();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("configItemId") != null) {
			returnVal.setConfigItemId((String) fields.get("configItemId"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("longDescription") != null) {
			returnVal.setLongDescription((String) fields.get("longDescription"));
}

		if(fields.get("configTypeId") != null) {
			returnVal.setConfigTypeId((String) fields.get("configTypeId"));
}

		if(fields.get("defaultConfigOptionId") != null) {
			returnVal.setDefaultConfigOptionId((String) fields.get("defaultConfigOptionId"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("isMandatory") != null) {
			returnVal.setIsMandatory((boolean) fields.get("isMandatory"));
}


		return returnVal;
 } 
	public static ProductConfig mapstrstr(Map<String, String> fields) throws Exception {

		ProductConfig returnVal = new ProductConfig();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("configItemId") != null) {
			returnVal.setConfigItemId((String) fields.get("configItemId"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("longDescription") != null) {
			returnVal.setLongDescription((String) fields.get("longDescription"));
}

		if(fields.get("configTypeId") != null) {
			returnVal.setConfigTypeId((String) fields.get("configTypeId"));
}

		if(fields.get("defaultConfigOptionId") != null) {
			returnVal.setDefaultConfigOptionId((String) fields.get("defaultConfigOptionId"));
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("isMandatory") != null) {
String buf;
buf = fields.get("isMandatory");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsMandatory(ibuf);
}


		return returnVal;
 } 
	public static ProductConfig map(GenericValue val) {

ProductConfig returnVal = new ProductConfig();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setConfigItemId(val.getString("configItemId"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setLongDescription(val.getString("longDescription"));
		returnVal.setConfigTypeId(val.getString("configTypeId"));
		returnVal.setDefaultConfigOptionId(val.getString("defaultConfigOptionId"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setIsMandatory(val.getBoolean("isMandatory"));


return returnVal;

}

public static ProductConfig map(HttpServletRequest request) throws Exception {

		ProductConfig returnVal = new ProductConfig();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("configItemId"))  {
returnVal.setConfigItemId(request.getParameter("configItemId"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("longDescription"))  {
returnVal.setLongDescription(request.getParameter("longDescription"));
}
		if(paramMap.containsKey("configTypeId"))  {
returnVal.setConfigTypeId(request.getParameter("configTypeId"));
}
		if(paramMap.containsKey("defaultConfigOptionId"))  {
returnVal.setDefaultConfigOptionId(request.getParameter("defaultConfigOptionId"));
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("isMandatory"))  {
String buf = request.getParameter("isMandatory");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsMandatory(ibuf);
}
return returnVal;

}
}
