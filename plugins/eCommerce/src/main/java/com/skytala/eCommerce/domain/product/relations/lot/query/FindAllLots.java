
package com.skytala.eCommerce.domain.product.relations.lot.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.lot.event.LotFound;
import com.skytala.eCommerce.domain.product.relations.lot.mapper.LotMapper;
import com.skytala.eCommerce.domain.product.relations.lot.model.Lot;


public class FindAllLots extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Lot> returnVal = new ArrayList<Lot>();
try{
List<GenericValue> results = delegator.findAll("Lot", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(LotMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new LotFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
