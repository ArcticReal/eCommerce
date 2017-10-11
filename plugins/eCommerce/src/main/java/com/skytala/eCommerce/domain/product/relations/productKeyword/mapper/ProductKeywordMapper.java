package com.skytala.eCommerce.domain.product.relations.productKeyword.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productKeyword.model.ProductKeyword;

public class ProductKeywordMapper  {


	public static Map<String, Object> map(ProductKeyword productkeyword) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productkeyword.getProductId() != null ){
			returnVal.put("productId",productkeyword.getProductId());
}

		if(productkeyword.getKeyword() != null ){
			returnVal.put("keyword",productkeyword.getKeyword());
}

		if(productkeyword.getKeywordTypeId() != null ){
			returnVal.put("keywordTypeId",productkeyword.getKeywordTypeId());
}

		if(productkeyword.getRelevancyWeight() != null ){
			returnVal.put("relevancyWeight",productkeyword.getRelevancyWeight());
}

		if(productkeyword.getStatusId() != null ){
			returnVal.put("statusId",productkeyword.getStatusId());
}

		return returnVal;
}


	public static ProductKeyword map(Map<String, Object> fields) {

		ProductKeyword returnVal = new ProductKeyword();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("keyword") != null) {
			returnVal.setKeyword((String) fields.get("keyword"));
}

		if(fields.get("keywordTypeId") != null) {
			returnVal.setKeywordTypeId((String) fields.get("keywordTypeId"));
}

		if(fields.get("relevancyWeight") != null) {
			returnVal.setRelevancyWeight((long) fields.get("relevancyWeight"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}


		return returnVal;
 } 
	public static ProductKeyword mapstrstr(Map<String, String> fields) throws Exception {

		ProductKeyword returnVal = new ProductKeyword();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("keyword") != null) {
			returnVal.setKeyword((String) fields.get("keyword"));
}

		if(fields.get("keywordTypeId") != null) {
			returnVal.setKeywordTypeId((String) fields.get("keywordTypeId"));
}

		if(fields.get("relevancyWeight") != null) {
String buf;
buf = fields.get("relevancyWeight");
long ibuf = Long.parseLong(buf);
			returnVal.setRelevancyWeight(ibuf);
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}


		return returnVal;
 } 
	public static ProductKeyword map(GenericValue val) {

ProductKeyword returnVal = new ProductKeyword();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setKeyword(val.getString("keyword"));
		returnVal.setKeywordTypeId(val.getString("keywordTypeId"));
		returnVal.setRelevancyWeight(val.getLong("relevancyWeight"));
		returnVal.setStatusId(val.getString("statusId"));


return returnVal;

}

public static ProductKeyword map(HttpServletRequest request) throws Exception {

		ProductKeyword returnVal = new ProductKeyword();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("keyword"))  {
returnVal.setKeyword(request.getParameter("keyword"));
}
		if(paramMap.containsKey("keywordTypeId"))  {
returnVal.setKeywordTypeId(request.getParameter("keywordTypeId"));
}
		if(paramMap.containsKey("relevancyWeight"))  {
String buf = request.getParameter("relevancyWeight");
Long ibuf = Long.parseLong(buf);
returnVal.setRelevancyWeight(ibuf);
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
return returnVal;

}
}
