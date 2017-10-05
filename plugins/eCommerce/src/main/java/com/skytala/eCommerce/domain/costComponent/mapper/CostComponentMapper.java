package com.skytala.eCommerce.domain.costComponent.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.costComponent.model.CostComponent;

public class CostComponentMapper  {


	public static Map<String, Object> map(CostComponent costcomponent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(costcomponent.getCostComponentId() != null ){
			returnVal.put("costComponentId",costcomponent.getCostComponentId());
}

		if(costcomponent.getCostComponentTypeId() != null ){
			returnVal.put("costComponentTypeId",costcomponent.getCostComponentTypeId());
}

		if(costcomponent.getProductId() != null ){
			returnVal.put("productId",costcomponent.getProductId());
}

		if(costcomponent.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",costcomponent.getProductFeatureId());
}

		if(costcomponent.getPartyId() != null ){
			returnVal.put("partyId",costcomponent.getPartyId());
}

		if(costcomponent.getGeoId() != null ){
			returnVal.put("geoId",costcomponent.getGeoId());
}

		if(costcomponent.getWorkEffortId() != null ){
			returnVal.put("workEffortId",costcomponent.getWorkEffortId());
}

		if(costcomponent.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",costcomponent.getFixedAssetId());
}

		if(costcomponent.getCostComponentCalcId() != null ){
			returnVal.put("costComponentCalcId",costcomponent.getCostComponentCalcId());
}

		if(costcomponent.getFromDate() != null ){
			returnVal.put("fromDate",costcomponent.getFromDate());
}

		if(costcomponent.getThruDate() != null ){
			returnVal.put("thruDate",costcomponent.getThruDate());
}

		if(costcomponent.getCost() != null ){
			returnVal.put("cost",costcomponent.getCost());
}

		if(costcomponent.getCostUomId() != null ){
			returnVal.put("costUomId",costcomponent.getCostUomId());
}

		return returnVal;
}


	public static CostComponent map(Map<String, Object> fields) {

		CostComponent returnVal = new CostComponent();

		if(fields.get("costComponentId") != null) {
			returnVal.setCostComponentId((String) fields.get("costComponentId"));
}

		if(fields.get("costComponentTypeId") != null) {
			returnVal.setCostComponentTypeId((String) fields.get("costComponentTypeId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("costComponentCalcId") != null) {
			returnVal.setCostComponentCalcId((String) fields.get("costComponentCalcId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("cost") != null) {
			returnVal.setCost((BigDecimal) fields.get("cost"));
}

		if(fields.get("costUomId") != null) {
			returnVal.setCostUomId((String) fields.get("costUomId"));
}


		return returnVal;
 } 
	public static CostComponent mapstrstr(Map<String, String> fields) throws Exception {

		CostComponent returnVal = new CostComponent();

		if(fields.get("costComponentId") != null) {
			returnVal.setCostComponentId((String) fields.get("costComponentId"));
}

		if(fields.get("costComponentTypeId") != null) {
			returnVal.setCostComponentTypeId((String) fields.get("costComponentTypeId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("costComponentCalcId") != null) {
			returnVal.setCostComponentCalcId((String) fields.get("costComponentCalcId"));
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

		if(fields.get("cost") != null) {
String buf;
buf = fields.get("cost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCost(bd);
}

		if(fields.get("costUomId") != null) {
			returnVal.setCostUomId((String) fields.get("costUomId"));
}


		return returnVal;
 } 
	public static CostComponent map(GenericValue val) {

CostComponent returnVal = new CostComponent();
		returnVal.setCostComponentId(val.getString("costComponentId"));
		returnVal.setCostComponentTypeId(val.getString("costComponentTypeId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductFeatureId(val.getString("productFeatureId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setGeoId(val.getString("geoId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setCostComponentCalcId(val.getString("costComponentCalcId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setCost(val.getBigDecimal("cost"));
		returnVal.setCostUomId(val.getString("costUomId"));


return returnVal;

}

public static CostComponent map(HttpServletRequest request) throws Exception {

		CostComponent returnVal = new CostComponent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("costComponentId")) {
returnVal.setCostComponentId(request.getParameter("costComponentId"));
}

		if(paramMap.containsKey("costComponentTypeId"))  {
returnVal.setCostComponentTypeId(request.getParameter("costComponentTypeId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("productFeatureId"))  {
returnVal.setProductFeatureId(request.getParameter("productFeatureId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("geoId"))  {
returnVal.setGeoId(request.getParameter("geoId"));
}
		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
		if(paramMap.containsKey("fixedAssetId"))  {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}
		if(paramMap.containsKey("costComponentCalcId"))  {
returnVal.setCostComponentCalcId(request.getParameter("costComponentCalcId"));
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
		if(paramMap.containsKey("cost"))  {
String buf = request.getParameter("cost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCost(bd);
}
		if(paramMap.containsKey("costUomId"))  {
returnVal.setCostUomId(request.getParameter("costUomId"));
}
return returnVal;

}
}
