
package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayRespMsg.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayRespMsg.event.PaymentGatewayRespMsgFound;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayRespMsg.mapper.PaymentGatewayRespMsgMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayRespMsg.model.PaymentGatewayRespMsg;


public class FindAllPaymentGatewayRespMsgs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewayRespMsg> returnVal = new ArrayList<PaymentGatewayRespMsg>();
try{
List<GenericValue> results = delegator.findAll("PaymentGatewayRespMsg", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGatewayRespMsgMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGatewayRespMsgFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
