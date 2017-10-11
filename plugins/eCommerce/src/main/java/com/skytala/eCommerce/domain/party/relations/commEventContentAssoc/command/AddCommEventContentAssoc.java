package com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.event.CommEventContentAssocAdded;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.mapper.CommEventContentAssocMapper;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.model.CommEventContentAssoc;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCommEventContentAssoc extends Command {

private CommEventContentAssoc elementToBeAdded;
public AddCommEventContentAssoc(CommEventContentAssoc elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CommEventContentAssoc addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CommEventContentAssoc", elementToBeAdded.mapAttributeField());
addedElement = CommEventContentAssocMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CommEventContentAssocAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
