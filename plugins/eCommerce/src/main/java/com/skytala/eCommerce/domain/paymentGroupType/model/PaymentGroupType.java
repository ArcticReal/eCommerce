package com.skytala.eCommerce.domain.paymentGroupType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.paymentGroupType.mapper.PaymentGroupTypeMapper;

public class PaymentGroupType implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGroupTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getPaymentGroupTypeId() {
return paymentGroupTypeId;
}

public void setPaymentGroupTypeId(String  paymentGroupTypeId) {
this.paymentGroupTypeId = paymentGroupTypeId;
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
return PaymentGroupTypeMapper.map(this);
}
}
