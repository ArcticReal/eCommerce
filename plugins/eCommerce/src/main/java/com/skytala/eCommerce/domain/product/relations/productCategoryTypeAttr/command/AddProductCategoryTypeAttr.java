package com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.event.ProductCategoryTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.mapper.ProductCategoryTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.model.ProductCategoryTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductCategoryTypeAttr extends Command {

private ProductCategoryTypeAttr elementToBeAdded;
public AddProductCategoryTypeAttr(ProductCategoryTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductCategoryTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductCategoryTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = ProductCategoryTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductCategoryTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
