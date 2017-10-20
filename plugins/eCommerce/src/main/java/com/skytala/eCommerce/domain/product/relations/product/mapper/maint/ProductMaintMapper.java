package com.skytala.eCommerce.domain.product.relations.product.mapper.maint;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.maint.ProductMaint;

public class ProductMaintMapper  {


	public static Map<String, Object> map(ProductMaint productmaint) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productmaint.getProductId() != null ){
			returnVal.put("productId",productmaint.getProductId());
}

		if(productmaint.getProductMaintSeqId() != null ){
			returnVal.put("productMaintSeqId",productmaint.getProductMaintSeqId());
}

		if(productmaint.getProductMaintTypeId() != null ){
			returnVal.put("productMaintTypeId",productmaint.getProductMaintTypeId());
}

		if(productmaint.getMaintName() != null ){
			returnVal.put("maintName",productmaint.getMaintName());
}

		if(productmaint.getMaintTemplateWorkEffortId() != null ){
			returnVal.put("maintTemplateWorkEffortId",productmaint.getMaintTemplateWorkEffortId());
}

		if(productmaint.getIntervalQuantity() != null ){
			returnVal.put("intervalQuantity",productmaint.getIntervalQuantity());
}

		if(productmaint.getIntervalUomId() != null ){
			returnVal.put("intervalUomId",productmaint.getIntervalUomId());
}

		if(productmaint.getIntervalMeterTypeId() != null ){
			returnVal.put("intervalMeterTypeId",productmaint.getIntervalMeterTypeId());
}

		if(productmaint.getRepeatCount() != null ){
			returnVal.put("repeatCount",productmaint.getRepeatCount());
}

		return returnVal;
}


	public static ProductMaint map(Map<String, Object> fields) {

		ProductMaint returnVal = new ProductMaint();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productMaintSeqId") != null) {
			returnVal.setProductMaintSeqId((String) fields.get("productMaintSeqId"));
}

		if(fields.get("productMaintTypeId") != null) {
			returnVal.setProductMaintTypeId((String) fields.get("productMaintTypeId"));
}

		if(fields.get("maintName") != null) {
			returnVal.setMaintName((String) fields.get("maintName"));
}

		if(fields.get("maintTemplateWorkEffortId") != null) {
			returnVal.setMaintTemplateWorkEffortId((String) fields.get("maintTemplateWorkEffortId"));
}

		if(fields.get("intervalQuantity") != null) {
			returnVal.setIntervalQuantity((BigDecimal) fields.get("intervalQuantity"));
}

		if(fields.get("intervalUomId") != null) {
			returnVal.setIntervalUomId((String) fields.get("intervalUomId"));
}

		if(fields.get("intervalMeterTypeId") != null) {
			returnVal.setIntervalMeterTypeId((String) fields.get("intervalMeterTypeId"));
}

		if(fields.get("repeatCount") != null) {
			returnVal.setRepeatCount((long) fields.get("repeatCount"));
}


		return returnVal;
 } 
	public static ProductMaint mapstrstr(Map<String, String> fields) throws Exception {

		ProductMaint returnVal = new ProductMaint();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productMaintSeqId") != null) {
			returnVal.setProductMaintSeqId((String) fields.get("productMaintSeqId"));
}

		if(fields.get("productMaintTypeId") != null) {
			returnVal.setProductMaintTypeId((String) fields.get("productMaintTypeId"));
}

		if(fields.get("maintName") != null) {
			returnVal.setMaintName((String) fields.get("maintName"));
}

		if(fields.get("maintTemplateWorkEffortId") != null) {
			returnVal.setMaintTemplateWorkEffortId((String) fields.get("maintTemplateWorkEffortId"));
}

		if(fields.get("intervalQuantity") != null) {
String buf;
buf = fields.get("intervalQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setIntervalQuantity(bd);
}

		if(fields.get("intervalUomId") != null) {
			returnVal.setIntervalUomId((String) fields.get("intervalUomId"));
}

		if(fields.get("intervalMeterTypeId") != null) {
			returnVal.setIntervalMeterTypeId((String) fields.get("intervalMeterTypeId"));
}

		if(fields.get("repeatCount") != null) {
String buf;
buf = fields.get("repeatCount");
long ibuf = Long.parseLong(buf);
			returnVal.setRepeatCount(ibuf);
}


		return returnVal;
 } 
	public static ProductMaint map(GenericValue val) {

ProductMaint returnVal = new ProductMaint();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductMaintSeqId(val.getString("productMaintSeqId"));
		returnVal.setProductMaintTypeId(val.getString("productMaintTypeId"));
		returnVal.setMaintName(val.getString("maintName"));
		returnVal.setMaintTemplateWorkEffortId(val.getString("maintTemplateWorkEffortId"));
		returnVal.setIntervalQuantity(val.getBigDecimal("intervalQuantity"));
		returnVal.setIntervalUomId(val.getString("intervalUomId"));
		returnVal.setIntervalMeterTypeId(val.getString("intervalMeterTypeId"));
		returnVal.setRepeatCount(val.getLong("repeatCount"));


return returnVal;

}

public static ProductMaint map(HttpServletRequest request) throws Exception {

		ProductMaint returnVal = new ProductMaint();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("productMaintSeqId"))  {
returnVal.setProductMaintSeqId(request.getParameter("productMaintSeqId"));
}
		if(paramMap.containsKey("productMaintTypeId"))  {
returnVal.setProductMaintTypeId(request.getParameter("productMaintTypeId"));
}
		if(paramMap.containsKey("maintName"))  {
returnVal.setMaintName(request.getParameter("maintName"));
}
		if(paramMap.containsKey("maintTemplateWorkEffortId"))  {
returnVal.setMaintTemplateWorkEffortId(request.getParameter("maintTemplateWorkEffortId"));
}
		if(paramMap.containsKey("intervalQuantity"))  {
String buf = request.getParameter("intervalQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setIntervalQuantity(bd);
}
		if(paramMap.containsKey("intervalUomId"))  {
returnVal.setIntervalUomId(request.getParameter("intervalUomId"));
}
		if(paramMap.containsKey("intervalMeterTypeId"))  {
returnVal.setIntervalMeterTypeId(request.getParameter("intervalMeterTypeId"));
}
		if(paramMap.containsKey("repeatCount"))  {
String buf = request.getParameter("repeatCount");
Long ibuf = Long.parseLong(buf);
returnVal.setRepeatCount(ibuf);
}
return returnVal;

}
}
