
package com.skytala.eCommerce.domain.product.relations.subscriptionFulfillmentPiece.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.subscriptionFulfillmentPiece.event.SubscriptionFulfillmentPieceFound;
import com.skytala.eCommerce.domain.product.relations.subscriptionFulfillmentPiece.mapper.SubscriptionFulfillmentPieceMapper;
import com.skytala.eCommerce.domain.product.relations.subscriptionFulfillmentPiece.model.SubscriptionFulfillmentPiece;


public class FindAllSubscriptionFulfillmentPieces extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SubscriptionFulfillmentPiece> returnVal = new ArrayList<SubscriptionFulfillmentPiece>();
try{
List<GenericValue> results = delegator.findAll("SubscriptionFulfillmentPiece", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SubscriptionFulfillmentPieceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SubscriptionFulfillmentPieceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
