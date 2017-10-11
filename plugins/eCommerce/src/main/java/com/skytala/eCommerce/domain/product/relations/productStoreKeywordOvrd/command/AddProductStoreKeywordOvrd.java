package com.skytala.eCommerce.domain.product.relations.productStoreKeywordOvrd.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productStoreKeywordOvrd.event.ProductStoreKeywordOvrdAdded;
import com.skytala.eCommerce.domain.product.relations.productStoreKeywordOvrd.mapper.ProductStoreKeywordOvrdMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreKeywordOvrd.model.ProductStoreKeywordOvrd;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStoreKeywordOvrd extends Command {

private ProductStoreKeywordOvrd elementToBeAdded;
public AddProductStoreKeywordOvrd(ProductStoreKeywordOvrd elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStoreKeywordOvrd addedElement = null;
boolean success = false;
try {
elementToBeAdded.setKeyword(delegator.getNextSeqId("ProductStoreKeywordOvrd"));
GenericValue newValue = delegator.makeValue("ProductStoreKeywordOvrd", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreKeywordOvrdMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreKeywordOvrdAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
