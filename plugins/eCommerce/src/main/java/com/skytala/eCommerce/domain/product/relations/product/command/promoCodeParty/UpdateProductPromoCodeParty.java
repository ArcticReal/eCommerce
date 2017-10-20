package com.skytala.eCommerce.domain.product.relations.product.command.promoCodeParty;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCodeParty.ProductPromoCodePartyUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCodeParty.ProductPromoCodeParty;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPromoCodeParty extends Command {

private ProductPromoCodeParty elementToBeUpdated;

public UpdateProductPromoCodeParty(ProductPromoCodeParty elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPromoCodeParty getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPromoCodeParty elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPromoCodeParty", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPromoCodeParty.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductPromoCodeParty.class);
}
success = false;
}
Event resultingEvent = new ProductPromoCodePartyUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
