package com.skytala.eCommerce.domain.product.relations.productFeatureIactn.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.model.ProductFeatureIactn;

public class ProductFeatureIactnMapper  {


	public static Map<String, Object> map(ProductFeatureIactn productfeatureiactn) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfeatureiactn.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",productfeatureiactn.getProductFeatureId());
}

		if(productfeatureiactn.getProductFeatureIdTo() != null ){
			returnVal.put("productFeatureIdTo",productfeatureiactn.getProductFeatureIdTo());
}

		if(productfeatureiactn.getProductFeatureIactnTypeId() != null ){
			returnVal.put("productFeatureIactnTypeId",productfeatureiactn.getProductFeatureIactnTypeId());
}

		if(productfeatureiactn.getProductId() != null ){
			returnVal.put("productId",productfeatureiactn.getProductId());
}

		return returnVal;
}


	public static ProductFeatureIactn map(Map<String, Object> fields) {

		ProductFeatureIactn returnVal = new ProductFeatureIactn();

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("productFeatureIdTo") != null) {
			returnVal.setProductFeatureIdTo((String) fields.get("productFeatureIdTo"));
}

		if(fields.get("productFeatureIactnTypeId") != null) {
			returnVal.setProductFeatureIactnTypeId((String) fields.get("productFeatureIactnTypeId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}


		return returnVal;
 } 
	public static ProductFeatureIactn mapstrstr(Map<String, String> fields) throws Exception {

		ProductFeatureIactn returnVal = new ProductFeatureIactn();

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("productFeatureIdTo") != null) {
			returnVal.setProductFeatureIdTo((String) fields.get("productFeatureIdTo"));
}

		if(fields.get("productFeatureIactnTypeId") != null) {
			returnVal.setProductFeatureIactnTypeId((String) fields.get("productFeatureIactnTypeId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}


		return returnVal;
 } 
	public static ProductFeatureIactn map(GenericValue val) {

ProductFeatureIactn returnVal = new ProductFeatureIactn();
		returnVal.setProductFeatureId(val.getString("productFeatureId"));
		returnVal.setProductFeatureIdTo(val.getString("productFeatureIdTo"));
		returnVal.setProductFeatureIactnTypeId(val.getString("productFeatureIactnTypeId"));
		returnVal.setProductId(val.getString("productId"));


return returnVal;

}

public static ProductFeatureIactn map(HttpServletRequest request) throws Exception {

		ProductFeatureIactn returnVal = new ProductFeatureIactn();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productFeatureId")) {
returnVal.setProductFeatureId(request.getParameter("productFeatureId"));
}

		if(paramMap.containsKey("productFeatureIdTo"))  {
returnVal.setProductFeatureIdTo(request.getParameter("productFeatureIdTo"));
}
		if(paramMap.containsKey("productFeatureIactnTypeId"))  {
returnVal.setProductFeatureIactnTypeId(request.getParameter("productFeatureIactnTypeId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
return returnVal;

}
}
