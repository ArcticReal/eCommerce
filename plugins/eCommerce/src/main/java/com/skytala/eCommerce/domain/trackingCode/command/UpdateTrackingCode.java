package com.skytala.eCommerce.domain.trackingCode.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.trackingCode.event.TrackingCodeUpdated;
import com.skytala.eCommerce.domain.trackingCode.model.TrackingCode;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTrackingCode extends Command {

private TrackingCode elementToBeUpdated;

public UpdateTrackingCode(TrackingCode elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TrackingCode getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TrackingCode elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TrackingCode", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TrackingCode.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TrackingCode.class);
}
success = false;
}
Event resultingEvent = new TrackingCodeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
