package com.skytala.eCommerce.domain.order.relations.custRequestType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequestType.model.CustRequestType;

public class CustRequestTypeMapper  {


	public static Map<String, Object> map(CustRequestType custrequesttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(custrequesttype.getCustRequestTypeId() != null ){
			returnVal.put("custRequestTypeId",custrequesttype.getCustRequestTypeId());
}

		if(custrequesttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",custrequesttype.getParentTypeId());
}

		if(custrequesttype.getHasTable() != null ){
			returnVal.put("hasTable",custrequesttype.getHasTable());
}

		if(custrequesttype.getDescription() != null ){
			returnVal.put("description",custrequesttype.getDescription());
}

		if(custrequesttype.getPartyId() != null ){
			returnVal.put("partyId",custrequesttype.getPartyId());
}

		return returnVal;
}


	public static CustRequestType map(Map<String, Object> fields) {

		CustRequestType returnVal = new CustRequestType();

		if(fields.get("custRequestTypeId") != null) {
			returnVal.setCustRequestTypeId((String) fields.get("custRequestTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}


		return returnVal;
 } 
	public static CustRequestType mapstrstr(Map<String, String> fields) throws Exception {

		CustRequestType returnVal = new CustRequestType();

		if(fields.get("custRequestTypeId") != null) {
			returnVal.setCustRequestTypeId((String) fields.get("custRequestTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}


		return returnVal;
 } 
	public static CustRequestType map(GenericValue val) {

CustRequestType returnVal = new CustRequestType();
		returnVal.setCustRequestTypeId(val.getString("custRequestTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setPartyId(val.getString("partyId"));


return returnVal;

}

public static CustRequestType map(HttpServletRequest request) throws Exception {

		CustRequestType returnVal = new CustRequestType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestTypeId")) {
returnVal.setCustRequestTypeId(request.getParameter("custRequestTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
return returnVal;

}
}
