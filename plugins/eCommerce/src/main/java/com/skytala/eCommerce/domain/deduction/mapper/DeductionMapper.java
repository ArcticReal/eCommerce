package com.skytala.eCommerce.domain.deduction.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.deduction.model.Deduction;

public class DeductionMapper  {


	public static Map<String, Object> map(Deduction deduction) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(deduction.getDeductionId() != null ){
			returnVal.put("deductionId",deduction.getDeductionId());
}

		if(deduction.getDeductionTypeId() != null ){
			returnVal.put("deductionTypeId",deduction.getDeductionTypeId());
}

		if(deduction.getPaymentId() != null ){
			returnVal.put("paymentId",deduction.getPaymentId());
}

		if(deduction.getAmount() != null ){
			returnVal.put("amount",deduction.getAmount());
}

		return returnVal;
}


	public static Deduction map(Map<String, Object> fields) {

		Deduction returnVal = new Deduction();

		if(fields.get("deductionId") != null) {
			returnVal.setDeductionId((String) fields.get("deductionId"));
}

		if(fields.get("deductionTypeId") != null) {
			returnVal.setDeductionTypeId((String) fields.get("deductionTypeId"));
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}


		return returnVal;
 } 
	public static Deduction mapstrstr(Map<String, String> fields) throws Exception {

		Deduction returnVal = new Deduction();

		if(fields.get("deductionId") != null) {
			returnVal.setDeductionId((String) fields.get("deductionId"));
}

		if(fields.get("deductionTypeId") != null) {
			returnVal.setDeductionTypeId((String) fields.get("deductionTypeId"));
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}


		return returnVal;
 } 
	public static Deduction map(GenericValue val) {

Deduction returnVal = new Deduction();
		returnVal.setDeductionId(val.getString("deductionId"));
		returnVal.setDeductionTypeId(val.getString("deductionTypeId"));
		returnVal.setPaymentId(val.getString("paymentId"));
		returnVal.setAmount(val.getBigDecimal("amount"));


return returnVal;

}

public static Deduction map(HttpServletRequest request) throws Exception {

		Deduction returnVal = new Deduction();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("deductionId")) {
returnVal.setDeductionId(request.getParameter("deductionId"));
}

		if(paramMap.containsKey("deductionTypeId"))  {
returnVal.setDeductionTypeId(request.getParameter("deductionTypeId"));
}
		if(paramMap.containsKey("paymentId"))  {
returnVal.setPaymentId(request.getParameter("paymentId"));
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
return returnVal;

}
}
