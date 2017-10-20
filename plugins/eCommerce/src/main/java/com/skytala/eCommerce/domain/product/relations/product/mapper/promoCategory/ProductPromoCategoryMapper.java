package com.skytala.eCommerce.domain.product.relations.product.mapper.promoCategory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCategory.ProductPromoCategory;

public class ProductPromoCategoryMapper  {


	public static Map<String, Object> map(ProductPromoCategory productpromocategory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpromocategory.getProductPromoId() != null ){
			returnVal.put("productPromoId",productpromocategory.getProductPromoId());
}

		if(productpromocategory.getProductPromoRuleId() != null ){
			returnVal.put("productPromoRuleId",productpromocategory.getProductPromoRuleId());
}

		if(productpromocategory.getProductPromoActionSeqId() != null ){
			returnVal.put("productPromoActionSeqId",productpromocategory.getProductPromoActionSeqId());
}

		if(productpromocategory.getProductPromoCondSeqId() != null ){
			returnVal.put("productPromoCondSeqId",productpromocategory.getProductPromoCondSeqId());
}

		if(productpromocategory.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",productpromocategory.getProductCategoryId());
}

		if(productpromocategory.getAndGroupId() != null ){
			returnVal.put("andGroupId",productpromocategory.getAndGroupId());
}

		if(productpromocategory.getProductPromoApplEnumId() != null ){
			returnVal.put("productPromoApplEnumId",productpromocategory.getProductPromoApplEnumId());
}

		if(productpromocategory.getIncludeSubCategories() != null ){
			returnVal.put("includeSubCategories",productpromocategory.getIncludeSubCategories());
}

		return returnVal;
}


	public static ProductPromoCategory map(Map<String, Object> fields) {

		ProductPromoCategory returnVal = new ProductPromoCategory();

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

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("andGroupId") != null) {
			returnVal.setAndGroupId((String) fields.get("andGroupId"));
}

		if(fields.get("productPromoApplEnumId") != null) {
			returnVal.setProductPromoApplEnumId((String) fields.get("productPromoApplEnumId"));
}

		if(fields.get("includeSubCategories") != null) {
			returnVal.setIncludeSubCategories((boolean) fields.get("includeSubCategories"));
}


		return returnVal;
 } 
	public static ProductPromoCategory mapstrstr(Map<String, String> fields) throws Exception {

		ProductPromoCategory returnVal = new ProductPromoCategory();

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

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("andGroupId") != null) {
			returnVal.setAndGroupId((String) fields.get("andGroupId"));
}

		if(fields.get("productPromoApplEnumId") != null) {
			returnVal.setProductPromoApplEnumId((String) fields.get("productPromoApplEnumId"));
}

		if(fields.get("includeSubCategories") != null) {
String buf;
buf = fields.get("includeSubCategories");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIncludeSubCategories(ibuf);
}


		return returnVal;
 } 
	public static ProductPromoCategory map(GenericValue val) {

ProductPromoCategory returnVal = new ProductPromoCategory();
		returnVal.setProductPromoId(val.getString("productPromoId"));
		returnVal.setProductPromoRuleId(val.getString("productPromoRuleId"));
		returnVal.setProductPromoActionSeqId(val.getString("productPromoActionSeqId"));
		returnVal.setProductPromoCondSeqId(val.getString("productPromoCondSeqId"));
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setAndGroupId(val.getString("andGroupId"));
		returnVal.setProductPromoApplEnumId(val.getString("productPromoApplEnumId"));
		returnVal.setIncludeSubCategories(val.getBoolean("includeSubCategories"));


return returnVal;

}

public static ProductPromoCategory map(HttpServletRequest request) throws Exception {

		ProductPromoCategory returnVal = new ProductPromoCategory();

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
		if(paramMap.containsKey("productCategoryId"))  {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}
		if(paramMap.containsKey("andGroupId"))  {
returnVal.setAndGroupId(request.getParameter("andGroupId"));
}
		if(paramMap.containsKey("productPromoApplEnumId"))  {
returnVal.setProductPromoApplEnumId(request.getParameter("productPromoApplEnumId"));
}
		if(paramMap.containsKey("includeSubCategories"))  {
String buf = request.getParameter("includeSubCategories");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIncludeSubCategories(ibuf);
}
return returnVal;

}
}
