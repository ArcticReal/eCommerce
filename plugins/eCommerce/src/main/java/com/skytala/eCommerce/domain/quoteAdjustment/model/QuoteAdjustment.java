package com.skytala.eCommerce.domain.quoteAdjustment.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.quoteAdjustment.mapper.QuoteAdjustmentMapper;

public class QuoteAdjustment implements Serializable{

private static final long serialVersionUID = 1L;
private String quoteAdjustmentId;
private String quoteAdjustmentTypeId;
private String quoteId;
private String quoteItemSeqId;
private String comments;
private String description;
private BigDecimal amount;
private String productPromoId;
private String productPromoRuleId;
private String productPromoActionSeqId;
private String productFeatureId;
private String correspondingProductId;
private String sourceReferenceId;
private BigDecimal sourcePercentage;
private String customerReferenceId;
private String primaryGeoId;
private String secondaryGeoId;
private BigDecimal exemptAmount;
private String taxAuthGeoId;
private String taxAuthPartyId;
private String overrideGlAccountId;
private Boolean includeInTax;
private Boolean includeInShipping;
private Timestamp createdDate;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByUserLogin;

public String getQuoteAdjustmentId() {
return quoteAdjustmentId;
}

public void setQuoteAdjustmentId(String  quoteAdjustmentId) {
this.quoteAdjustmentId = quoteAdjustmentId;
}

public String getQuoteAdjustmentTypeId() {
return quoteAdjustmentTypeId;
}

public void setQuoteAdjustmentTypeId(String  quoteAdjustmentTypeId) {
this.quoteAdjustmentTypeId = quoteAdjustmentTypeId;
}

public String getQuoteId() {
return quoteId;
}

public void setQuoteId(String  quoteId) {
this.quoteId = quoteId;
}

public String getQuoteItemSeqId() {
return quoteItemSeqId;
}

public void setQuoteItemSeqId(String  quoteItemSeqId) {
this.quoteItemSeqId = quoteItemSeqId;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}

public String getProductPromoId() {
return productPromoId;
}

public void setProductPromoId(String  productPromoId) {
this.productPromoId = productPromoId;
}

public String getProductPromoRuleId() {
return productPromoRuleId;
}

public void setProductPromoRuleId(String  productPromoRuleId) {
this.productPromoRuleId = productPromoRuleId;
}

public String getProductPromoActionSeqId() {
return productPromoActionSeqId;
}

public void setProductPromoActionSeqId(String  productPromoActionSeqId) {
this.productPromoActionSeqId = productPromoActionSeqId;
}

public String getProductFeatureId() {
return productFeatureId;
}

public void setProductFeatureId(String  productFeatureId) {
this.productFeatureId = productFeatureId;
}

public String getCorrespondingProductId() {
return correspondingProductId;
}

public void setCorrespondingProductId(String  correspondingProductId) {
this.correspondingProductId = correspondingProductId;
}

public String getSourceReferenceId() {
return sourceReferenceId;
}

public void setSourceReferenceId(String  sourceReferenceId) {
this.sourceReferenceId = sourceReferenceId;
}

public BigDecimal getSourcePercentage() {
return sourcePercentage;
}

public void setSourcePercentage(BigDecimal  sourcePercentage) {
this.sourcePercentage = sourcePercentage;
}

public String getCustomerReferenceId() {
return customerReferenceId;
}

public void setCustomerReferenceId(String  customerReferenceId) {
this.customerReferenceId = customerReferenceId;
}

public String getPrimaryGeoId() {
return primaryGeoId;
}

public void setPrimaryGeoId(String  primaryGeoId) {
this.primaryGeoId = primaryGeoId;
}

public String getSecondaryGeoId() {
return secondaryGeoId;
}

public void setSecondaryGeoId(String  secondaryGeoId) {
this.secondaryGeoId = secondaryGeoId;
}

public BigDecimal getExemptAmount() {
return exemptAmount;
}

public void setExemptAmount(BigDecimal  exemptAmount) {
this.exemptAmount = exemptAmount;
}

public String getTaxAuthGeoId() {
return taxAuthGeoId;
}

public void setTaxAuthGeoId(String  taxAuthGeoId) {
this.taxAuthGeoId = taxAuthGeoId;
}

public String getTaxAuthPartyId() {
return taxAuthPartyId;
}

public void setTaxAuthPartyId(String  taxAuthPartyId) {
this.taxAuthPartyId = taxAuthPartyId;
}

public String getOverrideGlAccountId() {
return overrideGlAccountId;
}

public void setOverrideGlAccountId(String  overrideGlAccountId) {
this.overrideGlAccountId = overrideGlAccountId;
}

public Boolean getIncludeInTax() {
return includeInTax;
}

public void setIncludeInTax(Boolean  includeInTax) {
this.includeInTax = includeInTax;
}

public Boolean getIncludeInShipping() {
return includeInShipping;
}

public void setIncludeInShipping(Boolean  includeInShipping) {
this.includeInShipping = includeInShipping;
}

public Timestamp getCreatedDate() {
return createdDate;
}

public void setCreatedDate(Timestamp  createdDate) {
this.createdDate = createdDate;
}

public String getCreatedByUserLogin() {
return createdByUserLogin;
}

public void setCreatedByUserLogin(String  createdByUserLogin) {
this.createdByUserLogin = createdByUserLogin;
}

public Timestamp getLastModifiedDate() {
return lastModifiedDate;
}

public void setLastModifiedDate(Timestamp  lastModifiedDate) {
this.lastModifiedDate = lastModifiedDate;
}

public String getLastModifiedByUserLogin() {
return lastModifiedByUserLogin;
}

public void setLastModifiedByUserLogin(String  lastModifiedByUserLogin) {
this.lastModifiedByUserLogin = lastModifiedByUserLogin;
}


public Map<String, Object> mapAttributeField() {
return QuoteAdjustmentMapper.map(this);
}
}
