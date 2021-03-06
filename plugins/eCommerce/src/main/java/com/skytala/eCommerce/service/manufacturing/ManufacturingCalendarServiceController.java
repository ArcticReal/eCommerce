package com.skytala.eCommerce.service.manufacturing;

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
@RequestMapping("/service/manufacturingCalendar")
public class ManufacturingCalendarServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/createCalendarExceptionDay")
	public ResponseEntity<Map<String, Object>> createCalendarExceptionDay(HttpSession session, @RequestParam(value="exceptionDateStartTime") Timestamp exceptionDateStartTime, @RequestParam(value="calendarId") String calendarId, @RequestParam(value="exceptionCapacity", required=false) BigDecimal exceptionCapacity, @RequestParam(value="usedCapacity", required=false) BigDecimal usedCapacity, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("exceptionDateStartTime",exceptionDateStartTime);
		paramMap.put("calendarId",calendarId);
		paramMap.put("exceptionCapacity",exceptionCapacity);
		paramMap.put("usedCapacity",usedCapacity);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCalendarExceptionDay", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeCalendarExceptionDay")
	public ResponseEntity<Map<String, Object>> removeCalendarExceptionDay(HttpSession session, @RequestParam(value="exceptionDateStartTime") Timestamp exceptionDateStartTime, @RequestParam(value="calendarId") String calendarId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("exceptionDateStartTime",exceptionDateStartTime);
		paramMap.put("calendarId",calendarId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeCalendarExceptionDay", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCalendar")
	public ResponseEntity<Map<String, Object>> createCalendar(HttpSession session, @RequestParam(value="calendarId") String calendarId, @RequestParam(value="calendarWeekId", required=false) String calendarWeekId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("calendarId",calendarId);
		paramMap.put("calendarWeekId",calendarWeekId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCalendar", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeCalendar")
	public ResponseEntity<Map<String, Object>> removeCalendar(HttpSession session, @RequestParam(value="calendarId") String calendarId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("calendarId",calendarId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeCalendar", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeCalendarExceptionWeek")
	public ResponseEntity<Map<String, Object>> removeCalendarExceptionWeek(HttpSession session, @RequestParam(value="exceptionDateStart") Timestamp exceptionDateStart, @RequestParam(value="calendarId") String calendarId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("exceptionDateStart",exceptionDateStart);
		paramMap.put("calendarId",calendarId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeCalendarExceptionWeek", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCalendarWeek")
	public ResponseEntity<Map<String, Object>> updateCalendarWeek(HttpSession session, @RequestParam(value="calendarWeekId") String calendarWeekId, @RequestParam(value="sundayCapacity", required=false) BigDecimal sundayCapacity, @RequestParam(value="wednesdayStartTime", required=false) Timestamp wednesdayStartTime, @RequestParam(value="thursdayCapacity", required=false) BigDecimal thursdayCapacity, @RequestParam(value="tuesdayStartTime", required=false) Timestamp tuesdayStartTime, @RequestParam(value="wednesdayCapacity", required=false) BigDecimal wednesdayCapacity, @RequestParam(value="fridayStartTime", required=false) Timestamp fridayStartTime, @RequestParam(value="description", required=false) String description, @RequestParam(value="saturdayCapacity", required=false) BigDecimal saturdayCapacity, @RequestParam(value="mondayCapacity", required=false) BigDecimal mondayCapacity, @RequestParam(value="mondayStartTime", required=false) Timestamp mondayStartTime, @RequestParam(value="saturdayStartTime", required=false) Timestamp saturdayStartTime, @RequestParam(value="thursdayStartTime", required=false) Timestamp thursdayStartTime, @RequestParam(value="sundayStartTime", required=false) Timestamp sundayStartTime, @RequestParam(value="fridayCapacity", required=false) BigDecimal fridayCapacity, @RequestParam(value="tuesdayCapacity", required=false) BigDecimal tuesdayCapacity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("calendarWeekId",calendarWeekId);
		paramMap.put("sundayCapacity",sundayCapacity);
		paramMap.put("wednesdayStartTime",wednesdayStartTime);
		paramMap.put("thursdayCapacity",thursdayCapacity);
		paramMap.put("tuesdayStartTime",tuesdayStartTime);
		paramMap.put("wednesdayCapacity",wednesdayCapacity);
		paramMap.put("fridayStartTime",fridayStartTime);
		paramMap.put("description",description);
		paramMap.put("saturdayCapacity",saturdayCapacity);
		paramMap.put("mondayCapacity",mondayCapacity);
		paramMap.put("mondayStartTime",mondayStartTime);
		paramMap.put("saturdayStartTime",saturdayStartTime);
		paramMap.put("thursdayStartTime",thursdayStartTime);
		paramMap.put("sundayStartTime",sundayStartTime);
		paramMap.put("fridayCapacity",fridayCapacity);
		paramMap.put("tuesdayCapacity",tuesdayCapacity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCalendarWeek", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCalendarExceptionWeek")
	public ResponseEntity<Map<String, Object>> updateCalendarExceptionWeek(HttpSession session, @RequestParam(value="exceptionDateStart") Timestamp exceptionDateStart, @RequestParam(value="calendarId") String calendarId, @RequestParam(value="calendarWeekId", required=false) String calendarWeekId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("exceptionDateStart",exceptionDateStart);
		paramMap.put("calendarId",calendarId);
		paramMap.put("calendarWeekId",calendarWeekId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCalendarExceptionWeek", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCalendarWeek")
	public ResponseEntity<Map<String, Object>> createCalendarWeek(HttpSession session, @RequestParam(value="calendarWeekId") String calendarWeekId, @RequestParam(value="sundayCapacity", required=false) BigDecimal sundayCapacity, @RequestParam(value="wednesdayStartTime", required=false) Timestamp wednesdayStartTime, @RequestParam(value="thursdayCapacity", required=false) BigDecimal thursdayCapacity, @RequestParam(value="tuesdayStartTime", required=false) Timestamp tuesdayStartTime, @RequestParam(value="wednesdayCapacity", required=false) BigDecimal wednesdayCapacity, @RequestParam(value="fridayStartTime", required=false) Timestamp fridayStartTime, @RequestParam(value="description", required=false) String description, @RequestParam(value="saturdayCapacity", required=false) BigDecimal saturdayCapacity, @RequestParam(value="mondayCapacity", required=false) BigDecimal mondayCapacity, @RequestParam(value="mondayStartTime", required=false) Timestamp mondayStartTime, @RequestParam(value="saturdayStartTime", required=false) Timestamp saturdayStartTime, @RequestParam(value="thursdayStartTime", required=false) Timestamp thursdayStartTime, @RequestParam(value="sundayStartTime", required=false) Timestamp sundayStartTime, @RequestParam(value="fridayCapacity", required=false) BigDecimal fridayCapacity, @RequestParam(value="tuesdayCapacity", required=false) BigDecimal tuesdayCapacity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("calendarWeekId",calendarWeekId);
		paramMap.put("sundayCapacity",sundayCapacity);
		paramMap.put("wednesdayStartTime",wednesdayStartTime);
		paramMap.put("thursdayCapacity",thursdayCapacity);
		paramMap.put("tuesdayStartTime",tuesdayStartTime);
		paramMap.put("wednesdayCapacity",wednesdayCapacity);
		paramMap.put("fridayStartTime",fridayStartTime);
		paramMap.put("description",description);
		paramMap.put("saturdayCapacity",saturdayCapacity);
		paramMap.put("mondayCapacity",mondayCapacity);
		paramMap.put("mondayStartTime",mondayStartTime);
		paramMap.put("saturdayStartTime",saturdayStartTime);
		paramMap.put("thursdayStartTime",thursdayStartTime);
		paramMap.put("sundayStartTime",sundayStartTime);
		paramMap.put("fridayCapacity",fridayCapacity);
		paramMap.put("tuesdayCapacity",tuesdayCapacity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCalendarWeek", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCalendar")
	public ResponseEntity<Map<String, Object>> updateCalendar(HttpSession session, @RequestParam(value="calendarId") String calendarId, @RequestParam(value="calendarWeekId", required=false) String calendarWeekId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("calendarId",calendarId);
		paramMap.put("calendarWeekId",calendarWeekId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCalendar", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeCalendarWeek")
	public ResponseEntity<Map<String, Object>> removeCalendarWeek(HttpSession session, @RequestParam(value="calendarWeekId") String calendarWeekId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("calendarWeekId",calendarWeekId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeCalendarWeek", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCalendarExceptionWeek")
	public ResponseEntity<Map<String, Object>> createCalendarExceptionWeek(HttpSession session, @RequestParam(value="exceptionDateStart") Timestamp exceptionDateStart, @RequestParam(value="calendarId") String calendarId, @RequestParam(value="calendarWeekId", required=false) String calendarWeekId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("exceptionDateStart",exceptionDateStart);
		paramMap.put("calendarId",calendarId);
		paramMap.put("calendarWeekId",calendarWeekId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCalendarExceptionWeek", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCalendarExceptionDay")
	public ResponseEntity<Map<String, Object>> updateCalendarExceptionDay(HttpSession session, @RequestParam(value="exceptionDateStartTime") Timestamp exceptionDateStartTime, @RequestParam(value="calendarId") String calendarId, @RequestParam(value="exceptionCapacity", required=false) BigDecimal exceptionCapacity, @RequestParam(value="usedCapacity", required=false) BigDecimal usedCapacity, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("exceptionDateStartTime",exceptionDateStartTime);
		paramMap.put("calendarId",calendarId);
		paramMap.put("exceptionCapacity",exceptionCapacity);
		paramMap.put("usedCapacity",usedCapacity);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCalendarExceptionDay", paramMap);
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
