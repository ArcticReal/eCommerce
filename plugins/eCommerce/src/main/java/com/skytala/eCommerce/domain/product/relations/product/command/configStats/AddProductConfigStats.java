package com.skytala.eCommerce.domain.product.relations.product.command.configStats;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.configStats.ProductConfigStatsAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configStats.ProductConfigStatsMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.configStats.ProductConfigStats;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductConfigStats extends Command {

private ProductConfigStats elementToBeAdded;
public AddProductConfigStats(ProductConfigStats elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductConfigStats addedElement = null;
boolean success = false;
try {
elementToBeAdded.setConfigId(delegator.getNextSeqId("ProductConfigStats"));
GenericValue newValue = delegator.makeValue("ProductConfigStats", elementToBeAdded.mapAttributeField());
addedElement = ProductConfigStatsMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductConfigStatsAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
