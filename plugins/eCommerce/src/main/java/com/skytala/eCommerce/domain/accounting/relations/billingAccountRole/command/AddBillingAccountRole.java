package com.skytala.eCommerce.domain.accounting.relations.billingAccountRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.billingAccountRole.event.BillingAccountRoleAdded;
import com.skytala.eCommerce.domain.accounting.relations.billingAccountRole.mapper.BillingAccountRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.billingAccountRole.model.BillingAccountRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBillingAccountRole extends Command {

private BillingAccountRole elementToBeAdded;
public AddBillingAccountRole(BillingAccountRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BillingAccountRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("BillingAccountRole", elementToBeAdded.mapAttributeField());
addedElement = BillingAccountRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BillingAccountRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
