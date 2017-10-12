package com.skytala.eCommerce.domain.shipment.relations.delivery.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.delivery.model.Delivery;

public class DeliveryMapper  {


	public static Map<String, Object> map(Delivery delivery) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(delivery.getDeliveryId() != null ){
			returnVal.put("deliveryId",delivery.getDeliveryId());
}

		if(delivery.getOriginFacilityId() != null ){
			returnVal.put("originFacilityId",delivery.getOriginFacilityId());
}

		if(delivery.getDestFacilityId() != null ){
			returnVal.put("destFacilityId",delivery.getDestFacilityId());
}

		if(delivery.getActualStartDate() != null ){
			returnVal.put("actualStartDate",delivery.getActualStartDate());
}

		if(delivery.getActualArrivalDate() != null ){
			returnVal.put("actualArrivalDate",delivery.getActualArrivalDate());
}

		if(delivery.getEstimatedStartDate() != null ){
			returnVal.put("estimatedStartDate",delivery.getEstimatedStartDate());
}

		if(delivery.getEstimatedArrivalDate() != null ){
			returnVal.put("estimatedArrivalDate",delivery.getEstimatedArrivalDate());
}

		if(delivery.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",delivery.getFixedAssetId());
}

		if(delivery.getStartMileage() != null ){
			returnVal.put("startMileage",delivery.getStartMileage());
}

		if(delivery.getEndMileage() != null ){
			returnVal.put("endMileage",delivery.getEndMileage());
}

		if(delivery.getFuelUsed() != null ){
			returnVal.put("fuelUsed",delivery.getFuelUsed());
}

		return returnVal;
}


	public static Delivery map(Map<String, Object> fields) {

		Delivery returnVal = new Delivery();

		if(fields.get("deliveryId") != null) {
			returnVal.setDeliveryId((String) fields.get("deliveryId"));
}

		if(fields.get("originFacilityId") != null) {
			returnVal.setOriginFacilityId((String) fields.get("originFacilityId"));
}

		if(fields.get("destFacilityId") != null) {
			returnVal.setDestFacilityId((String) fields.get("destFacilityId"));
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

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("startMileage") != null) {
			returnVal.setStartMileage((BigDecimal) fields.get("startMileage"));
}

		if(fields.get("endMileage") != null) {
			returnVal.setEndMileage((BigDecimal) fields.get("endMileage"));
}

		if(fields.get("fuelUsed") != null) {
			returnVal.setFuelUsed((BigDecimal) fields.get("fuelUsed"));
}


		return returnVal;
 } 
	public static Delivery mapstrstr(Map<String, String> fields) throws Exception {

		Delivery returnVal = new Delivery();

		if(fields.get("deliveryId") != null) {
			returnVal.setDeliveryId((String) fields.get("deliveryId"));
}

		if(fields.get("originFacilityId") != null) {
			returnVal.setOriginFacilityId((String) fields.get("originFacilityId"));
}

		if(fields.get("destFacilityId") != null) {
			returnVal.setDestFacilityId((String) fields.get("destFacilityId"));
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

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("startMileage") != null) {
String buf;
buf = fields.get("startMileage");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setStartMileage(bd);
}

		if(fields.get("endMileage") != null) {
String buf;
buf = fields.get("endMileage");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEndMileage(bd);
}

		if(fields.get("fuelUsed") != null) {
String buf;
buf = fields.get("fuelUsed");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFuelUsed(bd);
}


		return returnVal;
 } 
	public static Delivery map(GenericValue val) {

Delivery returnVal = new Delivery();
		returnVal.setDeliveryId(val.getString("deliveryId"));
		returnVal.setOriginFacilityId(val.getString("originFacilityId"));
		returnVal.setDestFacilityId(val.getString("destFacilityId"));
		returnVal.setActualStartDate(val.getTimestamp("actualStartDate"));
		returnVal.setActualArrivalDate(val.getTimestamp("actualArrivalDate"));
		returnVal.setEstimatedStartDate(val.getTimestamp("estimatedStartDate"));
		returnVal.setEstimatedArrivalDate(val.getTimestamp("estimatedArrivalDate"));
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setStartMileage(val.getBigDecimal("startMileage"));
		returnVal.setEndMileage(val.getBigDecimal("endMileage"));
		returnVal.setFuelUsed(val.getBigDecimal("fuelUsed"));


return returnVal;

}

public static Delivery map(HttpServletRequest request) throws Exception {

		Delivery returnVal = new Delivery();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("deliveryId")) {
returnVal.setDeliveryId(request.getParameter("deliveryId"));
}

		if(paramMap.containsKey("originFacilityId"))  {
returnVal.setOriginFacilityId(request.getParameter("originFacilityId"));
}
		if(paramMap.containsKey("destFacilityId"))  {
returnVal.setDestFacilityId(request.getParameter("destFacilityId"));
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
		if(paramMap.containsKey("fixedAssetId"))  {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}
		if(paramMap.containsKey("startMileage"))  {
String buf = request.getParameter("startMileage");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setStartMileage(bd);
}
		if(paramMap.containsKey("endMileage"))  {
String buf = request.getParameter("endMileage");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEndMileage(bd);
}
		if(paramMap.containsKey("fuelUsed"))  {
String buf = request.getParameter("fuelUsed");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFuelUsed(bd);
}
return returnVal;

}
}
