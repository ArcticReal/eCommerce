package com.skytala.eCommerce.domain.productFeatureCategory.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productFeatureCategory.model.ProductFeatureCategory;

public class ProductFeatureCategoryMapper  {


	public static Map<String, Object> map(ProductFeatureCategory productfeaturecategory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfeaturecategory.getProductFeatureCategoryId() != null ){
			returnVal.put("productFeatureCategoryId",productfeaturecategory.getProductFeatureCategoryId());
}

		if(productfeaturecategory.getParentCategoryId() != null ){
			returnVal.put("parentCategoryId",productfeaturecategory.getParentCategoryId());
}

		if(productfeaturecategory.getDescription() != null ){
			returnVal.put("description",productfeaturecategory.getDescription());
}

		return returnVal;
}


	public static ProductFeatureCategory map(Map<String, Object> fields) {

		ProductFeatureCategory returnVal = new ProductFeatureCategory();

		if(fields.get("productFeatureCategoryId") != null) {
			returnVal.setProductFeatureCategoryId((String) fields.get("productFeatureCategoryId"));
}

		if(fields.get("parentCategoryId") != null) {
			returnVal.setParentCategoryId((String) fields.get("parentCategoryId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductFeatureCategory mapstrstr(Map<String, String> fields) throws Exception {

		ProductFeatureCategory returnVal = new ProductFeatureCategory();

		if(fields.get("productFeatureCategoryId") != null) {
			returnVal.setProductFeatureCategoryId((String) fields.get("productFeatureCategoryId"));
}

		if(fields.get("parentCategoryId") != null) {
			returnVal.setParentCategoryId((String) fields.get("parentCategoryId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductFeatureCategory map(GenericValue val) {

ProductFeatureCategory returnVal = new ProductFeatureCategory();
		returnVal.setProductFeatureCategoryId(val.getString("productFeatureCategoryId"));
		returnVal.setParentCategoryId(val.getString("parentCategoryId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductFeatureCategory map(HttpServletRequest request) throws Exception {

		ProductFeatureCategory returnVal = new ProductFeatureCategory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productFeatureCategoryId")) {
returnVal.setProductFeatureCategoryId(request.getParameter("productFeatureCategoryId"));
}

		if(paramMap.containsKey("parentCategoryId"))  {
returnVal.setParentCategoryId(request.getParameter("parentCategoryId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
