package com.skytala.eCommerce.domain.unemploymentClaim.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.unemploymentClaim.model.UnemploymentClaim;

public class UnemploymentClaimMapper  {


	public static Map<String, Object> map(UnemploymentClaim unemploymentclaim) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(unemploymentclaim.getUnemploymentClaimId() != null ){
			returnVal.put("unemploymentClaimId",unemploymentclaim.getUnemploymentClaimId());
}

		if(unemploymentclaim.getUnemploymentClaimDate() != null ){
			returnVal.put("unemploymentClaimDate",unemploymentclaim.getUnemploymentClaimDate());
}

		if(unemploymentclaim.getDescription() != null ){
			returnVal.put("description",unemploymentclaim.getDescription());
}

		if(unemploymentclaim.getStatusId() != null ){
			returnVal.put("statusId",unemploymentclaim.getStatusId());
}

		if(unemploymentclaim.getPartyIdFrom() != null ){
			returnVal.put("partyIdFrom",unemploymentclaim.getPartyIdFrom());
}

		if(unemploymentclaim.getPartyIdTo() != null ){
			returnVal.put("partyIdTo",unemploymentclaim.getPartyIdTo());
}

		if(unemploymentclaim.getRoleTypeIdFrom() != null ){
			returnVal.put("roleTypeIdFrom",unemploymentclaim.getRoleTypeIdFrom());
}

		if(unemploymentclaim.getRoleTypeIdTo() != null ){
			returnVal.put("roleTypeIdTo",unemploymentclaim.getRoleTypeIdTo());
}

		if(unemploymentclaim.getFromDate() != null ){
			returnVal.put("fromDate",unemploymentclaim.getFromDate());
}

		if(unemploymentclaim.getThruDate() != null ){
			returnVal.put("thruDate",unemploymentclaim.getThruDate());
}

		return returnVal;
}


	public static UnemploymentClaim map(Map<String, Object> fields) {

		UnemploymentClaim returnVal = new UnemploymentClaim();

		if(fields.get("unemploymentClaimId") != null) {
			returnVal.setUnemploymentClaimId((String) fields.get("unemploymentClaimId"));
}

		if(fields.get("unemploymentClaimDate") != null) {
			returnVal.setUnemploymentClaimDate((Timestamp) fields.get("unemploymentClaimDate"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}

		if(fields.get("roleTypeIdFrom") != null) {
			returnVal.setRoleTypeIdFrom((String) fields.get("roleTypeIdFrom"));
}

		if(fields.get("roleTypeIdTo") != null) {
			returnVal.setRoleTypeIdTo((String) fields.get("roleTypeIdTo"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static UnemploymentClaim mapstrstr(Map<String, String> fields) throws Exception {

		UnemploymentClaim returnVal = new UnemploymentClaim();

		if(fields.get("unemploymentClaimId") != null) {
			returnVal.setUnemploymentClaimId((String) fields.get("unemploymentClaimId"));
}

		if(fields.get("unemploymentClaimDate") != null) {
String buf = fields.get("unemploymentClaimDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setUnemploymentClaimDate(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}

		if(fields.get("roleTypeIdFrom") != null) {
			returnVal.setRoleTypeIdFrom((String) fields.get("roleTypeIdFrom"));
}

		if(fields.get("roleTypeIdTo") != null) {
			returnVal.setRoleTypeIdTo((String) fields.get("roleTypeIdTo"));
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


		return returnVal;
 } 
	public static UnemploymentClaim map(GenericValue val) {

UnemploymentClaim returnVal = new UnemploymentClaim();
		returnVal.setUnemploymentClaimId(val.getString("unemploymentClaimId"));
		returnVal.setUnemploymentClaimDate(val.getTimestamp("unemploymentClaimDate"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setPartyIdFrom(val.getString("partyIdFrom"));
		returnVal.setPartyIdTo(val.getString("partyIdTo"));
		returnVal.setRoleTypeIdFrom(val.getString("roleTypeIdFrom"));
		returnVal.setRoleTypeIdTo(val.getString("roleTypeIdTo"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static UnemploymentClaim map(HttpServletRequest request) throws Exception {

		UnemploymentClaim returnVal = new UnemploymentClaim();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("unemploymentClaimId")) {
returnVal.setUnemploymentClaimId(request.getParameter("unemploymentClaimId"));
}

		if(paramMap.containsKey("unemploymentClaimDate"))  {
String buf = request.getParameter("unemploymentClaimDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setUnemploymentClaimDate(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("partyIdFrom"))  {
returnVal.setPartyIdFrom(request.getParameter("partyIdFrom"));
}
		if(paramMap.containsKey("partyIdTo"))  {
returnVal.setPartyIdTo(request.getParameter("partyIdTo"));
}
		if(paramMap.containsKey("roleTypeIdFrom"))  {
returnVal.setRoleTypeIdFrom(request.getParameter("roleTypeIdFrom"));
}
		if(paramMap.containsKey("roleTypeIdTo"))  {
returnVal.setRoleTypeIdTo(request.getParameter("roleTypeIdTo"));
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
return returnVal;

}
}
