package com.skytala.eCommerce.domain.product.relations.facilityGroup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityGroup.event.FacilityGroupAdded;
import com.skytala.eCommerce.domain.product.relations.facilityGroup.mapper.FacilityGroupMapper;
import com.skytala.eCommerce.domain.product.relations.facilityGroup.model.FacilityGroup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFacilityGroup extends Command {

private FacilityGroup elementToBeAdded;
public AddFacilityGroup(FacilityGroup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FacilityGroup addedElement = null;
boolean success = false;
try {
elementToBeAdded.setFacilityGroupId(delegator.getNextSeqId("FacilityGroup"));
GenericValue newValue = delegator.makeValue("FacilityGroup", elementToBeAdded.mapAttributeField());
addedElement = FacilityGroupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FacilityGroupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
