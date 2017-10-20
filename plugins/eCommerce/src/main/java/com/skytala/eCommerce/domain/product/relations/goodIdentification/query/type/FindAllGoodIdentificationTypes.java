
package com.skytala.eCommerce.domain.product.relations.goodIdentification.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.event.type.GoodIdentificationTypeFound;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.mapper.type.GoodIdentificationTypeMapper;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.type.GoodIdentificationType;


public class FindAllGoodIdentificationTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GoodIdentificationType> returnVal = new ArrayList<GoodIdentificationType>();
try{
List<GenericValue> results = delegator.findAll("GoodIdentificationType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GoodIdentificationTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GoodIdentificationTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
