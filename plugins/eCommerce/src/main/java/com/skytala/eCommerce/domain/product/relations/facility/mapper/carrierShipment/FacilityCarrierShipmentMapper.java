package com.skytala.eCommerce.domain.product.relations.facility.mapper.carrierShipment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facility.model.carrierShipment.FacilityCarrierShipment;

public class FacilityCarrierShipmentMapper  {


	public static Map<String, Object> map(FacilityCarrierShipment facilitycarriershipment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facilitycarriershipment.getFacilityId() != null ){
			returnVal.put("facilityId",facilitycarriershipment.getFacilityId());
}

		if(facilitycarriershipment.getPartyId() != null ){
			returnVal.put("partyId",facilitycarriershipment.getPartyId());
}

		if(facilitycarriershipment.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",facilitycarriershipment.getRoleTypeId());
}

		if(facilitycarriershipment.getShipmentMethodTypeId() != null ){
			returnVal.put("shipmentMethodTypeId",facilitycarriershipment.getShipmentMethodTypeId());
}

		return returnVal;
}


	public static FacilityCarrierShipment map(Map<String, Object> fields) {

		FacilityCarrierShipment returnVal = new FacilityCarrierShipment();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}


		return returnVal;
 } 
	public static FacilityCarrierShipment mapstrstr(Map<String, String> fields) throws Exception {

		FacilityCarrierShipment returnVal = new FacilityCarrierShipment();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}


		return returnVal;
 } 
	public static FacilityCarrierShipment map(GenericValue val) {

FacilityCarrierShipment returnVal = new FacilityCarrierShipment();
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setShipmentMethodTypeId(val.getString("shipmentMethodTypeId"));


return returnVal;

}

public static FacilityCarrierShipment map(HttpServletRequest request) throws Exception {

		FacilityCarrierShipment returnVal = new FacilityCarrierShipment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityId")) {
returnVal.setFacilityId(request.getParameter("facilityId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("shipmentMethodTypeId"))  {
returnVal.setShipmentMethodTypeId(request.getParameter("shipmentMethodTypeId"));
}
return returnVal;

}
}
