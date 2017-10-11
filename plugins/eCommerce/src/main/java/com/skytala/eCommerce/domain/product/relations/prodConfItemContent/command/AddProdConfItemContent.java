package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event.ProdConfItemContentAdded;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.mapper.ProdConfItemContentMapper;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.ProdConfItemContent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProdConfItemContent extends Command {

private ProdConfItemContent elementToBeAdded;
public AddProdConfItemContent(ProdConfItemContent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProdConfItemContent addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProdConfItemContent", elementToBeAdded.mapAttributeField());
addedElement = ProdConfItemContentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProdConfItemContentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
