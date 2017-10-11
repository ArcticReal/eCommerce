package com.skytala.eCommerce.domain.party.relations.agreementItem.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.agreementItem.mapper.AgreementItemMapper;

public class AgreementItem implements Serializable{

private static final long serialVersionUID = 1L;
private String agreementId;
private String agreementItemSeqId;
private String agreementItemTypeId;
private String currencyUomId;
private String agreementText;
private Object agreementImage;

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

public String getAgreementItemTypeId() {
return agreementItemTypeId;
}

public void setAgreementItemTypeId(String  agreementItemTypeId) {
this.agreementItemTypeId = agreementItemTypeId;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public String getAgreementText() {
return agreementText;
}

public void setAgreementText(String  agreementText) {
this.agreementText = agreementText;
}

public Object getAgreementImage() {
return agreementImage;
}

public void setAgreementImage(Object  agreementImage) {
this.agreementImage = agreementImage;
}


public Map<String, Object> mapAttributeField() {
return AgreementItemMapper.map(this);
}
}
