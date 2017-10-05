package com.skytala.eCommerce.domain.glAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.glAccount.event.GlAccountAdded;
import com.skytala.eCommerce.domain.glAccount.mapper.GlAccountMapper;
import com.skytala.eCommerce.domain.glAccount.model.GlAccount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlAccount extends Command {

private GlAccount elementToBeAdded;
public AddGlAccount(GlAccount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlAccount addedElement = null;
boolean success = false;
try {
elementToBeAdded.setGlAccountId(delegator.getNextSeqId("GlAccount"));
GenericValue newValue = delegator.makeValue("GlAccount", elementToBeAdded.mapAttributeField());
addedElement = GlAccountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlAccountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
