package com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.mapper.ProductStoreEmailSettingMapper;

public class ProductStoreEmailSetting implements Serializable{

private static final long serialVersionUID = 1L;
private String productStoreId;
private String emailType;
private String bodyScreenLocation;
private String xslfoAttachScreenLocation;
private String fromAddress;
private String ccAddress;
private String bccAddress;
private String subject;
private String contentType;

public String getProductStoreId() {
return productStoreId;
}

public void setProductStoreId(String  productStoreId) {
this.productStoreId = productStoreId;
}

public String getEmailType() {
return emailType;
}

public void setEmailType(String  emailType) {
this.emailType = emailType;
}

public String getBodyScreenLocation() {
return bodyScreenLocation;
}

public void setBodyScreenLocation(String  bodyScreenLocation) {
this.bodyScreenLocation = bodyScreenLocation;
}

public String getXslfoAttachScreenLocation() {
return xslfoAttachScreenLocation;
}

public void setXslfoAttachScreenLocation(String  xslfoAttachScreenLocation) {
this.xslfoAttachScreenLocation = xslfoAttachScreenLocation;
}

public String getFromAddress() {
return fromAddress;
}

public void setFromAddress(String  fromAddress) {
this.fromAddress = fromAddress;
}

public String getCcAddress() {
return ccAddress;
}

public void setCcAddress(String  ccAddress) {
this.ccAddress = ccAddress;
}

public String getBccAddress() {
return bccAddress;
}

public void setBccAddress(String  bccAddress) {
this.bccAddress = bccAddress;
}

public String getSubject() {
return subject;
}

public void setSubject(String  subject) {
this.subject = subject;
}

public String getContentType() {
return contentType;
}

public void setContentType(String  contentType) {
this.contentType = contentType;
}


public Map<String, Object> mapAttributeField() {
return ProductStoreEmailSettingMapper.map(this);
}
}
