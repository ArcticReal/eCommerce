package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.group;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.group.GlAccountGroupAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.group.GlAccountGroupMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.group.GlAccountGroup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlAccountGroup extends Command {

private GlAccountGroup elementToBeAdded;
public AddGlAccountGroup(GlAccountGroup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlAccountGroup addedElement = null;
boolean success = false;
try {
elementToBeAdded.setGlAccountGroupId(delegator.getNextSeqId("GlAccountGroup"));
GenericValue newValue = delegator.makeValue("GlAccountGroup", elementToBeAdded.mapAttributeField());
addedElement = GlAccountGroupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlAccountGroupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
