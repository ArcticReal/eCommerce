package com.skytala.eCommerce.domain.productPricePurpose.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productPricePurpose.model.ProductPricePurpose;

public class ProductPricePurposeMapper  {


	public static Map<String, Object> map(ProductPricePurpose productpricepurpose) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpricepurpose.getProductPricePurposeId() != null ){
			returnVal.put("productPricePurposeId",productpricepurpose.getProductPricePurposeId());
}

		if(productpricepurpose.getDescription() != null ){
			returnVal.put("description",productpricepurpose.getDescription());
}

		return returnVal;
}


	public static ProductPricePurpose map(Map<String, Object> fields) {

		ProductPricePurpose returnVal = new ProductPricePurpose();

		if(fields.get("productPricePurposeId") != null) {
			returnVal.setProductPricePurposeId((String) fields.get("productPricePurposeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductPricePurpose mapstrstr(Map<String, String> fields) throws Exception {

		ProductPricePurpose returnVal = new ProductPricePurpose();

		if(fields.get("productPricePurposeId") != null) {
			returnVal.setProductPricePurposeId((String) fields.get("productPricePurposeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductPricePurpose map(GenericValue val) {

ProductPricePurpose returnVal = new ProductPricePurpose();
		returnVal.setProductPricePurposeId(val.getString("productPricePurposeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductPricePurpose map(HttpServletRequest request) throws Exception {

		ProductPricePurpose returnVal = new ProductPricePurpose();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPricePurposeId")) {
returnVal.setProductPricePurposeId(request.getParameter("productPricePurposeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
