package com.skytala.eCommerce.domain.productFeature.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productFeature.model.ProductFeature;

public class ProductFeatureMapper  {


	public static Map<String, Object> map(ProductFeature productfeature) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfeature.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",productfeature.getProductFeatureId());
}

		if(productfeature.getProductFeatureTypeId() != null ){
			returnVal.put("productFeatureTypeId",productfeature.getProductFeatureTypeId());
}

		if(productfeature.getProductFeatureCategoryId() != null ){
			returnVal.put("productFeatureCategoryId",productfeature.getProductFeatureCategoryId());
}

		if(productfeature.getDescription() != null ){
			returnVal.put("description",productfeature.getDescription());
}

		if(productfeature.getUomId() != null ){
			returnVal.put("uomId",productfeature.getUomId());
}

		if(productfeature.getNumberSpecified() != null ){
			returnVal.put("numberSpecified",productfeature.getNumberSpecified());
}

		if(productfeature.getDefaultAmount() != null ){
			returnVal.put("defaultAmount",productfeature.getDefaultAmount());
}

		if(productfeature.getDefaultSequenceNum() != null ){
			returnVal.put("defaultSequenceNum",productfeature.getDefaultSequenceNum());
}

		if(productfeature.getAbbrev() != null ){
			returnVal.put("abbrev",productfeature.getAbbrev());
}

		if(productfeature.getIdCode() != null ){
			returnVal.put("idCode",productfeature.getIdCode());
}

		return returnVal;
}


	public static ProductFeature map(Map<String, Object> fields) {

		ProductFeature returnVal = new ProductFeature();

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("productFeatureTypeId") != null) {
			returnVal.setProductFeatureTypeId((String) fields.get("productFeatureTypeId"));
}

		if(fields.get("productFeatureCategoryId") != null) {
			returnVal.setProductFeatureCategoryId((String) fields.get("productFeatureCategoryId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}

		if(fields.get("numberSpecified") != null) {
			returnVal.setNumberSpecified((BigDecimal) fields.get("numberSpecified"));
}

		if(fields.get("defaultAmount") != null) {
			returnVal.setDefaultAmount((BigDecimal) fields.get("defaultAmount"));
}

		if(fields.get("defaultSequenceNum") != null) {
			returnVal.setDefaultSequenceNum((long) fields.get("defaultSequenceNum"));
}

		if(fields.get("abbrev") != null) {
			returnVal.setAbbrev((String) fields.get("abbrev"));
}

		if(fields.get("idCode") != null) {
			returnVal.setIdCode((String) fields.get("idCode"));
}


		return returnVal;
 } 
	public static ProductFeature mapstrstr(Map<String, String> fields) throws Exception {

		ProductFeature returnVal = new ProductFeature();

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("productFeatureTypeId") != null) {
			returnVal.setProductFeatureTypeId((String) fields.get("productFeatureTypeId"));
}

		if(fields.get("productFeatureCategoryId") != null) {
			returnVal.setProductFeatureCategoryId((String) fields.get("productFeatureCategoryId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}

		if(fields.get("numberSpecified") != null) {
String buf;
buf = fields.get("numberSpecified");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setNumberSpecified(bd);
}

		if(fields.get("defaultAmount") != null) {
String buf;
buf = fields.get("defaultAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setDefaultAmount(bd);
}

		if(fields.get("defaultSequenceNum") != null) {
String buf;
buf = fields.get("defaultSequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setDefaultSequenceNum(ibuf);
}

		if(fields.get("abbrev") != null) {
			returnVal.setAbbrev((String) fields.get("abbrev"));
}

		if(fields.get("idCode") != null) {
			returnVal.setIdCode((String) fields.get("idCode"));
}


		return returnVal;
 } 
	public static ProductFeature map(GenericValue val) {

ProductFeature returnVal = new ProductFeature();
		returnVal.setProductFeatureId(val.getString("productFeatureId"));
		returnVal.setProductFeatureTypeId(val.getString("productFeatureTypeId"));
		returnVal.setProductFeatureCategoryId(val.getString("productFeatureCategoryId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setUomId(val.getString("uomId"));
		returnVal.setNumberSpecified(val.getBigDecimal("numberSpecified"));
		returnVal.setDefaultAmount(val.getBigDecimal("defaultAmount"));
		returnVal.setDefaultSequenceNum(val.getLong("defaultSequenceNum"));
		returnVal.setAbbrev(val.getString("abbrev"));
		returnVal.setIdCode(val.getString("idCode"));


return returnVal;

}

public static ProductFeature map(HttpServletRequest request) throws Exception {

		ProductFeature returnVal = new ProductFeature();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productFeatureId")) {
returnVal.setProductFeatureId(request.getParameter("productFeatureId"));
}

		if(paramMap.containsKey("productFeatureTypeId"))  {
returnVal.setProductFeatureTypeId(request.getParameter("productFeatureTypeId"));
}
		if(paramMap.containsKey("productFeatureCategoryId"))  {
returnVal.setProductFeatureCategoryId(request.getParameter("productFeatureCategoryId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("uomId"))  {
returnVal.setUomId(request.getParameter("uomId"));
}
		if(paramMap.containsKey("numberSpecified"))  {
String buf = request.getParameter("numberSpecified");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setNumberSpecified(bd);
}
		if(paramMap.containsKey("defaultAmount"))  {
String buf = request.getParameter("defaultAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setDefaultAmount(bd);
}
		if(paramMap.containsKey("defaultSequenceNum"))  {
String buf = request.getParameter("defaultSequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setDefaultSequenceNum(ibuf);
}
		if(paramMap.containsKey("abbrev"))  {
returnVal.setAbbrev(request.getParameter("abbrev"));
}
		if(paramMap.containsKey("idCode"))  {
returnVal.setIdCode(request.getParameter("idCode"));
}
return returnVal;

}
}
