package com.skytala.eCommerce.domain.product.relations.product.mapper.storeRole;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.storeRole.ProductStoreRole;

public class ProductStoreRoleMapper  {


	public static Map<String, Object> map(ProductStoreRole productstorerole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstorerole.getPartyId() != null ){
			returnVal.put("partyId",productstorerole.getPartyId());
}

		if(productstorerole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",productstorerole.getRoleTypeId());
}

		if(productstorerole.getProductStoreId() != null ){
			returnVal.put("productStoreId",productstorerole.getProductStoreId());
}

		if(productstorerole.getFromDate() != null ){
			returnVal.put("fromDate",productstorerole.getFromDate());
}

		if(productstorerole.getThruDate() != null ){
			returnVal.put("thruDate",productstorerole.getThruDate());
}

		if(productstorerole.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productstorerole.getSequenceNum());
}

		return returnVal;
}


	public static ProductStoreRole map(Map<String, Object> fields) {

		ProductStoreRole returnVal = new ProductStoreRole();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
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
	public static ProductStoreRole mapstrstr(Map<String, String> fields) throws Exception {

		ProductStoreRole returnVal = new ProductStoreRole();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
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
	public static ProductStoreRole map(GenericValue val) {

ProductStoreRole returnVal = new ProductStoreRole();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ProductStoreRole map(HttpServletRequest request) throws Exception {

		ProductStoreRole returnVal = new ProductStoreRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("productStoreId"))  {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
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
