package com.skytala.eCommerce.domain.product.relations.product.command.promoContent;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.promoContent.ProductPromoContentUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.promoContent.ProductPromoContent;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPromoContent extends Command {

private ProductPromoContent elementToBeUpdated;

public UpdateProductPromoContent(ProductPromoContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPromoContent getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPromoContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPromoContent", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPromoContent.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPromoContent.class);
}
success = false;
}
Event resultingEvent = new ProductPromoContentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
