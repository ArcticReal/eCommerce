package com.skytala.eCommerce.domain.product.relations.facility.command.groupMember;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupMember.FacilityGroupMemberAdded;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.groupMember.FacilityGroupMemberMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.groupMember.FacilityGroupMember;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFacilityGroupMember extends Command {

private FacilityGroupMember elementToBeAdded;
public AddFacilityGroupMember(FacilityGroupMember elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FacilityGroupMember addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FacilityGroupMember", elementToBeAdded.mapAttributeField());
addedElement = FacilityGroupMemberMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FacilityGroupMemberAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
