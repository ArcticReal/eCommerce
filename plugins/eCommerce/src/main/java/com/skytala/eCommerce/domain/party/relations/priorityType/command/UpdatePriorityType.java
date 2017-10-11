package com.skytala.eCommerce.domain.party.relations.priorityType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.priorityType.event.PriorityTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.priorityType.model.PriorityType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePriorityType extends Command {

private PriorityType elementToBeUpdated;

public UpdatePriorityType(PriorityType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PriorityType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PriorityType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PriorityType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PriorityType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PriorityType.class);
}
success = false;
}
Event resultingEvent = new PriorityTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
