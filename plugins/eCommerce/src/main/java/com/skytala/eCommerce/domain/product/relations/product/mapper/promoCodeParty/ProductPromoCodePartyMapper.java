package com.skytala.eCommerce.domain.product.relations.product.mapper.promoCodeParty;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCodeParty.ProductPromoCodeParty;

public class ProductPromoCodePartyMapper  {


	public static Map<String, Object> map(ProductPromoCodeParty productpromocodeparty) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpromocodeparty.getProductPromoCodeId() != null ){
			returnVal.put("productPromoCodeId",productpromocodeparty.getProductPromoCodeId());
}

		if(productpromocodeparty.getPartyId() != null ){
			returnVal.put("partyId",productpromocodeparty.getPartyId());
}

		return returnVal;
}


	public static ProductPromoCodeParty map(Map<String, Object> fields) {

		ProductPromoCodeParty returnVal = new ProductPromoCodeParty();

		if(fields.get("productPromoCodeId") != null) {
			returnVal.setProductPromoCodeId((String) fields.get("productPromoCodeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}


		return returnVal;
 } 
	public static ProductPromoCodeParty mapstrstr(Map<String, String> fields) throws Exception {

		ProductPromoCodeParty returnVal = new ProductPromoCodeParty();

		if(fields.get("productPromoCodeId") != null) {
			returnVal.setProductPromoCodeId((String) fields.get("productPromoCodeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}


		return returnVal;
 } 
	public static ProductPromoCodeParty map(GenericValue val) {

ProductPromoCodeParty returnVal = new ProductPromoCodeParty();
		returnVal.setProductPromoCodeId(val.getString("productPromoCodeId"));
		returnVal.setPartyId(val.getString("partyId"));


return returnVal;

}

public static ProductPromoCodeParty map(HttpServletRequest request) throws Exception {

		ProductPromoCodeParty returnVal = new ProductPromoCodeParty();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPromoCodeId")) {
returnVal.setProductPromoCodeId(request.getParameter("productPromoCodeId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
return returnVal;

}
}
