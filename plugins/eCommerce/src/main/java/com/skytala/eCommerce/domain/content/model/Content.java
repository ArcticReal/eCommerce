package com.skytala.eCommerce.domain.content.model;
import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.mapper.ContentMapper;

public class Content implements Serializable{

private static final long serialVersionUID = 1L;
private String contentId;
private String contentTypeId;
private String ownerContentId;
private String decoratorContentId;
private String instanceOfContentId;
private String dataResourceId;
private String templateDataResourceId;
private String dataSourceId;
private String statusId;
private String privilegeEnumId;
private String serviceName;
private String customMethodId;
private Long contentName;
private String description;
private String localeString;
private String mimeTypeId;
private String characterSetId;
private Long childLeafCount;
private Long childBranchCount;
private Timestamp createdDate;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByUserLogin;

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getContentTypeId() {
return contentTypeId;
}

public void setContentTypeId(String  contentTypeId) {
this.contentTypeId = contentTypeId;
}

public String getOwnerContentId() {
return ownerContentId;
}

public void setOwnerContentId(String  ownerContentId) {
this.ownerContentId = ownerContentId;
}

public String getDecoratorContentId() {
return decoratorContentId;
}

public void setDecoratorContentId(String  decoratorContentId) {
this.decoratorContentId = decoratorContentId;
}

public String getInstanceOfContentId() {
return instanceOfContentId;
}

public void setInstanceOfContentId(String  instanceOfContentId) {
this.instanceOfContentId = instanceOfContentId;
}

public String getDataResourceId() {
return dataResourceId;
}

public void setDataResourceId(String  dataResourceId) {
this.dataResourceId = dataResourceId;
}

public String getTemplateDataResourceId() {
return templateDataResourceId;
}

public void setTemplateDataResourceId(String  templateDataResourceId) {
this.templateDataResourceId = templateDataResourceId;
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

public String getPrivilegeEnumId() {
return privilegeEnumId;
}

public void setPrivilegeEnumId(String  privilegeEnumId) {
this.privilegeEnumId = privilegeEnumId;
}

public String getServiceName() {
return serviceName;
}

public void setServiceName(String  serviceName) {
this.serviceName = serviceName;
}

public String getCustomMethodId() {
return customMethodId;
}

public void setCustomMethodId(String  customMethodId) {
this.customMethodId = customMethodId;
}

public Long getContentName() {
return contentName;
}

public void setContentName(Long  contentName) {
this.contentName = contentName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
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

public Long getChildLeafCount() {
return childLeafCount;
}

public void setChildLeafCount(Long  childLeafCount) {
this.childLeafCount = childLeafCount;
}

public Long getChildBranchCount() {
return childBranchCount;
}

public void setChildBranchCount(Long  childBranchCount) {
this.childBranchCount = childBranchCount;
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
return ContentMapper.map(this);
}
}
