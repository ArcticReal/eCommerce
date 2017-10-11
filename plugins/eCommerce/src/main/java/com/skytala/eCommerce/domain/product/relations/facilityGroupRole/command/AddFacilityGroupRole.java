package com.skytala.eCommerce.domain.product.relations.facilityGroupRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRole.event.FacilityGroupRoleAdded;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRole.mapper.FacilityGroupRoleMapper;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRole.model.FacilityGroupRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFacilityGroupRole extends Command {

private FacilityGroupRole elementToBeAdded;
public AddFacilityGroupRole(FacilityGroupRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FacilityGroupRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FacilityGroupRole", elementToBeAdded.mapAttributeField());
addedElement = FacilityGroupRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FacilityGroupRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
