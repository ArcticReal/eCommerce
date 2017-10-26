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

@RestController
@RequestMapping("/service/ContentsController")
public class ContentServicesController{

	@RequestMapping(method = RequestMethod.POST, value = "/updateDataResourceRole")
	public ResponseEntity<Object> updateDataResourceRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="partyId") String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDataResourceRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getFinalApprovalStatus")
	public ResponseEntity<Object> getFinalApprovalStatus(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="contentRevisionSeqId", required=false) String contentRevisionSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("contentRevisionSeqId",contentRevisionSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getFinalApprovalStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateBlogEntry")
	public ResponseEntity<Object> updateBlogEntry(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="blogContentId") String blogContentId, @RequestParam(value="_uploadedFile_contentType", required=false) String _uploadedFile_contentType, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="description", required=false) String description, @RequestParam(value="articleData", required=false) String articleData, @RequestParam(value="summaryData", required=false) String summaryData, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="_uploadedFile_fileName", required=false) String _uploadedFile_fileName, @RequestParam(value="contentName", required=false) String contentName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("blogContentId",blogContentId);
		paramMap.put("_uploadedFile_contentType",_uploadedFile_contentType);
		paramMap.put("statusId",statusId);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("description",description);
		paramMap.put("articleData",articleData);
		paramMap.put("summaryData",summaryData);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("_uploadedFile_fileName",_uploadedFile_fileName);
		paramMap.put("contentName",contentName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateBlogEntry", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/urlEncodeArgs")
	public ResponseEntity<Object> urlEncodeArgs(HttpSession session, @RequestParam(value="mapIn") java.util.Map mapIn) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mapIn",mapIn);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("urlEncodeArgs", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrRemove")
	public ResponseEntity<Object> updateOrRemove(HttpSession session, @RequestParam(value="entityName") String entityName, @RequestParam(value="pkFieldCount") String pkFieldCount, @RequestParam(value="fieldName0", required=false) String fieldName0, @RequestParam(value="fieldName2", required=false) String fieldName2, @RequestParam(value="fieldName1", required=false) String fieldName1, @RequestParam(value="fieldName3", required=false) String fieldName3, @RequestParam(value="action", required=false) String action, @RequestParam(value="fieldValue3", required=false) String fieldValue3, @RequestParam(value="fieldValue2", required=false) String fieldValue2, @RequestParam(value="fieldValue1", required=false) String fieldValue1, @RequestParam(value="fieldValue0", required=false) String fieldValue0) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entityName",entityName);
		paramMap.put("pkFieldCount",pkFieldCount);
		paramMap.put("fieldName0",fieldName0);
		paramMap.put("fieldName2",fieldName2);
		paramMap.put("fieldName1",fieldName1);
		paramMap.put("fieldName3",fieldName3);
		paramMap.put("action",action);
		paramMap.put("fieldValue3",fieldValue3);
		paramMap.put("fieldValue2",fieldValue2);
		paramMap.put("fieldValue1",fieldValue1);
		paramMap.put("fieldValue0",fieldValue0);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOrRemove", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateDataCategory")
	public ResponseEntity<Object> updateDataCategory(HttpSession session, @RequestParam(value="dataCategoryId") String dataCategoryId, @RequestParam(value="parentCategoryId", required=false) String parentCategoryId, @RequestParam(value="categoryName", required=false) String categoryName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("parentCategoryId",parentCategoryId);
		paramMap.put("categoryName",categoryName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDataCategory", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkContentAssocIds")
	public ResponseEntity<Object> checkContentAssocIds(HttpSession session, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="contentId", required=false) String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkContentAssocIds", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cloneTemplateContentApprovals")
	public ResponseEntity<Object> cloneTemplateContentApprovals(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="contentRevisionSeqId") String contentRevisionSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("contentRevisionSeqId",contentRevisionSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cloneTemplateContentApprovals", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateImage")
	public ResponseEntity<Object> updateImage(HttpSession session, @RequestParam(value="imageData") java.nio.ByteBuffer imageData, @RequestParam(value="dataResourceId") String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("imageData",imageData);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateImage", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/traverseContent")
	public ResponseEntity<Object> traverseContent(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="followWhen", required=false) String followWhen, @RequestParam(value="thruDateStr", required=false) String thruDateStr, @RequestParam(value="pickWhen", required=false) String pickWhen, @RequestParam(value="fromDateStr", required=false) String fromDateStr, @RequestParam(value="returnBeforePickWhen", required=false) String returnBeforePickWhen, @RequestParam(value="returnAfterPickWhen", required=false) String returnAfterPickWhen, @RequestParam(value="direction", required=false) String direction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("followWhen",followWhen);
		paramMap.put("thruDateStr",thruDateStr);
		paramMap.put("pickWhen",pickWhen);
		paramMap.put("fromDateStr",fromDateStr);
		paramMap.put("returnBeforePickWhen",returnBeforePickWhen);
		paramMap.put("returnAfterPickWhen",returnAfterPickWhen);
		paramMap.put("direction",direction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("traverseContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/persistCompDocContent")
	public ResponseEntity<Object> persistCompDocContent(HttpSession session, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="imageData", required=false) java.nio.ByteBuffer imageData, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="rootContentId", required=false) String rootContentId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="forceElectronicText", required=false) String forceElectronicText, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="DataResource", required=false) String DataResource, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="_imageData_fileName", required=false) String _imageData_fileName, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="Content", required=false) String Content, @RequestParam(value="_imageData_contentType", required=false) String _imageData_contentType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("imageData",imageData);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("rootContentId",rootContentId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("forceElectronicText",forceElectronicText);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("DataResource",DataResource);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("_imageData_fileName",_imageData_fileName);
		paramMap.put("userLogin",userLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("localeString",localeString);
		paramMap.put("Content",Content);
		paramMap.put("_imageData_contentType",_imageData_contentType);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("persistCompDocContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getBlogEntry")
	public ResponseEntity<Object> getBlogEntry(HttpSession session, @RequestParam(value="blogContentId") String blogContentId, @RequestParam(value="contentId", required=false) String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("blogContentId",blogContentId);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getBlogEntry", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getAssocAndContentAndDataResourceCache")
	public ResponseEntity<Object> getAssocAndContentAndDataResourceCache(HttpSession session, @RequestParam(value="fromDateStr", required=false) String fromDateStr, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="assocTypes", required=false) List assocTypes, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="nullThruDatesOnly", required=false) Boolean nullThruDatesOnly, @RequestParam(value="contentAssocPredicateId", required=false) String contentAssocPredicateId, @RequestParam(value="thruDateStr", required=false) String thruDateStr, @RequestParam(value="contentTypesString", required=false) String contentTypesString, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="assocTypesString", required=false) String assocTypesString, @RequestParam(value="contentTypes", required=false) List contentTypes, @RequestParam(value="direction", required=false) String direction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDateStr",fromDateStr);
		paramMap.put("mapKey",mapKey);
		paramMap.put("contentId",contentId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("assocTypes",assocTypes);
		paramMap.put("fromDate",fromDate);
		paramMap.put("nullThruDatesOnly",nullThruDatesOnly);
		paramMap.put("contentAssocPredicateId",contentAssocPredicateId);
		paramMap.put("thruDateStr",thruDateStr);
		paramMap.put("contentTypesString",contentTypesString);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("assocTypesString",assocTypesString);
		paramMap.put("contentTypes",contentTypes);
		paramMap.put("direction",direction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getAssocAndContentAndDataResourceCache", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/persistCompDocPdf2Survey")
	public ResponseEntity<Object> persistCompDocPdf2Survey(HttpSession session, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="imageData", required=false) java.nio.ByteBuffer imageData, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="rootContentId", required=false) String rootContentId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="forceElectronicText", required=false) String forceElectronicText, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="DataResource", required=false) String DataResource, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="_imageData_fileName", required=false) String _imageData_fileName, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="Content", required=false) String Content, @RequestParam(value="_imageData_contentType", required=false) String _imageData_contentType, @RequestParam(value="pdfName", required=false) String pdfName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("imageData",imageData);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("rootContentId",rootContentId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("forceElectronicText",forceElectronicText);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("DataResource",DataResource);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("_imageData_fileName",_imageData_fileName);
		paramMap.put("userLogin",userLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("localeString",localeString);
		paramMap.put("Content",Content);
		paramMap.put("_imageData_contentType",_imageData_contentType);
		paramMap.put("pdfName",pdfName);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("persistCompDocPdf2Survey", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/persistContentRevisionAndItem")
	public ResponseEntity<Object> persistContentRevisionAndItem(HttpSession session, @RequestParam(value="oldDataResourceId", required=false) String oldDataResourceId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="newDataResourceId", required=false) String newDataResourceId, @RequestParam(value="itemContentId", required=false) String itemContentId, @RequestParam(value="committedByPartyId", required=false) String committedByPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("oldDataResourceId",oldDataResourceId);
		paramMap.put("comments",comments);
		paramMap.put("contentId",contentId);
		paramMap.put("newDataResourceId",newDataResourceId);
		paramMap.put("itemContentId",itemContentId);
		paramMap.put("committedByPartyId",committedByPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("persistContentRevisionAndItem", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/genCompDocInstance")
	public ResponseEntity<Object> genCompDocInstance(HttpSession session, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="rootInstanceContentId", required=false) String rootInstanceContentId, @RequestParam(value="contentName", required=false) String contentName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("rootInstanceContentId",rootInstanceContentId);
		paramMap.put("contentName",contentName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("genCompDocInstance", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cloneInstanceContentApprovals")
	public ResponseEntity<Object> cloneInstanceContentApprovals(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="contentRevisionSeqId", required=false) String contentRevisionSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("contentRevisionSeqId",contentRevisionSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cloneInstanceContentApprovals", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getApprovalsWithPermissions")
	public ResponseEntity<Object> getApprovalsWithPermissions(HttpSession session, @RequestParam(value="userLogin") org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="contentRevisionSeqId") String contentRevisionSeqId, @RequestParam(value="rootContentId") String rootContentId, @RequestParam(value="checkPermission", required=false) String checkPermission) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin",userLogin);
		paramMap.put("contentRevisionSeqId",contentRevisionSeqId);
		paramMap.put("rootContentId",rootContentId);
		paramMap.put("checkPermission",checkPermission);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getApprovalsWithPermissions", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/genericDataResourcePermission")
	public ResponseEntity<Object> genericDataResourcePermission(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("genericDataResourcePermission", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/hasApprovalPermission")
	public ResponseEntity<Object> hasApprovalPermission(HttpSession session, @RequestParam(value="userLogin") org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="contentApprovalId") String contentApprovalId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin",userLogin);
		paramMap.put("contentApprovalId",contentApprovalId);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("hasApprovalPermission", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createBlogEntry")
	public ResponseEntity<Object> createBlogEntry(HttpSession session, @RequestParam(value="blogContentId") String blogContentId, @RequestParam(value="_uploadedFile_contentType", required=false) String _uploadedFile_contentType, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="description", required=false) String description, @RequestParam(value="articleData", required=false) String articleData, @RequestParam(value="summaryData", required=false) String summaryData, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="_uploadedFile_fileName", required=false) String _uploadedFile_fileName, @RequestParam(value="contentName", required=false) String contentName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("blogContentId",blogContentId);
		paramMap.put("_uploadedFile_contentType",_uploadedFile_contentType);
		paramMap.put("statusId",statusId);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("description",description);
		paramMap.put("articleData",articleData);
		paramMap.put("summaryData",summaryData);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("_uploadedFile_fileName",_uploadedFile_fileName);
		paramMap.put("contentName",contentName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createBlogEntry", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getSubSubContentWithPermCheck")
	public ResponseEntity<Object> getSubSubContentWithPermCheck(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="filterByDate", required=false) Boolean filterByDate, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="contentOperationId", required=false) String contentOperationId, @RequestParam(value="useCache", required=false) Boolean useCache, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="subMapKey", required=false) String subMapKey, @RequestParam(value="mainAction", required=false) String mainAction, @RequestParam(value="subContentAssocTypeId", required=false) String subContentAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("filterByDate",filterByDate);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("contentOperationId",contentOperationId);
		paramMap.put("useCache",useCache);
		paramMap.put("mapKey",mapKey);
		paramMap.put("subMapKey",subMapKey);
		paramMap.put("mainAction",mainAction);
		paramMap.put("subContentAssocTypeId",subContentAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getSubSubContentWithPermCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getSubContentWithPermCheck")
	public ResponseEntity<Object> getSubContentWithPermCheck(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="filterByDate", required=false) Boolean filterByDate, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="contentOperationId", required=false) String contentOperationId, @RequestParam(value="useCache", required=false) Boolean useCache, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("filterByDate",filterByDate);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("contentOperationId",contentOperationId);
		paramMap.put("useCache",useCache);
		paramMap.put("mapKey",mapKey);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getSubContentWithPermCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createDownloadContent")
	public ResponseEntity<Object> createDownloadContent(HttpSession session, @RequestParam(value="file") String file, @RequestParam(value="contentId", required=false) String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("file",file);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDownloadContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getSubContent")
	public ResponseEntity<Object> getSubContent(HttpSession session, @RequestParam(value="assocTypes", required=false) List assocTypes, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="subContentId", required=false) String subContentId, @RequestParam(value="assocTypesString", required=false) String assocTypesString, @RequestParam(value="contentTypes", required=false) List contentTypes) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("assocTypes",assocTypes);
		paramMap.put("fromDate",fromDate);
		paramMap.put("mapKey",mapKey);
		paramMap.put("contentId",contentId);
		paramMap.put("subContentId",subContentId);
		paramMap.put("assocTypesString",assocTypesString);
		paramMap.put("contentTypes",contentTypes);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getSubContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createWebPreferenceType")
	public ResponseEntity<Object> createWebPreferenceType(HttpSession session, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="contentRevisionSeqId", required=false) String contentRevisionSeqId, @RequestParam(value="itemContentId", required=false) String itemContentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("description",description);
		paramMap.put("contentRevisionSeqId",contentRevisionSeqId);
		paramMap.put("itemContentId",itemContentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWebPreferenceType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/decrementContentChildStats")
	public ResponseEntity<Object> decrementContentChildStats(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("decrementContentChildStats", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/renderSubContentAsText")
	public ResponseEntity<Object> renderSubContentAsText(HttpSession session, @RequestParam(value="templateContext") Map templateContext, @RequestParam(value="outWriter") java.io.Writer outWriter, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="subContentId", required=false) String subContentId, @RequestParam(value="subContentDataResourceView", required=false) org.apache.ofbiz.entity.GenericValue subContentDataResourceView, @RequestParam(value="locale", required=false) String locale) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("templateContext",templateContext);
		paramMap.put("outWriter",outWriter);
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentId",contentId);
		paramMap.put("mapKey",mapKey);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("subContentId",subContentId);
		paramMap.put("subContentDataResourceView",subContentDataResourceView);
		paramMap.put("locale",locale);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("renderSubContentAsText", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateDownloadContent")
	public ResponseEntity<Object> updateDownloadContent(HttpSession session, @RequestParam(value="file", required=false) String file, @RequestParam(value="fileDataResourceId", required=false) String fileDataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("file",file);
		paramMap.put("fileDataResourceId",fileDataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDownloadContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createContentAlternativeUrl")
	public ResponseEntity<Object> createContentAlternativeUrl(HttpSession session, @RequestParam(value="contentId", required=false) String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContentAlternativeUrl", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createSimpleTextContent")
	public ResponseEntity<Object> createSimpleTextContent(HttpSession session, @RequestParam(value="text") String text, @RequestParam(value="contentId", required=false) String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("text",text);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSimpleTextContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/findContentParents")
	public ResponseEntity<Object> findContentParents(HttpSession session, @RequestParam(value="contentAssocTypeId") String contentAssocTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="direction", required=false) String direction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("direction",direction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findContentParents", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateSiteRolesDyn")
	public ResponseEntity<Object> updateSiteRolesDyn(HttpSession session, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSiteRolesDyn", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getMostRecentRevision")
	public ResponseEntity<Object> getMostRecentRevision(HttpSession session, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getMostRecentRevision", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createArticleContent")
	public ResponseEntity<Object> createArticleContent(HttpSession session, @RequestParam(value="pubPtContentId") String pubPtContentId, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="summaryData", required=false) String summaryData, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="_uploadedFile_fileName", required=false) String _uploadedFile_fileName, @RequestParam(value="childBranchCount", required=false) Long childBranchCount, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="_uploadedFile_contentType", required=false) String _uploadedFile_contentType, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="threadContentId", required=false) String threadContentId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("pubPtContentId",pubPtContentId);
		paramMap.put("surveyId",surveyId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("partyId",partyId);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("summaryData",summaryData);
		paramMap.put("fromDate",fromDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("_uploadedFile_fileName",_uploadedFile_fileName);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("description",description);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("_uploadedFile_contentType",_uploadedFile_contentType);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("mapKey",mapKey);
		paramMap.put("serviceName",serviceName);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("contentName",contentName);
		paramMap.put("threadContentId",threadContentId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createArticleContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/renderDataResourceAsText")
	public ResponseEntity<Object> renderDataResourceAsText(HttpSession session, @RequestParam(value="templateContext") Map templateContext, @RequestParam(value="outWriter") java.io.Writer outWriter, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="subContentDataResourceView", required=false) org.apache.ofbiz.entity.GenericValue subContentDataResourceView, @RequestParam(value="locale", required=false) String locale) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("templateContext",templateContext);
		paramMap.put("outWriter",outWriter);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("subContentDataResourceView",subContentDataResourceView);
		paramMap.put("locale",locale);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("renderDataResourceAsText", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/contentManagerPermission")
	public ResponseEntity<Object> contentManagerPermission(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("contentManagerPermission", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/prepForApproval")
	public ResponseEntity<Object> prepForApproval(HttpSession session, @RequestParam(value="rootContentId") String rootContentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rootContentId",rootContentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("prepForApproval", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deactivateAssocs")
	public ResponseEntity<Object> deactivateAssocs(HttpSession session, @RequestParam(value="contentAssocTypeId") String contentAssocTypeId, @RequestParam(value="contentIdTo") String contentIdTo, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="activeContentId", required=false) String activeContentId, @RequestParam(value="contentId", required=false) String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("fromDate",fromDate);
		paramMap.put("mapKey",mapKey);
		paramMap.put("activeContentId",activeContentId);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deactivateAssocs", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateSiteRoles")
	public ResponseEntity<Object> updateSiteRoles(HttpSession session, @RequestParam(value="blogPublisher", required=false) String blogPublisher, @RequestParam(value="blogAuthorFromDate", required=false) Timestamp blogAuthorFromDate, @RequestParam(value="blogAdmin", required=false) String blogAdmin, @RequestParam(value="blogEditorFromDate", required=false) Timestamp blogEditorFromDate, @RequestParam(value="blogAdminFromDate", required=false) Timestamp blogAdminFromDate, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="blogUser", required=false) String blogUser, @RequestParam(value="blogUserFromDate", required=false) Timestamp blogUserFromDate, @RequestParam(value="blogPublisherFromDate", required=false) Timestamp blogPublisherFromDate, @RequestParam(value="blogEditor", required=false) String blogEditor, @RequestParam(value="blogAuthor", required=false) String blogAuthor) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("blogPublisher",blogPublisher);
		paramMap.put("blogAuthorFromDate",blogAuthorFromDate);
		paramMap.put("blogAdmin",blogAdmin);
		paramMap.put("blogEditorFromDate",blogEditorFromDate);
		paramMap.put("blogAdminFromDate",blogAdminFromDate);
		paramMap.put("contentId",contentId);
		paramMap.put("partyId",partyId);
		paramMap.put("blogUser",blogUser);
		paramMap.put("blogUserFromDate",blogUserFromDate);
		paramMap.put("blogPublisherFromDate",blogPublisherFromDate);
		paramMap.put("blogEditor",blogEditor);
		paramMap.put("blogAuthor",blogAuthor);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSiteRoles", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPublicForumMessage")
	public ResponseEntity<Object> getPublicForumMessage(HttpSession session, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPublicForumMessage", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createDataCategory")
	public ResponseEntity<Object> createDataCategory(HttpSession session, @RequestParam(value="dataCategoryId") String dataCategoryId, @RequestParam(value="parentCategoryId", required=false) String parentCategoryId, @RequestParam(value="categoryName", required=false) String categoryName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("parentCategoryId",parentCategoryId);
		paramMap.put("categoryName",categoryName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDataCategory", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/incrementContentChildStats")
	public ResponseEntity<Object> incrementContentChildStats(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("incrementContentChildStats", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/followNodeChildren")
	public ResponseEntity<Object> followNodeChildren(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="serviceName") String serviceName, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("serviceName",serviceName);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("followNodeChildren", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/clearContentAssocDataResourceViewCache")
	public ResponseEntity<Object> clearContentAssocDataResourceViewCache(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("clearContentAssocDataResourceViewCache", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/joinString")
	public ResponseEntity<Object> joinString(HttpSession session, @RequestParam(value="inputList") java.util.List inputList, @RequestParam(value="delimiter", required=false) String delimiter) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inputList",inputList);
		paramMap.put("delimiter",delimiter);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("joinString", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmailContent")
	public ResponseEntity<Object> updateEmailContent(HttpSession session, @RequestParam(value="plainBodyDataResourceId", required=false) String plainBodyDataResourceId, @RequestParam(value="htmlBodyDataResourceId", required=false) String htmlBodyDataResourceId, @RequestParam(value="htmlBody", required=false) String htmlBody, @RequestParam(value="subjectDataResourceId", required=false) String subjectDataResourceId, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="plainBody", required=false) String plainBody) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("plainBodyDataResourceId",plainBodyDataResourceId);
		paramMap.put("htmlBodyDataResourceId",htmlBodyDataResourceId);
		paramMap.put("htmlBody",htmlBody);
		paramMap.put("subjectDataResourceId",subjectDataResourceId);
		paramMap.put("subject",subject);
		paramMap.put("plainBody",plainBody);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmailContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/publishContent")
	public ResponseEntity<Object> publishContent(HttpSession session, @RequestParam(value="content") org.apache.ofbiz.entity.GenericValue content) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("content",content);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("publishContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getAssocAndContentAndDataResource")
	public ResponseEntity<Object> getAssocAndContentAndDataResource(HttpSession session, @RequestParam(value="assocTypes", required=false) List assocTypes, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="nullThruDatesOnly", required=false) Boolean nullThruDatesOnly, @RequestParam(value="thruDateStr", required=false) String thruDateStr, @RequestParam(value="fromDateStr", required=false) String fromDateStr, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="contentTypes", required=false) List contentTypes, @RequestParam(value="direction", required=false) String direction, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("assocTypes",assocTypes);
		paramMap.put("fromDate",fromDate);
		paramMap.put("nullThruDatesOnly",nullThruDatesOnly);
		paramMap.put("thruDateStr",thruDateStr);
		paramMap.put("fromDateStr",fromDateStr);
		paramMap.put("mapKey",mapKey);
		paramMap.put("contentId",contentId);
		paramMap.put("contentTypes",contentTypes);
		paramMap.put("direction",direction);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getAssocAndContentAndDataResource", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getContent")
	public ResponseEntity<Object> getContent(HttpSession session, @RequestParam(value="contentId", required=false) String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateDataResourceAttribute")
	public ResponseEntity<Object> updateDataResourceAttribute(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDataResourceAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/persistContentAndAssoc")
	public ResponseEntity<Object> persistContentAndAssoc(HttpSession session, @RequestParam(value="caFromDate", required=false) Timestamp caFromDate, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="caContentId", required=false) String caContentId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="imageData", required=false) java.nio.ByteBuffer imageData, @RequestParam(value="upperCoordinate", required=false) Long upperCoordinate, @RequestParam(value="leftCoordinate", required=false) Long leftCoordinate, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="contentAssocPredicateId", required=false) String contentAssocPredicateId, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="deactivateExisting", required=false) String deactivateExisting, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="caContentAssocTypeId", required=false) String caContentAssocTypeId, @RequestParam(value="caSequenceNum", required=false) Long caSequenceNum, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="forceElectronicText", required=false) String forceElectronicText, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="DataResource", required=false) String DataResource, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="_imageData_fileName", required=false) String _imageData_fileName, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="Content", required=false) String Content, @RequestParam(value="_imageData_contentType", required=false) String _imageData_contentType, @RequestParam(value="caContentIdTo", required=false) String caContentIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("caFromDate",caFromDate);
		paramMap.put("surveyId",surveyId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("caContentId",caContentId);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("imageData",imageData);
		paramMap.put("upperCoordinate",upperCoordinate);
		paramMap.put("leftCoordinate",leftCoordinate);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("contentAssocPredicateId",contentAssocPredicateId);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("deactivateExisting",deactivateExisting);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("caContentAssocTypeId",caContentAssocTypeId);
		paramMap.put("caSequenceNum",caSequenceNum);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("forceElectronicText",forceElectronicText);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("mapKey",mapKey);
		paramMap.put("DataResource",DataResource);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("_imageData_fileName",_imageData_fileName);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin",userLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("localeString",localeString);
		paramMap.put("Content",Content);
		paramMap.put("_imageData_contentType",_imageData_contentType);
		paramMap.put("caContentIdTo",caContentIdTo);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("persistContentAndAssoc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePageType")
	public ResponseEntity<Object> updatePageType(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="pageMode", required=false) String pageMode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("pageMode",pageMode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePageType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createDataResourceAttribute")
	public ResponseEntity<Object> createDataResourceAttribute(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDataResourceAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeDataResourceRole")
	public ResponseEntity<Object> removeDataResourceRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeDataResourceRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/initContentChildCounts")
	public ResponseEntity<Object> initContentChildCounts(HttpSession session, @RequestParam(value="content") org.apache.ofbiz.entity.GenericValue content) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("content",content);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("initContentChildCounts", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentSubscriptionByProduct")
	public ResponseEntity<Object> updateContentSubscriptionByProduct(HttpSession session, @RequestParam(value="quantity") Integer quantity, @RequestParam(value="productId") String productId, @RequestParam(value="partyId") String partyId, @RequestParam(value="orderCreatedDate", required=false) Timestamp orderCreatedDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quantity",quantity);
		paramMap.put("productId",productId);
		paramMap.put("partyId",partyId);
		paramMap.put("orderCreatedDate",orderCreatedDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentSubscriptionByProduct", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createDataResourceRole")
	public ResponseEntity<Object> createDataResourceRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDataResourceRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/resequence")
	public ResponseEntity<Object> resequence(HttpSession session, @RequestParam(value="contentIdTo") String contentIdTo, @RequestParam(value="typeList", required=false) List typeList, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="dir", required=false) String dir, @RequestParam(value="seqInc", required=false) Integer seqInc) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("typeList",typeList);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("dir",dir);
		paramMap.put("seqInc",seqInc);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("resequence", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/contentManagerRolePermission")
	public ResponseEntity<Object> contentManagerRolePermission(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("contentManagerRolePermission", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/persistContentWithRevision")
	public ResponseEntity<Object> persistContentWithRevision(HttpSession session, @RequestParam(value="masterRevisionContentId") String masterRevisionContentId, @RequestParam(value="caFromDate", required=false) Timestamp caFromDate, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="caContentId", required=false) String caContentId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="imageData", required=false) java.nio.ByteBuffer imageData, @RequestParam(value="upperCoordinate", required=false) Long upperCoordinate, @RequestParam(value="leftCoordinate", required=false) Long leftCoordinate, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="contentAssocPredicateId", required=false) String contentAssocPredicateId, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="deactivateExisting", required=false) String deactivateExisting, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="caContentAssocTypeId", required=false) String caContentAssocTypeId, @RequestParam(value="caSequenceNum", required=false) Long caSequenceNum, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="forceElectronicText", required=false) String forceElectronicText, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="DataResource", required=false) String DataResource, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="_imageData_fileName", required=false) String _imageData_fileName, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="Content", required=false) String Content, @RequestParam(value="_imageData_contentType", required=false) String _imageData_contentType, @RequestParam(value="caContentIdTo", required=false) String caContentIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("masterRevisionContentId",masterRevisionContentId);
		paramMap.put("caFromDate",caFromDate);
		paramMap.put("surveyId",surveyId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("caContentId",caContentId);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("imageData",imageData);
		paramMap.put("upperCoordinate",upperCoordinate);
		paramMap.put("leftCoordinate",leftCoordinate);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("contentAssocPredicateId",contentAssocPredicateId);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("deactivateExisting",deactivateExisting);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("caContentAssocTypeId",caContentAssocTypeId);
		paramMap.put("caSequenceNum",caSequenceNum);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("forceElectronicText",forceElectronicText);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("mapKey",mapKey);
		paramMap.put("DataResource",DataResource);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("_imageData_fileName",_imageData_fileName);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin",userLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("localeString",localeString);
		paramMap.put("Content",Content);
		paramMap.put("_imageData_contentType",_imageData_contentType);
		paramMap.put("caContentIdTo",caContentIdTo);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("persistContentWithRevision", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/generateBlogRssFeed")
	public ResponseEntity<Object> generateBlogRssFeed(HttpSession session, @RequestParam(value="entryLink") String entryLink, @RequestParam(value="feedType") String feedType, @RequestParam(value="mainLink") String mainLink, @RequestParam(value="blogContentId") String blogContentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("entryLink",entryLink);
		paramMap.put("feedType",feedType);
		paramMap.put("mainLink",mainLink);
		paramMap.put("blogContentId",blogContentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("generateBlogRssFeed", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createEmailContent")
	public ResponseEntity<Object> createEmailContent(HttpSession session, @RequestParam(value="subject") String subject, @RequestParam(value="plainBody") String plainBody, @RequestParam(value="htmlBody", required=false) String htmlBody, @RequestParam(value="contentId", required=false) String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subject",subject);
		paramMap.put("plainBody",plainBody);
		paramMap.put("htmlBody",htmlBody);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmailContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/findAssocContent")
	public ResponseEntity<Object> findAssocContent(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="mapKeys") List mapKeys, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ContentAssoc", required=false) String ContentAssoc, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="upperCoordinate", required=false) Long upperCoordinate, @RequestParam(value="leftCoordinate", required=false) Long leftCoordinate, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="contentAssocPredicateId", required=false) String contentAssocPredicateId, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="Content", required=false) String Content, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("mapKeys",mapKeys);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ContentAssoc",ContentAssoc);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("upperCoordinate",upperCoordinate);
		paramMap.put("leftCoordinate",leftCoordinate);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("contentAssocPredicateId",contentAssocPredicateId);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("mapKey",mapKey);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("contentName",contentName);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("Content",Content);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findAssocContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentChildStats")
	public ResponseEntity<Object> updateContentChildStats(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentChildStats", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/changeLeafToNode")
	public ResponseEntity<Object> changeLeafToNode(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin",userLogin);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("changeLeafToNode", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateWebPreferenceType")
	public ResponseEntity<Object> updateWebPreferenceType(HttpSession session, @RequestParam(value="webPreferenceTypeId") String webPreferenceTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("webPreferenceTypeId",webPreferenceTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWebPreferenceType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/persistCompDoc")
	public ResponseEntity<Object> persistCompDoc(HttpSession session, @RequestParam(value="caFromDate", required=false) Timestamp caFromDate, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="caContentId", required=false) String caContentId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="imageData", required=false) java.nio.ByteBuffer imageData, @RequestParam(value="upperCoordinate", required=false) Long upperCoordinate, @RequestParam(value="leftCoordinate", required=false) Long leftCoordinate, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="rootContentId", required=false) String rootContentId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="contentAssocPredicateId", required=false) String contentAssocPredicateId, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="deactivateExisting", required=false) String deactivateExisting, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="caContentAssocTypeId", required=false) String caContentAssocTypeId, @RequestParam(value="caSequenceNum", required=false) Long caSequenceNum, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="forceElectronicText", required=false) String forceElectronicText, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="DataResource", required=false) String DataResource, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="_imageData_fileName", required=false) String _imageData_fileName, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="Content", required=false) String Content, @RequestParam(value="_imageData_contentType", required=false) String _imageData_contentType, @RequestParam(value="caContentIdTo", required=false) String caContentIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("caFromDate",caFromDate);
		paramMap.put("surveyId",surveyId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("caContentId",caContentId);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("imageData",imageData);
		paramMap.put("upperCoordinate",upperCoordinate);
		paramMap.put("leftCoordinate",leftCoordinate);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("rootContentId",rootContentId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("contentAssocPredicateId",contentAssocPredicateId);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("deactivateExisting",deactivateExisting);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("caContentAssocTypeId",caContentAssocTypeId);
		paramMap.put("caSequenceNum",caSequenceNum);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("forceElectronicText",forceElectronicText);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("mapKey",mapKey);
		paramMap.put("DataResource",DataResource);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("_imageData_fileName",_imageData_fileName);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin",userLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("localeString",localeString);
		paramMap.put("Content",Content);
		paramMap.put("_imageData_contentType",_imageData_contentType);
		paramMap.put("caContentIdTo",caContentIdTo);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("persistCompDoc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createTopic")
	public ResponseEntity<Object> createTopic(HttpSession session, @RequestParam(value="newTopicId") String newTopicId, @RequestParam(value="newTopicDescription", required=false) String newTopicDescription) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("newTopicId",newTopicId);
		paramMap.put("newTopicDescription",newTopicDescription);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTopic", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createImage")
	public ResponseEntity<Object> createImage(HttpSession session, @RequestParam(value="imageData") java.nio.ByteBuffer imageData, @RequestParam(value="dataResourceId") String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("imageData",imageData);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createImage", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPrefixedMembers")
	public ResponseEntity<Object> getPrefixedMembers(HttpSession session, @RequestParam(value="prefix") String prefix, @RequestParam(value="mapIn", required=false) java.util.Map mapIn) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prefix",prefix);
		paramMap.put("mapIn",mapIn);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPrefixedMembers", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/resetToOutlineMode")
	public ResponseEntity<Object> resetToOutlineMode(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="pageMode", required=false) String pageMode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("pageMode",pageMode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("resetToOutlineMode", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getOwnedOrPublishedBlogEntries")
	public ResponseEntity<Object> getOwnedOrPublishedBlogEntries(HttpSession session, @RequestParam(value="userLogin") org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin",userLogin);
		paramMap.put("contentId",contentId);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getOwnedOrPublishedBlogEntries", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/genericContentPermission")
	public ResponseEntity<Object> genericContentPermission(HttpSession session, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentOperationId", required=false) String contentOperationId, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("statusId",statusId);
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentOperationId",contentOperationId);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("altPermission",altPermission);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("contentId",contentId);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("genericContentPermission", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/renderContentPdf")
	public ResponseEntity<Object> renderContentPdf(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="contentRevisionSeqId", required=false) String contentRevisionSeqId, @RequestParam(value="https", required=false) String https, @RequestParam(value="locale", required=false) java.util.Locale locale, @RequestParam(value="webSiteId", required=false) String webSiteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("contentRevisionSeqId",contentRevisionSeqId);
		paramMap.put("https",https);
		paramMap.put("locale",locale);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("renderContentPdf", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deactivateContentAssoc")
	public ResponseEntity<Object> deactivateContentAssoc(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="contentId", required=false) String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deactivateContentAssoc", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/splitString")
	public ResponseEntity<Object> splitString(HttpSession session, @RequestParam(value="delimiter", required=false) String delimiter, @RequestParam(value="inputString", required=false) String inputString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("delimiter",delimiter);
		paramMap.put("inputString",inputString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("splitString", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/linkContentToPubPt")
	public ResponseEntity<Object> linkContentToPubPt(HttpSession session, @RequestParam(value="contentIdTo") String contentIdTo, @RequestParam(value="contentId") String contentId, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="publish", required=false) String publish, @RequestParam(value="mapKey", required=false) String mapKey) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentId",contentId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("statusId",statusId);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("publish",publish);
		paramMap.put("mapKey",mapKey);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("linkContentToPubPt", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentSubscription")
	public ResponseEntity<Object> updateContentSubscription(HttpSession session, @RequestParam(value="useTimeUomId") String useTimeUomId, @RequestParam(value="useTime") Integer useTime, @RequestParam(value="partyId") String partyId, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="contentId", required=false) String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("useTime",useTime);
		paramMap.put("partyId",partyId);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentSubscription", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/uploadFileInterface")
	public ResponseEntity<Object> uploadFileInterface(HttpSession session, @RequestParam(value="_uploadedFile_contentType", required=false) String _uploadedFile_contentType, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="_uploadedFile_fileName", required=false) String _uploadedFile_fileName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("_uploadedFile_contentType",_uploadedFile_contentType);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("_uploadedFile_fileName",_uploadedFile_fileName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("uploadFileInterface", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/renderContentAsText")
	public ResponseEntity<Object> renderContentAsText(HttpSession session, @RequestParam(value="templateContext", required=false) Map templateContext, @RequestParam(value="outWriter", required=false) java.io.Writer outWriter, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="subContentDataResourceView", required=false) org.apache.ofbiz.entity.GenericValue subContentDataResourceView, @RequestParam(value="locale", required=false) String locale) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("templateContext",templateContext);
		paramMap.put("outWriter",outWriter);
		paramMap.put("contentId",contentId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("subContentDataResourceView",subContentDataResourceView);
		paramMap.put("locale",locale);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("renderContentAsText", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getContentAndDataResource")
	public ResponseEntity<Object> getContentAndDataResource(HttpSession session, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getContentAndDataResource", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/persistDataResourceAndData")
	public ResponseEntity<Object> persistDataResourceAndData(HttpSession session, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="imageData", required=false) java.nio.ByteBuffer imageData, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="forceElectronicText", required=false) String forceElectronicText, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="DataResource", required=false) String DataResource, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="_imageData_fileName", required=false) String _imageData_fileName, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="Content", required=false) String Content, @RequestParam(value="_imageData_contentType", required=false) String _imageData_contentType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("imageData",imageData);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("forceElectronicText",forceElectronicText);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("DataResource",DataResource);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("_imageData_fileName",_imageData_fileName);
		paramMap.put("userLogin",userLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("localeString",localeString);
		paramMap.put("Content",Content);
		paramMap.put("_imageData_contentType",_imageData_contentType);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("persistDataResourceAndData", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getDataResource")
	public ResponseEntity<Object> getDataResource(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getDataResource", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeDataResourceAttribute")
	public ResponseEntity<Object> removeDataResourceAttribute(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeDataResourceAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/findSubNodes")
	public ResponseEntity<Object> findSubNodes(HttpSession session, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findSubNodes", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWebPreferenceType")
	public ResponseEntity<Object> deleteWebPreferenceType(HttpSession session, @RequestParam(value="webPreferenceTypeId") String webPreferenceTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("webPreferenceTypeId",webPreferenceTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWebPreferenceType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateSimpleTextContent")
	public ResponseEntity<Object> updateSimpleTextContent(HttpSession session, @RequestParam(value="text", required=false) String text, @RequestParam(value="textDataResourceId", required=false) String textDataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("text",text);
		paramMap.put("textDataResourceId",textDataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSimpleTextContent", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkForWaitingApprovals")
	public ResponseEntity<Object> checkForWaitingApprovals(HttpSession session, @RequestParam(value="thisUserLogin", required=false) org.apache.ofbiz.entity.GenericValue thisUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("thisUserLogin",thisUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkForWaitingApprovals", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeDataCategory")
	public ResponseEntity<Object> removeDataCategory(HttpSession session, @RequestParam(value="dataCategoryId") String dataCategoryId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeDataCategory", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentSubscriptionByOrder")
	public ResponseEntity<Object> updateContentSubscriptionByOrder(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentSubscriptionByOrder", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/clearContentAssocViewCache")
	public ResponseEntity<Object> clearContentAssocViewCache(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("clearContentAssocViewCache", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/renderCompDocPdf")
	public ResponseEntity<Object> renderCompDocPdf(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="contentRevisionSeqId", required=false) String contentRevisionSeqId, @RequestParam(value="https", required=false) String https, @RequestParam(value="locale", required=false) java.util.Locale locale, @RequestParam(value="webSiteId", required=false) String webSiteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("contentRevisionSeqId",contentRevisionSeqId);
		paramMap.put("https",https);
		paramMap.put("locale",locale);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("renderCompDocPdf", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(value = (" * "))
	public ResponseEntity<Object> returnErrorPage(HttpSession session) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested service does not exist. JSESSIONID=" + session.getId());
	}

}
