package com.skytala.eCommerce.domain.accommodationSpot.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accommodationSpot.model.AccommodationSpot;

public class AccommodationSpotMapper  {


	public static Map<String, Object> map(AccommodationSpot accommodationspot) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(accommodationspot.getAccommodationSpotId() != null ){
			returnVal.put("accommodationSpotId",accommodationspot.getAccommodationSpotId());
}

		if(accommodationspot.getAccommodationClassId() != null ){
			returnVal.put("accommodationClassId",accommodationspot.getAccommodationClassId());
}

		if(accommodationspot.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",accommodationspot.getFixedAssetId());
}

		if(accommodationspot.getNumberOfSpaces() != null ){
			returnVal.put("numberOfSpaces",accommodationspot.getNumberOfSpaces());
}

		if(accommodationspot.getDescription() != null ){
			returnVal.put("description",accommodationspot.getDescription());
}

		return returnVal;
}


	public static AccommodationSpot map(Map<String, Object> fields) {

		AccommodationSpot returnVal = new AccommodationSpot();

		if(fields.get("accommodationSpotId") != null) {
			returnVal.setAccommodationSpotId((String) fields.get("accommodationSpotId"));
}

		if(fields.get("accommodationClassId") != null) {
			returnVal.setAccommodationClassId((String) fields.get("accommodationClassId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("numberOfSpaces") != null) {
			returnVal.setNumberOfSpaces((long) fields.get("numberOfSpaces"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AccommodationSpot mapstrstr(Map<String, String> fields) throws Exception {

		AccommodationSpot returnVal = new AccommodationSpot();

		if(fields.get("accommodationSpotId") != null) {
			returnVal.setAccommodationSpotId((String) fields.get("accommodationSpotId"));
}

		if(fields.get("accommodationClassId") != null) {
			returnVal.setAccommodationClassId((String) fields.get("accommodationClassId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("numberOfSpaces") != null) {
String buf;
buf = fields.get("numberOfSpaces");
long ibuf = Long.parseLong(buf);
			returnVal.setNumberOfSpaces(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AccommodationSpot map(GenericValue val) {

AccommodationSpot returnVal = new AccommodationSpot();
		returnVal.setAccommodationSpotId(val.getString("accommodationSpotId"));
		returnVal.setAccommodationClassId(val.getString("accommodationClassId"));
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setNumberOfSpaces(val.getLong("numberOfSpaces"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static AccommodationSpot map(HttpServletRequest request) throws Exception {

		AccommodationSpot returnVal = new AccommodationSpot();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("accommodationSpotId")) {
returnVal.setAccommodationSpotId(request.getParameter("accommodationSpotId"));
}

		if(paramMap.containsKey("accommodationClassId"))  {
returnVal.setAccommodationClassId(request.getParameter("accommodationClassId"));
}
		if(paramMap.containsKey("fixedAssetId"))  {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}
		if(paramMap.containsKey("numberOfSpaces"))  {
String buf = request.getParameter("numberOfSpaces");
Long ibuf = Long.parseLong(buf);
returnVal.setNumberOfSpaces(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
