package com.skytala.eCommerce.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ofbiz.entity.GenericValue;

public class OrderMapper {

	public static Map<String, Object> map(Order order) {

		Map<String, Object> returnVal = new HashMap<String, Object>();

		if (order.getOrderId() != null) {
			returnVal.put("orderId", order.getOrderId());
		}

		if (order.getOrderTypeId() != null) {
			returnVal.put("orderTypeId", order.getOrderTypeId());
		}

		if (order.getOrderName() != null) {
			returnVal.put("orderName", order.getOrderName());
		}

		if (order.getExternalId() != null) {
			returnVal.put("externalId", order.getExternalId());
		}

		if (order.getSalesChannelEnumId() != null) {
			returnVal.put("salesChannelEnumId", order.getSalesChannelEnumId());
		}

		if (order.getOrderDate() != null) {
			returnVal.put("orderDate", order.getOrderDate());
		}

		if (order.getPriority() != null) {
			returnVal.put("priority", order.getPriority());
		}

		if (order.getEntryDate() != null) {
			returnVal.put("entryDate", order.getEntryDate());
		}

		if (order.getPickSheetPrintedDate() != null) {
			returnVal.put("pickSheetPrintedDate", order.getPickSheetPrintedDate());
		}

		if (order.getVisitId() != null) {
			returnVal.put("visitId", order.getVisitId());
		}

		if (order.getStatusId() != null) {
			returnVal.put("statusId", order.getStatusId());
		}

		if (order.getCreatedBy() != null) {
			returnVal.put("createdBy", order.getCreatedBy());
		}

		if (order.getFirstAttemptOrderId() != null) {
			returnVal.put("firstAttemptOrderId", order.getFirstAttemptOrderId());
		}

		if (order.getCurrencyUom() != null) {
			returnVal.put("currencyUom", order.getCurrencyUom());
		}

		if (order.getSyncStatusId() != null) {
			returnVal.put("syncStatusId", order.getSyncStatusId());
		}

		if (order.getBillingAccountId() != null) {
			returnVal.put("billingAccountId", order.getBillingAccountId());
		}

		if (order.getOriginFacilityId() != null) {
			returnVal.put("originFacilityId", order.getOriginFacilityId());
		}

		if (order.getWebSiteId() != null) {
			returnVal.put("webSiteId", order.getWebSiteId());
		}

		if (order.getProductStoreId() != null) {
			returnVal.put("productStoreId", order.getProductStoreId());
		}

		if (order.getTerminalId() != null) {
			returnVal.put("terminalId", order.getTerminalId());
		}

		if (order.getTransactionId() != null) {
			returnVal.put("transactionId", order.getTransactionId());
		}

		if (order.getAutoOrderShoppingListId() != null) {
			returnVal.put("autoOrderShoppingListId", order.getAutoOrderShoppingListId());
		}

		if (order.getNeedsInventoryIssuance() != null) {
			returnVal.put("needsInventoryIssuance", order.getNeedsInventoryIssuance());
		}

		if (order.getIsRushOrder() != null) {
			returnVal.put("isRushOrder", order.getIsRushOrder());
		}

		if (order.getInternalCode() != null) {
			returnVal.put("internalCode", order.getInternalCode());
		}

		if (order.getRemainingSubTotal() != null) {
			returnVal.put("remainingSubTotal", order.getRemainingSubTotal());
		}

		if (order.getGrandTotal() != null) {
			returnVal.put("grandTotal", order.getGrandTotal());
		}

		if (order.getIsViewed() != null) {
			returnVal.put("isViewed", order.getIsViewed());
		}

		if (order.getInvoicePerShipment() != null) {
			returnVal.put("invoicePerShipment", order.getInvoicePerShipment());
		}

		return returnVal;
	}

	public static Order map(Map<String, Object> fields) {

		Order returnVal = new Order();

		if (fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
		}

		if (fields.get("orderTypeId") != null) {
			returnVal.setOrderTypeId((String) fields.get("orderTypeId"));
		}

		if (fields.get("orderName") != null) {
			returnVal.setOrderName((String) fields.get("orderName"));
		}

		if (fields.get("externalId") != null) {
			returnVal.setExternalId((String) fields.get("externalId"));
		}

		if (fields.get("salesChannelEnumId") != null) {
			returnVal.setSalesChannelEnumId((String) fields.get("salesChannelEnumId"));
		}

		if (fields.get("orderDate") != null) {
			returnVal.setOrderDate((Date) fields.get("orderDate"));
		}

		if (fields.get("priority") != null) {
			returnVal.setPriority((boolean) fields.get("priority"));
		}

		if (fields.get("entryDate") != null) {
			returnVal.setEntryDate((Date) fields.get("entryDate"));
		}

		if (fields.get("pickSheetPrintedDate") != null) {
			returnVal.setPickSheetPrintedDate((Date) fields.get("pickSheetPrintedDate"));
		}

		if (fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
		}

		if (fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
		}

		if (fields.get("createdBy") != null) {
			returnVal.setCreatedBy((String) fields.get("createdBy"));
		}

		if (fields.get("firstAttemptOrderId") != null) {
			returnVal.setFirstAttemptOrderId((String) fields.get("firstAttemptOrderId"));
		}

		if (fields.get("currencyUom") != null) {
			returnVal.setCurrencyUom((String) fields.get("currencyUom"));
		}

		if (fields.get("syncStatusId") != null) {
			returnVal.setSyncStatusId((String) fields.get("syncStatusId"));
		}

		if (fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
		}

		if (fields.get("originFacilityId") != null) {
			returnVal.setOriginFacilityId((String) fields.get("originFacilityId"));
		}

		if (fields.get("webSiteId") != null) {
			returnVal.setWebSiteId((String) fields.get("webSiteId"));
		}

		if (fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
		}

		if (fields.get("terminalId") != null) {
			returnVal.setTerminalId((String) fields.get("terminalId"));
		}

		if (fields.get("transactionId") != null) {
			returnVal.setTransactionId((String) fields.get("transactionId"));
		}

		if (fields.get("autoOrderShoppingListId") != null) {
			returnVal.setAutoOrderShoppingListId((String) fields.get("autoOrderShoppingListId"));
		}

		if (fields.get("needsInventoryIssuance") != null) {
			returnVal.setNeedsInventoryIssuance((boolean) fields.get("needsInventoryIssuance"));
		}

		if (fields.get("isRushOrder") != null) {
			returnVal.setIsRushOrder((boolean) fields.get("isRushOrder"));
		}

		if (fields.get("internalCode") != null) {
			returnVal.setInternalCode((String) fields.get("internalCode"));
		}

		if (fields.get("remainingSubTotal") != null) {
			returnVal.setRemainingSubTotal((float) fields.get("remainingSubTotal"));
		}

		if (fields.get("grandTotal") != null) {
			returnVal.setGrandTotal((float) fields.get("grandTotal"));
		}

		if (fields.get("isViewed") != null) {
			returnVal.setIsViewed((boolean) fields.get("isViewed"));
		}

		if (fields.get("invoicePerShipment") != null) {
			returnVal.setInvoicePerShipment((boolean) fields.get("invoicePerShipment"));
		}

		return returnVal;
	}

	public static Order mapstrstr(Map<String, String> fields) throws Exception {

		Order returnVal = new Order();

		if (fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
		}

		if (fields.get("orderTypeId") != null) {
			returnVal.setOrderTypeId((String) fields.get("orderTypeId"));
		}

		if (fields.get("orderName") != null) {
			returnVal.setOrderName((String) fields.get("orderName"));
		}

		if (fields.get("externalId") != null) {
			returnVal.setExternalId((String) fields.get("externalId"));
		}

		if (fields.get("salesChannelEnumId") != null) {
			returnVal.setSalesChannelEnumId((String) fields.get("salesChannelEnumId"));
		}

		if (fields.get("orderDate") != null) {
			String buf = fields.get("orderDate");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date ibuf = df.parse(buf);
			returnVal.setOrderDate(ibuf);
		}

		if (fields.get("priority") != null) {
			String buf;
			buf = fields.get("priority");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setPriority(ibuf);
		}

		if (fields.get("entryDate") != null) {
			String buf = fields.get("entryDate");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date ibuf = df.parse(buf);
			returnVal.setEntryDate(ibuf);
		}

		if (fields.get("pickSheetPrintedDate") != null) {
			String buf = fields.get("pickSheetPrintedDate");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date ibuf = df.parse(buf);
			returnVal.setPickSheetPrintedDate(ibuf);
		}

		if (fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
		}

		if (fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
		}

		if (fields.get("createdBy") != null) {
			returnVal.setCreatedBy((String) fields.get("createdBy"));
		}

		if (fields.get("firstAttemptOrderId") != null) {
			returnVal.setFirstAttemptOrderId((String) fields.get("firstAttemptOrderId"));
		}

		if (fields.get("currencyUom") != null) {
			returnVal.setCurrencyUom((String) fields.get("currencyUom"));
		}

		if (fields.get("syncStatusId") != null) {
			returnVal.setSyncStatusId((String) fields.get("syncStatusId"));
		}

		if (fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
		}

		if (fields.get("originFacilityId") != null) {
			returnVal.setOriginFacilityId((String) fields.get("originFacilityId"));
		}

		if (fields.get("webSiteId") != null) {
			returnVal.setWebSiteId((String) fields.get("webSiteId"));
		}

		if (fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
		}

		if (fields.get("terminalId") != null) {
			returnVal.setTerminalId((String) fields.get("terminalId"));
		}

		if (fields.get("transactionId") != null) {
			returnVal.setTransactionId((String) fields.get("transactionId"));
		}

		if (fields.get("autoOrderShoppingListId") != null) {
			returnVal.setAutoOrderShoppingListId((String) fields.get("autoOrderShoppingListId"));
		}

		if (fields.get("needsInventoryIssuance") != null) {
			String buf;
			buf = fields.get("needsInventoryIssuance");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setNeedsInventoryIssuance(ibuf);
		}

		if (fields.get("isRushOrder") != null) {
			String buf;
			buf = fields.get("isRushOrder");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsRushOrder(ibuf);
		}

		if (fields.get("internalCode") != null) {
			returnVal.setInternalCode((String) fields.get("internalCode"));
		}

		if (fields.get("remainingSubTotal") != null) {
			String buf;
			buf = fields.get("remainingSubTotal");
			float ibuf = Float.parseFloat(buf);
			returnVal.setRemainingSubTotal(ibuf);
		}

		if (fields.get("grandTotal") != null) {
			String buf;
			buf = fields.get("grandTotal");
			float ibuf = Float.parseFloat(buf);
			returnVal.setGrandTotal(ibuf);
		}

		if (fields.get("isViewed") != null) {
			String buf;
			buf = fields.get("isViewed");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsViewed(ibuf);
		}

		if (fields.get("invoicePerShipment") != null) {
			String buf;
			buf = fields.get("invoicePerShipment");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setInvoicePerShipment(ibuf);
		}

		return returnVal;
	}

	public static Order map(GenericValue val) {

		Order returnVal = new Order();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderTypeId(val.getString("orderTypeId"));
		returnVal.setOrderName(val.getString("orderName"));
		returnVal.setExternalId(val.getString("externalId"));
		returnVal.setSalesChannelEnumId(val.getString("salesChannelEnumId"));
		returnVal.setOrderDate(val.getDate("orderDate"));
		returnVal.setPriority(val.getBoolean("priority"));
		returnVal.setEntryDate(val.getDate("entryDate"));
		returnVal.setPickSheetPrintedDate(val.getDate("pickSheetPrintedDate"));
		returnVal.setVisitId(val.getString("visitId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setCreatedBy(val.getString("createdBy"));
		returnVal.setFirstAttemptOrderId(val.getString("firstAttemptOrderId"));
		returnVal.setCurrencyUom(val.getString("currencyUom"));
		returnVal.setSyncStatusId(val.getString("syncStatusId"));
		returnVal.setBillingAccountId(val.getString("billingAccountId"));
		returnVal.setOriginFacilityId(val.getString("originFacilityId"));
		returnVal.setWebSiteId(val.getString("webSiteId"));
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setTerminalId(val.getString("terminalId"));
		returnVal.setTransactionId(val.getString("transactionId"));
		returnVal.setAutoOrderShoppingListId(val.getString("autoOrderShoppingListId"));
		returnVal.setNeedsInventoryIssuance(val.getBoolean("needsInventoryIssuance"));
		returnVal.setIsRushOrder(val.getBoolean("isRushOrder"));
		returnVal.setInternalCode(val.getString("internalCode"));
		returnVal.setRemainingSubTotal(val.getFloat("remainingSubTotal"));
		returnVal.setGrandTotal(val.getFloat("grandTotal"));
		returnVal.setIsViewed(val.getBoolean("isViewed"));
		returnVal.setInvoicePerShipment(val.getBoolean("invoicePerShipment"));

		return returnVal;

	}

	public static Order map(HttpServletRequest request) throws Exception {

		Order returnVal = new Order();

		Map<String, String[]> paramMap = request.getParameterMap();

		if (!paramMap.containsKey("orderId")) {
			throw new Exception("Error! Id required");
		} else {
			returnVal.setOrderId(request.getParameter("productId"));
		}

		if (paramMap.containsKey("orderTypeId")) {
			returnVal.setOrderTypeId(request.getParameter("orderTypeId"));
		}
		if (paramMap.containsKey("orderName")) {
			returnVal.setOrderName(request.getParameter("orderName"));
		}
		if (paramMap.containsKey("externalId")) {
			returnVal.setExternalId(request.getParameter("externalId"));
		}
		if (paramMap.containsKey("salesChannelEnumId")) {
			returnVal.setSalesChannelEnumId(request.getParameter("salesChannelEnumId"));
		}
		if (paramMap.containsKey("orderDate")) {
			String buf = request.getParameter("orderDate");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date ibuf = df.parse(buf);
			returnVal.setOrderDate(ibuf);
		}
		if (paramMap.containsKey("priority")) {
			String buf = request.getParameter("priority");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setPriority(ibuf);
		}
		if (paramMap.containsKey("entryDate")) {
			String buf = request.getParameter("entryDate");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date ibuf = df.parse(buf);
			returnVal.setEntryDate(ibuf);
		}
		if (paramMap.containsKey("pickSheetPrintedDate")) {
			String buf = request.getParameter("pickSheetPrintedDate");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date ibuf = df.parse(buf);
			returnVal.setPickSheetPrintedDate(ibuf);
		}
		if (paramMap.containsKey("visitId")) {
			returnVal.setVisitId(request.getParameter("visitId"));
		}
		if (paramMap.containsKey("statusId")) {
			returnVal.setStatusId(request.getParameter("statusId"));
		}
		if (paramMap.containsKey("createdBy")) {
			returnVal.setCreatedBy(request.getParameter("createdBy"));
		}
		if (paramMap.containsKey("firstAttemptOrderId")) {
			returnVal.setFirstAttemptOrderId(request.getParameter("firstAttemptOrderId"));
		}
		if (paramMap.containsKey("currencyUom")) {
			returnVal.setCurrencyUom(request.getParameter("currencyUom"));
		}
		if (paramMap.containsKey("syncStatusId")) {
			returnVal.setSyncStatusId(request.getParameter("syncStatusId"));
		}
		if (paramMap.containsKey("billingAccountId")) {
			returnVal.setBillingAccountId(request.getParameter("billingAccountId"));
		}
		if (paramMap.containsKey("originFacilityId")) {
			returnVal.setOriginFacilityId(request.getParameter("originFacilityId"));
		}
		if (paramMap.containsKey("webSiteId")) {
			returnVal.setWebSiteId(request.getParameter("webSiteId"));
		}
		if (paramMap.containsKey("productStoreId")) {
			returnVal.setProductStoreId(request.getParameter("productStoreId"));
		}
		if (paramMap.containsKey("terminalId")) {
			returnVal.setTerminalId(request.getParameter("terminalId"));
		}
		if (paramMap.containsKey("transactionId")) {
			returnVal.setTransactionId(request.getParameter("transactionId"));
		}
		if (paramMap.containsKey("autoOrderShoppingListId")) {
			returnVal.setAutoOrderShoppingListId(request.getParameter("autoOrderShoppingListId"));
		}
		if (paramMap.containsKey("needsInventoryIssuance")) {
			String buf = request.getParameter("needsInventoryIssuance");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setNeedsInventoryIssuance(ibuf);
		}
		if (paramMap.containsKey("isRushOrder")) {
			String buf = request.getParameter("isRushOrder");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsRushOrder(ibuf);
		}
		if (paramMap.containsKey("internalCode")) {
			returnVal.setInternalCode(request.getParameter("internalCode"));
		}
		if (paramMap.containsKey("remainingSubTotal")) {
			String buf = request.getParameter("remainingSubTotal");
			Float ibuf = Float.parseFloat(buf);
			returnVal.setRemainingSubTotal(ibuf);
		}
		if (paramMap.containsKey("grandTotal")) {
			String buf = request.getParameter("grandTotal");
			Float ibuf = Float.parseFloat(buf);
			returnVal.setGrandTotal(ibuf);
		}
		if (paramMap.containsKey("isViewed")) {
			String buf = request.getParameter("isViewed");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsViewed(ibuf);
		}
		if (paramMap.containsKey("invoicePerShipment")) {
			String buf = request.getParameter("invoicePerShipment");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setInvoicePerShipment(ibuf);
		}
		return returnVal;

	}
}
