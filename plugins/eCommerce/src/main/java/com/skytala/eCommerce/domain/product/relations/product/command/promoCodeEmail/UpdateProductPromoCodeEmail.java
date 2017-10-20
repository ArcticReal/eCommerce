package com.skytala.eCommerce.domain.product.relations.product.command.promoCodeEmail;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCodeEmail.ProductPromoCodeEmailUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCodeEmail.ProductPromoCodeEmail;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPromoCodeEmail extends Command {

private ProductPromoCodeEmail elementToBeUpdated;

public UpdateProductPromoCodeEmail(ProductPromoCodeEmail elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPromoCodeEmail getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPromoCodeEmail elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPromoCodeEmail", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPromoCodeEmail.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPromoCodeEmail.class);
}
success = false;
}
Event resultingEvent = new ProductPromoCodeEmailUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
