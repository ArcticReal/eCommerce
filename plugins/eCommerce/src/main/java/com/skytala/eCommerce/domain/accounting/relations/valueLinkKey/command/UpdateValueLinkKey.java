package com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.event.ValueLinkKeyUpdated;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.model.ValueLinkKey;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateValueLinkKey extends Command {

private ValueLinkKey elementToBeUpdated;

public UpdateValueLinkKey(ValueLinkKey elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ValueLinkKey getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ValueLinkKey elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ValueLinkKey", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ValueLinkKey.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ValueLinkKey.class);
}
success = false;
}
Event resultingEvent = new ValueLinkKeyUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
