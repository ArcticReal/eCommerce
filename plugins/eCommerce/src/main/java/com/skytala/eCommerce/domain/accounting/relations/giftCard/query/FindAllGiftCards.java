
package com.skytala.eCommerce.domain.accounting.relations.giftCard.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.event.GiftCardFound;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.mapper.GiftCardMapper;
import com.skytala.eCommerce.domain.accounting.relations.giftCard.model.GiftCard;


public class FindAllGiftCards extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GiftCard> returnVal = new ArrayList<GiftCard>();
try{
List<GenericValue> results = delegator.findAll("GiftCard", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GiftCardMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GiftCardFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
