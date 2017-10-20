package com.skytala.eCommerce.domain.product.relations.product.mapper.promoCodeEmail;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCodeEmail.ProductPromoCodeEmail;

public class ProductPromoCodeEmailMapper  {


	public static Map<String, Object> map(ProductPromoCodeEmail productpromocodeemail) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpromocodeemail.getProductPromoCodeId() != null ){
			returnVal.put("productPromoCodeId",productpromocodeemail.getProductPromoCodeId());
}

		if(productpromocodeemail.getEmailAddress() != null ){
			returnVal.put("emailAddress",productpromocodeemail.getEmailAddress());
}

		return returnVal;
}


	public static ProductPromoCodeEmail map(Map<String, Object> fields) {

		ProductPromoCodeEmail returnVal = new ProductPromoCodeEmail();

		if(fields.get("productPromoCodeId") != null) {
			returnVal.setProductPromoCodeId((String) fields.get("productPromoCodeId"));
}

		if(fields.get("emailAddress") != null) {
			returnVal.setEmailAddress((String) fields.get("emailAddress"));
}


		return returnVal;
 } 
	public static ProductPromoCodeEmail mapstrstr(Map<String, String> fields) throws Exception {

		ProductPromoCodeEmail returnVal = new ProductPromoCodeEmail();

		if(fields.get("productPromoCodeId") != null) {
			returnVal.setProductPromoCodeId((String) fields.get("productPromoCodeId"));
}

		if(fields.get("emailAddress") != null) {
			returnVal.setEmailAddress((String) fields.get("emailAddress"));
}


		return returnVal;
 } 
	public static ProductPromoCodeEmail map(GenericValue val) {

ProductPromoCodeEmail returnVal = new ProductPromoCodeEmail();
		returnVal.setProductPromoCodeId(val.getString("productPromoCodeId"));
		returnVal.setEmailAddress(val.getString("emailAddress"));


return returnVal;

}

public static ProductPromoCodeEmail map(HttpServletRequest request) throws Exception {

		ProductPromoCodeEmail returnVal = new ProductPromoCodeEmail();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPromoCodeId")) {
returnVal.setProductPromoCodeId(request.getParameter("productPromoCodeId"));
}

		if(paramMap.containsKey("emailAddress"))  {
returnVal.setEmailAddress(request.getParameter("emailAddress"));
}
return returnVal;

}
}
