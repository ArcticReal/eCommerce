package com.skytala.eCommerce.domain.accounting.relations.giftCard.command.fulfillment;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.event.fulfillment.GiftCardFulfillmentUpdated;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.model.fulfillment.GiftCardFulfillment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateGiftCardFulfillment extends Command {

private GiftCardFulfillment elementToBeUpdated;

public UpdateGiftCardFulfillment(GiftCardFulfillment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public GiftCardFulfillment getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(GiftCardFulfillment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("GiftCardFulfillment", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(GiftCardFulfillment.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(GiftCardFulfillment.class);
}
success = false;
}
Event resultingEvent = new GiftCardFulfillmentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
