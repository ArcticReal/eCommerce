
package com.skytala.eCommerce.domain.content.relations.survey.query.question;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.survey.event.question.SurveyQuestionFound;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.question.SurveyQuestionMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.question.SurveyQuestion;


public class FindAllSurveyQuestions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SurveyQuestion> returnVal = new ArrayList<SurveyQuestion>();
try{
List<GenericValue> results = delegator.findAll("SurveyQuestion", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SurveyQuestionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SurveyQuestionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
