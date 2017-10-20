package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.entry;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.entry.AcctgTransEntry;

public class AcctgTransEntryMapper  {


	public static Map<String, Object> map(AcctgTransEntry acctgtransentry) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(acctgtransentry.getAcctgTransId() != null ){
			returnVal.put("acctgTransId",acctgtransentry.getAcctgTransId());
}

		if(acctgtransentry.getAcctgTransEntrySeqId() != null ){
			returnVal.put("acctgTransEntrySeqId",acctgtransentry.getAcctgTransEntrySeqId());
}

		if(acctgtransentry.getAcctgTransEntryTypeId() != null ){
			returnVal.put("acctgTransEntryTypeId",acctgtransentry.getAcctgTransEntryTypeId());
}

		if(acctgtransentry.getDescription() != null ){
			returnVal.put("description",acctgtransentry.getDescription());
}

		if(acctgtransentry.getVoucherRef() != null ){
			returnVal.put("voucherRef",acctgtransentry.getVoucherRef());
}

		if(acctgtransentry.getPartyId() != null ){
			returnVal.put("partyId",acctgtransentry.getPartyId());
}

		if(acctgtransentry.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",acctgtransentry.getRoleTypeId());
}

		if(acctgtransentry.getTheirPartyId() != null ){
			returnVal.put("theirPartyId",acctgtransentry.getTheirPartyId());
}

		if(acctgtransentry.getProductId() != null ){
			returnVal.put("productId",acctgtransentry.getProductId());
}

		if(acctgtransentry.getTheirProductId() != null ){
			returnVal.put("theirProductId",acctgtransentry.getTheirProductId());
}

		if(acctgtransentry.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",acctgtransentry.getInventoryItemId());
}

		if(acctgtransentry.getGlAccountTypeId() != null ){
			returnVal.put("glAccountTypeId",acctgtransentry.getGlAccountTypeId());
}

		if(acctgtransentry.getGlAccountId() != null ){
			returnVal.put("glAccountId",acctgtransentry.getGlAccountId());
}

		if(acctgtransentry.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",acctgtransentry.getOrganizationPartyId());
}

		if(acctgtransentry.getAmount() != null ){
			returnVal.put("amount",acctgtransentry.getAmount());
}

		if(acctgtransentry.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",acctgtransentry.getCurrencyUomId());
}

		if(acctgtransentry.getOrigAmount() != null ){
			returnVal.put("origAmount",acctgtransentry.getOrigAmount());
}

		if(acctgtransentry.getOrigCurrencyUomId() != null ){
			returnVal.put("origCurrencyUomId",acctgtransentry.getOrigCurrencyUomId());
}

		if(acctgtransentry.getDebitCreditFlag() != null ){
			returnVal.put("debitCreditFlag",acctgtransentry.getDebitCreditFlag());
}

		if(acctgtransentry.getDueDate() != null ){
			returnVal.put("dueDate",acctgtransentry.getDueDate());
}

		if(acctgtransentry.getGroupId() != null ){
			returnVal.put("groupId",acctgtransentry.getGroupId());
}

		if(acctgtransentry.getTaxId() != null ){
			returnVal.put("taxId",acctgtransentry.getTaxId());
}

		if(acctgtransentry.getReconcileStatusId() != null ){
			returnVal.put("reconcileStatusId",acctgtransentry.getReconcileStatusId());
}

		if(acctgtransentry.getSettlementTermId() != null ){
			returnVal.put("settlementTermId",acctgtransentry.getSettlementTermId());
}

		if(acctgtransentry.getIsSummary() != null ){
			returnVal.put("isSummary",acctgtransentry.getIsSummary());
}

		return returnVal;
}


	public static AcctgTransEntry map(Map<String, Object> fields) {

		AcctgTransEntry returnVal = new AcctgTransEntry();

		if(fields.get("acctgTransId") != null) {
			returnVal.setAcctgTransId((String) fields.get("acctgTransId"));
}

		if(fields.get("acctgTransEntrySeqId") != null) {
			returnVal.setAcctgTransEntrySeqId((String) fields.get("acctgTransEntrySeqId"));
}

		if(fields.get("acctgTransEntryTypeId") != null) {
			returnVal.setAcctgTransEntryTypeId((String) fields.get("acctgTransEntryTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("voucherRef") != null) {
			returnVal.setVoucherRef((String) fields.get("voucherRef"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("theirPartyId") != null) {
			returnVal.setTheirPartyId((String) fields.get("theirPartyId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("theirProductId") != null) {
			returnVal.setTheirProductId((String) fields.get("theirProductId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("glAccountTypeId") != null) {
			returnVal.setGlAccountTypeId((String) fields.get("glAccountTypeId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("origAmount") != null) {
			returnVal.setOrigAmount((BigDecimal) fields.get("origAmount"));
}

		if(fields.get("origCurrencyUomId") != null) {
			returnVal.setOrigCurrencyUomId((String) fields.get("origCurrencyUomId"));
}

		if(fields.get("debitCreditFlag") != null) {
			returnVal.setDebitCreditFlag((boolean) fields.get("debitCreditFlag"));
}

		if(fields.get("dueDate") != null) {
			returnVal.setDueDate((Timestamp) fields.get("dueDate"));
}

		if(fields.get("groupId") != null) {
			returnVal.setGroupId((String) fields.get("groupId"));
}

		if(fields.get("taxId") != null) {
			returnVal.setTaxId((String) fields.get("taxId"));
}

		if(fields.get("reconcileStatusId") != null) {
			returnVal.setReconcileStatusId((String) fields.get("reconcileStatusId"));
}

		if(fields.get("settlementTermId") != null) {
			returnVal.setSettlementTermId((String) fields.get("settlementTermId"));
}

		if(fields.get("isSummary") != null) {
			returnVal.setIsSummary((boolean) fields.get("isSummary"));
}


		return returnVal;
 } 
	public static AcctgTransEntry mapstrstr(Map<String, String> fields) throws Exception {

		AcctgTransEntry returnVal = new AcctgTransEntry();

		if(fields.get("acctgTransId") != null) {
			returnVal.setAcctgTransId((String) fields.get("acctgTransId"));
}

		if(fields.get("acctgTransEntrySeqId") != null) {
			returnVal.setAcctgTransEntrySeqId((String) fields.get("acctgTransEntrySeqId"));
}

		if(fields.get("acctgTransEntryTypeId") != null) {
			returnVal.setAcctgTransEntryTypeId((String) fields.get("acctgTransEntryTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("voucherRef") != null) {
			returnVal.setVoucherRef((String) fields.get("voucherRef"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("theirPartyId") != null) {
			returnVal.setTheirPartyId((String) fields.get("theirPartyId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("theirProductId") != null) {
			returnVal.setTheirProductId((String) fields.get("theirProductId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("glAccountTypeId") != null) {
			returnVal.setGlAccountTypeId((String) fields.get("glAccountTypeId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("origAmount") != null) {
String buf;
buf = fields.get("origAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOrigAmount(bd);
}

		if(fields.get("origCurrencyUomId") != null) {
			returnVal.setOrigCurrencyUomId((String) fields.get("origCurrencyUomId"));
}

		if(fields.get("debitCreditFlag") != null) {
String buf;
buf = fields.get("debitCreditFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setDebitCreditFlag(ibuf);
}

		if(fields.get("dueDate") != null) {
String buf = fields.get("dueDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDueDate(ibuf);
}

		if(fields.get("groupId") != null) {
			returnVal.setGroupId((String) fields.get("groupId"));
}

		if(fields.get("taxId") != null) {
			returnVal.setTaxId((String) fields.get("taxId"));
}

		if(fields.get("reconcileStatusId") != null) {
			returnVal.setReconcileStatusId((String) fields.get("reconcileStatusId"));
}

		if(fields.get("settlementTermId") != null) {
			returnVal.setSettlementTermId((String) fields.get("settlementTermId"));
}

		if(fields.get("isSummary") != null) {
String buf;
buf = fields.get("isSummary");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsSummary(ibuf);
}


		return returnVal;
 } 
	public static AcctgTransEntry map(GenericValue val) {

AcctgTransEntry returnVal = new AcctgTransEntry();
		returnVal.setAcctgTransId(val.getString("acctgTransId"));
		returnVal.setAcctgTransEntrySeqId(val.getString("acctgTransEntrySeqId"));
		returnVal.setAcctgTransEntryTypeId(val.getString("acctgTransEntryTypeId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setVoucherRef(val.getString("voucherRef"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setTheirPartyId(val.getString("theirPartyId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setTheirProductId(val.getString("theirProductId"));
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setGlAccountTypeId(val.getString("glAccountTypeId"));
		returnVal.setGlAccountId(val.getString("glAccountId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setOrigAmount(val.getBigDecimal("origAmount"));
		returnVal.setOrigCurrencyUomId(val.getString("origCurrencyUomId"));
		returnVal.setDebitCreditFlag(val.getBoolean("debitCreditFlag"));
		returnVal.setDueDate(val.getTimestamp("dueDate"));
		returnVal.setGroupId(val.getString("groupId"));
		returnVal.setTaxId(val.getString("taxId"));
		returnVal.setReconcileStatusId(val.getString("reconcileStatusId"));
		returnVal.setSettlementTermId(val.getString("settlementTermId"));
		returnVal.setIsSummary(val.getBoolean("isSummary"));


return returnVal;

}

public static AcctgTransEntry map(HttpServletRequest request) throws Exception {

		AcctgTransEntry returnVal = new AcctgTransEntry();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("acctgTransId")) {
returnVal.setAcctgTransId(request.getParameter("acctgTransId"));
}

		if(paramMap.containsKey("acctgTransEntrySeqId"))  {
returnVal.setAcctgTransEntrySeqId(request.getParameter("acctgTransEntrySeqId"));
}
		if(paramMap.containsKey("acctgTransEntryTypeId"))  {
returnVal.setAcctgTransEntryTypeId(request.getParameter("acctgTransEntryTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("voucherRef"))  {
returnVal.setVoucherRef(request.getParameter("voucherRef"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("theirPartyId"))  {
returnVal.setTheirPartyId(request.getParameter("theirPartyId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("theirProductId"))  {
returnVal.setTheirProductId(request.getParameter("theirProductId"));
}
		if(paramMap.containsKey("inventoryItemId"))  {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
}
		if(paramMap.containsKey("glAccountTypeId"))  {
returnVal.setGlAccountTypeId(request.getParameter("glAccountTypeId"));
}
		if(paramMap.containsKey("glAccountId"))  {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}
		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("origAmount"))  {
String buf = request.getParameter("origAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOrigAmount(bd);
}
		if(paramMap.containsKey("origCurrencyUomId"))  {
returnVal.setOrigCurrencyUomId(request.getParameter("origCurrencyUomId"));
}
		if(paramMap.containsKey("debitCreditFlag"))  {
String buf = request.getParameter("debitCreditFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setDebitCreditFlag(ibuf);
}
		if(paramMap.containsKey("dueDate"))  {
String buf = request.getParameter("dueDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDueDate(ibuf);
}
		if(paramMap.containsKey("groupId"))  {
returnVal.setGroupId(request.getParameter("groupId"));
}
		if(paramMap.containsKey("taxId"))  {
returnVal.setTaxId(request.getParameter("taxId"));
}
		if(paramMap.containsKey("reconcileStatusId"))  {
returnVal.setReconcileStatusId(request.getParameter("reconcileStatusId"));
}
		if(paramMap.containsKey("settlementTermId"))  {
returnVal.setSettlementTermId(request.getParameter("settlementTermId"));
}
		if(paramMap.containsKey("isSummary"))  {
String buf = request.getParameter("isSummary");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsSummary(ibuf);
}
return returnVal;

}
}
