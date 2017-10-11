package com.skytala.eCommerce.domain.product.relations.productConfigConfig.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productConfigConfig.model.ProductConfigConfig;

public class ProductConfigConfigMapper  {


	public static Map<String, Object> map(ProductConfigConfig productconfigconfig) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productconfigconfig.getConfigId() != null ){
			returnVal.put("configId",productconfigconfig.getConfigId());
}

		if(productconfigconfig.getConfigItemId() != null ){
			returnVal.put("configItemId",productconfigconfig.getConfigItemId());
}

		if(productconfigconfig.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productconfigconfig.getSequenceNum());
}

		if(productconfigconfig.getConfigOptionId() != null ){
			returnVal.put("configOptionId",productconfigconfig.getConfigOptionId());
}

		if(productconfigconfig.getDescription() != null ){
			returnVal.put("description",productconfigconfig.getDescription());
}

		return returnVal;
}


	public static ProductConfigConfig map(Map<String, Object> fields) {

		ProductConfigConfig returnVal = new ProductConfigConfig();

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

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductConfigConfig mapstrstr(Map<String, String> fields) throws Exception {

		ProductConfigConfig returnVal = new ProductConfigConfig();

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

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductConfigConfig map(GenericValue val) {

ProductConfigConfig returnVal = new ProductConfigConfig();
		returnVal.setConfigId(val.getString("configId"));
		returnVal.setConfigItemId(val.getString("configItemId"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setConfigOptionId(val.getString("configOptionId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductConfigConfig map(HttpServletRequest request) throws Exception {

		ProductConfigConfig returnVal = new ProductConfigConfig();

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
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
