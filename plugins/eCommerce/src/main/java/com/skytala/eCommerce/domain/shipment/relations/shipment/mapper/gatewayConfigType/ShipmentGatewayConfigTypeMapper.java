package com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayConfigType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayConfigType.ShipmentGatewayConfigType;

public class ShipmentGatewayConfigTypeMapper  {


	public static Map<String, Object> map(ShipmentGatewayConfigType shipmentgatewayconfigtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentgatewayconfigtype.getShipmentGatewayConfTypeId() != null ){
			returnVal.put("shipmentGatewayConfTypeId",shipmentgatewayconfigtype.getShipmentGatewayConfTypeId());
}

		if(shipmentgatewayconfigtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",shipmentgatewayconfigtype.getParentTypeId());
}

		if(shipmentgatewayconfigtype.getHasTable() != null ){
			returnVal.put("hasTable",shipmentgatewayconfigtype.getHasTable());
}

		if(shipmentgatewayconfigtype.getDescription() != null ){
			returnVal.put("description",shipmentgatewayconfigtype.getDescription());
}

		return returnVal;
}


	public static ShipmentGatewayConfigType map(Map<String, Object> fields) {

		ShipmentGatewayConfigType returnVal = new ShipmentGatewayConfigType();

		if(fields.get("shipmentGatewayConfTypeId") != null) {
			returnVal.setShipmentGatewayConfTypeId((String) fields.get("shipmentGatewayConfTypeId"));
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


		return returnVal;
 } 
	public static ShipmentGatewayConfigType mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentGatewayConfigType returnVal = new ShipmentGatewayConfigType();

		if(fields.get("shipmentGatewayConfTypeId") != null) {
			returnVal.setShipmentGatewayConfTypeId((String) fields.get("shipmentGatewayConfTypeId"));
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


		return returnVal;
 } 
	public static ShipmentGatewayConfigType map(GenericValue val) {

ShipmentGatewayConfigType returnVal = new ShipmentGatewayConfigType();
		returnVal.setShipmentGatewayConfTypeId(val.getString("shipmentGatewayConfTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ShipmentGatewayConfigType map(HttpServletRequest request) throws Exception {

		ShipmentGatewayConfigType returnVal = new ShipmentGatewayConfigType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentGatewayConfTypeId")) {
returnVal.setShipmentGatewayConfTypeId(request.getParameter("shipmentGatewayConfTypeId"));
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
return returnVal;

}
}
