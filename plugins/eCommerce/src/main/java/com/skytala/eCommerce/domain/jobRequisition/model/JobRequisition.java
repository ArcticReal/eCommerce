package com.skytala.eCommerce.domain.jobRequisition.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.jobRequisition.mapper.JobRequisitionMapper;

public class JobRequisition implements Serializable{

private static final long serialVersionUID = 1L;
private String jobRequisitionId;
private Long durationMonths;
private Long age;
private Boolean gender;
private Long experienceMonths;
private Long experienceYears;
private String qualification;
private String jobLocation;
private String skillTypeId;
private Long noOfResources;
private String jobPostingTypeEnumId;
private Timestamp jobRequisitionDate;
private String examTypeEnumId;
private Timestamp requiredOnDate;

public String getJobRequisitionId() {
return jobRequisitionId;
}

public void setJobRequisitionId(String  jobRequisitionId) {
this.jobRequisitionId = jobRequisitionId;
}

public Long getDurationMonths() {
return durationMonths;
}

public void setDurationMonths(Long  durationMonths) {
this.durationMonths = durationMonths;
}

public Long getAge() {
return age;
}

public void setAge(Long  age) {
this.age = age;
}

public Boolean getGender() {
return gender;
}

public void setGender(Boolean  gender) {
this.gender = gender;
}

public Long getExperienceMonths() {
return experienceMonths;
}

public void setExperienceMonths(Long  experienceMonths) {
this.experienceMonths = experienceMonths;
}

public Long getExperienceYears() {
return experienceYears;
}

public void setExperienceYears(Long  experienceYears) {
this.experienceYears = experienceYears;
}

public String getQualification() {
return qualification;
}

public void setQualification(String  qualification) {
this.qualification = qualification;
}

public String getJobLocation() {
return jobLocation;
}

public void setJobLocation(String  jobLocation) {
this.jobLocation = jobLocation;
}

public String getSkillTypeId() {
return skillTypeId;
}

public void setSkillTypeId(String  skillTypeId) {
this.skillTypeId = skillTypeId;
}

public Long getNoOfResources() {
return noOfResources;
}

public void setNoOfResources(Long  noOfResources) {
this.noOfResources = noOfResources;
}

public String getJobPostingTypeEnumId() {
return jobPostingTypeEnumId;
}

public void setJobPostingTypeEnumId(String  jobPostingTypeEnumId) {
this.jobPostingTypeEnumId = jobPostingTypeEnumId;
}

public Timestamp getJobRequisitionDate() {
return jobRequisitionDate;
}

public void setJobRequisitionDate(Timestamp  jobRequisitionDate) {
this.jobRequisitionDate = jobRequisitionDate;
}

public String getExamTypeEnumId() {
return examTypeEnumId;
}

public void setExamTypeEnumId(String  examTypeEnumId) {
this.examTypeEnumId = examTypeEnumId;
}

public Timestamp getRequiredOnDate() {
return requiredOnDate;
}

public void setRequiredOnDate(Timestamp  requiredOnDate) {
this.requiredOnDate = requiredOnDate;
}


public Map<String, Object> mapAttributeField() {
return JobRequisitionMapper.map(this);
}
}
