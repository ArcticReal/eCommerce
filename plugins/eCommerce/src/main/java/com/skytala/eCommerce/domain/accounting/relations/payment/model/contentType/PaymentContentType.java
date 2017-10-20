package com.skytala.eCommerce.domain.accounting.relations.payment.model.contentType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.contentType.PaymentContentTypeMapper;

public class PaymentContentType implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentContentTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getPaymentContentTypeId() {
return paymentContentTypeId;
}

public void setPaymentContentTypeId(String  paymentContentTypeId) {
this.paymentContentTypeId = paymentContentTypeId;
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
return PaymentContentTypeMapper.map(this);
}
}
