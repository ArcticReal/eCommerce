package com.skytala.eCommerce.domain.order.relations.requirement.command.typeAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.requirement.event.typeAttr.RequirementTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.typeAttr.RequirementTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.typeAttr.RequirementTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddRequirementTypeAttr extends Command {

private RequirementTypeAttr elementToBeAdded;
public AddRequirementTypeAttr(RequirementTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

RequirementTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("RequirementTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = RequirementTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new RequirementTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
