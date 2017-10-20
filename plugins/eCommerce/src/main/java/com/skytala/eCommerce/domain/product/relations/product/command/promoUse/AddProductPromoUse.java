package com.skytala.eCommerce.domain.product.relations.product.command.promoUse;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.promoUse.ProductPromoUseAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoUse.ProductPromoUseMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoUse.ProductPromoUse;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPromoUse extends Command {

private ProductPromoUse elementToBeAdded;
public AddProductPromoUse(ProductPromoUse elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPromoUse addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPromoSequenceId(delegator.getNextSeqId("ProductPromoUse"));
GenericValue newValue = delegator.makeValue("ProductPromoUse", elementToBeAdded.mapAttributeField());
addedElement = ProductPromoUseMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPromoUseAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
