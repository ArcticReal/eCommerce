package com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.event.GlAccountGroupMemberAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.mapper.GlAccountGroupMemberMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.model.GlAccountGroupMember;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlAccountGroupMember extends Command {

private GlAccountGroupMember elementToBeAdded;
public AddGlAccountGroupMember(GlAccountGroupMember elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlAccountGroupMember addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("GlAccountGroupMember", elementToBeAdded.mapAttributeField());
addedElement = GlAccountGroupMemberMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlAccountGroupMemberAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
