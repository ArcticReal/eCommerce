package com.skytala.eCommerce.domain.shipment.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.model.Shipment;

public class ShipmentMapper  {


	public static Map<String, Object> map(Shipment shipment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipment.getShipmentId() != null ){
			returnVal.put("shipmentId",shipment.getShipmentId());
}

		if(shipment.getShipmentTypeId() != null ){
			returnVal.put("shipmentTypeId",shipment.getShipmentTypeId());
}

		if(shipment.getStatusId() != null ){
			returnVal.put("statusId",shipment.getStatusId());
}

		if(shipment.getPrimaryOrderId() != null ){
			returnVal.put("primaryOrderId",shipment.getPrimaryOrderId());
}

		if(shipment.getPrimaryReturnId() != null ){
			returnVal.put("primaryReturnId",shipment.getPrimaryReturnId());
}

		if(shipment.getPrimaryShipGroupSeqId() != null ){
			returnVal.put("primaryShipGroupSeqId",shipment.getPrimaryShipGroupSeqId());
}

		if(shipment.getPicklistBinId() != null ){
			returnVal.put("picklistBinId",shipment.getPicklistBinId());
}

		if(shipment.getEstimatedReadyDate() != null ){
			returnVal.put("estimatedReadyDate",shipment.getEstimatedReadyDate());
}

		if(shipment.getEstimatedShipDate() != null ){
			returnVal.put("estimatedShipDate",shipment.getEstimatedShipDate());
}

		if(shipment.getEstimatedShipWorkEffId() != null ){
			returnVal.put("estimatedShipWorkEffId",shipment.getEstimatedShipWorkEffId());
}

		if(shipment.getEstimatedArrivalDate() != null ){
			returnVal.put("estimatedArrivalDate",shipment.getEstimatedArrivalDate());
}

		if(shipment.getEstimatedArrivalWorkEffId() != null ){
			returnVal.put("estimatedArrivalWorkEffId",shipment.getEstimatedArrivalWorkEffId());
}

		if(shipment.getLatestCancelDate() != null ){
			returnVal.put("latestCancelDate",shipment.getLatestCancelDate());
}

		if(shipment.getEstimatedShipCost() != null ){
			returnVal.put("estimatedShipCost",shipment.getEstimatedShipCost());
}

		if(shipment.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",shipment.getCurrencyUomId());
}

		if(shipment.getHandlingInstructions() != null ){
			returnVal.put("handlingInstructions",shipment.getHandlingInstructions());
}

		if(shipment.getOriginFacilityId() != null ){
			returnVal.put("originFacilityId",shipment.getOriginFacilityId());
}

		if(shipment.getDestinationFacilityId() != null ){
			returnVal.put("destinationFacilityId",shipment.getDestinationFacilityId());
}

		if(shipment.getOriginContactMechId() != null ){
			returnVal.put("originContactMechId",shipment.getOriginContactMechId());
}

		if(shipment.getOriginTelecomNumberId() != null ){
			returnVal.put("originTelecomNumberId",shipment.getOriginTelecomNumberId());
}

		if(shipment.getDestinationContactMechId() != null ){
			returnVal.put("destinationContactMechId",shipment.getDestinationContactMechId());
}

		if(shipment.getDestinationTelecomNumberId() != null ){
			returnVal.put("destinationTelecomNumberId",shipment.getDestinationTelecomNumberId());
}

		if(shipment.getPartyIdTo() != null ){
			returnVal.put("partyIdTo",shipment.getPartyIdTo());
}

		if(shipment.getPartyIdFrom() != null ){
			returnVal.put("partyIdFrom",shipment.getPartyIdFrom());
}

		if(shipment.getAdditionalShippingCharge() != null ){
			returnVal.put("additionalShippingCharge",shipment.getAdditionalShippingCharge());
}

		if(shipment.getAddtlShippingChargeDesc() != null ){
			returnVal.put("addtlShippingChargeDesc",shipment.getAddtlShippingChargeDesc());
}

		if(shipment.getCreatedDate() != null ){
			returnVal.put("createdDate",shipment.getCreatedDate());
}

		if(shipment.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",shipment.getCreatedByUserLogin());
}

		if(shipment.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",shipment.getLastModifiedDate());
}

		if(shipment.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",shipment.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static Shipment map(Map<String, Object> fields) {

		Shipment returnVal = new Shipment();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentTypeId") != null) {
			returnVal.setShipmentTypeId((String) fields.get("shipmentTypeId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("primaryOrderId") != null) {
			returnVal.setPrimaryOrderId((String) fields.get("primaryOrderId"));
}

		if(fields.get("primaryReturnId") != null) {
			returnVal.setPrimaryReturnId((String) fields.get("primaryReturnId"));
}

		if(fields.get("primaryShipGroupSeqId") != null) {
			returnVal.setPrimaryShipGroupSeqId((String) fields.get("primaryShipGroupSeqId"));
}

		if(fields.get("picklistBinId") != null) {
			returnVal.setPicklistBinId((String) fields.get("picklistBinId"));
}

		if(fields.get("estimatedReadyDate") != null) {
			returnVal.setEstimatedReadyDate((Timestamp) fields.get("estimatedReadyDate"));
}

		if(fields.get("estimatedShipDate") != null) {
			returnVal.setEstimatedShipDate((Timestamp) fields.get("estimatedShipDate"));
}

		if(fields.get("estimatedShipWorkEffId") != null) {
			returnVal.setEstimatedShipWorkEffId((String) fields.get("estimatedShipWorkEffId"));
}

		if(fields.get("estimatedArrivalDate") != null) {
			returnVal.setEstimatedArrivalDate((Timestamp) fields.get("estimatedArrivalDate"));
}

		if(fields.get("estimatedArrivalWorkEffId") != null) {
			returnVal.setEstimatedArrivalWorkEffId((String) fields.get("estimatedArrivalWorkEffId"));
}

		if(fields.get("latestCancelDate") != null) {
			returnVal.setLatestCancelDate((Timestamp) fields.get("latestCancelDate"));
}

		if(fields.get("estimatedShipCost") != null) {
			returnVal.setEstimatedShipCost((BigDecimal) fields.get("estimatedShipCost"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("handlingInstructions") != null) {
			returnVal.setHandlingInstructions((String) fields.get("handlingInstructions"));
}

		if(fields.get("originFacilityId") != null) {
			returnVal.setOriginFacilityId((String) fields.get("originFacilityId"));
}

		if(fields.get("destinationFacilityId") != null) {
			returnVal.setDestinationFacilityId((String) fields.get("destinationFacilityId"));
}

		if(fields.get("originContactMechId") != null) {
			returnVal.setOriginContactMechId((String) fields.get("originContactMechId"));
}

		if(fields.get("originTelecomNumberId") != null) {
			returnVal.setOriginTelecomNumberId((String) fields.get("originTelecomNumberId"));
}

		if(fields.get("destinationContactMechId") != null) {
			returnVal.setDestinationContactMechId((String) fields.get("destinationContactMechId"));
}

		if(fields.get("destinationTelecomNumberId") != null) {
			returnVal.setDestinationTelecomNumberId((String) fields.get("destinationTelecomNumberId"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("additionalShippingCharge") != null) {
			returnVal.setAdditionalShippingCharge((BigDecimal) fields.get("additionalShippingCharge"));
}

		if(fields.get("addtlShippingChargeDesc") != null) {
			returnVal.setAddtlShippingChargeDesc((String) fields.get("addtlShippingChargeDesc"));
}

		if(fields.get("createdDate") != null) {
			returnVal.setCreatedDate((Timestamp) fields.get("createdDate"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
			returnVal.setLastModifiedDate((Timestamp) fields.get("lastModifiedDate"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static Shipment mapstrstr(Map<String, String> fields) throws Exception {

		Shipment returnVal = new Shipment();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentTypeId") != null) {
			returnVal.setShipmentTypeId((String) fields.get("shipmentTypeId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("primaryOrderId") != null) {
			returnVal.setPrimaryOrderId((String) fields.get("primaryOrderId"));
}

		if(fields.get("primaryReturnId") != null) {
			returnVal.setPrimaryReturnId((String) fields.get("primaryReturnId"));
}

		if(fields.get("primaryShipGroupSeqId") != null) {
			returnVal.setPrimaryShipGroupSeqId((String) fields.get("primaryShipGroupSeqId"));
}

		if(fields.get("picklistBinId") != null) {
			returnVal.setPicklistBinId((String) fields.get("picklistBinId"));
}

		if(fields.get("estimatedReadyDate") != null) {
String buf = fields.get("estimatedReadyDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedReadyDate(ibuf);
}

		if(fields.get("estimatedShipDate") != null) {
String buf = fields.get("estimatedShipDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedShipDate(ibuf);
}

		if(fields.get("estimatedShipWorkEffId") != null) {
			returnVal.setEstimatedShipWorkEffId((String) fields.get("estimatedShipWorkEffId"));
}

		if(fields.get("estimatedArrivalDate") != null) {
String buf = fields.get("estimatedArrivalDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedArrivalDate(ibuf);
}

		if(fields.get("estimatedArrivalWorkEffId") != null) {
			returnVal.setEstimatedArrivalWorkEffId((String) fields.get("estimatedArrivalWorkEffId"));
}

		if(fields.get("latestCancelDate") != null) {
String buf = fields.get("latestCancelDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLatestCancelDate(ibuf);
}

		if(fields.get("estimatedShipCost") != null) {
String buf;
buf = fields.get("estimatedShipCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedShipCost(bd);
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("handlingInstructions") != null) {
			returnVal.setHandlingInstructions((String) fields.get("handlingInstructions"));
}

		if(fields.get("originFacilityId") != null) {
			returnVal.setOriginFacilityId((String) fields.get("originFacilityId"));
}

		if(fields.get("destinationFacilityId") != null) {
			returnVal.setDestinationFacilityId((String) fields.get("destinationFacilityId"));
}

		if(fields.get("originContactMechId") != null) {
			returnVal.setOriginContactMechId((String) fields.get("originContactMechId"));
}

		if(fields.get("originTelecomNumberId") != null) {
			returnVal.setOriginTelecomNumberId((String) fields.get("originTelecomNumberId"));
}

		if(fields.get("destinationContactMechId") != null) {
			returnVal.setDestinationContactMechId((String) fields.get("destinationContactMechId"));
}

		if(fields.get("destinationTelecomNumberId") != null) {
			returnVal.setDestinationTelecomNumberId((String) fields.get("destinationTelecomNumberId"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("additionalShippingCharge") != null) {
String buf;
buf = fields.get("additionalShippingCharge");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAdditionalShippingCharge(bd);
}

		if(fields.get("addtlShippingChargeDesc") != null) {
			returnVal.setAddtlShippingChargeDesc((String) fields.get("addtlShippingChargeDesc"));
}

		if(fields.get("createdDate") != null) {
String buf = fields.get("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
String buf = fields.get("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static Shipment map(GenericValue val) {

Shipment returnVal = new Shipment();
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setShipmentTypeId(val.getString("shipmentTypeId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setPrimaryOrderId(val.getString("primaryOrderId"));
		returnVal.setPrimaryReturnId(val.getString("primaryReturnId"));
		returnVal.setPrimaryShipGroupSeqId(val.getString("primaryShipGroupSeqId"));
		returnVal.setPicklistBinId(val.getString("picklistBinId"));
		returnVal.setEstimatedReadyDate(val.getTimestamp("estimatedReadyDate"));
		returnVal.setEstimatedShipDate(val.getTimestamp("estimatedShipDate"));
		returnVal.setEstimatedShipWorkEffId(val.getString("estimatedShipWorkEffId"));
		returnVal.setEstimatedArrivalDate(val.getTimestamp("estimatedArrivalDate"));
		returnVal.setEstimatedArrivalWorkEffId(val.getString("estimatedArrivalWorkEffId"));
		returnVal.setLatestCancelDate(val.getTimestamp("latestCancelDate"));
		returnVal.setEstimatedShipCost(val.getBigDecimal("estimatedShipCost"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setHandlingInstructions(val.getString("handlingInstructions"));
		returnVal.setOriginFacilityId(val.getString("originFacilityId"));
		returnVal.setDestinationFacilityId(val.getString("destinationFacilityId"));
		returnVal.setOriginContactMechId(val.getString("originContactMechId"));
		returnVal.setOriginTelecomNumberId(val.getString("originTelecomNumberId"));
		returnVal.setDestinationContactMechId(val.getString("destinationContactMechId"));
		returnVal.setDestinationTelecomNumberId(val.getString("destinationTelecomNumberId"));
		returnVal.setPartyIdTo(val.getString("partyIdTo"));
		returnVal.setPartyIdFrom(val.getString("partyIdFrom"));
		returnVal.setAdditionalShippingCharge(val.getBigDecimal("additionalShippingCharge"));
		returnVal.setAddtlShippingChargeDesc(val.getString("addtlShippingChargeDesc"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static Shipment map(HttpServletRequest request) throws Exception {

		Shipment returnVal = new Shipment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentId")) {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}

		if(paramMap.containsKey("shipmentTypeId"))  {
returnVal.setShipmentTypeId(request.getParameter("shipmentTypeId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("primaryOrderId"))  {
returnVal.setPrimaryOrderId(request.getParameter("primaryOrderId"));
}
		if(paramMap.containsKey("primaryReturnId"))  {
returnVal.setPrimaryReturnId(request.getParameter("primaryReturnId"));
}
		if(paramMap.containsKey("primaryShipGroupSeqId"))  {
returnVal.setPrimaryShipGroupSeqId(request.getParameter("primaryShipGroupSeqId"));
}
		if(paramMap.containsKey("picklistBinId"))  {
returnVal.setPicklistBinId(request.getParameter("picklistBinId"));
}
		if(paramMap.containsKey("estimatedReadyDate"))  {
String buf = request.getParameter("estimatedReadyDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEstimatedReadyDate(ibuf);
}
		if(paramMap.containsKey("estimatedShipDate"))  {
String buf = request.getParameter("estimatedShipDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEstimatedShipDate(ibuf);
}
		if(paramMap.containsKey("estimatedShipWorkEffId"))  {
returnVal.setEstimatedShipWorkEffId(request.getParameter("estimatedShipWorkEffId"));
}
		if(paramMap.containsKey("estimatedArrivalDate"))  {
String buf = request.getParameter("estimatedArrivalDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEstimatedArrivalDate(ibuf);
}
		if(paramMap.containsKey("estimatedArrivalWorkEffId"))  {
returnVal.setEstimatedArrivalWorkEffId(request.getParameter("estimatedArrivalWorkEffId"));
}
		if(paramMap.containsKey("latestCancelDate"))  {
String buf = request.getParameter("latestCancelDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLatestCancelDate(ibuf);
}
		if(paramMap.containsKey("estimatedShipCost"))  {
String buf = request.getParameter("estimatedShipCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedShipCost(bd);
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("handlingInstructions"))  {
returnVal.setHandlingInstructions(request.getParameter("handlingInstructions"));
}
		if(paramMap.containsKey("originFacilityId"))  {
returnVal.setOriginFacilityId(request.getParameter("originFacilityId"));
}
		if(paramMap.containsKey("destinationFacilityId"))  {
returnVal.setDestinationFacilityId(request.getParameter("destinationFacilityId"));
}
		if(paramMap.containsKey("originContactMechId"))  {
returnVal.setOriginContactMechId(request.getParameter("originContactMechId"));
}
		if(paramMap.containsKey("originTelecomNumberId"))  {
returnVal.setOriginTelecomNumberId(request.getParameter("originTelecomNumberId"));
}
		if(paramMap.containsKey("destinationContactMechId"))  {
returnVal.setDestinationContactMechId(request.getParameter("destinationContactMechId"));
}
		if(paramMap.containsKey("destinationTelecomNumberId"))  {
returnVal.setDestinationTelecomNumberId(request.getParameter("destinationTelecomNumberId"));
}
		if(paramMap.containsKey("partyIdTo"))  {
returnVal.setPartyIdTo(request.getParameter("partyIdTo"));
}
		if(paramMap.containsKey("partyIdFrom"))  {
returnVal.setPartyIdFrom(request.getParameter("partyIdFrom"));
}
		if(paramMap.containsKey("additionalShippingCharge"))  {
String buf = request.getParameter("additionalShippingCharge");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAdditionalShippingCharge(bd);
}
		if(paramMap.containsKey("addtlShippingChargeDesc"))  {
returnVal.setAddtlShippingChargeDesc(request.getParameter("addtlShippingChargeDesc"));
}
		if(paramMap.containsKey("createdDate"))  {
String buf = request.getParameter("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCreatedDate(ibuf);
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedDate"))  {
String buf = request.getParameter("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastModifiedDate(ibuf);
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
return returnVal;

}
}
