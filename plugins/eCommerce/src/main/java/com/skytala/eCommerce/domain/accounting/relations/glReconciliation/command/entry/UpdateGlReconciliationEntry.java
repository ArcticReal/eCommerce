package com.skytala.eCommerce.domain.accounting.relations.glReconciliation.command.entry;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event.entry.GlReconciliationEntryUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.model.entry.GlReconciliationEntry;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateGlReconciliationEntry extends Command {

private GlReconciliationEntry elementToBeUpdated;

public UpdateGlReconciliationEntry(GlReconciliationEntry elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public GlReconciliationEntry getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(GlReconciliationEntry elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("GlReconciliationEntry", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(GlReconciliationEntry.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(GlReconciliationEntry.class);
}
success = false;
}
Event resultingEvent = new GlReconciliationEntryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
