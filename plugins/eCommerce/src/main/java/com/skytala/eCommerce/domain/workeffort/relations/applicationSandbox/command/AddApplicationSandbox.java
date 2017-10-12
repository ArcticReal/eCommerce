package com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.event.ApplicationSandboxAdded;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.mapper.ApplicationSandboxMapper;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.model.ApplicationSandbox;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddApplicationSandbox extends Command {

private ApplicationSandbox elementToBeAdded;
public AddApplicationSandbox(ApplicationSandbox elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ApplicationSandbox addedElement = null;
boolean success = false;
try {
elementToBeAdded.setApplicationId(delegator.getNextSeqId("ApplicationSandbox"));
GenericValue newValue = delegator.makeValue("ApplicationSandbox", elementToBeAdded.mapAttributeField());
addedElement = ApplicationSandboxMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ApplicationSandboxAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
