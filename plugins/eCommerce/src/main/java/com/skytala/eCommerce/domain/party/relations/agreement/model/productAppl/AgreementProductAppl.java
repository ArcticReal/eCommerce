package com.skytala.eCommerce.domain.party.relations.agreement.model.productAppl;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.productAppl.AgreementProductApplMapper;

public class AgreementProductAppl implements Serializable{

private static final long serialVersionUID = 1L;
private String agreementId;
private String agreementItemSeqId;
private String productId;
private BigDecimal price;

public String getAgreementId() {
return agreementId;
}

public void setAgreementId(String  agreementId) {
this.agreementId = agreementId;
}

public String getAgreementItemSeqId() {
return agreementItemSeqId;
}

public void setAgreementItemSeqId(String  agreementItemSeqId) {
this.agreementItemSeqId = agreementItemSeqId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public BigDecimal getPrice() {
return price;
}

public void setPrice(BigDecimal  price) {
this.price = price;
}


public Map<String, Object> mapAttributeField() {
return AgreementProductApplMapper.map(this);
}
}
