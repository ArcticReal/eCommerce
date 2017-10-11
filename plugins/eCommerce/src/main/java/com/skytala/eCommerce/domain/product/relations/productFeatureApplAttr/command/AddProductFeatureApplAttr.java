package com.skytala.eCommerce.domain.product.relations.productFeatureApplAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFeatureApplAttr.event.ProductFeatureApplAttrAdded;
import com.skytala.eCommerce.domain.product.relations.productFeatureApplAttr.mapper.ProductFeatureApplAttrMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureApplAttr.model.ProductFeatureApplAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFeatureApplAttr extends Command {

private ProductFeatureApplAttr elementToBeAdded;
public AddProductFeatureApplAttr(ProductFeatureApplAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFeatureApplAttr addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAttrName(delegator.getNextSeqId("ProductFeatureApplAttr"));
GenericValue newValue = delegator.makeValue("ProductFeatureApplAttr", elementToBeAdded.mapAttributeField());
addedElement = ProductFeatureApplAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFeatureApplAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
