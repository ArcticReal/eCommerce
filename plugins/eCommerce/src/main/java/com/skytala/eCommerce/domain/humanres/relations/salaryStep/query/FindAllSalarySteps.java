
package com.skytala.eCommerce.domain.humanres.relations.salaryStep.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.event.SalaryStepFound;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.mapper.SalaryStepMapper;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.model.SalaryStep;


public class FindAllSalarySteps extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SalaryStep> returnVal = new ArrayList<SalaryStep>();
try{
List<GenericValue> results = delegator.findAll("SalaryStep", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SalaryStepMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SalaryStepFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
