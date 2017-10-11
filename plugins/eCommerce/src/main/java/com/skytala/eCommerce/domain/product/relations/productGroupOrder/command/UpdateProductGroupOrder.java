package com.skytala.eCommerce.domain.product.relations.productGroupOrder.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productGroupOrder.event.ProductGroupOrderUpdated;
import com.skytala.eCommerce.domain.product.relations.productGroupOrder.model.ProductGroupOrder;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductGroupOrder extends Command {

private ProductGroupOrder elementToBeUpdated;

public UpdateProductGroupOrder(ProductGroupOrder elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductGroupOrder getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductGroupOrder elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductGroupOrder", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductGroupOrder.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductGroupOrder.class);
}
success = false;
}
Event resultingEvent = new ProductGroupOrderUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
