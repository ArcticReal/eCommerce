
package com.skytala.eCommerce.domain.accounting.relations.payment.query.groupMember;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.groupMember.PaymentGroupMemberFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.groupMember.PaymentGroupMemberMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.groupMember.PaymentGroupMember;


public class FindAllPaymentGroupMembers extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGroupMember> returnVal = new ArrayList<PaymentGroupMember>();
try{
List<GenericValue> results = delegator.findAll("PaymentGroupMember", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGroupMemberMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGroupMemberFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
