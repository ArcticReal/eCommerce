package com.skytala.eCommerce.domain.product.relations.costComponent.mapper.calc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.calc.CostComponentCalc;

public class CostComponentCalcMapper  {


	public static Map<String, Object> map(CostComponentCalc costcomponentcalc) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(costcomponentcalc.getCostComponentCalcId() != null ){
			returnVal.put("costComponentCalcId",costcomponentcalc.getCostComponentCalcId());
}

		if(costcomponentcalc.getDescription() != null ){
			returnVal.put("description",costcomponentcalc.getDescription());
}

		if(costcomponentcalc.getCostGlAccountTypeId() != null ){
			returnVal.put("costGlAccountTypeId",costcomponentcalc.getCostGlAccountTypeId());
}

		if(costcomponentcalc.getOffsettingGlAccountTypeId() != null ){
			returnVal.put("offsettingGlAccountTypeId",costcomponentcalc.getOffsettingGlAccountTypeId());
}

		if(costcomponentcalc.getFixedCost() != null ){
			returnVal.put("fixedCost",costcomponentcalc.getFixedCost());
}

		if(costcomponentcalc.getVariableCost() != null ){
			returnVal.put("variableCost",costcomponentcalc.getVariableCost());
}

		if(costcomponentcalc.getPerMilliSecond() != null ){
			returnVal.put("perMilliSecond",costcomponentcalc.getPerMilliSecond());
}

		if(costcomponentcalc.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",costcomponentcalc.getCurrencyUomId());
}

		if(costcomponentcalc.getCostCustomMethodId() != null ){
			returnVal.put("costCustomMethodId",costcomponentcalc.getCostCustomMethodId());
}

		return returnVal;
}


	public static CostComponentCalc map(Map<String, Object> fields) {

		CostComponentCalc returnVal = new CostComponentCalc();

		if(fields.get("costComponentCalcId") != null) {
			returnVal.setCostComponentCalcId((String) fields.get("costComponentCalcId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("costGlAccountTypeId") != null) {
			returnVal.setCostGlAccountTypeId((String) fields.get("costGlAccountTypeId"));
}

		if(fields.get("offsettingGlAccountTypeId") != null) {
			returnVal.setOffsettingGlAccountTypeId((String) fields.get("offsettingGlAccountTypeId"));
}

		if(fields.get("fixedCost") != null) {
			returnVal.setFixedCost((BigDecimal) fields.get("fixedCost"));
}

		if(fields.get("variableCost") != null) {
			returnVal.setVariableCost((BigDecimal) fields.get("variableCost"));
}

		if(fields.get("perMilliSecond") != null) {
			returnVal.setPerMilliSecond((long) fields.get("perMilliSecond"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("costCustomMethodId") != null) {
			returnVal.setCostCustomMethodId((String) fields.get("costCustomMethodId"));
}


		return returnVal;
 } 
	public static CostComponentCalc mapstrstr(Map<String, String> fields) throws Exception {

		CostComponentCalc returnVal = new CostComponentCalc();

		if(fields.get("costComponentCalcId") != null) {
			returnVal.setCostComponentCalcId((String) fields.get("costComponentCalcId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("costGlAccountTypeId") != null) {
			returnVal.setCostGlAccountTypeId((String) fields.get("costGlAccountTypeId"));
}

		if(fields.get("offsettingGlAccountTypeId") != null) {
			returnVal.setOffsettingGlAccountTypeId((String) fields.get("offsettingGlAccountTypeId"));
}

		if(fields.get("fixedCost") != null) {
String buf;
buf = fields.get("fixedCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFixedCost(bd);
}

		if(fields.get("variableCost") != null) {
String buf;
buf = fields.get("variableCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setVariableCost(bd);
}

		if(fields.get("perMilliSecond") != null) {
String buf;
buf = fields.get("perMilliSecond");
long ibuf = Long.parseLong(buf);
			returnVal.setPerMilliSecond(ibuf);
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("costCustomMethodId") != null) {
			returnVal.setCostCustomMethodId((String) fields.get("costCustomMethodId"));
}


		return returnVal;
 } 
	public static CostComponentCalc map(GenericValue val) {

CostComponentCalc returnVal = new CostComponentCalc();
		returnVal.setCostComponentCalcId(val.getString("costComponentCalcId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setCostGlAccountTypeId(val.getString("costGlAccountTypeId"));
		returnVal.setOffsettingGlAccountTypeId(val.getString("offsettingGlAccountTypeId"));
		returnVal.setFixedCost(val.getBigDecimal("fixedCost"));
		returnVal.setVariableCost(val.getBigDecimal("variableCost"));
		returnVal.setPerMilliSecond(val.getLong("perMilliSecond"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setCostCustomMethodId(val.getString("costCustomMethodId"));


return returnVal;

}

public static CostComponentCalc map(HttpServletRequest request) throws Exception {

		CostComponentCalc returnVal = new CostComponentCalc();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("costComponentCalcId")) {
returnVal.setCostComponentCalcId(request.getParameter("costComponentCalcId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("costGlAccountTypeId"))  {
returnVal.setCostGlAccountTypeId(request.getParameter("costGlAccountTypeId"));
}
		if(paramMap.containsKey("offsettingGlAccountTypeId"))  {
returnVal.setOffsettingGlAccountTypeId(request.getParameter("offsettingGlAccountTypeId"));
}
		if(paramMap.containsKey("fixedCost"))  {
String buf = request.getParameter("fixedCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFixedCost(bd);
}
		if(paramMap.containsKey("variableCost"))  {
String buf = request.getParameter("variableCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setVariableCost(bd);
}
		if(paramMap.containsKey("perMilliSecond"))  {
String buf = request.getParameter("perMilliSecond");
Long ibuf = Long.parseLong(buf);
returnVal.setPerMilliSecond(ibuf);
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("costCustomMethodId"))  {
returnVal.setCostCustomMethodId(request.getParameter("costCustomMethodId"));
}
return returnVal;

}
}
