package com.skytala.eCommerce.domain.humanres.relations.partySkill.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.mapper.PartySkillMapper;

public class PartySkill implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String skillTypeId;
private Long yearsExperience;
private Long rating;
private Long skillLevel;
private Timestamp startedUsingDate;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getSkillTypeId() {
return skillTypeId;
}

public void setSkillTypeId(String  skillTypeId) {
this.skillTypeId = skillTypeId;
}

public Long getYearsExperience() {
return yearsExperience;
}

public void setYearsExperience(Long  yearsExperience) {
this.yearsExperience = yearsExperience;
}

public Long getRating() {
return rating;
}

public void setRating(Long  rating) {
this.rating = rating;
}

public Long getSkillLevel() {
return skillLevel;
}

public void setSkillLevel(Long  skillLevel) {
this.skillLevel = skillLevel;
}

public Timestamp getStartedUsingDate() {
return startedUsingDate;
}

public void setStartedUsingDate(Timestamp  startedUsingDate) {
this.startedUsingDate = startedUsingDate;
}


public Map<String, Object> mapAttributeField() {
return PartySkillMapper.map(this);
}
}
