package com.skytala.eCommerce.domain.content.relations.content.model.operation;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.content.mapper.operation.ContentOperationMapper;

public class ContentOperation implements Serializable{

private static final long serialVersionUID = 1L;
private String contentOperationId;
private String description;

public String getContentOperationId() {
return contentOperationId;
}

public void setContentOperationId(String  contentOperationId) {
this.contentOperationId = contentOperationId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ContentOperationMapper.map(this);
}
}
