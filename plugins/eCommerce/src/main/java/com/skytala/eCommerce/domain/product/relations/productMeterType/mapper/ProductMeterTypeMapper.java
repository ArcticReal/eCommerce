package com.skytala.eCommerce.domain.product.relations.productMeterType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productMeterType.model.ProductMeterType;

public class ProductMeterTypeMapper  {


	public static Map<String, Object> map(ProductMeterType productmetertype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productmetertype.getProductMeterTypeId() != null ){
			returnVal.put("productMeterTypeId",productmetertype.getProductMeterTypeId());
}

		if(productmetertype.getDescription() != null ){
			returnVal.put("description",productmetertype.getDescription());
}

		if(productmetertype.getDefaultUomId() != null ){
			returnVal.put("defaultUomId",productmetertype.getDefaultUomId());
}

		return returnVal;
}


	public static ProductMeterType map(Map<String, Object> fields) {

		ProductMeterType returnVal = new ProductMeterType();

		if(fields.get("productMeterTypeId") != null) {
			returnVal.setProductMeterTypeId((String) fields.get("productMeterTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("defaultUomId") != null) {
			returnVal.setDefaultUomId((String) fields.get("defaultUomId"));
}


		return returnVal;
 } 
	public static ProductMeterType mapstrstr(Map<String, String> fields) throws Exception {

		ProductMeterType returnVal = new ProductMeterType();

		if(fields.get("productMeterTypeId") != null) {
			returnVal.setProductMeterTypeId((String) fields.get("productMeterTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("defaultUomId") != null) {
			returnVal.setDefaultUomId((String) fields.get("defaultUomId"));
}


		return returnVal;
 } 
	public static ProductMeterType map(GenericValue val) {

ProductMeterType returnVal = new ProductMeterType();
		returnVal.setProductMeterTypeId(val.getString("productMeterTypeId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setDefaultUomId(val.getString("defaultUomId"));


return returnVal;

}

public static ProductMeterType map(HttpServletRequest request) throws Exception {

		ProductMeterType returnVal = new ProductMeterType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productMeterTypeId")) {
returnVal.setProductMeterTypeId(request.getParameter("productMeterTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("defaultUomId"))  {
returnVal.setDefaultUomId(request.getParameter("defaultUomId"));
}
return returnVal;

}
}
