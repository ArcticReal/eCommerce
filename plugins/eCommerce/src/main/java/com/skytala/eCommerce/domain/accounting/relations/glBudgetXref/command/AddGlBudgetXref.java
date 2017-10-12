package com.skytala.eCommerce.domain.accounting.relations.glBudgetXref.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glBudgetXref.event.GlBudgetXrefAdded;
import com.skytala.eCommerce.domain.accounting.relations.glBudgetXref.mapper.GlBudgetXrefMapper;
import com.skytala.eCommerce.domain.accounting.relations.glBudgetXref.model.GlBudgetXref;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlBudgetXref extends Command {

private GlBudgetXref elementToBeAdded;
public AddGlBudgetXref(GlBudgetXref elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlBudgetXref addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("GlBudgetXref", elementToBeAdded.mapAttributeField());
addedElement = GlBudgetXrefMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlBudgetXrefAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
