package com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.orderReturn;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.orderReturn.TrackingCodeOrderReturnAdded;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.orderReturn.TrackingCodeOrderReturnMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.orderReturn.TrackingCodeOrderReturn;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTrackingCodeOrderReturn extends Command {

private TrackingCodeOrderReturn elementToBeAdded;
public AddTrackingCodeOrderReturn(TrackingCodeOrderReturn elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TrackingCodeOrderReturn addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("TrackingCodeOrderReturn", elementToBeAdded.mapAttributeField());
addedElement = TrackingCodeOrderReturnMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TrackingCodeOrderReturnAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
