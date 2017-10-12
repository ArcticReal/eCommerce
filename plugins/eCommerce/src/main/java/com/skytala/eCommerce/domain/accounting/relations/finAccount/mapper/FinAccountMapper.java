package com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.FinAccount;

public class FinAccountMapper  {


	public static Map<String, Object> map(FinAccount finaccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(finaccount.getFinAccountId() != null ){
			returnVal.put("finAccountId",finaccount.getFinAccountId());
}

		if(finaccount.getFinAccountTypeId() != null ){
			returnVal.put("finAccountTypeId",finaccount.getFinAccountTypeId());
}

		if(finaccount.getStatusId() != null ){
			returnVal.put("statusId",finaccount.getStatusId());
}

		if(finaccount.getFinAccountName() != null ){
			returnVal.put("finAccountName",finaccount.getFinAccountName());
}

		if(finaccount.getFinAccountCode() != null ){
			returnVal.put("finAccountCode",finaccount.getFinAccountCode());
}

		if(finaccount.getFinAccountPin() != null ){
			returnVal.put("finAccountPin",finaccount.getFinAccountPin());
}

		if(finaccount.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",finaccount.getCurrencyUomId());
}

		if(finaccount.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",finaccount.getOrganizationPartyId());
}

		if(finaccount.getOwnerPartyId() != null ){
			returnVal.put("ownerPartyId",finaccount.getOwnerPartyId());
}

		if(finaccount.getPostToGlAccountId() != null ){
			returnVal.put("postToGlAccountId",finaccount.getPostToGlAccountId());
}

		if(finaccount.getFromDate() != null ){
			returnVal.put("fromDate",finaccount.getFromDate());
}

		if(finaccount.getThruDate() != null ){
			returnVal.put("thruDate",finaccount.getThruDate());
}

		if(finaccount.getIsRefundable() != null ){
			returnVal.put("isRefundable",finaccount.getIsRefundable());
}

		if(finaccount.getReplenishPaymentId() != null ){
			returnVal.put("replenishPaymentId",finaccount.getReplenishPaymentId());
}

		if(finaccount.getReplenishLevel() != null ){
			returnVal.put("replenishLevel",finaccount.getReplenishLevel());
}

		if(finaccount.getActualBalance() != null ){
			returnVal.put("actualBalance",finaccount.getActualBalance());
}

		if(finaccount.getAvailableBalance() != null ){
			returnVal.put("availableBalance",finaccount.getAvailableBalance());
}

		return returnVal;
}


	public static FinAccount map(Map<String, Object> fields) {

		FinAccount returnVal = new FinAccount();

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
}

		if(fields.get("finAccountTypeId") != null) {
			returnVal.setFinAccountTypeId((String) fields.get("finAccountTypeId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("finAccountName") != null) {
			returnVal.setFinAccountName((String) fields.get("finAccountName"));
}

		if(fields.get("finAccountCode") != null) {
			returnVal.setFinAccountCode((String) fields.get("finAccountCode"));
}

		if(fields.get("finAccountPin") != null) {
			returnVal.setFinAccountPin((String) fields.get("finAccountPin"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("ownerPartyId") != null) {
			returnVal.setOwnerPartyId((String) fields.get("ownerPartyId"));
}

		if(fields.get("postToGlAccountId") != null) {
			returnVal.setPostToGlAccountId((String) fields.get("postToGlAccountId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("isRefundable") != null) {
			returnVal.setIsRefundable((boolean) fields.get("isRefundable"));
}

		if(fields.get("replenishPaymentId") != null) {
			returnVal.setReplenishPaymentId((String) fields.get("replenishPaymentId"));
}

		if(fields.get("replenishLevel") != null) {
			returnVal.setReplenishLevel((BigDecimal) fields.get("replenishLevel"));
}

		if(fields.get("actualBalance") != null) {
			returnVal.setActualBalance((BigDecimal) fields.get("actualBalance"));
}

		if(fields.get("availableBalance") != null) {
			returnVal.setAvailableBalance((BigDecimal) fields.get("availableBalance"));
}


		return returnVal;
 } 
	public static FinAccount mapstrstr(Map<String, String> fields) throws Exception {

		FinAccount returnVal = new FinAccount();

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
}

		if(fields.get("finAccountTypeId") != null) {
			returnVal.setFinAccountTypeId((String) fields.get("finAccountTypeId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("finAccountName") != null) {
			returnVal.setFinAccountName((String) fields.get("finAccountName"));
}

		if(fields.get("finAccountCode") != null) {
			returnVal.setFinAccountCode((String) fields.get("finAccountCode"));
}

		if(fields.get("finAccountPin") != null) {
			returnVal.setFinAccountPin((String) fields.get("finAccountPin"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("ownerPartyId") != null) {
			returnVal.setOwnerPartyId((String) fields.get("ownerPartyId"));
}

		if(fields.get("postToGlAccountId") != null) {
			returnVal.setPostToGlAccountId((String) fields.get("postToGlAccountId"));
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

		if(fields.get("isRefundable") != null) {
String buf;
buf = fields.get("isRefundable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsRefundable(ibuf);
}

		if(fields.get("replenishPaymentId") != null) {
			returnVal.setReplenishPaymentId((String) fields.get("replenishPaymentId"));
}

		if(fields.get("replenishLevel") != null) {
String buf;
buf = fields.get("replenishLevel");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReplenishLevel(bd);
}

		if(fields.get("actualBalance") != null) {
String buf;
buf = fields.get("actualBalance");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualBalance(bd);
}

		if(fields.get("availableBalance") != null) {
String buf;
buf = fields.get("availableBalance");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAvailableBalance(bd);
}


		return returnVal;
 } 
	public static FinAccount map(GenericValue val) {

FinAccount returnVal = new FinAccount();
		returnVal.setFinAccountId(val.getString("finAccountId"));
		returnVal.setFinAccountTypeId(val.getString("finAccountTypeId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setFinAccountName(val.getString("finAccountName"));
		returnVal.setFinAccountCode(val.getString("finAccountCode"));
		returnVal.setFinAccountPin(val.getString("finAccountPin"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setOwnerPartyId(val.getString("ownerPartyId"));
		returnVal.setPostToGlAccountId(val.getString("postToGlAccountId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setIsRefundable(val.getBoolean("isRefundable"));
		returnVal.setReplenishPaymentId(val.getString("replenishPaymentId"));
		returnVal.setReplenishLevel(val.getBigDecimal("replenishLevel"));
		returnVal.setActualBalance(val.getBigDecimal("actualBalance"));
		returnVal.setAvailableBalance(val.getBigDecimal("availableBalance"));


return returnVal;

}

public static FinAccount map(HttpServletRequest request) throws Exception {

		FinAccount returnVal = new FinAccount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("finAccountId")) {
returnVal.setFinAccountId(request.getParameter("finAccountId"));
}

		if(paramMap.containsKey("finAccountTypeId"))  {
returnVal.setFinAccountTypeId(request.getParameter("finAccountTypeId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("finAccountName"))  {
returnVal.setFinAccountName(request.getParameter("finAccountName"));
}
		if(paramMap.containsKey("finAccountCode"))  {
returnVal.setFinAccountCode(request.getParameter("finAccountCode"));
}
		if(paramMap.containsKey("finAccountPin"))  {
returnVal.setFinAccountPin(request.getParameter("finAccountPin"));
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}
		if(paramMap.containsKey("ownerPartyId"))  {
returnVal.setOwnerPartyId(request.getParameter("ownerPartyId"));
}
		if(paramMap.containsKey("postToGlAccountId"))  {
returnVal.setPostToGlAccountId(request.getParameter("postToGlAccountId"));
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
		if(paramMap.containsKey("isRefundable"))  {
String buf = request.getParameter("isRefundable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsRefundable(ibuf);
}
		if(paramMap.containsKey("replenishPaymentId"))  {
returnVal.setReplenishPaymentId(request.getParameter("replenishPaymentId"));
}
		if(paramMap.containsKey("replenishLevel"))  {
String buf = request.getParameter("replenishLevel");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReplenishLevel(bd);
}
		if(paramMap.containsKey("actualBalance"))  {
String buf = request.getParameter("actualBalance");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualBalance(bd);
}
		if(paramMap.containsKey("availableBalance"))  {
String buf = request.getParameter("availableBalance");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAvailableBalance(bd);
}
return returnVal;

}
}
