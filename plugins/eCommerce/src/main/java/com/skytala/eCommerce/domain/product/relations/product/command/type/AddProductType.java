package com.skytala.eCommerce.domain.product.relations.product.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.type.ProductTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.type.ProductTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.type.ProductType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductType extends Command {

private ProductType elementToBeAdded;
public AddProductType(ProductType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductTypeId(delegator.getNextSeqId("ProductType"));
GenericValue newValue = delegator.makeValue("ProductType", elementToBeAdded.mapAttributeField());
addedElement = ProductTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
