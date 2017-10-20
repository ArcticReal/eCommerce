
package com.skytala.eCommerce.domain.content.relations.survey.query.multiRespColumn;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.survey.event.multiRespColumn.SurveyMultiRespColumnFound;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.multiRespColumn.SurveyMultiRespColumnMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.multiRespColumn.SurveyMultiRespColumn;


public class FindAllSurveyMultiRespColumns extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SurveyMultiRespColumn> returnVal = new ArrayList<SurveyMultiRespColumn>();
try{
List<GenericValue> results = delegator.findAll("SurveyMultiRespColumn", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SurveyMultiRespColumnMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SurveyMultiRespColumnFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
