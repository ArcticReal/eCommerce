package com.skytala.eCommerce.domain.order.relations.quoteAdjustment.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quoteAdjustment.model.QuoteAdjustment;

public class QuoteAdjustmentMapper  {


	public static Map<String, Object> map(QuoteAdjustment quoteadjustment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(quoteadjustment.getQuoteAdjustmentId() != null ){
			returnVal.put("quoteAdjustmentId",quoteadjustment.getQuoteAdjustmentId());
}

		if(quoteadjustment.getQuoteAdjustmentTypeId() != null ){
			returnVal.put("quoteAdjustmentTypeId",quoteadjustment.getQuoteAdjustmentTypeId());
}

		if(quoteadjustment.getQuoteId() != null ){
			returnVal.put("quoteId",quoteadjustment.getQuoteId());
}

		if(quoteadjustment.getQuoteItemSeqId() != null ){
			returnVal.put("quoteItemSeqId",quoteadjustment.getQuoteItemSeqId());
}

		if(quoteadjustment.getComments() != null ){
			returnVal.put("comments",quoteadjustment.getComments());
}

		if(quoteadjustment.getDescription() != null ){
			returnVal.put("description",quoteadjustment.getDescription());
}

		if(quoteadjustment.getAmount() != null ){
			returnVal.put("amount",quoteadjustment.getAmount());
}

		if(quoteadjustment.getProductPromoId() != null ){
			returnVal.put("productPromoId",quoteadjustment.getProductPromoId());
}

		if(quoteadjustment.getProductPromoRuleId() != null ){
			returnVal.put("productPromoRuleId",quoteadjustment.getProductPromoRuleId());
}

		if(quoteadjustment.getProductPromoActionSeqId() != null ){
			returnVal.put("productPromoActionSeqId",quoteadjustment.getProductPromoActionSeqId());
}

		if(quoteadjustment.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",quoteadjustment.getProductFeatureId());
}

		if(quoteadjustment.getCorrespondingProductId() != null ){
			returnVal.put("correspondingProductId",quoteadjustment.getCorrespondingProductId());
}

		if(quoteadjustment.getSourceReferenceId() != null ){
			returnVal.put("sourceReferenceId",quoteadjustment.getSourceReferenceId());
}

		if(quoteadjustment.getSourcePercentage() != null ){
			returnVal.put("sourcePercentage",quoteadjustment.getSourcePercentage());
}

		if(quoteadjustment.getCustomerReferenceId() != null ){
			returnVal.put("customerReferenceId",quoteadjustment.getCustomerReferenceId());
}

		if(quoteadjustment.getPrimaryGeoId() != null ){
			returnVal.put("primaryGeoId",quoteadjustment.getPrimaryGeoId());
}

		if(quoteadjustment.getSecondaryGeoId() != null ){
			returnVal.put("secondaryGeoId",quoteadjustment.getSecondaryGeoId());
}

		if(quoteadjustment.getExemptAmount() != null ){
			returnVal.put("exemptAmount",quoteadjustment.getExemptAmount());
}

		if(quoteadjustment.getTaxAuthGeoId() != null ){
			returnVal.put("taxAuthGeoId",quoteadjustment.getTaxAuthGeoId());
}

		if(quoteadjustment.getTaxAuthPartyId() != null ){
			returnVal.put("taxAuthPartyId",quoteadjustment.getTaxAuthPartyId());
}

		if(quoteadjustment.getOverrideGlAccountId() != null ){
			returnVal.put("overrideGlAccountId",quoteadjustment.getOverrideGlAccountId());
}

		if(quoteadjustment.getIncludeInTax() != null ){
			returnVal.put("includeInTax",quoteadjustment.getIncludeInTax());
}

		if(quoteadjustment.getIncludeInShipping() != null ){
			returnVal.put("includeInShipping",quoteadjustment.getIncludeInShipping());
}

		if(quoteadjustment.getCreatedDate() != null ){
			returnVal.put("createdDate",quoteadjustment.getCreatedDate());
}

		if(quoteadjustment.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",quoteadjustment.getCreatedByUserLogin());
}

		if(quoteadjustment.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",quoteadjustment.getLastModifiedDate());
}

		if(quoteadjustment.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",quoteadjustment.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static QuoteAdjustment map(Map<String, Object> fields) {

		QuoteAdjustment returnVal = new QuoteAdjustment();

		if(fields.get("quoteAdjustmentId") != null) {
			returnVal.setQuoteAdjustmentId((String) fields.get("quoteAdjustmentId"));
}

		if(fields.get("quoteAdjustmentTypeId") != null) {
			returnVal.setQuoteAdjustmentTypeId((String) fields.get("quoteAdjustmentTypeId"));
}

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("quoteItemSeqId") != null) {
			returnVal.setQuoteItemSeqId((String) fields.get("quoteItemSeqId"));
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
	public static QuoteAdjustment mapstrstr(Map<String, String> fields) throws Exception {

		QuoteAdjustment returnVal = new QuoteAdjustment();

		if(fields.get("quoteAdjustmentId") != null) {
			returnVal.setQuoteAdjustmentId((String) fields.get("quoteAdjustmentId"));
}

		if(fields.get("quoteAdjustmentTypeId") != null) {
			returnVal.setQuoteAdjustmentTypeId((String) fields.get("quoteAdjustmentTypeId"));
}

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("quoteItemSeqId") != null) {
			returnVal.setQuoteItemSeqId((String) fields.get("quoteItemSeqId"));
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
	public static QuoteAdjustment map(GenericValue val) {

QuoteAdjustment returnVal = new QuoteAdjustment();
		returnVal.setQuoteAdjustmentId(val.getString("quoteAdjustmentId"));
		returnVal.setQuoteAdjustmentTypeId(val.getString("quoteAdjustmentTypeId"));
		returnVal.setQuoteId(val.getString("quoteId"));
		returnVal.setQuoteItemSeqId(val.getString("quoteItemSeqId"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setProductPromoId(val.getString("productPromoId"));
		returnVal.setProductPromoRuleId(val.getString("productPromoRuleId"));
		returnVal.setProductPromoActionSeqId(val.getString("productPromoActionSeqId"));
		returnVal.setProductFeatureId(val.getString("productFeatureId"));
		returnVal.setCorrespondingProductId(val.getString("correspondingProductId"));
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

public static QuoteAdjustment map(HttpServletRequest request) throws Exception {

		QuoteAdjustment returnVal = new QuoteAdjustment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("quoteAdjustmentId")) {
returnVal.setQuoteAdjustmentId(request.getParameter("quoteAdjustmentId"));
}

		if(paramMap.containsKey("quoteAdjustmentTypeId"))  {
returnVal.setQuoteAdjustmentTypeId(request.getParameter("quoteAdjustmentTypeId"));
}
		if(paramMap.containsKey("quoteId"))  {
returnVal.setQuoteId(request.getParameter("quoteId"));
}
		if(paramMap.containsKey("quoteItemSeqId"))  {
returnVal.setQuoteItemSeqId(request.getParameter("quoteItemSeqId"));
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
