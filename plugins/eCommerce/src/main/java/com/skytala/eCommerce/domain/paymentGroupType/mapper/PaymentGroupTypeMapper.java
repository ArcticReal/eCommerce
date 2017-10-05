package com.skytala.eCommerce.domain.paymentGroupType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.paymentGroupType.model.PaymentGroupType;

public class PaymentGroupTypeMapper  {


	public static Map<String, Object> map(PaymentGroupType paymentgrouptype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgrouptype.getPaymentGroupTypeId() != null ){
			returnVal.put("paymentGroupTypeId",paymentgrouptype.getPaymentGroupTypeId());
}

		if(paymentgrouptype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",paymentgrouptype.getParentTypeId());
}

		if(paymentgrouptype.getHasTable() != null ){
			returnVal.put("hasTable",paymentgrouptype.getHasTable());
}

		if(paymentgrouptype.getDescription() != null ){
			returnVal.put("description",paymentgrouptype.getDescription());
}

		return returnVal;
}


	public static PaymentGroupType map(Map<String, Object> fields) {

		PaymentGroupType returnVal = new PaymentGroupType();

		if(fields.get("paymentGroupTypeId") != null) {
			returnVal.setPaymentGroupTypeId((String) fields.get("paymentGroupTypeId"));
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
	public static PaymentGroupType mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGroupType returnVal = new PaymentGroupType();

		if(fields.get("paymentGroupTypeId") != null) {
			returnVal.setPaymentGroupTypeId((String) fields.get("paymentGroupTypeId"));
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
	public static PaymentGroupType map(GenericValue val) {

PaymentGroupType returnVal = new PaymentGroupType();
		returnVal.setPaymentGroupTypeId(val.getString("paymentGroupTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PaymentGroupType map(HttpServletRequest request) throws Exception {

		PaymentGroupType returnVal = new PaymentGroupType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGroupTypeId")) {
returnVal.setPaymentGroupTypeId(request.getParameter("paymentGroupTypeId"));
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
