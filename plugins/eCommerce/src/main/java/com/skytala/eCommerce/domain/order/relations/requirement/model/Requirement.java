package com.skytala.eCommerce.domain.order.relations.requirement.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.RequirementMapper;

public class Requirement implements Serializable{

private static final long serialVersionUID = 1L;
private String requirementId;
private String requirementTypeId;
private String facilityId;
private String deliverableId;
private String fixedAssetId;
private String productId;
private String statusId;
private String description;
private Timestamp requirementStartDate;
private Timestamp requiredByDate;
private BigDecimal estimatedBudget;
private BigDecimal quantity;
private String useCase;
private String reason;
private Timestamp createdDate;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByUserLogin;

public String getRequirementId() {
return requirementId;
}

public void setRequirementId(String  requirementId) {
this.requirementId = requirementId;
}

public String getRequirementTypeId() {
return requirementTypeId;
}

public void setRequirementTypeId(String  requirementTypeId) {
this.requirementTypeId = requirementTypeId;
}

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public String getDeliverableId() {
return deliverableId;
}

public void setDeliverableId(String  deliverableId) {
this.deliverableId = deliverableId;
}

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public Timestamp getRequirementStartDate() {
return requirementStartDate;
}

public void setRequirementStartDate(Timestamp  requirementStartDate) {
this.requirementStartDate = requirementStartDate;
}

public Timestamp getRequiredByDate() {
return requiredByDate;
}

public void setRequiredByDate(Timestamp  requiredByDate) {
this.requiredByDate = requiredByDate;
}

public BigDecimal getEstimatedBudget() {
return estimatedBudget;
}

public void setEstimatedBudget(BigDecimal  estimatedBudget) {
this.estimatedBudget = estimatedBudget;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public String getUseCase() {
return useCase;
}

public void setUseCase(String  useCase) {
this.useCase = useCase;
}

public String getReason() {
return reason;
}

public void setReason(String  reason) {
this.reason = reason;
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
return RequirementMapper.map(this);
}
}
