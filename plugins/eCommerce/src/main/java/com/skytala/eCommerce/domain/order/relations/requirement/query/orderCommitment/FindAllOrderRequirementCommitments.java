
package com.skytala.eCommerce.domain.order.relations.requirement.query.orderCommitment;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.requirement.event.orderCommitment.OrderRequirementCommitmentFound;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.orderCommitment.OrderRequirementCommitmentMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.orderCommitment.OrderRequirementCommitment;


public class FindAllOrderRequirementCommitments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderRequirementCommitment> returnVal = new ArrayList<OrderRequirementCommitment>();
try{
List<GenericValue> results = delegator.findAll("OrderRequirementCommitment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderRequirementCommitmentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderRequirementCommitmentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
