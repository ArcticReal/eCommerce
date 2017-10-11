package com.skytala.eCommerce.domain.order.relations.requirementAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.event.RequirementAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.mapper.RequirementAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.model.RequirementAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddRequirementAttribute extends Command {

private RequirementAttribute elementToBeAdded;
public AddRequirementAttribute(RequirementAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

RequirementAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("RequirementAttribute", elementToBeAdded.mapAttributeField());
addedElement = RequirementAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new RequirementAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
