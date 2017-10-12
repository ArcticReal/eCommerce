package com.skytala.eCommerce.domain.workeffort.relations.workEffort.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.WorkEffortMapper;

public class WorkEffort implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String workEffortTypeId;
private String currentStatusId;
private Timestamp lastStatusUpdate;
private String workEffortPurposeTypeId;
private String workEffortParentId;
private String scopeEnumId;
private Long priority;
private Long percentComplete;
private String workEffortName;
private String showAsEnumId;
private Boolean sendNotificationEmail;
private String description;
private String locationDesc;
private Timestamp estimatedStartDate;
private Timestamp estimatedCompletionDate;
private Timestamp actualStartDate;
private Timestamp actualCompletionDate;
private BigDecimal estimatedMilliSeconds;
private BigDecimal estimatedSetupMillis;
private String estimateCalcMethod;
private BigDecimal actualMilliSeconds;
private BigDecimal actualSetupMillis;
private BigDecimal totalMilliSecondsAllowed;
private BigDecimal totalMoneyAllowed;
private String moneyUomId;
private String specialTerms;
private Long timeTransparency;
private String universalId;
private String sourceReferenceId;
private String fixedAssetId;
private String facilityId;
private String infoUrl;
private String recurrenceInfoId;
private String tempExprId;
private String runtimeDataId;
private String noteId;
private String serviceLoaderName;
private BigDecimal quantityToProduce;
private BigDecimal quantityProduced;
private BigDecimal quantityRejected;
private BigDecimal reservPersons;
private BigDecimal reserv2ndPPPerc;
private BigDecimal reservNthPPPerc;
private String accommodationMapId;
private String accommodationSpotId;
private Long revisionNumber;
private Timestamp createdDate;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByUserLogin;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getWorkEffortTypeId() {
return workEffortTypeId;
}

public void setWorkEffortTypeId(String  workEffortTypeId) {
this.workEffortTypeId = workEffortTypeId;
}

public String getCurrentStatusId() {
return currentStatusId;
}

public void setCurrentStatusId(String  currentStatusId) {
this.currentStatusId = currentStatusId;
}

public Timestamp getLastStatusUpdate() {
return lastStatusUpdate;
}

public void setLastStatusUpdate(Timestamp  lastStatusUpdate) {
this.lastStatusUpdate = lastStatusUpdate;
}

public String getWorkEffortPurposeTypeId() {
return workEffortPurposeTypeId;
}

public void setWorkEffortPurposeTypeId(String  workEffortPurposeTypeId) {
this.workEffortPurposeTypeId = workEffortPurposeTypeId;
}

public String getWorkEffortParentId() {
return workEffortParentId;
}

public void setWorkEffortParentId(String  workEffortParentId) {
this.workEffortParentId = workEffortParentId;
}

public String getScopeEnumId() {
return scopeEnumId;
}

public void setScopeEnumId(String  scopeEnumId) {
this.scopeEnumId = scopeEnumId;
}

public Long getPriority() {
return priority;
}

public void setPriority(Long  priority) {
this.priority = priority;
}

public Long getPercentComplete() {
return percentComplete;
}

public void setPercentComplete(Long  percentComplete) {
this.percentComplete = percentComplete;
}

public String getWorkEffortName() {
return workEffortName;
}

public void setWorkEffortName(String  workEffortName) {
this.workEffortName = workEffortName;
}

public String getShowAsEnumId() {
return showAsEnumId;
}

public void setShowAsEnumId(String  showAsEnumId) {
this.showAsEnumId = showAsEnumId;
}

public Boolean getSendNotificationEmail() {
return sendNotificationEmail;
}

public void setSendNotificationEmail(Boolean  sendNotificationEmail) {
this.sendNotificationEmail = sendNotificationEmail;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getLocationDesc() {
return locationDesc;
}

public void setLocationDesc(String  locationDesc) {
this.locationDesc = locationDesc;
}

public Timestamp getEstimatedStartDate() {
return estimatedStartDate;
}

public void setEstimatedStartDate(Timestamp  estimatedStartDate) {
this.estimatedStartDate = estimatedStartDate;
}

public Timestamp getEstimatedCompletionDate() {
return estimatedCompletionDate;
}

public void setEstimatedCompletionDate(Timestamp  estimatedCompletionDate) {
this.estimatedCompletionDate = estimatedCompletionDate;
}

public Timestamp getActualStartDate() {
return actualStartDate;
}

public void setActualStartDate(Timestamp  actualStartDate) {
this.actualStartDate = actualStartDate;
}

public Timestamp getActualCompletionDate() {
return actualCompletionDate;
}

public void setActualCompletionDate(Timestamp  actualCompletionDate) {
this.actualCompletionDate = actualCompletionDate;
}

public BigDecimal getEstimatedMilliSeconds() {
return estimatedMilliSeconds;
}

public void setEstimatedMilliSeconds(BigDecimal  estimatedMilliSeconds) {
this.estimatedMilliSeconds = estimatedMilliSeconds;
}

public BigDecimal getEstimatedSetupMillis() {
return estimatedSetupMillis;
}

public void setEstimatedSetupMillis(BigDecimal  estimatedSetupMillis) {
this.estimatedSetupMillis = estimatedSetupMillis;
}

public String getEstimateCalcMethod() {
return estimateCalcMethod;
}

public void setEstimateCalcMethod(String  estimateCalcMethod) {
this.estimateCalcMethod = estimateCalcMethod;
}

public BigDecimal getActualMilliSeconds() {
return actualMilliSeconds;
}

public void setActualMilliSeconds(BigDecimal  actualMilliSeconds) {
this.actualMilliSeconds = actualMilliSeconds;
}

public BigDecimal getActualSetupMillis() {
return actualSetupMillis;
}

public void setActualSetupMillis(BigDecimal  actualSetupMillis) {
this.actualSetupMillis = actualSetupMillis;
}

public BigDecimal getTotalMilliSecondsAllowed() {
return totalMilliSecondsAllowed;
}

public void setTotalMilliSecondsAllowed(BigDecimal  totalMilliSecondsAllowed) {
this.totalMilliSecondsAllowed = totalMilliSecondsAllowed;
}

public BigDecimal getTotalMoneyAllowed() {
return totalMoneyAllowed;
}

public void setTotalMoneyAllowed(BigDecimal  totalMoneyAllowed) {
this.totalMoneyAllowed = totalMoneyAllowed;
}

public String getMoneyUomId() {
return moneyUomId;
}

public void setMoneyUomId(String  moneyUomId) {
this.moneyUomId = moneyUomId;
}

public String getSpecialTerms() {
return specialTerms;
}

public void setSpecialTerms(String  specialTerms) {
this.specialTerms = specialTerms;
}

public Long getTimeTransparency() {
return timeTransparency;
}

public void setTimeTransparency(Long  timeTransparency) {
this.timeTransparency = timeTransparency;
}

public String getUniversalId() {
return universalId;
}

public void setUniversalId(String  universalId) {
this.universalId = universalId;
}

public String getSourceReferenceId() {
return sourceReferenceId;
}

public void setSourceReferenceId(String  sourceReferenceId) {
this.sourceReferenceId = sourceReferenceId;
}

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public String getInfoUrl() {
return infoUrl;
}

public void setInfoUrl(String  infoUrl) {
this.infoUrl = infoUrl;
}

public String getRecurrenceInfoId() {
return recurrenceInfoId;
}

public void setRecurrenceInfoId(String  recurrenceInfoId) {
this.recurrenceInfoId = recurrenceInfoId;
}

public String getTempExprId() {
return tempExprId;
}

public void setTempExprId(String  tempExprId) {
this.tempExprId = tempExprId;
}

public String getRuntimeDataId() {
return runtimeDataId;
}

public void setRuntimeDataId(String  runtimeDataId) {
this.runtimeDataId = runtimeDataId;
}

public String getNoteId() {
return noteId;
}

public void setNoteId(String  noteId) {
this.noteId = noteId;
}

public String getServiceLoaderName() {
return serviceLoaderName;
}

public void setServiceLoaderName(String  serviceLoaderName) {
this.serviceLoaderName = serviceLoaderName;
}

public BigDecimal getQuantityToProduce() {
return quantityToProduce;
}

public void setQuantityToProduce(BigDecimal  quantityToProduce) {
this.quantityToProduce = quantityToProduce;
}

public BigDecimal getQuantityProduced() {
return quantityProduced;
}

public void setQuantityProduced(BigDecimal  quantityProduced) {
this.quantityProduced = quantityProduced;
}

public BigDecimal getQuantityRejected() {
return quantityRejected;
}

public void setQuantityRejected(BigDecimal  quantityRejected) {
this.quantityRejected = quantityRejected;
}

public BigDecimal getReservPersons() {
return reservPersons;
}

public void setReservPersons(BigDecimal  reservPersons) {
this.reservPersons = reservPersons;
}

public BigDecimal getReserv2ndPPPerc() {
return reserv2ndPPPerc;
}

public void setReserv2ndPPPerc(BigDecimal  reserv2ndPPPerc) {
this.reserv2ndPPPerc = reserv2ndPPPerc;
}

public BigDecimal getReservNthPPPerc() {
return reservNthPPPerc;
}

public void setReservNthPPPerc(BigDecimal  reservNthPPPerc) {
this.reservNthPPPerc = reservNthPPPerc;
}

public String getAccommodationMapId() {
return accommodationMapId;
}

public void setAccommodationMapId(String  accommodationMapId) {
this.accommodationMapId = accommodationMapId;
}

public String getAccommodationSpotId() {
return accommodationSpotId;
}

public void setAccommodationSpotId(String  accommodationSpotId) {
this.accommodationSpotId = accommodationSpotId;
}

public Long getRevisionNumber() {
return revisionNumber;
}

public void setRevisionNumber(Long  revisionNumber) {
this.revisionNumber = revisionNumber;
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
return WorkEffortMapper.map(this);
}
}
