package com.skytala.eCommerce.domain.humanres.relations.partyResume.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.mapper.PartyResumeMapper;

public class PartyResume implements Serializable{

private static final long serialVersionUID = 1L;
private String resumeId;
private String partyId;
private String contentId;
private Timestamp resumeDate;
private String resumeText;

public String getResumeId() {
return resumeId;
}

public void setResumeId(String  resumeId) {
this.resumeId = resumeId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public Timestamp getResumeDate() {
return resumeDate;
}

public void setResumeDate(Timestamp  resumeDate) {
this.resumeDate = resumeDate;
}

public String getResumeText() {
return resumeText;
}

public void setResumeText(String  resumeText) {
this.resumeText = resumeText;
}


public Map<String, Object> mapAttributeField() {
return PartyResumeMapper.map(this);
}
}
