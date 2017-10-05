package com.skytala.eCommerce.domain.returnType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.returnType.mapper.ReturnTypeMapper;

public class ReturnType implements Serializable{

private static final long serialVersionUID = 1L;
private String returnTypeId;
private String description;
private String sequenceId;

public String getReturnTypeId() {
return returnTypeId;
}

public void setReturnTypeId(String  returnTypeId) {
this.returnTypeId = returnTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getSequenceId() {
return sequenceId;
}

public void setSequenceId(String  sequenceId) {
this.sequenceId = sequenceId;
}


public Map<String, Object> mapAttributeField() {
return ReturnTypeMapper.map(this);
}
}
