package com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.model.CommEventContentAssoc;
public class CommEventContentAssocFound implements Event{

	private List<CommEventContentAssoc> commEventContentAssocs;

	public CommEventContentAssocFound(List<CommEventContentAssoc> commEventContentAssocs) {
		this.commEventContentAssocs = commEventContentAssocs;
	}

	public List<CommEventContentAssoc> getCommEventContentAssocs()	{
		return commEventContentAssocs;
	}

}
