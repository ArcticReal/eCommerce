
package com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.event.SurveyQuestionApplFound;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.mapper.SurveyQuestionApplMapper;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.model.SurveyQuestionAppl;


public class FindAllSurveyQuestionAppls extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SurveyQuestionAppl> returnVal = new ArrayList<SurveyQuestionAppl>();
try{
List<GenericValue> results = delegator.findAll("SurveyQuestionAppl", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SurveyQuestionApplMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SurveyQuestionApplFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
