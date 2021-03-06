package com.skytala.eCommerce.domain.product.relations.product.mapper.role;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.role.ProductRole;

public class ProductRoleMapper  {


	public static Map<String, Object> map(ProductRole productrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productrole.getProductId() != null ){
			returnVal.put("productId",productrole.getProductId());
}

		if(productrole.getPartyId() != null ){
			returnVal.put("partyId",productrole.getPartyId());
}

		if(productrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",productrole.getRoleTypeId());
}

		if(productrole.getFromDate() != null ){
			returnVal.put("fromDate",productrole.getFromDate());
}

		if(productrole.getThruDate() != null ){
			returnVal.put("thruDate",productrole.getThruDate());
}

		if(productrole.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productrole.getSequenceNum());
}

		if(productrole.getComments() != null ){
			returnVal.put("comments",productrole.getComments());
}

		return returnVal;
}


	public static ProductRole map(Map<String, Object> fields) {

		ProductRole returnVal = new ProductRole();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
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

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static ProductRole mapstrstr(Map<String, String> fields) throws Exception {

		ProductRole returnVal = new ProductRole();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
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

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static ProductRole map(GenericValue val) {

ProductRole returnVal = new ProductRole();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static ProductRole map(HttpServletRequest request) throws Exception {

		ProductRole returnVal = new ProductRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
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
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
