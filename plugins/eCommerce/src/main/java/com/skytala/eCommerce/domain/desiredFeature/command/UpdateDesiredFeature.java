package com.skytala.eCommerce.domain.desiredFeature.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.desiredFeature.event.DesiredFeatureUpdated;
import com.skytala.eCommerce.domain.desiredFeature.model.DesiredFeature;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDesiredFeature extends Command {

private DesiredFeature elementToBeUpdated;

public UpdateDesiredFeature(DesiredFeature elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public DesiredFeature getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(DesiredFeature elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("DesiredFeature", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(DesiredFeature.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(DesiredFeature.class);
}
success = false;
}
Event resultingEvent = new DesiredFeatureUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
