package com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayUsps;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayUsps.ShipmentGatewayUsps;

public class ShipmentGatewayUspsMapper  {


	public static Map<String, Object> map(ShipmentGatewayUsps shipmentgatewayusps) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentgatewayusps.getShipmentGatewayConfigId() != null ){
			returnVal.put("shipmentGatewayConfigId",shipmentgatewayusps.getShipmentGatewayConfigId());
}

		if(shipmentgatewayusps.getConnectUrl() != null ){
			returnVal.put("connectUrl",shipmentgatewayusps.getConnectUrl());
}

		if(shipmentgatewayusps.getConnectUrlLabels() != null ){
			returnVal.put("connectUrlLabels",shipmentgatewayusps.getConnectUrlLabels());
}

		if(shipmentgatewayusps.getConnectTimeout() != null ){
			returnVal.put("connectTimeout",shipmentgatewayusps.getConnectTimeout());
}

		if(shipmentgatewayusps.getAccessUserId() != null ){
			returnVal.put("accessUserId",shipmentgatewayusps.getAccessUserId());
}

		if(shipmentgatewayusps.getAccessPassword() != null ){
			returnVal.put("accessPassword",shipmentgatewayusps.getAccessPassword());
}

		if(shipmentgatewayusps.getMaxEstimateWeight() != null ){
			returnVal.put("maxEstimateWeight",shipmentgatewayusps.getMaxEstimateWeight());
}

		if(shipmentgatewayusps.getTest() != null ){
			returnVal.put("test",shipmentgatewayusps.getTest());
}

		return returnVal;
}


	public static ShipmentGatewayUsps map(Map<String, Object> fields) {

		ShipmentGatewayUsps returnVal = new ShipmentGatewayUsps();

		if(fields.get("shipmentGatewayConfigId") != null) {
			returnVal.setShipmentGatewayConfigId((String) fields.get("shipmentGatewayConfigId"));
}

		if(fields.get("connectUrl") != null) {
			returnVal.setConnectUrl((long) fields.get("connectUrl"));
}

		if(fields.get("connectUrlLabels") != null) {
			returnVal.setConnectUrlLabels((long) fields.get("connectUrlLabels"));
}

		if(fields.get("connectTimeout") != null) {
			returnVal.setConnectTimeout((long) fields.get("connectTimeout"));
}

		if(fields.get("accessUserId") != null) {
			returnVal.setAccessUserId((long) fields.get("accessUserId"));
}

		if(fields.get("accessPassword") != null) {
			returnVal.setAccessPassword((long) fields.get("accessPassword"));
}

		if(fields.get("maxEstimateWeight") != null) {
			returnVal.setMaxEstimateWeight((long) fields.get("maxEstimateWeight"));
}

		if(fields.get("test") != null) {
			returnVal.setTest((String) fields.get("test"));
}


		return returnVal;
 } 
	public static ShipmentGatewayUsps mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentGatewayUsps returnVal = new ShipmentGatewayUsps();

		if(fields.get("shipmentGatewayConfigId") != null) {
			returnVal.setShipmentGatewayConfigId((String) fields.get("shipmentGatewayConfigId"));
}

		if(fields.get("connectUrl") != null) {
String buf;
buf = fields.get("connectUrl");
long ibuf = Long.parseLong(buf);
			returnVal.setConnectUrl(ibuf);
}

		if(fields.get("connectUrlLabels") != null) {
String buf;
buf = fields.get("connectUrlLabels");
long ibuf = Long.parseLong(buf);
			returnVal.setConnectUrlLabels(ibuf);
}

		if(fields.get("connectTimeout") != null) {
String buf;
buf = fields.get("connectTimeout");
long ibuf = Long.parseLong(buf);
			returnVal.setConnectTimeout(ibuf);
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

		if(fields.get("maxEstimateWeight") != null) {
String buf;
buf = fields.get("maxEstimateWeight");
long ibuf = Long.parseLong(buf);
			returnVal.setMaxEstimateWeight(ibuf);
}

		if(fields.get("test") != null) {
			returnVal.setTest((String) fields.get("test"));
}


		return returnVal;
 } 
	public static ShipmentGatewayUsps map(GenericValue val) {

ShipmentGatewayUsps returnVal = new ShipmentGatewayUsps();
		returnVal.setShipmentGatewayConfigId(val.getString("shipmentGatewayConfigId"));
		returnVal.setConnectUrl(val.getLong("connectUrl"));
		returnVal.setConnectUrlLabels(val.getLong("connectUrlLabels"));
		returnVal.setConnectTimeout(val.getLong("connectTimeout"));
		returnVal.setAccessUserId(val.getLong("accessUserId"));
		returnVal.setAccessPassword(val.getLong("accessPassword"));
		returnVal.setMaxEstimateWeight(val.getLong("maxEstimateWeight"));
		returnVal.setTest(val.getString("test"));


return returnVal;

}

public static ShipmentGatewayUsps map(HttpServletRequest request) throws Exception {

		ShipmentGatewayUsps returnVal = new ShipmentGatewayUsps();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentGatewayConfigId")) {
returnVal.setShipmentGatewayConfigId(request.getParameter("shipmentGatewayConfigId"));
}

		if(paramMap.containsKey("connectUrl"))  {
String buf = request.getParameter("connectUrl");
Long ibuf = Long.parseLong(buf);
returnVal.setConnectUrl(ibuf);
}
		if(paramMap.containsKey("connectUrlLabels"))  {
String buf = request.getParameter("connectUrlLabels");
Long ibuf = Long.parseLong(buf);
returnVal.setConnectUrlLabels(ibuf);
}
		if(paramMap.containsKey("connectTimeout"))  {
String buf = request.getParameter("connectTimeout");
Long ibuf = Long.parseLong(buf);
returnVal.setConnectTimeout(ibuf);
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
		if(paramMap.containsKey("maxEstimateWeight"))  {
String buf = request.getParameter("maxEstimateWeight");
Long ibuf = Long.parseLong(buf);
returnVal.setMaxEstimateWeight(ibuf);
}
		if(paramMap.containsKey("test"))  {
returnVal.setTest(request.getParameter("test"));
}
return returnVal;

}
}
