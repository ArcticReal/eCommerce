package com.skytala.eCommerce.domain.party.relations.party.command.dataSource;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.party.event.dataSource.PartyDataSourceUpdated;
import com.skytala.eCommerce.domain.party.relations.party.model.dataSource.PartyDataSource;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyDataSource extends Command {

private PartyDataSource elementToBeUpdated;

public UpdatePartyDataSource(PartyDataSource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyDataSource getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyDataSource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyDataSource", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyDataSource.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyDataSource.class);
}
success = false;
}
Event resultingEvent = new PartyDataSourceUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
