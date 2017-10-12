package com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeRate.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeRate.model.EmplPositionTypeRate;

public class EmplPositionTypeRateMapper  {


	public static Map<String, Object> map(EmplPositionTypeRate emplpositiontyperate) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(emplpositiontyperate.getEmplPositionTypeId() != null ){
			returnVal.put("emplPositionTypeId",emplpositiontyperate.getEmplPositionTypeId());
}

		if(emplpositiontyperate.getRateTypeId() != null ){
			returnVal.put("rateTypeId",emplpositiontyperate.getRateTypeId());
}

		if(emplpositiontyperate.getPayGradeId() != null ){
			returnVal.put("payGradeId",emplpositiontyperate.getPayGradeId());
}

		if(emplpositiontyperate.getSalaryStepSeqId() != null ){
			returnVal.put("salaryStepSeqId",emplpositiontyperate.getSalaryStepSeqId());
}

		if(emplpositiontyperate.getFromDate() != null ){
			returnVal.put("fromDate",emplpositiontyperate.getFromDate());
}

		if(emplpositiontyperate.getThruDate() != null ){
			returnVal.put("thruDate",emplpositiontyperate.getThruDate());
}

		return returnVal;
}


	public static EmplPositionTypeRate map(Map<String, Object> fields) {

		EmplPositionTypeRate returnVal = new EmplPositionTypeRate();

		if(fields.get("emplPositionTypeId") != null) {
			returnVal.setEmplPositionTypeId((String) fields.get("emplPositionTypeId"));
}

		if(fields.get("rateTypeId") != null) {
			returnVal.setRateTypeId((String) fields.get("rateTypeId"));
}

		if(fields.get("payGradeId") != null) {
			returnVal.setPayGradeId((String) fields.get("payGradeId"));
}

		if(fields.get("salaryStepSeqId") != null) {
			returnVal.setSalaryStepSeqId((String) fields.get("salaryStepSeqId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static EmplPositionTypeRate mapstrstr(Map<String, String> fields) throws Exception {

		EmplPositionTypeRate returnVal = new EmplPositionTypeRate();

		if(fields.get("emplPositionTypeId") != null) {
			returnVal.setEmplPositionTypeId((String) fields.get("emplPositionTypeId"));
}

		if(fields.get("rateTypeId") != null) {
			returnVal.setRateTypeId((String) fields.get("rateTypeId"));
}

		if(fields.get("payGradeId") != null) {
			returnVal.setPayGradeId((String) fields.get("payGradeId"));
}

		if(fields.get("salaryStepSeqId") != null) {
			returnVal.setSalaryStepSeqId((String) fields.get("salaryStepSeqId"));
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


		return returnVal;
 } 
	public static EmplPositionTypeRate map(GenericValue val) {

EmplPositionTypeRate returnVal = new EmplPositionTypeRate();
		returnVal.setEmplPositionTypeId(val.getString("emplPositionTypeId"));
		returnVal.setRateTypeId(val.getString("rateTypeId"));
		returnVal.setPayGradeId(val.getString("payGradeId"));
		returnVal.setSalaryStepSeqId(val.getString("salaryStepSeqId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static EmplPositionTypeRate map(HttpServletRequest request) throws Exception {

		EmplPositionTypeRate returnVal = new EmplPositionTypeRate();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("emplPositionTypeId")) {
returnVal.setEmplPositionTypeId(request.getParameter("emplPositionTypeId"));
}

		if(paramMap.containsKey("rateTypeId"))  {
returnVal.setRateTypeId(request.getParameter("rateTypeId"));
}
		if(paramMap.containsKey("payGradeId"))  {
returnVal.setPayGradeId(request.getParameter("payGradeId"));
}
		if(paramMap.containsKey("salaryStepSeqId"))  {
returnVal.setSalaryStepSeqId(request.getParameter("salaryStepSeqId"));
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
return returnVal;

}
}
