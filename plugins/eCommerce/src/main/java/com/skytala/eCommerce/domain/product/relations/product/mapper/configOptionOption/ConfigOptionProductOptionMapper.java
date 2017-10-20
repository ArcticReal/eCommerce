package com.skytala.eCommerce.domain.product.relations.product.mapper.configOptionOption;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.configOptionOption.ConfigOptionProductOption;

public class ConfigOptionProductOptionMapper  {


	public static Map<String, Object> map(ConfigOptionProductOption configoptionproductoption) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(configoptionproductoption.getConfigId() != null ){
			returnVal.put("configId",configoptionproductoption.getConfigId());
}

		if(configoptionproductoption.getConfigItemId() != null ){
			returnVal.put("configItemId",configoptionproductoption.getConfigItemId());
}

		if(configoptionproductoption.getSequenceNum() != null ){
			returnVal.put("sequenceNum",configoptionproductoption.getSequenceNum());
}

		if(configoptionproductoption.getConfigOptionId() != null ){
			returnVal.put("configOptionId",configoptionproductoption.getConfigOptionId());
}

		if(configoptionproductoption.getProductId() != null ){
			returnVal.put("productId",configoptionproductoption.getProductId());
}

		if(configoptionproductoption.getProductOptionId() != null ){
			returnVal.put("productOptionId",configoptionproductoption.getProductOptionId());
}

		if(configoptionproductoption.getDescription() != null ){
			returnVal.put("description",configoptionproductoption.getDescription());
}

		return returnVal;
}


	public static ConfigOptionProductOption map(Map<String, Object> fields) {

		ConfigOptionProductOption returnVal = new ConfigOptionProductOption();

		if(fields.get("configId") != null) {
			returnVal.setConfigId((String) fields.get("configId"));
}

		if(fields.get("configItemId") != null) {
			returnVal.setConfigItemId((String) fields.get("configItemId"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}

		if(fields.get("configOptionId") != null) {
			returnVal.setConfigOptionId((String) fields.get("configOptionId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productOptionId") != null) {
			returnVal.setProductOptionId((String) fields.get("productOptionId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ConfigOptionProductOption mapstrstr(Map<String, String> fields) throws Exception {

		ConfigOptionProductOption returnVal = new ConfigOptionProductOption();

		if(fields.get("configId") != null) {
			returnVal.setConfigId((String) fields.get("configId"));
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

		if(fields.get("configOptionId") != null) {
			returnVal.setConfigOptionId((String) fields.get("configOptionId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productOptionId") != null) {
			returnVal.setProductOptionId((String) fields.get("productOptionId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ConfigOptionProductOption map(GenericValue val) {

ConfigOptionProductOption returnVal = new ConfigOptionProductOption();
		returnVal.setConfigId(val.getString("configId"));
		returnVal.setConfigItemId(val.getString("configItemId"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setConfigOptionId(val.getString("configOptionId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductOptionId(val.getString("productOptionId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ConfigOptionProductOption map(HttpServletRequest request) throws Exception {

		ConfigOptionProductOption returnVal = new ConfigOptionProductOption();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("configId")) {
returnVal.setConfigId(request.getParameter("configId"));
}

		if(paramMap.containsKey("configItemId"))  {
returnVal.setConfigItemId(request.getParameter("configItemId"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
		if(paramMap.containsKey("configOptionId"))  {
returnVal.setConfigOptionId(request.getParameter("configOptionId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("productOptionId"))  {
returnVal.setProductOptionId(request.getParameter("productOptionId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
