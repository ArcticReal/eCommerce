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
@RequestMapping("/service/contentCommevent")
public class ContentCommeventServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/createCommContentDataResource")
	public ResponseEntity<Map<String, Object>> createCommContentDataResource(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="caFromDate", required=false) Timestamp caFromDate, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="caContentId", required=false) String caContentId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="imageData", required=false) java.nio.ByteBuffer imageData, @RequestParam(value="upperCoordinate", required=false) Long upperCoordinate, @RequestParam(value="leftCoordinate", required=false) Long leftCoordinate, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="contentAssocPredicateId", required=false) String contentAssocPredicateId, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="deactivateExisting", required=false) String deactivateExisting, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="caContentAssocTypeId", required=false) String caContentAssocTypeId, @RequestParam(value="caSequenceNum", required=false) Long caSequenceNum, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="forceElectronicText", required=false) String forceElectronicText, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="DataResource", required=false) String DataResource, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="_imageData_fileName", required=false) String _imageData_fileName, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="Content", required=false) String Content, @RequestParam(value="_imageData_contentType", required=false) String _imageData_contentType, @RequestParam(value="caContentIdTo", required=false) String caContentIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("communicationEventId",communicationEventId);
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
			result = dispatcher.runSync("createCommContentDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCommContentAssocType")
	public ResponseEntity<Map<String, Object>> createCommContentAssocType(HttpSession session, @RequestParam(value="commContentAssocTypeId", required=false) String commContentAssocTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("commContentAssocTypeId",commContentAssocTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommContentAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCommEventContentAssoc")
	public ResponseEntity<Map<String, Object>> createCommEventContentAssoc(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="fromDate", required=false) java.sql.Timestamp fromDate, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) java.sql.Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommEventContentAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCommContentDataResource")
	public ResponseEntity<Map<String, Object>> updateCommContentDataResource(HttpSession session, @RequestParam(value="fromDate") java.sql.Timestamp fromDate, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="caFromDate", required=false) Timestamp caFromDate, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="caContentId", required=false) String caContentId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="imageData", required=false) java.nio.ByteBuffer imageData, @RequestParam(value="upperCoordinate", required=false) Long upperCoordinate, @RequestParam(value="leftCoordinate", required=false) Long leftCoordinate, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="contentAssocPredicateId", required=false) String contentAssocPredicateId, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="deactivateExisting", required=false) String deactivateExisting, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="caContentAssocTypeId", required=false) String caContentAssocTypeId, @RequestParam(value="caSequenceNum", required=false) Long caSequenceNum, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="forceElectronicText", required=false) String forceElectronicText, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="DataResource", required=false) String DataResource, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="_imageData_fileName", required=false) String _imageData_fileName, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="Content", required=false) String Content, @RequestParam(value="_imageData_contentType", required=false) String _imageData_contentType, @RequestParam(value="caContentIdTo", required=false) String caContentIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("communicationEventId",communicationEventId);
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
			result = dispatcher.runSync("updateCommContentDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCommEventContentAssoc")
	public ResponseEntity<Map<String, Object>> updateCommEventContentAssoc(HttpSession session, @RequestParam(value="fromDate") java.sql.Timestamp fromDate, @RequestParam(value="contentId") String contentId, @RequestParam(value="communicationEventId") String communicationEventId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) java.sql.Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentId",contentId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCommEventContentAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCommContentAssocType")
	public ResponseEntity<Map<String, Object>> updateCommContentAssocType(HttpSession session, @RequestParam(value="commContentAssocTypeId") String commContentAssocTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("commContentAssocTypeId",commContentAssocTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCommContentAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCommContentAssocType")
	public ResponseEntity<Map<String, Object>> deleteCommContentAssocType(HttpSession session, @RequestParam(value="commContentAssocTypeId") String commContentAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("commContentAssocTypeId",commContentAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCommContentAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeCommEventContentAssoc")
	public ResponseEntity<Map<String, Object>> removeCommEventContentAssoc(HttpSession session, @RequestParam(value="fromDate") java.sql.Timestamp fromDate, @RequestParam(value="contentId") String contentId, @RequestParam(value="communicationEventId") String communicationEventId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentId",contentId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeCommEventContentAssoc", paramMap);
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
