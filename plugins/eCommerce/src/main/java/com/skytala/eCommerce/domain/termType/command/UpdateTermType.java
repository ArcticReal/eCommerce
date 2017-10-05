package com.skytala.eCommerce.domain.termType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.termType.event.TermTypeUpdated;
import com.skytala.eCommerce.domain.termType.model.TermType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTermType extends Command {

private TermType elementToBeUpdated;

public UpdateTermType(TermType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TermType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TermType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TermType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TermType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TermType.class);
}
success = false;
}
Event resultingEvent = new TermTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
