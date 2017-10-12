package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.model.ShipmentGatewayFedex;

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
			returnVal.setConnectUrl((long) fields.get("connectUrl"));
}

		if(fields.get("connectSoapUrl") != null) {
			returnVal.setConnectSoapUrl((long) fields.get("connectSoapUrl"));
}

		if(fields.get("connectTimeout") != null) {
			returnVal.setConnectTimeout((long) fields.get("connectTimeout"));
}

		if(fields.get("accessAccountNbr") != null) {
			returnVal.setAccessAccountNbr((long) fields.get("accessAccountNbr"));
}

		if(fields.get("accessMeterNumber") != null) {
			returnVal.setAccessMeterNumber((long) fields.get("accessMeterNumber"));
}

		if(fields.get("accessUserKey") != null) {
			returnVal.setAccessUserKey((long) fields.get("accessUserKey"));
}

		if(fields.get("accessUserPwd") != null) {
			returnVal.setAccessUserPwd((long) fields.get("accessUserPwd"));
}

		if(fields.get("labelImageType") != null) {
			returnVal.setLabelImageType((String) fields.get("labelImageType"));
}

		if(fields.get("defaultDropoffType") != null) {
			returnVal.setDefaultDropoffType((long) fields.get("defaultDropoffType"));
}

		if(fields.get("defaultPackagingType") != null) {
			returnVal.setDefaultPackagingType((long) fields.get("defaultPackagingType"));
}

		if(fields.get("templateShipment") != null) {
			returnVal.setTemplateShipment((long) fields.get("templateShipment"));
}

		if(fields.get("templateSubscription") != null) {
			returnVal.setTemplateSubscription((long) fields.get("templateSubscription"));
}

		if(fields.get("rateEstimateTemplate") != null) {
			returnVal.setRateEstimateTemplate((long) fields.get("rateEstimateTemplate"));
}


		return returnVal;
 } 
	public static ShipmentGatewayFedex mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentGatewayFedex returnVal = new ShipmentGatewayFedex();

		if(fields.get("shipmentGatewayConfigId") != null) {
			returnVal.setShipmentGatewayConfigId((String) fields.get("shipmentGatewayConfigId"));
}

		if(fields.get("connectUrl") != null) {
String buf;
buf = fields.get("connectUrl");
long ibuf = Long.parseLong(buf);
			returnVal.setConnectUrl(ibuf);
}

		if(fields.get("connectSoapUrl") != null) {
String buf;
buf = fields.get("connectSoapUrl");
long ibuf = Long.parseLong(buf);
			returnVal.setConnectSoapUrl(ibuf);
}

		if(fields.get("connectTimeout") != null) {
String buf;
buf = fields.get("connectTimeout");
long ibuf = Long.parseLong(buf);
			returnVal.setConnectTimeout(ibuf);
}

		if(fields.get("accessAccountNbr") != null) {
String buf;
buf = fields.get("accessAccountNbr");
long ibuf = Long.parseLong(buf);
			returnVal.setAccessAccountNbr(ibuf);
}

		if(fields.get("accessMeterNumber") != null) {
String buf;
buf = fields.get("accessMeterNumber");
long ibuf = Long.parseLong(buf);
			returnVal.setAccessMeterNumber(ibuf);
}

		if(fields.get("accessUserKey") != null) {
String buf;
buf = fields.get("accessUserKey");
long ibuf = Long.parseLong(buf);
			returnVal.setAccessUserKey(ibuf);
}

		if(fields.get("accessUserPwd") != null) {
String buf;
buf = fields.get("accessUserPwd");
long ibuf = Long.parseLong(buf);
			returnVal.setAccessUserPwd(ibuf);
}

		if(fields.get("labelImageType") != null) {
			returnVal.setLabelImageType((String) fields.get("labelImageType"));
}

		if(fields.get("defaultDropoffType") != null) {
String buf;
buf = fields.get("defaultDropoffType");
long ibuf = Long.parseLong(buf);
			returnVal.setDefaultDropoffType(ibuf);
}

		if(fields.get("defaultPackagingType") != null) {
String buf;
buf = fields.get("defaultPackagingType");
long ibuf = Long.parseLong(buf);
			returnVal.setDefaultPackagingType(ibuf);
}

		if(fields.get("templateShipment") != null) {
String buf;
buf = fields.get("templateShipment");
long ibuf = Long.parseLong(buf);
			returnVal.setTemplateShipment(ibuf);
}

		if(fields.get("templateSubscription") != null) {
String buf;
buf = fields.get("templateSubscription");
long ibuf = Long.parseLong(buf);
			returnVal.setTemplateSubscription(ibuf);
}

		if(fields.get("rateEstimateTemplate") != null) {
String buf;
buf = fields.get("rateEstimateTemplate");
long ibuf = Long.parseLong(buf);
			returnVal.setRateEstimateTemplate(ibuf);
}


		return returnVal;
 } 
	public static ShipmentGatewayFedex map(GenericValue val) {

ShipmentGatewayFedex returnVal = new ShipmentGatewayFedex();
		returnVal.setShipmentGatewayConfigId(val.getString("shipmentGatewayConfigId"));
		returnVal.setConnectUrl(val.getLong("connectUrl"));
		returnVal.setConnectSoapUrl(val.getLong("connectSoapUrl"));
		returnVal.setConnectTimeout(val.getLong("connectTimeout"));
		returnVal.setAccessAccountNbr(val.getLong("accessAccountNbr"));
		returnVal.setAccessMeterNumber(val.getLong("accessMeterNumber"));
		returnVal.setAccessUserKey(val.getLong("accessUserKey"));
		returnVal.setAccessUserPwd(val.getLong("accessUserPwd"));
		returnVal.setLabelImageType(val.getString("labelImageType"));
		returnVal.setDefaultDropoffType(val.getLong("defaultDropoffType"));
		returnVal.setDefaultPackagingType(val.getLong("defaultPackagingType"));
		returnVal.setTemplateShipment(val.getLong("templateShipment"));
		returnVal.setTemplateSubscription(val.getLong("templateSubscription"));
		returnVal.setRateEstimateTemplate(val.getLong("rateEstimateTemplate"));


return returnVal;

}

public static ShipmentGatewayFedex map(HttpServletRequest request) throws Exception {

		ShipmentGatewayFedex returnVal = new ShipmentGatewayFedex();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentGatewayConfigId")) {
returnVal.setShipmentGatewayConfigId(request.getParameter("shipmentGatewayConfigId"));
}

		if(paramMap.containsKey("connectUrl"))  {
String buf = request.getParameter("connectUrl");
Long ibuf = Long.parseLong(buf);
returnVal.setConnectUrl(ibuf);
}
		if(paramMap.containsKey("connectSoapUrl"))  {
String buf = request.getParameter("connectSoapUrl");
Long ibuf = Long.parseLong(buf);
returnVal.setConnectSoapUrl(ibuf);
}
		if(paramMap.containsKey("connectTimeout"))  {
String buf = request.getParameter("connectTimeout");
Long ibuf = Long.parseLong(buf);
returnVal.setConnectTimeout(ibuf);
}
		if(paramMap.containsKey("accessAccountNbr"))  {
String buf = request.getParameter("accessAccountNbr");
Long ibuf = Long.parseLong(buf);
returnVal.setAccessAccountNbr(ibuf);
}
		if(paramMap.containsKey("accessMeterNumber"))  {
String buf = request.getParameter("accessMeterNumber");
Long ibuf = Long.parseLong(buf);
returnVal.setAccessMeterNumber(ibuf);
}
		if(paramMap.containsKey("accessUserKey"))  {
String buf = request.getParameter("accessUserKey");
Long ibuf = Long.parseLong(buf);
returnVal.setAccessUserKey(ibuf);
}
		if(paramMap.containsKey("accessUserPwd"))  {
String buf = request.getParameter("accessUserPwd");
Long ibuf = Long.parseLong(buf);
returnVal.setAccessUserPwd(ibuf);
}
		if(paramMap.containsKey("labelImageType"))  {
returnVal.setLabelImageType(request.getParameter("labelImageType"));
}
		if(paramMap.containsKey("defaultDropoffType"))  {
String buf = request.getParameter("defaultDropoffType");
Long ibuf = Long.parseLong(buf);
returnVal.setDefaultDropoffType(ibuf);
}
		if(paramMap.containsKey("defaultPackagingType"))  {
String buf = request.getParameter("defaultPackagingType");
Long ibuf = Long.parseLong(buf);
returnVal.setDefaultPackagingType(ibuf);
}
		if(paramMap.containsKey("templateShipment"))  {
String buf = request.getParameter("templateShipment");
Long ibuf = Long.parseLong(buf);
returnVal.setTemplateShipment(ibuf);
}
		if(paramMap.containsKey("templateSubscription"))  {
String buf = request.getParameter("templateSubscription");
Long ibuf = Long.parseLong(buf);
returnVal.setTemplateSubscription(ibuf);
}
		if(paramMap.containsKey("rateEstimateTemplate"))  {
String buf = request.getParameter("rateEstimateTemplate");
Long ibuf = Long.parseLong(buf);
returnVal.setRateEstimateTemplate(ibuf);
}
return returnVal;

}
}
