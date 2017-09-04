package com.skytala.eCommerce.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class OrderHeader {

	private String orderId;
	private String orderTypeId;
	private String orderName;
	private String externalId;
	private String salesChannelEnumId;
	private Timestamp orderDate;
	private Boolean priority;
	private Timestamp entryDate;
	private Timestamp pickSheetPrintedDate;
	private String visitId;
	private String statusId;
	private String createdBy;
	private String firstAttemptOrderId;
	private String currencyUom;
	private String syncStatusId;
	private String billingAccountId;
	private String originFacilityId;
	private String webSiteId;
	private String productStoreId;
	private String terminalId;
	private String transactionId;
	private String autoOrderShoppingListId;
	private Boolean needsInventoryIssuance;
	private Boolean isRushOrder;
	private String internalCode;
	private BigDecimal remainingSubTotal;
	private BigDecimal grandTotal;
	private Boolean isViewed;
	private Boolean invoicePerShipment;

	public OrderHeader() {
		//when implementing a more complex ecommerce you might want to change following settings
		this.setOrderTypeId("SALES_ORDER");
		this.setSalesChannelEnumId("WEB_SALES_CHANNEL");
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		currentTime.setNanos(0);
		this.setOrderDate(currentTime);
		this.setEntryDate(currentTime);
		this.setStatusId("ORDER_HOLD");
		this.setCurrencyUom("EUR");
		this.setProductStoreId("10000");
	}
	


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderTypeId() {
		return orderTypeId;
	}

	public void setOrderTypeId(String orderTypeId) {
		this.orderTypeId = orderTypeId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getSalesChannelEnumId() {
		return salesChannelEnumId;
	}

	public void setSalesChannelEnumId(String salesChannelEnumId) {
		this.salesChannelEnumId = salesChannelEnumId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public Boolean getPriority() {
		return priority;
	}

	public void setPriority(Boolean priority) {
		this.priority = priority;
	}

	public Timestamp getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Timestamp entryDate) {
		this.entryDate = entryDate;
	}

	public Timestamp getPickSheetPrintedDate() {
		return pickSheetPrintedDate;
	}

	public void setPickSheetPrintedDate(Timestamp pickSheetPrintedDate) {
		this.pickSheetPrintedDate = pickSheetPrintedDate;
	}

	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getFirstAttemptOrderId() {
		return firstAttemptOrderId;
	}

	public void setFirstAttemptOrderId(String firstAttemptOrderId) {
		this.firstAttemptOrderId = firstAttemptOrderId;
	}

	public String getCurrencyUom() {
		return currencyUom;
	}

	public void setCurrencyUom(String currencyUom) {
		this.currencyUom = currencyUom;
	}

	public String getSyncStatusId() {
		return syncStatusId;
	}

	public void setSyncStatusId(String syncStatusId) {
		this.syncStatusId = syncStatusId;
	}

	public String getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(String billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public String getOriginFacilityId() {
		return originFacilityId;
	}

	public void setOriginFacilityId(String originFacilityId) {
		this.originFacilityId = originFacilityId;
	}

	public String getWebSiteId() {
		return webSiteId;
	}

	public void setWebSiteId(String webSiteId) {
		this.webSiteId = webSiteId;
	}

	public String getProductStoreId() {
		return productStoreId;
	}

	public void setProductStoreId(String productStoreId) {
		this.productStoreId = productStoreId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getAutoOrderShoppingListId() {
		return autoOrderShoppingListId;
	}

	public void setAutoOrderShoppingListId(String autoOrderShoppingListId) {
		this.autoOrderShoppingListId = autoOrderShoppingListId;
	}

	public Boolean getNeedsInventoryIssuance() {
		return needsInventoryIssuance;
	}

	public void setNeedsInventoryIssuance(Boolean needsInventoryIssuance) {
		this.needsInventoryIssuance = needsInventoryIssuance;
	}

	public Boolean getIsRushOrder() {
		return isRushOrder;
	}

	public void setIsRushOrder(Boolean isRushOrder) {
		this.isRushOrder = isRushOrder;
	}

	public String getInternalCode() {
		return internalCode;
	}

	public void setInternalCode(String internalCode) {
		this.internalCode = internalCode;
	}

	public BigDecimal getRemainingSubTotal() {
		return remainingSubTotal;
	}

	public void setRemainingSubTotal(BigDecimal remainingSubTotal) {
		this.remainingSubTotal = remainingSubTotal;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Boolean getIsViewed() {
		return isViewed;
	}

	public void setIsViewed(Boolean isViewed) {
		this.isViewed = isViewed;
	}

	public Boolean getInvoicePerShipment() {
		return invoicePerShipment;
	}

	public void setInvoicePerShipment(Boolean invoicePerShipment) {
		this.invoicePerShipment = invoicePerShipment;
	}

}
