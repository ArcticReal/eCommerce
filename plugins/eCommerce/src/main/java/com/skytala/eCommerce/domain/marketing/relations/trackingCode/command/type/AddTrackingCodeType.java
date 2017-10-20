package com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.type.TrackingCodeTypeAdded;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.type.TrackingCodeTypeMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.type.TrackingCodeType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTrackingCodeType extends Command {

private TrackingCodeType elementToBeAdded;
public AddTrackingCodeType(TrackingCodeType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TrackingCodeType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setTrackingCodeTypeId(delegator.getNextSeqId("TrackingCodeType"));
GenericValue newValue = delegator.makeValue("TrackingCodeType", elementToBeAdded.mapAttributeField());
addedElement = TrackingCodeTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TrackingCodeTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
