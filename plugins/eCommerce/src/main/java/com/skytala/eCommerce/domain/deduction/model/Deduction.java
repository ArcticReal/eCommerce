package com.skytala.eCommerce.domain.deduction.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.deduction.mapper.DeductionMapper;

public class Deduction implements Serializable{

private static final long serialVersionUID = 1L;
private String deductionId;
private String deductionTypeId;
private String paymentId;
private BigDecimal amount;

public String getDeductionId() {
return deductionId;
}

public void setDeductionId(String  deductionId) {
this.deductionId = deductionId;
}

public String getDeductionTypeId() {
return deductionTypeId;
}

public void setDeductionTypeId(String  deductionTypeId) {
this.deductionTypeId = deductionTypeId;
}

public String getPaymentId() {
return paymentId;
}

public void setPaymentId(String  paymentId) {
this.paymentId = paymentId;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}


public Map<String, Object> mapAttributeField() {
return DeductionMapper.map(this);
}
}
