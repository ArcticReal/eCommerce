package com.skytala.eCommerce.domain.humanres.relations.payGrade.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.payGrade.model.PayGrade;

public class PayGradeMapper  {


	public static Map<String, Object> map(PayGrade paygrade) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paygrade.getPayGradeId() != null ){
			returnVal.put("payGradeId",paygrade.getPayGradeId());
}

		if(paygrade.getPayGradeName() != null ){
			returnVal.put("payGradeName",paygrade.getPayGradeName());
}

		if(paygrade.getComments() != null ){
			returnVal.put("comments",paygrade.getComments());
}

		return returnVal;
}


	public static PayGrade map(Map<String, Object> fields) {

		PayGrade returnVal = new PayGrade();

		if(fields.get("payGradeId") != null) {
			returnVal.setPayGradeId((String) fields.get("payGradeId"));
}

		if(fields.get("payGradeName") != null) {
			returnVal.setPayGradeName((String) fields.get("payGradeName"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static PayGrade mapstrstr(Map<String, String> fields) throws Exception {

		PayGrade returnVal = new PayGrade();

		if(fields.get("payGradeId") != null) {
			returnVal.setPayGradeId((String) fields.get("payGradeId"));
}

		if(fields.get("payGradeName") != null) {
			returnVal.setPayGradeName((String) fields.get("payGradeName"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static PayGrade map(GenericValue val) {

PayGrade returnVal = new PayGrade();
		returnVal.setPayGradeId(val.getString("payGradeId"));
		returnVal.setPayGradeName(val.getString("payGradeName"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static PayGrade map(HttpServletRequest request) throws Exception {

		PayGrade returnVal = new PayGrade();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("payGradeId")) {
returnVal.setPayGradeId(request.getParameter("payGradeId"));
}

		if(paramMap.containsKey("payGradeName"))  {
returnVal.setPayGradeName(request.getParameter("payGradeName"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
