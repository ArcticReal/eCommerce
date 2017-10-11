package com.skytala.eCommerce.domain.product.relations.productPromoCodeParty.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPromoCodeParty.event.ProductPromoCodePartyAdded;
import com.skytala.eCommerce.domain.product.relations.productPromoCodeParty.mapper.ProductPromoCodePartyMapper;
import com.skytala.eCommerce.domain.product.relations.productPromoCodeParty.model.ProductPromoCodeParty;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPromoCodeParty extends Command {

private ProductPromoCodeParty elementToBeAdded;
public AddProductPromoCodeParty(ProductPromoCodeParty elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPromoCodeParty addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductPromoCodeParty", elementToBeAdded.mapAttributeField());
addedElement = ProductPromoCodePartyMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPromoCodePartyAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
