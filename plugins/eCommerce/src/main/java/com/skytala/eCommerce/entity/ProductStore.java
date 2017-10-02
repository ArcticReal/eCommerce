package com.skytala.eCommerce.entity;

import java.io.Serializable;
import java.util.Map;

public class ProductStore implements Serializable {

	private static final long serialVersionUID = 1L;
	private String productStoreId;
	private String primaryStoreGroupId;
	private String storeName;
	private String companyName;
	private String title;
	private String subtitle;
	private String payToPartyId;
	private Long daysToCancelNonPay;
	private Boolean manualAuthIsCapture;
	private Boolean prorateShipping;
	private Boolean prorateTaxes;
	private Boolean viewCartOnAdd;
	private Boolean autoSaveCart;
	private Boolean autoApproveReviews;
	private Boolean isDemoStore;
	private Boolean isImmediatelyFulfilled;
	private String inventoryFacilityId;
	private Boolean oneInventoryFacility;
	private Boolean checkInventory;
	private Boolean reserveInventory;
	private String reserveOrderEnumId;
	private Boolean requireInventory;
	private Boolean balanceResOnOrderCreation;
	private String requirementMethodEnumId;
	private String orderNumberPrefix;
	private String defaultLocaleString;
	private String defaultCurrencyUomId;
	private String defaultTimeZoneString;
	private String defaultSalesChannelEnumId;
	private Boolean allowPassword;
	private String defaultPassword;
	private Boolean explodeOrderItems;
	private Boolean checkGcBalance;
	private Boolean retryFailedAuths;
	private String headerApprovedStatus;
	private String itemApprovedStatus;
	private String digitalItemApprovedStatus;
	private String headerDeclinedStatus;
	private String itemDeclinedStatus;
	private String headerCancelStatus;
	private String itemCancelStatus;
	private String authDeclinedMessage;
	private String authFraudMessage;
	private String authErrorMessage;
	private String visualThemeId;
	private String storeCreditAccountEnumId;
	private Boolean usePrimaryEmailUsername;
	private Boolean requireCustomerRole;
	private Boolean autoInvoiceDigitalItems;
	private Boolean reqShipAddrForDigItems;
	private Boolean showCheckoutGiftOptions;
	private Boolean selectPaymentTypePerItem;
	private Boolean showPricesWithVatTax;
	private Boolean showTaxIsExempt;
	private String vatTaxAuthGeoId;
	private String vatTaxAuthPartyId;
	private Boolean enableAutoSuggestionList;
	private Boolean enableDigProdUpload;
	private Boolean prodSearchExcludeVariants;
	private String digProdUploadCategoryId;
	private Boolean autoOrderCcTryExp;
	private Boolean autoOrderCcTryOtherCards;
	private Boolean autoOrderCcTryLaterNsf;
	private Long autoOrderCcTryLaterMax;
	private Long storeCreditValidDays;
	private Boolean autoApproveInvoice;
	private Boolean autoApproveOrder;
	private Boolean shipIfCaptureFails;
	private Boolean setOwnerUponIssuance;
	private Boolean reqReturnInventoryReceive;
	private Boolean addToCartRemoveIncompat;
	private Boolean addToCartReplaceUpsell;
	private Boolean splitPayPrefPerShpGrp;
	private Boolean managedByLot;
	private Boolean showOutOfStockProducts;
	private Boolean orderDecimalQuantity;
	private Boolean allowComment;
	private String oldStyleSheet;
	private String oldHeaderLogo;
	private String oldHeaderMiddleBackground;
	private String oldHeaderRightBackground;

	public String getProductStoreId() {
		return productStoreId;
	}

	public void setProductStoreId(String productStoreId) {
		this.productStoreId = productStoreId;
	}

	public String getPrimaryStoreGroupId() {
		return primaryStoreGroupId;
	}

	public void setPrimaryStoreGroupId(String primaryStoreGroupId) {
		this.primaryStoreGroupId = primaryStoreGroupId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getPayToPartyId() {
		return payToPartyId;
	}

	public void setPayToPartyId(String payToPartyId) {
		this.payToPartyId = payToPartyId;
	}

	public Long getDaysToCancelNonPay() {
		return daysToCancelNonPay;
	}

	public void setDaysToCancelNonPay(Long daysToCancelNonPay) {
		this.daysToCancelNonPay = daysToCancelNonPay;
	}

	public Boolean getManualAuthIsCapture() {
		return manualAuthIsCapture;
	}

	public void setManualAuthIsCapture(Boolean manualAuthIsCapture) {
		this.manualAuthIsCapture = manualAuthIsCapture;
	}

	public Boolean getProrateShipping() {
		return prorateShipping;
	}

	public void setProrateShipping(Boolean prorateShipping) {
		this.prorateShipping = prorateShipping;
	}

	public Boolean getProrateTaxes() {
		return prorateTaxes;
	}

	public void setProrateTaxes(Boolean prorateTaxes) {
		this.prorateTaxes = prorateTaxes;
	}

	public Boolean getViewCartOnAdd() {
		return viewCartOnAdd;
	}

	public void setViewCartOnAdd(Boolean viewCartOnAdd) {
		this.viewCartOnAdd = viewCartOnAdd;
	}

	public Boolean getAutoSaveCart() {
		return autoSaveCart;
	}

	public void setAutoSaveCart(Boolean autoSaveCart) {
		this.autoSaveCart = autoSaveCart;
	}

	public Boolean getAutoApproveReviews() {
		return autoApproveReviews;
	}

	public void setAutoApproveReviews(Boolean autoApproveReviews) {
		this.autoApproveReviews = autoApproveReviews;
	}

	public Boolean getIsDemoStore() {
		return isDemoStore;
	}

	public void setIsDemoStore(Boolean isDemoStore) {
		this.isDemoStore = isDemoStore;
	}

	public Boolean getIsImmediatelyFulfilled() {
		return isImmediatelyFulfilled;
	}

	public void setIsImmediatelyFulfilled(Boolean isImmediatelyFulfilled) {
		this.isImmediatelyFulfilled = isImmediatelyFulfilled;
	}

	public String getInventoryFacilityId() {
		return inventoryFacilityId;
	}

	public void setInventoryFacilityId(String inventoryFacilityId) {
		this.inventoryFacilityId = inventoryFacilityId;
	}

	public Boolean getOneInventoryFacility() {
		return oneInventoryFacility;
	}

	public void setOneInventoryFacility(Boolean oneInventoryFacility) {
		this.oneInventoryFacility = oneInventoryFacility;
	}

	public Boolean getCheckInventory() {
		return checkInventory;
	}

	public void setCheckInventory(Boolean checkInventory) {
		this.checkInventory = checkInventory;
	}

	public Boolean getReserveInventory() {
		return reserveInventory;
	}

	public void setReserveInventory(Boolean reserveInventory) {
		this.reserveInventory = reserveInventory;
	}

	public String getReserveOrderEnumId() {
		return reserveOrderEnumId;
	}

	public void setReserveOrderEnumId(String reserveOrderEnumId) {
		this.reserveOrderEnumId = reserveOrderEnumId;
	}

	public Boolean getRequireInventory() {
		return requireInventory;
	}

	public void setRequireInventory(Boolean requireInventory) {
		this.requireInventory = requireInventory;
	}

	public Boolean getBalanceResOnOrderCreation() {
		return balanceResOnOrderCreation;
	}

	public void setBalanceResOnOrderCreation(Boolean balanceResOnOrderCreation) {
		this.balanceResOnOrderCreation = balanceResOnOrderCreation;
	}

	public String getRequirementMethodEnumId() {
		return requirementMethodEnumId;
	}

	public void setRequirementMethodEnumId(String requirementMethodEnumId) {
		this.requirementMethodEnumId = requirementMethodEnumId;
	}

	public String getOrderNumberPrefix() {
		return orderNumberPrefix;
	}

	public void setOrderNumberPrefix(String orderNumberPrefix) {
		this.orderNumberPrefix = orderNumberPrefix;
	}

	public String getDefaultLocaleString() {
		return defaultLocaleString;
	}

	public void setDefaultLocaleString(String defaultLocaleString) {
		this.defaultLocaleString = defaultLocaleString;
	}

	public String getDefaultCurrencyUomId() {
		return defaultCurrencyUomId;
	}

	public void setDefaultCurrencyUomId(String defaultCurrencyUomId) {
		this.defaultCurrencyUomId = defaultCurrencyUomId;
	}

	public String getDefaultTimeZoneString() {
		return defaultTimeZoneString;
	}

	public void setDefaultTimeZoneString(String defaultTimeZoneString) {
		this.defaultTimeZoneString = defaultTimeZoneString;
	}

	public String getDefaultSalesChannelEnumId() {
		return defaultSalesChannelEnumId;
	}

	public void setDefaultSalesChannelEnumId(String defaultSalesChannelEnumId) {
		this.defaultSalesChannelEnumId = defaultSalesChannelEnumId;
	}

	public Boolean getAllowPassword() {
		return allowPassword;
	}

	public void setAllowPassword(Boolean allowPassword) {
		this.allowPassword = allowPassword;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}

	public Boolean getExplodeOrderItems() {
		return explodeOrderItems;
	}

	public void setExplodeOrderItems(Boolean explodeOrderItems) {
		this.explodeOrderItems = explodeOrderItems;
	}

	public Boolean getCheckGcBalance() {
		return checkGcBalance;
	}

	public void setCheckGcBalance(Boolean checkGcBalance) {
		this.checkGcBalance = checkGcBalance;
	}

	public Boolean getRetryFailedAuths() {
		return retryFailedAuths;
	}

	public void setRetryFailedAuths(Boolean retryFailedAuths) {
		this.retryFailedAuths = retryFailedAuths;
	}

	public String getHeaderApprovedStatus() {
		return headerApprovedStatus;
	}

	public void setHeaderApprovedStatus(String headerApprovedStatus) {
		this.headerApprovedStatus = headerApprovedStatus;
	}

	public String getItemApprovedStatus() {
		return itemApprovedStatus;
	}

	public void setItemApprovedStatus(String itemApprovedStatus) {
		this.itemApprovedStatus = itemApprovedStatus;
	}

	public String getDigitalItemApprovedStatus() {
		return digitalItemApprovedStatus;
	}

	public void setDigitalItemApprovedStatus(String digitalItemApprovedStatus) {
		this.digitalItemApprovedStatus = digitalItemApprovedStatus;
	}

	public String getHeaderDeclinedStatus() {
		return headerDeclinedStatus;
	}

	public void setHeaderDeclinedStatus(String headerDeclinedStatus) {
		this.headerDeclinedStatus = headerDeclinedStatus;
	}

	public String getItemDeclinedStatus() {
		return itemDeclinedStatus;
	}

	public void setItemDeclinedStatus(String itemDeclinedStatus) {
		this.itemDeclinedStatus = itemDeclinedStatus;
	}

	public String getHeaderCancelStatus() {
		return headerCancelStatus;
	}

	public void setHeaderCancelStatus(String headerCancelStatus) {
		this.headerCancelStatus = headerCancelStatus;
	}

	public String getItemCancelStatus() {
		return itemCancelStatus;
	}

	public void setItemCancelStatus(String itemCancelStatus) {
		this.itemCancelStatus = itemCancelStatus;
	}

	public String getAuthDeclinedMessage() {
		return authDeclinedMessage;
	}

	public void setAuthDeclinedMessage(String authDeclinedMessage) {
		this.authDeclinedMessage = authDeclinedMessage;
	}

	public String getAuthFraudMessage() {
		return authFraudMessage;
	}

	public void setAuthFraudMessage(String authFraudMessage) {
		this.authFraudMessage = authFraudMessage;
	}

	public String getAuthErrorMessage() {
		return authErrorMessage;
	}

	public void setAuthErrorMessage(String authErrorMessage) {
		this.authErrorMessage = authErrorMessage;
	}

	public String getVisualThemeId() {
		return visualThemeId;
	}

	public void setVisualThemeId(String visualThemeId) {
		this.visualThemeId = visualThemeId;
	}

	public String getStoreCreditAccountEnumId() {
		return storeCreditAccountEnumId;
	}

	public void setStoreCreditAccountEnumId(String storeCreditAccountEnumId) {
		this.storeCreditAccountEnumId = storeCreditAccountEnumId;
	}

	public Boolean getUsePrimaryEmailUsername() {
		return usePrimaryEmailUsername;
	}

	public void setUsePrimaryEmailUsername(Boolean usePrimaryEmailUsername) {
		this.usePrimaryEmailUsername = usePrimaryEmailUsername;
	}

	public Boolean getRequireCustomerRole() {
		return requireCustomerRole;
	}

	public void setRequireCustomerRole(Boolean requireCustomerRole) {
		this.requireCustomerRole = requireCustomerRole;
	}

	public Boolean getAutoInvoiceDigitalItems() {
		return autoInvoiceDigitalItems;
	}

	public void setAutoInvoiceDigitalItems(Boolean autoInvoiceDigitalItems) {
		this.autoInvoiceDigitalItems = autoInvoiceDigitalItems;
	}

	public Boolean getReqShipAddrForDigItems() {
		return reqShipAddrForDigItems;
	}

	public void setReqShipAddrForDigItems(Boolean reqShipAddrForDigItems) {
		this.reqShipAddrForDigItems = reqShipAddrForDigItems;
	}

	public Boolean getShowCheckoutGiftOptions() {
		return showCheckoutGiftOptions;
	}

	public void setShowCheckoutGiftOptions(Boolean showCheckoutGiftOptions) {
		this.showCheckoutGiftOptions = showCheckoutGiftOptions;
	}

	public Boolean getSelectPaymentTypePerItem() {
		return selectPaymentTypePerItem;
	}

	public void setSelectPaymentTypePerItem(Boolean selectPaymentTypePerItem) {
		this.selectPaymentTypePerItem = selectPaymentTypePerItem;
	}

	public Boolean getShowPricesWithVatTax() {
		return showPricesWithVatTax;
	}

	public void setShowPricesWithVatTax(Boolean showPricesWithVatTax) {
		this.showPricesWithVatTax = showPricesWithVatTax;
	}

	public Boolean getShowTaxIsExempt() {
		return showTaxIsExempt;
	}

	public void setShowTaxIsExempt(Boolean showTaxIsExempt) {
		this.showTaxIsExempt = showTaxIsExempt;
	}

	public String getVatTaxAuthGeoId() {
		return vatTaxAuthGeoId;
	}

	public void setVatTaxAuthGeoId(String vatTaxAuthGeoId) {
		this.vatTaxAuthGeoId = vatTaxAuthGeoId;
	}

	public String getVatTaxAuthPartyId() {
		return vatTaxAuthPartyId;
	}

	public void setVatTaxAuthPartyId(String vatTaxAuthPartyId) {
		this.vatTaxAuthPartyId = vatTaxAuthPartyId;
	}

	public Boolean getEnableAutoSuggestionList() {
		return enableAutoSuggestionList;
	}

	public void setEnableAutoSuggestionList(Boolean enableAutoSuggestionList) {
		this.enableAutoSuggestionList = enableAutoSuggestionList;
	}

	public Boolean getEnableDigProdUpload() {
		return enableDigProdUpload;
	}

	public void setEnableDigProdUpload(Boolean enableDigProdUpload) {
		this.enableDigProdUpload = enableDigProdUpload;
	}

	public Boolean getProdSearchExcludeVariants() {
		return prodSearchExcludeVariants;
	}

	public void setProdSearchExcludeVariants(Boolean prodSearchExcludeVariants) {
		this.prodSearchExcludeVariants = prodSearchExcludeVariants;
	}

	public String getDigProdUploadCategoryId() {
		return digProdUploadCategoryId;
	}

	public void setDigProdUploadCategoryId(String digProdUploadCategoryId) {
		this.digProdUploadCategoryId = digProdUploadCategoryId;
	}

	public Boolean getAutoOrderCcTryExp() {
		return autoOrderCcTryExp;
	}

	public void setAutoOrderCcTryExp(Boolean autoOrderCcTryExp) {
		this.autoOrderCcTryExp = autoOrderCcTryExp;
	}

	public Boolean getAutoOrderCcTryOtherCards() {
		return autoOrderCcTryOtherCards;
	}

	public void setAutoOrderCcTryOtherCards(Boolean autoOrderCcTryOtherCards) {
		this.autoOrderCcTryOtherCards = autoOrderCcTryOtherCards;
	}

	public Boolean getAutoOrderCcTryLaterNsf() {
		return autoOrderCcTryLaterNsf;
	}

	public void setAutoOrderCcTryLaterNsf(Boolean autoOrderCcTryLaterNsf) {
		this.autoOrderCcTryLaterNsf = autoOrderCcTryLaterNsf;
	}

	public Long getAutoOrderCcTryLaterMax() {
		return autoOrderCcTryLaterMax;
	}

	public void setAutoOrderCcTryLaterMax(Long autoOrderCcTryLaterMax) {
		this.autoOrderCcTryLaterMax = autoOrderCcTryLaterMax;
	}

	public Long getStoreCreditValidDays() {
		return storeCreditValidDays;
	}

	public void setStoreCreditValidDays(Long storeCreditValidDays) {
		this.storeCreditValidDays = storeCreditValidDays;
	}

	public Boolean getAutoApproveInvoice() {
		return autoApproveInvoice;
	}

	public void setAutoApproveInvoice(Boolean autoApproveInvoice) {
		this.autoApproveInvoice = autoApproveInvoice;
	}

	public Boolean getAutoApproveOrder() {
		return autoApproveOrder;
	}

	public void setAutoApproveOrder(Boolean autoApproveOrder) {
		this.autoApproveOrder = autoApproveOrder;
	}

	public Boolean getShipIfCaptureFails() {
		return shipIfCaptureFails;
	}

	public void setShipIfCaptureFails(Boolean shipIfCaptureFails) {
		this.shipIfCaptureFails = shipIfCaptureFails;
	}

	public Boolean getSetOwnerUponIssuance() {
		return setOwnerUponIssuance;
	}

	public void setSetOwnerUponIssuance(Boolean setOwnerUponIssuance) {
		this.setOwnerUponIssuance = setOwnerUponIssuance;
	}

	public Boolean getReqReturnInventoryReceive() {
		return reqReturnInventoryReceive;
	}

	public void setReqReturnInventoryReceive(Boolean reqReturnInventoryReceive) {
		this.reqReturnInventoryReceive = reqReturnInventoryReceive;
	}

	public Boolean getAddToCartRemoveIncompat() {
		return addToCartRemoveIncompat;
	}

	public void setAddToCartRemoveIncompat(Boolean addToCartRemoveIncompat) {
		this.addToCartRemoveIncompat = addToCartRemoveIncompat;
	}

	public Boolean getAddToCartReplaceUpsell() {
		return addToCartReplaceUpsell;
	}

	public void setAddToCartReplaceUpsell(Boolean addToCartReplaceUpsell) {
		this.addToCartReplaceUpsell = addToCartReplaceUpsell;
	}

	public Boolean getSplitPayPrefPerShpGrp() {
		return splitPayPrefPerShpGrp;
	}

	public void setSplitPayPrefPerShpGrp(Boolean splitPayPrefPerShpGrp) {
		this.splitPayPrefPerShpGrp = splitPayPrefPerShpGrp;
	}

	public Boolean getManagedByLot() {
		return managedByLot;
	}

	public void setManagedByLot(Boolean managedByLot) {
		this.managedByLot = managedByLot;
	}

	public Boolean getShowOutOfStockProducts() {
		return showOutOfStockProducts;
	}

	public void setShowOutOfStockProducts(Boolean showOutOfStockProducts) {
		this.showOutOfStockProducts = showOutOfStockProducts;
	}

	public Boolean getOrderDecimalQuantity() {
		return orderDecimalQuantity;
	}

	public void setOrderDecimalQuantity(Boolean orderDecimalQuantity) {
		this.orderDecimalQuantity = orderDecimalQuantity;
	}

	public Boolean getAllowComment() {
		return allowComment;
	}

	public void setAllowComment(Boolean allowComment) {
		this.allowComment = allowComment;
	}

	public String getOldStyleSheet() {
		return oldStyleSheet;
	}

	public void setOldStyleSheet(String oldStyleSheet) {
		this.oldStyleSheet = oldStyleSheet;
	}

	public String getOldHeaderLogo() {
		return oldHeaderLogo;
	}

	public void setOldHeaderLogo(String oldHeaderLogo) {
		this.oldHeaderLogo = oldHeaderLogo;
	}

	public String getOldHeaderMiddleBackground() {
		return oldHeaderMiddleBackground;
	}

	public void setOldHeaderMiddleBackground(String oldHeaderMiddleBackground) {
		this.oldHeaderMiddleBackground = oldHeaderMiddleBackground;
	}

	public String getOldHeaderRightBackground() {
		return oldHeaderRightBackground;
	}

	public void setOldHeaderRightBackground(String oldHeaderRightBackground) {
		this.oldHeaderRightBackground = oldHeaderRightBackground;
	}

	public Map<String, Object> mapAttributeField() {
		return ProductStoreMapper.map(this);
	}
}
