package com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.event.ZipSalesRuleLookupUpdated;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.model.ZipSalesRuleLookup;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateZipSalesRuleLookup extends Command {

private ZipSalesRuleLookup elementToBeUpdated;

public UpdateZipSalesRuleLookup(ZipSalesRuleLookup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ZipSalesRuleLookup getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ZipSalesRuleLookup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ZipSalesRuleLookup", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ZipSalesRuleLookup.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ZipSalesRuleLookup.class);
}
success = false;
}
Event resultingEvent = new ZipSalesRuleLookupUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
