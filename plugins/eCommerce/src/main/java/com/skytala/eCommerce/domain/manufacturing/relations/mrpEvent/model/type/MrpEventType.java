package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.mapper.type.MrpEventTypeMapper;

public class MrpEventType implements Serializable{

private static final long serialVersionUID = 1L;
private String mrpEventTypeId;
private String description;

public String getMrpEventTypeId() {
return mrpEventTypeId;
}

public void setMrpEventTypeId(String  mrpEventTypeId) {
this.mrpEventTypeId = mrpEventTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return MrpEventTypeMapper.map(this);
}
}
