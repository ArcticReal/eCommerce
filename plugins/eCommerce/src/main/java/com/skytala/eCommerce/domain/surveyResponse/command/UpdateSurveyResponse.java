package com.skytala.eCommerce.domain.surveyResponse.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.surveyResponse.event.SurveyResponseUpdated;
import com.skytala.eCommerce.domain.surveyResponse.model.SurveyResponse;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSurveyResponse extends Command {

private SurveyResponse elementToBeUpdated;

public UpdateSurveyResponse(SurveyResponse elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SurveyResponse getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SurveyResponse elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SurveyResponse", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SurveyResponse.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SurveyResponse.class);
}
success = false;
}
Event resultingEvent = new SurveyResponseUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
