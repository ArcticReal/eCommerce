package com.skytala.eCommerce.domain.product.relations.costComponent.mapper.productCalc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.productCalc.ProductCostComponentCalc;

public class ProductCostComponentCalcMapper  {


	public static Map<String, Object> map(ProductCostComponentCalc productcostcomponentcalc) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productcostcomponentcalc.getProductId() != null ){
			returnVal.put("productId",productcostcomponentcalc.getProductId());
}

		if(productcostcomponentcalc.getCostComponentTypeId() != null ){
			returnVal.put("costComponentTypeId",productcostcomponentcalc.getCostComponentTypeId());
}

		if(productcostcomponentcalc.getCostComponentCalcId() != null ){
			returnVal.put("costComponentCalcId",productcostcomponentcalc.getCostComponentCalcId());
}

		if(productcostcomponentcalc.getFromDate() != null ){
			returnVal.put("fromDate",productcostcomponentcalc.getFromDate());
}

		if(productcostcomponentcalc.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productcostcomponentcalc.getSequenceNum());
}

		if(productcostcomponentcalc.getThruDate() != null ){
			returnVal.put("thruDate",productcostcomponentcalc.getThruDate());
}

		return returnVal;
}


	public static ProductCostComponentCalc map(Map<String, Object> fields) {

		ProductCostComponentCalc returnVal = new ProductCostComponentCalc();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("costComponentTypeId") != null) {
			returnVal.setCostComponentTypeId((String) fields.get("costComponentTypeId"));
}

		if(fields.get("costComponentCalcId") != null) {
			returnVal.setCostComponentCalcId((String) fields.get("costComponentCalcId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static ProductCostComponentCalc mapstrstr(Map<String, String> fields) throws Exception {

		ProductCostComponentCalc returnVal = new ProductCostComponentCalc();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("costComponentTypeId") != null) {
			returnVal.setCostComponentTypeId((String) fields.get("costComponentTypeId"));
}

		if(fields.get("costComponentCalcId") != null) {
			returnVal.setCostComponentCalcId((String) fields.get("costComponentCalcId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}


		return returnVal;
 } 
	public static ProductCostComponentCalc map(GenericValue val) {

ProductCostComponentCalc returnVal = new ProductCostComponentCalc();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setCostComponentTypeId(val.getString("costComponentTypeId"));
		returnVal.setCostComponentCalcId(val.getString("costComponentCalcId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static ProductCostComponentCalc map(HttpServletRequest request) throws Exception {

		ProductCostComponentCalc returnVal = new ProductCostComponentCalc();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("costComponentTypeId"))  {
returnVal.setCostComponentTypeId(request.getParameter("costComponentTypeId"));
}
		if(paramMap.containsKey("costComponentCalcId"))  {
returnVal.setCostComponentCalcId(request.getParameter("costComponentCalcId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
return returnVal;

}
}
