package com.skytala.eCommerce.domain.product.relations.productConfigOptionIactn.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productConfigOptionIactn.model.ProductConfigOptionIactn;

public class ProductConfigOptionIactnMapper  {


	public static Map<String, Object> map(ProductConfigOptionIactn productconfigoptioniactn) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productconfigoptioniactn.getConfigItemId() != null ){
			returnVal.put("configItemId",productconfigoptioniactn.getConfigItemId());
}

		if(productconfigoptioniactn.getConfigOptionId() != null ){
			returnVal.put("configOptionId",productconfigoptioniactn.getConfigOptionId());
}

		if(productconfigoptioniactn.getConfigItemIdTo() != null ){
			returnVal.put("configItemIdTo",productconfigoptioniactn.getConfigItemIdTo());
}

		if(productconfigoptioniactn.getConfigOptionIdTo() != null ){
			returnVal.put("configOptionIdTo",productconfigoptioniactn.getConfigOptionIdTo());
}

		if(productconfigoptioniactn.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productconfigoptioniactn.getSequenceNum());
}

		if(productconfigoptioniactn.getConfigIactnTypeId() != null ){
			returnVal.put("configIactnTypeId",productconfigoptioniactn.getConfigIactnTypeId());
}

		if(productconfigoptioniactn.getDescription() != null ){
			returnVal.put("description",productconfigoptioniactn.getDescription());
}

		return returnVal;
}


	public static ProductConfigOptionIactn map(Map<String, Object> fields) {

		ProductConfigOptionIactn returnVal = new ProductConfigOptionIactn();

		if(fields.get("configItemId") != null) {
			returnVal.setConfigItemId((String) fields.get("configItemId"));
}

		if(fields.get("configOptionId") != null) {
			returnVal.setConfigOptionId((String) fields.get("configOptionId"));
}

		if(fields.get("configItemIdTo") != null) {
			returnVal.setConfigItemIdTo((String) fields.get("configItemIdTo"));
}

		if(fields.get("configOptionIdTo") != null) {
			returnVal.setConfigOptionIdTo((String) fields.get("configOptionIdTo"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}

		if(fields.get("configIactnTypeId") != null) {
			returnVal.setConfigIactnTypeId((String) fields.get("configIactnTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductConfigOptionIactn mapstrstr(Map<String, String> fields) throws Exception {

		ProductConfigOptionIactn returnVal = new ProductConfigOptionIactn();

		if(fields.get("configItemId") != null) {
			returnVal.setConfigItemId((String) fields.get("configItemId"));
}

		if(fields.get("configOptionId") != null) {
			returnVal.setConfigOptionId((String) fields.get("configOptionId"));
}

		if(fields.get("configItemIdTo") != null) {
			returnVal.setConfigItemIdTo((String) fields.get("configItemIdTo"));
}

		if(fields.get("configOptionIdTo") != null) {
			returnVal.setConfigOptionIdTo((String) fields.get("configOptionIdTo"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}

		if(fields.get("configIactnTypeId") != null) {
			returnVal.setConfigIactnTypeId((String) fields.get("configIactnTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductConfigOptionIactn map(GenericValue val) {

ProductConfigOptionIactn returnVal = new ProductConfigOptionIactn();
		returnVal.setConfigItemId(val.getString("configItemId"));
		returnVal.setConfigOptionId(val.getString("configOptionId"));
		returnVal.setConfigItemIdTo(val.getString("configItemIdTo"));
		returnVal.setConfigOptionIdTo(val.getString("configOptionIdTo"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setConfigIactnTypeId(val.getString("configIactnTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductConfigOptionIactn map(HttpServletRequest request) throws Exception {

		ProductConfigOptionIactn returnVal = new ProductConfigOptionIactn();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("configItemId")) {
returnVal.setConfigItemId(request.getParameter("configItemId"));
}

		if(paramMap.containsKey("configOptionId"))  {
returnVal.setConfigOptionId(request.getParameter("configOptionId"));
}
		if(paramMap.containsKey("configItemIdTo"))  {
returnVal.setConfigItemIdTo(request.getParameter("configItemIdTo"));
}
		if(paramMap.containsKey("configOptionIdTo"))  {
returnVal.setConfigOptionIdTo(request.getParameter("configOptionIdTo"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
		if(paramMap.containsKey("configIactnTypeId"))  {
returnVal.setConfigIactnTypeId(request.getParameter("configIactnTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
