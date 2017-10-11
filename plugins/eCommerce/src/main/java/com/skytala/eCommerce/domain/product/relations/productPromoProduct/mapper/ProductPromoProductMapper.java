package com.skytala.eCommerce.domain.product.relations.productPromoProduct.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPromoProduct.model.ProductPromoProduct;

public class ProductPromoProductMapper  {


	public static Map<String, Object> map(ProductPromoProduct productpromoproduct) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpromoproduct.getProductPromoId() != null ){
			returnVal.put("productPromoId",productpromoproduct.getProductPromoId());
}

		if(productpromoproduct.getProductPromoRuleId() != null ){
			returnVal.put("productPromoRuleId",productpromoproduct.getProductPromoRuleId());
}

		if(productpromoproduct.getProductPromoActionSeqId() != null ){
			returnVal.put("productPromoActionSeqId",productpromoproduct.getProductPromoActionSeqId());
}

		if(productpromoproduct.getProductPromoCondSeqId() != null ){
			returnVal.put("productPromoCondSeqId",productpromoproduct.getProductPromoCondSeqId());
}

		if(productpromoproduct.getProductId() != null ){
			returnVal.put("productId",productpromoproduct.getProductId());
}

		if(productpromoproduct.getProductPromoApplEnumId() != null ){
			returnVal.put("productPromoApplEnumId",productpromoproduct.getProductPromoApplEnumId());
}

		return returnVal;
}


	public static ProductPromoProduct map(Map<String, Object> fields) {

		ProductPromoProduct returnVal = new ProductPromoProduct();

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("productPromoRuleId") != null) {
			returnVal.setProductPromoRuleId((String) fields.get("productPromoRuleId"));
}

		if(fields.get("productPromoActionSeqId") != null) {
			returnVal.setProductPromoActionSeqId((String) fields.get("productPromoActionSeqId"));
}

		if(fields.get("productPromoCondSeqId") != null) {
			returnVal.setProductPromoCondSeqId((String) fields.get("productPromoCondSeqId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productPromoApplEnumId") != null) {
			returnVal.setProductPromoApplEnumId((String) fields.get("productPromoApplEnumId"));
}


		return returnVal;
 } 
	public static ProductPromoProduct mapstrstr(Map<String, String> fields) throws Exception {

		ProductPromoProduct returnVal = new ProductPromoProduct();

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("productPromoRuleId") != null) {
			returnVal.setProductPromoRuleId((String) fields.get("productPromoRuleId"));
}

		if(fields.get("productPromoActionSeqId") != null) {
			returnVal.setProductPromoActionSeqId((String) fields.get("productPromoActionSeqId"));
}

		if(fields.get("productPromoCondSeqId") != null) {
			returnVal.setProductPromoCondSeqId((String) fields.get("productPromoCondSeqId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productPromoApplEnumId") != null) {
			returnVal.setProductPromoApplEnumId((String) fields.get("productPromoApplEnumId"));
}


		return returnVal;
 } 
	public static ProductPromoProduct map(GenericValue val) {

ProductPromoProduct returnVal = new ProductPromoProduct();
		returnVal.setProductPromoId(val.getString("productPromoId"));
		returnVal.setProductPromoRuleId(val.getString("productPromoRuleId"));
		returnVal.setProductPromoActionSeqId(val.getString("productPromoActionSeqId"));
		returnVal.setProductPromoCondSeqId(val.getString("productPromoCondSeqId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductPromoApplEnumId(val.getString("productPromoApplEnumId"));


return returnVal;

}

public static ProductPromoProduct map(HttpServletRequest request) throws Exception {

		ProductPromoProduct returnVal = new ProductPromoProduct();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPromoId")) {
returnVal.setProductPromoId(request.getParameter("productPromoId"));
}

		if(paramMap.containsKey("productPromoRuleId"))  {
returnVal.setProductPromoRuleId(request.getParameter("productPromoRuleId"));
}
		if(paramMap.containsKey("productPromoActionSeqId"))  {
returnVal.setProductPromoActionSeqId(request.getParameter("productPromoActionSeqId"));
}
		if(paramMap.containsKey("productPromoCondSeqId"))  {
returnVal.setProductPromoCondSeqId(request.getParameter("productPromoCondSeqId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("productPromoApplEnumId"))  {
returnVal.setProductPromoApplEnumId(request.getParameter("productPromoApplEnumId"));
}
return returnVal;

}
}
