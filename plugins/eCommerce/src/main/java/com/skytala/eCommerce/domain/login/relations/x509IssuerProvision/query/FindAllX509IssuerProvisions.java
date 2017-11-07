
package com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.event.X509IssuerProvisionFound;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.mapper.X509IssuerProvisionMapper;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.model.X509IssuerProvision;


public class FindAllX509IssuerProvisions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<X509IssuerProvision> returnVal = new ArrayList<X509IssuerProvision>();
try{
List<GenericValue> results = delegator.findAll("X509IssuerProvision", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(X509IssuerProvisionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new X509IssuerProvisionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
