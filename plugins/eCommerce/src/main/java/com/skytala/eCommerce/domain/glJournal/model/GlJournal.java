package com.skytala.eCommerce.domain.glJournal.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.glJournal.mapper.GlJournalMapper;

public class GlJournal implements Serializable{

private static final long serialVersionUID = 1L;
private String glJournalId;
private String glJournalName;
private String organizationPartyId;
private Boolean isPosted;
private Timestamp postedDate;

public String getGlJournalId() {
return glJournalId;
}

public void setGlJournalId(String  glJournalId) {
this.glJournalId = glJournalId;
}

public String getGlJournalName() {
return glJournalName;
}

public void setGlJournalName(String  glJournalName) {
this.glJournalName = glJournalName;
}

public String getOrganizationPartyId() {
return organizationPartyId;
}

public void setOrganizationPartyId(String  organizationPartyId) {
this.organizationPartyId = organizationPartyId;
}

public Boolean getIsPosted() {
return isPosted;
}

public void setIsPosted(Boolean  isPosted) {
this.isPosted = isPosted;
}

public Timestamp getPostedDate() {
return postedDate;
}

public void setPostedDate(Timestamp  postedDate) {
this.postedDate = postedDate;
}


public Map<String, Object> mapAttributeField() {
return GlJournalMapper.map(this);
}
}
