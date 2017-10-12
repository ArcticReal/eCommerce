package com.skytala.eCommerce.domain.humanres.relations.employment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.EmploymentUpdated;
import com.skytala.eCommerce.domain.humanres.relations.employment.model.Employment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateEmployment extends Command {

private Employment elementToBeUpdated;

public UpdateEmployment(Employment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Employment getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Employment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Employment", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Employment.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Employment.class);
}
success = false;
}
Event resultingEvent = new EmploymentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
