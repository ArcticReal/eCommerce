package com.skytala.eCommerce.domain.accounting.relations.creditCard.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.model.CreditCard;

public class CreditCardMapper  {


	public static Map<String, Object> map(CreditCard creditcard) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(creditcard.getPaymentMethodId() != null ){
			returnVal.put("paymentMethodId",creditcard.getPaymentMethodId());
}

		if(creditcard.getCardType() != null ){
			returnVal.put("cardType",creditcard.getCardType());
}

		if(creditcard.getCardNumber() != null ){
			returnVal.put("cardNumber",creditcard.getCardNumber());
}

		if(creditcard.getValidFromDate() != null ){
			returnVal.put("validFromDate",creditcard.getValidFromDate());
}

		if(creditcard.getExpireDate() != null ){
			returnVal.put("expireDate",creditcard.getExpireDate());
}

		if(creditcard.getIssueNumber() != null ){
			returnVal.put("issueNumber",creditcard.getIssueNumber());
}

		if(creditcard.getCompanyNameOnCard() != null ){
			returnVal.put("companyNameOnCard",creditcard.getCompanyNameOnCard());
}

		if(creditcard.getTitleOnCard() != null ){
			returnVal.put("titleOnCard",creditcard.getTitleOnCard());
}

		if(creditcard.getFirstNameOnCard() != null ){
			returnVal.put("firstNameOnCard",creditcard.getFirstNameOnCard());
}

		if(creditcard.getMiddleNameOnCard() != null ){
			returnVal.put("middleNameOnCard",creditcard.getMiddleNameOnCard());
}

		if(creditcard.getLastNameOnCard() != null ){
			returnVal.put("lastNameOnCard",creditcard.getLastNameOnCard());
}

		if(creditcard.getSuffixOnCard() != null ){
			returnVal.put("suffixOnCard",creditcard.getSuffixOnCard());
}

		if(creditcard.getContactMechId() != null ){
			returnVal.put("contactMechId",creditcard.getContactMechId());
}

		if(creditcard.getConsecutiveFailedAuths() != null ){
			returnVal.put("consecutiveFailedAuths",creditcard.getConsecutiveFailedAuths());
}

		if(creditcard.getLastFailedAuthDate() != null ){
			returnVal.put("lastFailedAuthDate",creditcard.getLastFailedAuthDate());
}

		if(creditcard.getConsecutiveFailedNsf() != null ){
			returnVal.put("consecutiveFailedNsf",creditcard.getConsecutiveFailedNsf());
}

		if(creditcard.getLastFailedNsfDate() != null ){
			returnVal.put("lastFailedNsfDate",creditcard.getLastFailedNsfDate());
}

		return returnVal;
}


	public static CreditCard map(Map<String, Object> fields) {

		CreditCard returnVal = new CreditCard();

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("cardType") != null) {
			returnVal.setCardType((String) fields.get("cardType"));
}

		if(fields.get("cardNumber") != null) {
			returnVal.setCardNumber((String) fields.get("cardNumber"));
}

		if(fields.get("validFromDate") != null) {
			returnVal.setValidFromDate((Timestamp) fields.get("validFromDate"));
}

		if(fields.get("expireDate") != null) {
			returnVal.setExpireDate((Timestamp) fields.get("expireDate"));
}

		if(fields.get("issueNumber") != null) {
			returnVal.setIssueNumber((Timestamp) fields.get("issueNumber"));
}

		if(fields.get("companyNameOnCard") != null) {
			returnVal.setCompanyNameOnCard((String) fields.get("companyNameOnCard"));
}

		if(fields.get("titleOnCard") != null) {
			returnVal.setTitleOnCard((String) fields.get("titleOnCard"));
}

		if(fields.get("firstNameOnCard") != null) {
			returnVal.setFirstNameOnCard((String) fields.get("firstNameOnCard"));
}

		if(fields.get("middleNameOnCard") != null) {
			returnVal.setMiddleNameOnCard((String) fields.get("middleNameOnCard"));
}

		if(fields.get("lastNameOnCard") != null) {
			returnVal.setLastNameOnCard((String) fields.get("lastNameOnCard"));
}

		if(fields.get("suffixOnCard") != null) {
			returnVal.setSuffixOnCard((String) fields.get("suffixOnCard"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("consecutiveFailedAuths") != null) {
			returnVal.setConsecutiveFailedAuths((long) fields.get("consecutiveFailedAuths"));
}

		if(fields.get("lastFailedAuthDate") != null) {
			returnVal.setLastFailedAuthDate((Timestamp) fields.get("lastFailedAuthDate"));
}

		if(fields.get("consecutiveFailedNsf") != null) {
			returnVal.setConsecutiveFailedNsf((long) fields.get("consecutiveFailedNsf"));
}

		if(fields.get("lastFailedNsfDate") != null) {
			returnVal.setLastFailedNsfDate((Timestamp) fields.get("lastFailedNsfDate"));
}


		return returnVal;
 } 
	public static CreditCard mapstrstr(Map<String, String> fields) throws Exception {

		CreditCard returnVal = new CreditCard();

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("cardType") != null) {
			returnVal.setCardType((String) fields.get("cardType"));
}

		if(fields.get("cardNumber") != null) {
			returnVal.setCardNumber((String) fields.get("cardNumber"));
}

		if(fields.get("validFromDate") != null) {
String buf = fields.get("validFromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setValidFromDate(ibuf);
}

		if(fields.get("expireDate") != null) {
String buf = fields.get("expireDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setExpireDate(ibuf);
}

		if(fields.get("issueNumber") != null) {
String buf = fields.get("issueNumber");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setIssueNumber(ibuf);
}

		if(fields.get("companyNameOnCard") != null) {
			returnVal.setCompanyNameOnCard((String) fields.get("companyNameOnCard"));
}

		if(fields.get("titleOnCard") != null) {
			returnVal.setTitleOnCard((String) fields.get("titleOnCard"));
}

		if(fields.get("firstNameOnCard") != null) {
			returnVal.setFirstNameOnCard((String) fields.get("firstNameOnCard"));
}

		if(fields.get("middleNameOnCard") != null) {
			returnVal.setMiddleNameOnCard((String) fields.get("middleNameOnCard"));
}

		if(fields.get("lastNameOnCard") != null) {
			returnVal.setLastNameOnCard((String) fields.get("lastNameOnCard"));
}

		if(fields.get("suffixOnCard") != null) {
			returnVal.setSuffixOnCard((String) fields.get("suffixOnCard"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("consecutiveFailedAuths") != null) {
String buf;
buf = fields.get("consecutiveFailedAuths");
long ibuf = Long.parseLong(buf);
			returnVal.setConsecutiveFailedAuths(ibuf);
}

		if(fields.get("lastFailedAuthDate") != null) {
String buf = fields.get("lastFailedAuthDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastFailedAuthDate(ibuf);
}

		if(fields.get("consecutiveFailedNsf") != null) {
String buf;
buf = fields.get("consecutiveFailedNsf");
long ibuf = Long.parseLong(buf);
			returnVal.setConsecutiveFailedNsf(ibuf);
}

		if(fields.get("lastFailedNsfDate") != null) {
String buf = fields.get("lastFailedNsfDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastFailedNsfDate(ibuf);
}


		return returnVal;
 } 
	public static CreditCard map(GenericValue val) {

CreditCard returnVal = new CreditCard();
		returnVal.setPaymentMethodId(val.getString("paymentMethodId"));
		returnVal.setCardType(val.getString("cardType"));
		returnVal.setCardNumber(val.getString("cardNumber"));
		returnVal.setValidFromDate(val.getTimestamp("validFromDate"));
		returnVal.setExpireDate(val.getTimestamp("expireDate"));
		returnVal.setIssueNumber(val.getTimestamp("issueNumber"));
		returnVal.setCompanyNameOnCard(val.getString("companyNameOnCard"));
		returnVal.setTitleOnCard(val.getString("titleOnCard"));
		returnVal.setFirstNameOnCard(val.getString("firstNameOnCard"));
		returnVal.setMiddleNameOnCard(val.getString("middleNameOnCard"));
		returnVal.setLastNameOnCard(val.getString("lastNameOnCard"));
		returnVal.setSuffixOnCard(val.getString("suffixOnCard"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setConsecutiveFailedAuths(val.getLong("consecutiveFailedAuths"));
		returnVal.setLastFailedAuthDate(val.getTimestamp("lastFailedAuthDate"));
		returnVal.setConsecutiveFailedNsf(val.getLong("consecutiveFailedNsf"));
		returnVal.setLastFailedNsfDate(val.getTimestamp("lastFailedNsfDate"));


return returnVal;

}

public static CreditCard map(HttpServletRequest request) throws Exception {

		CreditCard returnVal = new CreditCard();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentMethodId")) {
returnVal.setPaymentMethodId(request.getParameter("paymentMethodId"));
}

		if(paramMap.containsKey("cardType"))  {
returnVal.setCardType(request.getParameter("cardType"));
}
		if(paramMap.containsKey("cardNumber"))  {
returnVal.setCardNumber(request.getParameter("cardNumber"));
}
		if(paramMap.containsKey("validFromDate"))  {
String buf = request.getParameter("validFromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setValidFromDate(ibuf);
}
		if(paramMap.containsKey("expireDate"))  {
String buf = request.getParameter("expireDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setExpireDate(ibuf);
}
		if(paramMap.containsKey("issueNumber"))  {
String buf = request.getParameter("issueNumber");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setIssueNumber(ibuf);
}
		if(paramMap.containsKey("companyNameOnCard"))  {
returnVal.setCompanyNameOnCard(request.getParameter("companyNameOnCard"));
}
		if(paramMap.containsKey("titleOnCard"))  {
returnVal.setTitleOnCard(request.getParameter("titleOnCard"));
}
		if(paramMap.containsKey("firstNameOnCard"))  {
returnVal.setFirstNameOnCard(request.getParameter("firstNameOnCard"));
}
		if(paramMap.containsKey("middleNameOnCard"))  {
returnVal.setMiddleNameOnCard(request.getParameter("middleNameOnCard"));
}
		if(paramMap.containsKey("lastNameOnCard"))  {
returnVal.setLastNameOnCard(request.getParameter("lastNameOnCard"));
}
		if(paramMap.containsKey("suffixOnCard"))  {
returnVal.setSuffixOnCard(request.getParameter("suffixOnCard"));
}
		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
		if(paramMap.containsKey("consecutiveFailedAuths"))  {
String buf = request.getParameter("consecutiveFailedAuths");
Long ibuf = Long.parseLong(buf);
returnVal.setConsecutiveFailedAuths(ibuf);
}
		if(paramMap.containsKey("lastFailedAuthDate"))  {
String buf = request.getParameter("lastFailedAuthDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastFailedAuthDate(ibuf);
}
		if(paramMap.containsKey("consecutiveFailedNsf"))  {
String buf = request.getParameter("consecutiveFailedNsf");
Long ibuf = Long.parseLong(buf);
returnVal.setConsecutiveFailedNsf(ibuf);
}
		if(paramMap.containsKey("lastFailedNsfDate"))  {
String buf = request.getParameter("lastFailedNsfDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastFailedNsfDate(ibuf);
}
return returnVal;

}
}
