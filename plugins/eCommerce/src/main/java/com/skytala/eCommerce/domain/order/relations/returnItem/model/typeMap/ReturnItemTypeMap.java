package com.skytala.eCommerce.domain.order.relations.returnItem.model.typeMap;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.returnItem.mapper.typeMap.ReturnItemTypeMapMapper;

public class ReturnItemTypeMap implements Serializable{

private static final long serialVersionUID = 1L;
private String returnItemMapKey;
private String returnHeaderTypeId;
private String returnItemTypeId;

public String getReturnItemMapKey() {
return returnItemMapKey;
}

public void setReturnItemMapKey(String  returnItemMapKey) {
this.returnItemMapKey = returnItemMapKey;
}

public String getReturnHeaderTypeId() {
return returnHeaderTypeId;
}

public void setReturnHeaderTypeId(String  returnHeaderTypeId) {
this.returnHeaderTypeId = returnHeaderTypeId;
}

public String getReturnItemTypeId() {
return returnItemTypeId;
}

public void setReturnItemTypeId(String  returnItemTypeId) {
this.returnItemTypeId = returnItemTypeId;
}


public Map<String, Object> mapAttributeField() {
return ReturnItemTypeMapMapper.map(this);
}
}
