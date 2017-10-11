package com.skytala.eCommerce.domain.product.relations.prodCatalogRole.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.prodCatalogRole.model.ProdCatalogRole;

public class ProdCatalogRoleMapper  {


	public static Map<String, Object> map(ProdCatalogRole prodcatalogrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(prodcatalogrole.getPartyId() != null ){
			returnVal.put("partyId",prodcatalogrole.getPartyId());
}

		if(prodcatalogrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",prodcatalogrole.getRoleTypeId());
}

		if(prodcatalogrole.getProdCatalogId() != null ){
			returnVal.put("prodCatalogId",prodcatalogrole.getProdCatalogId());
}

		if(prodcatalogrole.getFromDate() != null ){
			returnVal.put("fromDate",prodcatalogrole.getFromDate());
}

		if(prodcatalogrole.getThruDate() != null ){
			returnVal.put("thruDate",prodcatalogrole.getThruDate());
}

		if(prodcatalogrole.getSequenceNum() != null ){
			returnVal.put("sequenceNum",prodcatalogrole.getSequenceNum());
}

		return returnVal;
}


	public static ProdCatalogRole map(Map<String, Object> fields) {

		ProdCatalogRole returnVal = new ProdCatalogRole();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
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
	public static ProdCatalogRole mapstrstr(Map<String, String> fields) throws Exception {

		ProdCatalogRole returnVal = new ProdCatalogRole();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
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
	public static ProdCatalogRole map(GenericValue val) {

ProdCatalogRole returnVal = new ProdCatalogRole();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setProdCatalogId(val.getString("prodCatalogId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ProdCatalogRole map(HttpServletRequest request) throws Exception {

		ProdCatalogRole returnVal = new ProdCatalogRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("prodCatalogId"))  {
returnVal.setProdCatalogId(request.getParameter("prodCatalogId"));
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
