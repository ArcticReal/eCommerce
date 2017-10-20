package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.maintMeter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maintMeter.FixedAssetMaintMeter;

public class FixedAssetMaintMeterMapper  {


	public static Map<String, Object> map(FixedAssetMaintMeter fixedassetmaintmeter) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassetmaintmeter.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",fixedassetmaintmeter.getFixedAssetId());
}

		if(fixedassetmaintmeter.getMaintHistSeqId() != null ){
			returnVal.put("maintHistSeqId",fixedassetmaintmeter.getMaintHistSeqId());
}

		if(fixedassetmaintmeter.getProductMeterTypeId() != null ){
			returnVal.put("productMeterTypeId",fixedassetmaintmeter.getProductMeterTypeId());
}

		if(fixedassetmaintmeter.getMeterValue() != null ){
			returnVal.put("meterValue",fixedassetmaintmeter.getMeterValue());
}

		return returnVal;
}


	public static FixedAssetMaintMeter map(Map<String, Object> fields) {

		FixedAssetMaintMeter returnVal = new FixedAssetMaintMeter();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("maintHistSeqId") != null) {
			returnVal.setMaintHistSeqId((String) fields.get("maintHistSeqId"));
}

		if(fields.get("productMeterTypeId") != null) {
			returnVal.setProductMeterTypeId((String) fields.get("productMeterTypeId"));
}

		if(fields.get("meterValue") != null) {
			returnVal.setMeterValue((BigDecimal) fields.get("meterValue"));
}


		return returnVal;
 } 
	public static FixedAssetMaintMeter mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetMaintMeter returnVal = new FixedAssetMaintMeter();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("maintHistSeqId") != null) {
			returnVal.setMaintHistSeqId((String) fields.get("maintHistSeqId"));
}

		if(fields.get("productMeterTypeId") != null) {
			returnVal.setProductMeterTypeId((String) fields.get("productMeterTypeId"));
}

		if(fields.get("meterValue") != null) {
String buf;
buf = fields.get("meterValue");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMeterValue(bd);
}


		return returnVal;
 } 
	public static FixedAssetMaintMeter map(GenericValue val) {

FixedAssetMaintMeter returnVal = new FixedAssetMaintMeter();
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setMaintHistSeqId(val.getString("maintHistSeqId"));
		returnVal.setProductMeterTypeId(val.getString("productMeterTypeId"));
		returnVal.setMeterValue(val.getBigDecimal("meterValue"));


return returnVal;

}

public static FixedAssetMaintMeter map(HttpServletRequest request) throws Exception {

		FixedAssetMaintMeter returnVal = new FixedAssetMaintMeter();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetId")) {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}

		if(paramMap.containsKey("maintHistSeqId"))  {
returnVal.setMaintHistSeqId(request.getParameter("maintHistSeqId"));
}
		if(paramMap.containsKey("productMeterTypeId"))  {
returnVal.setProductMeterTypeId(request.getParameter("productMeterTypeId"));
}
		if(paramMap.containsKey("meterValue"))  {
String buf = request.getParameter("meterValue");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMeterValue(bd);
}
return returnVal;

}
}
