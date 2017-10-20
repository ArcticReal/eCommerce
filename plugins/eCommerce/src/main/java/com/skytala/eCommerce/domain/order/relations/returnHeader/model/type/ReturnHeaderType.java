package com.skytala.eCommerce.domain.order.relations.returnHeader.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.returnHeader.mapper.type.ReturnHeaderTypeMapper;

public class ReturnHeaderType implements Serializable{

private static final long serialVersionUID = 1L;
private String returnHeaderTypeId;
private String parentTypeId;
private String description;

public String getReturnHeaderTypeId() {
return returnHeaderTypeId;
}

public void setReturnHeaderTypeId(String  returnHeaderTypeId) {
this.returnHeaderTypeId = returnHeaderTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ReturnHeaderTypeMapper.map(this);
}
}
