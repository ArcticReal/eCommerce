package com.skytala.eCommerce.domain.product.relations.productPriceType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPriceType.event.ProductPriceTypeAdded;
import com.skytala.eCommerce.domain.product.relations.productPriceType.mapper.ProductPriceTypeMapper;
import com.skytala.eCommerce.domain.product.relations.productPriceType.model.ProductPriceType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPriceType extends Command {

private ProductPriceType elementToBeAdded;
public AddProductPriceType(ProductPriceType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPriceType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductPriceTypeId(delegator.getNextSeqId("ProductPriceType"));
GenericValue newValue = delegator.makeValue("ProductPriceType", elementToBeAdded.mapAttributeField());
addedElement = ProductPriceTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPriceTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
