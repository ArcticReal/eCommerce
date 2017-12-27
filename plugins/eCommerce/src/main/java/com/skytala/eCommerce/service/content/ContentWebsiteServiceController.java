package com.skytala.eCommerce.service.content;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.ServiceAuthException;
import org.apache.ofbiz.service.ServiceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/service/contentWebsite")
public class ContentWebsiteServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/removeWebSitePathAlias")
	public ResponseEntity<Map<String, Object>> removeWebSitePathAlias(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="pathAlias") String pathAlias) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("pathAlias",pathAlias);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeWebSitePathAlias", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateWebSiteContentType")
	public ResponseEntity<Map<String, Object>> updateWebSiteContentType(HttpSession session, @RequestParam(value="webSiteContentTypeId") String webSiteContentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("webSiteContentTypeId",webSiteContentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWebSiteContentType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateWebSiteContent")
	public ResponseEntity<Map<String, Object>> updateWebSiteContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="webSiteContentTypeId") String webSiteContentTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("webSiteContentTypeId",webSiteContentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWebSiteContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/generateMissingSeoUrlForWebsite")
	public ResponseEntity<Map<String, Object>> generateMissingSeoUrlForWebsite(HttpSession session, @RequestParam(value="typeGenerate") List typeGenerate, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="prodCatalogId") String prodCatalogId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("typeGenerate",typeGenerate);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("generateMissingSeoUrlForWebsite", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createWebSiteRole")
	public ResponseEntity<Map<String, Object>> createWebSiteRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="sequenceNum") Long sequenceNum, @RequestParam(value="partyId") String partyId, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="thruDate") Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("partyId",partyId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWebSiteRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addWebSiteRole")
	public ResponseEntity<Map<String, Object>> addWebSiteRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="sequenceNum") Long sequenceNum, @RequestParam(value="partyId") String partyId, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="thruDate") Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("partyId",partyId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addWebSiteRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateWebSitePathAlias")
	public ResponseEntity<Map<String, Object>> updateWebSitePathAlias(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="pathAlias") String pathAlias, @RequestParam(value="aliasTo", required=false) String aliasTo, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("pathAlias",pathAlias);
		paramMap.put("aliasTo",aliasTo);
		paramMap.put("contentId",contentId);
		paramMap.put("mapKey",mapKey);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWebSitePathAlias", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateWebSiteRole")
	public ResponseEntity<Map<String, Object>> updateWebSiteRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="sequenceNum") Long sequenceNum, @RequestParam(value="partyId") String partyId, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="thruDate") Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("partyId",partyId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWebSiteRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createWebSite")
	public ResponseEntity<Map<String, Object>> createWebSite(HttpSession session, @RequestParam(value="siteName") String siteName, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="secureContentPrefix", required=false) String secureContentPrefix, @RequestParam(value="cookieDomain", required=false) String cookieDomain, @RequestParam(value="standardContentPrefix", required=false) String standardContentPrefix, @RequestParam(value="httpPort", required=false) String httpPort, @RequestParam(value="enableHttps", required=false) String enableHttps, @RequestParam(value="httpHost", required=false) String httpHost, @RequestParam(value="visualThemeSetId", required=false) String visualThemeSetId, @RequestParam(value="httpsHost", required=false) String httpsHost, @RequestParam(value="httpsPort", required=false) String httpsPort) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("siteName",siteName);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("secureContentPrefix",secureContentPrefix);
		paramMap.put("cookieDomain",cookieDomain);
		paramMap.put("standardContentPrefix",standardContentPrefix);
		paramMap.put("httpPort",httpPort);
		paramMap.put("enableHttps",enableHttps);
		paramMap.put("httpHost",httpHost);
		paramMap.put("visualThemeSetId",visualThemeSetId);
		paramMap.put("httpsHost",httpsHost);
		paramMap.put("httpsPort",httpsPort);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWebSite", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeWebSiteRole")
	public ResponseEntity<Map<String, Object>> removeWebSiteRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="webSiteId") String webSiteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeWebSiteRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createWebSiteContent")
	public ResponseEntity<Map<String, Object>> createWebSiteContent(HttpSession session, @RequestParam(value="webSiteContentTypeId") String webSiteContentTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("webSiteContentTypeId",webSiteContentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWebSiteContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getWebSitePathAlias")
	public ResponseEntity<Map<String, Object>> getWebSitePathAlias(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="pathAlias") String pathAlias) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("pathAlias",pathAlias);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getWebSitePathAlias", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateWebSite")
	public ResponseEntity<Map<String, Object>> updateWebSite(HttpSession session, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="secureContentPrefix", required=false) String secureContentPrefix, @RequestParam(value="cookieDomain", required=false) String cookieDomain, @RequestParam(value="standardContentPrefix", required=false) String standardContentPrefix, @RequestParam(value="httpPort", required=false) String httpPort, @RequestParam(value="siteName", required=false) String siteName, @RequestParam(value="enableHttps", required=false) String enableHttps, @RequestParam(value="httpHost", required=false) String httpHost, @RequestParam(value="visualThemeSetId", required=false) String visualThemeSetId, @RequestParam(value="httpsHost", required=false) String httpsHost, @RequestParam(value="httpsPort", required=false) String httpsPort) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("secureContentPrefix",secureContentPrefix);
		paramMap.put("cookieDomain",cookieDomain);
		paramMap.put("standardContentPrefix",standardContentPrefix);
		paramMap.put("httpPort",httpPort);
		paramMap.put("siteName",siteName);
		paramMap.put("enableHttps",enableHttps);
		paramMap.put("httpHost",httpHost);
		paramMap.put("visualThemeSetId",visualThemeSetId);
		paramMap.put("httpsHost",httpsHost);
		paramMap.put("httpsPort",httpsPort);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWebSite", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/webSiteRoleInterface")
	public ResponseEntity<Map<String, Object>> webSiteRoleInterface(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("webSiteRoleInterface", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeWebSiteContentType")
	public ResponseEntity<Map<String, Object>> removeWebSiteContentType(HttpSession session, @RequestParam(value="webSiteContentTypeId") String webSiteContentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("webSiteContentTypeId",webSiteContentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeWebSiteContentType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createWebSitePathAlias")
	public ResponseEntity<Map<String, Object>> createWebSitePathAlias(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="pathAlias") String pathAlias, @RequestParam(value="aliasTo", required=false) String aliasTo, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("pathAlias",pathAlias);
		paramMap.put("aliasTo",aliasTo);
		paramMap.put("contentId",contentId);
		paramMap.put("mapKey",mapKey);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWebSitePathAlias", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/autoCreateWebSiteContent")
	public ResponseEntity<Map<String, Object>> autoCreateWebSiteContent(HttpSession session, @RequestParam(value="webSiteContentTypeId") List webSiteContentTypeId, @RequestParam(value="webSiteId") String webSiteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("webSiteContentTypeId",webSiteContentTypeId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("autoCreateWebSiteContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createWebSiteContentType")
	public ResponseEntity<Map<String, Object>> createWebSiteContentType(HttpSession session, @RequestParam(value="webSiteContentTypeId") String webSiteContentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("webSiteContentTypeId",webSiteContentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWebSiteContentType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeWebSiteContent")
	public ResponseEntity<Map<String, Object>> removeWebSiteContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="webSiteContentTypeId") String webSiteContentTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="webSiteId") String webSiteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("webSiteContentTypeId",webSiteContentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeWebSiteContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}



}
