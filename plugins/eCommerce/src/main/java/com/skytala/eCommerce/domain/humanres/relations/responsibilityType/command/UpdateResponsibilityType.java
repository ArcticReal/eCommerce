package com.skytala.eCommerce.domain.humanres.relations.responsibilityType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.event.ResponsibilityTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.model.ResponsibilityType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateResponsibilityType extends Command {

private ResponsibilityType elementToBeUpdated;

public UpdateResponsibilityType(ResponsibilityType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ResponsibilityType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ResponsibilityType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ResponsibilityType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ResponsibilityType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ResponsibilityType.class);
}
success = false;
}
Event resultingEvent = new ResponsibilityTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
