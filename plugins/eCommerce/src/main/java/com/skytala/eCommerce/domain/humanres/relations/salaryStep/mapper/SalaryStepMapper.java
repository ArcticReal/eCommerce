package com.skytala.eCommerce.domain.humanres.relations.salaryStep.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.model.SalaryStep;

public class SalaryStepMapper  {


	public static Map<String, Object> map(SalaryStep salarystep) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(salarystep.getSalaryStepSeqId() != null ){
			returnVal.put("salaryStepSeqId",salarystep.getSalaryStepSeqId());
}

		if(salarystep.getPayGradeId() != null ){
			returnVal.put("payGradeId",salarystep.getPayGradeId());
}

		if(salarystep.getFromDate() != null ){
			returnVal.put("fromDate",salarystep.getFromDate());
}

		if(salarystep.getThruDate() != null ){
			returnVal.put("thruDate",salarystep.getThruDate());
}

		if(salarystep.getDateModified() != null ){
			returnVal.put("dateModified",salarystep.getDateModified());
}

		if(salarystep.getAmount() != null ){
			returnVal.put("amount",salarystep.getAmount());
}

		if(salarystep.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",salarystep.getCreatedByUserLogin());
}

		if(salarystep.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",salarystep.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static SalaryStep map(Map<String, Object> fields) {

		SalaryStep returnVal = new SalaryStep();

		if(fields.get("salaryStepSeqId") != null) {
			returnVal.setSalaryStepSeqId((String) fields.get("salaryStepSeqId"));
}

		if(fields.get("payGradeId") != null) {
			returnVal.setPayGradeId((String) fields.get("payGradeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("dateModified") != null) {
			returnVal.setDateModified((Timestamp) fields.get("dateModified"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static SalaryStep mapstrstr(Map<String, String> fields) throws Exception {

		SalaryStep returnVal = new SalaryStep();

		if(fields.get("salaryStepSeqId") != null) {
			returnVal.setSalaryStepSeqId((String) fields.get("salaryStepSeqId"));
}

		if(fields.get("payGradeId") != null) {
			returnVal.setPayGradeId((String) fields.get("payGradeId"));
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

		if(fields.get("dateModified") != null) {
String buf = fields.get("dateModified");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDateModified(ibuf);
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static SalaryStep map(GenericValue val) {

SalaryStep returnVal = new SalaryStep();
		returnVal.setSalaryStepSeqId(val.getString("salaryStepSeqId"));
		returnVal.setPayGradeId(val.getString("payGradeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setDateModified(val.getTimestamp("dateModified"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static SalaryStep map(HttpServletRequest request) throws Exception {

		SalaryStep returnVal = new SalaryStep();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("salaryStepSeqId")) {
returnVal.setSalaryStepSeqId(request.getParameter("salaryStepSeqId"));
}

		if(paramMap.containsKey("payGradeId"))  {
returnVal.setPayGradeId(request.getParameter("payGradeId"));
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
		if(paramMap.containsKey("dateModified"))  {
String buf = request.getParameter("dateModified");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDateModified(ibuf);
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
return returnVal;

}
}
