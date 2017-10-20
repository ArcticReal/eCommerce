package com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.visit;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.visit.TrackingCodeVisitUpdated;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.visit.TrackingCodeVisit;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTrackingCodeVisit extends Command {

private TrackingCodeVisit elementToBeUpdated;

public UpdateTrackingCodeVisit(TrackingCodeVisit elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TrackingCodeVisit getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TrackingCodeVisit elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TrackingCodeVisit", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TrackingCodeVisit.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TrackingCodeVisit.class);
}
success = false;
}
Event resultingEvent = new TrackingCodeVisitUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
