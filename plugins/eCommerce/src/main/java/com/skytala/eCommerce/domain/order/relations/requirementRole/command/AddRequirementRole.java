package com.skytala.eCommerce.domain.order.relations.requirementRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.requirementRole.event.RequirementRoleAdded;
import com.skytala.eCommerce.domain.order.relations.requirementRole.mapper.RequirementRoleMapper;
import com.skytala.eCommerce.domain.order.relations.requirementRole.model.RequirementRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddRequirementRole extends Command {

private RequirementRole elementToBeAdded;
public AddRequirementRole(RequirementRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

RequirementRole addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("RequirementRole"));
GenericValue newValue = delegator.makeValue("RequirementRole", elementToBeAdded.mapAttributeField());
addedElement = RequirementRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new RequirementRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
