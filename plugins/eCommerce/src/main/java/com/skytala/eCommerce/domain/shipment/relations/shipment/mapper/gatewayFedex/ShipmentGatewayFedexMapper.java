package com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayFedex;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayFedex.ShipmentGatewayFedex;

public class ShipmentGatewayFedexMapper  {


	public static Map<String, Object> map(ShipmentGatewayFedex shipmentgatewayfedex) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentgatewayfedex.getShipmentGatewayConfigId() != null ){
			returnVal.put("shipmentGatewayConfigId",shipmentgatewayfedex.getShipmentGatewayConfigId());
}

		if(shipmentgatewayfedex.getConnectUrl() != null ){
			returnVal.put("connectUrl",shipmentgatewayfedex.getConnectUrl());
}

		if(shipmentgatewayfedex.getConnectSoapUrl() != null ){
			returnVal.put("connectSoapUrl",shipmentgatewayfedex.getConnectSoapUrl());
}

		if(shipmentgatewayfedex.getConnectTimeout() != null ){
			returnVal.put("connectTimeout",shipmentgatewayfedex.getConnectTimeout());
}

		if(shipmentgatewayfedex.getAccessAccountNbr() != null ){
			returnVal.put("accessAccountNbr",shipmentgatewayfedex.getAccessAccountNbr());
}

		if(shipmentgatewayfedex.getAccessMeterNumber() != null ){
			returnVal.put("accessMeterNumber",shipmentgatewayfedex.getAccessMeterNumber());
}

		if(shipmentgatewayfedex.getAccessUserKey() != null ){
			returnVal.put("accessUserKey",shipmentgatewayfedex.getAccessUserKey());
}

		if(shipmentgatewayfedex.getAccessUserPwd() != null ){
			returnVal.put("accessUserPwd",shipmentgatewayfedex.getAccessUserPwd());
}

		if(shipmentgatewayfedex.getLabelImageType() != null ){
			returnVal.put("labelImageType",shipmentgatewayfedex.getLabelImageType());
}

		if(shipmentgatewayfedex.getDefaultDropoffType() != null ){
			returnVal.put("defaultDropoffType",shipmentgatewayfedex.getDefaultDropoffType());
}

		if(shipmentgatewayfedex.getDefaultPackagingType() != null ){
			returnVal.put("defaultPackagingType",shipmentgatewayfedex.getDefaultPackagingType());
}

		if(shipmentgatewayfedex.getTemplateShipment() != null ){
			returnVal.put("templateShipment",shipmentgatewayfedex.getTemplateShipment());
}

		if(shipmentgatewayfedex.getTemplateSubscription() != null ){
			returnVal.put("templateSubscription",shipmentgatewayfedex.getTemplateSubscription());
}

		if(shipmentgatewayfedex.getRateEstimateTemplate() != null ){
			returnVal.put("rateEstimateTemplate",shipmentgatewayfedex.getRateEstimateTemplate());
}

		return returnVal;
}


	public static ShipmentGatewayFedex map(Map<String, Object> fields) {

		ShipmentGatewayFedex returnVal = new ShipmentGatewayFedex();

		if(fields.get("shipmentGatewayConfigId") != null) {
			returnVal.setShipmentGatewayConfigId((String) fields.get("shipmentGatewayConfigId"));
}

		if(fields.get("connectUrl") != null) {
			returnVal.setConnectUrl((String) fields.get("connectUrl"));
}

		if(fields.get("connectSoapUrl") != null) {
			returnVal.setConnectSoapUrl((String) fields.get("connectSoapUrl"));
}

		if(fields.get("connectTimeout") != null) {
			returnVal.setConnectTimeout((long) fields.get("connectTimeout"));
}

		if(fields.get("accessAccountNbr") != null) {
			returnVal.setAccessAccountNbr((String) fields.get("accessAccountNbr"));
}

		if(fields.get("accessMeterNumber") != null) {
			returnVal.setAccessMeterNumber((String) fields.get("accessMeterNumber"));
}

		if(fields.get("accessUserKey") != null) {
			returnVal.setAccessUserKey((String) fields.get("accessUserKey"));
}

		if(fields.get("accessUserPwd") != null) {
			returnVal.setAccessUserPwd((String) fields.get("accessUserPwd"));
}

		if(fields.get("labelImageType") != null) {
			returnVal.setLabelImageType((String) fields.get("labelImageType"));
}

		if(fields.get("defaultDropoffType") != null) {
			returnVal.setDefaultDropoffType((String) fields.get("defaultDropoffType"));
}

		if(fields.get("defaultPackagingType") != null) {
			returnVal.setDefaultPackagingType((String) fields.get("defaultPackagingType"));
}

		if(fields.get("templateShipment") != null) {
			returnVal.setTemplateShipment((String) fields.get("templateShipment"));
}

		if(fields.get("templateSubscription") != null) {
			returnVal.setTemplateSubscription((String) fields.get("templateSubscription"));
}

		if(fields.get("rateEstimateTemplate") != null) {
			returnVal.setRateEstimateTemplate((String) fields.get("rateEstimateTemplate"));
}


		return returnVal;
 } 
	public static ShipmentGatewayFedex mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentGatewayFedex returnVal = new ShipmentGatewayFedex();

		if(fields.get("shipmentGatewayConfigId") != null) {
			returnVal.setShipmentGatewayConfigId((String) fields.get("shipmentGatewayConfigId"));
}

		if(fields.get("connectUrl") != null) {
			returnVal.setConnectUrl((String) fields.get("connectUrl"));
}

		if(fields.get("connectSoapUrl") != null) {
			returnVal.setConnectSoapUrl((String) fields.get("connectSoapUrl"));
}

		if(fields.get("connectTimeout") != null) {
String buf;
buf = fields.get("connectTimeout");
long ibuf = Long.parseLong(buf);
			returnVal.setConnectTimeout(ibuf);
}

		if(fields.get("accessAccountNbr") != null) {
			returnVal.setAccessAccountNbr((String) fields.get("accessAccountNbr"));
}

		if(fields.get("accessMeterNumber") != null) {
			returnVal.setAccessMeterNumber((String) fields.get("accessMeterNumber"));
}

		if(fields.get("accessUserKey") != null) {
			returnVal.setAccessUserKey((String) fields.get("accessUserKey"));
}

		if(fields.get("accessUserPwd") != null) {
			returnVal.setAccessUserPwd((String) fields.get("accessUserPwd"));
}

		if(fields.get("labelImageType") != null) {
			returnVal.setLabelImageType((String) fields.get("labelImageType"));
}

		if(fields.get("defaultDropoffType") != null) {
			returnVal.setDefaultDropoffType((String) fields.get("defaultDropoffType"));
}

		if(fields.get("defaultPackagingType") != null) {
			returnVal.setDefaultPackagingType((String) fields.get("defaultPackagingType"));
}

		if(fields.get("templateShipment") != null) {
			returnVal.setTemplateShipment((String) fields.get("templateShipment"));
}

		if(fields.get("templateSubscription") != null) {
			returnVal.setTemplateSubscription((String) fields.get("templateSubscription"));
}

		if(fields.get("rateEstimateTemplate") != null) {
			returnVal.setRateEstimateTemplate((String) fields.get("rateEstimateTemplate"));
}


		return returnVal;
 } 
	public static ShipmentGatewayFedex map(GenericValue val) {

ShipmentGatewayFedex returnVal = new ShipmentGatewayFedex();
		returnVal.setShipmentGatewayConfigId(val.getString("shipmentGatewayConfigId"));
		returnVal.setConnectUrl(val.getString("connectUrl"));
		returnVal.setConnectSoapUrl(val.getString("connectSoapUrl"));
		returnVal.setConnectTimeout(val.getLong("connectTimeout"));
		returnVal.setAccessAccountNbr(val.getString("accessAccountNbr"));
		returnVal.setAccessMeterNumber(val.getString("accessMeterNumber"));
		returnVal.setAccessUserKey(val.getString("accessUserKey"));
		returnVal.setAccessUserPwd(val.getString("accessUserPwd"));
		returnVal.setLabelImageType(val.getString("labelImageType"));
		returnVal.setDefaultDropoffType(val.getString("defaultDropoffType"));
		returnVal.setDefaultPackagingType(val.getString("defaultPackagingType"));
		returnVal.setTemplateShipment(val.getString("templateShipment"));
		returnVal.setTemplateSubscription(val.getString("templateSubscription"));
		returnVal.setRateEstimateTemplate(val.getString("rateEstimateTemplate"));


return returnVal;

}

public static ShipmentGatewayFedex map(HttpServletRequest request) throws Exception {

		ShipmentGatewayFedex returnVal = new ShipmentGatewayFedex();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentGatewayConfigId")) {
returnVal.setShipmentGatewayConfigId(request.getParameter("shipmentGatewayConfigId"));
}

		if(paramMap.containsKey("connectUrl"))  {
returnVal.setConnectUrl(request.getParameter("connectUrl"));
}
		if(paramMap.containsKey("connectSoapUrl"))  {
returnVal.setConnectSoapUrl(request.getParameter("connectSoapUrl"));
}
		if(paramMap.containsKey("connectTimeout"))  {
String buf = request.getParameter("connectTimeout");
Long ibuf = Long.parseLong(buf);
returnVal.setConnectTimeout(ibuf);
}
		if(paramMap.containsKey("accessAccountNbr"))  {
returnVal.setAccessAccountNbr(request.getParameter("accessAccountNbr"));
}
		if(paramMap.containsKey("accessMeterNumber"))  {
returnVal.setAccessMeterNumber(request.getParameter("accessMeterNumber"));
}
		if(paramMap.containsKey("accessUserKey"))  {
returnVal.setAccessUserKey(request.getParameter("accessUserKey"));
}
		if(paramMap.containsKey("accessUserPwd"))  {
returnVal.setAccessUserPwd(request.getParameter("accessUserPwd"));
}
		if(paramMap.containsKey("labelImageType"))  {
returnVal.setLabelImageType(request.getParameter("labelImageType"));
}
		if(paramMap.containsKey("defaultDropoffType"))  {
returnVal.setDefaultDropoffType(request.getParameter("defaultDropoffType"));
}
		if(paramMap.containsKey("defaultPackagingType"))  {
returnVal.setDefaultPackagingType(request.getParameter("defaultPackagingType"));
}
		if(paramMap.containsKey("templateShipment"))  {
returnVal.setTemplateShipment(request.getParameter("templateShipment"));
}
		if(paramMap.containsKey("templateSubscription"))  {
returnVal.setTemplateSubscription(request.getParameter("templateSubscription"));
}
		if(paramMap.containsKey("rateEstimateTemplate"))  {
returnVal.setRateEstimateTemplate(request.getParameter("rateEstimateTemplate"));
}
return returnVal;

}
}
