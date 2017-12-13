package com.skytala.eCommerce.domain.product.relations.product.command.typeAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.typeAttr.ProductTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.typeAttr.ProductTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.typeAttr.ProductTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductTypeAttr extends Command {

private ProductTypeAttr elementToBeAdded;
public AddProductTypeAttr(ProductTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductTypeAttr addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAttrName(delegator.getNextSeqId("ProductTypeAttr"));
GenericValue newValue = delegator.makeValue("ProductTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = ProductTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
