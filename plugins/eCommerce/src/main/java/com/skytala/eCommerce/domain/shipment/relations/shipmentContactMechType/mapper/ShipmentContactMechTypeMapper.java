package com.skytala.eCommerce.domain.shipment.relations.shipmentContactMechType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentContactMechType.model.ShipmentContactMechType;

public class ShipmentContactMechTypeMapper  {


	public static Map<String, Object> map(ShipmentContactMechType shipmentcontactmechtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentcontactmechtype.getShipmentContactMechTypeId() != null ){
			returnVal.put("shipmentContactMechTypeId",shipmentcontactmechtype.getShipmentContactMechTypeId());
}

		if(shipmentcontactmechtype.getDescription() != null ){
			returnVal.put("description",shipmentcontactmechtype.getDescription());
}

		return returnVal;
}


	public static ShipmentContactMechType map(Map<String, Object> fields) {

		ShipmentContactMechType returnVal = new ShipmentContactMechType();

		if(fields.get("shipmentContactMechTypeId") != null) {
			returnVal.setShipmentContactMechTypeId((String) fields.get("shipmentContactMechTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ShipmentContactMechType mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentContactMechType returnVal = new ShipmentContactMechType();

		if(fields.get("shipmentContactMechTypeId") != null) {
			returnVal.setShipmentContactMechTypeId((String) fields.get("shipmentContactMechTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ShipmentContactMechType map(GenericValue val) {

ShipmentContactMechType returnVal = new ShipmentContactMechType();
		returnVal.setShipmentContactMechTypeId(val.getString("shipmentContactMechTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ShipmentContactMechType map(HttpServletRequest request) throws Exception {

		ShipmentContactMechType returnVal = new ShipmentContactMechType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentContactMechTypeId")) {
returnVal.setShipmentContactMechTypeId(request.getParameter("shipmentContactMechTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
