package com.skytala.eCommerce.domain.product.relations.productFeatureCategoryAppl.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFeatureCategoryAppl.event.ProductFeatureCategoryApplAdded;
import com.skytala.eCommerce.domain.product.relations.productFeatureCategoryAppl.mapper.ProductFeatureCategoryApplMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureCategoryAppl.model.ProductFeatureCategoryAppl;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFeatureCategoryAppl extends Command {

private ProductFeatureCategoryAppl elementToBeAdded;
public AddProductFeatureCategoryAppl(ProductFeatureCategoryAppl elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFeatureCategoryAppl addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductFeatureCategoryAppl", elementToBeAdded.mapAttributeField());
addedElement = ProductFeatureCategoryApplMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFeatureCategoryApplAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
