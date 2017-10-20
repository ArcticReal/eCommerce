package com.skytala.eCommerce.domain.content.relations.content.model.typeAttr;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.content.mapper.typeAttr.ContentTypeAttrMapper;

public class ContentTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String contentTypeId;
private String attrName;
private String description;

public String getContentTypeId() {
return contentTypeId;
}

public void setContentTypeId(String  contentTypeId) {
this.contentTypeId = contentTypeId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ContentTypeAttrMapper.map(this);
}
}
