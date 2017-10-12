
package com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.event.SurveyQuestionOptionFound;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.mapper.SurveyQuestionOptionMapper;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.model.SurveyQuestionOption;


public class FindAllSurveyQuestionOptions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SurveyQuestionOption> returnVal = new ArrayList<SurveyQuestionOption>();
try{
List<GenericValue> results = delegator.findAll("SurveyQuestionOption", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SurveyQuestionOptionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SurveyQuestionOptionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
