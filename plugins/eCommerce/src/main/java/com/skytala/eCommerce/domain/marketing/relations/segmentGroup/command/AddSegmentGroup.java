package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.SegmentGroupAdded;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.SegmentGroupMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.SegmentGroup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSegmentGroup extends Command {

private SegmentGroup elementToBeAdded;
public AddSegmentGroup(SegmentGroup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SegmentGroup addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSegmentGroupId(delegator.getNextSeqId("SegmentGroup"));
GenericValue newValue = delegator.makeValue("SegmentGroup", elementToBeAdded.mapAttributeField());
addedElement = SegmentGroupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SegmentGroupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
