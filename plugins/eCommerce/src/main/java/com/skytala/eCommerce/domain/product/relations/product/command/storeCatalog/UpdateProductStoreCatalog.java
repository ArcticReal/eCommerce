package com.skytala.eCommerce.domain.product.relations.product.command.storeCatalog;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.storeCatalog.ProductStoreCatalogUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.storeCatalog.ProductStoreCatalog;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductStoreCatalog extends Command {

private ProductStoreCatalog elementToBeUpdated;

public UpdateProductStoreCatalog(ProductStoreCatalog elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductStoreCatalog getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductStoreCatalog elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductStoreCatalog", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductStoreCatalog.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductStoreCatalog.class);
}
success = false;
}
Event resultingEvent = new ProductStoreCatalogUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
