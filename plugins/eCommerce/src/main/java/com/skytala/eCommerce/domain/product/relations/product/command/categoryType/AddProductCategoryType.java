package com.skytala.eCommerce.domain.product.relations.product.command.categoryType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryType.ProductCategoryTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryType.ProductCategoryTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryType.ProductCategoryType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductCategoryType extends Command {

private ProductCategoryType elementToBeAdded;
public AddProductCategoryType(ProductCategoryType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductCategoryType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductCategoryTypeId(delegator.getNextSeqId("ProductCategoryType"));
GenericValue newValue = delegator.makeValue("ProductCategoryType", elementToBeAdded.mapAttributeField());
addedElement = ProductCategoryTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductCategoryTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
