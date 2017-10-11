package com.skytala.eCommerce.domain.product.relations.productFeatureCategory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFeatureCategory.event.ProductFeatureCategoryAdded;
import com.skytala.eCommerce.domain.product.relations.productFeatureCategory.mapper.ProductFeatureCategoryMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureCategory.model.ProductFeatureCategory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFeatureCategory extends Command {

private ProductFeatureCategory elementToBeAdded;
public AddProductFeatureCategory(ProductFeatureCategory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFeatureCategory addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductFeatureCategoryId(delegator.getNextSeqId("ProductFeatureCategory"));
GenericValue newValue = delegator.makeValue("ProductFeatureCategory", elementToBeAdded.mapAttributeField());
addedElement = ProductFeatureCategoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFeatureCategoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
