package com.skytala.eCommerce.domain.product.relations.productStoreKeywordOvrd.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productStoreKeywordOvrd.model.ProductStoreKeywordOvrd;

public class ProductStoreKeywordOvrdMapper  {


	public static Map<String, Object> map(ProductStoreKeywordOvrd productstorekeywordovrd) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstorekeywordovrd.getProductStoreId() != null ){
			returnVal.put("productStoreId",productstorekeywordovrd.getProductStoreId());
}

		if(productstorekeywordovrd.getKeyword() != null ){
			returnVal.put("keyword",productstorekeywordovrd.getKeyword());
}

		if(productstorekeywordovrd.getFromDate() != null ){
			returnVal.put("fromDate",productstorekeywordovrd.getFromDate());
}

		if(productstorekeywordovrd.getThruDate() != null ){
			returnVal.put("thruDate",productstorekeywordovrd.getThruDate());
}

		if(productstorekeywordovrd.getTarget() != null ){
			returnVal.put("target",productstorekeywordovrd.getTarget());
}

		if(productstorekeywordovrd.getTargetTypeEnumId() != null ){
			returnVal.put("targetTypeEnumId",productstorekeywordovrd.getTargetTypeEnumId());
}

		return returnVal;
}


	public static ProductStoreKeywordOvrd map(Map<String, Object> fields) {

		ProductStoreKeywordOvrd returnVal = new ProductStoreKeywordOvrd();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("keyword") != null) {
			returnVal.setKeyword((String) fields.get("keyword"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("target") != null) {
			returnVal.setTarget((String) fields.get("target"));
}

		if(fields.get("targetTypeEnumId") != null) {
			returnVal.setTargetTypeEnumId((String) fields.get("targetTypeEnumId"));
}


		return returnVal;
 } 
	public static ProductStoreKeywordOvrd mapstrstr(Map<String, String> fields) throws Exception {

		ProductStoreKeywordOvrd returnVal = new ProductStoreKeywordOvrd();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("keyword") != null) {
			returnVal.setKeyword((String) fields.get("keyword"));
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

		if(fields.get("target") != null) {
			returnVal.setTarget((String) fields.get("target"));
}

		if(fields.get("targetTypeEnumId") != null) {
			returnVal.setTargetTypeEnumId((String) fields.get("targetTypeEnumId"));
}


		return returnVal;
 } 
	public static ProductStoreKeywordOvrd map(GenericValue val) {

ProductStoreKeywordOvrd returnVal = new ProductStoreKeywordOvrd();
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setKeyword(val.getString("keyword"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setTarget(val.getString("target"));
		returnVal.setTargetTypeEnumId(val.getString("targetTypeEnumId"));


return returnVal;

}

public static ProductStoreKeywordOvrd map(HttpServletRequest request) throws Exception {

		ProductStoreKeywordOvrd returnVal = new ProductStoreKeywordOvrd();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreId")) {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}

		if(paramMap.containsKey("keyword"))  {
returnVal.setKeyword(request.getParameter("keyword"));
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
		if(paramMap.containsKey("target"))  {
returnVal.setTarget(request.getParameter("target"));
}
		if(paramMap.containsKey("targetTypeEnumId"))  {
returnVal.setTargetTypeEnumId(request.getParameter("targetTypeEnumId"));
}
return returnVal;

}
}
