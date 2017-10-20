package com.skytala.eCommerce.domain.product.relations.product.command.maintType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.maintType.ProductMaintTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.maintType.ProductMaintTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.maintType.ProductMaintType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductMaintType extends Command {

private ProductMaintType elementToBeAdded;
public AddProductMaintType(ProductMaintType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductMaintType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductMaintTypeId(delegator.getNextSeqId("ProductMaintType"));
GenericValue newValue = delegator.makeValue("ProductMaintType", elementToBeAdded.mapAttributeField());
addedElement = ProductMaintTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductMaintTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
