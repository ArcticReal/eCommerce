package com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.event.TrackingCodeOrderUpdated;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.model.TrackingCodeOrder;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTrackingCodeOrder extends Command {

private TrackingCodeOrder elementToBeUpdated;

public UpdateTrackingCodeOrder(TrackingCodeOrder elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TrackingCodeOrder getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TrackingCodeOrder elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TrackingCodeOrder", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TrackingCodeOrder.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TrackingCodeOrder.class);
}
success = false;
}
Event resultingEvent = new TrackingCodeOrderUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
