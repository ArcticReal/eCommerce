package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityCategory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityCategory.event.TaxAuthorityCategoryUpdated;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityCategory.model.TaxAuthorityCategory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTaxAuthorityCategory extends Command {

private TaxAuthorityCategory elementToBeUpdated;

public UpdateTaxAuthorityCategory(TaxAuthorityCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TaxAuthorityCategory getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TaxAuthorityCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TaxAuthorityCategory", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TaxAuthorityCategory.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TaxAuthorityCategory.class);
}
success = false;
}
Event resultingEvent = new TaxAuthorityCategoryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
