package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.promo;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.promo.MarketingCampaignPromoDeleted;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.promo.MarketingCampaignPromo;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;

public class DeleteMarketingCampaignPromo extends Command {

private String toBeDeletedId;
public DeleteMarketingCampaignPromo(String toBeDeletedId){
this.toBeDeletedId = toBeDeletedId;
}

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success = false;

try{
int countRemoved = delegator.removeByAnd("MarketingCampaignPromo", UtilMisc.toMap("marketingCampaignPromoId", toBeDeletedId));
if(countRemoved > 0) {
success = true;
}
else{
throw new RecordNotFoundException(MarketingCampaignPromo.class);
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(MarketingCampaignPromo.class);
}
}
Event resultingEvent = new MarketingCampaignPromoDeleted(success);
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
