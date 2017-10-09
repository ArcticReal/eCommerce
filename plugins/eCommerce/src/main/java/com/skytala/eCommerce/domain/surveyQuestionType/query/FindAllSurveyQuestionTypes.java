
package com.skytala.eCommerce.domain.surveyQuestionType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.surveyQuestionType.event.SurveyQuestionTypeFound;
import com.skytala.eCommerce.domain.surveyQuestionType.mapper.SurveyQuestionTypeMapper;
import com.skytala.eCommerce.domain.surveyQuestionType.model.SurveyQuestionType;


public class FindAllSurveyQuestionTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SurveyQuestionType> returnVal = new ArrayList<SurveyQuestionType>();
try{
List<GenericValue> results = delegator.findAll("SurveyQuestionType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SurveyQuestionTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SurveyQuestionTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}