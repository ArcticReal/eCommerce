package com.skytala.eCommerce.domain.content.relations.surveyMultiRespColumn.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.surveyMultiRespColumn.event.SurveyMultiRespColumnAdded;
import com.skytala.eCommerce.domain.content.relations.surveyMultiRespColumn.mapper.SurveyMultiRespColumnMapper;
import com.skytala.eCommerce.domain.content.relations.surveyMultiRespColumn.model.SurveyMultiRespColumn;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSurveyMultiRespColumn extends Command {

private SurveyMultiRespColumn elementToBeAdded;
public AddSurveyMultiRespColumn(SurveyMultiRespColumn elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SurveyMultiRespColumn addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("SurveyMultiRespColumn", elementToBeAdded.mapAttributeField());
addedElement = SurveyMultiRespColumnMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SurveyMultiRespColumnAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}