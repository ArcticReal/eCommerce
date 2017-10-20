
package com.skytala.eCommerce.domain.product.relations.facility.query.contactMechPurpose;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facility.event.contactMechPurpose.FacilityContactMechPurposeFound;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.contactMechPurpose.FacilityContactMechPurposeMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.contactMechPurpose.FacilityContactMechPurpose;


public class FindAllFacilityContactMechPurposes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FacilityContactMechPurpose> returnVal = new ArrayList<FacilityContactMechPurpose>();
try{
List<GenericValue> results = delegator.findAll("FacilityContactMechPurpose", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityContactMechPurposeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityContactMechPurposeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
