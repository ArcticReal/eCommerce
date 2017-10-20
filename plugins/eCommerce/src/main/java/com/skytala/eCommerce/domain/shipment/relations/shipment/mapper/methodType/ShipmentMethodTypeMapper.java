package com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.methodType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.methodType.ShipmentMethodType;

public class ShipmentMethodTypeMapper  {


	public static Map<String, Object> map(ShipmentMethodType shipmentmethodtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentmethodtype.getShipmentMethodTypeId() != null ){
			returnVal.put("shipmentMethodTypeId",shipmentmethodtype.getShipmentMethodTypeId());
}

		if(shipmentmethodtype.getDescription() != null ){
			returnVal.put("description",shipmentmethodtype.getDescription());
}

		if(shipmentmethodtype.getSequenceNum() != null ){
			returnVal.put("sequenceNum",shipmentmethodtype.getSequenceNum());
}

		return returnVal;
}


	public static ShipmentMethodType map(Map<String, Object> fields) {

		ShipmentMethodType returnVal = new ShipmentMethodType();

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static ShipmentMethodType mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentMethodType returnVal = new ShipmentMethodType();

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static ShipmentMethodType map(GenericValue val) {

ShipmentMethodType returnVal = new ShipmentMethodType();
		returnVal.setShipmentMethodTypeId(val.getString("shipmentMethodTypeId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ShipmentMethodType map(HttpServletRequest request) throws Exception {

		ShipmentMethodType returnVal = new ShipmentMethodType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentMethodTypeId")) {
returnVal.setShipmentMethodTypeId(request.getParameter("shipmentMethodTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
