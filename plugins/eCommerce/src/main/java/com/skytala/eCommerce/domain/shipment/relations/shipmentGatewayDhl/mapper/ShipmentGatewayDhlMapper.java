package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayDhl.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayDhl.model.ShipmentGatewayDhl;

public class ShipmentGatewayDhlMapper  {


	public static Map<String, Object> map(ShipmentGatewayDhl shipmentgatewaydhl) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentgatewaydhl.getShipmentGatewayConfigId() != null ){
			returnVal.put("shipmentGatewayConfigId",shipmentgatewaydhl.getShipmentGatewayConfigId());
}

		if(shipmentgatewaydhl.getConnectUrl() != null ){
			returnVal.put("connectUrl",shipmentgatewaydhl.getConnectUrl());
}

		if(shipmentgatewaydhl.getConnectTimeout() != null ){
			returnVal.put("connectTimeout",shipmentgatewaydhl.getConnectTimeout());
}

		if(shipmentgatewaydhl.getHeadVersion() != null ){
			returnVal.put("headVersion",shipmentgatewaydhl.getHeadVersion());
}

		if(shipmentgatewaydhl.getHeadAction() != null ){
			returnVal.put("headAction",shipmentgatewaydhl.getHeadAction());
}

		if(shipmentgatewaydhl.getAccessUserId() != null ){
			returnVal.put("accessUserId",shipmentgatewaydhl.getAccessUserId());
}

		if(shipmentgatewaydhl.getAccessPassword() != null ){
			returnVal.put("accessPassword",shipmentgatewaydhl.getAccessPassword());
}

		if(shipmentgatewaydhl.getAccessAccountNbr() != null ){
			returnVal.put("accessAccountNbr",shipmentgatewaydhl.getAccessAccountNbr());
}

		if(shipmentgatewaydhl.getAccessShippingKey() != null ){
			returnVal.put("accessShippingKey",shipmentgatewaydhl.getAccessShippingKey());
}

		if(shipmentgatewaydhl.getLabelImageFormat() != null ){
			returnVal.put("labelImageFormat",shipmentgatewaydhl.getLabelImageFormat());
}

		if(shipmentgatewaydhl.getRateEstimateTemplate() != null ){
			returnVal.put("rateEstimateTemplate",shipmentgatewaydhl.getRateEstimateTemplate());
}

		return returnVal;
}


	public static ShipmentGatewayDhl map(Map<String, Object> fields) {

		ShipmentGatewayDhl returnVal = new ShipmentGatewayDhl();

		if(fields.get("shipmentGatewayConfigId") != null) {
			returnVal.setShipmentGatewayConfigId((String) fields.get("shipmentGatewayConfigId"));
}

		if(fields.get("connectUrl") != null) {
			returnVal.setConnectUrl((long) fields.get("connectUrl"));
}

		if(fields.get("connectTimeout") != null) {
			returnVal.setConnectTimeout((long) fields.get("connectTimeout"));
}

		if(fields.get("headVersion") != null) {
			returnVal.setHeadVersion((String) fields.get("headVersion"));
}

		if(fields.get("headAction") != null) {
			returnVal.setHeadAction((long) fields.get("headAction"));
}

		if(fields.get("accessUserId") != null) {
			returnVal.setAccessUserId((long) fields.get("accessUserId"));
}

		if(fields.get("accessPassword") != null) {
			returnVal.setAccessPassword((long) fields.get("accessPassword"));
}

		if(fields.get("accessAccountNbr") != null) {
			returnVal.setAccessAccountNbr((long) fields.get("accessAccountNbr"));
}

		if(fields.get("accessShippingKey") != null) {
			returnVal.setAccessShippingKey((long) fields.get("accessShippingKey"));
}

		if(fields.get("labelImageFormat") != null) {
			returnVal.setLabelImageFormat((String) fields.get("labelImageFormat"));
}

		if(fields.get("rateEstimateTemplate") != null) {
			returnVal.setRateEstimateTemplate((long) fields.get("rateEstimateTemplate"));
}


		return returnVal;
 } 
	public static ShipmentGatewayDhl mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentGatewayDhl returnVal = new ShipmentGatewayDhl();

		if(fields.get("shipmentGatewayConfigId") != null) {
			returnVal.setShipmentGatewayConfigId((String) fields.get("shipmentGatewayConfigId"));
}

		if(fields.get("connectUrl") != null) {
String buf;
buf = fields.get("connectUrl");
long ibuf = Long.parseLong(buf);
			returnVal.setConnectUrl(ibuf);
}

		if(fields.get("connectTimeout") != null) {
String buf;
buf = fields.get("connectTimeout");
long ibuf = Long.parseLong(buf);
			returnVal.setConnectTimeout(ibuf);
}

		if(fields.get("headVersion") != null) {
			returnVal.setHeadVersion((String) fields.get("headVersion"));
}

		if(fields.get("headAction") != null) {
String buf;
buf = fields.get("headAction");
long ibuf = Long.parseLong(buf);
			returnVal.setHeadAction(ibuf);
}

		if(fields.get("accessUserId") != null) {
String buf;
buf = fields.get("accessUserId");
long ibuf = Long.parseLong(buf);
			returnVal.setAccessUserId(ibuf);
}

		if(fields.get("accessPassword") != null) {
String buf;
buf = fields.get("accessPassword");
long ibuf = Long.parseLong(buf);
			returnVal.setAccessPassword(ibuf);
}

		if(fields.get("accessAccountNbr") != null) {
String buf;
buf = fields.get("accessAccountNbr");
long ibuf = Long.parseLong(buf);
			returnVal.setAccessAccountNbr(ibuf);
}

		if(fields.get("accessShippingKey") != null) {
String buf;
buf = fields.get("accessShippingKey");
long ibuf = Long.parseLong(buf);
			returnVal.setAccessShippingKey(ibuf);
}

		if(fields.get("labelImageFormat") != null) {
			returnVal.setLabelImageFormat((String) fields.get("labelImageFormat"));
}

		if(fields.get("rateEstimateTemplate") != null) {
String buf;
buf = fields.get("rateEstimateTemplate");
long ibuf = Long.parseLong(buf);
			returnVal.setRateEstimateTemplate(ibuf);
}


		return returnVal;
 } 
	public static ShipmentGatewayDhl map(GenericValue val) {

ShipmentGatewayDhl returnVal = new ShipmentGatewayDhl();
		returnVal.setShipmentGatewayConfigId(val.getString("shipmentGatewayConfigId"));
		returnVal.setConnectUrl(val.getLong("connectUrl"));
		returnVal.setConnectTimeout(val.getLong("connectTimeout"));
		returnVal.setHeadVersion(val.getString("headVersion"));
		returnVal.setHeadAction(val.getLong("headAction"));
		returnVal.setAccessUserId(val.getLong("accessUserId"));
		returnVal.setAccessPassword(val.getLong("accessPassword"));
		returnVal.setAccessAccountNbr(val.getLong("accessAccountNbr"));
		returnVal.setAccessShippingKey(val.getLong("accessShippingKey"));
		returnVal.setLabelImageFormat(val.getString("labelImageFormat"));
		returnVal.setRateEstimateTemplate(val.getLong("rateEstimateTemplate"));


return returnVal;

}

public static ShipmentGatewayDhl map(HttpServletRequest request) throws Exception {

		ShipmentGatewayDhl returnVal = new ShipmentGatewayDhl();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentGatewayConfigId")) {
returnVal.setShipmentGatewayConfigId(request.getParameter("shipmentGatewayConfigId"));
}

		if(paramMap.containsKey("connectUrl"))  {
String buf = request.getParameter("connectUrl");
Long ibuf = Long.parseLong(buf);
returnVal.setConnectUrl(ibuf);
}
		if(paramMap.containsKey("connectTimeout"))  {
String buf = request.getParameter("connectTimeout");
Long ibuf = Long.parseLong(buf);
returnVal.setConnectTimeout(ibuf);
}
		if(paramMap.containsKey("headVersion"))  {
returnVal.setHeadVersion(request.getParameter("headVersion"));
}
		if(paramMap.containsKey("headAction"))  {
String buf = request.getParameter("headAction");
Long ibuf = Long.parseLong(buf);
returnVal.setHeadAction(ibuf);
}
		if(paramMap.containsKey("accessUserId"))  {
String buf = request.getParameter("accessUserId");
Long ibuf = Long.parseLong(buf);
returnVal.setAccessUserId(ibuf);
}
		if(paramMap.containsKey("accessPassword"))  {
String buf = request.getParameter("accessPassword");
Long ibuf = Long.parseLong(buf);
returnVal.setAccessPassword(ibuf);
}
		if(paramMap.containsKey("accessAccountNbr"))  {
String buf = request.getParameter("accessAccountNbr");
Long ibuf = Long.parseLong(buf);
returnVal.setAccessAccountNbr(ibuf);
}
		if(paramMap.containsKey("accessShippingKey"))  {
String buf = request.getParameter("accessShippingKey");
Long ibuf = Long.parseLong(buf);
returnVal.setAccessShippingKey(ibuf);
}
		if(paramMap.containsKey("labelImageFormat"))  {
returnVal.setLabelImageFormat(request.getParameter("labelImageFormat"));
}
		if(paramMap.containsKey("rateEstimateTemplate"))  {
String buf = request.getParameter("rateEstimateTemplate");
Long ibuf = Long.parseLong(buf);
returnVal.setRateEstimateTemplate(ibuf);
}
return returnVal;

}
}
