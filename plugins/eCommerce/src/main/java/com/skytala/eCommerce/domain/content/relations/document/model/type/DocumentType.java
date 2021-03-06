package com.skytala.eCommerce.domain.content.relations.document.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.document.mapper.type.DocumentTypeMapper;

public class DocumentType implements Serializable{

private static final long serialVersionUID = 1L;
private String documentTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getDocumentTypeId() {
return documentTypeId;
}

public void setDocumentTypeId(String  documentTypeId) {
this.documentTypeId = documentTypeId;
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
return DocumentTypeMapper.map(this);
}
}
