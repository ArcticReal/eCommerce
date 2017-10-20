package com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.routeSegment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.routeSegment.ShipmentRouteSegment;

public class ShipmentRouteSegmentMapper  {


	public static Map<String, Object> map(ShipmentRouteSegment shipmentroutesegment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentroutesegment.getShipmentId() != null ){
			returnVal.put("shipmentId",shipmentroutesegment.getShipmentId());
}

		if(shipmentroutesegment.getShipmentRouteSegmentId() != null ){
			returnVal.put("shipmentRouteSegmentId",shipmentroutesegment.getShipmentRouteSegmentId());
}

		if(shipmentroutesegment.getDeliveryId() != null ){
			returnVal.put("deliveryId",shipmentroutesegment.getDeliveryId());
}

		if(shipmentroutesegment.getOriginFacilityId() != null ){
			returnVal.put("originFacilityId",shipmentroutesegment.getOriginFacilityId());
}

		if(shipmentroutesegment.getDestFacilityId() != null ){
			returnVal.put("destFacilityId",shipmentroutesegment.getDestFacilityId());
}

		if(shipmentroutesegment.getOriginContactMechId() != null ){
			returnVal.put("originContactMechId",shipmentroutesegment.getOriginContactMechId());
}

		if(shipmentroutesegment.getOriginTelecomNumberId() != null ){
			returnVal.put("originTelecomNumberId",shipmentroutesegment.getOriginTelecomNumberId());
}

		if(shipmentroutesegment.getDestContactMechId() != null ){
			returnVal.put("destContactMechId",shipmentroutesegment.getDestContactMechId());
}

		if(shipmentroutesegment.getDestTelecomNumberId() != null ){
			returnVal.put("destTelecomNumberId",shipmentroutesegment.getDestTelecomNumberId());
}

		if(shipmentroutesegment.getCarrierPartyId() != null ){
			returnVal.put("carrierPartyId",shipmentroutesegment.getCarrierPartyId());
}

		if(shipmentroutesegment.getShipmentMethodTypeId() != null ){
			returnVal.put("shipmentMethodTypeId",shipmentroutesegment.getShipmentMethodTypeId());
}

		if(shipmentroutesegment.getCarrierServiceStatusId() != null ){
			returnVal.put("carrierServiceStatusId",shipmentroutesegment.getCarrierServiceStatusId());
}

		if(shipmentroutesegment.getCarrierDeliveryZone() != null ){
			returnVal.put("carrierDeliveryZone",shipmentroutesegment.getCarrierDeliveryZone());
}

		if(shipmentroutesegment.getCarrierRestrictionCodes() != null ){
			returnVal.put("carrierRestrictionCodes",shipmentroutesegment.getCarrierRestrictionCodes());
}

		if(shipmentroutesegment.getCarrierRestrictionDesc() != null ){
			returnVal.put("carrierRestrictionDesc",shipmentroutesegment.getCarrierRestrictionDesc());
}

		if(shipmentroutesegment.getBillingWeight() != null ){
			returnVal.put("billingWeight",shipmentroutesegment.getBillingWeight());
}

		if(shipmentroutesegment.getBillingWeightUomId() != null ){
			returnVal.put("billingWeightUomId",shipmentroutesegment.getBillingWeightUomId());
}

		if(shipmentroutesegment.getActualTransportCost() != null ){
			returnVal.put("actualTransportCost",shipmentroutesegment.getActualTransportCost());
}

		if(shipmentroutesegment.getActualServiceCost() != null ){
			returnVal.put("actualServiceCost",shipmentroutesegment.getActualServiceCost());
}

		if(shipmentroutesegment.getActualOtherCost() != null ){
			returnVal.put("actualOtherCost",shipmentroutesegment.getActualOtherCost());
}

		if(shipmentroutesegment.getActualCost() != null ){
			returnVal.put("actualCost",shipmentroutesegment.getActualCost());
}

		if(shipmentroutesegment.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",shipmentroutesegment.getCurrencyUomId());
}

		if(shipmentroutesegment.getActualStartDate() != null ){
			returnVal.put("actualStartDate",shipmentroutesegment.getActualStartDate());
}

		if(shipmentroutesegment.getActualArrivalDate() != null ){
			returnVal.put("actualArrivalDate",shipmentroutesegment.getActualArrivalDate());
}

		if(shipmentroutesegment.getEstimatedStartDate() != null ){
			returnVal.put("estimatedStartDate",shipmentroutesegment.getEstimatedStartDate());
}

		if(shipmentroutesegment.getEstimatedArrivalDate() != null ){
			returnVal.put("estimatedArrivalDate",shipmentroutesegment.getEstimatedArrivalDate());
}

		if(shipmentroutesegment.getTrackingIdNumber() != null ){
			returnVal.put("trackingIdNumber",shipmentroutesegment.getTrackingIdNumber());
}

		if(shipmentroutesegment.getTrackingDigest() != null ){
			returnVal.put("trackingDigest",shipmentroutesegment.getTrackingDigest());
}

		if(shipmentroutesegment.getUpdatedByUserLoginId() != null ){
			returnVal.put("updatedByUserLoginId",shipmentroutesegment.getUpdatedByUserLoginId());
}

		if(shipmentroutesegment.getLastUpdatedDate() != null ){
			returnVal.put("lastUpdatedDate",shipmentroutesegment.getLastUpdatedDate());
}

		if(shipmentroutesegment.getHomeDeliveryType() != null ){
			returnVal.put("homeDeliveryType",shipmentroutesegment.getHomeDeliveryType());
}

		if(shipmentroutesegment.getHomeDeliveryDate() != null ){
			returnVal.put("homeDeliveryDate",shipmentroutesegment.getHomeDeliveryDate());
}

		if(shipmentroutesegment.getThirdPartyAccountNumber() != null ){
			returnVal.put("thirdPartyAccountNumber",shipmentroutesegment.getThirdPartyAccountNumber());
}

		if(shipmentroutesegment.getThirdPartyPostalCode() != null ){
			returnVal.put("thirdPartyPostalCode",shipmentroutesegment.getThirdPartyPostalCode());
}

		if(shipmentroutesegment.getThirdPartyCountryGeoCode() != null ){
			returnVal.put("thirdPartyCountryGeoCode",shipmentroutesegment.getThirdPartyCountryGeoCode());
}

		if(shipmentroutesegment.getUpsHighValueReport() != null ){
			returnVal.put("upsHighValueReport",shipmentroutesegment.getUpsHighValueReport());
}

		return returnVal;
}


	public static ShipmentRouteSegment map(Map<String, Object> fields) {

		ShipmentRouteSegment returnVal = new ShipmentRouteSegment();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentRouteSegmentId") != null) {
			returnVal.setShipmentRouteSegmentId((String) fields.get("shipmentRouteSegmentId"));
}

		if(fields.get("deliveryId") != null) {
			returnVal.setDeliveryId((String) fields.get("deliveryId"));
}

		if(fields.get("originFacilityId") != null) {
			returnVal.setOriginFacilityId((String) fields.get("originFacilityId"));
}

		if(fields.get("destFacilityId") != null) {
			returnVal.setDestFacilityId((String) fields.get("destFacilityId"));
}

		if(fields.get("originContactMechId") != null) {
			returnVal.setOriginContactMechId((String) fields.get("originContactMechId"));
}

		if(fields.get("originTelecomNumberId") != null) {
			returnVal.setOriginTelecomNumberId((String) fields.get("originTelecomNumberId"));
}

		if(fields.get("destContactMechId") != null) {
			returnVal.setDestContactMechId((String) fields.get("destContactMechId"));
}

		if(fields.get("destTelecomNumberId") != null) {
			returnVal.setDestTelecomNumberId((String) fields.get("destTelecomNumberId"));
}

		if(fields.get("carrierPartyId") != null) {
			returnVal.setCarrierPartyId((String) fields.get("carrierPartyId"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("carrierServiceStatusId") != null) {
			returnVal.setCarrierServiceStatusId((String) fields.get("carrierServiceStatusId"));
}

		if(fields.get("carrierDeliveryZone") != null) {
			returnVal.setCarrierDeliveryZone((String) fields.get("carrierDeliveryZone"));
}

		if(fields.get("carrierRestrictionCodes") != null) {
			returnVal.setCarrierRestrictionCodes((String) fields.get("carrierRestrictionCodes"));
}

		if(fields.get("carrierRestrictionDesc") != null) {
			returnVal.setCarrierRestrictionDesc((String) fields.get("carrierRestrictionDesc"));
}

		if(fields.get("billingWeight") != null) {
			returnVal.setBillingWeight((BigDecimal) fields.get("billingWeight"));
}

		if(fields.get("billingWeightUomId") != null) {
			returnVal.setBillingWeightUomId((String) fields.get("billingWeightUomId"));
}

		if(fields.get("actualTransportCost") != null) {
			returnVal.setActualTransportCost((BigDecimal) fields.get("actualTransportCost"));
}

		if(fields.get("actualServiceCost") != null) {
			returnVal.setActualServiceCost((BigDecimal) fields.get("actualServiceCost"));
}

		if(fields.get("actualOtherCost") != null) {
			returnVal.setActualOtherCost((BigDecimal) fields.get("actualOtherCost"));
}

		if(fields.get("actualCost") != null) {
			returnVal.setActualCost((BigDecimal) fields.get("actualCost"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("actualStartDate") != null) {
			returnVal.setActualStartDate((Timestamp) fields.get("actualStartDate"));
}

		if(fields.get("actualArrivalDate") != null) {
			returnVal.setActualArrivalDate((Timestamp) fields.get("actualArrivalDate"));
}

		if(fields.get("estimatedStartDate") != null) {
			returnVal.setEstimatedStartDate((Timestamp) fields.get("estimatedStartDate"));
}

		if(fields.get("estimatedArrivalDate") != null) {
			returnVal.setEstimatedArrivalDate((Timestamp) fields.get("estimatedArrivalDate"));
}

		if(fields.get("trackingIdNumber") != null) {
			returnVal.setTrackingIdNumber((String) fields.get("trackingIdNumber"));
}

		if(fields.get("trackingDigest") != null) {
			returnVal.setTrackingDigest((String) fields.get("trackingDigest"));
}

		if(fields.get("updatedByUserLoginId") != null) {
			returnVal.setUpdatedByUserLoginId((String) fields.get("updatedByUserLoginId"));
}

		if(fields.get("lastUpdatedDate") != null) {
			returnVal.setLastUpdatedDate((Timestamp) fields.get("lastUpdatedDate"));
}

		if(fields.get("homeDeliveryType") != null) {
			returnVal.setHomeDeliveryType((String) fields.get("homeDeliveryType"));
}

		if(fields.get("homeDeliveryDate") != null) {
			returnVal.setHomeDeliveryDate((Timestamp) fields.get("homeDeliveryDate"));
}

		if(fields.get("thirdPartyAccountNumber") != null) {
			returnVal.setThirdPartyAccountNumber((String) fields.get("thirdPartyAccountNumber"));
}

		if(fields.get("thirdPartyPostalCode") != null) {
			returnVal.setThirdPartyPostalCode((String) fields.get("thirdPartyPostalCode"));
}

		if(fields.get("thirdPartyCountryGeoCode") != null) {
			returnVal.setThirdPartyCountryGeoCode((String) fields.get("thirdPartyCountryGeoCode"));
}

		if(fields.get("upsHighValueReport") != null) {
			returnVal.setUpsHighValueReport((byte[]) fields.get("upsHighValueReport"));
}


		return returnVal;
 } 
	public static ShipmentRouteSegment mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentRouteSegment returnVal = new ShipmentRouteSegment();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentRouteSegmentId") != null) {
			returnVal.setShipmentRouteSegmentId((String) fields.get("shipmentRouteSegmentId"));
}

		if(fields.get("deliveryId") != null) {
			returnVal.setDeliveryId((String) fields.get("deliveryId"));
}

		if(fields.get("originFacilityId") != null) {
			returnVal.setOriginFacilityId((String) fields.get("originFacilityId"));
}

		if(fields.get("destFacilityId") != null) {
			returnVal.setDestFacilityId((String) fields.get("destFacilityId"));
}

		if(fields.get("originContactMechId") != null) {
			returnVal.setOriginContactMechId((String) fields.get("originContactMechId"));
}

		if(fields.get("originTelecomNumberId") != null) {
			returnVal.setOriginTelecomNumberId((String) fields.get("originTelecomNumberId"));
}

		if(fields.get("destContactMechId") != null) {
			returnVal.setDestContactMechId((String) fields.get("destContactMechId"));
}

		if(fields.get("destTelecomNumberId") != null) {
			returnVal.setDestTelecomNumberId((String) fields.get("destTelecomNumberId"));
}

		if(fields.get("carrierPartyId") != null) {
			returnVal.setCarrierPartyId((String) fields.get("carrierPartyId"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("carrierServiceStatusId") != null) {
			returnVal.setCarrierServiceStatusId((String) fields.get("carrierServiceStatusId"));
}

		if(fields.get("carrierDeliveryZone") != null) {
			returnVal.setCarrierDeliveryZone((String) fields.get("carrierDeliveryZone"));
}

		if(fields.get("carrierRestrictionCodes") != null) {
			returnVal.setCarrierRestrictionCodes((String) fields.get("carrierRestrictionCodes"));
}

		if(fields.get("carrierRestrictionDesc") != null) {
			returnVal.setCarrierRestrictionDesc((String) fields.get("carrierRestrictionDesc"));
}

		if(fields.get("billingWeight") != null) {
String buf;
buf = fields.get("billingWeight");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBillingWeight(bd);
}

		if(fields.get("billingWeightUomId") != null) {
			returnVal.setBillingWeightUomId((String) fields.get("billingWeightUomId"));
}

		if(fields.get("actualTransportCost") != null) {
String buf;
buf = fields.get("actualTransportCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualTransportCost(bd);
}

		if(fields.get("actualServiceCost") != null) {
String buf;
buf = fields.get("actualServiceCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualServiceCost(bd);
}

		if(fields.get("actualOtherCost") != null) {
String buf;
buf = fields.get("actualOtherCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualOtherCost(bd);
}

		if(fields.get("actualCost") != null) {
String buf;
buf = fields.get("actualCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualCost(bd);
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("actualStartDate") != null) {
String buf = fields.get("actualStartDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setActualStartDate(ibuf);
}

		if(fields.get("actualArrivalDate") != null) {
String buf = fields.get("actualArrivalDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setActualArrivalDate(ibuf);
}

		if(fields.get("estimatedStartDate") != null) {
String buf = fields.get("estimatedStartDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedStartDate(ibuf);
}

		if(fields.get("estimatedArrivalDate") != null) {
String buf = fields.get("estimatedArrivalDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedArrivalDate(ibuf);
}

		if(fields.get("trackingIdNumber") != null) {
			returnVal.setTrackingIdNumber((String) fields.get("trackingIdNumber"));
}

		if(fields.get("trackingDigest") != null) {
			returnVal.setTrackingDigest((String) fields.get("trackingDigest"));
}

		if(fields.get("updatedByUserLoginId") != null) {
			returnVal.setUpdatedByUserLoginId((String) fields.get("updatedByUserLoginId"));
}

		if(fields.get("lastUpdatedDate") != null) {
String buf = fields.get("lastUpdatedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastUpdatedDate(ibuf);
}

		if(fields.get("homeDeliveryType") != null) {
			returnVal.setHomeDeliveryType((String) fields.get("homeDeliveryType"));
}

		if(fields.get("homeDeliveryDate") != null) {
String buf = fields.get("homeDeliveryDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setHomeDeliveryDate(ibuf);
}

		if(fields.get("thirdPartyAccountNumber") != null) {
			returnVal.setThirdPartyAccountNumber((String) fields.get("thirdPartyAccountNumber"));
}

		if(fields.get("thirdPartyPostalCode") != null) {
			returnVal.setThirdPartyPostalCode((String) fields.get("thirdPartyPostalCode"));
}

		if(fields.get("thirdPartyCountryGeoCode") != null) {
			returnVal.setThirdPartyCountryGeoCode((String) fields.get("thirdPartyCountryGeoCode"));
}

		if(fields.get("upsHighValueReport") != null) {
String buf = fields.get("upsHighValueReport");
byte[] ibuf = buf.getBytes();
			returnVal.setUpsHighValueReport(ibuf);
}


		return returnVal;
 } 
	public static ShipmentRouteSegment map(GenericValue val) {

ShipmentRouteSegment returnVal = new ShipmentRouteSegment();
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setShipmentRouteSegmentId(val.getString("shipmentRouteSegmentId"));
		returnVal.setDeliveryId(val.getString("deliveryId"));
		returnVal.setOriginFacilityId(val.getString("originFacilityId"));
		returnVal.setDestFacilityId(val.getString("destFacilityId"));
		returnVal.setOriginContactMechId(val.getString("originContactMechId"));
		returnVal.setOriginTelecomNumberId(val.getString("originTelecomNumberId"));
		returnVal.setDestContactMechId(val.getString("destContactMechId"));
		returnVal.setDestTelecomNumberId(val.getString("destTelecomNumberId"));
		returnVal.setCarrierPartyId(val.getString("carrierPartyId"));
		returnVal.setShipmentMethodTypeId(val.getString("shipmentMethodTypeId"));
		returnVal.setCarrierServiceStatusId(val.getString("carrierServiceStatusId"));
		returnVal.setCarrierDeliveryZone(val.getString("carrierDeliveryZone"));
		returnVal.setCarrierRestrictionCodes(val.getString("carrierRestrictionCodes"));
		returnVal.setCarrierRestrictionDesc(val.getString("carrierRestrictionDesc"));
		returnVal.setBillingWeight(val.getBigDecimal("billingWeight"));
		returnVal.setBillingWeightUomId(val.getString("billingWeightUomId"));
		returnVal.setActualTransportCost(val.getBigDecimal("actualTransportCost"));
		returnVal.setActualServiceCost(val.getBigDecimal("actualServiceCost"));
		returnVal.setActualOtherCost(val.getBigDecimal("actualOtherCost"));
		returnVal.setActualCost(val.getBigDecimal("actualCost"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setActualStartDate(val.getTimestamp("actualStartDate"));
		returnVal.setActualArrivalDate(val.getTimestamp("actualArrivalDate"));
		returnVal.setEstimatedStartDate(val.getTimestamp("estimatedStartDate"));
		returnVal.setEstimatedArrivalDate(val.getTimestamp("estimatedArrivalDate"));
		returnVal.setTrackingIdNumber(val.getString("trackingIdNumber"));
		returnVal.setTrackingDigest(val.getString("trackingDigest"));
		returnVal.setUpdatedByUserLoginId(val.getString("updatedByUserLoginId"));
		returnVal.setLastUpdatedDate(val.getTimestamp("lastUpdatedDate"));
		returnVal.setHomeDeliveryType(val.getString("homeDeliveryType"));
		returnVal.setHomeDeliveryDate(val.getTimestamp("homeDeliveryDate"));
		returnVal.setThirdPartyAccountNumber(val.getString("thirdPartyAccountNumber"));
		returnVal.setThirdPartyPostalCode(val.getString("thirdPartyPostalCode"));
		returnVal.setThirdPartyCountryGeoCode(val.getString("thirdPartyCountryGeoCode"));
		returnVal.setUpsHighValueReport(val.getBytes("upsHighValueReport"));


return returnVal;

}

public static ShipmentRouteSegment map(HttpServletRequest request) throws Exception {

		ShipmentRouteSegment returnVal = new ShipmentRouteSegment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentId")) {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}

		if(paramMap.containsKey("shipmentRouteSegmentId"))  {
returnVal.setShipmentRouteSegmentId(request.getParameter("shipmentRouteSegmentId"));
}
		if(paramMap.containsKey("deliveryId"))  {
returnVal.setDeliveryId(request.getParameter("deliveryId"));
}
		if(paramMap.containsKey("originFacilityId"))  {
returnVal.setOriginFacilityId(request.getParameter("originFacilityId"));
}
		if(paramMap.containsKey("destFacilityId"))  {
returnVal.setDestFacilityId(request.getParameter("destFacilityId"));
}
		if(paramMap.containsKey("originContactMechId"))  {
returnVal.setOriginContactMechId(request.getParameter("originContactMechId"));
}
		if(paramMap.containsKey("originTelecomNumberId"))  {
returnVal.setOriginTelecomNumberId(request.getParameter("originTelecomNumberId"));
}
		if(paramMap.containsKey("destContactMechId"))  {
returnVal.setDestContactMechId(request.getParameter("destContactMechId"));
}
		if(paramMap.containsKey("destTelecomNumberId"))  {
returnVal.setDestTelecomNumberId(request.getParameter("destTelecomNumberId"));
}
		if(paramMap.containsKey("carrierPartyId"))  {
returnVal.setCarrierPartyId(request.getParameter("carrierPartyId"));
}
		if(paramMap.containsKey("shipmentMethodTypeId"))  {
returnVal.setShipmentMethodTypeId(request.getParameter("shipmentMethodTypeId"));
}
		if(paramMap.containsKey("carrierServiceStatusId"))  {
returnVal.setCarrierServiceStatusId(request.getParameter("carrierServiceStatusId"));
}
		if(paramMap.containsKey("carrierDeliveryZone"))  {
returnVal.setCarrierDeliveryZone(request.getParameter("carrierDeliveryZone"));
}
		if(paramMap.containsKey("carrierRestrictionCodes"))  {
returnVal.setCarrierRestrictionCodes(request.getParameter("carrierRestrictionCodes"));
}
		if(paramMap.containsKey("carrierRestrictionDesc"))  {
returnVal.setCarrierRestrictionDesc(request.getParameter("carrierRestrictionDesc"));
}
		if(paramMap.containsKey("billingWeight"))  {
String buf = request.getParameter("billingWeight");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBillingWeight(bd);
}
		if(paramMap.containsKey("billingWeightUomId"))  {
returnVal.setBillingWeightUomId(request.getParameter("billingWeightUomId"));
}
		if(paramMap.containsKey("actualTransportCost"))  {
String buf = request.getParameter("actualTransportCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualTransportCost(bd);
}
		if(paramMap.containsKey("actualServiceCost"))  {
String buf = request.getParameter("actualServiceCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualServiceCost(bd);
}
		if(paramMap.containsKey("actualOtherCost"))  {
String buf = request.getParameter("actualOtherCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualOtherCost(bd);
}
		if(paramMap.containsKey("actualCost"))  {
String buf = request.getParameter("actualCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualCost(bd);
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("actualStartDate"))  {
String buf = request.getParameter("actualStartDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setActualStartDate(ibuf);
}
		if(paramMap.containsKey("actualArrivalDate"))  {
String buf = request.getParameter("actualArrivalDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setActualArrivalDate(ibuf);
}
		if(paramMap.containsKey("estimatedStartDate"))  {
String buf = request.getParameter("estimatedStartDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEstimatedStartDate(ibuf);
}
		if(paramMap.containsKey("estimatedArrivalDate"))  {
String buf = request.getParameter("estimatedArrivalDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEstimatedArrivalDate(ibuf);
}
		if(paramMap.containsKey("trackingIdNumber"))  {
returnVal.setTrackingIdNumber(request.getParameter("trackingIdNumber"));
}
		if(paramMap.containsKey("trackingDigest"))  {
returnVal.setTrackingDigest(request.getParameter("trackingDigest"));
}
		if(paramMap.containsKey("updatedByUserLoginId"))  {
returnVal.setUpdatedByUserLoginId(request.getParameter("updatedByUserLoginId"));
}
		if(paramMap.containsKey("lastUpdatedDate"))  {
String buf = request.getParameter("lastUpdatedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastUpdatedDate(ibuf);
}
		if(paramMap.containsKey("homeDeliveryType"))  {
returnVal.setHomeDeliveryType(request.getParameter("homeDeliveryType"));
}
		if(paramMap.containsKey("homeDeliveryDate"))  {
String buf = request.getParameter("homeDeliveryDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setHomeDeliveryDate(ibuf);
}
		if(paramMap.containsKey("thirdPartyAccountNumber"))  {
returnVal.setThirdPartyAccountNumber(request.getParameter("thirdPartyAccountNumber"));
}
		if(paramMap.containsKey("thirdPartyPostalCode"))  {
returnVal.setThirdPartyPostalCode(request.getParameter("thirdPartyPostalCode"));
}
		if(paramMap.containsKey("thirdPartyCountryGeoCode"))  {
returnVal.setThirdPartyCountryGeoCode(request.getParameter("thirdPartyCountryGeoCode"));
}
		if(paramMap.containsKey("upsHighValueReport"))  {
String buf = request.getParameter("upsHighValueReport");
byte[] ibuf = buf.getBytes();
returnVal.setUpsHighValueReport(ibuf);
}
return returnVal;

}
}
