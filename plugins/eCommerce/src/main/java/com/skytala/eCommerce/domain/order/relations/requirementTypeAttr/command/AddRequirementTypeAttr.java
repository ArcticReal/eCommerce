package com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.event.RequirementTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.mapper.RequirementTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.model.RequirementTypeAttr;
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
