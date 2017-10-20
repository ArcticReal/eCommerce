package com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroupRollup;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRollup.ProductStoreGroupRollup;

public class ProductStoreGroupRollupMapper  {


	public static Map<String, Object> map(ProductStoreGroupRollup productstoregrouprollup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstoregrouprollup.getProductStoreGroupId() != null ){
			returnVal.put("productStoreGroupId",productstoregrouprollup.getProductStoreGroupId());
}

		if(productstoregrouprollup.getParentGroupId() != null ){
			returnVal.put("parentGroupId",productstoregrouprollup.getParentGroupId());
}

		if(productstoregrouprollup.getFromDate() != null ){
			returnVal.put("fromDate",productstoregrouprollup.getFromDate());
}

		if(productstoregrouprollup.getThruDate() != null ){
			returnVal.put("thruDate",productstoregrouprollup.getThruDate());
}

		if(productstoregrouprollup.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productstoregrouprollup.getSequenceNum());
}

		return returnVal;
}


	public static ProductStoreGroupRollup map(Map<String, Object> fields) {

		ProductStoreGroupRollup returnVal = new ProductStoreGroupRollup();

		if(fields.get("productStoreGroupId") != null) {
			returnVal.setProductStoreGroupId((String) fields.get("productStoreGroupId"));
}

		if(fields.get("parentGroupId") != null) {
			returnVal.setParentGroupId((String) fields.get("parentGroupId"));
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
	public static ProductStoreGroupRollup mapstrstr(Map<String, String> fields) throws Exception {

		ProductStoreGroupRollup returnVal = new ProductStoreGroupRollup();

		if(fields.get("productStoreGroupId") != null) {
			returnVal.setProductStoreGroupId((String) fields.get("productStoreGroupId"));
}

		if(fields.get("parentGroupId") != null) {
			returnVal.setParentGroupId((String) fields.get("parentGroupId"));
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
	public static ProductStoreGroupRollup map(GenericValue val) {

ProductStoreGroupRollup returnVal = new ProductStoreGroupRollup();
		returnVal.setProductStoreGroupId(val.getString("productStoreGroupId"));
		returnVal.setParentGroupId(val.getString("parentGroupId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ProductStoreGroupRollup map(HttpServletRequest request) throws Exception {

		ProductStoreGroupRollup returnVal = new ProductStoreGroupRollup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreGroupId")) {
returnVal.setProductStoreGroupId(request.getParameter("productStoreGroupId"));
}

		if(paramMap.containsKey("parentGroupId"))  {
returnVal.setParentGroupId(request.getParameter("parentGroupId"));
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
