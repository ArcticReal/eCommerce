
package com.skytala.eCommerce.domain.accounting.relations.giftCard.query.fulfillment;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.event.fulfillment.GiftCardFulfillmentFound;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.mapper.fulfillment.GiftCardFulfillmentMapper;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.model.fulfillment.GiftCardFulfillment;


public class FindAllGiftCardFulfillments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GiftCardFulfillment> returnVal = new ArrayList<GiftCardFulfillment>();
try{
List<GenericValue> results = delegator.findAll("GiftCardFulfillment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GiftCardFulfillmentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GiftCardFulfillmentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
