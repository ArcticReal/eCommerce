
package com.skytala.eCommerce.domain.payGrade.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.payGrade.event.PayGradeFound;
import com.skytala.eCommerce.domain.payGrade.mapper.PayGradeMapper;
import com.skytala.eCommerce.domain.payGrade.model.PayGrade;


public class FindAllPayGrades extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PayGrade> returnVal = new ArrayList<PayGrade>();
try{
List<GenericValue> results = delegator.findAll("PayGrade", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PayGradeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PayGradeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
