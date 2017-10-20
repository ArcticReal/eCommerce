
package com.skytala.eCommerce.domain.product.relations.facility.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facility.event.attribute.FacilityAttributeFound;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.attribute.FacilityAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.attribute.FacilityAttribute;


public class FindAllFacilityAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FacilityAttribute> returnVal = new ArrayList<FacilityAttribute>();
try{
List<GenericValue> results = delegator.findAll("FacilityAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
