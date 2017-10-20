package com.skytala.eCommerce.domain.product.relations.product.mapper.categoryAttribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryAttribute.ProductCategoryAttribute;

public class ProductCategoryAttributeMapper  {


	public static Map<String, Object> map(ProductCategoryAttribute productcategoryattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productcategoryattribute.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",productcategoryattribute.getProductCategoryId());
}

		if(productcategoryattribute.getAttrName() != null ){
			returnVal.put("attrName",productcategoryattribute.getAttrName());
}

		if(productcategoryattribute.getAttrValue() != null ){
			returnVal.put("attrValue",productcategoryattribute.getAttrValue());
}

		if(productcategoryattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",productcategoryattribute.getAttrDescription());
}

		return returnVal;
}


	public static ProductCategoryAttribute map(Map<String, Object> fields) {

		ProductCategoryAttribute returnVal = new ProductCategoryAttribute();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static ProductCategoryAttribute mapstrstr(Map<String, String> fields) throws Exception {

		ProductCategoryAttribute returnVal = new ProductCategoryAttribute();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
String buf;
buf = fields.get("attrValue");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrValue(ibuf);
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static ProductCategoryAttribute map(GenericValue val) {

ProductCategoryAttribute returnVal = new ProductCategoryAttribute();
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static ProductCategoryAttribute map(HttpServletRequest request) throws Exception {

		ProductCategoryAttribute returnVal = new ProductCategoryAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productCategoryId")) {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
