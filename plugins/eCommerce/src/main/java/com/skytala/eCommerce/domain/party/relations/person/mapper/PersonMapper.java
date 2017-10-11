package com.skytala.eCommerce.domain.party.relations.person.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.person.model.Person;

public class PersonMapper  {


	public static Map<String, Object> map(Person person) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(person.getPartyId() != null ){
			returnVal.put("partyId",person.getPartyId());
}

		if(person.getSalutation() != null ){
			returnVal.put("salutation",person.getSalutation());
}

		if(person.getFirstName() != null ){
			returnVal.put("firstName",person.getFirstName());
}

		if(person.getMiddleName() != null ){
			returnVal.put("middleName",person.getMiddleName());
}

		if(person.getLastName() != null ){
			returnVal.put("lastName",person.getLastName());
}

		if(person.getPersonalTitle() != null ){
			returnVal.put("personalTitle",person.getPersonalTitle());
}

		if(person.getSuffix() != null ){
			returnVal.put("suffix",person.getSuffix());
}

		if(person.getNickname() != null ){
			returnVal.put("nickname",person.getNickname());
}

		if(person.getFirstNameLocal() != null ){
			returnVal.put("firstNameLocal",person.getFirstNameLocal());
}

		if(person.getMiddleNameLocal() != null ){
			returnVal.put("middleNameLocal",person.getMiddleNameLocal());
}

		if(person.getLastNameLocal() != null ){
			returnVal.put("lastNameLocal",person.getLastNameLocal());
}

		if(person.getOtherLocal() != null ){
			returnVal.put("otherLocal",person.getOtherLocal());
}

		if(person.getMemberId() != null ){
			returnVal.put("memberId",person.getMemberId());
}

		if(person.getGender() != null ){
			returnVal.put("gender",person.getGender());
}

		if(person.getBirthDate() != null ){
			returnVal.put("birthDate",person.getBirthDate());
}

		if(person.getDeceasedDate() != null ){
			returnVal.put("deceasedDate",person.getDeceasedDate());
}

		if(person.getHeight() != null ){
			returnVal.put("height",person.getHeight());
}

		if(person.getWeight() != null ){
			returnVal.put("weight",person.getWeight());
}

		if(person.getMothersMaidenName() != null ){
			returnVal.put("mothersMaidenName",person.getMothersMaidenName());
}

		if(person.getMaritalStatus() != null ){
			returnVal.put("maritalStatus",person.getMaritalStatus());
}

		if(person.getSocialSecurityNumber() != null ){
			returnVal.put("socialSecurityNumber",person.getSocialSecurityNumber());
}

		if(person.getPassportNumber() != null ){
			returnVal.put("passportNumber",person.getPassportNumber());
}

		if(person.getPassportExpireDate() != null ){
			returnVal.put("passportExpireDate",person.getPassportExpireDate());
}

		if(person.getTotalYearsWorkExperience() != null ){
			returnVal.put("totalYearsWorkExperience",person.getTotalYearsWorkExperience());
}

		if(person.getComments() != null ){
			returnVal.put("comments",person.getComments());
}

		if(person.getEmploymentStatusEnumId() != null ){
			returnVal.put("employmentStatusEnumId",person.getEmploymentStatusEnumId());
}

		if(person.getResidenceStatusEnumId() != null ){
			returnVal.put("residenceStatusEnumId",person.getResidenceStatusEnumId());
}

		if(person.getOccupation() != null ){
			returnVal.put("occupation",person.getOccupation());
}

		if(person.getYearsWithEmployer() != null ){
			returnVal.put("yearsWithEmployer",person.getYearsWithEmployer());
}

		if(person.getMonthsWithEmployer() != null ){
			returnVal.put("monthsWithEmployer",person.getMonthsWithEmployer());
}

		if(person.getExistingCustomer() != null ){
			returnVal.put("existingCustomer",person.getExistingCustomer());
}

		if(person.getCardId() != null ){
			returnVal.put("cardId",person.getCardId());
}

		return returnVal;
}


	public static Person map(Map<String, Object> fields) {

		Person returnVal = new Person();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("salutation") != null) {
			returnVal.setSalutation((String) fields.get("salutation"));
}

		if(fields.get("firstName") != null) {
			returnVal.setFirstName((String) fields.get("firstName"));
}

		if(fields.get("middleName") != null) {
			returnVal.setMiddleName((String) fields.get("middleName"));
}

		if(fields.get("lastName") != null) {
			returnVal.setLastName((String) fields.get("lastName"));
}

		if(fields.get("personalTitle") != null) {
			returnVal.setPersonalTitle((String) fields.get("personalTitle"));
}

		if(fields.get("suffix") != null) {
			returnVal.setSuffix((String) fields.get("suffix"));
}

		if(fields.get("nickname") != null) {
			returnVal.setNickname((String) fields.get("nickname"));
}

		if(fields.get("firstNameLocal") != null) {
			returnVal.setFirstNameLocal((String) fields.get("firstNameLocal"));
}

		if(fields.get("middleNameLocal") != null) {
			returnVal.setMiddleNameLocal((String) fields.get("middleNameLocal"));
}

		if(fields.get("lastNameLocal") != null) {
			returnVal.setLastNameLocal((String) fields.get("lastNameLocal"));
}

		if(fields.get("otherLocal") != null) {
			returnVal.setOtherLocal((String) fields.get("otherLocal"));
}

		if(fields.get("memberId") != null) {
			returnVal.setMemberId((String) fields.get("memberId"));
}

		if(fields.get("gender") != null) {
			returnVal.setGender((boolean) fields.get("gender"));
}

		if(fields.get("birthDate") != null) {
			returnVal.setBirthDate((Timestamp) fields.get("birthDate"));
}

		if(fields.get("deceasedDate") != null) {
			returnVal.setDeceasedDate((Timestamp) fields.get("deceasedDate"));
}

		if(fields.get("height") != null) {
			returnVal.setHeight((BigDecimal) fields.get("height"));
}

		if(fields.get("weight") != null) {
			returnVal.setWeight((BigDecimal) fields.get("weight"));
}

		if(fields.get("mothersMaidenName") != null) {
			returnVal.setMothersMaidenName((String) fields.get("mothersMaidenName"));
}

		if(fields.get("maritalStatus") != null) {
			returnVal.setMaritalStatus((boolean) fields.get("maritalStatus"));
}

		if(fields.get("socialSecurityNumber") != null) {
			returnVal.setSocialSecurityNumber((String) fields.get("socialSecurityNumber"));
}

		if(fields.get("passportNumber") != null) {
			returnVal.setPassportNumber((String) fields.get("passportNumber"));
}

		if(fields.get("passportExpireDate") != null) {
			returnVal.setPassportExpireDate((Timestamp) fields.get("passportExpireDate"));
}

		if(fields.get("totalYearsWorkExperience") != null) {
			returnVal.setTotalYearsWorkExperience((BigDecimal) fields.get("totalYearsWorkExperience"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("employmentStatusEnumId") != null) {
			returnVal.setEmploymentStatusEnumId((String) fields.get("employmentStatusEnumId"));
}

		if(fields.get("residenceStatusEnumId") != null) {
			returnVal.setResidenceStatusEnumId((String) fields.get("residenceStatusEnumId"));
}

		if(fields.get("occupation") != null) {
			returnVal.setOccupation((String) fields.get("occupation"));
}

		if(fields.get("yearsWithEmployer") != null) {
			returnVal.setYearsWithEmployer((long) fields.get("yearsWithEmployer"));
}

		if(fields.get("monthsWithEmployer") != null) {
			returnVal.setMonthsWithEmployer((long) fields.get("monthsWithEmployer"));
}

		if(fields.get("existingCustomer") != null) {
			returnVal.setExistingCustomer((boolean) fields.get("existingCustomer"));
}

		if(fields.get("cardId") != null) {
			returnVal.setCardId((String) fields.get("cardId"));
}


		return returnVal;
 } 
	public static Person mapstrstr(Map<String, String> fields) throws Exception {

		Person returnVal = new Person();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("salutation") != null) {
			returnVal.setSalutation((String) fields.get("salutation"));
}

		if(fields.get("firstName") != null) {
			returnVal.setFirstName((String) fields.get("firstName"));
}

		if(fields.get("middleName") != null) {
			returnVal.setMiddleName((String) fields.get("middleName"));
}

		if(fields.get("lastName") != null) {
			returnVal.setLastName((String) fields.get("lastName"));
}

		if(fields.get("personalTitle") != null) {
			returnVal.setPersonalTitle((String) fields.get("personalTitle"));
}

		if(fields.get("suffix") != null) {
			returnVal.setSuffix((String) fields.get("suffix"));
}

		if(fields.get("nickname") != null) {
			returnVal.setNickname((String) fields.get("nickname"));
}

		if(fields.get("firstNameLocal") != null) {
			returnVal.setFirstNameLocal((String) fields.get("firstNameLocal"));
}

		if(fields.get("middleNameLocal") != null) {
			returnVal.setMiddleNameLocal((String) fields.get("middleNameLocal"));
}

		if(fields.get("lastNameLocal") != null) {
			returnVal.setLastNameLocal((String) fields.get("lastNameLocal"));
}

		if(fields.get("otherLocal") != null) {
			returnVal.setOtherLocal((String) fields.get("otherLocal"));
}

		if(fields.get("memberId") != null) {
			returnVal.setMemberId((String) fields.get("memberId"));
}

		if(fields.get("gender") != null) {
String buf;
buf = fields.get("gender");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setGender(ibuf);
}

		if(fields.get("birthDate") != null) {
String buf = fields.get("birthDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setBirthDate(ibuf);
}

		if(fields.get("deceasedDate") != null) {
String buf = fields.get("deceasedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDeceasedDate(ibuf);
}

		if(fields.get("height") != null) {
String buf;
buf = fields.get("height");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setHeight(bd);
}

		if(fields.get("weight") != null) {
String buf;
buf = fields.get("weight");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setWeight(bd);
}

		if(fields.get("mothersMaidenName") != null) {
			returnVal.setMothersMaidenName((String) fields.get("mothersMaidenName"));
}

		if(fields.get("maritalStatus") != null) {
String buf;
buf = fields.get("maritalStatus");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setMaritalStatus(ibuf);
}

		if(fields.get("socialSecurityNumber") != null) {
			returnVal.setSocialSecurityNumber((String) fields.get("socialSecurityNumber"));
}

		if(fields.get("passportNumber") != null) {
			returnVal.setPassportNumber((String) fields.get("passportNumber"));
}

		if(fields.get("passportExpireDate") != null) {
String buf = fields.get("passportExpireDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPassportExpireDate(ibuf);
}

		if(fields.get("totalYearsWorkExperience") != null) {
String buf;
buf = fields.get("totalYearsWorkExperience");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalYearsWorkExperience(bd);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("employmentStatusEnumId") != null) {
			returnVal.setEmploymentStatusEnumId((String) fields.get("employmentStatusEnumId"));
}

		if(fields.get("residenceStatusEnumId") != null) {
			returnVal.setResidenceStatusEnumId((String) fields.get("residenceStatusEnumId"));
}

		if(fields.get("occupation") != null) {
			returnVal.setOccupation((String) fields.get("occupation"));
}

		if(fields.get("yearsWithEmployer") != null) {
String buf;
buf = fields.get("yearsWithEmployer");
long ibuf = Long.parseLong(buf);
			returnVal.setYearsWithEmployer(ibuf);
}

		if(fields.get("monthsWithEmployer") != null) {
String buf;
buf = fields.get("monthsWithEmployer");
long ibuf = Long.parseLong(buf);
			returnVal.setMonthsWithEmployer(ibuf);
}

		if(fields.get("existingCustomer") != null) {
String buf;
buf = fields.get("existingCustomer");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setExistingCustomer(ibuf);
}

		if(fields.get("cardId") != null) {
			returnVal.setCardId((String) fields.get("cardId"));
}


		return returnVal;
 } 
	public static Person map(GenericValue val) {

Person returnVal = new Person();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setSalutation(val.getString("salutation"));
		returnVal.setFirstName(val.getString("firstName"));
		returnVal.setMiddleName(val.getString("middleName"));
		returnVal.setLastName(val.getString("lastName"));
		returnVal.setPersonalTitle(val.getString("personalTitle"));
		returnVal.setSuffix(val.getString("suffix"));
		returnVal.setNickname(val.getString("nickname"));
		returnVal.setFirstNameLocal(val.getString("firstNameLocal"));
		returnVal.setMiddleNameLocal(val.getString("middleNameLocal"));
		returnVal.setLastNameLocal(val.getString("lastNameLocal"));
		returnVal.setOtherLocal(val.getString("otherLocal"));
		returnVal.setMemberId(val.getString("memberId"));
		returnVal.setGender(val.getBoolean("gender"));
		returnVal.setBirthDate(val.getTimestamp("birthDate"));
		returnVal.setDeceasedDate(val.getTimestamp("deceasedDate"));
		returnVal.setHeight(val.getBigDecimal("height"));
		returnVal.setWeight(val.getBigDecimal("weight"));
		returnVal.setMothersMaidenName(val.getString("mothersMaidenName"));
		returnVal.setMaritalStatus(val.getBoolean("maritalStatus"));
		returnVal.setSocialSecurityNumber(val.getString("socialSecurityNumber"));
		returnVal.setPassportNumber(val.getString("passportNumber"));
		returnVal.setPassportExpireDate(val.getTimestamp("passportExpireDate"));
		returnVal.setTotalYearsWorkExperience(val.getBigDecimal("totalYearsWorkExperience"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setEmploymentStatusEnumId(val.getString("employmentStatusEnumId"));
		returnVal.setResidenceStatusEnumId(val.getString("residenceStatusEnumId"));
		returnVal.setOccupation(val.getString("occupation"));
		returnVal.setYearsWithEmployer(val.getLong("yearsWithEmployer"));
		returnVal.setMonthsWithEmployer(val.getLong("monthsWithEmployer"));
		returnVal.setExistingCustomer(val.getBoolean("existingCustomer"));
		returnVal.setCardId(val.getString("cardId"));


return returnVal;

}

public static Person map(HttpServletRequest request) throws Exception {

		Person returnVal = new Person();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("salutation"))  {
returnVal.setSalutation(request.getParameter("salutation"));
}
		if(paramMap.containsKey("firstName"))  {
returnVal.setFirstName(request.getParameter("firstName"));
}
		if(paramMap.containsKey("middleName"))  {
returnVal.setMiddleName(request.getParameter("middleName"));
}
		if(paramMap.containsKey("lastName"))  {
returnVal.setLastName(request.getParameter("lastName"));
}
		if(paramMap.containsKey("personalTitle"))  {
returnVal.setPersonalTitle(request.getParameter("personalTitle"));
}
		if(paramMap.containsKey("suffix"))  {
returnVal.setSuffix(request.getParameter("suffix"));
}
		if(paramMap.containsKey("nickname"))  {
returnVal.setNickname(request.getParameter("nickname"));
}
		if(paramMap.containsKey("firstNameLocal"))  {
returnVal.setFirstNameLocal(request.getParameter("firstNameLocal"));
}
		if(paramMap.containsKey("middleNameLocal"))  {
returnVal.setMiddleNameLocal(request.getParameter("middleNameLocal"));
}
		if(paramMap.containsKey("lastNameLocal"))  {
returnVal.setLastNameLocal(request.getParameter("lastNameLocal"));
}
		if(paramMap.containsKey("otherLocal"))  {
returnVal.setOtherLocal(request.getParameter("otherLocal"));
}
		if(paramMap.containsKey("memberId"))  {
returnVal.setMemberId(request.getParameter("memberId"));
}
		if(paramMap.containsKey("gender"))  {
String buf = request.getParameter("gender");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setGender(ibuf);
}
		if(paramMap.containsKey("birthDate"))  {
String buf = request.getParameter("birthDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setBirthDate(ibuf);
}
		if(paramMap.containsKey("deceasedDate"))  {
String buf = request.getParameter("deceasedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDeceasedDate(ibuf);
}
		if(paramMap.containsKey("height"))  {
String buf = request.getParameter("height");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setHeight(bd);
}
		if(paramMap.containsKey("weight"))  {
String buf = request.getParameter("weight");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setWeight(bd);
}
		if(paramMap.containsKey("mothersMaidenName"))  {
returnVal.setMothersMaidenName(request.getParameter("mothersMaidenName"));
}
		if(paramMap.containsKey("maritalStatus"))  {
String buf = request.getParameter("maritalStatus");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setMaritalStatus(ibuf);
}
		if(paramMap.containsKey("socialSecurityNumber"))  {
returnVal.setSocialSecurityNumber(request.getParameter("socialSecurityNumber"));
}
		if(paramMap.containsKey("passportNumber"))  {
returnVal.setPassportNumber(request.getParameter("passportNumber"));
}
		if(paramMap.containsKey("passportExpireDate"))  {
String buf = request.getParameter("passportExpireDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setPassportExpireDate(ibuf);
}
		if(paramMap.containsKey("totalYearsWorkExperience"))  {
String buf = request.getParameter("totalYearsWorkExperience");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalYearsWorkExperience(bd);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("employmentStatusEnumId"))  {
returnVal.setEmploymentStatusEnumId(request.getParameter("employmentStatusEnumId"));
}
		if(paramMap.containsKey("residenceStatusEnumId"))  {
returnVal.setResidenceStatusEnumId(request.getParameter("residenceStatusEnumId"));
}
		if(paramMap.containsKey("occupation"))  {
returnVal.setOccupation(request.getParameter("occupation"));
}
		if(paramMap.containsKey("yearsWithEmployer"))  {
String buf = request.getParameter("yearsWithEmployer");
Long ibuf = Long.parseLong(buf);
returnVal.setYearsWithEmployer(ibuf);
}
		if(paramMap.containsKey("monthsWithEmployer"))  {
String buf = request.getParameter("monthsWithEmployer");
Long ibuf = Long.parseLong(buf);
returnVal.setMonthsWithEmployer(ibuf);
}
		if(paramMap.containsKey("existingCustomer"))  {
String buf = request.getParameter("existingCustomer");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setExistingCustomer(ibuf);
}
		if(paramMap.containsKey("cardId"))  {
returnVal.setCardId(request.getParameter("cardId"));
}
return returnVal;

}
}
