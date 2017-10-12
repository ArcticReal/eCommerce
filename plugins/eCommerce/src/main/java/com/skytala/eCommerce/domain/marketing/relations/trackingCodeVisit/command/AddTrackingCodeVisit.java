package com.skytala.eCommerce.domain.marketing.relations.trackingCodeVisit.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeVisit.event.TrackingCodeVisitAdded;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeVisit.mapper.TrackingCodeVisitMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeVisit.model.TrackingCodeVisit;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTrackingCodeVisit extends Command {

private TrackingCodeVisit elementToBeAdded;
public AddTrackingCodeVisit(TrackingCodeVisit elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TrackingCodeVisit addedElement = null;
boolean success = false;
try {
elementToBeAdded.setVisitId(delegator.getNextSeqId("TrackingCodeVisit"));
GenericValue newValue = delegator.makeValue("TrackingCodeVisit", elementToBeAdded.mapAttributeField());
addedElement = TrackingCodeVisitMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TrackingCodeVisitAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
