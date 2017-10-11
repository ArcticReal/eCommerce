package com.skytala.eCommerce.domain.product.relations.productTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productTypeAttr.model.ProductTypeAttr;

public class ProductTypeAttrMapper  {


	public static Map<String, Object> map(ProductTypeAttr producttypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(producttypeattr.getProductTypeId() != null ){
			returnVal.put("productTypeId",producttypeattr.getProductTypeId());
}

		if(producttypeattr.getAttrName() != null ){
			returnVal.put("attrName",producttypeattr.getAttrName());
}

		if(producttypeattr.getDescription() != null ){
			returnVal.put("description",producttypeattr.getDescription());
}

		return returnVal;
}


	public static ProductTypeAttr map(Map<String, Object> fields) {

		ProductTypeAttr returnVal = new ProductTypeAttr();

		if(fields.get("productTypeId") != null) {
			returnVal.setProductTypeId((String) fields.get("productTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		ProductTypeAttr returnVal = new ProductTypeAttr();

		if(fields.get("productTypeId") != null) {
			returnVal.setProductTypeId((String) fields.get("productTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductTypeAttr map(GenericValue val) {

ProductTypeAttr returnVal = new ProductTypeAttr();
		returnVal.setProductTypeId(val.getString("productTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductTypeAttr map(HttpServletRequest request) throws Exception {

		ProductTypeAttr returnVal = new ProductTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productTypeId")) {
returnVal.setProductTypeId(request.getParameter("productTypeId"));
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
