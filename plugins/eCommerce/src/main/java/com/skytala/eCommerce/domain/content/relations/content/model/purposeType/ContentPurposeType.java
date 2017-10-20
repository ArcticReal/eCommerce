package com.skytala.eCommerce.domain.content.relations.content.model.purposeType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.content.mapper.purposeType.ContentPurposeTypeMapper;

public class ContentPurposeType implements Serializable{

private static final long serialVersionUID = 1L;
private String contentPurposeTypeId;
private String description;

public String getContentPurposeTypeId() {
return contentPurposeTypeId;
}

public void setContentPurposeTypeId(String  contentPurposeTypeId) {
this.contentPurposeTypeId = contentPurposeTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ContentPurposeTypeMapper.map(this);
}
}
