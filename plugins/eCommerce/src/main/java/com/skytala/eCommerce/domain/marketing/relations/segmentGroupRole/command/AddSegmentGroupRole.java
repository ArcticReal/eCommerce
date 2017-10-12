package com.skytala.eCommerce.domain.marketing.relations.segmentGroupRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupRole.event.SegmentGroupRoleAdded;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupRole.mapper.SegmentGroupRoleMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupRole.model.SegmentGroupRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSegmentGroupRole extends Command {

private SegmentGroupRole elementToBeAdded;
public AddSegmentGroupRole(SegmentGroupRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SegmentGroupRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("SegmentGroupRole", elementToBeAdded.mapAttributeField());
addedElement = SegmentGroupRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SegmentGroupRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
