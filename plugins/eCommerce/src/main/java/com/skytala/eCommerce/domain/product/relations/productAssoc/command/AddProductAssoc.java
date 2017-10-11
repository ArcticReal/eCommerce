package com.skytala.eCommerce.domain.product.relations.productAssoc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productAssoc.event.ProductAssocAdded;
import com.skytala.eCommerce.domain.product.relations.productAssoc.mapper.ProductAssocMapper;
import com.skytala.eCommerce.domain.product.relations.productAssoc.model.ProductAssoc;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductAssoc extends Command {

private ProductAssoc elementToBeAdded;
public AddProductAssoc(ProductAssoc elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductAssoc addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductAssoc", elementToBeAdded.mapAttributeField());
addedElement = ProductAssocMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductAssocAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
