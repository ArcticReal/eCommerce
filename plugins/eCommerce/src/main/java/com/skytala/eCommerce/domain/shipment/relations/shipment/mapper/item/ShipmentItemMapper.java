package com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.item;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.item.ShipmentItem;

public class ShipmentItemMapper  {


	public static Map<String, Object> map(ShipmentItem shipmentitem) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentitem.getShipmentId() != null ){
			returnVal.put("shipmentId",shipmentitem.getShipmentId());
}

		if(shipmentitem.getShipmentItemSeqId() != null ){
			returnVal.put("shipmentItemSeqId",shipmentitem.getShipmentItemSeqId());
}

		if(shipmentitem.getProductId() != null ){
			returnVal.put("productId",shipmentitem.getProductId());
}

		if(shipmentitem.getQuantity() != null ){
			returnVal.put("quantity",shipmentitem.getQuantity());
}

		if(shipmentitem.getShipmentContentDescription() != null ){
			returnVal.put("shipmentContentDescription",shipmentitem.getShipmentContentDescription());
}

		return returnVal;
}


	public static ShipmentItem map(Map<String, Object> fields) {

		ShipmentItem returnVal = new ShipmentItem();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("shipmentContentDescription") != null) {
			returnVal.setShipmentContentDescription((String) fields.get("shipmentContentDescription"));
}


		return returnVal;
 } 
	public static ShipmentItem mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentItem returnVal = new ShipmentItem();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("shipmentContentDescription") != null) {
			returnVal.setShipmentContentDescription((String) fields.get("shipmentContentDescription"));
}


		return returnVal;
 } 
	public static ShipmentItem map(GenericValue val) {

ShipmentItem returnVal = new ShipmentItem();
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setShipmentItemSeqId(val.getString("shipmentItemSeqId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setShipmentContentDescription(val.getString("shipmentContentDescription"));


return returnVal;

}

public static ShipmentItem map(HttpServletRequest request) throws Exception {

		ShipmentItem returnVal = new ShipmentItem();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentId")) {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}

		if(paramMap.containsKey("shipmentItemSeqId"))  {
returnVal.setShipmentItemSeqId(request.getParameter("shipmentItemSeqId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("shipmentContentDescription"))  {
returnVal.setShipmentContentDescription(request.getParameter("shipmentContentDescription"));
}
return returnVal;

}
}
