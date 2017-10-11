package com.skytala.eCommerce.domain.product.relations.productPromoAction.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productPromoAction.event.ProductPromoActionUpdated;
import com.skytala.eCommerce.domain.product.relations.productPromoAction.model.ProductPromoAction;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPromoAction extends Command {

private ProductPromoAction elementToBeUpdated;

public UpdateProductPromoAction(ProductPromoAction elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPromoAction getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPromoAction elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPromoAction", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPromoAction.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPromoAction.class);
}
success = false;
}
Event resultingEvent = new ProductPromoActionUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
