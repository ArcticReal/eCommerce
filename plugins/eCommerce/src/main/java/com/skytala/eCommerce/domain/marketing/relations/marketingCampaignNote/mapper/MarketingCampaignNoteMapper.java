package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignNote.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignNote.model.MarketingCampaignNote;

public class MarketingCampaignNoteMapper  {


	public static Map<String, Object> map(MarketingCampaignNote marketingcampaignnote) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(marketingcampaignnote.getMarketingCampaignId() != null ){
			returnVal.put("marketingCampaignId",marketingcampaignnote.getMarketingCampaignId());
}

		if(marketingcampaignnote.getNoteId() != null ){
			returnVal.put("noteId",marketingcampaignnote.getNoteId());
}

		return returnVal;
}


	public static MarketingCampaignNote map(Map<String, Object> fields) {

		MarketingCampaignNote returnVal = new MarketingCampaignNote();

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}


		return returnVal;
 } 
	public static MarketingCampaignNote mapstrstr(Map<String, String> fields) throws Exception {

		MarketingCampaignNote returnVal = new MarketingCampaignNote();

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}


		return returnVal;
 } 
	public static MarketingCampaignNote map(GenericValue val) {

MarketingCampaignNote returnVal = new MarketingCampaignNote();
		returnVal.setMarketingCampaignId(val.getString("marketingCampaignId"));
		returnVal.setNoteId(val.getString("noteId"));


return returnVal;

}

public static MarketingCampaignNote map(HttpServletRequest request) throws Exception {

		MarketingCampaignNote returnVal = new MarketingCampaignNote();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("marketingCampaignId")) {
returnVal.setMarketingCampaignId(request.getParameter("marketingCampaignId"));
}

		if(paramMap.containsKey("noteId"))  {
returnVal.setNoteId(request.getParameter("noteId"));
}
return returnVal;

}
}
