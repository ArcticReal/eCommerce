package com.skytala.eCommerce.domain.product.relations.product.command.calculatedInfo;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.calculatedInfo.ProductCalculatedInfoAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.calculatedInfo.ProductCalculatedInfoMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.calculatedInfo.ProductCalculatedInfo;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductCalculatedInfo extends Command {

private ProductCalculatedInfo elementToBeAdded;
public AddProductCalculatedInfo(ProductCalculatedInfo elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductCalculatedInfo addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductCalculatedInfo", elementToBeAdded.mapAttributeField());
addedElement = ProductCalculatedInfoMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductCalculatedInfoAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
