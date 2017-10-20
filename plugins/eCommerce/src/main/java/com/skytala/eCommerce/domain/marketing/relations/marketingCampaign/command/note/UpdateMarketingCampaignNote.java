package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.note;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.note.MarketingCampaignNoteUpdated;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.note.MarketingCampaignNote;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateMarketingCampaignNote extends Command {

private MarketingCampaignNote elementToBeUpdated;

public UpdateMarketingCampaignNote(MarketingCampaignNote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public MarketingCampaignNote getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(MarketingCampaignNote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("MarketingCampaignNote", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(MarketingCampaignNote.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(MarketingCampaignNote.class);
}
success = false;
}
Event resultingEvent = new MarketingCampaignNoteUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
