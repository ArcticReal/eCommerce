package com.skytala.eCommerce.domain.accounting.relations.paymentType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentType.model.PaymentType;

public class PaymentTypeMapper  {


	public static Map<String, Object> map(PaymentType paymenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymenttype.getPaymentTypeId() != null ){
			returnVal.put("paymentTypeId",paymenttype.getPaymentTypeId());
}

		if(paymenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",paymenttype.getParentTypeId());
}

		if(paymenttype.getHasTable() != null ){
			returnVal.put("hasTable",paymenttype.getHasTable());
}

		if(paymenttype.getDescription() != null ){
			returnVal.put("description",paymenttype.getDescription());
}

		return returnVal;
}


	public static PaymentType map(Map<String, Object> fields) {

		PaymentType returnVal = new PaymentType();

		if(fields.get("paymentTypeId") != null) {
			returnVal.setPaymentTypeId((String) fields.get("paymentTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PaymentType mapstrstr(Map<String, String> fields) throws Exception {

		PaymentType returnVal = new PaymentType();

		if(fields.get("paymentTypeId") != null) {
			returnVal.setPaymentTypeId((String) fields.get("paymentTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PaymentType map(GenericValue val) {

PaymentType returnVal = new PaymentType();
		returnVal.setPaymentTypeId(val.getString("paymentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PaymentType map(HttpServletRequest request) throws Exception {

		PaymentType returnVal = new PaymentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentTypeId")) {
returnVal.setPaymentTypeId(request.getParameter("paymentTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
