package com.skytala.eCommerce.domain.accounting.relations.glReconciliation.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event.GlReconciliationAdded;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.mapper.GlReconciliationMapper;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.model.GlReconciliation;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlReconciliation extends Command {

private GlReconciliation elementToBeAdded;
public AddGlReconciliation(GlReconciliation elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlReconciliation addedElement = null;
boolean success = false;
try {
elementToBeAdded.setGlReconciliationId(delegator.getNextSeqId("GlReconciliation"));
GenericValue newValue = delegator.makeValue("GlReconciliation", elementToBeAdded.mapAttributeField());
addedElement = GlReconciliationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlReconciliationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
