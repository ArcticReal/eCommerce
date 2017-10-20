package com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.receiptRole;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.receiptRole.ShipmentReceiptRole;

public class ShipmentReceiptRoleMapper  {


	public static Map<String, Object> map(ShipmentReceiptRole shipmentreceiptrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentreceiptrole.getReceiptId() != null ){
			returnVal.put("receiptId",shipmentreceiptrole.getReceiptId());
}

		if(shipmentreceiptrole.getPartyId() != null ){
			returnVal.put("partyId",shipmentreceiptrole.getPartyId());
}

		if(shipmentreceiptrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",shipmentreceiptrole.getRoleTypeId());
}

		return returnVal;
}


	public static ShipmentReceiptRole map(Map<String, Object> fields) {

		ShipmentReceiptRole returnVal = new ShipmentReceiptRole();

		if(fields.get("receiptId") != null) {
			returnVal.setReceiptId((String) fields.get("receiptId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static ShipmentReceiptRole mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentReceiptRole returnVal = new ShipmentReceiptRole();

		if(fields.get("receiptId") != null) {
			returnVal.setReceiptId((String) fields.get("receiptId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static ShipmentReceiptRole map(GenericValue val) {

ShipmentReceiptRole returnVal = new ShipmentReceiptRole();
		returnVal.setReceiptId(val.getString("receiptId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));


return returnVal;

}

public static ShipmentReceiptRole map(HttpServletRequest request) throws Exception {

		ShipmentReceiptRole returnVal = new ShipmentReceiptRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("receiptId")) {
returnVal.setReceiptId(request.getParameter("receiptId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
return returnVal;

}
}
