package com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.model.CommEventContentAssoc;
public class CommEventContentAssocAdded implements Event{

	private CommEventContentAssoc addedCommEventContentAssoc;
	private boolean success;

	public CommEventContentAssocAdded(CommEventContentAssoc addedCommEventContentAssoc, boolean success){
		this.addedCommEventContentAssoc = addedCommEventContentAssoc;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CommEventContentAssoc getAddedCommEventContentAssoc() {
		return addedCommEventContentAssoc;
	}

}
