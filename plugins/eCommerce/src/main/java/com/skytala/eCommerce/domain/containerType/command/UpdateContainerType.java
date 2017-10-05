package com.skytala.eCommerce.domain.containerType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.containerType.event.ContainerTypeUpdated;
import com.skytala.eCommerce.domain.containerType.model.ContainerType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContainerType extends Command {

private ContainerType elementToBeUpdated;

public UpdateContainerType(ContainerType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContainerType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContainerType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContainerType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContainerType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContainerType.class);
}
success = false;
}
Event resultingEvent = new ContainerTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
