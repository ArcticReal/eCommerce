package com.skytala.eCommerce.domain.shipment.relations.shippingDocument.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.model.ShippingDocument;

public class ShippingDocumentMapper  {


	public static Map<String, Object> map(ShippingDocument shippingdocument) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shippingdocument.getDocumentId() != null ){
			returnVal.put("documentId",shippingdocument.getDocumentId());
}

		if(shippingdocument.getShipmentId() != null ){
			returnVal.put("shipmentId",shippingdocument.getShipmentId());
}

		if(shippingdocument.getShipmentItemSeqId() != null ){
			returnVal.put("shipmentItemSeqId",shippingdocument.getShipmentItemSeqId());
}

		if(shippingdocument.getShipmentPackageSeqId() != null ){
			returnVal.put("shipmentPackageSeqId",shippingdocument.getShipmentPackageSeqId());
}

		if(shippingdocument.getDescription() != null ){
			returnVal.put("description",shippingdocument.getDescription());
}

		return returnVal;
}


	public static ShippingDocument map(Map<String, Object> fields) {

		ShippingDocument returnVal = new ShippingDocument();

		if(fields.get("documentId") != null) {
			returnVal.setDocumentId((String) fields.get("documentId"));
}

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("shipmentPackageSeqId") != null) {
			returnVal.setShipmentPackageSeqId((String) fields.get("shipmentPackageSeqId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ShippingDocument mapstrstr(Map<String, String> fields) throws Exception {

		ShippingDocument returnVal = new ShippingDocument();

		if(fields.get("documentId") != null) {
			returnVal.setDocumentId((String) fields.get("documentId"));
}

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("shipmentPackageSeqId") != null) {
			returnVal.setShipmentPackageSeqId((String) fields.get("shipmentPackageSeqId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ShippingDocument map(GenericValue val) {

ShippingDocument returnVal = new ShippingDocument();
		returnVal.setDocumentId(val.getString("documentId"));
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setShipmentItemSeqId(val.getString("shipmentItemSeqId"));
		returnVal.setShipmentPackageSeqId(val.getString("shipmentPackageSeqId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ShippingDocument map(HttpServletRequest request) throws Exception {

		ShippingDocument returnVal = new ShippingDocument();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("documentId")) {
returnVal.setDocumentId(request.getParameter("documentId"));
}

		if(paramMap.containsKey("shipmentId"))  {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}
		if(paramMap.containsKey("shipmentItemSeqId"))  {
returnVal.setShipmentItemSeqId(request.getParameter("shipmentItemSeqId"));
}
		if(paramMap.containsKey("shipmentPackageSeqId"))  {
returnVal.setShipmentPackageSeqId(request.getParameter("shipmentPackageSeqId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
