package com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryMember.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryMember.model.GlAccountCategoryMember;

public class GlAccountCategoryMemberMapper  {


	public static Map<String, Object> map(GlAccountCategoryMember glaccountcategorymember) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glaccountcategorymember.getGlAccountId() != null ){
			returnVal.put("glAccountId",glaccountcategorymember.getGlAccountId());
}

		if(glaccountcategorymember.getGlAccountCategoryId() != null ){
			returnVal.put("glAccountCategoryId",glaccountcategorymember.getGlAccountCategoryId());
}

		if(glaccountcategorymember.getFromDate() != null ){
			returnVal.put("fromDate",glaccountcategorymember.getFromDate());
}

		if(glaccountcategorymember.getThruDate() != null ){
			returnVal.put("thruDate",glaccountcategorymember.getThruDate());
}

		if(glaccountcategorymember.getAmountPercentage() != null ){
			returnVal.put("amountPercentage",glaccountcategorymember.getAmountPercentage());
}

		return returnVal;
}


	public static GlAccountCategoryMember map(Map<String, Object> fields) {

		GlAccountCategoryMember returnVal = new GlAccountCategoryMember();

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("glAccountCategoryId") != null) {
			returnVal.setGlAccountCategoryId((String) fields.get("glAccountCategoryId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("amountPercentage") != null) {
			returnVal.setAmountPercentage((BigDecimal) fields.get("amountPercentage"));
}


		return returnVal;
 } 
	public static GlAccountCategoryMember mapstrstr(Map<String, String> fields) throws Exception {

		GlAccountCategoryMember returnVal = new GlAccountCategoryMember();

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("glAccountCategoryId") != null) {
			returnVal.setGlAccountCategoryId((String) fields.get("glAccountCategoryId"));
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

		if(fields.get("amountPercentage") != null) {
String buf;
buf = fields.get("amountPercentage");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmountPercentage(bd);
}


		return returnVal;
 } 
	public static GlAccountCategoryMember map(GenericValue val) {

GlAccountCategoryMember returnVal = new GlAccountCategoryMember();
		returnVal.setGlAccountId(val.getString("glAccountId"));
		returnVal.setGlAccountCategoryId(val.getString("glAccountCategoryId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setAmountPercentage(val.getBigDecimal("amountPercentage"));


return returnVal;

}

public static GlAccountCategoryMember map(HttpServletRequest request) throws Exception {

		GlAccountCategoryMember returnVal = new GlAccountCategoryMember();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glAccountId")) {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}

		if(paramMap.containsKey("glAccountCategoryId"))  {
returnVal.setGlAccountCategoryId(request.getParameter("glAccountCategoryId"));
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
		if(paramMap.containsKey("amountPercentage"))  {
String buf = request.getParameter("amountPercentage");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmountPercentage(bd);
}
return returnVal;

}
}
