package com.skytala.eCommerce.domain.product.relations.productKeyword.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productKeyword.event.ProductKeywordAdded;
import com.skytala.eCommerce.domain.product.relations.productKeyword.mapper.ProductKeywordMapper;
import com.skytala.eCommerce.domain.product.relations.productKeyword.model.ProductKeyword;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductKeyword extends Command {

private ProductKeyword elementToBeAdded;
public AddProductKeyword(ProductKeyword elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductKeyword addedElement = null;
boolean success = false;
try {
elementToBeAdded.setKeyword(delegator.getNextSeqId("ProductKeyword"));
GenericValue newValue = delegator.makeValue("ProductKeyword", elementToBeAdded.mapAttributeField());
addedElement = ProductKeywordMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductKeywordAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
