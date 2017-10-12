package com.skytala.eCommerce.domain.humanres.relations.trainingRequest.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.event.TrainingRequestUpdated;
import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.model.TrainingRequest;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTrainingRequest extends Command {

private TrainingRequest elementToBeUpdated;

public UpdateTrainingRequest(TrainingRequest elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TrainingRequest getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TrainingRequest elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TrainingRequest", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TrainingRequest.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TrainingRequest.class);
}
success = false;
}
Event resultingEvent = new TrainingRequestUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
