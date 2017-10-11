
package com.skytala.eCommerce.domain.product.relations.facilityContent.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facilityContent.event.FacilityContentFound;
import com.skytala.eCommerce.domain.product.relations.facilityContent.mapper.FacilityContentMapper;
import com.skytala.eCommerce.domain.product.relations.facilityContent.model.FacilityContent;


public class FindAllFacilityContents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FacilityContent> returnVal = new ArrayList<FacilityContent>();
try{
List<GenericValue> results = delegator.findAll("FacilityContent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityContentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityContentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
