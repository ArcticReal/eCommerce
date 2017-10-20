package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.type.GlAccountTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.type.GlAccountTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.type.GlAccountType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlAccountType extends Command {

private GlAccountType elementToBeAdded;
public AddGlAccountType(GlAccountType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlAccountType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setGlAccountTypeId(delegator.getNextSeqId("GlAccountType"));
GenericValue newValue = delegator.makeValue("GlAccountType", elementToBeAdded.mapAttributeField());
addedElement = GlAccountTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlAccountTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
