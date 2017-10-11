package com.skytala.eCommerce.domain.product.relations.productCategoryContent.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productCategoryContent.model.ProductCategoryContent;

public class ProductCategoryContentMapper  {


	public static Map<String, Object> map(ProductCategoryContent productcategorycontent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productcategorycontent.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",productcategorycontent.getProductCategoryId());
}

		if(productcategorycontent.getContentId() != null ){
			returnVal.put("contentId",productcategorycontent.getContentId());
}

		if(productcategorycontent.getProdCatContentTypeId() != null ){
			returnVal.put("prodCatContentTypeId",productcategorycontent.getProdCatContentTypeId());
}

		if(productcategorycontent.getFromDate() != null ){
			returnVal.put("fromDate",productcategorycontent.getFromDate());
}

		if(productcategorycontent.getThruDate() != null ){
			returnVal.put("thruDate",productcategorycontent.getThruDate());
}

		if(productcategorycontent.getPurchaseFromDate() != null ){
			returnVal.put("purchaseFromDate",productcategorycontent.getPurchaseFromDate());
}

		if(productcategorycontent.getPurchaseThruDate() != null ){
			returnVal.put("purchaseThruDate",productcategorycontent.getPurchaseThruDate());
}

		if(productcategorycontent.getUseCountLimit() != null ){
			returnVal.put("useCountLimit",productcategorycontent.getUseCountLimit());
}

		if(productcategorycontent.getUseDaysLimit() != null ){
			returnVal.put("useDaysLimit",productcategorycontent.getUseDaysLimit());
}

		return returnVal;
}


	public static ProductCategoryContent map(Map<String, Object> fields) {

		ProductCategoryContent returnVal = new ProductCategoryContent();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("prodCatContentTypeId") != null) {
			returnVal.setProdCatContentTypeId((String) fields.get("prodCatContentTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("purchaseFromDate") != null) {
			returnVal.setPurchaseFromDate((Timestamp) fields.get("purchaseFromDate"));
}

		if(fields.get("purchaseThruDate") != null) {
			returnVal.setPurchaseThruDate((Timestamp) fields.get("purchaseThruDate"));
}

		if(fields.get("useCountLimit") != null) {
			returnVal.setUseCountLimit((long) fields.get("useCountLimit"));
}

		if(fields.get("useDaysLimit") != null) {
			returnVal.setUseDaysLimit((BigDecimal) fields.get("useDaysLimit"));
}


		return returnVal;
 } 
	public static ProductCategoryContent mapstrstr(Map<String, String> fields) throws Exception {

		ProductCategoryContent returnVal = new ProductCategoryContent();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("prodCatContentTypeId") != null) {
			returnVal.setProdCatContentTypeId((String) fields.get("prodCatContentTypeId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("purchaseFromDate") != null) {
String buf = fields.get("purchaseFromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPurchaseFromDate(ibuf);
}

		if(fields.get("purchaseThruDate") != null) {
String buf = fields.get("purchaseThruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPurchaseThruDate(ibuf);
}

		if(fields.get("useCountLimit") != null) {
String buf;
buf = fields.get("useCountLimit");
long ibuf = Long.parseLong(buf);
			returnVal.setUseCountLimit(ibuf);
}

		if(fields.get("useDaysLimit") != null) {
String buf;
buf = fields.get("useDaysLimit");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUseDaysLimit(bd);
}


		return returnVal;
 } 
	public static ProductCategoryContent map(GenericValue val) {

ProductCategoryContent returnVal = new ProductCategoryContent();
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setProdCatContentTypeId(val.getString("prodCatContentTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setPurchaseFromDate(val.getTimestamp("purchaseFromDate"));
		returnVal.setPurchaseThruDate(val.getTimestamp("purchaseThruDate"));
		returnVal.setUseCountLimit(val.getLong("useCountLimit"));
		returnVal.setUseDaysLimit(val.getBigDecimal("useDaysLimit"));


return returnVal;

}

public static ProductCategoryContent map(HttpServletRequest request) throws Exception {

		ProductCategoryContent returnVal = new ProductCategoryContent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productCategoryId")) {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}

		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
}
		if(paramMap.containsKey("prodCatContentTypeId"))  {
returnVal.setProdCatContentTypeId(request.getParameter("prodCatContentTypeId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("purchaseFromDate"))  {
String buf = request.getParameter("purchaseFromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setPurchaseFromDate(ibuf);
}
		if(paramMap.containsKey("purchaseThruDate"))  {
String buf = request.getParameter("purchaseThruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setPurchaseThruDate(ibuf);
}
		if(paramMap.containsKey("useCountLimit"))  {
String buf = request.getParameter("useCountLimit");
Long ibuf = Long.parseLong(buf);
returnVal.setUseCountLimit(ibuf);
}
		if(paramMap.containsKey("useDaysLimit"))  {
String buf = request.getParameter("useDaysLimit");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUseDaysLimit(bd);
}
return returnVal;

}
}
