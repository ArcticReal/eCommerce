package com.skytala.eCommerce.domain.order.relations.returnHeader.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnHeader.model.ReturnHeader;

public class ReturnHeaderMapper  {


	public static Map<String, Object> map(ReturnHeader returnheader) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(returnheader.getReturnId() != null ){
			returnVal.put("returnId",returnheader.getReturnId());
}

		if(returnheader.getReturnHeaderTypeId() != null ){
			returnVal.put("returnHeaderTypeId",returnheader.getReturnHeaderTypeId());
}

		if(returnheader.getStatusId() != null ){
			returnVal.put("statusId",returnheader.getStatusId());
}

		if(returnheader.getCreatedBy() != null ){
			returnVal.put("createdBy",returnheader.getCreatedBy());
}

		if(returnheader.getFromPartyId() != null ){
			returnVal.put("fromPartyId",returnheader.getFromPartyId());
}

		if(returnheader.getToPartyId() != null ){
			returnVal.put("toPartyId",returnheader.getToPartyId());
}

		if(returnheader.getPaymentMethodId() != null ){
			returnVal.put("paymentMethodId",returnheader.getPaymentMethodId());
}

		if(returnheader.getFinAccountId() != null ){
			returnVal.put("finAccountId",returnheader.getFinAccountId());
}

		if(returnheader.getBillingAccountId() != null ){
			returnVal.put("billingAccountId",returnheader.getBillingAccountId());
}

		if(returnheader.getEntryDate() != null ){
			returnVal.put("entryDate",returnheader.getEntryDate());
}

		if(returnheader.getOriginContactMechId() != null ){
			returnVal.put("originContactMechId",returnheader.getOriginContactMechId());
}

		if(returnheader.getDestinationFacilityId() != null ){
			returnVal.put("destinationFacilityId",returnheader.getDestinationFacilityId());
}

		if(returnheader.getNeedsInventoryReceive() != null ){
			returnVal.put("needsInventoryReceive",returnheader.getNeedsInventoryReceive());
}

		if(returnheader.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",returnheader.getCurrencyUomId());
}

		if(returnheader.getSupplierRmaId() != null ){
			returnVal.put("supplierRmaId",returnheader.getSupplierRmaId());
}

		return returnVal;
}


	public static ReturnHeader map(Map<String, Object> fields) {

		ReturnHeader returnVal = new ReturnHeader();

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnHeaderTypeId") != null) {
			returnVal.setReturnHeaderTypeId((String) fields.get("returnHeaderTypeId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("createdBy") != null) {
			returnVal.setCreatedBy((String) fields.get("createdBy"));
}

		if(fields.get("fromPartyId") != null) {
			returnVal.setFromPartyId((String) fields.get("fromPartyId"));
}

		if(fields.get("toPartyId") != null) {
			returnVal.setToPartyId((String) fields.get("toPartyId"));
}

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
}

		if(fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
}

		if(fields.get("entryDate") != null) {
			returnVal.setEntryDate((Timestamp) fields.get("entryDate"));
}

		if(fields.get("originContactMechId") != null) {
			returnVal.setOriginContactMechId((String) fields.get("originContactMechId"));
}

		if(fields.get("destinationFacilityId") != null) {
			returnVal.setDestinationFacilityId((String) fields.get("destinationFacilityId"));
}

		if(fields.get("needsInventoryReceive") != null) {
			returnVal.setNeedsInventoryReceive((boolean) fields.get("needsInventoryReceive"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("supplierRmaId") != null) {
			returnVal.setSupplierRmaId((String) fields.get("supplierRmaId"));
}


		return returnVal;
 } 
	public static ReturnHeader mapstrstr(Map<String, String> fields) throws Exception {

		ReturnHeader returnVal = new ReturnHeader();

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnHeaderTypeId") != null) {
			returnVal.setReturnHeaderTypeId((String) fields.get("returnHeaderTypeId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("createdBy") != null) {
			returnVal.setCreatedBy((String) fields.get("createdBy"));
}

		if(fields.get("fromPartyId") != null) {
			returnVal.setFromPartyId((String) fields.get("fromPartyId"));
}

		if(fields.get("toPartyId") != null) {
			returnVal.setToPartyId((String) fields.get("toPartyId"));
}

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
}

		if(fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
}

		if(fields.get("entryDate") != null) {
String buf = fields.get("entryDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEntryDate(ibuf);
}

		if(fields.get("originContactMechId") != null) {
			returnVal.setOriginContactMechId((String) fields.get("originContactMechId"));
}

		if(fields.get("destinationFacilityId") != null) {
			returnVal.setDestinationFacilityId((String) fields.get("destinationFacilityId"));
}

		if(fields.get("needsInventoryReceive") != null) {
String buf;
buf = fields.get("needsInventoryReceive");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setNeedsInventoryReceive(ibuf);
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("supplierRmaId") != null) {
			returnVal.setSupplierRmaId((String) fields.get("supplierRmaId"));
}


		return returnVal;
 } 
	public static ReturnHeader map(GenericValue val) {

ReturnHeader returnVal = new ReturnHeader();
		returnVal.setReturnId(val.getString("returnId"));
		returnVal.setReturnHeaderTypeId(val.getString("returnHeaderTypeId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setCreatedBy(val.getString("createdBy"));
		returnVal.setFromPartyId(val.getString("fromPartyId"));
		returnVal.setToPartyId(val.getString("toPartyId"));
		returnVal.setPaymentMethodId(val.getString("paymentMethodId"));
		returnVal.setFinAccountId(val.getString("finAccountId"));
		returnVal.setBillingAccountId(val.getString("billingAccountId"));
		returnVal.setEntryDate(val.getTimestamp("entryDate"));
		returnVal.setOriginContactMechId(val.getString("originContactMechId"));
		returnVal.setDestinationFacilityId(val.getString("destinationFacilityId"));
		returnVal.setNeedsInventoryReceive(val.getBoolean("needsInventoryReceive"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setSupplierRmaId(val.getString("supplierRmaId"));


return returnVal;

}

public static ReturnHeader map(HttpServletRequest request) throws Exception {

		ReturnHeader returnVal = new ReturnHeader();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("returnId")) {
returnVal.setReturnId(request.getParameter("returnId"));
}

		if(paramMap.containsKey("returnHeaderTypeId"))  {
returnVal.setReturnHeaderTypeId(request.getParameter("returnHeaderTypeId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("createdBy"))  {
returnVal.setCreatedBy(request.getParameter("createdBy"));
}
		if(paramMap.containsKey("fromPartyId"))  {
returnVal.setFromPartyId(request.getParameter("fromPartyId"));
}
		if(paramMap.containsKey("toPartyId"))  {
returnVal.setToPartyId(request.getParameter("toPartyId"));
}
		if(paramMap.containsKey("paymentMethodId"))  {
returnVal.setPaymentMethodId(request.getParameter("paymentMethodId"));
}
		if(paramMap.containsKey("finAccountId"))  {
returnVal.setFinAccountId(request.getParameter("finAccountId"));
}
		if(paramMap.containsKey("billingAccountId"))  {
returnVal.setBillingAccountId(request.getParameter("billingAccountId"));
}
		if(paramMap.containsKey("entryDate"))  {
String buf = request.getParameter("entryDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEntryDate(ibuf);
}
		if(paramMap.containsKey("originContactMechId"))  {
returnVal.setOriginContactMechId(request.getParameter("originContactMechId"));
}
		if(paramMap.containsKey("destinationFacilityId"))  {
returnVal.setDestinationFacilityId(request.getParameter("destinationFacilityId"));
}
		if(paramMap.containsKey("needsInventoryReceive"))  {
String buf = request.getParameter("needsInventoryReceive");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setNeedsInventoryReceive(ibuf);
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("supplierRmaId"))  {
returnVal.setSupplierRmaId(request.getParameter("supplierRmaId"));
}
return returnVal;

}
}
