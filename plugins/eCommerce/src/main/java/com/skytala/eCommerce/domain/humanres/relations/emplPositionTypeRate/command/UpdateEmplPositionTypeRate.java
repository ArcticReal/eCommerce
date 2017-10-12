package com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeRate.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeRate.event.EmplPositionTypeRateUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeRate.model.EmplPositionTypeRate;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateEmplPositionTypeRate extends Command {

private EmplPositionTypeRate elementToBeUpdated;

public UpdateEmplPositionTypeRate(EmplPositionTypeRate elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public EmplPositionTypeRate getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(EmplPositionTypeRate elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("EmplPositionTypeRate", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(EmplPositionTypeRate.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(EmplPositionTypeRate.class);
}
success = false;
}
Event resultingEvent = new EmplPositionTypeRateUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
