package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.event.TaxAuthorityAssocUpdated;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.model.TaxAuthorityAssoc;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTaxAuthorityAssoc extends Command {

private TaxAuthorityAssoc elementToBeUpdated;

public UpdateTaxAuthorityAssoc(TaxAuthorityAssoc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TaxAuthorityAssoc getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TaxAuthorityAssoc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TaxAuthorityAssoc", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TaxAuthorityAssoc.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TaxAuthorityAssoc.class);
}
success = false;
}
Event resultingEvent = new TaxAuthorityAssocUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
