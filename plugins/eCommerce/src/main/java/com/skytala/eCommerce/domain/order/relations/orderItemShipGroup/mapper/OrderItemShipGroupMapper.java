package com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.model.OrderItemShipGroup;

public class OrderItemShipGroupMapper  {


	public static Map<String, Object> map(OrderItemShipGroup orderitemshipgroup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderitemshipgroup.getOrderId() != null ){
			returnVal.put("orderId",orderitemshipgroup.getOrderId());
}

		if(orderitemshipgroup.getShipGroupSeqId() != null ){
			returnVal.put("shipGroupSeqId",orderitemshipgroup.getShipGroupSeqId());
}

		if(orderitemshipgroup.getShipmentMethodTypeId() != null ){
			returnVal.put("shipmentMethodTypeId",orderitemshipgroup.getShipmentMethodTypeId());
}

		if(orderitemshipgroup.getSupplierPartyId() != null ){
			returnVal.put("supplierPartyId",orderitemshipgroup.getSupplierPartyId());
}

		if(orderitemshipgroup.getVendorPartyId() != null ){
			returnVal.put("vendorPartyId",orderitemshipgroup.getVendorPartyId());
}

		if(orderitemshipgroup.getCarrierPartyId() != null ){
			returnVal.put("carrierPartyId",orderitemshipgroup.getCarrierPartyId());
}

		if(orderitemshipgroup.getCarrierRoleTypeId() != null ){
			returnVal.put("carrierRoleTypeId",orderitemshipgroup.getCarrierRoleTypeId());
}

		if(orderitemshipgroup.getFacilityId() != null ){
			returnVal.put("facilityId",orderitemshipgroup.getFacilityId());
}

		if(orderitemshipgroup.getContactMechId() != null ){
			returnVal.put("contactMechId",orderitemshipgroup.getContactMechId());
}

		if(orderitemshipgroup.getTelecomContactMechId() != null ){
			returnVal.put("telecomContactMechId",orderitemshipgroup.getTelecomContactMechId());
}

		if(orderitemshipgroup.getTrackingNumber() != null ){
			returnVal.put("trackingNumber",orderitemshipgroup.getTrackingNumber());
}

		if(orderitemshipgroup.getShippingInstructions() != null ){
			returnVal.put("shippingInstructions",orderitemshipgroup.getShippingInstructions());
}

		if(orderitemshipgroup.getMaySplit() != null ){
			returnVal.put("maySplit",orderitemshipgroup.getMaySplit());
}

		if(orderitemshipgroup.getGiftMessage() != null ){
			returnVal.put("giftMessage",orderitemshipgroup.getGiftMessage());
}

		if(orderitemshipgroup.getIsGift() != null ){
			returnVal.put("isGift",orderitemshipgroup.getIsGift());
}

		if(orderitemshipgroup.getShipAfterDate() != null ){
			returnVal.put("shipAfterDate",orderitemshipgroup.getShipAfterDate());
}

		if(orderitemshipgroup.getShipByDate() != null ){
			returnVal.put("shipByDate",orderitemshipgroup.getShipByDate());
}

		if(orderitemshipgroup.getEstimatedShipDate() != null ){
			returnVal.put("estimatedShipDate",orderitemshipgroup.getEstimatedShipDate());
}

		if(orderitemshipgroup.getEstimatedDeliveryDate() != null ){
			returnVal.put("estimatedDeliveryDate",orderitemshipgroup.getEstimatedDeliveryDate());
}

		return returnVal;
}


	public static OrderItemShipGroup map(Map<String, Object> fields) {

		OrderItemShipGroup returnVal = new OrderItemShipGroup();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("supplierPartyId") != null) {
			returnVal.setSupplierPartyId((String) fields.get("supplierPartyId"));
}

		if(fields.get("vendorPartyId") != null) {
			returnVal.setVendorPartyId((String) fields.get("vendorPartyId"));
}

		if(fields.get("carrierPartyId") != null) {
			returnVal.setCarrierPartyId((String) fields.get("carrierPartyId"));
}

		if(fields.get("carrierRoleTypeId") != null) {
			returnVal.setCarrierRoleTypeId((String) fields.get("carrierRoleTypeId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("telecomContactMechId") != null) {
			returnVal.setTelecomContactMechId((String) fields.get("telecomContactMechId"));
}

		if(fields.get("trackingNumber") != null) {
			returnVal.setTrackingNumber((String) fields.get("trackingNumber"));
}

		if(fields.get("shippingInstructions") != null) {
			returnVal.setShippingInstructions((String) fields.get("shippingInstructions"));
}

		if(fields.get("maySplit") != null) {
			returnVal.setMaySplit((boolean) fields.get("maySplit"));
}

		if(fields.get("giftMessage") != null) {
			returnVal.setGiftMessage((String) fields.get("giftMessage"));
}

		if(fields.get("isGift") != null) {
			returnVal.setIsGift((boolean) fields.get("isGift"));
}

		if(fields.get("shipAfterDate") != null) {
			returnVal.setShipAfterDate((Timestamp) fields.get("shipAfterDate"));
}

		if(fields.get("shipByDate") != null) {
			returnVal.setShipByDate((Timestamp) fields.get("shipByDate"));
}

		if(fields.get("estimatedShipDate") != null) {
			returnVal.setEstimatedShipDate((Timestamp) fields.get("estimatedShipDate"));
}

		if(fields.get("estimatedDeliveryDate") != null) {
			returnVal.setEstimatedDeliveryDate((Timestamp) fields.get("estimatedDeliveryDate"));
}


		return returnVal;
 } 
	public static OrderItemShipGroup mapstrstr(Map<String, String> fields) throws Exception {

		OrderItemShipGroup returnVal = new OrderItemShipGroup();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("supplierPartyId") != null) {
			returnVal.setSupplierPartyId((String) fields.get("supplierPartyId"));
}

		if(fields.get("vendorPartyId") != null) {
			returnVal.setVendorPartyId((String) fields.get("vendorPartyId"));
}

		if(fields.get("carrierPartyId") != null) {
			returnVal.setCarrierPartyId((String) fields.get("carrierPartyId"));
}

		if(fields.get("carrierRoleTypeId") != null) {
			returnVal.setCarrierRoleTypeId((String) fields.get("carrierRoleTypeId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("telecomContactMechId") != null) {
			returnVal.setTelecomContactMechId((String) fields.get("telecomContactMechId"));
}

		if(fields.get("trackingNumber") != null) {
			returnVal.setTrackingNumber((String) fields.get("trackingNumber"));
}

		if(fields.get("shippingInstructions") != null) {
			returnVal.setShippingInstructions((String) fields.get("shippingInstructions"));
}

		if(fields.get("maySplit") != null) {
String buf;
buf = fields.get("maySplit");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setMaySplit(ibuf);
}

		if(fields.get("giftMessage") != null) {
			returnVal.setGiftMessage((String) fields.get("giftMessage"));
}

		if(fields.get("isGift") != null) {
String buf;
buf = fields.get("isGift");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsGift(ibuf);
}

		if(fields.get("shipAfterDate") != null) {
String buf = fields.get("shipAfterDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setShipAfterDate(ibuf);
}

		if(fields.get("shipByDate") != null) {
String buf = fields.get("shipByDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setShipByDate(ibuf);
}

		if(fields.get("estimatedShipDate") != null) {
String buf = fields.get("estimatedShipDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedShipDate(ibuf);
}

		if(fields.get("estimatedDeliveryDate") != null) {
String buf = fields.get("estimatedDeliveryDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedDeliveryDate(ibuf);
}


		return returnVal;
 } 
	public static OrderItemShipGroup map(GenericValue val) {

OrderItemShipGroup returnVal = new OrderItemShipGroup();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setShipGroupSeqId(val.getString("shipGroupSeqId"));
		returnVal.setShipmentMethodTypeId(val.getString("shipmentMethodTypeId"));
		returnVal.setSupplierPartyId(val.getString("supplierPartyId"));
		returnVal.setVendorPartyId(val.getString("vendorPartyId"));
		returnVal.setCarrierPartyId(val.getString("carrierPartyId"));
		returnVal.setCarrierRoleTypeId(val.getString("carrierRoleTypeId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setTelecomContactMechId(val.getString("telecomContactMechId"));
		returnVal.setTrackingNumber(val.getString("trackingNumber"));
		returnVal.setShippingInstructions(val.getString("shippingInstructions"));
		returnVal.setMaySplit(val.getBoolean("maySplit"));
		returnVal.setGiftMessage(val.getString("giftMessage"));
		returnVal.setIsGift(val.getBoolean("isGift"));
		returnVal.setShipAfterDate(val.getTimestamp("shipAfterDate"));
		returnVal.setShipByDate(val.getTimestamp("shipByDate"));
		returnVal.setEstimatedShipDate(val.getTimestamp("estimatedShipDate"));
		returnVal.setEstimatedDeliveryDate(val.getTimestamp("estimatedDeliveryDate"));


return returnVal;

}

public static OrderItemShipGroup map(HttpServletRequest request) throws Exception {

		OrderItemShipGroup returnVal = new OrderItemShipGroup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("shipGroupSeqId"))  {
returnVal.setShipGroupSeqId(request.getParameter("shipGroupSeqId"));
}
		if(paramMap.containsKey("shipmentMethodTypeId"))  {
returnVal.setShipmentMethodTypeId(request.getParameter("shipmentMethodTypeId"));
}
		if(paramMap.containsKey("supplierPartyId"))  {
returnVal.setSupplierPartyId(request.getParameter("supplierPartyId"));
}
		if(paramMap.containsKey("vendorPartyId"))  {
returnVal.setVendorPartyId(request.getParameter("vendorPartyId"));
}
		if(paramMap.containsKey("carrierPartyId"))  {
returnVal.setCarrierPartyId(request.getParameter("carrierPartyId"));
}
		if(paramMap.containsKey("carrierRoleTypeId"))  {
returnVal.setCarrierRoleTypeId(request.getParameter("carrierRoleTypeId"));
}
		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
}
		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
		if(paramMap.containsKey("telecomContactMechId"))  {
returnVal.setTelecomContactMechId(request.getParameter("telecomContactMechId"));
}
		if(paramMap.containsKey("trackingNumber"))  {
returnVal.setTrackingNumber(request.getParameter("trackingNumber"));
}
		if(paramMap.containsKey("shippingInstructions"))  {
returnVal.setShippingInstructions(request.getParameter("shippingInstructions"));
}
		if(paramMap.containsKey("maySplit"))  {
String buf = request.getParameter("maySplit");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setMaySplit(ibuf);
}
		if(paramMap.containsKey("giftMessage"))  {
returnVal.setGiftMessage(request.getParameter("giftMessage"));
}
		if(paramMap.containsKey("isGift"))  {
String buf = request.getParameter("isGift");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsGift(ibuf);
}
		if(paramMap.containsKey("shipAfterDate"))  {
String buf = request.getParameter("shipAfterDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setShipAfterDate(ibuf);
}
		if(paramMap.containsKey("shipByDate"))  {
String buf = request.getParameter("shipByDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setShipByDate(ibuf);
}
		if(paramMap.containsKey("estimatedShipDate"))  {
String buf = request.getParameter("estimatedShipDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEstimatedShipDate(ibuf);
}
		if(paramMap.containsKey("estimatedDeliveryDate"))  {
String buf = request.getParameter("estimatedDeliveryDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEstimatedDeliveryDate(ibuf);
}
return returnVal;

}
}
