package com.skytala.eCommerce.domain.product.relations.product.mapper.configOption;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.configOption.ProductConfigOption;

public class ProductConfigOptionMapper  {


	public static Map<String, Object> map(ProductConfigOption productconfigoption) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productconfigoption.getConfigItemId() != null ){
			returnVal.put("configItemId",productconfigoption.getConfigItemId());
}

		if(productconfigoption.getConfigOptionId() != null ){
			returnVal.put("configOptionId",productconfigoption.getConfigOptionId());
}

		if(productconfigoption.getConfigOptionName() != null ){
			returnVal.put("configOptionName",productconfigoption.getConfigOptionName());
}

		if(productconfigoption.getDescription() != null ){
			returnVal.put("description",productconfigoption.getDescription());
}

		if(productconfigoption.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productconfigoption.getSequenceNum());
}

		return returnVal;
}


	public static ProductConfigOption map(Map<String, Object> fields) {

		ProductConfigOption returnVal = new ProductConfigOption();

		if(fields.get("configItemId") != null) {
			returnVal.setConfigItemId((String) fields.get("configItemId"));
}

		if(fields.get("configOptionId") != null) {
			returnVal.setConfigOptionId((String) fields.get("configOptionId"));
}

		if(fields.get("configOptionName") != null) {
			returnVal.setConfigOptionName((String) fields.get("configOptionName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static ProductConfigOption mapstrstr(Map<String, String> fields) throws Exception {

		ProductConfigOption returnVal = new ProductConfigOption();

		if(fields.get("configItemId") != null) {
			returnVal.setConfigItemId((String) fields.get("configItemId"));
}

		if(fields.get("configOptionId") != null) {
			returnVal.setConfigOptionId((String) fields.get("configOptionId"));
}

		if(fields.get("configOptionName") != null) {
			returnVal.setConfigOptionName((String) fields.get("configOptionName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static ProductConfigOption map(GenericValue val) {

ProductConfigOption returnVal = new ProductConfigOption();
		returnVal.setConfigItemId(val.getString("configItemId"));
		returnVal.setConfigOptionId(val.getString("configOptionId"));
		returnVal.setConfigOptionName(val.getString("configOptionName"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ProductConfigOption map(HttpServletRequest request) throws Exception {

		ProductConfigOption returnVal = new ProductConfigOption();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("configItemId")) {
returnVal.setConfigItemId(request.getParameter("configItemId"));
}

		if(paramMap.containsKey("configOptionId"))  {
returnVal.setConfigOptionId(request.getParameter("configOptionId"));
}
		if(paramMap.containsKey("configOptionName"))  {
returnVal.setConfigOptionName(request.getParameter("configOptionName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
