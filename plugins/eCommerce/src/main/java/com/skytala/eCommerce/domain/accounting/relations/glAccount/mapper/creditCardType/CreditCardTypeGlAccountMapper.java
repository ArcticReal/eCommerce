package com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.creditCardType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.creditCardType.CreditCardTypeGlAccount;

public class CreditCardTypeGlAccountMapper  {


	public static Map<String, Object> map(CreditCardTypeGlAccount creditcardtypeglaccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(creditcardtypeglaccount.getCardType() != null ){
			returnVal.put("cardType",creditcardtypeglaccount.getCardType());
}

		if(creditcardtypeglaccount.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",creditcardtypeglaccount.getOrganizationPartyId());
}

		if(creditcardtypeglaccount.getGlAccountId() != null ){
			returnVal.put("glAccountId",creditcardtypeglaccount.getGlAccountId());
}

		return returnVal;
}


	public static CreditCardTypeGlAccount map(Map<String, Object> fields) {

		CreditCardTypeGlAccount returnVal = new CreditCardTypeGlAccount();

		if(fields.get("cardType") != null) {
			returnVal.setCardType((String) fields.get("cardType"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static CreditCardTypeGlAccount mapstrstr(Map<String, String> fields) throws Exception {

		CreditCardTypeGlAccount returnVal = new CreditCardTypeGlAccount();

		if(fields.get("cardType") != null) {
			returnVal.setCardType((String) fields.get("cardType"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static CreditCardTypeGlAccount map(GenericValue val) {

CreditCardTypeGlAccount returnVal = new CreditCardTypeGlAccount();
		returnVal.setCardType(val.getString("cardType"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setGlAccountId(val.getString("glAccountId"));


return returnVal;

}

public static CreditCardTypeGlAccount map(HttpServletRequest request) throws Exception {

		CreditCardTypeGlAccount returnVal = new CreditCardTypeGlAccount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("cardType")) {
returnVal.setCardType(request.getParameter("cardType"));
}

		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}
		if(paramMap.containsKey("glAccountId"))  {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}
return returnVal;

}
}
