package com.skytala.eCommerce.domain.accommodationMap.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accommodationMap.model.AccommodationMap;

public class AccommodationMapMapper  {


	public static Map<String, Object> map(AccommodationMap accommodationmap) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(accommodationmap.getAccommodationMapId() != null ){
			returnVal.put("accommodationMapId",accommodationmap.getAccommodationMapId());
}

		if(accommodationmap.getAccommodationClassId() != null ){
			returnVal.put("accommodationClassId",accommodationmap.getAccommodationClassId());
}

		if(accommodationmap.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",accommodationmap.getFixedAssetId());
}

		if(accommodationmap.getAccommodationMapTypeId() != null ){
			returnVal.put("accommodationMapTypeId",accommodationmap.getAccommodationMapTypeId());
}

		if(accommodationmap.getNumberOfSpaces() != null ){
			returnVal.put("numberOfSpaces",accommodationmap.getNumberOfSpaces());
}

		return returnVal;
}


	public static AccommodationMap map(Map<String, Object> fields) {

		AccommodationMap returnVal = new AccommodationMap();

		if(fields.get("accommodationMapId") != null) {
			returnVal.setAccommodationMapId((String) fields.get("accommodationMapId"));
}

		if(fields.get("accommodationClassId") != null) {
			returnVal.setAccommodationClassId((String) fields.get("accommodationClassId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("accommodationMapTypeId") != null) {
			returnVal.setAccommodationMapTypeId((String) fields.get("accommodationMapTypeId"));
}

		if(fields.get("numberOfSpaces") != null) {
			returnVal.setNumberOfSpaces((long) fields.get("numberOfSpaces"));
}


		return returnVal;
 } 
	public static AccommodationMap mapstrstr(Map<String, String> fields) throws Exception {

		AccommodationMap returnVal = new AccommodationMap();

		if(fields.get("accommodationMapId") != null) {
			returnVal.setAccommodationMapId((String) fields.get("accommodationMapId"));
}

		if(fields.get("accommodationClassId") != null) {
			returnVal.setAccommodationClassId((String) fields.get("accommodationClassId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("accommodationMapTypeId") != null) {
			returnVal.setAccommodationMapTypeId((String) fields.get("accommodationMapTypeId"));
}

		if(fields.get("numberOfSpaces") != null) {
String buf;
buf = fields.get("numberOfSpaces");
long ibuf = Long.parseLong(buf);
			returnVal.setNumberOfSpaces(ibuf);
}


		return returnVal;
 } 
	public static AccommodationMap map(GenericValue val) {

AccommodationMap returnVal = new AccommodationMap();
		returnVal.setAccommodationMapId(val.getString("accommodationMapId"));
		returnVal.setAccommodationClassId(val.getString("accommodationClassId"));
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setAccommodationMapTypeId(val.getString("accommodationMapTypeId"));
		returnVal.setNumberOfSpaces(val.getLong("numberOfSpaces"));


return returnVal;

}

public static AccommodationMap map(HttpServletRequest request) throws Exception {

		AccommodationMap returnVal = new AccommodationMap();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("accommodationMapId")) {
returnVal.setAccommodationMapId(request.getParameter("accommodationMapId"));
}

		if(paramMap.containsKey("accommodationClassId"))  {
returnVal.setAccommodationClassId(request.getParameter("accommodationClassId"));
}
		if(paramMap.containsKey("fixedAssetId"))  {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}
		if(paramMap.containsKey("accommodationMapTypeId"))  {
returnVal.setAccommodationMapTypeId(request.getParameter("accommodationMapTypeId"));
}
		if(paramMap.containsKey("numberOfSpaces"))  {
String buf = request.getParameter("numberOfSpaces");
Long ibuf = Long.parseLong(buf);
returnVal.setNumberOfSpaces(ibuf);
}
return returnVal;

}
}
