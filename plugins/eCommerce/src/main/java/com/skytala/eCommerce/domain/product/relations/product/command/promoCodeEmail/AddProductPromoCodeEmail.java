package com.skytala.eCommerce.domain.product.relations.product.command.promoCodeEmail;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCodeEmail.ProductPromoCodeEmailAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoCodeEmail.ProductPromoCodeEmailMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCodeEmail.ProductPromoCodeEmail;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPromoCodeEmail extends Command {

private ProductPromoCodeEmail elementToBeAdded;
public AddProductPromoCodeEmail(ProductPromoCodeEmail elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPromoCodeEmail addedElement = null;
boolean success = false;
try {
elementToBeAdded.setEmailAddress(delegator.getNextSeqId("ProductPromoCodeEmail"));
GenericValue newValue = delegator.makeValue("ProductPromoCodeEmail", elementToBeAdded.mapAttributeField());
addedElement = ProductPromoCodeEmailMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPromoCodeEmailAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
