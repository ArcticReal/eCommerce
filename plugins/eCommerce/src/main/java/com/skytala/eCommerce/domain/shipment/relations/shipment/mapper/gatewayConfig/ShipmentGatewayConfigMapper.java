package com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayConfig;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayConfig.ShipmentGatewayConfig;

public class ShipmentGatewayConfigMapper  {


	public static Map<String, Object> map(ShipmentGatewayConfig shipmentgatewayconfig) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentgatewayconfig.getShipmentGatewayConfigId() != null ){
			returnVal.put("shipmentGatewayConfigId",shipmentgatewayconfig.getShipmentGatewayConfigId());
}

		if(shipmentgatewayconfig.getShipmentGatewayConfTypeId() != null ){
			returnVal.put("shipmentGatewayConfTypeId",shipmentgatewayconfig.getShipmentGatewayConfTypeId());
}

		if(shipmentgatewayconfig.getDescription() != null ){
			returnVal.put("description",shipmentgatewayconfig.getDescription());
}

		return returnVal;
}


	public static ShipmentGatewayConfig map(Map<String, Object> fields) {

		ShipmentGatewayConfig returnVal = new ShipmentGatewayConfig();

		if(fields.get("shipmentGatewayConfigId") != null) {
			returnVal.setShipmentGatewayConfigId((String) fields.get("shipmentGatewayConfigId"));
}

		if(fields.get("shipmentGatewayConfTypeId") != null) {
			returnVal.setShipmentGatewayConfTypeId((String) fields.get("shipmentGatewayConfTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ShipmentGatewayConfig mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentGatewayConfig returnVal = new ShipmentGatewayConfig();

		if(fields.get("shipmentGatewayConfigId") != null) {
			returnVal.setShipmentGatewayConfigId((String) fields.get("shipmentGatewayConfigId"));
}

		if(fields.get("shipmentGatewayConfTypeId") != null) {
			returnVal.setShipmentGatewayConfTypeId((String) fields.get("shipmentGatewayConfTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ShipmentGatewayConfig map(GenericValue val) {

ShipmentGatewayConfig returnVal = new ShipmentGatewayConfig();
		returnVal.setShipmentGatewayConfigId(val.getString("shipmentGatewayConfigId"));
		returnVal.setShipmentGatewayConfTypeId(val.getString("shipmentGatewayConfTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ShipmentGatewayConfig map(HttpServletRequest request) throws Exception {

		ShipmentGatewayConfig returnVal = new ShipmentGatewayConfig();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentGatewayConfigId")) {
returnVal.setShipmentGatewayConfigId(request.getParameter("shipmentGatewayConfigId"));
}

		if(paramMap.containsKey("shipmentGatewayConfTypeId"))  {
returnVal.setShipmentGatewayConfTypeId(request.getParameter("shipmentGatewayConfTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
