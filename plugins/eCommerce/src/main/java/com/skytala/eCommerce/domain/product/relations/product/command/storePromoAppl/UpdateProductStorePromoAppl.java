package com.skytala.eCommerce.domain.product.relations.product.command.storePromoAppl;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.storePromoAppl.ProductStorePromoApplUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.storePromoAppl.ProductStorePromoAppl;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductStorePromoAppl extends Command {

private ProductStorePromoAppl elementToBeUpdated;

public UpdateProductStorePromoAppl(ProductStorePromoAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductStorePromoAppl getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductStorePromoAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductStorePromoAppl", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductStorePromoAppl.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductStorePromoAppl.class);
}
success = false;
}
Event resultingEvent = new ProductStorePromoApplUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
