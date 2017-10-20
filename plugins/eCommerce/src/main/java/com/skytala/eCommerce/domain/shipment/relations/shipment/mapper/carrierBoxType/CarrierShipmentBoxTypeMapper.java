package com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.carrierBoxType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.carrierBoxType.CarrierShipmentBoxType;

public class CarrierShipmentBoxTypeMapper  {


	public static Map<String, Object> map(CarrierShipmentBoxType carriershipmentboxtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(carriershipmentboxtype.getShipmentBoxTypeId() != null ){
			returnVal.put("shipmentBoxTypeId",carriershipmentboxtype.getShipmentBoxTypeId());
}

		if(carriershipmentboxtype.getPartyId() != null ){
			returnVal.put("partyId",carriershipmentboxtype.getPartyId());
}

		if(carriershipmentboxtype.getPackagingTypeCode() != null ){
			returnVal.put("packagingTypeCode",carriershipmentboxtype.getPackagingTypeCode());
}

		if(carriershipmentboxtype.getOversizeCode() != null ){
			returnVal.put("oversizeCode",carriershipmentboxtype.getOversizeCode());
}

		return returnVal;
}


	public static CarrierShipmentBoxType map(Map<String, Object> fields) {

		CarrierShipmentBoxType returnVal = new CarrierShipmentBoxType();

		if(fields.get("shipmentBoxTypeId") != null) {
			returnVal.setShipmentBoxTypeId((String) fields.get("shipmentBoxTypeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("packagingTypeCode") != null) {
			returnVal.setPackagingTypeCode((String) fields.get("packagingTypeCode"));
}

		if(fields.get("oversizeCode") != null) {
			returnVal.setOversizeCode((String) fields.get("oversizeCode"));
}


		return returnVal;
 } 
	public static CarrierShipmentBoxType mapstrstr(Map<String, String> fields) throws Exception {

		CarrierShipmentBoxType returnVal = new CarrierShipmentBoxType();

		if(fields.get("shipmentBoxTypeId") != null) {
			returnVal.setShipmentBoxTypeId((String) fields.get("shipmentBoxTypeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("packagingTypeCode") != null) {
			returnVal.setPackagingTypeCode((String) fields.get("packagingTypeCode"));
}

		if(fields.get("oversizeCode") != null) {
			returnVal.setOversizeCode((String) fields.get("oversizeCode"));
}


		return returnVal;
 } 
	public static CarrierShipmentBoxType map(GenericValue val) {

CarrierShipmentBoxType returnVal = new CarrierShipmentBoxType();
		returnVal.setShipmentBoxTypeId(val.getString("shipmentBoxTypeId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setPackagingTypeCode(val.getString("packagingTypeCode"));
		returnVal.setOversizeCode(val.getString("oversizeCode"));


return returnVal;

}

public static CarrierShipmentBoxType map(HttpServletRequest request) throws Exception {

		CarrierShipmentBoxType returnVal = new CarrierShipmentBoxType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentBoxTypeId")) {
returnVal.setShipmentBoxTypeId(request.getParameter("shipmentBoxTypeId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("packagingTypeCode"))  {
returnVal.setPackagingTypeCode(request.getParameter("packagingTypeCode"));
}
		if(paramMap.containsKey("oversizeCode"))  {
returnVal.setOversizeCode(request.getParameter("oversizeCode"));
}
return returnVal;

}
}
