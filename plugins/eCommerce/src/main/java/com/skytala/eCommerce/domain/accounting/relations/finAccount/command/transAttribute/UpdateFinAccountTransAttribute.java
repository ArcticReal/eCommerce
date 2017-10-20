package com.skytala.eCommerce.domain.accounting.relations.finAccount.command.transAttribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transAttribute.FinAccountTransAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transAttribute.FinAccountTransAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFinAccountTransAttribute extends Command {

private FinAccountTransAttribute elementToBeUpdated;

public UpdateFinAccountTransAttribute(FinAccountTransAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FinAccountTransAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FinAccountTransAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FinAccountTransAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FinAccountTransAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FinAccountTransAttribute.class);
}
success = false;
}
Event resultingEvent = new FinAccountTransAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
