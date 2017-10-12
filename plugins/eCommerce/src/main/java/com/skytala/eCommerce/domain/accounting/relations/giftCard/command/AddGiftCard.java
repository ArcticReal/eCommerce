package com.skytala.eCommerce.domain.accounting.relations.giftCard.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.event.GiftCardAdded;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.mapper.GiftCardMapper;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.model.GiftCard;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGiftCard extends Command {

private GiftCard elementToBeAdded;
public AddGiftCard(GiftCard elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GiftCard addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("GiftCard", elementToBeAdded.mapAttributeField());
addedElement = GiftCardMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GiftCardAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
