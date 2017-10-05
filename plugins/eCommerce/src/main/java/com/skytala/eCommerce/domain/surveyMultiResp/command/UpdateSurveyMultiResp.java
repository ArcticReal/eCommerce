package com.skytala.eCommerce.domain.surveyMultiResp.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.surveyMultiResp.event.SurveyMultiRespUpdated;
import com.skytala.eCommerce.domain.surveyMultiResp.model.SurveyMultiResp;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSurveyMultiResp extends Command {

private SurveyMultiResp elementToBeUpdated;

public UpdateSurveyMultiResp(SurveyMultiResp elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SurveyMultiResp getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SurveyMultiResp elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SurveyMultiResp", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SurveyMultiResp.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SurveyMultiResp.class);
}
success = false;
}
Event resultingEvent = new SurveyMultiRespUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
