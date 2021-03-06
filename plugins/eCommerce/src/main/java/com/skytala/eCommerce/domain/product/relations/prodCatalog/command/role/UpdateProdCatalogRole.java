package com.skytala.eCommerce.domain.product.relations.prodCatalog.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.event.role.ProdCatalogRoleUpdated;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.role.ProdCatalogRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProdCatalogRole extends Command {

private ProdCatalogRole elementToBeUpdated;

public UpdateProdCatalogRole(ProdCatalogRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProdCatalogRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProdCatalogRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProdCatalogRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProdCatalogRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProdCatalogRole.class);
}
success = false;
}
Event resultingEvent = new ProdCatalogRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
