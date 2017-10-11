package com.skytala.eCommerce.domain.product.relations.productStoreShipmentMeth.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productStoreShipmentMeth.event.ProductStoreShipmentMethUpdated;
import com.skytala.eCommerce.domain.product.relations.productStoreShipmentMeth.model.ProductStoreShipmentMeth;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductStoreShipmentMeth extends Command {

private ProductStoreShipmentMeth elementToBeUpdated;

public UpdateProductStoreShipmentMeth(ProductStoreShipmentMeth elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductStoreShipmentMeth getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductStoreShipmentMeth elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductStoreShipmentMeth", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductStoreShipmentMeth.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductStoreShipmentMeth.class);
}
success = false;
}
Event resultingEvent = new ProductStoreShipmentMethUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
