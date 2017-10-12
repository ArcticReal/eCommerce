package com.skytala.eCommerce.domain.marketing.relations.webSiteContactList.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.webSiteContactList.mapper.WebSiteContactListMapper;

public class WebSiteContactList implements Serializable{

private static final long serialVersionUID = 1L;
private String webSiteId;
private String contactListId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getWebSiteId() {
return webSiteId;
}

public void setWebSiteId(String  webSiteId) {
this.webSiteId = webSiteId;
}

public String getContactListId() {
return contactListId;
}

public void setContactListId(String  contactListId) {
this.contactListId = contactListId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}


public Map<String, Object> mapAttributeField() {
return WebSiteContactListMapper.map(this);
}
}
