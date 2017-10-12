package com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.event.TrackingCodeOrderAdded;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.mapper.TrackingCodeOrderMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrder.model.TrackingCodeOrder;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTrackingCodeOrder extends Command {

private TrackingCodeOrder elementToBeAdded;
public AddTrackingCodeOrder(TrackingCodeOrder elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TrackingCodeOrder addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("TrackingCodeOrder", elementToBeAdded.mapAttributeField());
addedElement = TrackingCodeOrderMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TrackingCodeOrderAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
