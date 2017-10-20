package com.skytala.eCommerce.domain.product.relations.product.command.categoryContentType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryContentType.ProductCategoryContentTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryContentType.ProductCategoryContentTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryContentType.ProductCategoryContentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductCategoryContentType extends Command {

private ProductCategoryContentType elementToBeAdded;
public AddProductCategoryContentType(ProductCategoryContentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductCategoryContentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProdCatContentTypeId(delegator.getNextSeqId("ProductCategoryContentType"));
GenericValue newValue = delegator.makeValue("ProductCategoryContentType", elementToBeAdded.mapAttributeField());
addedElement = ProductCategoryContentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductCategoryContentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
