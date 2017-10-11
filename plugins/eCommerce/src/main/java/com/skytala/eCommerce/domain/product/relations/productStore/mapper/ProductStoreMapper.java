package com.skytala.eCommerce.domain.product.relations.productStore.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productStore.model.ProductStore;

public class ProductStoreMapper  {


	public static Map<String, Object> map(ProductStore productstore) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstore.getProductStoreId() != null ){
			returnVal.put("productStoreId",productstore.getProductStoreId());
}

		if(productstore.getPrimaryStoreGroupId() != null ){
			returnVal.put("primaryStoreGroupId",productstore.getPrimaryStoreGroupId());
}

		if(productstore.getStoreName() != null ){
			returnVal.put("storeName",productstore.getStoreName());
}

		if(productstore.getCompanyName() != null ){
			returnVal.put("companyName",productstore.getCompanyName());
}

		if(productstore.getTitle() != null ){
			returnVal.put("title",productstore.getTitle());
}

		if(productstore.getSubtitle() != null ){
			returnVal.put("subtitle",productstore.getSubtitle());
}

		if(productstore.getPayToPartyId() != null ){
			returnVal.put("payToPartyId",productstore.getPayToPartyId());
}

		if(productstore.getDaysToCancelNonPay() != null ){
			returnVal.put("daysToCancelNonPay",productstore.getDaysToCancelNonPay());
}

		if(productstore.getManualAuthIsCapture() != null ){
			returnVal.put("manualAuthIsCapture",productstore.getManualAuthIsCapture());
}

		if(productstore.getProrateShipping() != null ){
			returnVal.put("prorateShipping",productstore.getProrateShipping());
}

		if(productstore.getProrateTaxes() != null ){
			returnVal.put("prorateTaxes",productstore.getProrateTaxes());
}

		if(productstore.getViewCartOnAdd() != null ){
			returnVal.put("viewCartOnAdd",productstore.getViewCartOnAdd());
}

		if(productstore.getAutoSaveCart() != null ){
			returnVal.put("autoSaveCart",productstore.getAutoSaveCart());
}

		if(productstore.getAutoApproveReviews() != null ){
			returnVal.put("autoApproveReviews",productstore.getAutoApproveReviews());
}

		if(productstore.getIsDemoStore() != null ){
			returnVal.put("isDemoStore",productstore.getIsDemoStore());
}

		if(productstore.getIsImmediatelyFulfilled() != null ){
			returnVal.put("isImmediatelyFulfilled",productstore.getIsImmediatelyFulfilled());
}

		if(productstore.getInventoryFacilityId() != null ){
			returnVal.put("inventoryFacilityId",productstore.getInventoryFacilityId());
}

		if(productstore.getOneInventoryFacility() != null ){
			returnVal.put("oneInventoryFacility",productstore.getOneInventoryFacility());
}

		if(productstore.getCheckInventory() != null ){
			returnVal.put("checkInventory",productstore.getCheckInventory());
}

		if(productstore.getReserveInventory() != null ){
			returnVal.put("reserveInventory",productstore.getReserveInventory());
}

		if(productstore.getReserveOrderEnumId() != null ){
			returnVal.put("reserveOrderEnumId",productstore.getReserveOrderEnumId());
}

		if(productstore.getRequireInventory() != null ){
			returnVal.put("requireInventory",productstore.getRequireInventory());
}

		if(productstore.getBalanceResOnOrderCreation() != null ){
			returnVal.put("balanceResOnOrderCreation",productstore.getBalanceResOnOrderCreation());
}

		if(productstore.getRequirementMethodEnumId() != null ){
			returnVal.put("requirementMethodEnumId",productstore.getRequirementMethodEnumId());
}

		if(productstore.getOrderNumberPrefix() != null ){
			returnVal.put("orderNumberPrefix",productstore.getOrderNumberPrefix());
}

		if(productstore.getDefaultLocaleString() != null ){
			returnVal.put("defaultLocaleString",productstore.getDefaultLocaleString());
}

		if(productstore.getDefaultCurrencyUomId() != null ){
			returnVal.put("defaultCurrencyUomId",productstore.getDefaultCurrencyUomId());
}

		if(productstore.getDefaultTimeZoneString() != null ){
			returnVal.put("defaultTimeZoneString",productstore.getDefaultTimeZoneString());
}

		if(productstore.getDefaultSalesChannelEnumId() != null ){
			returnVal.put("defaultSalesChannelEnumId",productstore.getDefaultSalesChannelEnumId());
}

		if(productstore.getAllowPassword() != null ){
			returnVal.put("allowPassword",productstore.getAllowPassword());
}

		if(productstore.getDefaultPassword() != null ){
			returnVal.put("defaultPassword",productstore.getDefaultPassword());
}

		if(productstore.getExplodeOrderItems() != null ){
			returnVal.put("explodeOrderItems",productstore.getExplodeOrderItems());
}

		if(productstore.getCheckGcBalance() != null ){
			returnVal.put("checkGcBalance",productstore.getCheckGcBalance());
}

		if(productstore.getRetryFailedAuths() != null ){
			returnVal.put("retryFailedAuths",productstore.getRetryFailedAuths());
}

		if(productstore.getHeaderApprovedStatus() != null ){
			returnVal.put("headerApprovedStatus",productstore.getHeaderApprovedStatus());
}

		if(productstore.getItemApprovedStatus() != null ){
			returnVal.put("itemApprovedStatus",productstore.getItemApprovedStatus());
}

		if(productstore.getDigitalItemApprovedStatus() != null ){
			returnVal.put("digitalItemApprovedStatus",productstore.getDigitalItemApprovedStatus());
}

		if(productstore.getHeaderDeclinedStatus() != null ){
			returnVal.put("headerDeclinedStatus",productstore.getHeaderDeclinedStatus());
}

		if(productstore.getItemDeclinedStatus() != null ){
			returnVal.put("itemDeclinedStatus",productstore.getItemDeclinedStatus());
}

		if(productstore.getHeaderCancelStatus() != null ){
			returnVal.put("headerCancelStatus",productstore.getHeaderCancelStatus());
}

		if(productstore.getItemCancelStatus() != null ){
			returnVal.put("itemCancelStatus",productstore.getItemCancelStatus());
}

		if(productstore.getAuthDeclinedMessage() != null ){
			returnVal.put("authDeclinedMessage",productstore.getAuthDeclinedMessage());
}

		if(productstore.getAuthFraudMessage() != null ){
			returnVal.put("authFraudMessage",productstore.getAuthFraudMessage());
}

		if(productstore.getAuthErrorMessage() != null ){
			returnVal.put("authErrorMessage",productstore.getAuthErrorMessage());
}

		if(productstore.getVisualThemeId() != null ){
			returnVal.put("visualThemeId",productstore.getVisualThemeId());
}

		if(productstore.getStoreCreditAccountEnumId() != null ){
			returnVal.put("storeCreditAccountEnumId",productstore.getStoreCreditAccountEnumId());
}

		if(productstore.getUsePrimaryEmailUsername() != null ){
			returnVal.put("usePrimaryEmailUsername",productstore.getUsePrimaryEmailUsername());
}

		if(productstore.getRequireCustomerRole() != null ){
			returnVal.put("requireCustomerRole",productstore.getRequireCustomerRole());
}

		if(productstore.getAutoInvoiceDigitalItems() != null ){
			returnVal.put("autoInvoiceDigitalItems",productstore.getAutoInvoiceDigitalItems());
}

		if(productstore.getReqShipAddrForDigItems() != null ){
			returnVal.put("reqShipAddrForDigItems",productstore.getReqShipAddrForDigItems());
}

		if(productstore.getShowCheckoutGiftOptions() != null ){
			returnVal.put("showCheckoutGiftOptions",productstore.getShowCheckoutGiftOptions());
}

		if(productstore.getSelectPaymentTypePerItem() != null ){
			returnVal.put("selectPaymentTypePerItem",productstore.getSelectPaymentTypePerItem());
}

		if(productstore.getShowPricesWithVatTax() != null ){
			returnVal.put("showPricesWithVatTax",productstore.getShowPricesWithVatTax());
}

		if(productstore.getShowTaxIsExempt() != null ){
			returnVal.put("showTaxIsExempt",productstore.getShowTaxIsExempt());
}

		if(productstore.getVatTaxAuthGeoId() != null ){
			returnVal.put("vatTaxAuthGeoId",productstore.getVatTaxAuthGeoId());
}

		if(productstore.getVatTaxAuthPartyId() != null ){
			returnVal.put("vatTaxAuthPartyId",productstore.getVatTaxAuthPartyId());
}

		if(productstore.getEnableAutoSuggestionList() != null ){
			returnVal.put("enableAutoSuggestionList",productstore.getEnableAutoSuggestionList());
}

		if(productstore.getEnableDigProdUpload() != null ){
			returnVal.put("enableDigProdUpload",productstore.getEnableDigProdUpload());
}

		if(productstore.getProdSearchExcludeVariants() != null ){
			returnVal.put("prodSearchExcludeVariants",productstore.getProdSearchExcludeVariants());
}

		if(productstore.getDigProdUploadCategoryId() != null ){
			returnVal.put("digProdUploadCategoryId",productstore.getDigProdUploadCategoryId());
}

		if(productstore.getAutoOrderCcTryExp() != null ){
			returnVal.put("autoOrderCcTryExp",productstore.getAutoOrderCcTryExp());
}

		if(productstore.getAutoOrderCcTryOtherCards() != null ){
			returnVal.put("autoOrderCcTryOtherCards",productstore.getAutoOrderCcTryOtherCards());
}

		if(productstore.getAutoOrderCcTryLaterNsf() != null ){
			returnVal.put("autoOrderCcTryLaterNsf",productstore.getAutoOrderCcTryLaterNsf());
}

		if(productstore.getAutoOrderCcTryLaterMax() != null ){
			returnVal.put("autoOrderCcTryLaterMax",productstore.getAutoOrderCcTryLaterMax());
}

		if(productstore.getStoreCreditValidDays() != null ){
			returnVal.put("storeCreditValidDays",productstore.getStoreCreditValidDays());
}

		if(productstore.getAutoApproveInvoice() != null ){
			returnVal.put("autoApproveInvoice",productstore.getAutoApproveInvoice());
}

		if(productstore.getAutoApproveOrder() != null ){
			returnVal.put("autoApproveOrder",productstore.getAutoApproveOrder());
}

		if(productstore.getShipIfCaptureFails() != null ){
			returnVal.put("shipIfCaptureFails",productstore.getShipIfCaptureFails());
}

		if(productstore.getSetOwnerUponIssuance() != null ){
			returnVal.put("setOwnerUponIssuance",productstore.getSetOwnerUponIssuance());
}

		if(productstore.getReqReturnInventoryReceive() != null ){
			returnVal.put("reqReturnInventoryReceive",productstore.getReqReturnInventoryReceive());
}

		if(productstore.getAddToCartRemoveIncompat() != null ){
			returnVal.put("addToCartRemoveIncompat",productstore.getAddToCartRemoveIncompat());
}

		if(productstore.getAddToCartReplaceUpsell() != null ){
			returnVal.put("addToCartReplaceUpsell",productstore.getAddToCartReplaceUpsell());
}

		if(productstore.getSplitPayPrefPerShpGrp() != null ){
			returnVal.put("splitPayPrefPerShpGrp",productstore.getSplitPayPrefPerShpGrp());
}

		if(productstore.getManagedByLot() != null ){
			returnVal.put("managedByLot",productstore.getManagedByLot());
}

		if(productstore.getShowOutOfStockProducts() != null ){
			returnVal.put("showOutOfStockProducts",productstore.getShowOutOfStockProducts());
}

		if(productstore.getOrderDecimalQuantity() != null ){
			returnVal.put("orderDecimalQuantity",productstore.getOrderDecimalQuantity());
}

		if(productstore.getAllowComment() != null ){
			returnVal.put("allowComment",productstore.getAllowComment());
}

		if(productstore.getOldStyleSheet() != null ){
			returnVal.put("oldStyleSheet",productstore.getOldStyleSheet());
}

		if(productstore.getOldHeaderLogo() != null ){
			returnVal.put("oldHeaderLogo",productstore.getOldHeaderLogo());
}

		if(productstore.getOldHeaderMiddleBackground() != null ){
			returnVal.put("oldHeaderMiddleBackground",productstore.getOldHeaderMiddleBackground());
}

		if(productstore.getOldHeaderRightBackground() != null ){
			returnVal.put("oldHeaderRightBackground",productstore.getOldHeaderRightBackground());
}

		return returnVal;
}


	public static ProductStore map(Map<String, Object> fields) {

		ProductStore returnVal = new ProductStore();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("primaryStoreGroupId") != null) {
			returnVal.setPrimaryStoreGroupId((String) fields.get("primaryStoreGroupId"));
}

		if(fields.get("storeName") != null) {
			returnVal.setStoreName((String) fields.get("storeName"));
}

		if(fields.get("companyName") != null) {
			returnVal.setCompanyName((String) fields.get("companyName"));
}

		if(fields.get("title") != null) {
			returnVal.setTitle((String) fields.get("title"));
}

		if(fields.get("subtitle") != null) {
			returnVal.setSubtitle((String) fields.get("subtitle"));
}

		if(fields.get("payToPartyId") != null) {
			returnVal.setPayToPartyId((String) fields.get("payToPartyId"));
}

		if(fields.get("daysToCancelNonPay") != null) {
			returnVal.setDaysToCancelNonPay((long) fields.get("daysToCancelNonPay"));
}

		if(fields.get("manualAuthIsCapture") != null) {
			returnVal.setManualAuthIsCapture((boolean) fields.get("manualAuthIsCapture"));
}

		if(fields.get("prorateShipping") != null) {
			returnVal.setProrateShipping((boolean) fields.get("prorateShipping"));
}

		if(fields.get("prorateTaxes") != null) {
			returnVal.setProrateTaxes((boolean) fields.get("prorateTaxes"));
}

		if(fields.get("viewCartOnAdd") != null) {
			returnVal.setViewCartOnAdd((boolean) fields.get("viewCartOnAdd"));
}

		if(fields.get("autoSaveCart") != null) {
			returnVal.setAutoSaveCart((boolean) fields.get("autoSaveCart"));
}

		if(fields.get("autoApproveReviews") != null) {
			returnVal.setAutoApproveReviews((boolean) fields.get("autoApproveReviews"));
}

		if(fields.get("isDemoStore") != null) {
			returnVal.setIsDemoStore((boolean) fields.get("isDemoStore"));
}

		if(fields.get("isImmediatelyFulfilled") != null) {
			returnVal.setIsImmediatelyFulfilled((boolean) fields.get("isImmediatelyFulfilled"));
}

		if(fields.get("inventoryFacilityId") != null) {
			returnVal.setInventoryFacilityId((String) fields.get("inventoryFacilityId"));
}

		if(fields.get("oneInventoryFacility") != null) {
			returnVal.setOneInventoryFacility((boolean) fields.get("oneInventoryFacility"));
}

		if(fields.get("checkInventory") != null) {
			returnVal.setCheckInventory((boolean) fields.get("checkInventory"));
}

		if(fields.get("reserveInventory") != null) {
			returnVal.setReserveInventory((boolean) fields.get("reserveInventory"));
}

		if(fields.get("reserveOrderEnumId") != null) {
			returnVal.setReserveOrderEnumId((String) fields.get("reserveOrderEnumId"));
}

		if(fields.get("requireInventory") != null) {
			returnVal.setRequireInventory((boolean) fields.get("requireInventory"));
}

		if(fields.get("balanceResOnOrderCreation") != null) {
			returnVal.setBalanceResOnOrderCreation((boolean) fields.get("balanceResOnOrderCreation"));
}

		if(fields.get("requirementMethodEnumId") != null) {
			returnVal.setRequirementMethodEnumId((String) fields.get("requirementMethodEnumId"));
}

		if(fields.get("orderNumberPrefix") != null) {
			returnVal.setOrderNumberPrefix((String) fields.get("orderNumberPrefix"));
}

		if(fields.get("defaultLocaleString") != null) {
			returnVal.setDefaultLocaleString((String) fields.get("defaultLocaleString"));
}

		if(fields.get("defaultCurrencyUomId") != null) {
			returnVal.setDefaultCurrencyUomId((String) fields.get("defaultCurrencyUomId"));
}

		if(fields.get("defaultTimeZoneString") != null) {
			returnVal.setDefaultTimeZoneString((String) fields.get("defaultTimeZoneString"));
}

		if(fields.get("defaultSalesChannelEnumId") != null) {
			returnVal.setDefaultSalesChannelEnumId((String) fields.get("defaultSalesChannelEnumId"));
}

		if(fields.get("allowPassword") != null) {
			returnVal.setAllowPassword((boolean) fields.get("allowPassword"));
}

		if(fields.get("defaultPassword") != null) {
			returnVal.setDefaultPassword((String) fields.get("defaultPassword"));
}

		if(fields.get("explodeOrderItems") != null) {
			returnVal.setExplodeOrderItems((boolean) fields.get("explodeOrderItems"));
}

		if(fields.get("checkGcBalance") != null) {
			returnVal.setCheckGcBalance((boolean) fields.get("checkGcBalance"));
}

		if(fields.get("retryFailedAuths") != null) {
			returnVal.setRetryFailedAuths((boolean) fields.get("retryFailedAuths"));
}

		if(fields.get("headerApprovedStatus") != null) {
			returnVal.setHeaderApprovedStatus((String) fields.get("headerApprovedStatus"));
}

		if(fields.get("itemApprovedStatus") != null) {
			returnVal.setItemApprovedStatus((String) fields.get("itemApprovedStatus"));
}

		if(fields.get("digitalItemApprovedStatus") != null) {
			returnVal.setDigitalItemApprovedStatus((String) fields.get("digitalItemApprovedStatus"));
}

		if(fields.get("headerDeclinedStatus") != null) {
			returnVal.setHeaderDeclinedStatus((String) fields.get("headerDeclinedStatus"));
}

		if(fields.get("itemDeclinedStatus") != null) {
			returnVal.setItemDeclinedStatus((String) fields.get("itemDeclinedStatus"));
}

		if(fields.get("headerCancelStatus") != null) {
			returnVal.setHeaderCancelStatus((String) fields.get("headerCancelStatus"));
}

		if(fields.get("itemCancelStatus") != null) {
			returnVal.setItemCancelStatus((String) fields.get("itemCancelStatus"));
}

		if(fields.get("authDeclinedMessage") != null) {
			returnVal.setAuthDeclinedMessage((String) fields.get("authDeclinedMessage"));
}

		if(fields.get("authFraudMessage") != null) {
			returnVal.setAuthFraudMessage((String) fields.get("authFraudMessage"));
}

		if(fields.get("authErrorMessage") != null) {
			returnVal.setAuthErrorMessage((String) fields.get("authErrorMessage"));
}

		if(fields.get("visualThemeId") != null) {
			returnVal.setVisualThemeId((String) fields.get("visualThemeId"));
}

		if(fields.get("storeCreditAccountEnumId") != null) {
			returnVal.setStoreCreditAccountEnumId((String) fields.get("storeCreditAccountEnumId"));
}

		if(fields.get("usePrimaryEmailUsername") != null) {
			returnVal.setUsePrimaryEmailUsername((boolean) fields.get("usePrimaryEmailUsername"));
}

		if(fields.get("requireCustomerRole") != null) {
			returnVal.setRequireCustomerRole((boolean) fields.get("requireCustomerRole"));
}

		if(fields.get("autoInvoiceDigitalItems") != null) {
			returnVal.setAutoInvoiceDigitalItems((boolean) fields.get("autoInvoiceDigitalItems"));
}

		if(fields.get("reqShipAddrForDigItems") != null) {
			returnVal.setReqShipAddrForDigItems((boolean) fields.get("reqShipAddrForDigItems"));
}

		if(fields.get("showCheckoutGiftOptions") != null) {
			returnVal.setShowCheckoutGiftOptions((boolean) fields.get("showCheckoutGiftOptions"));
}

		if(fields.get("selectPaymentTypePerItem") != null) {
			returnVal.setSelectPaymentTypePerItem((boolean) fields.get("selectPaymentTypePerItem"));
}

		if(fields.get("showPricesWithVatTax") != null) {
			returnVal.setShowPricesWithVatTax((boolean) fields.get("showPricesWithVatTax"));
}

		if(fields.get("showTaxIsExempt") != null) {
			returnVal.setShowTaxIsExempt((boolean) fields.get("showTaxIsExempt"));
}

		if(fields.get("vatTaxAuthGeoId") != null) {
			returnVal.setVatTaxAuthGeoId((String) fields.get("vatTaxAuthGeoId"));
}

		if(fields.get("vatTaxAuthPartyId") != null) {
			returnVal.setVatTaxAuthPartyId((String) fields.get("vatTaxAuthPartyId"));
}

		if(fields.get("enableAutoSuggestionList") != null) {
			returnVal.setEnableAutoSuggestionList((boolean) fields.get("enableAutoSuggestionList"));
}

		if(fields.get("enableDigProdUpload") != null) {
			returnVal.setEnableDigProdUpload((boolean) fields.get("enableDigProdUpload"));
}

		if(fields.get("prodSearchExcludeVariants") != null) {
			returnVal.setProdSearchExcludeVariants((boolean) fields.get("prodSearchExcludeVariants"));
}

		if(fields.get("digProdUploadCategoryId") != null) {
			returnVal.setDigProdUploadCategoryId((String) fields.get("digProdUploadCategoryId"));
}

		if(fields.get("autoOrderCcTryExp") != null) {
			returnVal.setAutoOrderCcTryExp((boolean) fields.get("autoOrderCcTryExp"));
}

		if(fields.get("autoOrderCcTryOtherCards") != null) {
			returnVal.setAutoOrderCcTryOtherCards((boolean) fields.get("autoOrderCcTryOtherCards"));
}

		if(fields.get("autoOrderCcTryLaterNsf") != null) {
			returnVal.setAutoOrderCcTryLaterNsf((boolean) fields.get("autoOrderCcTryLaterNsf"));
}

		if(fields.get("autoOrderCcTryLaterMax") != null) {
			returnVal.setAutoOrderCcTryLaterMax((long) fields.get("autoOrderCcTryLaterMax"));
}

		if(fields.get("storeCreditValidDays") != null) {
			returnVal.setStoreCreditValidDays((long) fields.get("storeCreditValidDays"));
}

		if(fields.get("autoApproveInvoice") != null) {
			returnVal.setAutoApproveInvoice((boolean) fields.get("autoApproveInvoice"));
}

		if(fields.get("autoApproveOrder") != null) {
			returnVal.setAutoApproveOrder((boolean) fields.get("autoApproveOrder"));
}

		if(fields.get("shipIfCaptureFails") != null) {
			returnVal.setShipIfCaptureFails((boolean) fields.get("shipIfCaptureFails"));
}

		if(fields.get("setOwnerUponIssuance") != null) {
			returnVal.setSetOwnerUponIssuance((boolean) fields.get("setOwnerUponIssuance"));
}

		if(fields.get("reqReturnInventoryReceive") != null) {
			returnVal.setReqReturnInventoryReceive((boolean) fields.get("reqReturnInventoryReceive"));
}

		if(fields.get("addToCartRemoveIncompat") != null) {
			returnVal.setAddToCartRemoveIncompat((boolean) fields.get("addToCartRemoveIncompat"));
}

		if(fields.get("addToCartReplaceUpsell") != null) {
			returnVal.setAddToCartReplaceUpsell((boolean) fields.get("addToCartReplaceUpsell"));
}

		if(fields.get("splitPayPrefPerShpGrp") != null) {
			returnVal.setSplitPayPrefPerShpGrp((boolean) fields.get("splitPayPrefPerShpGrp"));
}

		if(fields.get("managedByLot") != null) {
			returnVal.setManagedByLot((boolean) fields.get("managedByLot"));
}

		if(fields.get("showOutOfStockProducts") != null) {
			returnVal.setShowOutOfStockProducts((boolean) fields.get("showOutOfStockProducts"));
}

		if(fields.get("orderDecimalQuantity") != null) {
			returnVal.setOrderDecimalQuantity((boolean) fields.get("orderDecimalQuantity"));
}

		if(fields.get("allowComment") != null) {
			returnVal.setAllowComment((boolean) fields.get("allowComment"));
}

		if(fields.get("oldStyleSheet") != null) {
			returnVal.setOldStyleSheet((String) fields.get("oldStyleSheet"));
}

		if(fields.get("oldHeaderLogo") != null) {
			returnVal.setOldHeaderLogo((String) fields.get("oldHeaderLogo"));
}

		if(fields.get("oldHeaderMiddleBackground") != null) {
			returnVal.setOldHeaderMiddleBackground((String) fields.get("oldHeaderMiddleBackground"));
}

		if(fields.get("oldHeaderRightBackground") != null) {
			returnVal.setOldHeaderRightBackground((String) fields.get("oldHeaderRightBackground"));
}


		return returnVal;
 } 
	public static ProductStore mapstrstr(Map<String, String> fields) throws Exception {

		ProductStore returnVal = new ProductStore();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("primaryStoreGroupId") != null) {
			returnVal.setPrimaryStoreGroupId((String) fields.get("primaryStoreGroupId"));
}

		if(fields.get("storeName") != null) {
			returnVal.setStoreName((String) fields.get("storeName"));
}

		if(fields.get("companyName") != null) {
			returnVal.setCompanyName((String) fields.get("companyName"));
}

		if(fields.get("title") != null) {
			returnVal.setTitle((String) fields.get("title"));
}

		if(fields.get("subtitle") != null) {
			returnVal.setSubtitle((String) fields.get("subtitle"));
}

		if(fields.get("payToPartyId") != null) {
			returnVal.setPayToPartyId((String) fields.get("payToPartyId"));
}

		if(fields.get("daysToCancelNonPay") != null) {
String buf;
buf = fields.get("daysToCancelNonPay");
long ibuf = Long.parseLong(buf);
			returnVal.setDaysToCancelNonPay(ibuf);
}

		if(fields.get("manualAuthIsCapture") != null) {
String buf;
buf = fields.get("manualAuthIsCapture");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setManualAuthIsCapture(ibuf);
}

		if(fields.get("prorateShipping") != null) {
String buf;
buf = fields.get("prorateShipping");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setProrateShipping(ibuf);
}

		if(fields.get("prorateTaxes") != null) {
String buf;
buf = fields.get("prorateTaxes");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setProrateTaxes(ibuf);
}

		if(fields.get("viewCartOnAdd") != null) {
String buf;
buf = fields.get("viewCartOnAdd");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setViewCartOnAdd(ibuf);
}

		if(fields.get("autoSaveCart") != null) {
String buf;
buf = fields.get("autoSaveCart");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAutoSaveCart(ibuf);
}

		if(fields.get("autoApproveReviews") != null) {
String buf;
buf = fields.get("autoApproveReviews");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAutoApproveReviews(ibuf);
}

		if(fields.get("isDemoStore") != null) {
String buf;
buf = fields.get("isDemoStore");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsDemoStore(ibuf);
}

		if(fields.get("isImmediatelyFulfilled") != null) {
String buf;
buf = fields.get("isImmediatelyFulfilled");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsImmediatelyFulfilled(ibuf);
}

		if(fields.get("inventoryFacilityId") != null) {
			returnVal.setInventoryFacilityId((String) fields.get("inventoryFacilityId"));
}

		if(fields.get("oneInventoryFacility") != null) {
String buf;
buf = fields.get("oneInventoryFacility");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setOneInventoryFacility(ibuf);
}

		if(fields.get("checkInventory") != null) {
String buf;
buf = fields.get("checkInventory");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setCheckInventory(ibuf);
}

		if(fields.get("reserveInventory") != null) {
String buf;
buf = fields.get("reserveInventory");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setReserveInventory(ibuf);
}

		if(fields.get("reserveOrderEnumId") != null) {
			returnVal.setReserveOrderEnumId((String) fields.get("reserveOrderEnumId"));
}

		if(fields.get("requireInventory") != null) {
String buf;
buf = fields.get("requireInventory");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequireInventory(ibuf);
}

		if(fields.get("balanceResOnOrderCreation") != null) {
String buf;
buf = fields.get("balanceResOnOrderCreation");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setBalanceResOnOrderCreation(ibuf);
}

		if(fields.get("requirementMethodEnumId") != null) {
			returnVal.setRequirementMethodEnumId((String) fields.get("requirementMethodEnumId"));
}

		if(fields.get("orderNumberPrefix") != null) {
			returnVal.setOrderNumberPrefix((String) fields.get("orderNumberPrefix"));
}

		if(fields.get("defaultLocaleString") != null) {
			returnVal.setDefaultLocaleString((String) fields.get("defaultLocaleString"));
}

		if(fields.get("defaultCurrencyUomId") != null) {
			returnVal.setDefaultCurrencyUomId((String) fields.get("defaultCurrencyUomId"));
}

		if(fields.get("defaultTimeZoneString") != null) {
			returnVal.setDefaultTimeZoneString((String) fields.get("defaultTimeZoneString"));
}

		if(fields.get("defaultSalesChannelEnumId") != null) {
			returnVal.setDefaultSalesChannelEnumId((String) fields.get("defaultSalesChannelEnumId"));
}

		if(fields.get("allowPassword") != null) {
String buf;
buf = fields.get("allowPassword");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAllowPassword(ibuf);
}

		if(fields.get("defaultPassword") != null) {
			returnVal.setDefaultPassword((String) fields.get("defaultPassword"));
}

		if(fields.get("explodeOrderItems") != null) {
String buf;
buf = fields.get("explodeOrderItems");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setExplodeOrderItems(ibuf);
}

		if(fields.get("checkGcBalance") != null) {
String buf;
buf = fields.get("checkGcBalance");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setCheckGcBalance(ibuf);
}

		if(fields.get("retryFailedAuths") != null) {
String buf;
buf = fields.get("retryFailedAuths");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRetryFailedAuths(ibuf);
}

		if(fields.get("headerApprovedStatus") != null) {
			returnVal.setHeaderApprovedStatus((String) fields.get("headerApprovedStatus"));
}

		if(fields.get("itemApprovedStatus") != null) {
			returnVal.setItemApprovedStatus((String) fields.get("itemApprovedStatus"));
}

		if(fields.get("digitalItemApprovedStatus") != null) {
			returnVal.setDigitalItemApprovedStatus((String) fields.get("digitalItemApprovedStatus"));
}

		if(fields.get("headerDeclinedStatus") != null) {
			returnVal.setHeaderDeclinedStatus((String) fields.get("headerDeclinedStatus"));
}

		if(fields.get("itemDeclinedStatus") != null) {
			returnVal.setItemDeclinedStatus((String) fields.get("itemDeclinedStatus"));
}

		if(fields.get("headerCancelStatus") != null) {
			returnVal.setHeaderCancelStatus((String) fields.get("headerCancelStatus"));
}

		if(fields.get("itemCancelStatus") != null) {
			returnVal.setItemCancelStatus((String) fields.get("itemCancelStatus"));
}

		if(fields.get("authDeclinedMessage") != null) {
			returnVal.setAuthDeclinedMessage((String) fields.get("authDeclinedMessage"));
}

		if(fields.get("authFraudMessage") != null) {
			returnVal.setAuthFraudMessage((String) fields.get("authFraudMessage"));
}

		if(fields.get("authErrorMessage") != null) {
			returnVal.setAuthErrorMessage((String) fields.get("authErrorMessage"));
}

		if(fields.get("visualThemeId") != null) {
			returnVal.setVisualThemeId((String) fields.get("visualThemeId"));
}

		if(fields.get("storeCreditAccountEnumId") != null) {
			returnVal.setStoreCreditAccountEnumId((String) fields.get("storeCreditAccountEnumId"));
}

		if(fields.get("usePrimaryEmailUsername") != null) {
String buf;
buf = fields.get("usePrimaryEmailUsername");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setUsePrimaryEmailUsername(ibuf);
}

		if(fields.get("requireCustomerRole") != null) {
String buf;
buf = fields.get("requireCustomerRole");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequireCustomerRole(ibuf);
}

		if(fields.get("autoInvoiceDigitalItems") != null) {
String buf;
buf = fields.get("autoInvoiceDigitalItems");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAutoInvoiceDigitalItems(ibuf);
}

		if(fields.get("reqShipAddrForDigItems") != null) {
String buf;
buf = fields.get("reqShipAddrForDigItems");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setReqShipAddrForDigItems(ibuf);
}

		if(fields.get("showCheckoutGiftOptions") != null) {
String buf;
buf = fields.get("showCheckoutGiftOptions");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setShowCheckoutGiftOptions(ibuf);
}

		if(fields.get("selectPaymentTypePerItem") != null) {
String buf;
buf = fields.get("selectPaymentTypePerItem");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setSelectPaymentTypePerItem(ibuf);
}

		if(fields.get("showPricesWithVatTax") != null) {
String buf;
buf = fields.get("showPricesWithVatTax");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setShowPricesWithVatTax(ibuf);
}

		if(fields.get("showTaxIsExempt") != null) {
String buf;
buf = fields.get("showTaxIsExempt");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setShowTaxIsExempt(ibuf);
}

		if(fields.get("vatTaxAuthGeoId") != null) {
			returnVal.setVatTaxAuthGeoId((String) fields.get("vatTaxAuthGeoId"));
}

		if(fields.get("vatTaxAuthPartyId") != null) {
			returnVal.setVatTaxAuthPartyId((String) fields.get("vatTaxAuthPartyId"));
}

		if(fields.get("enableAutoSuggestionList") != null) {
String buf;
buf = fields.get("enableAutoSuggestionList");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setEnableAutoSuggestionList(ibuf);
}

		if(fields.get("enableDigProdUpload") != null) {
String buf;
buf = fields.get("enableDigProdUpload");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setEnableDigProdUpload(ibuf);
}

		if(fields.get("prodSearchExcludeVariants") != null) {
String buf;
buf = fields.get("prodSearchExcludeVariants");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setProdSearchExcludeVariants(ibuf);
}

		if(fields.get("digProdUploadCategoryId") != null) {
			returnVal.setDigProdUploadCategoryId((String) fields.get("digProdUploadCategoryId"));
}

		if(fields.get("autoOrderCcTryExp") != null) {
String buf;
buf = fields.get("autoOrderCcTryExp");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAutoOrderCcTryExp(ibuf);
}

		if(fields.get("autoOrderCcTryOtherCards") != null) {
String buf;
buf = fields.get("autoOrderCcTryOtherCards");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAutoOrderCcTryOtherCards(ibuf);
}

		if(fields.get("autoOrderCcTryLaterNsf") != null) {
String buf;
buf = fields.get("autoOrderCcTryLaterNsf");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAutoOrderCcTryLaterNsf(ibuf);
}

		if(fields.get("autoOrderCcTryLaterMax") != null) {
String buf;
buf = fields.get("autoOrderCcTryLaterMax");
long ibuf = Long.parseLong(buf);
			returnVal.setAutoOrderCcTryLaterMax(ibuf);
}

		if(fields.get("storeCreditValidDays") != null) {
String buf;
buf = fields.get("storeCreditValidDays");
long ibuf = Long.parseLong(buf);
			returnVal.setStoreCreditValidDays(ibuf);
}

		if(fields.get("autoApproveInvoice") != null) {
String buf;
buf = fields.get("autoApproveInvoice");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAutoApproveInvoice(ibuf);
}

		if(fields.get("autoApproveOrder") != null) {
String buf;
buf = fields.get("autoApproveOrder");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAutoApproveOrder(ibuf);
}

		if(fields.get("shipIfCaptureFails") != null) {
String buf;
buf = fields.get("shipIfCaptureFails");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setShipIfCaptureFails(ibuf);
}

		if(fields.get("setOwnerUponIssuance") != null) {
String buf;
buf = fields.get("setOwnerUponIssuance");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setSetOwnerUponIssuance(ibuf);
}

		if(fields.get("reqReturnInventoryReceive") != null) {
String buf;
buf = fields.get("reqReturnInventoryReceive");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setReqReturnInventoryReceive(ibuf);
}

		if(fields.get("addToCartRemoveIncompat") != null) {
String buf;
buf = fields.get("addToCartRemoveIncompat");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAddToCartRemoveIncompat(ibuf);
}

		if(fields.get("addToCartReplaceUpsell") != null) {
String buf;
buf = fields.get("addToCartReplaceUpsell");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAddToCartReplaceUpsell(ibuf);
}

		if(fields.get("splitPayPrefPerShpGrp") != null) {
String buf;
buf = fields.get("splitPayPrefPerShpGrp");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setSplitPayPrefPerShpGrp(ibuf);
}

		if(fields.get("managedByLot") != null) {
String buf;
buf = fields.get("managedByLot");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setManagedByLot(ibuf);
}

		if(fields.get("showOutOfStockProducts") != null) {
String buf;
buf = fields.get("showOutOfStockProducts");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setShowOutOfStockProducts(ibuf);
}

		if(fields.get("orderDecimalQuantity") != null) {
String buf;
buf = fields.get("orderDecimalQuantity");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setOrderDecimalQuantity(ibuf);
}

		if(fields.get("allowComment") != null) {
String buf;
buf = fields.get("allowComment");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAllowComment(ibuf);
}

		if(fields.get("oldStyleSheet") != null) {
			returnVal.setOldStyleSheet((String) fields.get("oldStyleSheet"));
}

		if(fields.get("oldHeaderLogo") != null) {
			returnVal.setOldHeaderLogo((String) fields.get("oldHeaderLogo"));
}

		if(fields.get("oldHeaderMiddleBackground") != null) {
			returnVal.setOldHeaderMiddleBackground((String) fields.get("oldHeaderMiddleBackground"));
}

		if(fields.get("oldHeaderRightBackground") != null) {
			returnVal.setOldHeaderRightBackground((String) fields.get("oldHeaderRightBackground"));
}


		return returnVal;
 } 
	public static ProductStore map(GenericValue val) {

ProductStore returnVal = new ProductStore();
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setPrimaryStoreGroupId(val.getString("primaryStoreGroupId"));
		returnVal.setStoreName(val.getString("storeName"));
		returnVal.setCompanyName(val.getString("companyName"));
		returnVal.setTitle(val.getString("title"));
		returnVal.setSubtitle(val.getString("subtitle"));
		returnVal.setPayToPartyId(val.getString("payToPartyId"));
		returnVal.setDaysToCancelNonPay(val.getLong("daysToCancelNonPay"));
		returnVal.setManualAuthIsCapture(val.getBoolean("manualAuthIsCapture"));
		returnVal.setProrateShipping(val.getBoolean("prorateShipping"));
		returnVal.setProrateTaxes(val.getBoolean("prorateTaxes"));
		returnVal.setViewCartOnAdd(val.getBoolean("viewCartOnAdd"));
		returnVal.setAutoSaveCart(val.getBoolean("autoSaveCart"));
		returnVal.setAutoApproveReviews(val.getBoolean("autoApproveReviews"));
		returnVal.setIsDemoStore(val.getBoolean("isDemoStore"));
		returnVal.setIsImmediatelyFulfilled(val.getBoolean("isImmediatelyFulfilled"));
		returnVal.setInventoryFacilityId(val.getString("inventoryFacilityId"));
		returnVal.setOneInventoryFacility(val.getBoolean("oneInventoryFacility"));
		returnVal.setCheckInventory(val.getBoolean("checkInventory"));
		returnVal.setReserveInventory(val.getBoolean("reserveInventory"));
		returnVal.setReserveOrderEnumId(val.getString("reserveOrderEnumId"));
		returnVal.setRequireInventory(val.getBoolean("requireInventory"));
		returnVal.setBalanceResOnOrderCreation(val.getBoolean("balanceResOnOrderCreation"));
		returnVal.setRequirementMethodEnumId(val.getString("requirementMethodEnumId"));
		returnVal.setOrderNumberPrefix(val.getString("orderNumberPrefix"));
		returnVal.setDefaultLocaleString(val.getString("defaultLocaleString"));
		returnVal.setDefaultCurrencyUomId(val.getString("defaultCurrencyUomId"));
		returnVal.setDefaultTimeZoneString(val.getString("defaultTimeZoneString"));
		returnVal.setDefaultSalesChannelEnumId(val.getString("defaultSalesChannelEnumId"));
		returnVal.setAllowPassword(val.getBoolean("allowPassword"));
		returnVal.setDefaultPassword(val.getString("defaultPassword"));
		returnVal.setExplodeOrderItems(val.getBoolean("explodeOrderItems"));
		returnVal.setCheckGcBalance(val.getBoolean("checkGcBalance"));
		returnVal.setRetryFailedAuths(val.getBoolean("retryFailedAuths"));
		returnVal.setHeaderApprovedStatus(val.getString("headerApprovedStatus"));
		returnVal.setItemApprovedStatus(val.getString("itemApprovedStatus"));
		returnVal.setDigitalItemApprovedStatus(val.getString("digitalItemApprovedStatus"));
		returnVal.setHeaderDeclinedStatus(val.getString("headerDeclinedStatus"));
		returnVal.setItemDeclinedStatus(val.getString("itemDeclinedStatus"));
		returnVal.setHeaderCancelStatus(val.getString("headerCancelStatus"));
		returnVal.setItemCancelStatus(val.getString("itemCancelStatus"));
		returnVal.setAuthDeclinedMessage(val.getString("authDeclinedMessage"));
		returnVal.setAuthFraudMessage(val.getString("authFraudMessage"));
		returnVal.setAuthErrorMessage(val.getString("authErrorMessage"));
		returnVal.setVisualThemeId(val.getString("visualThemeId"));
		returnVal.setStoreCreditAccountEnumId(val.getString("storeCreditAccountEnumId"));
		returnVal.setUsePrimaryEmailUsername(val.getBoolean("usePrimaryEmailUsername"));
		returnVal.setRequireCustomerRole(val.getBoolean("requireCustomerRole"));
		returnVal.setAutoInvoiceDigitalItems(val.getBoolean("autoInvoiceDigitalItems"));
		returnVal.setReqShipAddrForDigItems(val.getBoolean("reqShipAddrForDigItems"));
		returnVal.setShowCheckoutGiftOptions(val.getBoolean("showCheckoutGiftOptions"));
		returnVal.setSelectPaymentTypePerItem(val.getBoolean("selectPaymentTypePerItem"));
		returnVal.setShowPricesWithVatTax(val.getBoolean("showPricesWithVatTax"));
		returnVal.setShowTaxIsExempt(val.getBoolean("showTaxIsExempt"));
		returnVal.setVatTaxAuthGeoId(val.getString("vatTaxAuthGeoId"));
		returnVal.setVatTaxAuthPartyId(val.getString("vatTaxAuthPartyId"));
		returnVal.setEnableAutoSuggestionList(val.getBoolean("enableAutoSuggestionList"));
		returnVal.setEnableDigProdUpload(val.getBoolean("enableDigProdUpload"));
		returnVal.setProdSearchExcludeVariants(val.getBoolean("prodSearchExcludeVariants"));
		returnVal.setDigProdUploadCategoryId(val.getString("digProdUploadCategoryId"));
		returnVal.setAutoOrderCcTryExp(val.getBoolean("autoOrderCcTryExp"));
		returnVal.setAutoOrderCcTryOtherCards(val.getBoolean("autoOrderCcTryOtherCards"));
		returnVal.setAutoOrderCcTryLaterNsf(val.getBoolean("autoOrderCcTryLaterNsf"));
		returnVal.setAutoOrderCcTryLaterMax(val.getLong("autoOrderCcTryLaterMax"));
		returnVal.setStoreCreditValidDays(val.getLong("storeCreditValidDays"));
		returnVal.setAutoApproveInvoice(val.getBoolean("autoApproveInvoice"));
		returnVal.setAutoApproveOrder(val.getBoolean("autoApproveOrder"));
		returnVal.setShipIfCaptureFails(val.getBoolean("shipIfCaptureFails"));
		returnVal.setSetOwnerUponIssuance(val.getBoolean("setOwnerUponIssuance"));
		returnVal.setReqReturnInventoryReceive(val.getBoolean("reqReturnInventoryReceive"));
		returnVal.setAddToCartRemoveIncompat(val.getBoolean("addToCartRemoveIncompat"));
		returnVal.setAddToCartReplaceUpsell(val.getBoolean("addToCartReplaceUpsell"));
		returnVal.setSplitPayPrefPerShpGrp(val.getBoolean("splitPayPrefPerShpGrp"));
		returnVal.setManagedByLot(val.getBoolean("managedByLot"));
		returnVal.setShowOutOfStockProducts(val.getBoolean("showOutOfStockProducts"));
		returnVal.setOrderDecimalQuantity(val.getBoolean("orderDecimalQuantity"));
		returnVal.setAllowComment(val.getBoolean("allowComment"));
		returnVal.setOldStyleSheet(val.getString("oldStyleSheet"));
		returnVal.setOldHeaderLogo(val.getString("oldHeaderLogo"));
		returnVal.setOldHeaderMiddleBackground(val.getString("oldHeaderMiddleBackground"));
		returnVal.setOldHeaderRightBackground(val.getString("oldHeaderRightBackground"));


return returnVal;

}

public static ProductStore map(HttpServletRequest request) throws Exception {

		ProductStore returnVal = new ProductStore();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreId")) {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}

		if(paramMap.containsKey("primaryStoreGroupId"))  {
returnVal.setPrimaryStoreGroupId(request.getParameter("primaryStoreGroupId"));
}
		if(paramMap.containsKey("storeName"))  {
returnVal.setStoreName(request.getParameter("storeName"));
}
		if(paramMap.containsKey("companyName"))  {
returnVal.setCompanyName(request.getParameter("companyName"));
}
		if(paramMap.containsKey("title"))  {
returnVal.setTitle(request.getParameter("title"));
}
		if(paramMap.containsKey("subtitle"))  {
returnVal.setSubtitle(request.getParameter("subtitle"));
}
		if(paramMap.containsKey("payToPartyId"))  {
returnVal.setPayToPartyId(request.getParameter("payToPartyId"));
}
		if(paramMap.containsKey("daysToCancelNonPay"))  {
String buf = request.getParameter("daysToCancelNonPay");
Long ibuf = Long.parseLong(buf);
returnVal.setDaysToCancelNonPay(ibuf);
}
		if(paramMap.containsKey("manualAuthIsCapture"))  {
String buf = request.getParameter("manualAuthIsCapture");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setManualAuthIsCapture(ibuf);
}
		if(paramMap.containsKey("prorateShipping"))  {
String buf = request.getParameter("prorateShipping");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setProrateShipping(ibuf);
}
		if(paramMap.containsKey("prorateTaxes"))  {
String buf = request.getParameter("prorateTaxes");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setProrateTaxes(ibuf);
}
		if(paramMap.containsKey("viewCartOnAdd"))  {
String buf = request.getParameter("viewCartOnAdd");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setViewCartOnAdd(ibuf);
}
		if(paramMap.containsKey("autoSaveCart"))  {
String buf = request.getParameter("autoSaveCart");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAutoSaveCart(ibuf);
}
		if(paramMap.containsKey("autoApproveReviews"))  {
String buf = request.getParameter("autoApproveReviews");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAutoApproveReviews(ibuf);
}
		if(paramMap.containsKey("isDemoStore"))  {
String buf = request.getParameter("isDemoStore");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsDemoStore(ibuf);
}
		if(paramMap.containsKey("isImmediatelyFulfilled"))  {
String buf = request.getParameter("isImmediatelyFulfilled");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsImmediatelyFulfilled(ibuf);
}
		if(paramMap.containsKey("inventoryFacilityId"))  {
returnVal.setInventoryFacilityId(request.getParameter("inventoryFacilityId"));
}
		if(paramMap.containsKey("oneInventoryFacility"))  {
String buf = request.getParameter("oneInventoryFacility");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setOneInventoryFacility(ibuf);
}
		if(paramMap.containsKey("checkInventory"))  {
String buf = request.getParameter("checkInventory");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setCheckInventory(ibuf);
}
		if(paramMap.containsKey("reserveInventory"))  {
String buf = request.getParameter("reserveInventory");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setReserveInventory(ibuf);
}
		if(paramMap.containsKey("reserveOrderEnumId"))  {
returnVal.setReserveOrderEnumId(request.getParameter("reserveOrderEnumId"));
}
		if(paramMap.containsKey("requireInventory"))  {
String buf = request.getParameter("requireInventory");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setRequireInventory(ibuf);
}
		if(paramMap.containsKey("balanceResOnOrderCreation"))  {
String buf = request.getParameter("balanceResOnOrderCreation");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setBalanceResOnOrderCreation(ibuf);
}
		if(paramMap.containsKey("requirementMethodEnumId"))  {
returnVal.setRequirementMethodEnumId(request.getParameter("requirementMethodEnumId"));
}
		if(paramMap.containsKey("orderNumberPrefix"))  {
returnVal.setOrderNumberPrefix(request.getParameter("orderNumberPrefix"));
}
		if(paramMap.containsKey("defaultLocaleString"))  {
returnVal.setDefaultLocaleString(request.getParameter("defaultLocaleString"));
}
		if(paramMap.containsKey("defaultCurrencyUomId"))  {
returnVal.setDefaultCurrencyUomId(request.getParameter("defaultCurrencyUomId"));
}
		if(paramMap.containsKey("defaultTimeZoneString"))  {
returnVal.setDefaultTimeZoneString(request.getParameter("defaultTimeZoneString"));
}
		if(paramMap.containsKey("defaultSalesChannelEnumId"))  {
returnVal.setDefaultSalesChannelEnumId(request.getParameter("defaultSalesChannelEnumId"));
}
		if(paramMap.containsKey("allowPassword"))  {
String buf = request.getParameter("allowPassword");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAllowPassword(ibuf);
}
		if(paramMap.containsKey("defaultPassword"))  {
returnVal.setDefaultPassword(request.getParameter("defaultPassword"));
}
		if(paramMap.containsKey("explodeOrderItems"))  {
String buf = request.getParameter("explodeOrderItems");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setExplodeOrderItems(ibuf);
}
		if(paramMap.containsKey("checkGcBalance"))  {
String buf = request.getParameter("checkGcBalance");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setCheckGcBalance(ibuf);
}
		if(paramMap.containsKey("retryFailedAuths"))  {
String buf = request.getParameter("retryFailedAuths");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setRetryFailedAuths(ibuf);
}
		if(paramMap.containsKey("headerApprovedStatus"))  {
returnVal.setHeaderApprovedStatus(request.getParameter("headerApprovedStatus"));
}
		if(paramMap.containsKey("itemApprovedStatus"))  {
returnVal.setItemApprovedStatus(request.getParameter("itemApprovedStatus"));
}
		if(paramMap.containsKey("digitalItemApprovedStatus"))  {
returnVal.setDigitalItemApprovedStatus(request.getParameter("digitalItemApprovedStatus"));
}
		if(paramMap.containsKey("headerDeclinedStatus"))  {
returnVal.setHeaderDeclinedStatus(request.getParameter("headerDeclinedStatus"));
}
		if(paramMap.containsKey("itemDeclinedStatus"))  {
returnVal.setItemDeclinedStatus(request.getParameter("itemDeclinedStatus"));
}
		if(paramMap.containsKey("headerCancelStatus"))  {
returnVal.setHeaderCancelStatus(request.getParameter("headerCancelStatus"));
}
		if(paramMap.containsKey("itemCancelStatus"))  {
returnVal.setItemCancelStatus(request.getParameter("itemCancelStatus"));
}
		if(paramMap.containsKey("authDeclinedMessage"))  {
returnVal.setAuthDeclinedMessage(request.getParameter("authDeclinedMessage"));
}
		if(paramMap.containsKey("authFraudMessage"))  {
returnVal.setAuthFraudMessage(request.getParameter("authFraudMessage"));
}
		if(paramMap.containsKey("authErrorMessage"))  {
returnVal.setAuthErrorMessage(request.getParameter("authErrorMessage"));
}
		if(paramMap.containsKey("visualThemeId"))  {
returnVal.setVisualThemeId(request.getParameter("visualThemeId"));
}
		if(paramMap.containsKey("storeCreditAccountEnumId"))  {
returnVal.setStoreCreditAccountEnumId(request.getParameter("storeCreditAccountEnumId"));
}
		if(paramMap.containsKey("usePrimaryEmailUsername"))  {
String buf = request.getParameter("usePrimaryEmailUsername");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setUsePrimaryEmailUsername(ibuf);
}
		if(paramMap.containsKey("requireCustomerRole"))  {
String buf = request.getParameter("requireCustomerRole");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setRequireCustomerRole(ibuf);
}
		if(paramMap.containsKey("autoInvoiceDigitalItems"))  {
String buf = request.getParameter("autoInvoiceDigitalItems");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAutoInvoiceDigitalItems(ibuf);
}
		if(paramMap.containsKey("reqShipAddrForDigItems"))  {
String buf = request.getParameter("reqShipAddrForDigItems");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setReqShipAddrForDigItems(ibuf);
}
		if(paramMap.containsKey("showCheckoutGiftOptions"))  {
String buf = request.getParameter("showCheckoutGiftOptions");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setShowCheckoutGiftOptions(ibuf);
}
		if(paramMap.containsKey("selectPaymentTypePerItem"))  {
String buf = request.getParameter("selectPaymentTypePerItem");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setSelectPaymentTypePerItem(ibuf);
}
		if(paramMap.containsKey("showPricesWithVatTax"))  {
String buf = request.getParameter("showPricesWithVatTax");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setShowPricesWithVatTax(ibuf);
}
		if(paramMap.containsKey("showTaxIsExempt"))  {
String buf = request.getParameter("showTaxIsExempt");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setShowTaxIsExempt(ibuf);
}
		if(paramMap.containsKey("vatTaxAuthGeoId"))  {
returnVal.setVatTaxAuthGeoId(request.getParameter("vatTaxAuthGeoId"));
}
		if(paramMap.containsKey("vatTaxAuthPartyId"))  {
returnVal.setVatTaxAuthPartyId(request.getParameter("vatTaxAuthPartyId"));
}
		if(paramMap.containsKey("enableAutoSuggestionList"))  {
String buf = request.getParameter("enableAutoSuggestionList");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setEnableAutoSuggestionList(ibuf);
}
		if(paramMap.containsKey("enableDigProdUpload"))  {
String buf = request.getParameter("enableDigProdUpload");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setEnableDigProdUpload(ibuf);
}
		if(paramMap.containsKey("prodSearchExcludeVariants"))  {
String buf = request.getParameter("prodSearchExcludeVariants");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setProdSearchExcludeVariants(ibuf);
}
		if(paramMap.containsKey("digProdUploadCategoryId"))  {
returnVal.setDigProdUploadCategoryId(request.getParameter("digProdUploadCategoryId"));
}
		if(paramMap.containsKey("autoOrderCcTryExp"))  {
String buf = request.getParameter("autoOrderCcTryExp");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAutoOrderCcTryExp(ibuf);
}
		if(paramMap.containsKey("autoOrderCcTryOtherCards"))  {
String buf = request.getParameter("autoOrderCcTryOtherCards");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAutoOrderCcTryOtherCards(ibuf);
}
		if(paramMap.containsKey("autoOrderCcTryLaterNsf"))  {
String buf = request.getParameter("autoOrderCcTryLaterNsf");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAutoOrderCcTryLaterNsf(ibuf);
}
		if(paramMap.containsKey("autoOrderCcTryLaterMax"))  {
String buf = request.getParameter("autoOrderCcTryLaterMax");
Long ibuf = Long.parseLong(buf);
returnVal.setAutoOrderCcTryLaterMax(ibuf);
}
		if(paramMap.containsKey("storeCreditValidDays"))  {
String buf = request.getParameter("storeCreditValidDays");
Long ibuf = Long.parseLong(buf);
returnVal.setStoreCreditValidDays(ibuf);
}
		if(paramMap.containsKey("autoApproveInvoice"))  {
String buf = request.getParameter("autoApproveInvoice");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAutoApproveInvoice(ibuf);
}
		if(paramMap.containsKey("autoApproveOrder"))  {
String buf = request.getParameter("autoApproveOrder");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAutoApproveOrder(ibuf);
}
		if(paramMap.containsKey("shipIfCaptureFails"))  {
String buf = request.getParameter("shipIfCaptureFails");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setShipIfCaptureFails(ibuf);
}
		if(paramMap.containsKey("setOwnerUponIssuance"))  {
String buf = request.getParameter("setOwnerUponIssuance");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setSetOwnerUponIssuance(ibuf);
}
		if(paramMap.containsKey("reqReturnInventoryReceive"))  {
String buf = request.getParameter("reqReturnInventoryReceive");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setReqReturnInventoryReceive(ibuf);
}
		if(paramMap.containsKey("addToCartRemoveIncompat"))  {
String buf = request.getParameter("addToCartRemoveIncompat");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAddToCartRemoveIncompat(ibuf);
}
		if(paramMap.containsKey("addToCartReplaceUpsell"))  {
String buf = request.getParameter("addToCartReplaceUpsell");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAddToCartReplaceUpsell(ibuf);
}
		if(paramMap.containsKey("splitPayPrefPerShpGrp"))  {
String buf = request.getParameter("splitPayPrefPerShpGrp");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setSplitPayPrefPerShpGrp(ibuf);
}
		if(paramMap.containsKey("managedByLot"))  {
String buf = request.getParameter("managedByLot");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setManagedByLot(ibuf);
}
		if(paramMap.containsKey("showOutOfStockProducts"))  {
String buf = request.getParameter("showOutOfStockProducts");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setShowOutOfStockProducts(ibuf);
}
		if(paramMap.containsKey("orderDecimalQuantity"))  {
String buf = request.getParameter("orderDecimalQuantity");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setOrderDecimalQuantity(ibuf);
}
		if(paramMap.containsKey("allowComment"))  {
String buf = request.getParameter("allowComment");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAllowComment(ibuf);
}
		if(paramMap.containsKey("oldStyleSheet"))  {
returnVal.setOldStyleSheet(request.getParameter("oldStyleSheet"));
}
		if(paramMap.containsKey("oldHeaderLogo"))  {
returnVal.setOldHeaderLogo(request.getParameter("oldHeaderLogo"));
}
		if(paramMap.containsKey("oldHeaderMiddleBackground"))  {
returnVal.setOldHeaderMiddleBackground(request.getParameter("oldHeaderMiddleBackground"));
}
		if(paramMap.containsKey("oldHeaderRightBackground"))  {
returnVal.setOldHeaderRightBackground(request.getParameter("oldHeaderRightBackground"));
}
return returnVal;

}
}
