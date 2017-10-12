package com.skytala.eCommerce.domain.humanres.relations.terminationType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.event.TerminationTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.model.TerminationType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTerminationType extends Command {

private TerminationType elementToBeUpdated;

public UpdateTerminationType(TerminationType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TerminationType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TerminationType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TerminationType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TerminationType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TerminationType.class);
}
success = false;
}
Event resultingEvent = new TerminationTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
