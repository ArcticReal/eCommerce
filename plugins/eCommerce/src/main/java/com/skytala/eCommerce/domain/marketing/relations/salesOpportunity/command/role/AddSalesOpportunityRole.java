package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.role.SalesOpportunityRoleAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.role.SalesOpportunityRoleMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.role.SalesOpportunityRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSalesOpportunityRole extends Command {

private SalesOpportunityRole elementToBeAdded;
public AddSalesOpportunityRole(SalesOpportunityRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SalesOpportunityRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("SalesOpportunityRole", elementToBeAdded.mapAttributeField());
addedElement = SalesOpportunityRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SalesOpportunityRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
