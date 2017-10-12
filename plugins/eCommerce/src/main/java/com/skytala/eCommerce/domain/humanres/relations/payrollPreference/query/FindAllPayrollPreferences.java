
package com.skytala.eCommerce.domain.humanres.relations.payrollPreference.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.event.PayrollPreferenceFound;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.mapper.PayrollPreferenceMapper;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.model.PayrollPreference;


public class FindAllPayrollPreferences extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PayrollPreference> returnVal = new ArrayList<PayrollPreference>();
try{
List<GenericValue> results = delegator.findAll("PayrollPreference", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PayrollPreferenceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PayrollPreferenceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
