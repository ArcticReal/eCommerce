package com.skytala.eCommerce.domain.product.relations.product.mapper.calculatedInfo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.calculatedInfo.ProductCalculatedInfo;

public class ProductCalculatedInfoMapper  {


	public static Map<String, Object> map(ProductCalculatedInfo productcalculatedinfo) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productcalculatedinfo.getProductId() != null ){
			returnVal.put("productId",productcalculatedinfo.getProductId());
}

		if(productcalculatedinfo.getTotalQuantityOrdered() != null ){
			returnVal.put("totalQuantityOrdered",productcalculatedinfo.getTotalQuantityOrdered());
}

		if(productcalculatedinfo.getTotalTimesViewed() != null ){
			returnVal.put("totalTimesViewed",productcalculatedinfo.getTotalTimesViewed());
}

		if(productcalculatedinfo.getAverageCustomerRating() != null ){
			returnVal.put("averageCustomerRating",productcalculatedinfo.getAverageCustomerRating());
}

		return returnVal;
}


	public static ProductCalculatedInfo map(Map<String, Object> fields) {

		ProductCalculatedInfo returnVal = new ProductCalculatedInfo();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("totalQuantityOrdered") != null) {
			returnVal.setTotalQuantityOrdered((BigDecimal) fields.get("totalQuantityOrdered"));
}

		if(fields.get("totalTimesViewed") != null) {
			returnVal.setTotalTimesViewed((long) fields.get("totalTimesViewed"));
}

		if(fields.get("averageCustomerRating") != null) {
			returnVal.setAverageCustomerRating((BigDecimal) fields.get("averageCustomerRating"));
}


		return returnVal;
 } 
	public static ProductCalculatedInfo mapstrstr(Map<String, String> fields) throws Exception {

		ProductCalculatedInfo returnVal = new ProductCalculatedInfo();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("totalQuantityOrdered") != null) {
String buf;
buf = fields.get("totalQuantityOrdered");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalQuantityOrdered(bd);
}

		if(fields.get("totalTimesViewed") != null) {
String buf;
buf = fields.get("totalTimesViewed");
long ibuf = Long.parseLong(buf);
			returnVal.setTotalTimesViewed(ibuf);
}

		if(fields.get("averageCustomerRating") != null) {
String buf;
buf = fields.get("averageCustomerRating");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAverageCustomerRating(bd);
}


		return returnVal;
 } 
	public static ProductCalculatedInfo map(GenericValue val) {

ProductCalculatedInfo returnVal = new ProductCalculatedInfo();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setTotalQuantityOrdered(val.getBigDecimal("totalQuantityOrdered"));
		returnVal.setTotalTimesViewed(val.getLong("totalTimesViewed"));
		returnVal.setAverageCustomerRating(val.getBigDecimal("averageCustomerRating"));


return returnVal;

}

public static ProductCalculatedInfo map(HttpServletRequest request) throws Exception {

		ProductCalculatedInfo returnVal = new ProductCalculatedInfo();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("totalQuantityOrdered"))  {
String buf = request.getParameter("totalQuantityOrdered");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalQuantityOrdered(bd);
}
		if(paramMap.containsKey("totalTimesViewed"))  {
String buf = request.getParameter("totalTimesViewed");
Long ibuf = Long.parseLong(buf);
returnVal.setTotalTimesViewed(ibuf);
}
		if(paramMap.containsKey("averageCustomerRating"))  {
String buf = request.getParameter("averageCustomerRating");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAverageCustomerRating(bd);
}
return returnVal;

}
}
