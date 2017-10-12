package com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.event.TrackingCodeOrderReturnUpdated;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.model.TrackingCodeOrderReturn;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTrackingCodeOrderReturn extends Command {

private TrackingCodeOrderReturn elementToBeUpdated;

public UpdateTrackingCodeOrderReturn(TrackingCodeOrderReturn elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TrackingCodeOrderReturn getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TrackingCodeOrderReturn elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TrackingCodeOrderReturn", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TrackingCodeOrderReturn.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TrackingCodeOrderReturn.class);
}
success = false;
}
Event resultingEvent = new TrackingCodeOrderReturnUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
