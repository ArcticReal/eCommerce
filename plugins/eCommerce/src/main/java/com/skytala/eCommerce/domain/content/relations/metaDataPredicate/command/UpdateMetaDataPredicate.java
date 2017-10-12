package com.skytala.eCommerce.domain.content.relations.metaDataPredicate.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.event.MetaDataPredicateUpdated;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.model.MetaDataPredicate;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateMetaDataPredicate extends Command {

private MetaDataPredicate elementToBeUpdated;

public UpdateMetaDataPredicate(MetaDataPredicate elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public MetaDataPredicate getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(MetaDataPredicate elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("MetaDataPredicate", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(MetaDataPredicate.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(MetaDataPredicate.class);
}
success = false;
}
Event resultingEvent = new MetaDataPredicateUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
