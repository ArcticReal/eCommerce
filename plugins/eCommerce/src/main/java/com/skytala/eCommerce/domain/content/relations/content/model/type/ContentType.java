package com.skytala.eCommerce.domain.content.relations.content.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.content.mapper.type.ContentTypeMapper;

public class ContentType implements Serializable{

private static final long serialVersionUID = 1L;
private String contentTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getContentTypeId() {
return contentTypeId;
}

public void setContentTypeId(String  contentTypeId) {
this.contentTypeId = contentTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ContentTypeMapper.map(this);
}
}
