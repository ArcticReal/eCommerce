package com.skytala.eCommerce.domain.shipment.relations.shipmentContactMech.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentContactMech.model.ShipmentContactMech;

public class ShipmentContactMechMapper  {


	public static Map<String, Object> map(ShipmentContactMech shipmentcontactmech) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentcontactmech.getShipmentId() != null ){
			returnVal.put("shipmentId",shipmentcontactmech.getShipmentId());
}

		if(shipmentcontactmech.getShipmentContactMechTypeId() != null ){
			returnVal.put("shipmentContactMechTypeId",shipmentcontactmech.getShipmentContactMechTypeId());
}

		if(shipmentcontactmech.getContactMechId() != null ){
			returnVal.put("contactMechId",shipmentcontactmech.getContactMechId());
}

		return returnVal;
}


	public static ShipmentContactMech map(Map<String, Object> fields) {

		ShipmentContactMech returnVal = new ShipmentContactMech();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentContactMechTypeId") != null) {
			returnVal.setShipmentContactMechTypeId((String) fields.get("shipmentContactMechTypeId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}


		return returnVal;
 } 
	public static ShipmentContactMech mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentContactMech returnVal = new ShipmentContactMech();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentContactMechTypeId") != null) {
			returnVal.setShipmentContactMechTypeId((String) fields.get("shipmentContactMechTypeId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}


		return returnVal;
 } 
	public static ShipmentContactMech map(GenericValue val) {

ShipmentContactMech returnVal = new ShipmentContactMech();
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setShipmentContactMechTypeId(val.getString("shipmentContactMechTypeId"));
		returnVal.setContactMechId(val.getString("contactMechId"));


return returnVal;

}

public static ShipmentContactMech map(HttpServletRequest request) throws Exception {

		ShipmentContactMech returnVal = new ShipmentContactMech();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentId")) {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}

		if(paramMap.containsKey("shipmentContactMechTypeId"))  {
returnVal.setShipmentContactMechTypeId(request.getParameter("shipmentContactMechTypeId"));
}
		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
return returnVal;

}
}
