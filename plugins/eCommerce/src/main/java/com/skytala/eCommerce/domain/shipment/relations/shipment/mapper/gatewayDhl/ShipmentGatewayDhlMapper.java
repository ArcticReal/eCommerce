package com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayDhl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayDhl.ShipmentGatewayDhl;

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
			returnVal.setConnectUrl((String) fields.get("connectUrl"));
}

		if(fields.get("connectTimeout") != null) {
			returnVal.setConnectTimeout((long) fields.get("connectTimeout"));
}

		if(fields.get("headVersion") != null) {
			returnVal.setHeadVersion((String) fields.get("headVersion"));
}

		if(fields.get("headAction") != null) {
			returnVal.setHeadAction((String) fields.get("headAction"));
}

		if(fields.get("accessUserId") != null) {
			returnVal.setAccessUserId((String) fields.get("accessUserId"));
}

		if(fields.get("accessPassword") != null) {
			returnVal.setAccessPassword((String) fields.get("accessPassword"));
}

		if(fields.get("accessAccountNbr") != null) {
			returnVal.setAccessAccountNbr((String) fields.get("accessAccountNbr"));
}

		if(fields.get("accessShippingKey") != null) {
			returnVal.setAccessShippingKey((String) fields.get("accessShippingKey"));
}

		if(fields.get("labelImageFormat") != null) {
			returnVal.setLabelImageFormat((String) fields.get("labelImageFormat"));
}

		if(fields.get("rateEstimateTemplate") != null) {
			returnVal.setRateEstimateTemplate((String) fields.get("rateEstimateTemplate"));
}


		return returnVal;
 } 
	public static ShipmentGatewayDhl mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentGatewayDhl returnVal = new ShipmentGatewayDhl();

		if(fields.get("shipmentGatewayConfigId") != null) {
			returnVal.setShipmentGatewayConfigId((String) fields.get("shipmentGatewayConfigId"));
}

		if(fields.get("connectUrl") != null) {
			returnVal.setConnectUrl((String) fields.get("connectUrl"));
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
			returnVal.setHeadAction((String) fields.get("headAction"));
}

		if(fields.get("accessUserId") != null) {
			returnVal.setAccessUserId((String) fields.get("accessUserId"));
}

		if(fields.get("accessPassword") != null) {
			returnVal.setAccessPassword((String) fields.get("accessPassword"));
}

		if(fields.get("accessAccountNbr") != null) {
			returnVal.setAccessAccountNbr((String) fields.get("accessAccountNbr"));
}

		if(fields.get("accessShippingKey") != null) {
			returnVal.setAccessShippingKey((String) fields.get("accessShippingKey"));
}

		if(fields.get("labelImageFormat") != null) {
			returnVal.setLabelImageFormat((String) fields.get("labelImageFormat"));
}

		if(fields.get("rateEstimateTemplate") != null) {
			returnVal.setRateEstimateTemplate((String) fields.get("rateEstimateTemplate"));
}


		return returnVal;
 } 
	public static ShipmentGatewayDhl map(GenericValue val) {

ShipmentGatewayDhl returnVal = new ShipmentGatewayDhl();
		returnVal.setShipmentGatewayConfigId(val.getString("shipmentGatewayConfigId"));
		returnVal.setConnectUrl(val.getString("connectUrl"));
		returnVal.setConnectTimeout(val.getLong("connectTimeout"));
		returnVal.setHeadVersion(val.getString("headVersion"));
		returnVal.setHeadAction(val.getString("headAction"));
		returnVal.setAccessUserId(val.getString("accessUserId"));
		returnVal.setAccessPassword(val.getString("accessPassword"));
		returnVal.setAccessAccountNbr(val.getString("accessAccountNbr"));
		returnVal.setAccessShippingKey(val.getString("accessShippingKey"));
		returnVal.setLabelImageFormat(val.getString("labelImageFormat"));
		returnVal.setRateEstimateTemplate(val.getString("rateEstimateTemplate"));


return returnVal;

}

public static ShipmentGatewayDhl map(HttpServletRequest request) throws Exception {

		ShipmentGatewayDhl returnVal = new ShipmentGatewayDhl();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentGatewayConfigId")) {
returnVal.setShipmentGatewayConfigId(request.getParameter("shipmentGatewayConfigId"));
}

		if(paramMap.containsKey("connectUrl"))  {
returnVal.setConnectUrl(request.getParameter("connectUrl"));
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
returnVal.setHeadAction(request.getParameter("headAction"));
}
		if(paramMap.containsKey("accessUserId"))  {
returnVal.setAccessUserId(request.getParameter("accessUserId"));
}
		if(paramMap.containsKey("accessPassword"))  {
returnVal.setAccessPassword(request.getParameter("accessPassword"));
}
		if(paramMap.containsKey("accessAccountNbr"))  {
returnVal.setAccessAccountNbr(request.getParameter("accessAccountNbr"));
}
		if(paramMap.containsKey("accessShippingKey"))  {
returnVal.setAccessShippingKey(request.getParameter("accessShippingKey"));
}
		if(paramMap.containsKey("labelImageFormat"))  {
returnVal.setLabelImageFormat(request.getParameter("labelImageFormat"));
}
		if(paramMap.containsKey("rateEstimateTemplate"))  {
returnVal.setRateEstimateTemplate(request.getParameter("rateEstimateTemplate"));
}
return returnVal;

}
}
