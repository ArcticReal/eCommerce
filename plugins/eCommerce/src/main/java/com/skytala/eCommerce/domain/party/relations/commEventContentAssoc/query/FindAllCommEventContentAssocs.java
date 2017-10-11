
package com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.event.CommEventContentAssocFound;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.mapper.CommEventContentAssocMapper;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.model.CommEventContentAssoc;


public class FindAllCommEventContentAssocs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CommEventContentAssoc> returnVal = new ArrayList<CommEventContentAssoc>();
try{
List<GenericValue> results = delegator.findAll("CommEventContentAssoc", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CommEventContentAssocMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CommEventContentAssocFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
