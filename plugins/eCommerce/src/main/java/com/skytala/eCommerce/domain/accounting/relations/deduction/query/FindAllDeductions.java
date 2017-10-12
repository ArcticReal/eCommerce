
package com.skytala.eCommerce.domain.accounting.relations.deduction.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.deduction.event.DeductionFound;
import com.skytala.eCommerce.domain.accounting.relations.deduction.mapper.DeductionMapper;
import com.skytala.eCommerce.domain.accounting.relations.deduction.model.Deduction;


public class FindAllDeductions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Deduction> returnVal = new ArrayList<Deduction>();
try{
List<GenericValue> results = delegator.findAll("Deduction", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DeductionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DeductionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
