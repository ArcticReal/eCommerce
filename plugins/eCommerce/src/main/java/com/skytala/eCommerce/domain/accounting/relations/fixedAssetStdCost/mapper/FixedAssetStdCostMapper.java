package com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.model.FixedAssetStdCost;

public class FixedAssetStdCostMapper  {


	public static Map<String, Object> map(FixedAssetStdCost fixedassetstdcost) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassetstdcost.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",fixedassetstdcost.getFixedAssetId());
}

		if(fixedassetstdcost.getFixedAssetStdCostTypeId() != null ){
			returnVal.put("fixedAssetStdCostTypeId",fixedassetstdcost.getFixedAssetStdCostTypeId());
}

		if(fixedassetstdcost.getFromDate() != null ){
			returnVal.put("fromDate",fixedassetstdcost.getFromDate());
}

		if(fixedassetstdcost.getThruDate() != null ){
			returnVal.put("thruDate",fixedassetstdcost.getThruDate());
}

		if(fixedassetstdcost.getAmountUomId() != null ){
			returnVal.put("amountUomId",fixedassetstdcost.getAmountUomId());
}

		if(fixedassetstdcost.getAmount() != null ){
			returnVal.put("amount",fixedassetstdcost.getAmount());
}

		return returnVal;
}


	public static FixedAssetStdCost map(Map<String, Object> fields) {

		FixedAssetStdCost returnVal = new FixedAssetStdCost();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("fixedAssetStdCostTypeId") != null) {
			returnVal.setFixedAssetStdCostTypeId((String) fields.get("fixedAssetStdCostTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("amountUomId") != null) {
			returnVal.setAmountUomId((String) fields.get("amountUomId"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}


		return returnVal;
 } 
	public static FixedAssetStdCost mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetStdCost returnVal = new FixedAssetStdCost();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("fixedAssetStdCostTypeId") != null) {
			returnVal.setFixedAssetStdCostTypeId((String) fields.get("fixedAssetStdCostTypeId"));
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

		if(fields.get("amountUomId") != null) {
			returnVal.setAmountUomId((String) fields.get("amountUomId"));
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}


		return returnVal;
 } 
	public static FixedAssetStdCost map(GenericValue val) {

FixedAssetStdCost returnVal = new FixedAssetStdCost();
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setFixedAssetStdCostTypeId(val.getString("fixedAssetStdCostTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setAmountUomId(val.getString("amountUomId"));
		returnVal.setAmount(val.getBigDecimal("amount"));


return returnVal;

}

public static FixedAssetStdCost map(HttpServletRequest request) throws Exception {

		FixedAssetStdCost returnVal = new FixedAssetStdCost();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetId")) {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}

		if(paramMap.containsKey("fixedAssetStdCostTypeId"))  {
returnVal.setFixedAssetStdCostTypeId(request.getParameter("fixedAssetStdCostTypeId"));
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
		if(paramMap.containsKey("amountUomId"))  {
returnVal.setAmountUomId(request.getParameter("amountUomId"));
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
return returnVal;

}
}
