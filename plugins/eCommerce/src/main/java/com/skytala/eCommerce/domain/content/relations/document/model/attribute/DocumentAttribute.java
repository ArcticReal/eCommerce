package com.skytala.eCommerce.domain.content.relations.document.model.attribute;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.document.mapper.attribute.DocumentAttributeMapper;

public class DocumentAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String documentId;
private String attrName;
private Long attrValue;
private String attrDescription;

public String getDocumentId() {
return documentId;
}

public void setDocumentId(String  documentId) {
this.documentId = documentId;
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
return DocumentAttributeMapper.map(this);
}
}
