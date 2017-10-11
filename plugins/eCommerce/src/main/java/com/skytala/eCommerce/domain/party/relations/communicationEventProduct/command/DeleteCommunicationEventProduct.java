package com.skytala.eCommerce.domain.party.relations.communicationEventProduct.command;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.communicationEventProduct.event.CommunicationEventProductDeleted;
import com.skytala.eCommerce.domain.party.relations.communicationEventProduct.model.CommunicationEventProduct;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;

public class DeleteCommunicationEventProduct extends Command {

private String toBeDeletedId;
public DeleteCommunicationEventProduct(String toBeDeletedId){
this.toBeDeletedId = toBeDeletedId;
}

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success = false;

try{
int countRemoved = delegator.removeByAnd("CommunicationEventProduct", UtilMisc.toMap("communicationEventProductId", toBeDeletedId));
if(countRemoved > 0) {
success = true;
}
else{
throw new RecordNotFoundException(CommunicationEventProduct.class);
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CommunicationEventProduct.class);
}
}
Event resultingEvent = new CommunicationEventProductDeleted(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;

}
public String getToBeDeletedId() {
return toBeDeletedId;
}
public void setToBeDeletedId(String toBeDeletedId) {
this.toBeDeletedId = toBeDeletedId;
}
}
