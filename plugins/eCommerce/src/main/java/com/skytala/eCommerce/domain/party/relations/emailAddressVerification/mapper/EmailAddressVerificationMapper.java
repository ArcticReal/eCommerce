package com.skytala.eCommerce.domain.party.relations.emailAddressVerification.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.model.EmailAddressVerification;

public class EmailAddressVerificationMapper  {


	public static Map<String, Object> map(EmailAddressVerification emailaddressverification) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(emailaddressverification.getEmailAddress() != null ){
			returnVal.put("emailAddress",emailaddressverification.getEmailAddress());
}

		if(emailaddressverification.getVerifyHash() != null ){
			returnVal.put("verifyHash",emailaddressverification.getVerifyHash());
}

		if(emailaddressverification.getExpireDate() != null ){
			returnVal.put("expireDate",emailaddressverification.getExpireDate());
}

		return returnVal;
}


	public static EmailAddressVerification map(Map<String, Object> fields) {

		EmailAddressVerification returnVal = new EmailAddressVerification();

		if(fields.get("emailAddress") != null) {
			returnVal.setEmailAddress((String) fields.get("emailAddress"));
}

		if(fields.get("verifyHash") != null) {
			returnVal.setVerifyHash((long) fields.get("verifyHash"));
}

		if(fields.get("expireDate") != null) {
			returnVal.setExpireDate((Timestamp) fields.get("expireDate"));
}


		return returnVal;
 } 
	public static EmailAddressVerification mapstrstr(Map<String, String> fields) throws Exception {

		EmailAddressVerification returnVal = new EmailAddressVerification();

		if(fields.get("emailAddress") != null) {
			returnVal.setEmailAddress((String) fields.get("emailAddress"));
}

		if(fields.get("verifyHash") != null) {
String buf;
buf = fields.get("verifyHash");
long ibuf = Long.parseLong(buf);
			returnVal.setVerifyHash(ibuf);
}

		if(fields.get("expireDate") != null) {
String buf = fields.get("expireDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setExpireDate(ibuf);
}


		return returnVal;
 } 
	public static EmailAddressVerification map(GenericValue val) {

EmailAddressVerification returnVal = new EmailAddressVerification();
		returnVal.setEmailAddress(val.getString("emailAddress"));
		returnVal.setVerifyHash(val.getLong("verifyHash"));
		returnVal.setExpireDate(val.getTimestamp("expireDate"));


return returnVal;

}

public static EmailAddressVerification map(HttpServletRequest request) throws Exception {

		EmailAddressVerification returnVal = new EmailAddressVerification();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("emailAddress")) {
returnVal.setEmailAddress(request.getParameter("emailAddress"));
}

		if(paramMap.containsKey("verifyHash"))  {
String buf = request.getParameter("verifyHash");
Long ibuf = Long.parseLong(buf);
returnVal.setVerifyHash(ibuf);
}
		if(paramMap.containsKey("expireDate"))  {
String buf = request.getParameter("expireDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setExpireDate(ibuf);
}
return returnVal;

}
}
