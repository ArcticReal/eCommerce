
package com.skytala.eCommerce.domain.product.relations.containerGeoPoint.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.containerGeoPoint.event.ContainerGeoPointFound;
import com.skytala.eCommerce.domain.product.relations.containerGeoPoint.mapper.ContainerGeoPointMapper;
import com.skytala.eCommerce.domain.product.relations.containerGeoPoint.model.ContainerGeoPoint;


public class FindAllContainerGeoPoints extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContainerGeoPoint> returnVal = new ArrayList<ContainerGeoPoint>();
try{
List<GenericValue> results = delegator.findAll("ContainerGeoPoint", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContainerGeoPointMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContainerGeoPointFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
