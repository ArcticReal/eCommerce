package com.skytala.eCommerce.domain.content.relations.dataResource.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.DataResourceMapper;

public class DataResource implements Serializable{

private static final long serialVersionUID = 1L;
private String dataResourceId;
private String dataResourceTypeId;
private String dataTemplateTypeId;
private String dataCategoryId;
private String dataSourceId;
private String statusId;
private Long dataResourceName;
private String localeString;
private String mimeTypeId;
private String characterSetId;
private String objectInfo;
private String surveyId;
private String surveyResponseId;
private String relatedDetailId;
private Boolean isPublic;
private Timestamp createdDate;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByUserLogin;

public String getDataResourceId() {
return dataResourceId;
}

public void setDataResourceId(String  dataResourceId) {
this.dataResourceId = dataResourceId;
}

public String getDataResourceTypeId() {
return dataResourceTypeId;
}

public void setDataResourceTypeId(String  dataResourceTypeId) {
this.dataResourceTypeId = dataResourceTypeId;
}

public String getDataTemplateTypeId() {
return dataTemplateTypeId;
}

public void setDataTemplateTypeId(String  dataTemplateTypeId) {
this.dataTemplateTypeId = dataTemplateTypeId;
}

public String getDataCategoryId() {
return dataCategoryId;
}

public void setDataCategoryId(String  dataCategoryId) {
this.dataCategoryId = dataCategoryId;
}

public String getDataSourceId() {
return dataSourceId;
}

public void setDataSourceId(String  dataSourceId) {
this.dataSourceId = dataSourceId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public Long getDataResourceName() {
return dataResourceName;
}

public void setDataResourceName(Long  dataResourceName) {
this.dataResourceName = dataResourceName;
}

public String getLocaleString() {
return localeString;
}

public void setLocaleString(String  localeString) {
this.localeString = localeString;
}

public String getMimeTypeId() {
return mimeTypeId;
}

public void setMimeTypeId(String  mimeTypeId) {
this.mimeTypeId = mimeTypeId;
}

public String getCharacterSetId() {
return characterSetId;
}

public void setCharacterSetId(String  characterSetId) {
this.characterSetId = characterSetId;
}

public String getObjectInfo() {
return objectInfo;
}

public void setObjectInfo(String  objectInfo) {
this.objectInfo = objectInfo;
}

public String getSurveyId() {
return surveyId;
}

public void setSurveyId(String  surveyId) {
this.surveyId = surveyId;
}

public String getSurveyResponseId() {
return surveyResponseId;
}

public void setSurveyResponseId(String  surveyResponseId) {
this.surveyResponseId = surveyResponseId;
}

public String getRelatedDetailId() {
return relatedDetailId;
}

public void setRelatedDetailId(String  relatedDetailId) {
this.relatedDetailId = relatedDetailId;
}

public Boolean getIsPublic() {
return isPublic;
}

public void setIsPublic(Boolean  isPublic) {
this.isPublic = isPublic;
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
return DataResourceMapper.map(this);
}
}
