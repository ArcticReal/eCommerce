package com.skytala.eCommerce.domain.workeffort.relations.deliverable.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.mapper.DeliverableMapper;

public class Deliverable implements Serializable{

private static final long serialVersionUID = 1L;
private String deliverableId;
private String deliverableTypeId;
private String deliverableName;
private String description;

public String getDeliverableId() {
return deliverableId;
}

public void setDeliverableId(String  deliverableId) {
this.deliverableId = deliverableId;
}

public String getDeliverableTypeId() {
return deliverableTypeId;
}

public void setDeliverableTypeId(String  deliverableTypeId) {
this.deliverableTypeId = deliverableTypeId;
}

public String getDeliverableName() {
return deliverableName;
}

public void setDeliverableName(String  deliverableName) {
this.deliverableName = deliverableName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return DeliverableMapper.map(this);
}
}
