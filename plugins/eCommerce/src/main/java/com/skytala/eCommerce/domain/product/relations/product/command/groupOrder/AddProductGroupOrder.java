package com.skytala.eCommerce.domain.product.relations.product.command.groupOrder;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.groupOrder.ProductGroupOrderAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.groupOrder.ProductGroupOrderMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.groupOrder.ProductGroupOrder;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductGroupOrder extends Command {

private ProductGroupOrder elementToBeAdded;
public AddProductGroupOrder(ProductGroupOrder elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductGroupOrder addedElement = null;
boolean success = false;
try {
elementToBeAdded.setGroupOrderId(delegator.getNextSeqId("ProductGroupOrder"));
GenericValue newValue = delegator.makeValue("ProductGroupOrder", elementToBeAdded.mapAttributeField());
addedElement = ProductGroupOrderMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductGroupOrderAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
