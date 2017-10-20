package com.skytala.eCommerce.domain.accounting.relations.billingAccount.mapper.role;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.role.BillingAccountRole;

public class BillingAccountRoleMapper  {


	public static Map<String, Object> map(BillingAccountRole billingaccountrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(billingaccountrole.getBillingAccountId() != null ){
			returnVal.put("billingAccountId",billingaccountrole.getBillingAccountId());
}

		if(billingaccountrole.getPartyId() != null ){
			returnVal.put("partyId",billingaccountrole.getPartyId());
}

		if(billingaccountrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",billingaccountrole.getRoleTypeId());
}

		if(billingaccountrole.getFromDate() != null ){
			returnVal.put("fromDate",billingaccountrole.getFromDate());
}

		if(billingaccountrole.getThruDate() != null ){
			returnVal.put("thruDate",billingaccountrole.getThruDate());
}

		return returnVal;
}


	public static BillingAccountRole map(Map<String, Object> fields) {

		BillingAccountRole returnVal = new BillingAccountRole();

		if(fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static BillingAccountRole mapstrstr(Map<String, String> fields) throws Exception {

		BillingAccountRole returnVal = new BillingAccountRole();

		if(fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
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
	public static BillingAccountRole map(GenericValue val) {

BillingAccountRole returnVal = new BillingAccountRole();
		returnVal.setBillingAccountId(val.getString("billingAccountId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static BillingAccountRole map(HttpServletRequest request) throws Exception {

		BillingAccountRole returnVal = new BillingAccountRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("billingAccountId")) {
returnVal.setBillingAccountId(request.getParameter("billingAccountId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
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
