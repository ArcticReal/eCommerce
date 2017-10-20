package com.skytala.eCommerce.domain.order.relations.returnItem.mapper.shipment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.shipment.ReturnItemShipment;

public class ReturnItemShipmentMapper  {


	public static Map<String, Object> map(ReturnItemShipment returnitemshipment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(returnitemshipment.getReturnId() != null ){
			returnVal.put("returnId",returnitemshipment.getReturnId());
}

		if(returnitemshipment.getReturnItemSeqId() != null ){
			returnVal.put("returnItemSeqId",returnitemshipment.getReturnItemSeqId());
}

		if(returnitemshipment.getShipmentId() != null ){
			returnVal.put("shipmentId",returnitemshipment.getShipmentId());
}

		if(returnitemshipment.getShipmentItemSeqId() != null ){
			returnVal.put("shipmentItemSeqId",returnitemshipment.getShipmentItemSeqId());
}

		if(returnitemshipment.getQuantity() != null ){
			returnVal.put("quantity",returnitemshipment.getQuantity());
}

		return returnVal;
}


	public static ReturnItemShipment map(Map<String, Object> fields) {

		ReturnItemShipment returnVal = new ReturnItemShipment();

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnItemSeqId") != null) {
			returnVal.setReturnItemSeqId((String) fields.get("returnItemSeqId"));
}

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}


		return returnVal;
 } 
	public static ReturnItemShipment mapstrstr(Map<String, String> fields) throws Exception {

		ReturnItemShipment returnVal = new ReturnItemShipment();

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnItemSeqId") != null) {
			returnVal.setReturnItemSeqId((String) fields.get("returnItemSeqId"));
}

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}


		return returnVal;
 } 
	public static ReturnItemShipment map(GenericValue val) {

ReturnItemShipment returnVal = new ReturnItemShipment();
		returnVal.setReturnId(val.getString("returnId"));
		returnVal.setReturnItemSeqId(val.getString("returnItemSeqId"));
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setShipmentItemSeqId(val.getString("shipmentItemSeqId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));


return returnVal;

}

public static ReturnItemShipment map(HttpServletRequest request) throws Exception {

		ReturnItemShipment returnVal = new ReturnItemShipment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("returnId")) {
returnVal.setReturnId(request.getParameter("returnId"));
}

		if(paramMap.containsKey("returnItemSeqId"))  {
returnVal.setReturnItemSeqId(request.getParameter("returnItemSeqId"));
}
		if(paramMap.containsKey("shipmentId"))  {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}
		if(paramMap.containsKey("shipmentItemSeqId"))  {
returnVal.setShipmentItemSeqId(request.getParameter("shipmentItemSeqId"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
return returnVal;

}
}
