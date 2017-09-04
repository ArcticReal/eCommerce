package com.skytala.eCommerce.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ofbiz.entity.GenericValue;

public class OrderItemMapper {

	public static Map<String, Object> map(OrderItem orderitem) {

		Map<String, Object> returnVal = new HashMap<String, Object>();

		if (orderitem.getOrderId() != null) {
			returnVal.put("orderId", orderitem.getOrderId());
		}

		if (orderitem.getOrderItemSeqId() != null) {
			returnVal.put("orderItemSeqId", orderitem.getOrderItemSeqId());
		}

		if (orderitem.getExternalId() != null) {
			returnVal.put("externalId", orderitem.getExternalId());
		}

		if (orderitem.getOrderItemTypeId() != null) {
			returnVal.put("orderItemTypeId", orderitem.getOrderItemTypeId());
		}

		if (orderitem.getOrderItemGroupSeqId() != null) {
			returnVal.put("orderItemGroupSeqId", orderitem.getOrderItemGroupSeqId());
		}

		if (orderitem.getIsItemGroupPrimary() != null) {
			returnVal.put("isItemGroupPrimary", orderitem.getIsItemGroupPrimary());
		}

		if (orderitem.getFromInventoryItemId() != null) {
			returnVal.put("fromInventoryItemId", orderitem.getFromInventoryItemId());
		}

		if (orderitem.getBudgetId() != null) {
			returnVal.put("budgetId", orderitem.getBudgetId());
		}

		if (orderitem.getBudgetItemSeqId() != null) {
			returnVal.put("budgetItemSeqId", orderitem.getBudgetItemSeqId());
		}

		if (orderitem.getProductId() != null) {
			returnVal.put("productId", orderitem.getProductId());
		}

		if (orderitem.getSupplierProductId() != null) {
			returnVal.put("supplierProductId", orderitem.getSupplierProductId());
		}

		if (orderitem.getProductFeatureId() != null) {
			returnVal.put("productFeatureId", orderitem.getProductFeatureId());
		}

		if (orderitem.getProdCatalogId() != null) {
			returnVal.put("prodCatalogId", orderitem.getProdCatalogId());
		}

		if (orderitem.getProductCategoryId() != null) {
			returnVal.put("productCategoryId", orderitem.getProductCategoryId());
		}

		if (orderitem.getIsPromo() != null) {
			returnVal.put("isPromo", orderitem.getIsPromo());
		}

		if (orderitem.getQuoteId() != null) {
			returnVal.put("quoteId", orderitem.getQuoteId());
		}

		if (orderitem.getQuoteItemSeqId() != null) {
			returnVal.put("quoteItemSeqId", orderitem.getQuoteItemSeqId());
		}

		if (orderitem.getShoppingListId() != null) {
			returnVal.put("shoppingListId", orderitem.getShoppingListId());
		}

		if (orderitem.getShoppingListItemSeqId() != null) {
			returnVal.put("shoppingListItemSeqId", orderitem.getShoppingListItemSeqId());
		}

		if (orderitem.getSubscriptionId() != null) {
			returnVal.put("subscriptionId", orderitem.getSubscriptionId());
		}

		if (orderitem.getDeploymentId() != null) {
			returnVal.put("deploymentId", orderitem.getDeploymentId());
		}

		if (orderitem.getQuantity() != null) {
			returnVal.put("quantity", orderitem.getQuantity());
		}

		if (orderitem.getCancelQuantity() != null) {
			returnVal.put("cancelQuantity", orderitem.getCancelQuantity());
		}

		if (orderitem.getSelectedAmount() != null) {
			returnVal.put("selectedAmount", orderitem.getSelectedAmount());
		}

		if (orderitem.getUnitPrice() != null) {
			returnVal.put("unitPrice", orderitem.getUnitPrice());
		}

		if (orderitem.getUnitListPrice() != null) {
			returnVal.put("unitListPrice", orderitem.getUnitListPrice());
		}

		if (orderitem.getUnitAverageCost() != null) {
			returnVal.put("unitAverageCost", orderitem.getUnitAverageCost());
		}

		if (orderitem.getUnitRecurringPrice() != null) {
			returnVal.put("unitRecurringPrice", orderitem.getUnitRecurringPrice());
		}

		if (orderitem.getIsModifiedPrice() != null) {
			returnVal.put("isModifiedPrice", orderitem.getIsModifiedPrice());
		}

		if (orderitem.getRecurringFreqUomId() != null) {
			returnVal.put("recurringFreqUomId", orderitem.getRecurringFreqUomId());
		}

		if (orderitem.getItemDescription() != null) {
			returnVal.put("itemDescription", orderitem.getItemDescription());
		}

		if (orderitem.getComments() != null) {
			returnVal.put("comments", orderitem.getComments());
		}

		if (orderitem.getCorrespondingPoId() != null) {
			returnVal.put("correspondingPoId", orderitem.getCorrespondingPoId());
		}

		if (orderitem.getStatusId() != null) {
			returnVal.put("statusId", orderitem.getStatusId());
		}

		if (orderitem.getSyncStatusId() != null) {
			returnVal.put("syncStatusId", orderitem.getSyncStatusId());
		}

		if (orderitem.getEstimatedShipDate() != null) {
			returnVal.put("estimatedShipDate", orderitem.getEstimatedShipDate());
		}

		if (orderitem.getEstimatedDeliveryDate() != null) {
			returnVal.put("estimatedDeliveryDate", orderitem.getEstimatedDeliveryDate());
		}

		if (orderitem.getAutoCancelDate() != null) {
			returnVal.put("autoCancelDate", orderitem.getAutoCancelDate());
		}

		if (orderitem.getDontCancelSetDate() != null) {
			returnVal.put("dontCancelSetDate", orderitem.getDontCancelSetDate());
		}

		if (orderitem.getDontCancelSetUserLogin() != null) {
			returnVal.put("dontCancelSetUserLogin", orderitem.getDontCancelSetUserLogin());
		}

		if (orderitem.getShipBeforeDate() != null) {
			returnVal.put("shipBeforeDate", orderitem.getShipBeforeDate());
		}

		if (orderitem.getShipAfterDate() != null) {
			returnVal.put("shipAfterDate", orderitem.getShipAfterDate());
		}

		if (orderitem.getCancelBackOrderDate() != null) {
			returnVal.put("cancelBackOrderDate", orderitem.getCancelBackOrderDate());
		}

		if (orderitem.getOverrideGlAccountId() != null) {
			returnVal.put("overrideGlAccountId", orderitem.getOverrideGlAccountId());
		}

		if (orderitem.getSalesOpportunityId() != null) {
			returnVal.put("salesOpportunityId", orderitem.getSalesOpportunityId());
		}

		if (orderitem.getChangeByUserLoginId() != null) {
			returnVal.put("changeByUserLoginId", orderitem.getChangeByUserLoginId());
		}

		return returnVal;
	}

	public static OrderItem map(Map<String, Object> fields) {

		OrderItem returnVal = new OrderItem();

		if (fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
		}

		if (fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
		}

		if (fields.get("externalId") != null) {
			returnVal.setExternalId((String) fields.get("externalId"));
		}

		if (fields.get("orderItemTypeId") != null) {
			returnVal.setOrderItemTypeId((String) fields.get("orderItemTypeId"));
		}

		if (fields.get("orderItemGroupSeqId") != null) {
			returnVal.setOrderItemGroupSeqId((String) fields.get("orderItemGroupSeqId"));
		}

		if (fields.get("isItemGroupPrimary") != null) {
			returnVal.setIsItemGroupPrimary((boolean) fields.get("isItemGroupPrimary"));
		}

		if (fields.get("fromInventoryItemId") != null) {
			returnVal.setFromInventoryItemId((String) fields.get("fromInventoryItemId"));
		}

		if (fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
		}

		if (fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
		}

		if (fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
		}

		if (fields.get("supplierProductId") != null) {
			returnVal.setSupplierProductId((String) fields.get("supplierProductId"));
		}

		if (fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
		}

		if (fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
		}

		if (fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
		}

		if (fields.get("isPromo") != null) {
			returnVal.setIsPromo((boolean) fields.get("isPromo"));
		}

		if (fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
		}

		if (fields.get("quoteItemSeqId") != null) {
			returnVal.setQuoteItemSeqId((String) fields.get("quoteItemSeqId"));
		}

		if (fields.get("shoppingListId") != null) {
			returnVal.setShoppingListId((String) fields.get("shoppingListId"));
		}

		if (fields.get("shoppingListItemSeqId") != null) {
			returnVal.setShoppingListItemSeqId((String) fields.get("shoppingListItemSeqId"));
		}

		if (fields.get("subscriptionId") != null) {
			returnVal.setSubscriptionId((String) fields.get("subscriptionId"));
		}

		if (fields.get("deploymentId") != null) {
			returnVal.setDeploymentId((String) fields.get("deploymentId"));
		}

		if (fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
		}

		if (fields.get("cancelQuantity") != null) {
			returnVal.setCancelQuantity((BigDecimal) fields.get("cancelQuantity"));
		}

		if (fields.get("selectedAmount") != null) {
			returnVal.setSelectedAmount((BigDecimal) fields.get("selectedAmount"));
		}

		if (fields.get("unitPrice") != null) {
			returnVal.setUnitPrice((BigDecimal) fields.get("unitPrice"));
		}

		if (fields.get("unitListPrice") != null) {
			returnVal.setUnitListPrice((BigDecimal) fields.get("unitListPrice"));
		}

		if (fields.get("unitAverageCost") != null) {
			returnVal.setUnitAverageCost((BigDecimal) fields.get("unitAverageCost"));
		}

		if (fields.get("unitRecurringPrice") != null) {
			returnVal.setUnitRecurringPrice((BigDecimal) fields.get("unitRecurringPrice"));
		}

		if (fields.get("isModifiedPrice") != null) {
			returnVal.setIsModifiedPrice((boolean) fields.get("isModifiedPrice"));
		}

		if (fields.get("recurringFreqUomId") != null) {
			returnVal.setRecurringFreqUomId((String) fields.get("recurringFreqUomId"));
		}

		if (fields.get("itemDescription") != null) {
			returnVal.setItemDescription((String) fields.get("itemDescription"));
		}

		if (fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
		}

		if (fields.get("correspondingPoId") != null) {
			returnVal.setCorrespondingPoId((String) fields.get("correspondingPoId"));
		}

		if (fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
		}

		if (fields.get("syncStatusId") != null) {
			returnVal.setSyncStatusId((String) fields.get("syncStatusId"));
		}

		if (fields.get("estimatedShipDate") != null) {
			returnVal.setEstimatedShipDate((Timestamp) fields.get("estimatedShipDate"));
		}

		if (fields.get("estimatedDeliveryDate") != null) {
			returnVal.setEstimatedDeliveryDate((Timestamp) fields.get("estimatedDeliveryDate"));
		}

		if (fields.get("autoCancelDate") != null) {
			returnVal.setAutoCancelDate((Timestamp) fields.get("autoCancelDate"));
		}

		if (fields.get("dontCancelSetDate") != null) {
			returnVal.setDontCancelSetDate((Timestamp) fields.get("dontCancelSetDate"));
		}

		if (fields.get("dontCancelSetUserLogin") != null) {
			returnVal.setDontCancelSetUserLogin((String) fields.get("dontCancelSetUserLogin"));
		}

		if (fields.get("shipBeforeDate") != null) {
			returnVal.setShipBeforeDate((Timestamp) fields.get("shipBeforeDate"));
		}

		if (fields.get("shipAfterDate") != null) {
			returnVal.setShipAfterDate((Timestamp) fields.get("shipAfterDate"));
		}

		if (fields.get("cancelBackOrderDate") != null) {
			returnVal.setCancelBackOrderDate((Timestamp) fields.get("cancelBackOrderDate"));
		}

		if (fields.get("overrideGlAccountId") != null) {
			returnVal.setOverrideGlAccountId((String) fields.get("overrideGlAccountId"));
		}

		if (fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
		}

		if (fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
		}

		return returnVal;
	}

	public static OrderItem mapstrstr(Map<String, String> fields) throws Exception {

		OrderItem returnVal = new OrderItem();

		if (fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
		}

		if (fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
		}

		if (fields.get("externalId") != null) {
			returnVal.setExternalId((String) fields.get("externalId"));
		}

		if (fields.get("orderItemTypeId") != null) {
			returnVal.setOrderItemTypeId((String) fields.get("orderItemTypeId"));
		}

		if (fields.get("orderItemGroupSeqId") != null) {
			returnVal.setOrderItemGroupSeqId((String) fields.get("orderItemGroupSeqId"));
		}

		if (fields.get("isItemGroupPrimary") != null) {
			String buf;
			buf = fields.get("isItemGroupPrimary");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsItemGroupPrimary(ibuf);
		}

		if (fields.get("fromInventoryItemId") != null) {
			returnVal.setFromInventoryItemId((String) fields.get("fromInventoryItemId"));
		}

		if (fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
		}

		if (fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
		}

		if (fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
		}

		if (fields.get("supplierProductId") != null) {
			returnVal.setSupplierProductId((String) fields.get("supplierProductId"));
		}

		if (fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
		}

		if (fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
		}

		if (fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
		}

		if (fields.get("isPromo") != null) {
			String buf;
			buf = fields.get("isPromo");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsPromo(ibuf);
		}

		if (fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
		}

		if (fields.get("quoteItemSeqId") != null) {
			returnVal.setQuoteItemSeqId((String) fields.get("quoteItemSeqId"));
		}

		if (fields.get("shoppingListId") != null) {
			returnVal.setShoppingListId((String) fields.get("shoppingListId"));
		}

		if (fields.get("shoppingListItemSeqId") != null) {
			returnVal.setShoppingListItemSeqId((String) fields.get("shoppingListItemSeqId"));
		}

		if (fields.get("subscriptionId") != null) {
			returnVal.setSubscriptionId((String) fields.get("subscriptionId"));
		}

		if (fields.get("deploymentId") != null) {
			returnVal.setDeploymentId((String) fields.get("deploymentId"));
		}

		if (fields.get("quantity") != null) {
			String buf;
			buf = fields.get("quantity");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
		}

		if (fields.get("cancelQuantity") != null) {
			String buf;
			buf = fields.get("cancelQuantity");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCancelQuantity(bd);
		}

		if (fields.get("selectedAmount") != null) {
			String buf;
			buf = fields.get("selectedAmount");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSelectedAmount(bd);
		}

		if (fields.get("unitPrice") != null) {
			String buf;
			buf = fields.get("unitPrice");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitPrice(bd);
		}

		if (fields.get("unitListPrice") != null) {
			String buf;
			buf = fields.get("unitListPrice");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitListPrice(bd);
		}

		if (fields.get("unitAverageCost") != null) {
			String buf;
			buf = fields.get("unitAverageCost");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitAverageCost(bd);
		}

		if (fields.get("unitRecurringPrice") != null) {
			String buf;
			buf = fields.get("unitRecurringPrice");
			float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitRecurringPrice(bd);
		}

		if (fields.get("isModifiedPrice") != null) {
			String buf;
			buf = fields.get("isModifiedPrice");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsModifiedPrice(ibuf);
		}

		if (fields.get("recurringFreqUomId") != null) {
			returnVal.setRecurringFreqUomId((String) fields.get("recurringFreqUomId"));
		}

		if (fields.get("itemDescription") != null) {
			returnVal.setItemDescription((String) fields.get("itemDescription"));
		}

		if (fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
		}

		if (fields.get("correspondingPoId") != null) {
			returnVal.setCorrespondingPoId((String) fields.get("correspondingPoId"));
		}

		if (fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
		}

		if (fields.get("syncStatusId") != null) {
			returnVal.setSyncStatusId((String) fields.get("syncStatusId"));
		}

		if (fields.get("estimatedShipDate") != null) {
			String buf = fields.get("estimatedShipDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedShipDate(ibuf);
		}

		if (fields.get("estimatedDeliveryDate") != null) {
			String buf = fields.get("estimatedDeliveryDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedDeliveryDate(ibuf);
		}

		if (fields.get("autoCancelDate") != null) {
			String buf = fields.get("autoCancelDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setAutoCancelDate(ibuf);
		}

		if (fields.get("dontCancelSetDate") != null) {
			String buf = fields.get("dontCancelSetDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDontCancelSetDate(ibuf);
		}

		if (fields.get("dontCancelSetUserLogin") != null) {
			returnVal.setDontCancelSetUserLogin((String) fields.get("dontCancelSetUserLogin"));
		}

		if (fields.get("shipBeforeDate") != null) {
			String buf = fields.get("shipBeforeDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setShipBeforeDate(ibuf);
		}

		if (fields.get("shipAfterDate") != null) {
			String buf = fields.get("shipAfterDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setShipAfterDate(ibuf);
		}

		if (fields.get("cancelBackOrderDate") != null) {
			String buf = fields.get("cancelBackOrderDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCancelBackOrderDate(ibuf);
		}

		if (fields.get("overrideGlAccountId") != null) {
			returnVal.setOverrideGlAccountId((String) fields.get("overrideGlAccountId"));
		}

		if (fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
		}

		if (fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
		}

		return returnVal;
	}

	public static OrderItem map(GenericValue val) {

		OrderItem returnVal = new OrderItem();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setExternalId(val.getString("externalId"));
		returnVal.setOrderItemTypeId(val.getString("orderItemTypeId"));
		returnVal.setOrderItemGroupSeqId(val.getString("orderItemGroupSeqId"));
		returnVal.setIsItemGroupPrimary(val.getBoolean("isItemGroupPrimary"));
		returnVal.setFromInventoryItemId(val.getString("fromInventoryItemId"));
		returnVal.setBudgetId(val.getString("budgetId"));
		returnVal.setBudgetItemSeqId(val.getString("budgetItemSeqId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setSupplierProductId(val.getString("supplierProductId"));
		returnVal.setProductFeatureId(val.getString("productFeatureId"));
		returnVal.setProdCatalogId(val.getString("prodCatalogId"));
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setIsPromo(val.getBoolean("isPromo"));
		returnVal.setQuoteId(val.getString("quoteId"));
		returnVal.setQuoteItemSeqId(val.getString("quoteItemSeqId"));
		returnVal.setShoppingListId(val.getString("shoppingListId"));
		returnVal.setShoppingListItemSeqId(val.getString("shoppingListItemSeqId"));
		returnVal.setSubscriptionId(val.getString("subscriptionId"));
		returnVal.setDeploymentId(val.getString("deploymentId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setCancelQuantity(val.getBigDecimal("cancelQuantity"));
		returnVal.setSelectedAmount(val.getBigDecimal("selectedAmount"));
		returnVal.setUnitPrice(val.getBigDecimal("unitPrice"));
		returnVal.setUnitListPrice(val.getBigDecimal("unitListPrice"));
		returnVal.setUnitAverageCost(val.getBigDecimal("unitAverageCost"));
		returnVal.setUnitRecurringPrice(val.getBigDecimal("unitRecurringPrice"));
		returnVal.setIsModifiedPrice(val.getBoolean("isModifiedPrice"));
		returnVal.setRecurringFreqUomId(val.getString("recurringFreqUomId"));
		returnVal.setItemDescription(val.getString("itemDescription"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setCorrespondingPoId(val.getString("correspondingPoId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setSyncStatusId(val.getString("syncStatusId"));
		returnVal.setEstimatedShipDate(val.getTimestamp("estimatedShipDate"));
		returnVal.setEstimatedDeliveryDate(val.getTimestamp("estimatedDeliveryDate"));
		returnVal.setAutoCancelDate(val.getTimestamp("autoCancelDate"));
		returnVal.setDontCancelSetDate(val.getTimestamp("dontCancelSetDate"));
		returnVal.setDontCancelSetUserLogin(val.getString("dontCancelSetUserLogin"));
		returnVal.setShipBeforeDate(val.getTimestamp("shipBeforeDate"));
		returnVal.setShipAfterDate(val.getTimestamp("shipAfterDate"));
		returnVal.setCancelBackOrderDate(val.getTimestamp("cancelBackOrderDate"));
		returnVal.setOverrideGlAccountId(val.getString("overrideGlAccountId"));
		returnVal.setSalesOpportunityId(val.getString("salesOpportunityId"));
		returnVal.setChangeByUserLoginId(val.getString("changeByUserLoginId"));

		return returnVal;

	}

	public static OrderItem map(HttpServletRequest request) throws Exception {

		OrderItem returnVal = new OrderItem();

		Map<String, String[]> paramMap = request.getParameterMap();

		if (!paramMap.containsKey("orderId")) {
			throw new Exception("Error! Id required");
		} else {
			returnVal.setOrderId(request.getParameter("productId"));
		}

		if (paramMap.containsKey("orderItemSeqId")) {
			returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
		}
		if (paramMap.containsKey("externalId")) {
			returnVal.setExternalId(request.getParameter("externalId"));
		}
		if (paramMap.containsKey("orderItemTypeId")) {
			returnVal.setOrderItemTypeId(request.getParameter("orderItemTypeId"));
		}
		if (paramMap.containsKey("orderItemGroupSeqId")) {
			returnVal.setOrderItemGroupSeqId(request.getParameter("orderItemGroupSeqId"));
		}
		if (paramMap.containsKey("isItemGroupPrimary")) {
			String buf = request.getParameter("isItemGroupPrimary");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsItemGroupPrimary(ibuf);
		}
		if (paramMap.containsKey("fromInventoryItemId")) {
			returnVal.setFromInventoryItemId(request.getParameter("fromInventoryItemId"));
		}
		if (paramMap.containsKey("budgetId")) {
			returnVal.setBudgetId(request.getParameter("budgetId"));
		}
		if (paramMap.containsKey("budgetItemSeqId")) {
			returnVal.setBudgetItemSeqId(request.getParameter("budgetItemSeqId"));
		}
		if (paramMap.containsKey("productId")) {
			returnVal.setProductId(request.getParameter("productId"));
		}
		if (paramMap.containsKey("supplierProductId")) {
			returnVal.setSupplierProductId(request.getParameter("supplierProductId"));
		}
		if (paramMap.containsKey("productFeatureId")) {
			returnVal.setProductFeatureId(request.getParameter("productFeatureId"));
		}
		if (paramMap.containsKey("prodCatalogId")) {
			returnVal.setProdCatalogId(request.getParameter("prodCatalogId"));
		}
		if (paramMap.containsKey("productCategoryId")) {
			returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
		}
		if (paramMap.containsKey("isPromo")) {
			String buf = request.getParameter("isPromo");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsPromo(ibuf);
		}
		if (paramMap.containsKey("quoteId")) {
			returnVal.setQuoteId(request.getParameter("quoteId"));
		}
		if (paramMap.containsKey("quoteItemSeqId")) {
			returnVal.setQuoteItemSeqId(request.getParameter("quoteItemSeqId"));
		}
		if (paramMap.containsKey("shoppingListId")) {
			returnVal.setShoppingListId(request.getParameter("shoppingListId"));
		}
		if (paramMap.containsKey("shoppingListItemSeqId")) {
			returnVal.setShoppingListItemSeqId(request.getParameter("shoppingListItemSeqId"));
		}
		if (paramMap.containsKey("subscriptionId")) {
			returnVal.setSubscriptionId(request.getParameter("subscriptionId"));
		}
		if (paramMap.containsKey("deploymentId")) {
			returnVal.setDeploymentId(request.getParameter("deploymentId"));
		}
		if (paramMap.containsKey("quantity")) {
			String buf = request.getParameter("quantity");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
		}
		if (paramMap.containsKey("cancelQuantity")) {
			String buf = request.getParameter("cancelQuantity");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCancelQuantity(bd);
		}
		if (paramMap.containsKey("selectedAmount")) {
			String buf = request.getParameter("selectedAmount");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSelectedAmount(bd);
		}
		if (paramMap.containsKey("unitPrice")) {
			String buf = request.getParameter("unitPrice");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitPrice(bd);
		}
		if (paramMap.containsKey("unitListPrice")) {
			String buf = request.getParameter("unitListPrice");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitListPrice(bd);
		}
		if (paramMap.containsKey("unitAverageCost")) {
			String buf = request.getParameter("unitAverageCost");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitAverageCost(bd);
		}
		if (paramMap.containsKey("unitRecurringPrice")) {
			String buf = request.getParameter("unitRecurringPrice");
			Float ibuf = Float.parseFloat(buf);
			BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitRecurringPrice(bd);
		}
		if (paramMap.containsKey("isModifiedPrice")) {
			String buf = request.getParameter("isModifiedPrice");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsModifiedPrice(ibuf);
		}
		if (paramMap.containsKey("recurringFreqUomId")) {
			returnVal.setRecurringFreqUomId(request.getParameter("recurringFreqUomId"));
		}
		if (paramMap.containsKey("itemDescription")) {
			returnVal.setItemDescription(request.getParameter("itemDescription"));
		}
		if (paramMap.containsKey("comments")) {
			returnVal.setComments(request.getParameter("comments"));
		}
		if (paramMap.containsKey("correspondingPoId")) {
			returnVal.setCorrespondingPoId(request.getParameter("correspondingPoId"));
		}
		if (paramMap.containsKey("statusId")) {
			returnVal.setStatusId(request.getParameter("statusId"));
		}
		if (paramMap.containsKey("syncStatusId")) {
			returnVal.setSyncStatusId(request.getParameter("syncStatusId"));
		}
		if (paramMap.containsKey("estimatedShipDate")) {
			String buf = request.getParameter("estimatedShipDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedShipDate(ibuf);
		}
		if (paramMap.containsKey("estimatedDeliveryDate")) {
			String buf = request.getParameter("estimatedDeliveryDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedDeliveryDate(ibuf);
		}
		if (paramMap.containsKey("autoCancelDate")) {
			String buf = request.getParameter("autoCancelDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setAutoCancelDate(ibuf);
		}
		if (paramMap.containsKey("dontCancelSetDate")) {
			String buf = request.getParameter("dontCancelSetDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDontCancelSetDate(ibuf);
		}
		if (paramMap.containsKey("dontCancelSetUserLogin")) {
			returnVal.setDontCancelSetUserLogin(request.getParameter("dontCancelSetUserLogin"));
		}
		if (paramMap.containsKey("shipBeforeDate")) {
			String buf = request.getParameter("shipBeforeDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setShipBeforeDate(ibuf);
		}
		if (paramMap.containsKey("shipAfterDate")) {
			String buf = request.getParameter("shipAfterDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setShipAfterDate(ibuf);
		}
		if (paramMap.containsKey("cancelBackOrderDate")) {
			String buf = request.getParameter("cancelBackOrderDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCancelBackOrderDate(ibuf);
		}
		if (paramMap.containsKey("overrideGlAccountId")) {
			returnVal.setOverrideGlAccountId(request.getParameter("overrideGlAccountId"));
		}
		if (paramMap.containsKey("salesOpportunityId")) {
			returnVal.setSalesOpportunityId(request.getParameter("salesOpportunityId"));
		}
		if (paramMap.containsKey("changeByUserLoginId")) {
			returnVal.setChangeByUserLoginId(request.getParameter("changeByUserLoginId"));
		}
		return returnVal;

	}
}
