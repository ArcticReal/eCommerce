package com.skytala.eCommerce.domain.content.relations.documentTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.documentTypeAttr.mapper.DocumentTypeAttrMapper;

public class DocumentTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String documentTypeId;
private String attrName;
private String description;

public String getDocumentTypeId() {
return documentTypeId;
}

public void setDocumentTypeId(String  documentTypeId) {
this.documentTypeId = documentTypeId;
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
return DocumentTypeAttrMapper.map(this);
}
}
