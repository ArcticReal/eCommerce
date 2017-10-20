package com.skytala.eCommerce.domain.product.relations.product.mapper.categoryTypeAttr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryTypeAttr.ProductCategoryTypeAttr;

public class ProductCategoryTypeAttrMapper  {


	public static Map<String, Object> map(ProductCategoryTypeAttr productcategorytypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productcategorytypeattr.getProductCategoryTypeId() != null ){
			returnVal.put("productCategoryTypeId",productcategorytypeattr.getProductCategoryTypeId());
}

		if(productcategorytypeattr.getAttrName() != null ){
			returnVal.put("attrName",productcategorytypeattr.getAttrName());
}

		if(productcategorytypeattr.getDescription() != null ){
			returnVal.put("description",productcategorytypeattr.getDescription());
}

		return returnVal;
}


	public static ProductCategoryTypeAttr map(Map<String, Object> fields) {

		ProductCategoryTypeAttr returnVal = new ProductCategoryTypeAttr();

		if(fields.get("productCategoryTypeId") != null) {
			returnVal.setProductCategoryTypeId((String) fields.get("productCategoryTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductCategoryTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		ProductCategoryTypeAttr returnVal = new ProductCategoryTypeAttr();

		if(fields.get("productCategoryTypeId") != null) {
			returnVal.setProductCategoryTypeId((String) fields.get("productCategoryTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductCategoryTypeAttr map(GenericValue val) {

ProductCategoryTypeAttr returnVal = new ProductCategoryTypeAttr();
		returnVal.setProductCategoryTypeId(val.getString("productCategoryTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductCategoryTypeAttr map(HttpServletRequest request) throws Exception {

		ProductCategoryTypeAttr returnVal = new ProductCategoryTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productCategoryTypeId")) {
returnVal.setProductCategoryTypeId(request.getParameter("productCategoryTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
