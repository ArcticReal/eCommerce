package com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.model.ProductStoreGroupMember;

public class ProductStoreGroupMemberMapper  {


	public static Map<String, Object> map(ProductStoreGroupMember productstoregroupmember) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstoregroupmember.getProductStoreId() != null ){
			returnVal.put("productStoreId",productstoregroupmember.getProductStoreId());
}

		if(productstoregroupmember.getProductStoreGroupId() != null ){
			returnVal.put("productStoreGroupId",productstoregroupmember.getProductStoreGroupId());
}

		if(productstoregroupmember.getFromDate() != null ){
			returnVal.put("fromDate",productstoregroupmember.getFromDate());
}

		if(productstoregroupmember.getThruDate() != null ){
			returnVal.put("thruDate",productstoregroupmember.getThruDate());
}

		if(productstoregroupmember.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productstoregroupmember.getSequenceNum());
}

		return returnVal;
}


	public static ProductStoreGroupMember map(Map<String, Object> fields) {

		ProductStoreGroupMember returnVal = new ProductStoreGroupMember();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("productStoreGroupId") != null) {
			returnVal.setProductStoreGroupId((String) fields.get("productStoreGroupId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static ProductStoreGroupMember mapstrstr(Map<String, String> fields) throws Exception {

		ProductStoreGroupMember returnVal = new ProductStoreGroupMember();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("productStoreGroupId") != null) {
			returnVal.setProductStoreGroupId((String) fields.get("productStoreGroupId"));
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

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static ProductStoreGroupMember map(GenericValue val) {

ProductStoreGroupMember returnVal = new ProductStoreGroupMember();
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setProductStoreGroupId(val.getString("productStoreGroupId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ProductStoreGroupMember map(HttpServletRequest request) throws Exception {

		ProductStoreGroupMember returnVal = new ProductStoreGroupMember();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreId")) {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}

		if(paramMap.containsKey("productStoreGroupId"))  {
returnVal.setProductStoreGroupId(request.getParameter("productStoreGroupId"));
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
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
