package com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.event.ProdConfItemContentTypeAdded;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.mapper.ProdConfItemContentTypeMapper;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.model.ProdConfItemContentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProdConfItemContentType extends Command {

private ProdConfItemContentType elementToBeAdded;
public AddProdConfItemContentType(ProdConfItemContentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProdConfItemContentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setConfItemContentTypeId(delegator.getNextSeqId("ProdConfItemContentType"));
GenericValue newValue = delegator.makeValue("ProdConfItemContentType", elementToBeAdded.mapAttributeField());
addedElement = ProdConfItemContentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProdConfItemContentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
