package com.skytala.eCommerce.domain.product.relations.product.command.categoryAttribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryAttribute.ProductCategoryAttributeAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryAttribute.ProductCategoryAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryAttribute.ProductCategoryAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductCategoryAttribute extends Command {

private ProductCategoryAttribute elementToBeAdded;
public AddProductCategoryAttribute(ProductCategoryAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductCategoryAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductCategoryAttribute", elementToBeAdded.mapAttributeField());
addedElement = ProductCategoryAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductCategoryAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
