package com.skytala.eCommerce.domain.shipment.relations.carrierShipmentMethod.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.carrierShipmentMethod.model.CarrierShipmentMethod;

public class CarrierShipmentMethodMapper  {


	public static Map<String, Object> map(CarrierShipmentMethod carriershipmentmethod) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(carriershipmentmethod.getShipmentMethodTypeId() != null ){
			returnVal.put("shipmentMethodTypeId",carriershipmentmethod.getShipmentMethodTypeId());
}

		if(carriershipmentmethod.getPartyId() != null ){
			returnVal.put("partyId",carriershipmentmethod.getPartyId());
}

		if(carriershipmentmethod.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",carriershipmentmethod.getRoleTypeId());
}

		if(carriershipmentmethod.getSequenceNumber() != null ){
			returnVal.put("sequenceNumber",carriershipmentmethod.getSequenceNumber());
}

		if(carriershipmentmethod.getCarrierServiceCode() != null ){
			returnVal.put("carrierServiceCode",carriershipmentmethod.getCarrierServiceCode());
}

		return returnVal;
}


	public static CarrierShipmentMethod map(Map<String, Object> fields) {

		CarrierShipmentMethod returnVal = new CarrierShipmentMethod();

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("sequenceNumber") != null) {
			returnVal.setSequenceNumber((long) fields.get("sequenceNumber"));
}

		if(fields.get("carrierServiceCode") != null) {
			returnVal.setCarrierServiceCode((String) fields.get("carrierServiceCode"));
}


		return returnVal;
 } 
	public static CarrierShipmentMethod mapstrstr(Map<String, String> fields) throws Exception {

		CarrierShipmentMethod returnVal = new CarrierShipmentMethod();

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("sequenceNumber") != null) {
String buf;
buf = fields.get("sequenceNumber");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNumber(ibuf);
}

		if(fields.get("carrierServiceCode") != null) {
			returnVal.setCarrierServiceCode((String) fields.get("carrierServiceCode"));
}


		return returnVal;
 } 
	public static CarrierShipmentMethod map(GenericValue val) {

CarrierShipmentMethod returnVal = new CarrierShipmentMethod();
		returnVal.setShipmentMethodTypeId(val.getString("shipmentMethodTypeId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setSequenceNumber(val.getLong("sequenceNumber"));
		returnVal.setCarrierServiceCode(val.getString("carrierServiceCode"));


return returnVal;

}

public static CarrierShipmentMethod map(HttpServletRequest request) throws Exception {

		CarrierShipmentMethod returnVal = new CarrierShipmentMethod();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentMethodTypeId")) {
returnVal.setShipmentMethodTypeId(request.getParameter("shipmentMethodTypeId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("sequenceNumber"))  {
String buf = request.getParameter("sequenceNumber");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNumber(ibuf);
}
		if(paramMap.containsKey("carrierServiceCode"))  {
returnVal.setCarrierServiceCode(request.getParameter("carrierServiceCode"));
}
return returnVal;

}
}
