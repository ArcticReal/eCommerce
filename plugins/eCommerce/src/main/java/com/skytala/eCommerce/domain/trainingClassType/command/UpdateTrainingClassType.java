package com.skytala.eCommerce.domain.trainingClassType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.trainingClassType.event.TrainingClassTypeUpdated;
import com.skytala.eCommerce.domain.trainingClassType.model.TrainingClassType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTrainingClassType extends Command {

private TrainingClassType elementToBeUpdated;

public UpdateTrainingClassType(TrainingClassType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TrainingClassType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TrainingClassType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TrainingClassType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TrainingClassType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TrainingClassType.class);
}
success = false;
}
Event resultingEvent = new TrainingClassTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
