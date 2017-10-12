package com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.model.ShipmentItemBilling;

public class ShipmentItemBillingMapper  {


	public static Map<String, Object> map(ShipmentItemBilling shipmentitembilling) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentitembilling.getShipmentId() != null ){
			returnVal.put("shipmentId",shipmentitembilling.getShipmentId());
}

		if(shipmentitembilling.getShipmentItemSeqId() != null ){
			returnVal.put("shipmentItemSeqId",shipmentitembilling.getShipmentItemSeqId());
}

		if(shipmentitembilling.getInvoiceId() != null ){
			returnVal.put("invoiceId",shipmentitembilling.getInvoiceId());
}

		if(shipmentitembilling.getInvoiceItemSeqId() != null ){
			returnVal.put("invoiceItemSeqId",shipmentitembilling.getInvoiceItemSeqId());
}

		return returnVal;
}


	public static ShipmentItemBilling map(Map<String, Object> fields) {

		ShipmentItemBilling returnVal = new ShipmentItemBilling();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}


		return returnVal;
 } 
	public static ShipmentItemBilling mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentItemBilling returnVal = new ShipmentItemBilling();

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}


		return returnVal;
 } 
	public static ShipmentItemBilling map(GenericValue val) {

ShipmentItemBilling returnVal = new ShipmentItemBilling();
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setShipmentItemSeqId(val.getString("shipmentItemSeqId"));
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setInvoiceItemSeqId(val.getString("invoiceItemSeqId"));


return returnVal;

}

public static ShipmentItemBilling map(HttpServletRequest request) throws Exception {

		ShipmentItemBilling returnVal = new ShipmentItemBilling();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentId")) {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}

		if(paramMap.containsKey("shipmentItemSeqId"))  {
returnVal.setShipmentItemSeqId(request.getParameter("shipmentItemSeqId"));
}
		if(paramMap.containsKey("invoiceId"))  {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}
		if(paramMap.containsKey("invoiceItemSeqId"))  {
returnVal.setInvoiceItemSeqId(request.getParameter("invoiceItemSeqId"));
}
return returnVal;

}
}
