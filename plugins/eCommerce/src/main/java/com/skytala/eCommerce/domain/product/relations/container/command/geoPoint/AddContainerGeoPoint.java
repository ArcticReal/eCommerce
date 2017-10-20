package com.skytala.eCommerce.domain.product.relations.container.command.geoPoint;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.container.event.geoPoint.ContainerGeoPointAdded;
import com.skytala.eCommerce.domain.product.relations.container.mapper.geoPoint.ContainerGeoPointMapper;
import com.skytala.eCommerce.domain.product.relations.container.model.geoPoint.ContainerGeoPoint;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContainerGeoPoint extends Command {

private ContainerGeoPoint elementToBeAdded;
public AddContainerGeoPoint(ContainerGeoPoint elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContainerGeoPoint addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ContainerGeoPoint", elementToBeAdded.mapAttributeField());
addedElement = ContainerGeoPointMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContainerGeoPointAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
