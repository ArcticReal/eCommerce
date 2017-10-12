package com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.model.ShipmentPackageContent;

public class ShipmentPackageContentMapper  {


	public static Map<String, Object> map(ShipmentPackageContent shipmentpackagecontent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentpackagecontent.getShipmentId() != null ){
			returnVal.put("shipmentId",shipmentpackagecontent.getShipmentId());
}

		if(shipmentpackagecontent.getShipmentPackageSeqId() != null ){
			returnVal.put("shipmentPackageSeqId",shipmentpackagecontent.getShipmentPackageSeqId());
}

		if(shipmentpackagecontent.getShipmentItemSeqId() != null ){
			returnVal.put("shipmentItemSeqId",shipmentpackagecontent.getShipmentItemSeqId());
}

		if(shipmentpackagecontent.getQuantity() != null ){
			returnVal.put("quantity",shipmentpackagecontent.getQuantity());
}

		if(shipmentpackagecontent.getSubProductId() != null ){
			returnVal.put("subProductId",shipmentpackagecontent.getSubProductId());
}

		if(shipmentpackagecontent.getSubProductQuantity() != null ){
			returnVal.put("subProductQuantity",shipmentpackagecontent.getSubProductQuantity());
}

		return returnVal;
}


	public static ShipmentPackageContent map(Map<String, Object> fields) {

		ShipmentPackageContent returnVal = new ShipmentPackageContent();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentPackageSeqId") != null) {
			returnVal.setShipmentPackageSeqId((String) fields.get("shipmentPackageSeqId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("subProductId") != null) {
			returnVal.setSubProductId((String) fields.get("subProductId"));
}

		if(fields.get("subProductQuantity") != null) {
			returnVal.setSubProductQuantity((BigDecimal) fields.get("subProductQuantity"));
}


		return returnVal;
 } 
	public static ShipmentPackageContent mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentPackageContent returnVal = new ShipmentPackageContent();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentPackageSeqId") != null) {
			returnVal.setShipmentPackageSeqId((String) fields.get("shipmentPackageSeqId"));
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

		if(fields.get("subProductId") != null) {
			returnVal.setSubProductId((String) fields.get("subProductId"));
}

		if(fields.get("subProductQuantity") != null) {
String buf;
buf = fields.get("subProductQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSubProductQuantity(bd);
}


		return returnVal;
 } 
	public static ShipmentPackageContent map(GenericValue val) {

ShipmentPackageContent returnVal = new ShipmentPackageContent();
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setShipmentPackageSeqId(val.getString("shipmentPackageSeqId"));
		returnVal.setShipmentItemSeqId(val.getString("shipmentItemSeqId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setSubProductId(val.getString("subProductId"));
		returnVal.setSubProductQuantity(val.getBigDecimal("subProductQuantity"));


return returnVal;

}

public static ShipmentPackageContent map(HttpServletRequest request) throws Exception {

		ShipmentPackageContent returnVal = new ShipmentPackageContent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentId")) {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}

		if(paramMap.containsKey("shipmentPackageSeqId"))  {
returnVal.setShipmentPackageSeqId(request.getParameter("shipmentPackageSeqId"));
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
		if(paramMap.containsKey("subProductId"))  {
returnVal.setSubProductId(request.getParameter("subProductId"));
}
		if(paramMap.containsKey("subProductQuantity"))  {
String buf = request.getParameter("subProductQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSubProductQuantity(bd);
}
return returnVal;

}
}
