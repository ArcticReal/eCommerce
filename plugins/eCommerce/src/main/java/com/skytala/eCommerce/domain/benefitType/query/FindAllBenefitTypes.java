
package com.skytala.eCommerce.domain.benefitType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.benefitType.event.BenefitTypeFound;
import com.skytala.eCommerce.domain.benefitType.mapper.BenefitTypeMapper;
import com.skytala.eCommerce.domain.benefitType.model.BenefitType;


public class FindAllBenefitTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BenefitType> returnVal = new ArrayList<BenefitType>();
try{
List<GenericValue> results = delegator.findAll("BenefitType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BenefitTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BenefitTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
