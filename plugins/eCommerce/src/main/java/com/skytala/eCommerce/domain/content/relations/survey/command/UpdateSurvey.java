package com.skytala.eCommerce.domain.content.relations.survey.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.survey.event.SurveyUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.model.Survey;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSurvey extends Command {

private Survey elementToBeUpdated;

public UpdateSurvey(Survey elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Survey getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Survey elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Survey", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Survey.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Survey.class);
}
success = false;
}
Event resultingEvent = new SurveyUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
