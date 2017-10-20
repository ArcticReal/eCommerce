package com.skytala.eCommerce.domain.product.relations.product.mapper.featureGroup;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.featureGroup.ProductFeatureGroup;

public class ProductFeatureGroupMapper  {


	public static Map<String, Object> map(ProductFeatureGroup productfeaturegroup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfeaturegroup.getProductFeatureGroupId() != null ){
			returnVal.put("productFeatureGroupId",productfeaturegroup.getProductFeatureGroupId());
}

		if(productfeaturegroup.getDescription() != null ){
			returnVal.put("description",productfeaturegroup.getDescription());
}

		return returnVal;
}


	public static ProductFeatureGroup map(Map<String, Object> fields) {

		ProductFeatureGroup returnVal = new ProductFeatureGroup();

		if(fields.get("productFeatureGroupId") != null) {
			returnVal.setProductFeatureGroupId((String) fields.get("productFeatureGroupId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductFeatureGroup mapstrstr(Map<String, String> fields) throws Exception {

		ProductFeatureGroup returnVal = new ProductFeatureGroup();

		if(fields.get("productFeatureGroupId") != null) {
			returnVal.setProductFeatureGroupId((String) fields.get("productFeatureGroupId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductFeatureGroup map(GenericValue val) {

ProductFeatureGroup returnVal = new ProductFeatureGroup();
		returnVal.setProductFeatureGroupId(val.getString("productFeatureGroupId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductFeatureGroup map(HttpServletRequest request) throws Exception {

		ProductFeatureGroup returnVal = new ProductFeatureGroup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productFeatureGroupId")) {
returnVal.setProductFeatureGroupId(request.getParameter("productFeatureGroupId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
