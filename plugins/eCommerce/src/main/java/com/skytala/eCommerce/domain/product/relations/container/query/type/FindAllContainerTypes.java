
package com.skytala.eCommerce.domain.product.relations.container.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.container.event.type.ContainerTypeFound;
import com.skytala.eCommerce.domain.product.relations.container.mapper.type.ContainerTypeMapper;
import com.skytala.eCommerce.domain.product.relations.container.model.type.ContainerType;


public class FindAllContainerTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContainerType> returnVal = new ArrayList<ContainerType>();
try{
List<GenericValue> results = delegator.findAll("ContainerType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContainerTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContainerTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
