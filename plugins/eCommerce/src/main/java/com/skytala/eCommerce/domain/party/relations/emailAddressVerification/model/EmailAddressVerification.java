package com.skytala.eCommerce.domain.party.relations.emailAddressVerification.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.mapper.EmailAddressVerificationMapper;

public class EmailAddressVerification implements Serializable{

private static final long serialVersionUID = 1L;
private String emailAddress;
private Long verifyHash;
private Timestamp expireDate;

public String getEmailAddress() {
return emailAddress;
}

public void setEmailAddress(String  emailAddress) {
this.emailAddress = emailAddress;
}

public Long getVerifyHash() {
return verifyHash;
}

public void setVerifyHash(Long  verifyHash) {
this.verifyHash = verifyHash;
}

public Timestamp getExpireDate() {
return expireDate;
}

public void setExpireDate(Timestamp  expireDate) {
this.expireDate = expireDate;
}


public Map<String, Object> mapAttributeField() {
return EmailAddressVerificationMapper.map(this);
}
}
