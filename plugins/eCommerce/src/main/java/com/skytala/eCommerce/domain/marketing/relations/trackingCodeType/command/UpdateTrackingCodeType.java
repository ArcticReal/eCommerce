package com.skytala.eCommerce.domain.marketing.relations.trackingCodeType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeType.event.TrackingCodeTypeUpdated;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeType.model.TrackingCodeType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTrackingCodeType extends Command {

private TrackingCodeType elementToBeUpdated;

public UpdateTrackingCodeType(TrackingCodeType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TrackingCodeType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TrackingCodeType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TrackingCodeType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TrackingCodeType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TrackingCodeType.class);
}
success = false;
}
Event resultingEvent = new TrackingCodeTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
