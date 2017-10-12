package com.skytala.eCommerce.domain.marketing.relations.segmentGroupGeo.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupGeo.event.SegmentGroupGeoAdded;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupGeo.mapper.SegmentGroupGeoMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupGeo.model.SegmentGroupGeo;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSegmentGroupGeo extends Command {

private SegmentGroupGeo elementToBeAdded;
public AddSegmentGroupGeo(SegmentGroupGeo elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SegmentGroupGeo addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("SegmentGroupGeo", elementToBeAdded.mapAttributeField());
addedElement = SegmentGroupGeoMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SegmentGroupGeoAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
