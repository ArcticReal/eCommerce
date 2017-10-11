package com.skytala.eCommerce.domain.product.relations.physicalInventory.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.model.PhysicalInventory;

public class PhysicalInventoryMapper  {


	public static Map<String, Object> map(PhysicalInventory physicalinventory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(physicalinventory.getPhysicalInventoryId() != null ){
			returnVal.put("physicalInventoryId",physicalinventory.getPhysicalInventoryId());
}

		if(physicalinventory.getPhysicalInventoryDate() != null ){
			returnVal.put("physicalInventoryDate",physicalinventory.getPhysicalInventoryDate());
}

		if(physicalinventory.getPartyId() != null ){
			returnVal.put("partyId",physicalinventory.getPartyId());
}

		if(physicalinventory.getGeneralComments() != null ){
			returnVal.put("generalComments",physicalinventory.getGeneralComments());
}

		return returnVal;
}


	public static PhysicalInventory map(Map<String, Object> fields) {

		PhysicalInventory returnVal = new PhysicalInventory();

		if(fields.get("physicalInventoryId") != null) {
			returnVal.setPhysicalInventoryId((String) fields.get("physicalInventoryId"));
}

		if(fields.get("physicalInventoryDate") != null) {
			returnVal.setPhysicalInventoryDate((Timestamp) fields.get("physicalInventoryDate"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("generalComments") != null) {
			returnVal.setGeneralComments((String) fields.get("generalComments"));
}


		return returnVal;
 } 
	public static PhysicalInventory mapstrstr(Map<String, String> fields) throws Exception {

		PhysicalInventory returnVal = new PhysicalInventory();

		if(fields.get("physicalInventoryId") != null) {
			returnVal.setPhysicalInventoryId((String) fields.get("physicalInventoryId"));
}

		if(fields.get("physicalInventoryDate") != null) {
String buf = fields.get("physicalInventoryDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPhysicalInventoryDate(ibuf);
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("generalComments") != null) {
			returnVal.setGeneralComments((String) fields.get("generalComments"));
}


		return returnVal;
 } 
	public static PhysicalInventory map(GenericValue val) {

PhysicalInventory returnVal = new PhysicalInventory();
		returnVal.setPhysicalInventoryId(val.getString("physicalInventoryId"));
		returnVal.setPhysicalInventoryDate(val.getTimestamp("physicalInventoryDate"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setGeneralComments(val.getString("generalComments"));


return returnVal;

}

public static PhysicalInventory map(HttpServletRequest request) throws Exception {

		PhysicalInventory returnVal = new PhysicalInventory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("physicalInventoryId")) {
returnVal.setPhysicalInventoryId(request.getParameter("physicalInventoryId"));
}

		if(paramMap.containsKey("physicalInventoryDate"))  {
String buf = request.getParameter("physicalInventoryDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setPhysicalInventoryDate(ibuf);
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("generalComments"))  {
returnVal.setGeneralComments(request.getParameter("generalComments"));
}
return returnVal;

}
}
