package com.skytala.eCommerce.domain.product.relations.containerType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.containerType.mapper.ContainerTypeMapper;

public class ContainerType implements Serializable{

private static final long serialVersionUID = 1L;
private String containerTypeId;
private String description;

public String getContainerTypeId() {
return containerTypeId;
}

public void setContainerTypeId(String  containerTypeId) {
this.containerTypeId = containerTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ContainerTypeMapper.map(this);
}
}
