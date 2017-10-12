package com.skytala.eCommerce.domain.accounting.relations.finAccountType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.finAccountType.event.FinAccountTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccountType.model.FinAccountType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFinAccountType extends Command {

private FinAccountType elementToBeUpdated;

public UpdateFinAccountType(FinAccountType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FinAccountType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FinAccountType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FinAccountType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FinAccountType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FinAccountType.class);
}
success = false;
}
Event resultingEvent = new FinAccountTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
