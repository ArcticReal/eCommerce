package com.skytala.eCommerce.domain.product.relations.productSearchConstraint.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productSearchConstraint.event.ProductSearchConstraintAdded;
import com.skytala.eCommerce.domain.product.relations.productSearchConstraint.mapper.ProductSearchConstraintMapper;
import com.skytala.eCommerce.domain.product.relations.productSearchConstraint.model.ProductSearchConstraint;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductSearchConstraint extends Command {

private ProductSearchConstraint elementToBeAdded;
public AddProductSearchConstraint(ProductSearchConstraint elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductSearchConstraint addedElement = null;
boolean success = false;
try {
elementToBeAdded.setConstraintSeqId(delegator.getNextSeqId("ProductSearchConstraint"));
GenericValue newValue = delegator.makeValue("ProductSearchConstraint", elementToBeAdded.mapAttributeField());
addedElement = ProductSearchConstraintMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductSearchConstraintAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
