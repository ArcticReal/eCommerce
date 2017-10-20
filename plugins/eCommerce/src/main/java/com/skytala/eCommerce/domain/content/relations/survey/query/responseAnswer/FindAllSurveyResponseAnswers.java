
package com.skytala.eCommerce.domain.content.relations.survey.query.responseAnswer;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.survey.event.responseAnswer.SurveyResponseAnswerFound;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.responseAnswer.SurveyResponseAnswerMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.responseAnswer.SurveyResponseAnswer;


public class FindAllSurveyResponseAnswers extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SurveyResponseAnswer> returnVal = new ArrayList<SurveyResponseAnswer>();
try{
List<GenericValue> results = delegator.findAll("SurveyResponseAnswer", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SurveyResponseAnswerMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SurveyResponseAnswerFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
