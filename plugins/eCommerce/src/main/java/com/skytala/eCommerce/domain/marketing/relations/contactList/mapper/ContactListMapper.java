package com.skytala.eCommerce.domain.marketing.relations.contactList.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.ContactList;

public class ContactListMapper  {


	public static Map<String, Object> map(ContactList contactlist) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contactlist.getContactListId() != null ){
			returnVal.put("contactListId",contactlist.getContactListId());
}

		if(contactlist.getContactListTypeId() != null ){
			returnVal.put("contactListTypeId",contactlist.getContactListTypeId());
}

		if(contactlist.getContactMechTypeId() != null ){
			returnVal.put("contactMechTypeId",contactlist.getContactMechTypeId());
}

		if(contactlist.getMarketingCampaignId() != null ){
			returnVal.put("marketingCampaignId",contactlist.getMarketingCampaignId());
}

		if(contactlist.getContactListName() != null ){
			returnVal.put("contactListName",contactlist.getContactListName());
}

		if(contactlist.getDescription() != null ){
			returnVal.put("description",contactlist.getDescription());
}

		if(contactlist.getComments() != null ){
			returnVal.put("comments",contactlist.getComments());
}

		if(contactlist.getIsPublic() != null ){
			returnVal.put("isPublic",contactlist.getIsPublic());
}

		if(contactlist.getSingleUse() != null ){
			returnVal.put("singleUse",contactlist.getSingleUse());
}

		if(contactlist.getOwnerPartyId() != null ){
			returnVal.put("ownerPartyId",contactlist.getOwnerPartyId());
}

		if(contactlist.getVerifyEmailFrom() != null ){
			returnVal.put("verifyEmailFrom",contactlist.getVerifyEmailFrom());
}

		if(contactlist.getVerifyEmailScreen() != null ){
			returnVal.put("verifyEmailScreen",contactlist.getVerifyEmailScreen());
}

		if(contactlist.getVerifyEmailSubject() != null ){
			returnVal.put("verifyEmailSubject",contactlist.getVerifyEmailSubject());
}

		if(contactlist.getVerifyEmailWebSiteId() != null ){
			returnVal.put("verifyEmailWebSiteId",contactlist.getVerifyEmailWebSiteId());
}

		if(contactlist.getOptOutScreen() != null ){
			returnVal.put("optOutScreen",contactlist.getOptOutScreen());
}

		if(contactlist.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",contactlist.getCreatedByUserLogin());
}

		if(contactlist.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",contactlist.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static ContactList map(Map<String, Object> fields) {

		ContactList returnVal = new ContactList();

		if(fields.get("contactListId") != null) {
			returnVal.setContactListId((String) fields.get("contactListId"));
}

		if(fields.get("contactListTypeId") != null) {
			returnVal.setContactListTypeId((String) fields.get("contactListTypeId"));
}

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("contactListName") != null) {
			returnVal.setContactListName((String) fields.get("contactListName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("isPublic") != null) {
			returnVal.setIsPublic((boolean) fields.get("isPublic"));
}

		if(fields.get("singleUse") != null) {
			returnVal.setSingleUse((boolean) fields.get("singleUse"));
}

		if(fields.get("ownerPartyId") != null) {
			returnVal.setOwnerPartyId((String) fields.get("ownerPartyId"));
}

		if(fields.get("verifyEmailFrom") != null) {
			returnVal.setVerifyEmailFrom((String) fields.get("verifyEmailFrom"));
}

		if(fields.get("verifyEmailScreen") != null) {
			returnVal.setVerifyEmailScreen((String) fields.get("verifyEmailScreen"));
}

		if(fields.get("verifyEmailSubject") != null) {
			returnVal.setVerifyEmailSubject((String) fields.get("verifyEmailSubject"));
}

		if(fields.get("verifyEmailWebSiteId") != null) {
			returnVal.setVerifyEmailWebSiteId((String) fields.get("verifyEmailWebSiteId"));
}

		if(fields.get("optOutScreen") != null) {
			returnVal.setOptOutScreen((String) fields.get("optOutScreen"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static ContactList mapstrstr(Map<String, String> fields) throws Exception {

		ContactList returnVal = new ContactList();

		if(fields.get("contactListId") != null) {
			returnVal.setContactListId((String) fields.get("contactListId"));
}

		if(fields.get("contactListTypeId") != null) {
			returnVal.setContactListTypeId((String) fields.get("contactListTypeId"));
}

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("contactListName") != null) {
			returnVal.setContactListName((String) fields.get("contactListName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("isPublic") != null) {
String buf;
buf = fields.get("isPublic");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsPublic(ibuf);
}

		if(fields.get("singleUse") != null) {
String buf;
buf = fields.get("singleUse");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setSingleUse(ibuf);
}

		if(fields.get("ownerPartyId") != null) {
			returnVal.setOwnerPartyId((String) fields.get("ownerPartyId"));
}

		if(fields.get("verifyEmailFrom") != null) {
			returnVal.setVerifyEmailFrom((String) fields.get("verifyEmailFrom"));
}

		if(fields.get("verifyEmailScreen") != null) {
			returnVal.setVerifyEmailScreen((String) fields.get("verifyEmailScreen"));
}

		if(fields.get("verifyEmailSubject") != null) {
			returnVal.setVerifyEmailSubject((String) fields.get("verifyEmailSubject"));
}

		if(fields.get("verifyEmailWebSiteId") != null) {
			returnVal.setVerifyEmailWebSiteId((String) fields.get("verifyEmailWebSiteId"));
}

		if(fields.get("optOutScreen") != null) {
			returnVal.setOptOutScreen((String) fields.get("optOutScreen"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static ContactList map(GenericValue val) {

ContactList returnVal = new ContactList();
		returnVal.setContactListId(val.getString("contactListId"));
		returnVal.setContactListTypeId(val.getString("contactListTypeId"));
		returnVal.setContactMechTypeId(val.getString("contactMechTypeId"));
		returnVal.setMarketingCampaignId(val.getString("marketingCampaignId"));
		returnVal.setContactListName(val.getString("contactListName"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setIsPublic(val.getBoolean("isPublic"));
		returnVal.setSingleUse(val.getBoolean("singleUse"));
		returnVal.setOwnerPartyId(val.getString("ownerPartyId"));
		returnVal.setVerifyEmailFrom(val.getString("verifyEmailFrom"));
		returnVal.setVerifyEmailScreen(val.getString("verifyEmailScreen"));
		returnVal.setVerifyEmailSubject(val.getString("verifyEmailSubject"));
		returnVal.setVerifyEmailWebSiteId(val.getString("verifyEmailWebSiteId"));
		returnVal.setOptOutScreen(val.getString("optOutScreen"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static ContactList map(HttpServletRequest request) throws Exception {

		ContactList returnVal = new ContactList();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contactListId")) {
returnVal.setContactListId(request.getParameter("contactListId"));
}

		if(paramMap.containsKey("contactListTypeId"))  {
returnVal.setContactListTypeId(request.getParameter("contactListTypeId"));
}
		if(paramMap.containsKey("contactMechTypeId"))  {
returnVal.setContactMechTypeId(request.getParameter("contactMechTypeId"));
}
		if(paramMap.containsKey("marketingCampaignId"))  {
returnVal.setMarketingCampaignId(request.getParameter("marketingCampaignId"));
}
		if(paramMap.containsKey("contactListName"))  {
returnVal.setContactListName(request.getParameter("contactListName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("isPublic"))  {
String buf = request.getParameter("isPublic");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsPublic(ibuf);
}
		if(paramMap.containsKey("singleUse"))  {
String buf = request.getParameter("singleUse");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setSingleUse(ibuf);
}
		if(paramMap.containsKey("ownerPartyId"))  {
returnVal.setOwnerPartyId(request.getParameter("ownerPartyId"));
}
		if(paramMap.containsKey("verifyEmailFrom"))  {
returnVal.setVerifyEmailFrom(request.getParameter("verifyEmailFrom"));
}
		if(paramMap.containsKey("verifyEmailScreen"))  {
returnVal.setVerifyEmailScreen(request.getParameter("verifyEmailScreen"));
}
		if(paramMap.containsKey("verifyEmailSubject"))  {
returnVal.setVerifyEmailSubject(request.getParameter("verifyEmailSubject"));
}
		if(paramMap.containsKey("verifyEmailWebSiteId"))  {
returnVal.setVerifyEmailWebSiteId(request.getParameter("verifyEmailWebSiteId"));
}
		if(paramMap.containsKey("optOutScreen"))  {
returnVal.setOptOutScreen(request.getParameter("optOutScreen"));
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
