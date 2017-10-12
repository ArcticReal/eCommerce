package com.skytala.eCommerce.domain.accounting.relations.giftCard.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.model.GiftCard;

public class GiftCardMapper  {


	public static Map<String, Object> map(GiftCard giftcard) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(giftcard.getPaymentMethodId() != null ){
			returnVal.put("paymentMethodId",giftcard.getPaymentMethodId());
}

		if(giftcard.getCardNumber() != null ){
			returnVal.put("cardNumber",giftcard.getCardNumber());
}

		if(giftcard.getPinNumber() != null ){
			returnVal.put("pinNumber",giftcard.getPinNumber());
}

		if(giftcard.getExpireDate() != null ){
			returnVal.put("expireDate",giftcard.getExpireDate());
}

		if(giftcard.getContactMechId() != null ){
			returnVal.put("contactMechId",giftcard.getContactMechId());
}

		return returnVal;
}


	public static GiftCard map(Map<String, Object> fields) {

		GiftCard returnVal = new GiftCard();

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("cardNumber") != null) {
			returnVal.setCardNumber((String) fields.get("cardNumber"));
}

		if(fields.get("pinNumber") != null) {
			returnVal.setPinNumber((String) fields.get("pinNumber"));
}

		if(fields.get("expireDate") != null) {
			returnVal.setExpireDate((Timestamp) fields.get("expireDate"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}


		return returnVal;
 } 
	public static GiftCard mapstrstr(Map<String, String> fields) throws Exception {

		GiftCard returnVal = new GiftCard();

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("cardNumber") != null) {
			returnVal.setCardNumber((String) fields.get("cardNumber"));
}

		if(fields.get("pinNumber") != null) {
			returnVal.setPinNumber((String) fields.get("pinNumber"));
}

		if(fields.get("expireDate") != null) {
String buf = fields.get("expireDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setExpireDate(ibuf);
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}


		return returnVal;
 } 
	public static GiftCard map(GenericValue val) {

GiftCard returnVal = new GiftCard();
		returnVal.setPaymentMethodId(val.getString("paymentMethodId"));
		returnVal.setCardNumber(val.getString("cardNumber"));
		returnVal.setPinNumber(val.getString("pinNumber"));
		returnVal.setExpireDate(val.getTimestamp("expireDate"));
		returnVal.setContactMechId(val.getString("contactMechId"));


return returnVal;

}

public static GiftCard map(HttpServletRequest request) throws Exception {

		GiftCard returnVal = new GiftCard();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentMethodId")) {
returnVal.setPaymentMethodId(request.getParameter("paymentMethodId"));
}

		if(paramMap.containsKey("cardNumber"))  {
returnVal.setCardNumber(request.getParameter("cardNumber"));
}
		if(paramMap.containsKey("pinNumber"))  {
returnVal.setPinNumber(request.getParameter("pinNumber"));
}
		if(paramMap.containsKey("expireDate"))  {
String buf = request.getParameter("expireDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setExpireDate(ibuf);
}
		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
return returnVal;

}
}
