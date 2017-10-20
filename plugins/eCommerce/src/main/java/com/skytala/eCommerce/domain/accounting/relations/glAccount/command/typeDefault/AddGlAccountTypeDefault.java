package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.typeDefault;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.typeDefault.GlAccountTypeDefaultAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.typeDefault.GlAccountTypeDefaultMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.typeDefault.GlAccountTypeDefault;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlAccountTypeDefault extends Command {

private GlAccountTypeDefault elementToBeAdded;
public AddGlAccountTypeDefault(GlAccountTypeDefault elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlAccountTypeDefault addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("GlAccountTypeDefault", elementToBeAdded.mapAttributeField());
addedElement = GlAccountTypeDefaultMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlAccountTypeDefaultAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
