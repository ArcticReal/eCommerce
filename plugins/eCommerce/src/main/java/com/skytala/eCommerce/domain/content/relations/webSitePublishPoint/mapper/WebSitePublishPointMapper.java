package com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.model.WebSitePublishPoint;

public class WebSitePublishPointMapper  {


	public static Map<String, Object> map(WebSitePublishPoint websitepublishpoint) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(websitepublishpoint.getContentId() != null ){
			returnVal.put("contentId",websitepublishpoint.getContentId());
}

		if(websitepublishpoint.getTemplateTitle() != null ){
			returnVal.put("templateTitle",websitepublishpoint.getTemplateTitle());
}

		if(websitepublishpoint.getStyleSheetFile() != null ){
			returnVal.put("styleSheetFile",websitepublishpoint.getStyleSheetFile());
}

		if(websitepublishpoint.getLogo() != null ){
			returnVal.put("logo",websitepublishpoint.getLogo());
}

		if(websitepublishpoint.getMedallionLogo() != null ){
			returnVal.put("medallionLogo",websitepublishpoint.getMedallionLogo());
}

		if(websitepublishpoint.getLineLogo() != null ){
			returnVal.put("lineLogo",websitepublishpoint.getLineLogo());
}

		if(websitepublishpoint.getLeftBarId() != null ){
			returnVal.put("leftBarId",websitepublishpoint.getLeftBarId());
}

		if(websitepublishpoint.getRightBarId() != null ){
			returnVal.put("rightBarId",websitepublishpoint.getRightBarId());
}

		if(websitepublishpoint.getContentDept() != null ){
			returnVal.put("contentDept",websitepublishpoint.getContentDept());
}

		if(websitepublishpoint.getAboutContentId() != null ){
			returnVal.put("aboutContentId",websitepublishpoint.getAboutContentId());
}

		return returnVal;
}


	public static WebSitePublishPoint map(Map<String, Object> fields) {

		WebSitePublishPoint returnVal = new WebSitePublishPoint();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("templateTitle") != null) {
			returnVal.setTemplateTitle((String) fields.get("templateTitle"));
}

		if(fields.get("styleSheetFile") != null) {
			returnVal.setStyleSheetFile((String) fields.get("styleSheetFile"));
}

		if(fields.get("logo") != null) {
			returnVal.setLogo((String) fields.get("logo"));
}

		if(fields.get("medallionLogo") != null) {
			returnVal.setMedallionLogo((String) fields.get("medallionLogo"));
}

		if(fields.get("lineLogo") != null) {
			returnVal.setLineLogo((String) fields.get("lineLogo"));
}

		if(fields.get("leftBarId") != null) {
			returnVal.setLeftBarId((String) fields.get("leftBarId"));
}

		if(fields.get("rightBarId") != null) {
			returnVal.setRightBarId((String) fields.get("rightBarId"));
}

		if(fields.get("contentDept") != null) {
			returnVal.setContentDept((String) fields.get("contentDept"));
}

		if(fields.get("aboutContentId") != null) {
			returnVal.setAboutContentId((String) fields.get("aboutContentId"));
}


		return returnVal;
 } 
	public static WebSitePublishPoint mapstrstr(Map<String, String> fields) throws Exception {

		WebSitePublishPoint returnVal = new WebSitePublishPoint();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("templateTitle") != null) {
			returnVal.setTemplateTitle((String) fields.get("templateTitle"));
}

		if(fields.get("styleSheetFile") != null) {
			returnVal.setStyleSheetFile((String) fields.get("styleSheetFile"));
}

		if(fields.get("logo") != null) {
			returnVal.setLogo((String) fields.get("logo"));
}

		if(fields.get("medallionLogo") != null) {
			returnVal.setMedallionLogo((String) fields.get("medallionLogo"));
}

		if(fields.get("lineLogo") != null) {
			returnVal.setLineLogo((String) fields.get("lineLogo"));
}

		if(fields.get("leftBarId") != null) {
			returnVal.setLeftBarId((String) fields.get("leftBarId"));
}

		if(fields.get("rightBarId") != null) {
			returnVal.setRightBarId((String) fields.get("rightBarId"));
}

		if(fields.get("contentDept") != null) {
			returnVal.setContentDept((String) fields.get("contentDept"));
}

		if(fields.get("aboutContentId") != null) {
			returnVal.setAboutContentId((String) fields.get("aboutContentId"));
}


		return returnVal;
 } 
	public static WebSitePublishPoint map(GenericValue val) {

WebSitePublishPoint returnVal = new WebSitePublishPoint();
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setTemplateTitle(val.getString("templateTitle"));
		returnVal.setStyleSheetFile(val.getString("styleSheetFile"));
		returnVal.setLogo(val.getString("logo"));
		returnVal.setMedallionLogo(val.getString("medallionLogo"));
		returnVal.setLineLogo(val.getString("lineLogo"));
		returnVal.setLeftBarId(val.getString("leftBarId"));
		returnVal.setRightBarId(val.getString("rightBarId"));
		returnVal.setContentDept(val.getString("contentDept"));
		returnVal.setAboutContentId(val.getString("aboutContentId"));


return returnVal;

}

public static WebSitePublishPoint map(HttpServletRequest request) throws Exception {

		WebSitePublishPoint returnVal = new WebSitePublishPoint();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentId")) {
returnVal.setContentId(request.getParameter("contentId"));
}

		if(paramMap.containsKey("templateTitle"))  {
returnVal.setTemplateTitle(request.getParameter("templateTitle"));
}
		if(paramMap.containsKey("styleSheetFile"))  {
returnVal.setStyleSheetFile(request.getParameter("styleSheetFile"));
}
		if(paramMap.containsKey("logo"))  {
returnVal.setLogo(request.getParameter("logo"));
}
		if(paramMap.containsKey("medallionLogo"))  {
returnVal.setMedallionLogo(request.getParameter("medallionLogo"));
}
		if(paramMap.containsKey("lineLogo"))  {
returnVal.setLineLogo(request.getParameter("lineLogo"));
}
		if(paramMap.containsKey("leftBarId"))  {
returnVal.setLeftBarId(request.getParameter("leftBarId"));
}
		if(paramMap.containsKey("rightBarId"))  {
returnVal.setRightBarId(request.getParameter("rightBarId"));
}
		if(paramMap.containsKey("contentDept"))  {
returnVal.setContentDept(request.getParameter("contentDept"));
}
		if(paramMap.containsKey("aboutContentId"))  {
returnVal.setAboutContentId(request.getParameter("aboutContentId"));
}
return returnVal;

}
}
