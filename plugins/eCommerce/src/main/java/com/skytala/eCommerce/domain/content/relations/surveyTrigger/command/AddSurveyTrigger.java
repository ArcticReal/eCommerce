package com.skytala.eCommerce.domain.content.relations.surveyTrigger.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.surveyTrigger.event.SurveyTriggerAdded;
import com.skytala.eCommerce.domain.content.relations.surveyTrigger.mapper.SurveyTriggerMapper;
import com.skytala.eCommerce.domain.content.relations.surveyTrigger.model.SurveyTrigger;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSurveyTrigger extends Command {

private SurveyTrigger elementToBeAdded;
public AddSurveyTrigger(SurveyTrigger elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SurveyTrigger addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("SurveyTrigger", elementToBeAdded.mapAttributeField());
addedElement = SurveyTriggerMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SurveyTriggerAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
