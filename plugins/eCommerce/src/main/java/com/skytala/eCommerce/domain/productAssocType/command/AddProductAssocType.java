package com.skytala.eCommerce.domain.productAssocType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productAssocType.event.ProductAssocTypeAdded;
import com.skytala.eCommerce.domain.productAssocType.mapper.ProductAssocTypeMapper;
import com.skytala.eCommerce.domain.productAssocType.model.ProductAssocType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductAssocType extends Command {

private ProductAssocType elementToBeAdded;
public AddProductAssocType(ProductAssocType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductAssocType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductAssocTypeId(delegator.getNextSeqId("ProductAssocType"));
GenericValue newValue = delegator.makeValue("ProductAssocType", elementToBeAdded.mapAttributeField());
addedElement = ProductAssocTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductAssocTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
