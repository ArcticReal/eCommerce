package com.skytala.eCommerce.domain.content.relations.contentAttribute.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.contentAttribute.mapper.ContentAttributeMapper;

public class ContentAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String contentId;
private String attrName;
private Long attrValue;
private String attrDescription;

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public Long getAttrValue() {
return attrValue;
}

public void setAttrValue(Long  attrValue) {
this.attrValue = attrValue;
}

public String getAttrDescription() {
return attrDescription;
}

public void setAttrDescription(String  attrDescription) {
this.attrDescription = attrDescription;
}


public Map<String, Object> mapAttributeField() {
return ContentAttributeMapper.map(this);
}
}
