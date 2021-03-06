
package com.skytala.eCommerce.domain.product.relations.facility.query.typeAttr;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facility.event.typeAttr.FacilityTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.typeAttr.FacilityTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.typeAttr.FacilityTypeAttr;


public class FindAllFacilityTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FacilityTypeAttr> returnVal = new ArrayList<FacilityTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("FacilityTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
