package com.skytala.eCommerce.domain.shipment.relations.itemIssuance.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event.role.ItemIssuanceRoleAdded;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.mapper.role.ItemIssuanceRoleMapper;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.model.role.ItemIssuanceRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddItemIssuanceRole extends Command {

private ItemIssuanceRole elementToBeAdded;
public AddItemIssuanceRole(ItemIssuanceRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ItemIssuanceRole addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("ItemIssuanceRole"));
GenericValue newValue = delegator.makeValue("ItemIssuanceRole", elementToBeAdded.mapAttributeField());
addedElement = ItemIssuanceRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ItemIssuanceRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
