package com.skytala.eCommerce.domain.product.relations.product.mapper.configItem;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.configItem.ProductConfigItem;

public class ProductConfigItemMapper  {


	public static Map<String, Object> map(ProductConfigItem productconfigitem) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productconfigitem.getConfigItemId() != null ){
			returnVal.put("configItemId",productconfigitem.getConfigItemId());
}

		if(productconfigitem.getConfigItemTypeId() != null ){
			returnVal.put("configItemTypeId",productconfigitem.getConfigItemTypeId());
}

		if(productconfigitem.getConfigItemName() != null ){
			returnVal.put("configItemName",productconfigitem.getConfigItemName());
}

		if(productconfigitem.getDescription() != null ){
			returnVal.put("description",productconfigitem.getDescription());
}

		if(productconfigitem.getLongDescription() != null ){
			returnVal.put("longDescription",productconfigitem.getLongDescription());
}

		if(productconfigitem.getImageUrl() != null ){
			returnVal.put("imageUrl",productconfigitem.getImageUrl());
}

		return returnVal;
}


	public static ProductConfigItem map(Map<String, Object> fields) {

		ProductConfigItem returnVal = new ProductConfigItem();

		if(fields.get("configItemId") != null) {
			returnVal.setConfigItemId((String) fields.get("configItemId"));
}

		if(fields.get("configItemTypeId") != null) {
			returnVal.setConfigItemTypeId((String) fields.get("configItemTypeId"));
}

		if(fields.get("configItemName") != null) {
			returnVal.setConfigItemName((String) fields.get("configItemName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("longDescription") != null) {
			returnVal.setLongDescription((String) fields.get("longDescription"));
}

		if(fields.get("imageUrl") != null) {
			returnVal.setImageUrl((String) fields.get("imageUrl"));
}


		return returnVal;
 } 
	public static ProductConfigItem mapstrstr(Map<String, String> fields) throws Exception {

		ProductConfigItem returnVal = new ProductConfigItem();

		if(fields.get("configItemId") != null) {
			returnVal.setConfigItemId((String) fields.get("configItemId"));
}

		if(fields.get("configItemTypeId") != null) {
			returnVal.setConfigItemTypeId((String) fields.get("configItemTypeId"));
}

		if(fields.get("configItemName") != null) {
			returnVal.setConfigItemName((String) fields.get("configItemName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("longDescription") != null) {
			returnVal.setLongDescription((String) fields.get("longDescription"));
}

		if(fields.get("imageUrl") != null) {
			returnVal.setImageUrl((String) fields.get("imageUrl"));
}


		return returnVal;
 } 
	public static ProductConfigItem map(GenericValue val) {

ProductConfigItem returnVal = new ProductConfigItem();
		returnVal.setConfigItemId(val.getString("configItemId"));
		returnVal.setConfigItemTypeId(val.getString("configItemTypeId"));
		returnVal.setConfigItemName(val.getString("configItemName"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setLongDescription(val.getString("longDescription"));
		returnVal.setImageUrl(val.getString("imageUrl"));


return returnVal;

}

public static ProductConfigItem map(HttpServletRequest request) throws Exception {

		ProductConfigItem returnVal = new ProductConfigItem();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("configItemId")) {
returnVal.setConfigItemId(request.getParameter("configItemId"));
}

		if(paramMap.containsKey("configItemTypeId"))  {
returnVal.setConfigItemTypeId(request.getParameter("configItemTypeId"));
}
		if(paramMap.containsKey("configItemName"))  {
returnVal.setConfigItemName(request.getParameter("configItemName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("longDescription"))  {
returnVal.setLongDescription(request.getParameter("longDescription"));
}
		if(paramMap.containsKey("imageUrl"))  {
returnVal.setImageUrl(request.getParameter("imageUrl"));
}
return returnVal;

}
}
