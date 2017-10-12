package com.skytala.eCommerce.domain.marketing.relations.contactList.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.ContactListMapper;

public class ContactList implements Serializable{

private static final long serialVersionUID = 1L;
private String contactListId;
private String contactListTypeId;
private String contactMechTypeId;
private String marketingCampaignId;
private String contactListName;
private String description;
private String comments;
private Boolean isPublic;
private Boolean singleUse;
private String ownerPartyId;
private String verifyEmailFrom;
private String verifyEmailScreen;
private String verifyEmailSubject;
private String verifyEmailWebSiteId;
private String optOutScreen;
private String createdByUserLogin;
private String lastModifiedByUserLogin;

public String getContactListId() {
return contactListId;
}

public void setContactListId(String  contactListId) {
this.contactListId = contactListId;
}

public String getContactListTypeId() {
return contactListTypeId;
}

public void setContactListTypeId(String  contactListTypeId) {
this.contactListTypeId = contactListTypeId;
}

public String getContactMechTypeId() {
return contactMechTypeId;
}

public void setContactMechTypeId(String  contactMechTypeId) {
this.contactMechTypeId = contactMechTypeId;
}

public String getMarketingCampaignId() {
return marketingCampaignId;
}

public void setMarketingCampaignId(String  marketingCampaignId) {
this.marketingCampaignId = marketingCampaignId;
}

public String getContactListName() {
return contactListName;
}

public void setContactListName(String  contactListName) {
this.contactListName = contactListName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public Boolean getIsPublic() {
return isPublic;
}

public void setIsPublic(Boolean  isPublic) {
this.isPublic = isPublic;
}

public Boolean getSingleUse() {
return singleUse;
}

public void setSingleUse(Boolean  singleUse) {
this.singleUse = singleUse;
}

public String getOwnerPartyId() {
return ownerPartyId;
}

public void setOwnerPartyId(String  ownerPartyId) {
this.ownerPartyId = ownerPartyId;
}

public String getVerifyEmailFrom() {
return verifyEmailFrom;
}

public void setVerifyEmailFrom(String  verifyEmailFrom) {
this.verifyEmailFrom = verifyEmailFrom;
}

public String getVerifyEmailScreen() {
return verifyEmailScreen;
}

public void setVerifyEmailScreen(String  verifyEmailScreen) {
this.verifyEmailScreen = verifyEmailScreen;
}

public String getVerifyEmailSubject() {
return verifyEmailSubject;
}

public void setVerifyEmailSubject(String  verifyEmailSubject) {
this.verifyEmailSubject = verifyEmailSubject;
}

public String getVerifyEmailWebSiteId() {
return verifyEmailWebSiteId;
}

public void setVerifyEmailWebSiteId(String  verifyEmailWebSiteId) {
this.verifyEmailWebSiteId = verifyEmailWebSiteId;
}

public String getOptOutScreen() {
return optOutScreen;
}

public void setOptOutScreen(String  optOutScreen) {
this.optOutScreen = optOutScreen;
}

public String getCreatedByUserLogin() {
return createdByUserLogin;
}

public void setCreatedByUserLogin(String  createdByUserLogin) {
this.createdByUserLogin = createdByUserLogin;
}

public String getLastModifiedByUserLogin() {
return lastModifiedByUserLogin;
}

public void setLastModifiedByUserLogin(String  lastModifiedByUserLogin) {
this.lastModifiedByUserLogin = lastModifiedByUserLogin;
}


public Map<String, Object> mapAttributeField() {
return ContactListMapper.map(this);
}
}
