package com.skytala.eCommerce.domain.product.relations.productConfigProduct.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productConfigProduct.model.ProductConfigProduct;

public class ProductConfigProductMapper  {


	public static Map<String, Object> map(ProductConfigProduct productconfigproduct) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productconfigproduct.getConfigItemId() != null ){
			returnVal.put("configItemId",productconfigproduct.getConfigItemId());
}

		if(productconfigproduct.getConfigOptionId() != null ){
			returnVal.put("configOptionId",productconfigproduct.getConfigOptionId());
}

		if(productconfigproduct.getProductId() != null ){
			returnVal.put("productId",productconfigproduct.getProductId());
}

		if(productconfigproduct.getQuantity() != null ){
			returnVal.put("quantity",productconfigproduct.getQuantity());
}

		if(productconfigproduct.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productconfigproduct.getSequenceNum());
}

		return returnVal;
}


	public static ProductConfigProduct map(Map<String, Object> fields) {

		ProductConfigProduct returnVal = new ProductConfigProduct();

		if(fields.get("configItemId") != null) {
			returnVal.setConfigItemId((String) fields.get("configItemId"));
}

		if(fields.get("configOptionId") != null) {
			returnVal.setConfigOptionId((String) fields.get("configOptionId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static ProductConfigProduct mapstrstr(Map<String, String> fields) throws Exception {

		ProductConfigProduct returnVal = new ProductConfigProduct();

		if(fields.get("configItemId") != null) {
			returnVal.setConfigItemId((String) fields.get("configItemId"));
}

		if(fields.get("configOptionId") != null) {
			returnVal.setConfigOptionId((String) fields.get("configOptionId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static ProductConfigProduct map(GenericValue val) {

ProductConfigProduct returnVal = new ProductConfigProduct();
		returnVal.setConfigItemId(val.getString("configItemId"));
		returnVal.setConfigOptionId(val.getString("configOptionId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ProductConfigProduct map(HttpServletRequest request) throws Exception {

		ProductConfigProduct returnVal = new ProductConfigProduct();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("configItemId")) {
returnVal.setConfigItemId(request.getParameter("configItemId"));
}

		if(paramMap.containsKey("configOptionId"))  {
returnVal.setConfigOptionId(request.getParameter("configOptionId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
