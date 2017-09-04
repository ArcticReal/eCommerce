package com.skytala.eCommerce.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ofbiz.entity.GenericValue;

public class OrderHeaderMapper {

	public static Map<String, Object> map(OrderHeader orderheader) {

		Map<String, Object> returnVal = new HashMap<String, Object>();

		if (orderheader.getOrderId() != null) {
			returnVal.put("orderId", orderheader.getOrderId());
		}

		if (orderheader.getOrderTypeId() != null) {
			returnVal.put("orderTypeId", orderheader.getOrderTypeId());
		}

		if (orderheader.getOrderName() != null) {
			returnVal.put("orderName", orderheader.getOrderName());
		}

		if (orderheader.getExternalId() != null) {
			returnVal.put("externalId", orderheader.getExternalId());
		}

		if (orderheader.getSalesChannelEnumId() != null) {
			returnVal.put("salesChannelEnumId", orderheader.getSalesChannelEnumId());
		}

		if (orderheader.getOrderDate() != null) {
			returnVal.put("orderDate", orderheader.getOrderDate());
		}

		if (orderheader.getPriority() != null) {
			returnVal.put("priority", orderheader.getPriority());
		}

		if (orderheader.getEntryDate() != null) {
			returnVal.put("entryDate", orderheader.getEntryDate());
		}

		if (orderheader.getPickSheetPrintedDate() != null) {
			returnVal.put("pickSheetPrintedDate", orderheader.getPickSheetPrintedDate());
		}

		if (orderheader.getVisitId() != null) {
			returnVal.put("visitId", orderheader.getVisitId());
		}

		if (orderheader.getStatusId() != null) {
			returnVal.put("statusId", orderheader.getStatusId());
		}

		if (orderheader.getCreatedBy() != null) {
			returnVal.put("createdBy", orderheader.getCreatedBy());
		}

		if (orderheader.getFirstAttemptOrderId() != null) {
			returnVal.put("firstAttemptOrderId", orderheader.getFirstAttemptOrderId());
		}

		if (orderheader.getCurrencyUom() != null) {
			returnVal.put("currencyUom", orderheader.getCurrencyUom());
		}

		if (orderheader.getSyncStatusId() != null) {
			returnVal.put("syncStatusId", orderheader.getSyncStatusId());
		}

		if (orderheader.getBillingAccountId() != null) {
			returnVal.put("billingAccountId", orderheader.getBillingAccountId());
		}

		if (orderheader.getOriginFacilityId() != null) {
			returnVal.put("originFacilityId", orderheader.getOriginFacilityId());
		}

		if (orderheader.getWebSiteId() != null) {
			returnVal.put("webSiteId", orderheader.getWebSiteId());
		}

		if (orderheader.getProductStoreId() != null) {
			returnVal.put("productStoreId", orderheader.getProductStoreId());
		}

		if (orderheader.getTerminalId() != null) {
			returnVal.put("terminalId", orderheader.getTerminalId());
		}

		if (orderheader.getTransactionId() != null) {
			returnVal.put("transactionId", orderheader.getTransactionId());
		}

		if (orderheader.getAutoOrderShoppingListId() != null) {
			returnVal.put("autoOrderShoppingListId", orderheader.getAutoOrderShoppingListId());
		}

		if (orderheader.getNeedsInventoryIssuance() != null) {
			returnVal.put("needsInventoryIssuance", orderheader.getNeedsInventoryIssuance());
		}

		if (orderheader.getIsRushOrder() != null) {
			returnVal.put("isRushOrder", orderheader.getIsRushOrder());
		}

		if (orderheader.getInternalCode() != null) {
			returnVal.put("internalCode", orderheader.getInternalCode());
		}

		if (orderheader.getRemainingSubTotal() != null) {
			returnVal.put("remainingSubTotal", orderheader.getRemainingSubTotal());
		}

		if (orderheader.getGrandTotal() != null) {
			returnVal.put("grandTotal", orderheader.getGrandTotal());
		}

		if (orderheader.getIsViewed() != null) {
			returnVal.put("isViewed", orderheader.getIsViewed());
		}

		if (orderheader.getInvoicePerShipment() != null) {
			returnVal.put("invoicePerShipment", orderheader.getInvoicePerShipment());
		}

		return returnVal;
	}

	public static OrderHeader map(Map<String, Object> fields) {

		OrderHeader returnVal = new OrderHeader();

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
			returnVal.setOrderDate((Timestamp) fields.get("orderDate"));
		}

		if (fields.get("priority") != null) {
			returnVal.setPriority((boolean) fields.get("priority"));
		}

		if (fields.get("entryDate") != null) {
			returnVal.setEntryDate((Timestamp) fields.get("entryDate"));
		}

		if (fields.get("pickSheetPrintedDate") != null) {
			returnVal.setPickSheetPrintedDate((Timestamp) fields.get("pickSheetPrintedDate"));
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
			returnVal.setRemainingSubTotal((BigDecimal) fields.get("remainingSubTotal"));
		}

		if (fields.get("grandTotal") != null) {
			returnVal.setGrandTotal((BigDecimal) fields.get("grandTotal"));
		}

		if (fields.get("isViewed") != null) {
			returnVal.setIsViewed((boolean) fields.get("isViewed"));
		}

		if (fields.get("invoicePerShipment") != null) {
			returnVal.setInvoicePerShipment((boolean) fields.get("invoicePerShipment"));
		}

		return returnVal;
	}

	public static OrderHeader mapstrstr(Map<String, String> fields) throws Exception {

		OrderHeader returnVal = new OrderHeader();

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
			Timestamp ibuf = Timestamp.valueOf(buf);
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
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEntryDate(ibuf);
		}

		if (fields.get("pickSheetPrintedDate") != null) {
			String buf = fields.get("pickSheetPrintedDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
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
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setRemainingSubTotal(bd);
		}

		if (fields.get("grandTotal") != null) {
			String buf;
			buf = fields.get("grandTotal");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setGrandTotal(bd);
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

	public static OrderHeader map(GenericValue val) {

		OrderHeader returnVal = new OrderHeader();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderTypeId(val.getString("orderTypeId"));
		returnVal.setOrderName(val.getString("orderName"));
		returnVal.setExternalId(val.getString("externalId"));
		returnVal.setSalesChannelEnumId(val.getString("salesChannelEnumId"));
		returnVal.setOrderDate(val.getTimestamp("orderDate"));
		returnVal.setPriority(val.getBoolean("priority"));
		returnVal.setEntryDate(val.getTimestamp("entryDate"));
		returnVal.setPickSheetPrintedDate(val.getTimestamp("pickSheetPrintedDate"));
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
		returnVal.setRemainingSubTotal(val.getBigDecimal("remainingSubTotal"));
		returnVal.setGrandTotal(val.getBigDecimal("grandTotal"));
		returnVal.setIsViewed(val.getBoolean("isViewed"));
		returnVal.setInvoicePerShipment(val.getBoolean("invoicePerShipment"));

		return returnVal;

	}

	public static OrderHeader map(HttpServletRequest request) throws Exception {

		OrderHeader returnVal = new OrderHeader();

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
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setOrderDate(ibuf);
		}
		if (paramMap.containsKey("priority")) {
			String buf = request.getParameter("priority");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setPriority(ibuf);
		}
		if (paramMap.containsKey("entryDate")) {
			String buf = request.getParameter("entryDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEntryDate(ibuf);
		}
		if (paramMap.containsKey("pickSheetPrintedDate")) {
			String buf = request.getParameter("pickSheetPrintedDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
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
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setRemainingSubTotal(bd);
		}
		if (paramMap.containsKey("grandTotal")) {
			String buf = request.getParameter("grandTotal");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setGrandTotal(bd);
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
