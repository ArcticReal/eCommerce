package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.model.MarketingCampaignPromo;

public class MarketingCampaignPromoMapper  {


	public static Map<String, Object> map(MarketingCampaignPromo marketingcampaignpromo) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(marketingcampaignpromo.getMarketingCampaignId() != null ){
			returnVal.put("marketingCampaignId",marketingcampaignpromo.getMarketingCampaignId());
}

		if(marketingcampaignpromo.getProductPromoId() != null ){
			returnVal.put("productPromoId",marketingcampaignpromo.getProductPromoId());
}

		return returnVal;
}


	public static MarketingCampaignPromo map(Map<String, Object> fields) {

		MarketingCampaignPromo returnVal = new MarketingCampaignPromo();

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}


		return returnVal;
 } 
	public static MarketingCampaignPromo mapstrstr(Map<String, String> fields) throws Exception {

		MarketingCampaignPromo returnVal = new MarketingCampaignPromo();

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}


		return returnVal;
 } 
	public static MarketingCampaignPromo map(GenericValue val) {

MarketingCampaignPromo returnVal = new MarketingCampaignPromo();
		returnVal.setMarketingCampaignId(val.getString("marketingCampaignId"));
		returnVal.setProductPromoId(val.getString("productPromoId"));


return returnVal;

}

public static MarketingCampaignPromo map(HttpServletRequest request) throws Exception {

		MarketingCampaignPromo returnVal = new MarketingCampaignPromo();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("marketingCampaignId")) {
returnVal.setMarketingCampaignId(request.getParameter("marketingCampaignId"));
}

		if(paramMap.containsKey("productPromoId"))  {
returnVal.setProductPromoId(request.getParameter("productPromoId"));
}
return returnVal;

}
}
