package com.skytala.eCommerce.domain.accounting.relations.creditCardTypeGlAccount.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.creditCardTypeGlAccount.mapper.CreditCardTypeGlAccountMapper;

public class CreditCardTypeGlAccount implements Serializable{

private static final long serialVersionUID = 1L;
private String cardType;
private String organizationPartyId;
private String glAccountId;

public String getCardType() {
return cardType;
}

public void setCardType(String  cardType) {
this.cardType = cardType;
}

public String getOrganizationPartyId() {
return organizationPartyId;
}

public void setOrganizationPartyId(String  organizationPartyId) {
this.organizationPartyId = organizationPartyId;
}

public String getGlAccountId() {
return glAccountId;
}

public void setGlAccountId(String  glAccountId) {
this.glAccountId = glAccountId;
}


public Map<String, Object> mapAttributeField() {
return CreditCardTypeGlAccountMapper.map(this);
}
}
