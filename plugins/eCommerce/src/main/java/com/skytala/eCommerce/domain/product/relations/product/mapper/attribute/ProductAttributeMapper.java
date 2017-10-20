package com.skytala.eCommerce.domain.product.relations.product.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.attribute.ProductAttribute;

public class ProductAttributeMapper  {


	public static Map<String, Object> map(ProductAttribute productattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productattribute.getProductId() != null ){
			returnVal.put("productId",productattribute.getProductId());
}

		if(productattribute.getAttrName() != null ){
			returnVal.put("attrName",productattribute.getAttrName());
}

		if(productattribute.getAttrValue() != null ){
			returnVal.put("attrValue",productattribute.getAttrValue());
}

		if(productattribute.getAttrType() != null ){
			returnVal.put("attrType",productattribute.getAttrType());
}

		if(productattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",productattribute.getAttrDescription());
}

		return returnVal;
}


	public static ProductAttribute map(Map<String, Object> fields) {

		ProductAttribute returnVal = new ProductAttribute();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}

		if(fields.get("attrType") != null) {
			returnVal.setAttrType((long) fields.get("attrType"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static ProductAttribute mapstrstr(Map<String, String> fields) throws Exception {

		ProductAttribute returnVal = new ProductAttribute();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
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

		if(fields.get("attrType") != null) {
String buf;
buf = fields.get("attrType");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrType(ibuf);
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static ProductAttribute map(GenericValue val) {

ProductAttribute returnVal = new ProductAttribute();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrType(val.getLong("attrType"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static ProductAttribute map(HttpServletRequest request) throws Exception {

		ProductAttribute returnVal = new ProductAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
		if(paramMap.containsKey("attrType"))  {
String buf = request.getParameter("attrType");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrType(ibuf);
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
