package com.skytala.eCommerce.domain.responsibilityType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.responsibilityType.event.ResponsibilityTypeAdded;
import com.skytala.eCommerce.domain.responsibilityType.mapper.ResponsibilityTypeMapper;
import com.skytala.eCommerce.domain.responsibilityType.model.ResponsibilityType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddResponsibilityType extends Command {

private ResponsibilityType elementToBeAdded;
public AddResponsibilityType(ResponsibilityType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ResponsibilityType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setResponsibilityTypeId(delegator.getNextSeqId("ResponsibilityType"));
GenericValue newValue = delegator.makeValue("ResponsibilityType", elementToBeAdded.mapAttributeField());
addedElement = ResponsibilityTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ResponsibilityTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
