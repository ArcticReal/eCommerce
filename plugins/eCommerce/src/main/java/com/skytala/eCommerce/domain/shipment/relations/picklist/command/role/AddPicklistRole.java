package com.skytala.eCommerce.domain.shipment.relations.picklist.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.role.PicklistRoleAdded;
import com.skytala.eCommerce.domain.shipment.relations.picklist.mapper.role.PicklistRoleMapper;
import com.skytala.eCommerce.domain.shipment.relations.picklist.model.role.PicklistRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPicklistRole extends Command {

private PicklistRole elementToBeAdded;
public AddPicklistRole(PicklistRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PicklistRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PicklistRole", elementToBeAdded.mapAttributeField());
addedElement = PicklistRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PicklistRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
