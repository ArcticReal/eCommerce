package com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.model.GlReconciliationEntry;

public class GlReconciliationEntryMapper  {


	public static Map<String, Object> map(GlReconciliationEntry glreconciliationentry) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glreconciliationentry.getGlReconciliationId() != null ){
			returnVal.put("glReconciliationId",glreconciliationentry.getGlReconciliationId());
}

		if(glreconciliationentry.getAcctgTransId() != null ){
			returnVal.put("acctgTransId",glreconciliationentry.getAcctgTransId());
}

		if(glreconciliationentry.getAcctgTransEntrySeqId() != null ){
			returnVal.put("acctgTransEntrySeqId",glreconciliationentry.getAcctgTransEntrySeqId());
}

		if(glreconciliationentry.getReconciledAmount() != null ){
			returnVal.put("reconciledAmount",glreconciliationentry.getReconciledAmount());
}

		return returnVal;
}


	public static GlReconciliationEntry map(Map<String, Object> fields) {

		GlReconciliationEntry returnVal = new GlReconciliationEntry();

		if(fields.get("glReconciliationId") != null) {
			returnVal.setGlReconciliationId((String) fields.get("glReconciliationId"));
}

		if(fields.get("acctgTransId") != null) {
			returnVal.setAcctgTransId((String) fields.get("acctgTransId"));
}

		if(fields.get("acctgTransEntrySeqId") != null) {
			returnVal.setAcctgTransEntrySeqId((String) fields.get("acctgTransEntrySeqId"));
}

		if(fields.get("reconciledAmount") != null) {
			returnVal.setReconciledAmount((BigDecimal) fields.get("reconciledAmount"));
}


		return returnVal;
 } 
	public static GlReconciliationEntry mapstrstr(Map<String, String> fields) throws Exception {

		GlReconciliationEntry returnVal = new GlReconciliationEntry();

		if(fields.get("glReconciliationId") != null) {
			returnVal.setGlReconciliationId((String) fields.get("glReconciliationId"));
}

		if(fields.get("acctgTransId") != null) {
			returnVal.setAcctgTransId((String) fields.get("acctgTransId"));
}

		if(fields.get("acctgTransEntrySeqId") != null) {
			returnVal.setAcctgTransEntrySeqId((String) fields.get("acctgTransEntrySeqId"));
}

		if(fields.get("reconciledAmount") != null) {
String buf;
buf = fields.get("reconciledAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReconciledAmount(bd);
}


		return returnVal;
 } 
	public static GlReconciliationEntry map(GenericValue val) {

GlReconciliationEntry returnVal = new GlReconciliationEntry();
		returnVal.setGlReconciliationId(val.getString("glReconciliationId"));
		returnVal.setAcctgTransId(val.getString("acctgTransId"));
		returnVal.setAcctgTransEntrySeqId(val.getString("acctgTransEntrySeqId"));
		returnVal.setReconciledAmount(val.getBigDecimal("reconciledAmount"));


return returnVal;

}

public static GlReconciliationEntry map(HttpServletRequest request) throws Exception {

		GlReconciliationEntry returnVal = new GlReconciliationEntry();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glReconciliationId")) {
returnVal.setGlReconciliationId(request.getParameter("glReconciliationId"));
}

		if(paramMap.containsKey("acctgTransId"))  {
returnVal.setAcctgTransId(request.getParameter("acctgTransId"));
}
		if(paramMap.containsKey("acctgTransEntrySeqId"))  {
returnVal.setAcctgTransEntrySeqId(request.getParameter("acctgTransEntrySeqId"));
}
		if(paramMap.containsKey("reconciledAmount"))  {
String buf = request.getParameter("reconciledAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReconciledAmount(bd);
}
return returnVal;

}
}
