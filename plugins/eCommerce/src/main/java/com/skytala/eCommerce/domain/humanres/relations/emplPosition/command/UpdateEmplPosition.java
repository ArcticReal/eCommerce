package com.skytala.eCommerce.domain.humanres.relations.emplPosition.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.EmplPositionUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.EmplPosition;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateEmplPosition extends Command {

private EmplPosition elementToBeUpdated;

public UpdateEmplPosition(EmplPosition elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public EmplPosition getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(EmplPosition elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("EmplPosition", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(EmplPosition.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(EmplPosition.class);
}
success = false;
}
Event resultingEvent = new EmplPositionUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
