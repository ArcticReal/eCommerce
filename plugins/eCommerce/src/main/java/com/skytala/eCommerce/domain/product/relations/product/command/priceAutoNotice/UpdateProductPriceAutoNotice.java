package com.skytala.eCommerce.domain.product.relations.product.command.priceAutoNotice;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.priceAutoNotice.ProductPriceAutoNoticeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.priceAutoNotice.ProductPriceAutoNotice;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPriceAutoNotice extends Command {

private ProductPriceAutoNotice elementToBeUpdated;

public UpdateProductPriceAutoNotice(ProductPriceAutoNotice elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPriceAutoNotice getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPriceAutoNotice elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPriceAutoNotice", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPriceAutoNotice.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPriceAutoNotice.class);
}
success = false;
}
Event resultingEvent = new ProductPriceAutoNoticeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
