package com.skytala.eCommerce.domain.product.relations.product.mapper.meter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.meter.ProductMeter;

public class ProductMeterMapper  {


	public static Map<String, Object> map(ProductMeter productmeter) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productmeter.getProductId() != null ){
			returnVal.put("productId",productmeter.getProductId());
}

		if(productmeter.getProductMeterTypeId() != null ){
			returnVal.put("productMeterTypeId",productmeter.getProductMeterTypeId());
}

		if(productmeter.getMeterUomId() != null ){
			returnVal.put("meterUomId",productmeter.getMeterUomId());
}

		if(productmeter.getMeterName() != null ){
			returnVal.put("meterName",productmeter.getMeterName());
}

		return returnVal;
}


	public static ProductMeter map(Map<String, Object> fields) {

		ProductMeter returnVal = new ProductMeter();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productMeterTypeId") != null) {
			returnVal.setProductMeterTypeId((String) fields.get("productMeterTypeId"));
}

		if(fields.get("meterUomId") != null) {
			returnVal.setMeterUomId((String) fields.get("meterUomId"));
}

		if(fields.get("meterName") != null) {
			returnVal.setMeterName((String) fields.get("meterName"));
}


		return returnVal;
 } 
	public static ProductMeter mapstrstr(Map<String, String> fields) throws Exception {

		ProductMeter returnVal = new ProductMeter();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productMeterTypeId") != null) {
			returnVal.setProductMeterTypeId((String) fields.get("productMeterTypeId"));
}

		if(fields.get("meterUomId") != null) {
			returnVal.setMeterUomId((String) fields.get("meterUomId"));
}

		if(fields.get("meterName") != null) {
			returnVal.setMeterName((String) fields.get("meterName"));
}


		return returnVal;
 } 
	public static ProductMeter map(GenericValue val) {

ProductMeter returnVal = new ProductMeter();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductMeterTypeId(val.getString("productMeterTypeId"));
		returnVal.setMeterUomId(val.getString("meterUomId"));
		returnVal.setMeterName(val.getString("meterName"));


return returnVal;

}

public static ProductMeter map(HttpServletRequest request) throws Exception {

		ProductMeter returnVal = new ProductMeter();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("productMeterTypeId"))  {
returnVal.setProductMeterTypeId(request.getParameter("productMeterTypeId"));
}
		if(paramMap.containsKey("meterUomId"))  {
returnVal.setMeterUomId(request.getParameter("meterUomId"));
}
		if(paramMap.containsKey("meterName"))  {
returnVal.setMeterName(request.getParameter("meterName"));
}
return returnVal;

}
}
