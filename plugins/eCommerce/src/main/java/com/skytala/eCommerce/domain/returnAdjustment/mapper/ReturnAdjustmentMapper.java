package com.skytala.eCommerce.domain.returnAdjustment.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.returnAdjustment.model.ReturnAdjustment;

public class ReturnAdjustmentMapper  {


	public static Map<String, Object> map(ReturnAdjustment returnadjustment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(returnadjustment.getReturnAdjustmentId() != null ){
			returnVal.put("returnAdjustmentId",returnadjustment.getReturnAdjustmentId());
}

		if(returnadjustment.getReturnAdjustmentTypeId() != null ){
			returnVal.put("returnAdjustmentTypeId",returnadjustment.getReturnAdjustmentTypeId());
}

		if(returnadjustment.getReturnId() != null ){
			returnVal.put("returnId",returnadjustment.getReturnId());
}

		if(returnadjustment.getReturnItemSeqId() != null ){
			returnVal.put("returnItemSeqId",returnadjustment.getReturnItemSeqId());
}

		if(returnadjustment.getShipGroupSeqId() != null ){
			returnVal.put("shipGroupSeqId",returnadjustment.getShipGroupSeqId());
}

		if(returnadjustment.getComments() != null ){
			returnVal.put("comments",returnadjustment.getComments());
}

		if(returnadjustment.getDescription() != null ){
			returnVal.put("description",returnadjustment.getDescription());
}

		if(returnadjustment.getReturnTypeId() != null ){
			returnVal.put("returnTypeId",returnadjustment.getReturnTypeId());
}

		if(returnadjustment.getOrderAdjustmentId() != null ){
			returnVal.put("orderAdjustmentId",returnadjustment.getOrderAdjustmentId());
}

		if(returnadjustment.getAmount() != null ){
			returnVal.put("amount",returnadjustment.getAmount());
}

		if(returnadjustment.getProductPromoId() != null ){
			returnVal.put("productPromoId",returnadjustment.getProductPromoId());
}

		if(returnadjustment.getProductPromoRuleId() != null ){
			returnVal.put("productPromoRuleId",returnadjustment.getProductPromoRuleId());
}

		if(returnadjustment.getProductPromoActionSeqId() != null ){
			returnVal.put("productPromoActionSeqId",returnadjustment.getProductPromoActionSeqId());
}

		if(returnadjustment.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",returnadjustment.getProductFeatureId());
}

		if(returnadjustment.getCorrespondingProductId() != null ){
			returnVal.put("correspondingProductId",returnadjustment.getCorrespondingProductId());
}

		if(returnadjustment.getTaxAuthorityRateSeqId() != null ){
			returnVal.put("taxAuthorityRateSeqId",returnadjustment.getTaxAuthorityRateSeqId());
}

		if(returnadjustment.getSourceReferenceId() != null ){
			returnVal.put("sourceReferenceId",returnadjustment.getSourceReferenceId());
}

		if(returnadjustment.getSourcePercentage() != null ){
			returnVal.put("sourcePercentage",returnadjustment.getSourcePercentage());
}

		if(returnadjustment.getCustomerReferenceId() != null ){
			returnVal.put("customerReferenceId",returnadjustment.getCustomerReferenceId());
}

		if(returnadjustment.getPrimaryGeoId() != null ){
			returnVal.put("primaryGeoId",returnadjustment.getPrimaryGeoId());
}

		if(returnadjustment.getSecondaryGeoId() != null ){
			returnVal.put("secondaryGeoId",returnadjustment.getSecondaryGeoId());
}

		if(returnadjustment.getExemptAmount() != null ){
			returnVal.put("exemptAmount",returnadjustment.getExemptAmount());
}

		if(returnadjustment.getTaxAuthGeoId() != null ){
			returnVal.put("taxAuthGeoId",returnadjustment.getTaxAuthGeoId());
}

		if(returnadjustment.getTaxAuthPartyId() != null ){
			returnVal.put("taxAuthPartyId",returnadjustment.getTaxAuthPartyId());
}

		if(returnadjustment.getOverrideGlAccountId() != null ){
			returnVal.put("overrideGlAccountId",returnadjustment.getOverrideGlAccountId());
}

		if(returnadjustment.getIncludeInTax() != null ){
			returnVal.put("includeInTax",returnadjustment.getIncludeInTax());
}

		if(returnadjustment.getIncludeInShipping() != null ){
			returnVal.put("includeInShipping",returnadjustment.getIncludeInShipping());
}

		if(returnadjustment.getCreatedDate() != null ){
			returnVal.put("createdDate",returnadjustment.getCreatedDate());
}

		if(returnadjustment.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",returnadjustment.getCreatedByUserLogin());
}

		if(returnadjustment.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",returnadjustment.getLastModifiedDate());
}

		if(returnadjustment.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",returnadjustment.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static ReturnAdjustment map(Map<String, Object> fields) {

		ReturnAdjustment returnVal = new ReturnAdjustment();

		if(fields.get("returnAdjustmentId") != null) {
			returnVal.setReturnAdjustmentId((String) fields.get("returnAdjustmentId"));
}

		if(fields.get("returnAdjustmentTypeId") != null) {
			returnVal.setReturnAdjustmentTypeId((String) fields.get("returnAdjustmentTypeId"));
}

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnItemSeqId") != null) {
			returnVal.setReturnItemSeqId((String) fields.get("returnItemSeqId"));
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

		if(fields.get("returnTypeId") != null) {
			returnVal.setReturnTypeId((String) fields.get("returnTypeId"));
}

		if(fields.get("orderAdjustmentId") != null) {
			returnVal.setOrderAdjustmentId((String) fields.get("orderAdjustmentId"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
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


		return returnVal;
 } 
	public static ReturnAdjustment mapstrstr(Map<String, String> fields) throws Exception {

		ReturnAdjustment returnVal = new ReturnAdjustment();

		if(fields.get("returnAdjustmentId") != null) {
			returnVal.setReturnAdjustmentId((String) fields.get("returnAdjustmentId"));
}

		if(fields.get("returnAdjustmentTypeId") != null) {
			returnVal.setReturnAdjustmentTypeId((String) fields.get("returnAdjustmentTypeId"));
}

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnItemSeqId") != null) {
			returnVal.setReturnItemSeqId((String) fields.get("returnItemSeqId"));
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

		if(fields.get("returnTypeId") != null) {
			returnVal.setReturnTypeId((String) fields.get("returnTypeId"));
}

		if(fields.get("orderAdjustmentId") != null) {
			returnVal.setOrderAdjustmentId((String) fields.get("orderAdjustmentId"));
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
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


		return returnVal;
 } 
	public static ReturnAdjustment map(GenericValue val) {

ReturnAdjustment returnVal = new ReturnAdjustment();
		returnVal.setReturnAdjustmentId(val.getString("returnAdjustmentId"));
		returnVal.setReturnAdjustmentTypeId(val.getString("returnAdjustmentTypeId"));
		returnVal.setReturnId(val.getString("returnId"));
		returnVal.setReturnItemSeqId(val.getString("returnItemSeqId"));
		returnVal.setShipGroupSeqId(val.getString("shipGroupSeqId"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setReturnTypeId(val.getString("returnTypeId"));
		returnVal.setOrderAdjustmentId(val.getString("orderAdjustmentId"));
		returnVal.setAmount(val.getBigDecimal("amount"));
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
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static ReturnAdjustment map(HttpServletRequest request) throws Exception {

		ReturnAdjustment returnVal = new ReturnAdjustment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("returnAdjustmentId")) {
returnVal.setReturnAdjustmentId(request.getParameter("returnAdjustmentId"));
}

		if(paramMap.containsKey("returnAdjustmentTypeId"))  {
returnVal.setReturnAdjustmentTypeId(request.getParameter("returnAdjustmentTypeId"));
}
		if(paramMap.containsKey("returnId"))  {
returnVal.setReturnId(request.getParameter("returnId"));
}
		if(paramMap.containsKey("returnItemSeqId"))  {
returnVal.setReturnItemSeqId(request.getParameter("returnItemSeqId"));
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
		if(paramMap.containsKey("returnTypeId"))  {
returnVal.setReturnTypeId(request.getParameter("returnTypeId"));
}
		if(paramMap.containsKey("orderAdjustmentId"))  {
returnVal.setOrderAdjustmentId(request.getParameter("orderAdjustmentId"));
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
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
return returnVal;

}
}
