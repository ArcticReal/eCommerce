package com.skytala.eCommerce.domain.party.relations.partyProfileDefault.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyProfileDefault.model.PartyProfileDefault;

public class PartyProfileDefaultMapper  {


	public static Map<String, Object> map(PartyProfileDefault partyprofiledefault) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyprofiledefault.getPartyId() != null ){
			returnVal.put("partyId",partyprofiledefault.getPartyId());
}

		if(partyprofiledefault.getProductStoreId() != null ){
			returnVal.put("productStoreId",partyprofiledefault.getProductStoreId());
}

		if(partyprofiledefault.getDefaultShipAddr() != null ){
			returnVal.put("defaultShipAddr",partyprofiledefault.getDefaultShipAddr());
}

		if(partyprofiledefault.getDefaultBillAddr() != null ){
			returnVal.put("defaultBillAddr",partyprofiledefault.getDefaultBillAddr());
}

		if(partyprofiledefault.getDefaultPayMeth() != null ){
			returnVal.put("defaultPayMeth",partyprofiledefault.getDefaultPayMeth());
}

		if(partyprofiledefault.getDefaultShipMeth() != null ){
			returnVal.put("defaultShipMeth",partyprofiledefault.getDefaultShipMeth());
}

		return returnVal;
}


	public static PartyProfileDefault map(Map<String, Object> fields) {

		PartyProfileDefault returnVal = new PartyProfileDefault();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("defaultShipAddr") != null) {
			returnVal.setDefaultShipAddr((String) fields.get("defaultShipAddr"));
}

		if(fields.get("defaultBillAddr") != null) {
			returnVal.setDefaultBillAddr((String) fields.get("defaultBillAddr"));
}

		if(fields.get("defaultPayMeth") != null) {
			returnVal.setDefaultPayMeth((String) fields.get("defaultPayMeth"));
}

		if(fields.get("defaultShipMeth") != null) {
			returnVal.setDefaultShipMeth((String) fields.get("defaultShipMeth"));
}


		return returnVal;
 } 
	public static PartyProfileDefault mapstrstr(Map<String, String> fields) throws Exception {

		PartyProfileDefault returnVal = new PartyProfileDefault();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("defaultShipAddr") != null) {
			returnVal.setDefaultShipAddr((String) fields.get("defaultShipAddr"));
}

		if(fields.get("defaultBillAddr") != null) {
			returnVal.setDefaultBillAddr((String) fields.get("defaultBillAddr"));
}

		if(fields.get("defaultPayMeth") != null) {
			returnVal.setDefaultPayMeth((String) fields.get("defaultPayMeth"));
}

		if(fields.get("defaultShipMeth") != null) {
			returnVal.setDefaultShipMeth((String) fields.get("defaultShipMeth"));
}


		return returnVal;
 } 
	public static PartyProfileDefault map(GenericValue val) {

PartyProfileDefault returnVal = new PartyProfileDefault();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setDefaultShipAddr(val.getString("defaultShipAddr"));
		returnVal.setDefaultBillAddr(val.getString("defaultBillAddr"));
		returnVal.setDefaultPayMeth(val.getString("defaultPayMeth"));
		returnVal.setDefaultShipMeth(val.getString("defaultShipMeth"));


return returnVal;

}

public static PartyProfileDefault map(HttpServletRequest request) throws Exception {

		PartyProfileDefault returnVal = new PartyProfileDefault();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("productStoreId"))  {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}
		if(paramMap.containsKey("defaultShipAddr"))  {
returnVal.setDefaultShipAddr(request.getParameter("defaultShipAddr"));
}
		if(paramMap.containsKey("defaultBillAddr"))  {
returnVal.setDefaultBillAddr(request.getParameter("defaultBillAddr"));
}
		if(paramMap.containsKey("defaultPayMeth"))  {
returnVal.setDefaultPayMeth(request.getParameter("defaultPayMeth"));
}
		if(paramMap.containsKey("defaultShipMeth"))  {
returnVal.setDefaultShipMeth(request.getParameter("defaultShipMeth"));
}
return returnVal;

}
}
