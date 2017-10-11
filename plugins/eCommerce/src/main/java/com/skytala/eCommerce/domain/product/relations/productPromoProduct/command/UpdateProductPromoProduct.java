package com.skytala.eCommerce.domain.product.relations.productPromoProduct.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productPromoProduct.event.ProductPromoProductUpdated;
import com.skytala.eCommerce.domain.product.relations.productPromoProduct.model.ProductPromoProduct;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPromoProduct extends Command {

private ProductPromoProduct elementToBeUpdated;

public UpdateProductPromoProduct(ProductPromoProduct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPromoProduct getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPromoProduct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPromoProduct", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPromoProduct.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPromoProduct.class);
}
success = false;
}
Event resultingEvent = new ProductPromoProductUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
