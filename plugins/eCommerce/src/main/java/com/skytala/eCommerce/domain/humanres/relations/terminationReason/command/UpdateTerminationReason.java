package com.skytala.eCommerce.domain.humanres.relations.terminationReason.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.event.TerminationReasonUpdated;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.model.TerminationReason;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTerminationReason extends Command {

private TerminationReason elementToBeUpdated;

public UpdateTerminationReason(TerminationReason elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TerminationReason getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TerminationReason elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TerminationReason", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TerminationReason.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TerminationReason.class);
}
success = false;
}
Event resultingEvent = new TerminationReasonUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
