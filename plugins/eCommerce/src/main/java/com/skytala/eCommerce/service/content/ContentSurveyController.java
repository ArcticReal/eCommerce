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
@RequestMapping("/service/ContentSurveyController")
public class ContentSurveyController{

	@RequestMapping(method = RequestMethod.POST, value = "/updateSurvey")
	public ResponseEntity<Object> updateSurvey(HttpSession session, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="isAnonymous", required=false) String isAnonymous, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="surveyName", required=false) String surveyName, @RequestParam(value="allowMultiple", required=false) String allowMultiple, @RequestParam(value="responseService", required=false) String responseService, @RequestParam(value="description", required=false) String description, @RequestParam(value="acroFormContentId", required=false) String acroFormContentId, @RequestParam(value="allowUpdate", required=false) String allowUpdate, @RequestParam(value="submitCaption", required=false) String submitCaption) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("isAnonymous",isAnonymous);
		paramMap.put("comments",comments);
		paramMap.put("surveyName",surveyName);
		paramMap.put("allowMultiple",allowMultiple);
		paramMap.put("responseService",responseService);
		paramMap.put("description",description);
		paramMap.put("acroFormContentId",acroFormContentId);
		paramMap.put("allowUpdate",allowUpdate);
		paramMap.put("submitCaption",submitCaption);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSurvey", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSurveyQuestionAppl")
	public ResponseEntity<Object> deleteSurveyQuestionAppl(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="surveyQuestionId") String surveyQuestionId, @RequestParam(value="surveyId") String surveyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("surveyQuestionId",surveyQuestionId);
		paramMap.put("surveyId",surveyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSurveyQuestionAppl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSurveyQuestion")
	public ResponseEntity<Object> deleteSurveyQuestion(HttpSession session, @RequestParam(value="surveyQuestionId") String surveyQuestionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyQuestionId",surveyQuestionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSurveyQuestion", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/buildSurveyQuestionsAndAnswers")
	public ResponseEntity<Object> buildSurveyQuestionsAndAnswers(HttpSession session, @RequestParam(value="surveyResponseId") String surveyResponseId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("buildSurveyQuestionsAndAnswers", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSurveyPage")
	public ResponseEntity<Object> createSurveyPage(HttpSession session, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="pageName", required=false) String pageName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("pageName",pageName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSurveyPage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSurveyQuestionType")
	public ResponseEntity<Object> updateSurveyQuestionType(HttpSession session, @RequestParam(value="surveyQuestionTypeId") String surveyQuestionTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyQuestionTypeId",surveyQuestionTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSurveyQuestionType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSurveyMultiRespColumn")
	public ResponseEntity<Object> deleteSurveyMultiRespColumn(HttpSession session, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="surveyMultiRespId") String surveyMultiRespId, @RequestParam(value="surveyMultiRespColId") String surveyMultiRespColId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("surveyMultiRespId",surveyMultiRespId);
		paramMap.put("surveyMultiRespColId",surveyMultiRespColId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSurveyMultiRespColumn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSurveyQuestionCategory")
	public ResponseEntity<Object> updateSurveyQuestionCategory(HttpSession session, @RequestParam(value="surveyQuestionCategoryId") String surveyQuestionCategoryId, @RequestParam(value="description", required=false) String description, @RequestParam(value="parentCategoryId", required=false) String parentCategoryId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyQuestionCategoryId",surveyQuestionCategoryId);
		paramMap.put("description",description);
		paramMap.put("parentCategoryId",parentCategoryId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSurveyQuestionCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSurveyTrigger")
	public ResponseEntity<Object> deleteSurveyTrigger(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="surveyApplTypeId") String surveyApplTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("surveyId",surveyId);
		paramMap.put("surveyApplTypeId",surveyApplTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSurveyTrigger", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setAcroFields")
	public ResponseEntity<Object> setAcroFields(HttpSession session, @RequestParam(value="acroFieldMap") Map acroFieldMap, @RequestParam(value="inputByteBuffer", required=false) java.nio.ByteBuffer inputByteBuffer, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="pdfFileNameIn", required=false) String pdfFileNameIn) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acroFieldMap",acroFieldMap);
		paramMap.put("inputByteBuffer",inputByteBuffer);
		paramMap.put("contentId",contentId);
		paramMap.put("pdfFileNameIn",pdfFileNameIn);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setAcroFields", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSurveyQuestion")
	public ResponseEntity<Object> updateSurveyQuestion(HttpSession session, @RequestParam(value="surveyQuestionId") String surveyQuestionId, @RequestParam(value="surveyQuestionTypeId", required=false) String surveyQuestionTypeId, @RequestParam(value="enumTypeId", required=false) String enumTypeId, @RequestParam(value="formatString", required=false) Long formatString, @RequestParam(value="surveyQuestionCategoryId", required=false) String surveyQuestionCategoryId, @RequestParam(value="question", required=false) String question, @RequestParam(value="hint", required=false) String hint, @RequestParam(value="geoId", required=false) String geoId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyQuestionId",surveyQuestionId);
		paramMap.put("surveyQuestionTypeId",surveyQuestionTypeId);
		paramMap.put("enumTypeId",enumTypeId);
		paramMap.put("formatString",formatString);
		paramMap.put("surveyQuestionCategoryId",surveyQuestionCategoryId);
		paramMap.put("question",question);
		paramMap.put("hint",hint);
		paramMap.put("geoId",geoId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSurveyQuestion", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSurveyQuestionAppl")
	public ResponseEntity<Object> updateSurveyQuestionAppl(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="surveyQuestionId") String surveyQuestionId, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="withSurveyOptionSeqId", required=false) String withSurveyOptionSeqId, @RequestParam(value="externalFieldRef", required=false) String externalFieldRef, @RequestParam(value="requiredField", required=false) String requiredField, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="withSurveyQuestionId", required=false) String withSurveyQuestionId, @RequestParam(value="surveyPageSeqId", required=false) String surveyPageSeqId, @RequestParam(value="surveyMultiRespId", required=false) String surveyMultiRespId, @RequestParam(value="surveyMultiRespColId", required=false) String surveyMultiRespColId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("surveyQuestionId",surveyQuestionId);
		paramMap.put("surveyId",surveyId);
		paramMap.put("withSurveyOptionSeqId",withSurveyOptionSeqId);
		paramMap.put("externalFieldRef",externalFieldRef);
		paramMap.put("requiredField",requiredField);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("withSurveyQuestionId",withSurveyQuestionId);
		paramMap.put("surveyPageSeqId",surveyPageSeqId);
		paramMap.put("surveyMultiRespId",surveyMultiRespId);
		paramMap.put("surveyMultiRespColId",surveyMultiRespColId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSurveyQuestionAppl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSurveyTrigger")
	public ResponseEntity<Object> createSurveyTrigger(HttpSession session, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="surveyApplTypeId") String surveyApplTypeId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("surveyApplTypeId",surveyApplTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSurveyTrigger", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSurveyApplType")
	public ResponseEntity<Object> createSurveyApplType(HttpSession session, @RequestParam(value="description", required=false) String description, @RequestParam(value="surveyApplTypeId", required=false) String surveyApplTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("surveyApplTypeId",surveyApplTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSurveyApplType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/surveyResponseProcessInterface")
	public ResponseEntity<Object> surveyResponseProcessInterface(HttpSession session, @RequestParam(value="surveyResponseId") String surveyResponseId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("surveyResponseProcessInterface", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSurveyMultiRespColumn")
	public ResponseEntity<Object> createSurveyMultiRespColumn(HttpSession session, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="surveyMultiRespId") String surveyMultiRespId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="columnTitle", required=false) String columnTitle) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("surveyMultiRespId",surveyMultiRespId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("columnTitle",columnTitle);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSurveyMultiRespColumn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSurveyQuestionCategory")
	public ResponseEntity<Object> deleteSurveyQuestionCategory(HttpSession session, @RequestParam(value="surveyQuestionCategoryId") String surveyQuestionCategoryId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyQuestionCategoryId",surveyQuestionCategoryId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSurveyQuestionCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSurveyMultiResp")
	public ResponseEntity<Object> updateSurveyMultiResp(HttpSession session, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="surveyMultiRespId") String surveyMultiRespId, @RequestParam(value="multiRespTitle", required=false) String multiRespTitle) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("surveyMultiRespId",surveyMultiRespId);
		paramMap.put("multiRespTitle",multiRespTitle);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSurveyMultiResp", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setAcroFieldsFromSurveyResponse")
	public ResponseEntity<Object> setAcroFieldsFromSurveyResponse(HttpSession session, @RequestParam(value="surveyResponseId") String surveyResponseId, @RequestParam(value="inputByteBuffer", required=false) java.nio.ByteBuffer inputByteBuffer, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="pdfFileNameIn", required=false) String pdfFileNameIn) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("inputByteBuffer",inputByteBuffer);
		paramMap.put("contentId",contentId);
		paramMap.put("pdfFileNameIn",pdfFileNameIn);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setAcroFieldsFromSurveyResponse", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSurveyTrigger")
	public ResponseEntity<Object> updateSurveyTrigger(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="surveyApplTypeId") String surveyApplTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("surveyId",surveyId);
		paramMap.put("surveyApplTypeId",surveyApplTypeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSurveyTrigger", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSurveyApplType")
	public ResponseEntity<Object> deleteSurveyApplType(HttpSession session, @RequestParam(value="surveyApplTypeId") String surveyApplTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyApplTypeId",surveyApplTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSurveyApplType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSurveyQuestionType")
	public ResponseEntity<Object> createSurveyQuestionType(HttpSession session, @RequestParam(value="surveyQuestionTypeId", required=false) String surveyQuestionTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyQuestionTypeId",surveyQuestionTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSurveyQuestionType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSurvey")
	public ResponseEntity<Object> deleteSurvey(HttpSession session, @RequestParam(value="surveyId") String surveyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSurvey", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/buildPdfFromSurveyResponse")
	public ResponseEntity<Object> buildPdfFromSurveyResponse(HttpSession session, @RequestParam(value="surveyResponseId") String surveyResponseId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("buildPdfFromSurveyResponse", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSurveyResponse")
	public ResponseEntity<Object> createSurveyResponse(HttpSession session, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="answers") Map answers, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId, @RequestParam(value="productStoreSurveyId", required=false) String productStoreSurveyId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="orderId", required=false) String orderId, @RequestParam(value="generalFeedback", required=false) String generalFeedback, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="responseDate", required=false) Timestamp responseDate, @RequestParam(value="referenceId", required=false) String referenceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("answers",answers);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("productStoreSurveyId",productStoreSurveyId);
		paramMap.put("statusId",statusId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("orderId",orderId);
		paramMap.put("generalFeedback",generalFeedback);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("partyId",partyId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("responseDate",responseDate);
		paramMap.put("referenceId",referenceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSurveyResponse", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSurveyQuestionAppl")
	public ResponseEntity<Object> createSurveyQuestionAppl(HttpSession session, @RequestParam(value="surveyQuestionId") String surveyQuestionId, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="withSurveyOptionSeqId", required=false) String withSurveyOptionSeqId, @RequestParam(value="externalFieldRef", required=false) String externalFieldRef, @RequestParam(value="requiredField", required=false) String requiredField, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="withSurveyQuestionId", required=false) String withSurveyQuestionId, @RequestParam(value="surveyPageSeqId", required=false) String surveyPageSeqId, @RequestParam(value="surveyMultiRespId", required=false) String surveyMultiRespId, @RequestParam(value="surveyMultiRespColId", required=false) String surveyMultiRespColId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyQuestionId",surveyQuestionId);
		paramMap.put("surveyId",surveyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("withSurveyOptionSeqId",withSurveyOptionSeqId);
		paramMap.put("externalFieldRef",externalFieldRef);
		paramMap.put("requiredField",requiredField);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("withSurveyQuestionId",withSurveyQuestionId);
		paramMap.put("surveyPageSeqId",surveyPageSeqId);
		paramMap.put("surveyMultiRespId",surveyMultiRespId);
		paramMap.put("surveyMultiRespColId",surveyMultiRespColId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSurveyQuestionAppl", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSurveyQuestionOption")
	public ResponseEntity<Object> createSurveyQuestionOption(HttpSession session, @RequestParam(value="surveyQuestionId") String surveyQuestionId, @RequestParam(value="amountBase", required=false) BigDecimal amountBase, @RequestParam(value="duration", required=false) Long duration, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="amountBaseUomId", required=false) String amountBaseUomId, @RequestParam(value="durationUomId", required=false) String durationUomId, @RequestParam(value="description", required=false) String description, @RequestParam(value="weightFactor", required=false) BigDecimal weightFactor) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyQuestionId",surveyQuestionId);
		paramMap.put("amountBase",amountBase);
		paramMap.put("duration",duration);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("amountBaseUomId",amountBaseUomId);
		paramMap.put("durationUomId",durationUomId);
		paramMap.put("description",description);
		paramMap.put("weightFactor",weightFactor);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSurveyQuestionOption", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSurvey")
	public ResponseEntity<Object> createSurvey(HttpSession session, @RequestParam(value="isAnonymous", required=false) String isAnonymous, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="surveyName", required=false) String surveyName, @RequestParam(value="allowMultiple", required=false) String allowMultiple, @RequestParam(value="responseService", required=false) String responseService, @RequestParam(value="description", required=false) String description, @RequestParam(value="acroFormContentId", required=false) String acroFormContentId, @RequestParam(value="allowUpdate", required=false) String allowUpdate, @RequestParam(value="submitCaption", required=false) String submitCaption) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("isAnonymous",isAnonymous);
		paramMap.put("comments",comments);
		paramMap.put("surveyName",surveyName);
		paramMap.put("allowMultiple",allowMultiple);
		paramMap.put("responseService",responseService);
		paramMap.put("description",description);
		paramMap.put("acroFormContentId",acroFormContentId);
		paramMap.put("allowUpdate",allowUpdate);
		paramMap.put("submitCaption",submitCaption);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSurvey", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSurveyApplType")
	public ResponseEntity<Object> updateSurveyApplType(HttpSession session, @RequestParam(value="surveyApplTypeId") String surveyApplTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyApplTypeId",surveyApplTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSurveyApplType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cloneSurvey")
	public ResponseEntity<Object> cloneSurvey(HttpSession session, @RequestParam(value="surveyId") String surveyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cloneSurvey", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSurveyQuestionCategory")
	public ResponseEntity<Object> createSurveyQuestionCategory(HttpSession session, @RequestParam(value="description", required=false) String description, @RequestParam(value="parentCategoryId", required=false) String parentCategoryId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("parentCategoryId",parentCategoryId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSurveyQuestionCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSurveyMultiRespColumn")
	public ResponseEntity<Object> updateSurveyMultiRespColumn(HttpSession session, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="surveyMultiRespId") String surveyMultiRespId, @RequestParam(value="surveyMultiRespColId") String surveyMultiRespColId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="columnTitle", required=false) String columnTitle) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("surveyMultiRespId",surveyMultiRespId);
		paramMap.put("surveyMultiRespColId",surveyMultiRespColId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("columnTitle",columnTitle);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSurveyMultiRespColumn", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSurveyQuestionOption")
	public ResponseEntity<Object> deleteSurveyQuestionOption(HttpSession session, @RequestParam(value="surveyQuestionId") String surveyQuestionId, @RequestParam(value="surveyOptionSeqId") String surveyOptionSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyQuestionId",surveyQuestionId);
		paramMap.put("surveyOptionSeqId",surveyOptionSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSurveyQuestionOption", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSurveyQuestion")
	public ResponseEntity<Object> createSurveyQuestion(HttpSession session, @RequestParam(value="surveyQuestionTypeId", required=false) String surveyQuestionTypeId, @RequestParam(value="enumTypeId", required=false) String enumTypeId, @RequestParam(value="formatString", required=false) Long formatString, @RequestParam(value="surveyQuestionCategoryId", required=false) String surveyQuestionCategoryId, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="question", required=false) String question, @RequestParam(value="hint", required=false) String hint, @RequestParam(value="geoId", required=false) String geoId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyQuestionTypeId",surveyQuestionTypeId);
		paramMap.put("enumTypeId",enumTypeId);
		paramMap.put("formatString",formatString);
		paramMap.put("surveyQuestionCategoryId",surveyQuestionCategoryId);
		paramMap.put("surveyId",surveyId);
		paramMap.put("question",question);
		paramMap.put("hint",hint);
		paramMap.put("geoId",geoId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSurveyQuestion", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/buildSurveyResponseFromPdf")
	public ResponseEntity<Object> buildSurveyResponseFromPdf(HttpSession session, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="inputByteBuffer", required=false) java.nio.ByteBuffer inputByteBuffer, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="pdfFileNameIn", required=false) String pdfFileNameIn, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("inputByteBuffer",inputByteBuffer);
		paramMap.put("contentId",contentId);
		paramMap.put("pdfFileNameIn",pdfFileNameIn);
		paramMap.put("partyId",partyId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("buildSurveyResponseFromPdf", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getAcroFieldsFromPdf")
	public ResponseEntity<Object> getAcroFieldsFromPdf(HttpSession session, @RequestParam(value="inputByteBuffer", required=false) java.nio.ByteBuffer inputByteBuffer, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="pdfFileNameIn", required=false) String pdfFileNameIn) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inputByteBuffer",inputByteBuffer);
		paramMap.put("contentId",contentId);
		paramMap.put("pdfFileNameIn",pdfFileNameIn);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getAcroFieldsFromPdf", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSurveyQuestionType")
	public ResponseEntity<Object> deleteSurveyQuestionType(HttpSession session, @RequestParam(value="surveyQuestionTypeId") String surveyQuestionTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyQuestionTypeId",surveyQuestionTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSurveyQuestionType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/buildSurveyFromPdf")
	public ResponseEntity<Object> buildSurveyFromPdf(HttpSession session, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="surveyName", required=false) String surveyName, @RequestParam(value="inputByteBuffer", required=false) java.nio.ByteBuffer inputByteBuffer, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="pdfFileNameIn", required=false) String pdfFileNameIn) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("surveyName",surveyName);
		paramMap.put("inputByteBuffer",inputByteBuffer);
		paramMap.put("contentId",contentId);
		paramMap.put("pdfFileNameIn",pdfFileNameIn);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("buildSurveyFromPdf", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSurveyMultiResp")
	public ResponseEntity<Object> createSurveyMultiResp(HttpSession session, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="multiRespTitle", required=false) String multiRespTitle) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("multiRespTitle",multiRespTitle);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSurveyMultiResp", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSurveyPage")
	public ResponseEntity<Object> updateSurveyPage(HttpSession session, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="surveyPageSeqId") String surveyPageSeqId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="pageName", required=false) String pageName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("surveyPageSeqId",surveyPageSeqId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("pageName",pageName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSurveyPage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSurveyQuestionOption")
	public ResponseEntity<Object> updateSurveyQuestionOption(HttpSession session, @RequestParam(value="surveyQuestionId") String surveyQuestionId, @RequestParam(value="surveyOptionSeqId") String surveyOptionSeqId, @RequestParam(value="amountBase", required=false) BigDecimal amountBase, @RequestParam(value="duration", required=false) Long duration, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="amountBaseUomId", required=false) String amountBaseUomId, @RequestParam(value="durationUomId", required=false) String durationUomId, @RequestParam(value="description", required=false) String description, @RequestParam(value="weightFactor", required=false) BigDecimal weightFactor) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyQuestionId",surveyQuestionId);
		paramMap.put("surveyOptionSeqId",surveyOptionSeqId);
		paramMap.put("amountBase",amountBase);
		paramMap.put("duration",duration);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("amountBaseUomId",amountBaseUomId);
		paramMap.put("durationUomId",durationUomId);
		paramMap.put("description",description);
		paramMap.put("weightFactor",weightFactor);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSurveyQuestionOption", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSurveyMultiResp")
	public ResponseEntity<Object> deleteSurveyMultiResp(HttpSession session, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="surveyMultiRespId") String surveyMultiRespId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("surveyMultiRespId",surveyMultiRespId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSurveyMultiResp", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSurveyPage")
	public ResponseEntity<Object> deleteSurveyPage(HttpSession session, @RequestParam(value="surveyId") String surveyId, @RequestParam(value="surveyPageSeqId") String surveyPageSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("surveyPageSeqId",surveyPageSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSurveyPage", paramMap);
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
