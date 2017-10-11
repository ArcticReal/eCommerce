
package com.skytala.eCommerce.domain.product.relations.goodIdentification.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.event.GoodIdentificationFound;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.mapper.GoodIdentificationMapper;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.GoodIdentification;


public class FindAllGoodIdentifications extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GoodIdentification> returnVal = new ArrayList<GoodIdentification>();
try{
List<GenericValue> results = delegator.findAll("GoodIdentification", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GoodIdentificationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GoodIdentificationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
