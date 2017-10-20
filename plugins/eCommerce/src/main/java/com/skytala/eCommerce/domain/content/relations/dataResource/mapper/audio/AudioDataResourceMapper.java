package com.skytala.eCommerce.domain.content.relations.dataResource.mapper.audio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.audio.AudioDataResource;

public class AudioDataResourceMapper  {


	public static Map<String, Object> map(AudioDataResource audiodataresource) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(audiodataresource.getDataResourceId() != null ){
			returnVal.put("dataResourceId",audiodataresource.getDataResourceId());
}

		if(audiodataresource.getAudioData() != null ){
			returnVal.put("audioData",audiodataresource.getAudioData());
}

		return returnVal;
}


	public static AudioDataResource map(Map<String, Object> fields) {

		AudioDataResource returnVal = new AudioDataResource();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("audioData") != null) {
			returnVal.setAudioData((byte[]) fields.get("audioData"));
}


		return returnVal;
 } 
	public static AudioDataResource mapstrstr(Map<String, String> fields) throws Exception {

		AudioDataResource returnVal = new AudioDataResource();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("audioData") != null) {
String buf = fields.get("audioData");
byte[] ibuf = buf.getBytes();
			returnVal.setAudioData(ibuf);
}


		return returnVal;
 } 
	public static AudioDataResource map(GenericValue val) {

AudioDataResource returnVal = new AudioDataResource();
		returnVal.setDataResourceId(val.getString("dataResourceId"));
		returnVal.setAudioData(val.getBytes("audioData"));


return returnVal;

}

public static AudioDataResource map(HttpServletRequest request) throws Exception {

		AudioDataResource returnVal = new AudioDataResource();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("dataResourceId")) {
returnVal.setDataResourceId(request.getParameter("dataResourceId"));
}

		if(paramMap.containsKey("audioData"))  {
String buf = request.getParameter("audioData");
byte[] ibuf = buf.getBytes();
returnVal.setAudioData(ibuf);
}
return returnVal;

}
}
