package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityRateProduct.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityRateProduct.event.TaxAuthorityRateProductUpdated;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityRateProduct.model.TaxAuthorityRateProduct;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTaxAuthorityRateProduct extends Command {

private TaxAuthorityRateProduct elementToBeUpdated;

public UpdateTaxAuthorityRateProduct(TaxAuthorityRateProduct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TaxAuthorityRateProduct getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TaxAuthorityRateProduct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TaxAuthorityRateProduct", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TaxAuthorityRateProduct.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TaxAuthorityRateProduct.class);
}
success = false;
}
Event resultingEvent = new TaxAuthorityRateProductUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
