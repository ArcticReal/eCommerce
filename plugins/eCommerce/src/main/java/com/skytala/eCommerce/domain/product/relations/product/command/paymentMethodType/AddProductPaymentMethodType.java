package com.skytala.eCommerce.domain.product.relations.product.command.paymentMethodType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.paymentMethodType.ProductPaymentMethodTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.paymentMethodType.ProductPaymentMethodTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.paymentMethodType.ProductPaymentMethodType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductPaymentMethodType extends Command {

private ProductPaymentMethodType elementToBeAdded;
public AddProductPaymentMethodType(ProductPaymentMethodType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductPaymentMethodType addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductPaymentMethodType", elementToBeAdded.mapAttributeField());
addedElement = ProductPaymentMethodTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductPaymentMethodTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
