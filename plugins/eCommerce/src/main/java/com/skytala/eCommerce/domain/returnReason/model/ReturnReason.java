package com.skytala.eCommerce.domain.returnReason.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.returnReason.mapper.ReturnReasonMapper;

public class ReturnReason implements Serializable{

private static final long serialVersionUID = 1L;
private String returnReasonId;
private String description;
private String sequenceId;

public String getReturnReasonId() {
return returnReasonId;
}

public void setReturnReasonId(String  returnReasonId) {
this.returnReasonId = returnReasonId;
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
return ReturnReasonMapper.map(this);
}
}
