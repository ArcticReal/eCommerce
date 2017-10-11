package com.skytala.eCommerce.domain.product.relations.prodCatalogInvFacility.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.prodCatalogInvFacility.event.ProdCatalogInvFacilityUpdated;
import com.skytala.eCommerce.domain.product.relations.prodCatalogInvFacility.model.ProdCatalogInvFacility;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProdCatalogInvFacility extends Command {

private ProdCatalogInvFacility elementToBeUpdated;

public UpdateProdCatalogInvFacility(ProdCatalogInvFacility elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProdCatalogInvFacility getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProdCatalogInvFacility elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProdCatalogInvFacility", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProdCatalogInvFacility.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProdCatalogInvFacility.class);
}
success = false;
}
Event resultingEvent = new ProdCatalogInvFacilityUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
