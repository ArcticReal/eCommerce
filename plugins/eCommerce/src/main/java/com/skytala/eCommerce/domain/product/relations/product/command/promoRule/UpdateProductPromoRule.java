package com.skytala.eCommerce.domain.product.relations.product.command.promoRule;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.promoRule.ProductPromoRuleUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.promoRule.ProductPromoRule;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPromoRule extends Command {

private ProductPromoRule elementToBeUpdated;

public UpdateProductPromoRule(ProductPromoRule elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPromoRule getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPromoRule elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPromoRule", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPromoRule.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPromoRule.class);
}
success = false;
}
Event resultingEvent = new ProductPromoRuleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
