package com.skytala.eCommerce.domain.productContentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productContentType.event.ProductContentTypeAdded;
import com.skytala.eCommerce.domain.productContentType.mapper.ProductContentTypeMapper;
import com.skytala.eCommerce.domain.productContentType.model.ProductContentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductContentType extends Command {

private ProductContentType elementToBeAdded;
public AddProductContentType(ProductContentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductContentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductContentTypeId(delegator.getNextSeqId("ProductContentType"));
GenericValue newValue = delegator.makeValue("ProductContentType", elementToBeAdded.mapAttributeField());
addedElement = ProductContentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductContentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
