package com.skytala.eCommerce.domain.survey.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.survey.event.SurveyAdded;
import com.skytala.eCommerce.domain.survey.mapper.SurveyMapper;
import com.skytala.eCommerce.domain.survey.model.Survey;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSurvey extends Command {

private Survey elementToBeAdded;
public AddSurvey(Survey elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Survey addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSurveyId(delegator.getNextSeqId("Survey"));
GenericValue newValue = delegator.makeValue("Survey", elementToBeAdded.mapAttributeField());
addedElement = SurveyMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SurveyAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
