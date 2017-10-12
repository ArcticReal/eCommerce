package com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.event.GlReconciliationEntryAdded;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.mapper.GlReconciliationEntryMapper;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.model.GlReconciliationEntry;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlReconciliationEntry extends Command {

private GlReconciliationEntry elementToBeAdded;
public AddGlReconciliationEntry(GlReconciliationEntry elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlReconciliationEntry addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAcctgTransEntrySeqId(delegator.getNextSeqId("GlReconciliationEntry"));
GenericValue newValue = delegator.makeValue("GlReconciliationEntry", elementToBeAdded.mapAttributeField());
addedElement = GlReconciliationEntryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlReconciliationEntryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
