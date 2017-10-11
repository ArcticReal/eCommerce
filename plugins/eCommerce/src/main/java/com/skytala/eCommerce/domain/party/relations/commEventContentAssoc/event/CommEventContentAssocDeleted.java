package com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.model.CommEventContentAssoc;
public class CommEventContentAssocDeleted implements Event{

	private boolean success;

	public CommEventContentAssocDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
