package com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.model.OrderDeliverySchedule;

public class OrderDeliveryScheduleMapper  {


	public static Map<String, Object> map(OrderDeliverySchedule orderdeliveryschedule) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderdeliveryschedule.getOrderId() != null ){
			returnVal.put("orderId",orderdeliveryschedule.getOrderId());
}

		if(orderdeliveryschedule.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderdeliveryschedule.getOrderItemSeqId());
}

		if(orderdeliveryschedule.getEstimatedReadyDate() != null ){
			returnVal.put("estimatedReadyDate",orderdeliveryschedule.getEstimatedReadyDate());
}

		if(orderdeliveryschedule.getCartons() != null ){
			returnVal.put("cartons",orderdeliveryschedule.getCartons());
}

		if(orderdeliveryschedule.getSkidsPallets() != null ){
			returnVal.put("skidsPallets",orderdeliveryschedule.getSkidsPallets());
}

		if(orderdeliveryschedule.getUnitsPieces() != null ){
			returnVal.put("unitsPieces",orderdeliveryschedule.getUnitsPieces());
}

		if(orderdeliveryschedule.getTotalCubicSize() != null ){
			returnVal.put("totalCubicSize",orderdeliveryschedule.getTotalCubicSize());
}

		if(orderdeliveryschedule.getTotalCubicUomId() != null ){
			returnVal.put("totalCubicUomId",orderdeliveryschedule.getTotalCubicUomId());
}

		if(orderdeliveryschedule.getTotalWeight() != null ){
			returnVal.put("totalWeight",orderdeliveryschedule.getTotalWeight());
}

		if(orderdeliveryschedule.getTotalWeightUomId() != null ){
			returnVal.put("totalWeightUomId",orderdeliveryschedule.getTotalWeightUomId());
}

		if(orderdeliveryschedule.getStatusId() != null ){
			returnVal.put("statusId",orderdeliveryschedule.getStatusId());
}

		return returnVal;
}


	public static OrderDeliverySchedule map(Map<String, Object> fields) {

		OrderDeliverySchedule returnVal = new OrderDeliverySchedule();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("estimatedReadyDate") != null) {
			returnVal.setEstimatedReadyDate((Timestamp) fields.get("estimatedReadyDate"));
}

		if(fields.get("cartons") != null) {
			returnVal.setCartons((long) fields.get("cartons"));
}

		if(fields.get("skidsPallets") != null) {
			returnVal.setSkidsPallets((long) fields.get("skidsPallets"));
}

		if(fields.get("unitsPieces") != null) {
			returnVal.setUnitsPieces((BigDecimal) fields.get("unitsPieces"));
}

		if(fields.get("totalCubicSize") != null) {
			returnVal.setTotalCubicSize((BigDecimal) fields.get("totalCubicSize"));
}

		if(fields.get("totalCubicUomId") != null) {
			returnVal.setTotalCubicUomId((String) fields.get("totalCubicUomId"));
}

		if(fields.get("totalWeight") != null) {
			returnVal.setTotalWeight((BigDecimal) fields.get("totalWeight"));
}

		if(fields.get("totalWeightUomId") != null) {
			returnVal.setTotalWeightUomId((String) fields.get("totalWeightUomId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}


		return returnVal;
 } 
	public static OrderDeliverySchedule mapstrstr(Map<String, String> fields) throws Exception {

		OrderDeliverySchedule returnVal = new OrderDeliverySchedule();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("estimatedReadyDate") != null) {
String buf = fields.get("estimatedReadyDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedReadyDate(ibuf);
}

		if(fields.get("cartons") != null) {
String buf;
buf = fields.get("cartons");
long ibuf = Long.parseLong(buf);
			returnVal.setCartons(ibuf);
}

		if(fields.get("skidsPallets") != null) {
String buf;
buf = fields.get("skidsPallets");
long ibuf = Long.parseLong(buf);
			returnVal.setSkidsPallets(ibuf);
}

		if(fields.get("unitsPieces") != null) {
String buf;
buf = fields.get("unitsPieces");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitsPieces(bd);
}

		if(fields.get("totalCubicSize") != null) {
String buf;
buf = fields.get("totalCubicSize");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalCubicSize(bd);
}

		if(fields.get("totalCubicUomId") != null) {
			returnVal.setTotalCubicUomId((String) fields.get("totalCubicUomId"));
}

		if(fields.get("totalWeight") != null) {
String buf;
buf = fields.get("totalWeight");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalWeight(bd);
}

		if(fields.get("totalWeightUomId") != null) {
			returnVal.setTotalWeightUomId((String) fields.get("totalWeightUomId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}


		return returnVal;
 } 
	public static OrderDeliverySchedule map(GenericValue val) {

OrderDeliverySchedule returnVal = new OrderDeliverySchedule();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setEstimatedReadyDate(val.getTimestamp("estimatedReadyDate"));
		returnVal.setCartons(val.getLong("cartons"));
		returnVal.setSkidsPallets(val.getLong("skidsPallets"));
		returnVal.setUnitsPieces(val.getBigDecimal("unitsPieces"));
		returnVal.setTotalCubicSize(val.getBigDecimal("totalCubicSize"));
		returnVal.setTotalCubicUomId(val.getString("totalCubicUomId"));
		returnVal.setTotalWeight(val.getBigDecimal("totalWeight"));
		returnVal.setTotalWeightUomId(val.getString("totalWeightUomId"));
		returnVal.setStatusId(val.getString("statusId"));


return returnVal;

}

public static OrderDeliverySchedule map(HttpServletRequest request) throws Exception {

		OrderDeliverySchedule returnVal = new OrderDeliverySchedule();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("estimatedReadyDate"))  {
String buf = request.getParameter("estimatedReadyDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEstimatedReadyDate(ibuf);
}
		if(paramMap.containsKey("cartons"))  {
String buf = request.getParameter("cartons");
Long ibuf = Long.parseLong(buf);
returnVal.setCartons(ibuf);
}
		if(paramMap.containsKey("skidsPallets"))  {
String buf = request.getParameter("skidsPallets");
Long ibuf = Long.parseLong(buf);
returnVal.setSkidsPallets(ibuf);
}
		if(paramMap.containsKey("unitsPieces"))  {
String buf = request.getParameter("unitsPieces");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitsPieces(bd);
}
		if(paramMap.containsKey("totalCubicSize"))  {
String buf = request.getParameter("totalCubicSize");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalCubicSize(bd);
}
		if(paramMap.containsKey("totalCubicUomId"))  {
returnVal.setTotalCubicUomId(request.getParameter("totalCubicUomId"));
}
		if(paramMap.containsKey("totalWeight"))  {
String buf = request.getParameter("totalWeight");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalWeight(bd);
}
		if(paramMap.containsKey("totalWeightUomId"))  {
returnVal.setTotalWeightUomId(request.getParameter("totalWeightUomId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
return returnVal;

}
}
