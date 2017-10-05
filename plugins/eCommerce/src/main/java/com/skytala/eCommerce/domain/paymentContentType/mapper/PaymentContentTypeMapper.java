package com.skytala.eCommerce.domain.paymentContentType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.paymentContentType.model.PaymentContentType;

public class PaymentContentTypeMapper  {


	public static Map<String, Object> map(PaymentContentType paymentcontenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentcontenttype.getPaymentContentTypeId() != null ){
			returnVal.put("paymentContentTypeId",paymentcontenttype.getPaymentContentTypeId());
}

		if(paymentcontenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",paymentcontenttype.getParentTypeId());
}

		if(paymentcontenttype.getHasTable() != null ){
			returnVal.put("hasTable",paymentcontenttype.getHasTable());
}

		if(paymentcontenttype.getDescription() != null ){
			returnVal.put("description",paymentcontenttype.getDescription());
}

		return returnVal;
}


	public static PaymentContentType map(Map<String, Object> fields) {

		PaymentContentType returnVal = new PaymentContentType();

		if(fields.get("paymentContentTypeId") != null) {
			returnVal.setPaymentContentTypeId((String) fields.get("paymentContentTypeId"));
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
	public static PaymentContentType mapstrstr(Map<String, String> fields) throws Exception {

		PaymentContentType returnVal = new PaymentContentType();

		if(fields.get("paymentContentTypeId") != null) {
			returnVal.setPaymentContentTypeId((String) fields.get("paymentContentTypeId"));
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
	public static PaymentContentType map(GenericValue val) {

PaymentContentType returnVal = new PaymentContentType();
		returnVal.setPaymentContentTypeId(val.getString("paymentContentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PaymentContentType map(HttpServletRequest request) throws Exception {

		PaymentContentType returnVal = new PaymentContentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentContentTypeId")) {
returnVal.setPaymentContentTypeId(request.getParameter("paymentContentTypeId"));
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
