package com.skytala.eCommerce.domain.marketing.relations.salesForecast.mapper.detail;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.detail.SalesForecastDetail;

public class SalesForecastDetailMapper  {


	public static Map<String, Object> map(SalesForecastDetail salesforecastdetail) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(salesforecastdetail.getSalesForecastId() != null ){
			returnVal.put("salesForecastId",salesforecastdetail.getSalesForecastId());
}

		if(salesforecastdetail.getSalesForecastDetailId() != null ){
			returnVal.put("salesForecastDetailId",salesforecastdetail.getSalesForecastDetailId());
}

		if(salesforecastdetail.getAmount() != null ){
			returnVal.put("amount",salesforecastdetail.getAmount());
}

		if(salesforecastdetail.getQuantityUomId() != null ){
			returnVal.put("quantityUomId",salesforecastdetail.getQuantityUomId());
}

		if(salesforecastdetail.getQuantity() != null ){
			returnVal.put("quantity",salesforecastdetail.getQuantity());
}

		if(salesforecastdetail.getProductId() != null ){
			returnVal.put("productId",salesforecastdetail.getProductId());
}

		if(salesforecastdetail.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",salesforecastdetail.getProductCategoryId());
}

		return returnVal;
}


	public static SalesForecastDetail map(Map<String, Object> fields) {

		SalesForecastDetail returnVal = new SalesForecastDetail();

		if(fields.get("salesForecastId") != null) {
			returnVal.setSalesForecastId((String) fields.get("salesForecastId"));
}

		if(fields.get("salesForecastDetailId") != null) {
			returnVal.setSalesForecastDetailId((String) fields.get("salesForecastDetailId"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}

		if(fields.get("quantityUomId") != null) {
			returnVal.setQuantityUomId((String) fields.get("quantityUomId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}


		return returnVal;
 } 
	public static SalesForecastDetail mapstrstr(Map<String, String> fields) throws Exception {

		SalesForecastDetail returnVal = new SalesForecastDetail();

		if(fields.get("salesForecastId") != null) {
			returnVal.setSalesForecastId((String) fields.get("salesForecastId"));
}

		if(fields.get("salesForecastDetailId") != null) {
			returnVal.setSalesForecastDetailId((String) fields.get("salesForecastDetailId"));
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}

		if(fields.get("quantityUomId") != null) {
			returnVal.setQuantityUomId((String) fields.get("quantityUomId"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}


		return returnVal;
 } 
	public static SalesForecastDetail map(GenericValue val) {

SalesForecastDetail returnVal = new SalesForecastDetail();
		returnVal.setSalesForecastId(val.getString("salesForecastId"));
		returnVal.setSalesForecastDetailId(val.getString("salesForecastDetailId"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setQuantityUomId(val.getString("quantityUomId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductCategoryId(val.getString("productCategoryId"));


return returnVal;

}

public static SalesForecastDetail map(HttpServletRequest request) throws Exception {

		SalesForecastDetail returnVal = new SalesForecastDetail();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("salesForecastId")) {
returnVal.setSalesForecastId(request.getParameter("salesForecastId"));
}

		if(paramMap.containsKey("salesForecastDetailId"))  {
returnVal.setSalesForecastDetailId(request.getParameter("salesForecastDetailId"));
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
		if(paramMap.containsKey("quantityUomId"))  {
returnVal.setQuantityUomId(request.getParameter("quantityUomId"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("productCategoryId"))  {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}
return returnVal;

}
}
