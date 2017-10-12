package com.skytala.eCommerce.domain.content.relations.dataTemplateType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.dataTemplateType.mapper.DataTemplateTypeMapper;

public class DataTemplateType implements Serializable{

private static final long serialVersionUID = 1L;
private String dataTemplateTypeId;
private String description;
private String extension;

public String getDataTemplateTypeId() {
return dataTemplateTypeId;
}

public void setDataTemplateTypeId(String  dataTemplateTypeId) {
this.dataTemplateTypeId = dataTemplateTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getExtension() {
return extension;
}

public void setExtension(String  extension) {
this.extension = extension;
}


public Map<String, Object> mapAttributeField() {
return DataTemplateTypeMapper.map(this);
}
}
