package com.skytala.eCommerce.domain.quantityBreakType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.quantityBreakType.event.QuantityBreakTypeUpdated;
import com.skytala.eCommerce.domain.quantityBreakType.model.QuantityBreakType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateQuantityBreakType extends Command {

private QuantityBreakType elementToBeUpdated;

public UpdateQuantityBreakType(QuantityBreakType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public QuantityBreakType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(QuantityBreakType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("QuantityBreakType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(QuantityBreakType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(QuantityBreakType.class);
}
success = false;
}
Event resultingEvent = new QuantityBreakTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
