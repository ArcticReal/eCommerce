package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMeter.model.FixedAssetMeter;

public class FixedAssetMeterMapper  {


	public static Map<String, Object> map(FixedAssetMeter fixedassetmeter) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassetmeter.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",fixedassetmeter.getFixedAssetId());
}

		if(fixedassetmeter.getProductMeterTypeId() != null ){
			returnVal.put("productMeterTypeId",fixedassetmeter.getProductMeterTypeId());
}

		if(fixedassetmeter.getReadingDate() != null ){
			returnVal.put("readingDate",fixedassetmeter.getReadingDate());
}

		if(fixedassetmeter.getMeterValue() != null ){
			returnVal.put("meterValue",fixedassetmeter.getMeterValue());
}

		if(fixedassetmeter.getReadingReasonEnumId() != null ){
			returnVal.put("readingReasonEnumId",fixedassetmeter.getReadingReasonEnumId());
}

		if(fixedassetmeter.getMaintHistSeqId() != null ){
			returnVal.put("maintHistSeqId",fixedassetmeter.getMaintHistSeqId());
}

		if(fixedassetmeter.getWorkEffortId() != null ){
			returnVal.put("workEffortId",fixedassetmeter.getWorkEffortId());
}

		return returnVal;
}


	public static FixedAssetMeter map(Map<String, Object> fields) {

		FixedAssetMeter returnVal = new FixedAssetMeter();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("productMeterTypeId") != null) {
			returnVal.setProductMeterTypeId((String) fields.get("productMeterTypeId"));
}

		if(fields.get("readingDate") != null) {
			returnVal.setReadingDate((Timestamp) fields.get("readingDate"));
}

		if(fields.get("meterValue") != null) {
			returnVal.setMeterValue((BigDecimal) fields.get("meterValue"));
}

		if(fields.get("readingReasonEnumId") != null) {
			returnVal.setReadingReasonEnumId((String) fields.get("readingReasonEnumId"));
}

		if(fields.get("maintHistSeqId") != null) {
			returnVal.setMaintHistSeqId((String) fields.get("maintHistSeqId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static FixedAssetMeter mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetMeter returnVal = new FixedAssetMeter();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("productMeterTypeId") != null) {
			returnVal.setProductMeterTypeId((String) fields.get("productMeterTypeId"));
}

		if(fields.get("readingDate") != null) {
String buf = fields.get("readingDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setReadingDate(ibuf);
}

		if(fields.get("meterValue") != null) {
String buf;
buf = fields.get("meterValue");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMeterValue(bd);
}

		if(fields.get("readingReasonEnumId") != null) {
			returnVal.setReadingReasonEnumId((String) fields.get("readingReasonEnumId"));
}

		if(fields.get("maintHistSeqId") != null) {
			returnVal.setMaintHistSeqId((String) fields.get("maintHistSeqId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static FixedAssetMeter map(GenericValue val) {

FixedAssetMeter returnVal = new FixedAssetMeter();
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setProductMeterTypeId(val.getString("productMeterTypeId"));
		returnVal.setReadingDate(val.getTimestamp("readingDate"));
		returnVal.setMeterValue(val.getBigDecimal("meterValue"));
		returnVal.setReadingReasonEnumId(val.getString("readingReasonEnumId"));
		returnVal.setMaintHistSeqId(val.getString("maintHistSeqId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));


return returnVal;

}

public static FixedAssetMeter map(HttpServletRequest request) throws Exception {

		FixedAssetMeter returnVal = new FixedAssetMeter();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetId")) {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}

		if(paramMap.containsKey("productMeterTypeId"))  {
returnVal.setProductMeterTypeId(request.getParameter("productMeterTypeId"));
}
		if(paramMap.containsKey("readingDate"))  {
String buf = request.getParameter("readingDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setReadingDate(ibuf);
}
		if(paramMap.containsKey("meterValue"))  {
String buf = request.getParameter("meterValue");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMeterValue(bd);
}
		if(paramMap.containsKey("readingReasonEnumId"))  {
returnVal.setReadingReasonEnumId(request.getParameter("readingReasonEnumId"));
}
		if(paramMap.containsKey("maintHistSeqId"))  {
returnVal.setMaintHistSeqId(request.getParameter("maintHistSeqId"));
}
		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
return returnVal;

}
}
