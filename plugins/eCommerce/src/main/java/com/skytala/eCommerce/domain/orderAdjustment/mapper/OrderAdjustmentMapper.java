package com.skytala.eCommerce.domain.orderAdjustment.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.orderAdjustment.model.OrderAdjustment;

public class OrderAdjustmentMapper  {


	public static Map<String, Object> map(OrderAdjustment orderadjustment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderadjustment.getOrderAdjustmentId() != null ){
			returnVal.put("orderAdjustmentId",orderadjustment.getOrderAdjustmentId());
}

		if(orderadjustment.getOrderAdjustmentTypeId() != null ){
			returnVal.put("orderAdjustmentTypeId",orderadjustment.getOrderAdjustmentTypeId());
}

		if(orderadjustment.getOrderId() != null ){
			returnVal.put("orderId",orderadjustment.getOrderId());
}

		if(orderadjustment.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderadjustment.getOrderItemSeqId());
}

		if(orderadjustment.getShipGroupSeqId() != null ){
			returnVal.put("shipGroupSeqId",orderadjustment.getShipGroupSeqId());
}

		if(orderadjustment.getComments() != null ){
			returnVal.put("comments",orderadjustment.getComments());
}

		if(orderadjustment.getDescription() != null ){
			returnVal.put("description",orderadjustment.getDescription());
}

		if(orderadjustment.getAmount() != null ){
			returnVal.put("amount",orderadjustment.getAmount());
}

		if(orderadjustment.getRecurringAmount() != null ){
			returnVal.put("recurringAmount",orderadjustment.getRecurringAmount());
}

		if(orderadjustment.getAmountAlreadyIncluded() != null ){
			returnVal.put("amountAlreadyIncluded",orderadjustment.getAmountAlreadyIncluded());
}

		if(orderadjustment.getProductPromoId() != null ){
			returnVal.put("productPromoId",orderadjustment.getProductPromoId());
}

		if(orderadjustment.getProductPromoRuleId() != null ){
			returnVal.put("productPromoRuleId",orderadjustment.getProductPromoRuleId());
}

		if(orderadjustment.getProductPromoActionSeqId() != null ){
			returnVal.put("productPromoActionSeqId",orderadjustment.getProductPromoActionSeqId());
}

		if(orderadjustment.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",orderadjustment.getProductFeatureId());
}

		if(orderadjustment.getCorrespondingProductId() != null ){
			returnVal.put("correspondingProductId",orderadjustment.getCorrespondingProductId());
}

		if(orderadjustment.getTaxAuthorityRateSeqId() != null ){
			returnVal.put("taxAuthorityRateSeqId",orderadjustment.getTaxAuthorityRateSeqId());
}

		if(orderadjustment.getSourceReferenceId() != null ){
			returnVal.put("sourceReferenceId",orderadjustment.getSourceReferenceId());
}

		if(orderadjustment.getSourcePercentage() != null ){
			returnVal.put("sourcePercentage",orderadjustment.getSourcePercentage());
}

		if(orderadjustment.getCustomerReferenceId() != null ){
			returnVal.put("customerReferenceId",orderadjustment.getCustomerReferenceId());
}

		if(orderadjustment.getPrimaryGeoId() != null ){
			returnVal.put("primaryGeoId",orderadjustment.getPrimaryGeoId());
}

		if(orderadjustment.getSecondaryGeoId() != null ){
			returnVal.put("secondaryGeoId",orderadjustment.getSecondaryGeoId());
}

		if(orderadjustment.getExemptAmount() != null ){
			returnVal.put("exemptAmount",orderadjustment.getExemptAmount());
}

		if(orderadjustment.getTaxAuthGeoId() != null ){
			returnVal.put("taxAuthGeoId",orderadjustment.getTaxAuthGeoId());
}

		if(orderadjustment.getTaxAuthPartyId() != null ){
			returnVal.put("taxAuthPartyId",orderadjustment.getTaxAuthPartyId());
}

		if(orderadjustment.getOverrideGlAccountId() != null ){
			returnVal.put("overrideGlAccountId",orderadjustment.getOverrideGlAccountId());
}

		if(orderadjustment.getIncludeInTax() != null ){
			returnVal.put("includeInTax",orderadjustment.getIncludeInTax());
}

		if(orderadjustment.getIncludeInShipping() != null ){
			returnVal.put("includeInShipping",orderadjustment.getIncludeInShipping());
}

		if(orderadjustment.getIsManual() != null ){
			returnVal.put("isManual",orderadjustment.getIsManual());
}

		if(orderadjustment.getCreatedDate() != null ){
			returnVal.put("createdDate",orderadjustment.getCreatedDate());
}

		if(orderadjustment.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",orderadjustment.getCreatedByUserLogin());
}

		if(orderadjustment.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",orderadjustment.getLastModifiedDate());
}

		if(orderadjustment.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",orderadjustment.getLastModifiedByUserLogin());
}

		if(orderadjustment.getOriginalAdjustmentId() != null ){
			returnVal.put("originalAdjustmentId",orderadjustment.getOriginalAdjustmentId());
}

		if(orderadjustment.getOldAmountPerQuantity() != null ){
			returnVal.put("oldAmountPerQuantity",orderadjustment.getOldAmountPerQuantity());
}

		if(orderadjustment.getOldPercentage() != null ){
			returnVal.put("oldPercentage",orderadjustment.getOldPercentage());
}

		return returnVal;
}


	public static OrderAdjustment map(Map<String, Object> fields) {

		OrderAdjustment returnVal = new OrderAdjustment();

		if(fields.get("orderAdjustmentId") != null) {
			returnVal.setOrderAdjustmentId((String) fields.get("orderAdjustmentId"));
}

		if(fields.get("orderAdjustmentTypeId") != null) {
			returnVal.setOrderAdjustmentTypeId((String) fields.get("orderAdjustmentTypeId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}

		if(fields.get("recurringAmount") != null) {
			returnVal.setRecurringAmount((BigDecimal) fields.get("recurringAmount"));
}

		if(fields.get("amountAlreadyIncluded") != null) {
			returnVal.setAmountAlreadyIncluded((BigDecimal) fields.get("amountAlreadyIncluded"));
}

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("productPromoRuleId") != null) {
			returnVal.setProductPromoRuleId((String) fields.get("productPromoRuleId"));
}

		if(fields.get("productPromoActionSeqId") != null) {
			returnVal.setProductPromoActionSeqId((String) fields.get("productPromoActionSeqId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("correspondingProductId") != null) {
			returnVal.setCorrespondingProductId((String) fields.get("correspondingProductId"));
}

		if(fields.get("taxAuthorityRateSeqId") != null) {
			returnVal.setTaxAuthorityRateSeqId((String) fields.get("taxAuthorityRateSeqId"));
}

		if(fields.get("sourceReferenceId") != null) {
			returnVal.setSourceReferenceId((String) fields.get("sourceReferenceId"));
}

		if(fields.get("sourcePercentage") != null) {
			returnVal.setSourcePercentage((BigDecimal) fields.get("sourcePercentage"));
}

		if(fields.get("customerReferenceId") != null) {
			returnVal.setCustomerReferenceId((String) fields.get("customerReferenceId"));
}

		if(fields.get("primaryGeoId") != null) {
			returnVal.setPrimaryGeoId((String) fields.get("primaryGeoId"));
}

		if(fields.get("secondaryGeoId") != null) {
			returnVal.setSecondaryGeoId((String) fields.get("secondaryGeoId"));
}

		if(fields.get("exemptAmount") != null) {
			returnVal.setExemptAmount((BigDecimal) fields.get("exemptAmount"));
}

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("overrideGlAccountId") != null) {
			returnVal.setOverrideGlAccountId((String) fields.get("overrideGlAccountId"));
}

		if(fields.get("includeInTax") != null) {
			returnVal.setIncludeInTax((boolean) fields.get("includeInTax"));
}

		if(fields.get("includeInShipping") != null) {
			returnVal.setIncludeInShipping((boolean) fields.get("includeInShipping"));
}

		if(fields.get("isManual") != null) {
			returnVal.setIsManual((boolean) fields.get("isManual"));
}

		if(fields.get("createdDate") != null) {
			returnVal.setCreatedDate((Timestamp) fields.get("createdDate"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
			returnVal.setLastModifiedDate((Timestamp) fields.get("lastModifiedDate"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}

		if(fields.get("originalAdjustmentId") != null) {
			returnVal.setOriginalAdjustmentId((String) fields.get("originalAdjustmentId"));
}

		if(fields.get("oldAmountPerQuantity") != null) {
			returnVal.setOldAmountPerQuantity((BigDecimal) fields.get("oldAmountPerQuantity"));
}

		if(fields.get("oldPercentage") != null) {
			returnVal.setOldPercentage((BigDecimal) fields.get("oldPercentage"));
}


		return returnVal;
 } 
	public static OrderAdjustment mapstrstr(Map<String, String> fields) throws Exception {

		OrderAdjustment returnVal = new OrderAdjustment();

		if(fields.get("orderAdjustmentId") != null) {
			returnVal.setOrderAdjustmentId((String) fields.get("orderAdjustmentId"));
}

		if(fields.get("orderAdjustmentTypeId") != null) {
			returnVal.setOrderAdjustmentTypeId((String) fields.get("orderAdjustmentTypeId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}

		if(fields.get("recurringAmount") != null) {
String buf;
buf = fields.get("recurringAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setRecurringAmount(bd);
}

		if(fields.get("amountAlreadyIncluded") != null) {
String buf;
buf = fields.get("amountAlreadyIncluded");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmountAlreadyIncluded(bd);
}

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("productPromoRuleId") != null) {
			returnVal.setProductPromoRuleId((String) fields.get("productPromoRuleId"));
}

		if(fields.get("productPromoActionSeqId") != null) {
			returnVal.setProductPromoActionSeqId((String) fields.get("productPromoActionSeqId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("correspondingProductId") != null) {
			returnVal.setCorrespondingProductId((String) fields.get("correspondingProductId"));
}

		if(fields.get("taxAuthorityRateSeqId") != null) {
			returnVal.setTaxAuthorityRateSeqId((String) fields.get("taxAuthorityRateSeqId"));
}

		if(fields.get("sourceReferenceId") != null) {
			returnVal.setSourceReferenceId((String) fields.get("sourceReferenceId"));
}

		if(fields.get("sourcePercentage") != null) {
String buf;
buf = fields.get("sourcePercentage");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSourcePercentage(bd);
}

		if(fields.get("customerReferenceId") != null) {
			returnVal.setCustomerReferenceId((String) fields.get("customerReferenceId"));
}

		if(fields.get("primaryGeoId") != null) {
			returnVal.setPrimaryGeoId((String) fields.get("primaryGeoId"));
}

		if(fields.get("secondaryGeoId") != null) {
			returnVal.setSecondaryGeoId((String) fields.get("secondaryGeoId"));
}

		if(fields.get("exemptAmount") != null) {
String buf;
buf = fields.get("exemptAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setExemptAmount(bd);
}

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("overrideGlAccountId") != null) {
			returnVal.setOverrideGlAccountId((String) fields.get("overrideGlAccountId"));
}

		if(fields.get("includeInTax") != null) {
String buf;
buf = fields.get("includeInTax");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIncludeInTax(ibuf);
}

		if(fields.get("includeInShipping") != null) {
String buf;
buf = fields.get("includeInShipping");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIncludeInShipping(ibuf);
}

		if(fields.get("isManual") != null) {
String buf;
buf = fields.get("isManual");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsManual(ibuf);
}

		if(fields.get("createdDate") != null) {
String buf = fields.get("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
String buf = fields.get("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}

		if(fields.get("originalAdjustmentId") != null) {
			returnVal.setOriginalAdjustmentId((String) fields.get("originalAdjustmentId"));
}

		if(fields.get("oldAmountPerQuantity") != null) {
String buf;
buf = fields.get("oldAmountPerQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOldAmountPerQuantity(bd);
}

		if(fields.get("oldPercentage") != null) {
String buf;
buf = fields.get("oldPercentage");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOldPercentage(bd);
}


		return returnVal;
 } 
	public static OrderAdjustment map(GenericValue val) {

OrderAdjustment returnVal = new OrderAdjustment();
		returnVal.setOrderAdjustmentId(val.getString("orderAdjustmentId"));
		returnVal.setOrderAdjustmentTypeId(val.getString("orderAdjustmentTypeId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setShipGroupSeqId(val.getString("shipGroupSeqId"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setRecurringAmount(val.getBigDecimal("recurringAmount"));
		returnVal.setAmountAlreadyIncluded(val.getBigDecimal("amountAlreadyIncluded"));
		returnVal.setProductPromoId(val.getString("productPromoId"));
		returnVal.setProductPromoRuleId(val.getString("productPromoRuleId"));
		returnVal.setProductPromoActionSeqId(val.getString("productPromoActionSeqId"));
		returnVal.setProductFeatureId(val.getString("productFeatureId"));
		returnVal.setCorrespondingProductId(val.getString("correspondingProductId"));
		returnVal.setTaxAuthorityRateSeqId(val.getString("taxAuthorityRateSeqId"));
		returnVal.setSourceReferenceId(val.getString("sourceReferenceId"));
		returnVal.setSourcePercentage(val.getBigDecimal("sourcePercentage"));
		returnVal.setCustomerReferenceId(val.getString("customerReferenceId"));
		returnVal.setPrimaryGeoId(val.getString("primaryGeoId"));
		returnVal.setSecondaryGeoId(val.getString("secondaryGeoId"));
		returnVal.setExemptAmount(val.getBigDecimal("exemptAmount"));
		returnVal.setTaxAuthGeoId(val.getString("taxAuthGeoId"));
		returnVal.setTaxAuthPartyId(val.getString("taxAuthPartyId"));
		returnVal.setOverrideGlAccountId(val.getString("overrideGlAccountId"));
		returnVal.setIncludeInTax(val.getBoolean("includeInTax"));
		returnVal.setIncludeInShipping(val.getBoolean("includeInShipping"));
		returnVal.setIsManual(val.getBoolean("isManual"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));
		returnVal.setOriginalAdjustmentId(val.getString("originalAdjustmentId"));
		returnVal.setOldAmountPerQuantity(val.getBigDecimal("oldAmountPerQuantity"));
		returnVal.setOldPercentage(val.getBigDecimal("oldPercentage"));


return returnVal;

}

public static OrderAdjustment map(HttpServletRequest request) throws Exception {

		OrderAdjustment returnVal = new OrderAdjustment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderAdjustmentId")) {
returnVal.setOrderAdjustmentId(request.getParameter("orderAdjustmentId"));
}

		if(paramMap.containsKey("orderAdjustmentTypeId"))  {
returnVal.setOrderAdjustmentTypeId(request.getParameter("orderAdjustmentTypeId"));
}
		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("shipGroupSeqId"))  {
returnVal.setShipGroupSeqId(request.getParameter("shipGroupSeqId"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
		if(paramMap.containsKey("recurringAmount"))  {
String buf = request.getParameter("recurringAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setRecurringAmount(bd);
}
		if(paramMap.containsKey("amountAlreadyIncluded"))  {
String buf = request.getParameter("amountAlreadyIncluded");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmountAlreadyIncluded(bd);
}
		if(paramMap.containsKey("productPromoId"))  {
returnVal.setProductPromoId(request.getParameter("productPromoId"));
}
		if(paramMap.containsKey("productPromoRuleId"))  {
returnVal.setProductPromoRuleId(request.getParameter("productPromoRuleId"));
}
		if(paramMap.containsKey("productPromoActionSeqId"))  {
returnVal.setProductPromoActionSeqId(request.getParameter("productPromoActionSeqId"));
}
		if(paramMap.containsKey("productFeatureId"))  {
returnVal.setProductFeatureId(request.getParameter("productFeatureId"));
}
		if(paramMap.containsKey("correspondingProductId"))  {
returnVal.setCorrespondingProductId(request.getParameter("correspondingProductId"));
}
		if(paramMap.containsKey("taxAuthorityRateSeqId"))  {
returnVal.setTaxAuthorityRateSeqId(request.getParameter("taxAuthorityRateSeqId"));
}
		if(paramMap.containsKey("sourceReferenceId"))  {
returnVal.setSourceReferenceId(request.getParameter("sourceReferenceId"));
}
		if(paramMap.containsKey("sourcePercentage"))  {
String buf = request.getParameter("sourcePercentage");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSourcePercentage(bd);
}
		if(paramMap.containsKey("customerReferenceId"))  {
returnVal.setCustomerReferenceId(request.getParameter("customerReferenceId"));
}
		if(paramMap.containsKey("primaryGeoId"))  {
returnVal.setPrimaryGeoId(request.getParameter("primaryGeoId"));
}
		if(paramMap.containsKey("secondaryGeoId"))  {
returnVal.setSecondaryGeoId(request.getParameter("secondaryGeoId"));
}
		if(paramMap.containsKey("exemptAmount"))  {
String buf = request.getParameter("exemptAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setExemptAmount(bd);
}
		if(paramMap.containsKey("taxAuthGeoId"))  {
returnVal.setTaxAuthGeoId(request.getParameter("taxAuthGeoId"));
}
		if(paramMap.containsKey("taxAuthPartyId"))  {
returnVal.setTaxAuthPartyId(request.getParameter("taxAuthPartyId"));
}
		if(paramMap.containsKey("overrideGlAccountId"))  {
returnVal.setOverrideGlAccountId(request.getParameter("overrideGlAccountId"));
}
		if(paramMap.containsKey("includeInTax"))  {
String buf = request.getParameter("includeInTax");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIncludeInTax(ibuf);
}
		if(paramMap.containsKey("includeInShipping"))  {
String buf = request.getParameter("includeInShipping");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIncludeInShipping(ibuf);
}
		if(paramMap.containsKey("isManual"))  {
String buf = request.getParameter("isManual");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsManual(ibuf);
}
		if(paramMap.containsKey("createdDate"))  {
String buf = request.getParameter("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCreatedDate(ibuf);
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedDate"))  {
String buf = request.getParameter("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastModifiedDate(ibuf);
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
		if(paramMap.containsKey("originalAdjustmentId"))  {
returnVal.setOriginalAdjustmentId(request.getParameter("originalAdjustmentId"));
}
		if(paramMap.containsKey("oldAmountPerQuantity"))  {
String buf = request.getParameter("oldAmountPerQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOldAmountPerQuantity(bd);
}
		if(paramMap.containsKey("oldPercentage"))  {
String buf = request.getParameter("oldPercentage");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOldPercentage(bd);
}
return returnVal;

}
}
