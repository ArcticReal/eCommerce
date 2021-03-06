package com.skytala.eCommerce.domain.accounting.relations.finAccount.command.transType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transType.FinAccountTransTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transType.FinAccountTransType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFinAccountTransType extends Command {

private FinAccountTransType elementToBeUpdated;

public UpdateFinAccountTransType(FinAccountTransType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FinAccountTransType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FinAccountTransType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FinAccountTransType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FinAccountTransType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FinAccountTransType.class);
}
success = false;
}
Event resultingEvent = new FinAccountTransTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
