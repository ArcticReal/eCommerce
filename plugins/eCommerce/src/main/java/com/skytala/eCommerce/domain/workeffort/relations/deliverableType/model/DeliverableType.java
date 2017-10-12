package com.skytala.eCommerce.domain.workeffort.relations.deliverableType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.deliverableType.mapper.DeliverableTypeMapper;

public class DeliverableType implements Serializable{

private static final long serialVersionUID = 1L;
private String deliverableTypeId;
private String description;

public String getDeliverableTypeId() {
return deliverableTypeId;
}

public void setDeliverableTypeId(String  deliverableTypeId) {
this.deliverableTypeId = deliverableTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return DeliverableTypeMapper.map(this);
}
}
