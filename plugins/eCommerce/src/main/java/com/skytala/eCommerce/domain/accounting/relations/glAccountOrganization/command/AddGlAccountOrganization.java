package com.skytala.eCommerce.domain.accounting.relations.glAccountOrganization.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccountOrganization.event.GlAccountOrganizationAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccountOrganization.mapper.GlAccountOrganizationMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccountOrganization.model.GlAccountOrganization;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlAccountOrganization extends Command {

private GlAccountOrganization elementToBeAdded;
public AddGlAccountOrganization(GlAccountOrganization elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlAccountOrganization addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("GlAccountOrganization", elementToBeAdded.mapAttributeField());
addedElement = GlAccountOrganizationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlAccountOrganizationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
