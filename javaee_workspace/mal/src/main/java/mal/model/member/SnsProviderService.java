package mal.model.member;

import java.util.List;

import mal.domain.SnsProvider;

public interface SnsProviderService {

	public List selectAll();
	public SnsProvider selectByName(String name);
}
