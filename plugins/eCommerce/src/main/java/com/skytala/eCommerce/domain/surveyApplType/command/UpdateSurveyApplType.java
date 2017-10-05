package com.skytala.eCommerce.domain.surveyApplType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.surveyApplType.event.SurveyApplTypeUpdated;
import com.skytala.eCommerce.domain.surveyApplType.model.SurveyApplType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSurveyApplType extends Command {

private SurveyApplType elementToBeUpdated;

public UpdateSurveyApplType(SurveyApplType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SurveyApplType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SurveyApplType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SurveyApplType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SurveyApplType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SurveyApplType.class);
}
success = false;
}
Event resultingEvent = new SurveyApplTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
