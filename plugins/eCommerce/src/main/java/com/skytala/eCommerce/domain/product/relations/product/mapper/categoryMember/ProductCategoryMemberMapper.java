package com.skytala.eCommerce.domain.product.relations.product.mapper.categoryMember;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryMember.ProductCategoryMember;

public class ProductCategoryMemberMapper  {


	public static Map<String, Object> map(ProductCategoryMember productcategorymember) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productcategorymember.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",productcategorymember.getProductCategoryId());
}

		if(productcategorymember.getProductId() != null ){
			returnVal.put("productId",productcategorymember.getProductId());
}

		if(productcategorymember.getFromDate() != null ){
			returnVal.put("fromDate",productcategorymember.getFromDate());
}

		if(productcategorymember.getThruDate() != null ){
			returnVal.put("thruDate",productcategorymember.getThruDate());
}

		if(productcategorymember.getComments() != null ){
			returnVal.put("comments",productcategorymember.getComments());
}

		if(productcategorymember.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productcategorymember.getSequenceNum());
}

		if(productcategorymember.getQuantity() != null ){
			returnVal.put("quantity",productcategorymember.getQuantity());
}

		return returnVal;
}


	public static ProductCategoryMember map(Map<String, Object> fields) {

		ProductCategoryMember returnVal = new ProductCategoryMember();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}


		return returnVal;
 } 
	public static ProductCategoryMember mapstrstr(Map<String, String> fields) throws Exception {

		ProductCategoryMember returnVal = new ProductCategoryMember();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}


		return returnVal;
 } 
	public static ProductCategoryMember map(GenericValue val) {

ProductCategoryMember returnVal = new ProductCategoryMember();
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));


return returnVal;

}

public static ProductCategoryMember map(HttpServletRequest request) throws Exception {

		ProductCategoryMember returnVal = new ProductCategoryMember();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productCategoryId")) {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}

		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
return returnVal;

}
}
