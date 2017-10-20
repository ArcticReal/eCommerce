package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.type.SegmentGroupTypeAdded;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.type.SegmentGroupTypeMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.type.SegmentGroupType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSegmentGroupType extends Command {

private SegmentGroupType elementToBeAdded;
public AddSegmentGroupType(SegmentGroupType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SegmentGroupType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSegmentGroupTypeId(delegator.getNextSeqId("SegmentGroupType"));
GenericValue newValue = delegator.makeValue("SegmentGroupType", elementToBeAdded.mapAttributeField());
addedElement = SegmentGroupTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SegmentGroupTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
