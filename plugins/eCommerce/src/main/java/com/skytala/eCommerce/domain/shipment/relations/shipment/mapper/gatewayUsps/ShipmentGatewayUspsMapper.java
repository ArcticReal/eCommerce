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
			returnVal.setConnectUrl((String) fields.get("connectUrl"));
}

		if(fields.get("connectUrlLabels") != null) {
			returnVal.setConnectUrlLabels((String) fields.get("connectUrlLabels"));
}

		if(fields.get("connectTimeout") != null) {
			returnVal.setConnectTimeout((long) fields.get("connectTimeout"));
}

		if(fields.get("accessUserId") != null) {
			returnVal.setAccessUserId((String) fields.get("accessUserId"));
}

		if(fields.get("accessPassword") != null) {
			returnVal.setAccessPassword((String) fields.get("accessPassword"));
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
			returnVal.setConnectUrl((String) fields.get("connectUrl"));
}

		if(fields.get("connectUrlLabels") != null) {
			returnVal.setConnectUrlLabels((String) fields.get("connectUrlLabels"));
}

		if(fields.get("connectTimeout") != null) {
String buf;
buf = fields.get("connectTimeout");
long ibuf = Long.parseLong(buf);
			returnVal.setConnectTimeout(ibuf);
}

		if(fields.get("accessUserId") != null) {
			returnVal.setAccessUserId((String) fields.get("accessUserId"));
}

		if(fields.get("accessPassword") != null) {
			returnVal.setAccessPassword((String) fields.get("accessPassword"));
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
		returnVal.setConnectUrl(val.getString("connectUrl"));
		returnVal.setConnectUrlLabels(val.getString("connectUrlLabels"));
		returnVal.setConnectTimeout(val.getLong("connectTimeout"));
		returnVal.setAccessUserId(val.getString("accessUserId"));
		returnVal.setAccessPassword(val.getString("accessPassword"));
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
returnVal.setConnectUrl(request.getParameter("connectUrl"));
}
		if(paramMap.containsKey("connectUrlLabels"))  {
returnVal.setConnectUrlLabels(request.getParameter("connectUrlLabels"));
}
		if(paramMap.containsKey("connectTimeout"))  {
String buf = request.getParameter("connectTimeout");
Long ibuf = Long.parseLong(buf);
returnVal.setConnectTimeout(ibuf);
}
		if(paramMap.containsKey("accessUserId"))  {
returnVal.setAccessUserId(request.getParameter("accessUserId"));
}
		if(paramMap.containsKey("accessPassword"))  {
returnVal.setAccessPassword(request.getParameter("accessPassword"));
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
