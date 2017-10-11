package com.skytala.eCommerce.domain.party.relations.roleTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.event.RoleTypeAttrAdded;
import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.mapper.RoleTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.model.RoleTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddRoleTypeAttr extends Command {

private RoleTypeAttr elementToBeAdded;
public AddRoleTypeAttr(RoleTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

RoleTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("RoleTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = RoleTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new RoleTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
