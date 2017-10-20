package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.mapper.type.ProdConfItemContentTypeMapper;

public class ProdConfItemContentType implements Serializable{

private static final long serialVersionUID = 1L;
private String confItemContentTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getConfItemContentTypeId() {
return confItemContentTypeId;
}

public void setConfItemContentTypeId(String  confItemContentTypeId) {
this.confItemContentTypeId = confItemContentTypeId;
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
return ProdConfItemContentTypeMapper.map(this);
}
}
