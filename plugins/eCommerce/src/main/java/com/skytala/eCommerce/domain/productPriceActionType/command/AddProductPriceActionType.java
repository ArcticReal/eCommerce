package com.skytala.eCommerce.domain.productPriceActionType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productPriceActionType.event.ProductPriceActionTypeAdded;
import com.skytala.eCommerce.domain.productPriceActionType.mapper.ProductPriceActionTypeMapper;
import com.skytala.eCommerce.domain.productPriceActionType.model.ProductPriceActionType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPriceActionType extends Command {

private ProductPriceActionType elementToBeAdded;
public AddProductPriceActionType(ProductPriceActionType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPriceActionType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductPriceActionTypeId(delegator.getNextSeqId("ProductPriceActionType"));
GenericValue newValue = delegator.makeValue("ProductPriceActionType", elementToBeAdded.mapAttributeField());
addedElement = ProductPriceActionTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPriceActionTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
