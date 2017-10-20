package com.skytala.eCommerce.domain.product.relations.product.mapper.featureDataResource;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.featureDataResource.ProductFeatureDataResource;

public class ProductFeatureDataResourceMapper  {


	public static Map<String, Object> map(ProductFeatureDataResource productfeaturedataresource) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfeaturedataresource.getDataResourceId() != null ){
			returnVal.put("dataResourceId",productfeaturedataresource.getDataResourceId());
}

		if(productfeaturedataresource.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",productfeaturedataresource.getProductFeatureId());
}

		return returnVal;
}


	public static ProductFeatureDataResource map(Map<String, Object> fields) {

		ProductFeatureDataResource returnVal = new ProductFeatureDataResource();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}


		return returnVal;
 } 
	public static ProductFeatureDataResource mapstrstr(Map<String, String> fields) throws Exception {

		ProductFeatureDataResource returnVal = new ProductFeatureDataResource();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}


		return returnVal;
 } 
	public static ProductFeatureDataResource map(GenericValue val) {

ProductFeatureDataResource returnVal = new ProductFeatureDataResource();
		returnVal.setDataResourceId(val.getString("dataResourceId"));
		returnVal.setProductFeatureId(val.getString("productFeatureId"));


return returnVal;

}

public static ProductFeatureDataResource map(HttpServletRequest request) throws Exception {

		ProductFeatureDataResource returnVal = new ProductFeatureDataResource();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("dataResourceId")) {
returnVal.setDataResourceId(request.getParameter("dataResourceId"));
}

		if(paramMap.containsKey("productFeatureId"))  {
returnVal.setProductFeatureId(request.getParameter("productFeatureId"));
}
return returnVal;

}
}
