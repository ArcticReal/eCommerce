package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.billing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.billing.WorkEffortBilling;

public class WorkEffortBillingMapper  {


	public static Map<String, Object> map(WorkEffortBilling workeffortbilling) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortbilling.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortbilling.getWorkEffortId());
}

		if(workeffortbilling.getInvoiceId() != null ){
			returnVal.put("invoiceId",workeffortbilling.getInvoiceId());
}

		if(workeffortbilling.getInvoiceItemSeqId() != null ){
			returnVal.put("invoiceItemSeqId",workeffortbilling.getInvoiceItemSeqId());
}

		if(workeffortbilling.getPercentage() != null ){
			returnVal.put("percentage",workeffortbilling.getPercentage());
}

		return returnVal;
}


	public static WorkEffortBilling map(Map<String, Object> fields) {

		WorkEffortBilling returnVal = new WorkEffortBilling();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}

		if(fields.get("percentage") != null) {
			returnVal.setPercentage((BigDecimal) fields.get("percentage"));
}


		return returnVal;
 } 
	public static WorkEffortBilling mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortBilling returnVal = new WorkEffortBilling();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}

		if(fields.get("percentage") != null) {
String buf;
buf = fields.get("percentage");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPercentage(bd);
}


		return returnVal;
 } 
	public static WorkEffortBilling map(GenericValue val) {

WorkEffortBilling returnVal = new WorkEffortBilling();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setInvoiceItemSeqId(val.getString("invoiceItemSeqId"));
		returnVal.setPercentage(val.getBigDecimal("percentage"));


return returnVal;

}

public static WorkEffortBilling map(HttpServletRequest request) throws Exception {

		WorkEffortBilling returnVal = new WorkEffortBilling();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("invoiceId"))  {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}
		if(paramMap.containsKey("invoiceItemSeqId"))  {
returnVal.setInvoiceItemSeqId(request.getParameter("invoiceItemSeqId"));
}
		if(paramMap.containsKey("percentage"))  {
String buf = request.getParameter("percentage");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPercentage(bd);
}
return returnVal;

}
}
