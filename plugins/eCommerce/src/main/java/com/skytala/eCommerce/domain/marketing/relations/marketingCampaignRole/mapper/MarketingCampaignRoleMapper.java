package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.model.MarketingCampaignRole;

public class MarketingCampaignRoleMapper  {


	public static Map<String, Object> map(MarketingCampaignRole marketingcampaignrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(marketingcampaignrole.getMarketingCampaignId() != null ){
			returnVal.put("marketingCampaignId",marketingcampaignrole.getMarketingCampaignId());
}

		if(marketingcampaignrole.getPartyId() != null ){
			returnVal.put("partyId",marketingcampaignrole.getPartyId());
}

		if(marketingcampaignrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",marketingcampaignrole.getRoleTypeId());
}

		return returnVal;
}


	public static MarketingCampaignRole map(Map<String, Object> fields) {

		MarketingCampaignRole returnVal = new MarketingCampaignRole();

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static MarketingCampaignRole mapstrstr(Map<String, String> fields) throws Exception {

		MarketingCampaignRole returnVal = new MarketingCampaignRole();

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static MarketingCampaignRole map(GenericValue val) {

MarketingCampaignRole returnVal = new MarketingCampaignRole();
		returnVal.setMarketingCampaignId(val.getString("marketingCampaignId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));


return returnVal;

}

public static MarketingCampaignRole map(HttpServletRequest request) throws Exception {

		MarketingCampaignRole returnVal = new MarketingCampaignRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("marketingCampaignId")) {
returnVal.setMarketingCampaignId(request.getParameter("marketingCampaignId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
return returnVal;

}
}
