package com.skytala.eCommerce.domain.product.relations.saleType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.saleType.event.SaleTypeAdded;
import com.skytala.eCommerce.domain.product.relations.saleType.mapper.SaleTypeMapper;
import com.skytala.eCommerce.domain.product.relations.saleType.model.SaleType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSaleType extends Command {

private SaleType elementToBeAdded;
public AddSaleType(SaleType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SaleType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSaleTypeId(delegator.getNextSeqId("SaleType"));
GenericValue newValue = delegator.makeValue("SaleType", elementToBeAdded.mapAttributeField());
addedElement = SaleTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SaleTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
