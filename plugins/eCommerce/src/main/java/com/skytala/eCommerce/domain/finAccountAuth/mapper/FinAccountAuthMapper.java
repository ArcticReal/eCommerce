package com.skytala.eCommerce.domain.finAccountAuth.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.finAccountAuth.model.FinAccountAuth;

public class FinAccountAuthMapper  {


	public static Map<String, Object> map(FinAccountAuth finaccountauth) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(finaccountauth.getFinAccountAuthId() != null ){
			returnVal.put("finAccountAuthId",finaccountauth.getFinAccountAuthId());
}

		if(finaccountauth.getFinAccountId() != null ){
			returnVal.put("finAccountId",finaccountauth.getFinAccountId());
}

		if(finaccountauth.getAmount() != null ){
			returnVal.put("amount",finaccountauth.getAmount());
}

		if(finaccountauth.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",finaccountauth.getCurrencyUomId());
}

		if(finaccountauth.getAuthorizationDate() != null ){
			returnVal.put("authorizationDate",finaccountauth.getAuthorizationDate());
}

		if(finaccountauth.getFromDate() != null ){
			returnVal.put("fromDate",finaccountauth.getFromDate());
}

		if(finaccountauth.getThruDate() != null ){
			returnVal.put("thruDate",finaccountauth.getThruDate());
}

		return returnVal;
}


	public static FinAccountAuth map(Map<String, Object> fields) {

		FinAccountAuth returnVal = new FinAccountAuth();

		if(fields.get("finAccountAuthId") != null) {
			returnVal.setFinAccountAuthId((String) fields.get("finAccountAuthId"));
}

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("authorizationDate") != null) {
			returnVal.setAuthorizationDate((Timestamp) fields.get("authorizationDate"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static FinAccountAuth mapstrstr(Map<String, String> fields) throws Exception {

		FinAccountAuth returnVal = new FinAccountAuth();

		if(fields.get("finAccountAuthId") != null) {
			returnVal.setFinAccountAuthId((String) fields.get("finAccountAuthId"));
}

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("authorizationDate") != null) {
String buf = fields.get("authorizationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setAuthorizationDate(ibuf);
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
	public static FinAccountAuth map(GenericValue val) {

FinAccountAuth returnVal = new FinAccountAuth();
		returnVal.setFinAccountAuthId(val.getString("finAccountAuthId"));
		returnVal.setFinAccountId(val.getString("finAccountId"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setAuthorizationDate(val.getTimestamp("authorizationDate"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static FinAccountAuth map(HttpServletRequest request) throws Exception {

		FinAccountAuth returnVal = new FinAccountAuth();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("finAccountAuthId")) {
returnVal.setFinAccountAuthId(request.getParameter("finAccountAuthId"));
}

		if(paramMap.containsKey("finAccountId"))  {
returnVal.setFinAccountId(request.getParameter("finAccountId"));
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("authorizationDate"))  {
String buf = request.getParameter("authorizationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setAuthorizationDate(ibuf);
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
