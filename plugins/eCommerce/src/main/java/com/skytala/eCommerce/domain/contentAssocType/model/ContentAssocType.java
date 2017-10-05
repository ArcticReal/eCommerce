package com.skytala.eCommerce.domain.contentAssocType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.contentAssocType.mapper.ContentAssocTypeMapper;

public class ContentAssocType implements Serializable{

private static final long serialVersionUID = 1L;
private String contentAssocTypeId;
private String description;

public String getContentAssocTypeId() {
return contentAssocTypeId;
}

public void setContentAssocTypeId(String  contentAssocTypeId) {
this.contentAssocTypeId = contentAssocTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ContentAssocTypeMapper.map(this);
}
}
