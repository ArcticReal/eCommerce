package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.mapper.price;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.price.MarketingCampaignPrice;

public class MarketingCampaignPriceMapper  {


	public static Map<String, Object> map(MarketingCampaignPrice marketingcampaignprice) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(marketingcampaignprice.getMarketingCampaignId() != null ){
			returnVal.put("marketingCampaignId",marketingcampaignprice.getMarketingCampaignId());
}

		if(marketingcampaignprice.getProductPriceRuleId() != null ){
			returnVal.put("productPriceRuleId",marketingcampaignprice.getProductPriceRuleId());
}

		return returnVal;
}


	public static MarketingCampaignPrice map(Map<String, Object> fields) {

		MarketingCampaignPrice returnVal = new MarketingCampaignPrice();

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("productPriceRuleId") != null) {
			returnVal.setProductPriceRuleId((String) fields.get("productPriceRuleId"));
}


		return returnVal;
 } 
	public static MarketingCampaignPrice mapstrstr(Map<String, String> fields) throws Exception {

		MarketingCampaignPrice returnVal = new MarketingCampaignPrice();

		if(fields.get("marketingCampaignId") != null) {
			returnVal.setMarketingCampaignId((String) fields.get("marketingCampaignId"));
}

		if(fields.get("productPriceRuleId") != null) {
			returnVal.setProductPriceRuleId((String) fields.get("productPriceRuleId"));
}


		return returnVal;
 } 
	public static MarketingCampaignPrice map(GenericValue val) {

MarketingCampaignPrice returnVal = new MarketingCampaignPrice();
		returnVal.setMarketingCampaignId(val.getString("marketingCampaignId"));
		returnVal.setProductPriceRuleId(val.getString("productPriceRuleId"));


return returnVal;

}

public static MarketingCampaignPrice map(HttpServletRequest request) throws Exception {

		MarketingCampaignPrice returnVal = new MarketingCampaignPrice();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("marketingCampaignId")) {
returnVal.setMarketingCampaignId(request.getParameter("marketingCampaignId"));
}

		if(paramMap.containsKey("productPriceRuleId"))  {
returnVal.setProductPriceRuleId(request.getParameter("productPriceRuleId"));
}
return returnVal;

}
}
