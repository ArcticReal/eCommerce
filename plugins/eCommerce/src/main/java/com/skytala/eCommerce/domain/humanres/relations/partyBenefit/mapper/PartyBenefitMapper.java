package com.skytala.eCommerce.domain.humanres.relations.partyBenefit.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.model.PartyBenefit;

public class PartyBenefitMapper  {


	public static Map<String, Object> map(PartyBenefit partybenefit) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partybenefit.getRoleTypeIdFrom() != null ){
			returnVal.put("roleTypeIdFrom",partybenefit.getRoleTypeIdFrom());
}

		if(partybenefit.getRoleTypeIdTo() != null ){
			returnVal.put("roleTypeIdTo",partybenefit.getRoleTypeIdTo());
}

		if(partybenefit.getPartyIdFrom() != null ){
			returnVal.put("partyIdFrom",partybenefit.getPartyIdFrom());
}

		if(partybenefit.getPartyIdTo() != null ){
			returnVal.put("partyIdTo",partybenefit.getPartyIdTo());
}

		if(partybenefit.getBenefitTypeId() != null ){
			returnVal.put("benefitTypeId",partybenefit.getBenefitTypeId());
}

		if(partybenefit.getFromDate() != null ){
			returnVal.put("fromDate",partybenefit.getFromDate());
}

		if(partybenefit.getThruDate() != null ){
			returnVal.put("thruDate",partybenefit.getThruDate());
}

		if(partybenefit.getPeriodTypeId() != null ){
			returnVal.put("periodTypeId",partybenefit.getPeriodTypeId());
}

		if(partybenefit.getCost() != null ){
			returnVal.put("cost",partybenefit.getCost());
}

		if(partybenefit.getActualEmployerPaidPercent() != null ){
			returnVal.put("actualEmployerPaidPercent",partybenefit.getActualEmployerPaidPercent());
}

		if(partybenefit.getAvailableTime() != null ){
			returnVal.put("availableTime",partybenefit.getAvailableTime());
}

		return returnVal;
}


	public static PartyBenefit map(Map<String, Object> fields) {

		PartyBenefit returnVal = new PartyBenefit();

		if(fields.get("roleTypeIdFrom") != null) {
			returnVal.setRoleTypeIdFrom((String) fields.get("roleTypeIdFrom"));
}

		if(fields.get("roleTypeIdTo") != null) {
			returnVal.setRoleTypeIdTo((String) fields.get("roleTypeIdTo"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}

		if(fields.get("benefitTypeId") != null) {
			returnVal.setBenefitTypeId((String) fields.get("benefitTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("periodTypeId") != null) {
			returnVal.setPeriodTypeId((String) fields.get("periodTypeId"));
}

		if(fields.get("cost") != null) {
			returnVal.setCost((BigDecimal) fields.get("cost"));
}

		if(fields.get("actualEmployerPaidPercent") != null) {
			returnVal.setActualEmployerPaidPercent((BigDecimal) fields.get("actualEmployerPaidPercent"));
}

		if(fields.get("availableTime") != null) {
			returnVal.setAvailableTime((long) fields.get("availableTime"));
}


		return returnVal;
 } 
	public static PartyBenefit mapstrstr(Map<String, String> fields) throws Exception {

		PartyBenefit returnVal = new PartyBenefit();

		if(fields.get("roleTypeIdFrom") != null) {
			returnVal.setRoleTypeIdFrom((String) fields.get("roleTypeIdFrom"));
}

		if(fields.get("roleTypeIdTo") != null) {
			returnVal.setRoleTypeIdTo((String) fields.get("roleTypeIdTo"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}

		if(fields.get("benefitTypeId") != null) {
			returnVal.setBenefitTypeId((String) fields.get("benefitTypeId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("periodTypeId") != null) {
			returnVal.setPeriodTypeId((String) fields.get("periodTypeId"));
}

		if(fields.get("cost") != null) {
String buf;
buf = fields.get("cost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCost(bd);
}

		if(fields.get("actualEmployerPaidPercent") != null) {
String buf;
buf = fields.get("actualEmployerPaidPercent");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualEmployerPaidPercent(bd);
}

		if(fields.get("availableTime") != null) {
String buf;
buf = fields.get("availableTime");
long ibuf = Long.parseLong(buf);
			returnVal.setAvailableTime(ibuf);
}


		return returnVal;
 } 
	public static PartyBenefit map(GenericValue val) {

PartyBenefit returnVal = new PartyBenefit();
		returnVal.setRoleTypeIdFrom(val.getString("roleTypeIdFrom"));
		returnVal.setRoleTypeIdTo(val.getString("roleTypeIdTo"));
		returnVal.setPartyIdFrom(val.getString("partyIdFrom"));
		returnVal.setPartyIdTo(val.getString("partyIdTo"));
		returnVal.setBenefitTypeId(val.getString("benefitTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setPeriodTypeId(val.getString("periodTypeId"));
		returnVal.setCost(val.getBigDecimal("cost"));
		returnVal.setActualEmployerPaidPercent(val.getBigDecimal("actualEmployerPaidPercent"));
		returnVal.setAvailableTime(val.getLong("availableTime"));


return returnVal;

}

public static PartyBenefit map(HttpServletRequest request) throws Exception {

		PartyBenefit returnVal = new PartyBenefit();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("roleTypeIdFrom")) {
returnVal.setRoleTypeIdFrom(request.getParameter("roleTypeIdFrom"));
}

		if(paramMap.containsKey("roleTypeIdTo"))  {
returnVal.setRoleTypeIdTo(request.getParameter("roleTypeIdTo"));
}
		if(paramMap.containsKey("partyIdFrom"))  {
returnVal.setPartyIdFrom(request.getParameter("partyIdFrom"));
}
		if(paramMap.containsKey("partyIdTo"))  {
returnVal.setPartyIdTo(request.getParameter("partyIdTo"));
}
		if(paramMap.containsKey("benefitTypeId"))  {
returnVal.setBenefitTypeId(request.getParameter("benefitTypeId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("periodTypeId"))  {
returnVal.setPeriodTypeId(request.getParameter("periodTypeId"));
}
		if(paramMap.containsKey("cost"))  {
String buf = request.getParameter("cost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCost(bd);
}
		if(paramMap.containsKey("actualEmployerPaidPercent"))  {
String buf = request.getParameter("actualEmployerPaidPercent");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualEmployerPaidPercent(bd);
}
		if(paramMap.containsKey("availableTime"))  {
String buf = request.getParameter("availableTime");
Long ibuf = Long.parseLong(buf);
returnVal.setAvailableTime(ibuf);
}
return returnVal;

}
}
