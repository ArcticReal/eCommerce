package com.skytala.eCommerce.domain.party.relations.affiliate.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.affiliate.mapper.AffiliateMapper;

public class Affiliate implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String affiliateName;
private String affiliateDescription;
private String yearEstablished;
private String siteType;
private String sitePageViews;
private String siteVisitors;
private Timestamp dateTimeCreated;
private Timestamp dateTimeApproved;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getAffiliateName() {
return affiliateName;
}

public void setAffiliateName(String  affiliateName) {
this.affiliateName = affiliateName;
}

public String getAffiliateDescription() {
return affiliateDescription;
}

public void setAffiliateDescription(String  affiliateDescription) {
this.affiliateDescription = affiliateDescription;
}

public String getYearEstablished() {
return yearEstablished;
}

public void setYearEstablished(String  yearEstablished) {
this.yearEstablished = yearEstablished;
}

public String getSiteType() {
return siteType;
}

public void setSiteType(String  siteType) {
this.siteType = siteType;
}

public String getSitePageViews() {
return sitePageViews;
}

public void setSitePageViews(String  sitePageViews) {
this.sitePageViews = sitePageViews;
}

public String getSiteVisitors() {
return siteVisitors;
}

public void setSiteVisitors(String  siteVisitors) {
this.siteVisitors = siteVisitors;
}

public Timestamp getDateTimeCreated() {
return dateTimeCreated;
}

public void setDateTimeCreated(Timestamp  dateTimeCreated) {
this.dateTimeCreated = dateTimeCreated;
}

public Timestamp getDateTimeApproved() {
return dateTimeApproved;
}

public void setDateTimeApproved(Timestamp  dateTimeApproved) {
this.dateTimeApproved = dateTimeApproved;
}


public Map<String, Object> mapAttributeField() {
return AffiliateMapper.map(this);
}
}
