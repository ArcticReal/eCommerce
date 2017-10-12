package com.skytala.eCommerce.domain.accounting.relations.fixedAssetProduct.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProduct.model.FixedAssetProduct;

public class FixedAssetProductMapper  {


	public static Map<String, Object> map(FixedAssetProduct fixedassetproduct) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassetproduct.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",fixedassetproduct.getFixedAssetId());
}

		if(fixedassetproduct.getProductId() != null ){
			returnVal.put("productId",fixedassetproduct.getProductId());
}

		if(fixedassetproduct.getFixedAssetProductTypeId() != null ){
			returnVal.put("fixedAssetProductTypeId",fixedassetproduct.getFixedAssetProductTypeId());
}

		if(fixedassetproduct.getFromDate() != null ){
			returnVal.put("fromDate",fixedassetproduct.getFromDate());
}

		if(fixedassetproduct.getThruDate() != null ){
			returnVal.put("thruDate",fixedassetproduct.getThruDate());
}

		if(fixedassetproduct.getComments() != null ){
			returnVal.put("comments",fixedassetproduct.getComments());
}

		if(fixedassetproduct.getSequenceNum() != null ){
			returnVal.put("sequenceNum",fixedassetproduct.getSequenceNum());
}

		if(fixedassetproduct.getQuantity() != null ){
			returnVal.put("quantity",fixedassetproduct.getQuantity());
}

		if(fixedassetproduct.getQuantityUomId() != null ){
			returnVal.put("quantityUomId",fixedassetproduct.getQuantityUomId());
}

		return returnVal;
}


	public static FixedAssetProduct map(Map<String, Object> fields) {

		FixedAssetProduct returnVal = new FixedAssetProduct();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("fixedAssetProductTypeId") != null) {
			returnVal.setFixedAssetProductTypeId((String) fields.get("fixedAssetProductTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("quantityUomId") != null) {
			returnVal.setQuantityUomId((String) fields.get("quantityUomId"));
}


		return returnVal;
 } 
	public static FixedAssetProduct mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetProduct returnVal = new FixedAssetProduct();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("fixedAssetProductTypeId") != null) {
			returnVal.setFixedAssetProductTypeId((String) fields.get("fixedAssetProductTypeId"));
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

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("quantityUomId") != null) {
			returnVal.setQuantityUomId((String) fields.get("quantityUomId"));
}


		return returnVal;
 } 
	public static FixedAssetProduct map(GenericValue val) {

FixedAssetProduct returnVal = new FixedAssetProduct();
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setFixedAssetProductTypeId(val.getString("fixedAssetProductTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setQuantityUomId(val.getString("quantityUomId"));


return returnVal;

}

public static FixedAssetProduct map(HttpServletRequest request) throws Exception {

		FixedAssetProduct returnVal = new FixedAssetProduct();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetId")) {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}

		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("fixedAssetProductTypeId"))  {
returnVal.setFixedAssetProductTypeId(request.getParameter("fixedAssetProductTypeId"));
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
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("quantityUomId"))  {
returnVal.setQuantityUomId(request.getParameter("quantityUomId"));
}
return returnVal;

}
}
