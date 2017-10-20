package com.skytala.eCommerce.domain.product.relations.product.mapper.geo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.geo.ProductGeo;

public class ProductGeoMapper  {


	public static Map<String, Object> map(ProductGeo productgeo) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productgeo.getProductId() != null ){
			returnVal.put("productId",productgeo.getProductId());
}

		if(productgeo.getGeoId() != null ){
			returnVal.put("geoId",productgeo.getGeoId());
}

		if(productgeo.getProductGeoEnumId() != null ){
			returnVal.put("productGeoEnumId",productgeo.getProductGeoEnumId());
}

		if(productgeo.getDescription() != null ){
			returnVal.put("description",productgeo.getDescription());
}

		return returnVal;
}


	public static ProductGeo map(Map<String, Object> fields) {

		ProductGeo returnVal = new ProductGeo();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
}

		if(fields.get("productGeoEnumId") != null) {
			returnVal.setProductGeoEnumId((String) fields.get("productGeoEnumId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductGeo mapstrstr(Map<String, String> fields) throws Exception {

		ProductGeo returnVal = new ProductGeo();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
}

		if(fields.get("productGeoEnumId") != null) {
			returnVal.setProductGeoEnumId((String) fields.get("productGeoEnumId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductGeo map(GenericValue val) {

ProductGeo returnVal = new ProductGeo();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setGeoId(val.getString("geoId"));
		returnVal.setProductGeoEnumId(val.getString("productGeoEnumId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductGeo map(HttpServletRequest request) throws Exception {

		ProductGeo returnVal = new ProductGeo();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("geoId"))  {
returnVal.setGeoId(request.getParameter("geoId"));
}
		if(paramMap.containsKey("productGeoEnumId"))  {
returnVal.setProductGeoEnumId(request.getParameter("productGeoEnumId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
