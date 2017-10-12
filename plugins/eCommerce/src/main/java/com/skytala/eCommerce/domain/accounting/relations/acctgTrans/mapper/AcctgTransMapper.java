package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.AcctgTrans;

public class AcctgTransMapper  {


	public static Map<String, Object> map(AcctgTrans acctgtrans) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(acctgtrans.getAcctgTransId() != null ){
			returnVal.put("acctgTransId",acctgtrans.getAcctgTransId());
}

		if(acctgtrans.getAcctgTransTypeId() != null ){
			returnVal.put("acctgTransTypeId",acctgtrans.getAcctgTransTypeId());
}

		if(acctgtrans.getDescription() != null ){
			returnVal.put("description",acctgtrans.getDescription());
}

		if(acctgtrans.getTransactionDate() != null ){
			returnVal.put("transactionDate",acctgtrans.getTransactionDate());
}

		if(acctgtrans.getIsPosted() != null ){
			returnVal.put("isPosted",acctgtrans.getIsPosted());
}

		if(acctgtrans.getPostedDate() != null ){
			returnVal.put("postedDate",acctgtrans.getPostedDate());
}

		if(acctgtrans.getScheduledPostingDate() != null ){
			returnVal.put("scheduledPostingDate",acctgtrans.getScheduledPostingDate());
}

		if(acctgtrans.getGlJournalId() != null ){
			returnVal.put("glJournalId",acctgtrans.getGlJournalId());
}

		if(acctgtrans.getGlFiscalTypeId() != null ){
			returnVal.put("glFiscalTypeId",acctgtrans.getGlFiscalTypeId());
}

		if(acctgtrans.getVoucherRef() != null ){
			returnVal.put("voucherRef",acctgtrans.getVoucherRef());
}

		if(acctgtrans.getVoucherDate() != null ){
			returnVal.put("voucherDate",acctgtrans.getVoucherDate());
}

		if(acctgtrans.getGroupStatusId() != null ){
			returnVal.put("groupStatusId",acctgtrans.getGroupStatusId());
}

		if(acctgtrans.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",acctgtrans.getFixedAssetId());
}

		if(acctgtrans.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",acctgtrans.getInventoryItemId());
}

		if(acctgtrans.getPhysicalInventoryId() != null ){
			returnVal.put("physicalInventoryId",acctgtrans.getPhysicalInventoryId());
}

		if(acctgtrans.getPartyId() != null ){
			returnVal.put("partyId",acctgtrans.getPartyId());
}

		if(acctgtrans.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",acctgtrans.getRoleTypeId());
}

		if(acctgtrans.getInvoiceId() != null ){
			returnVal.put("invoiceId",acctgtrans.getInvoiceId());
}

		if(acctgtrans.getPaymentId() != null ){
			returnVal.put("paymentId",acctgtrans.getPaymentId());
}

		if(acctgtrans.getFinAccountTransId() != null ){
			returnVal.put("finAccountTransId",acctgtrans.getFinAccountTransId());
}

		if(acctgtrans.getShipmentId() != null ){
			returnVal.put("shipmentId",acctgtrans.getShipmentId());
}

		if(acctgtrans.getReceiptId() != null ){
			returnVal.put("receiptId",acctgtrans.getReceiptId());
}

		if(acctgtrans.getWorkEffortId() != null ){
			returnVal.put("workEffortId",acctgtrans.getWorkEffortId());
}

		if(acctgtrans.getTheirAcctgTransId() != null ){
			returnVal.put("theirAcctgTransId",acctgtrans.getTheirAcctgTransId());
}

		if(acctgtrans.getCreatedDate() != null ){
			returnVal.put("createdDate",acctgtrans.getCreatedDate());
}

		if(acctgtrans.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",acctgtrans.getCreatedByUserLogin());
}

		if(acctgtrans.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",acctgtrans.getLastModifiedDate());
}

		if(acctgtrans.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",acctgtrans.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static AcctgTrans map(Map<String, Object> fields) {

		AcctgTrans returnVal = new AcctgTrans();

		if(fields.get("acctgTransId") != null) {
			returnVal.setAcctgTransId((String) fields.get("acctgTransId"));
}

		if(fields.get("acctgTransTypeId") != null) {
			returnVal.setAcctgTransTypeId((String) fields.get("acctgTransTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("transactionDate") != null) {
			returnVal.setTransactionDate((Timestamp) fields.get("transactionDate"));
}

		if(fields.get("isPosted") != null) {
			returnVal.setIsPosted((boolean) fields.get("isPosted"));
}

		if(fields.get("postedDate") != null) {
			returnVal.setPostedDate((Timestamp) fields.get("postedDate"));
}

		if(fields.get("scheduledPostingDate") != null) {
			returnVal.setScheduledPostingDate((Timestamp) fields.get("scheduledPostingDate"));
}

		if(fields.get("glJournalId") != null) {
			returnVal.setGlJournalId((String) fields.get("glJournalId"));
}

		if(fields.get("glFiscalTypeId") != null) {
			returnVal.setGlFiscalTypeId((String) fields.get("glFiscalTypeId"));
}

		if(fields.get("voucherRef") != null) {
			returnVal.setVoucherRef((String) fields.get("voucherRef"));
}

		if(fields.get("voucherDate") != null) {
			returnVal.setVoucherDate((Timestamp) fields.get("voucherDate"));
}

		if(fields.get("groupStatusId") != null) {
			returnVal.setGroupStatusId((String) fields.get("groupStatusId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("physicalInventoryId") != null) {
			returnVal.setPhysicalInventoryId((String) fields.get("physicalInventoryId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("finAccountTransId") != null) {
			returnVal.setFinAccountTransId((String) fields.get("finAccountTransId"));
}

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("receiptId") != null) {
			returnVal.setReceiptId((String) fields.get("receiptId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("theirAcctgTransId") != null) {
			returnVal.setTheirAcctgTransId((String) fields.get("theirAcctgTransId"));
}

		if(fields.get("createdDate") != null) {
			returnVal.setCreatedDate((Timestamp) fields.get("createdDate"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
			returnVal.setLastModifiedDate((Timestamp) fields.get("lastModifiedDate"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static AcctgTrans mapstrstr(Map<String, String> fields) throws Exception {

		AcctgTrans returnVal = new AcctgTrans();

		if(fields.get("acctgTransId") != null) {
			returnVal.setAcctgTransId((String) fields.get("acctgTransId"));
}

		if(fields.get("acctgTransTypeId") != null) {
			returnVal.setAcctgTransTypeId((String) fields.get("acctgTransTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("transactionDate") != null) {
String buf = fields.get("transactionDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setTransactionDate(ibuf);
}

		if(fields.get("isPosted") != null) {
String buf;
buf = fields.get("isPosted");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsPosted(ibuf);
}

		if(fields.get("postedDate") != null) {
String buf = fields.get("postedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPostedDate(ibuf);
}

		if(fields.get("scheduledPostingDate") != null) {
String buf = fields.get("scheduledPostingDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setScheduledPostingDate(ibuf);
}

		if(fields.get("glJournalId") != null) {
			returnVal.setGlJournalId((String) fields.get("glJournalId"));
}

		if(fields.get("glFiscalTypeId") != null) {
			returnVal.setGlFiscalTypeId((String) fields.get("glFiscalTypeId"));
}

		if(fields.get("voucherRef") != null) {
			returnVal.setVoucherRef((String) fields.get("voucherRef"));
}

		if(fields.get("voucherDate") != null) {
String buf = fields.get("voucherDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setVoucherDate(ibuf);
}

		if(fields.get("groupStatusId") != null) {
			returnVal.setGroupStatusId((String) fields.get("groupStatusId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("physicalInventoryId") != null) {
			returnVal.setPhysicalInventoryId((String) fields.get("physicalInventoryId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("finAccountTransId") != null) {
			returnVal.setFinAccountTransId((String) fields.get("finAccountTransId"));
}

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("receiptId") != null) {
			returnVal.setReceiptId((String) fields.get("receiptId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("theirAcctgTransId") != null) {
			returnVal.setTheirAcctgTransId((String) fields.get("theirAcctgTransId"));
}

		if(fields.get("createdDate") != null) {
String buf = fields.get("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
String buf = fields.get("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static AcctgTrans map(GenericValue val) {

AcctgTrans returnVal = new AcctgTrans();
		returnVal.setAcctgTransId(val.getString("acctgTransId"));
		returnVal.setAcctgTransTypeId(val.getString("acctgTransTypeId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setTransactionDate(val.getTimestamp("transactionDate"));
		returnVal.setIsPosted(val.getBoolean("isPosted"));
		returnVal.setPostedDate(val.getTimestamp("postedDate"));
		returnVal.setScheduledPostingDate(val.getTimestamp("scheduledPostingDate"));
		returnVal.setGlJournalId(val.getString("glJournalId"));
		returnVal.setGlFiscalTypeId(val.getString("glFiscalTypeId"));
		returnVal.setVoucherRef(val.getString("voucherRef"));
		returnVal.setVoucherDate(val.getTimestamp("voucherDate"));
		returnVal.setGroupStatusId(val.getString("groupStatusId"));
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setPhysicalInventoryId(val.getString("physicalInventoryId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setPaymentId(val.getString("paymentId"));
		returnVal.setFinAccountTransId(val.getString("finAccountTransId"));
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setReceiptId(val.getString("receiptId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setTheirAcctgTransId(val.getString("theirAcctgTransId"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static AcctgTrans map(HttpServletRequest request) throws Exception {

		AcctgTrans returnVal = new AcctgTrans();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("acctgTransId")) {
returnVal.setAcctgTransId(request.getParameter("acctgTransId"));
}

		if(paramMap.containsKey("acctgTransTypeId"))  {
returnVal.setAcctgTransTypeId(request.getParameter("acctgTransTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("transactionDate"))  {
String buf = request.getParameter("transactionDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setTransactionDate(ibuf);
}
		if(paramMap.containsKey("isPosted"))  {
String buf = request.getParameter("isPosted");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsPosted(ibuf);
}
		if(paramMap.containsKey("postedDate"))  {
String buf = request.getParameter("postedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setPostedDate(ibuf);
}
		if(paramMap.containsKey("scheduledPostingDate"))  {
String buf = request.getParameter("scheduledPostingDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setScheduledPostingDate(ibuf);
}
		if(paramMap.containsKey("glJournalId"))  {
returnVal.setGlJournalId(request.getParameter("glJournalId"));
}
		if(paramMap.containsKey("glFiscalTypeId"))  {
returnVal.setGlFiscalTypeId(request.getParameter("glFiscalTypeId"));
}
		if(paramMap.containsKey("voucherRef"))  {
returnVal.setVoucherRef(request.getParameter("voucherRef"));
}
		if(paramMap.containsKey("voucherDate"))  {
String buf = request.getParameter("voucherDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setVoucherDate(ibuf);
}
		if(paramMap.containsKey("groupStatusId"))  {
returnVal.setGroupStatusId(request.getParameter("groupStatusId"));
}
		if(paramMap.containsKey("fixedAssetId"))  {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}
		if(paramMap.containsKey("inventoryItemId"))  {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
}
		if(paramMap.containsKey("physicalInventoryId"))  {
returnVal.setPhysicalInventoryId(request.getParameter("physicalInventoryId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("invoiceId"))  {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}
		if(paramMap.containsKey("paymentId"))  {
returnVal.setPaymentId(request.getParameter("paymentId"));
}
		if(paramMap.containsKey("finAccountTransId"))  {
returnVal.setFinAccountTransId(request.getParameter("finAccountTransId"));
}
		if(paramMap.containsKey("shipmentId"))  {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}
		if(paramMap.containsKey("receiptId"))  {
returnVal.setReceiptId(request.getParameter("receiptId"));
}
		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
		if(paramMap.containsKey("theirAcctgTransId"))  {
returnVal.setTheirAcctgTransId(request.getParameter("theirAcctgTransId"));
}
		if(paramMap.containsKey("createdDate"))  {
String buf = request.getParameter("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCreatedDate(ibuf);
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedDate"))  {
String buf = request.getParameter("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastModifiedDate(ibuf);
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
return returnVal;

}
}
