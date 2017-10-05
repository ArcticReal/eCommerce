package com.skytala.eCommerce.domain.requirementType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.requirementType.event.RequirementTypeAdded;
import com.skytala.eCommerce.domain.requirementType.mapper.RequirementTypeMapper;
import com.skytala.eCommerce.domain.requirementType.model.RequirementType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddRequirementType extends Command {

private RequirementType elementToBeAdded;
public AddRequirementType(RequirementType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

RequirementType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRequirementTypeId(delegator.getNextSeqId("RequirementType"));
GenericValue newValue = delegator.makeValue("RequirementType", elementToBeAdded.mapAttributeField());
addedElement = RequirementTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new RequirementTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
