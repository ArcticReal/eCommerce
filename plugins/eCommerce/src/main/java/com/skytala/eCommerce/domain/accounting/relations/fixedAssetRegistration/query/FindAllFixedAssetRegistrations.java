
package com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.event.FixedAssetRegistrationFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.mapper.FixedAssetRegistrationMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.model.FixedAssetRegistration;


public class FindAllFixedAssetRegistrations extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetRegistration> returnVal = new ArrayList<FixedAssetRegistration>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetRegistration", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetRegistrationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetRegistrationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
