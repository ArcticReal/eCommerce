package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.event.TaxAuthorityGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityGlAccount.model.TaxAuthorityGlAccount;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTaxAuthorityGlAccount extends Command {

private TaxAuthorityGlAccount elementToBeUpdated;

public UpdateTaxAuthorityGlAccount(TaxAuthorityGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TaxAuthorityGlAccount getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TaxAuthorityGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TaxAuthorityGlAccount", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TaxAuthorityGlAccount.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TaxAuthorityGlAccount.class);
}
success = false;
}
Event resultingEvent = new TaxAuthorityGlAccountUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}