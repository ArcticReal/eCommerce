package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.TaxAuthorityUpdated;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.TaxAuthority;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTaxAuthority extends Command {

private TaxAuthority elementToBeUpdated;

public UpdateTaxAuthority(TaxAuthority elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TaxAuthority getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TaxAuthority elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TaxAuthority", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TaxAuthority.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TaxAuthority.class);
}
success = false;
}
Event resultingEvent = new TaxAuthorityUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
