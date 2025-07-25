package mal.model.member;

import java.util.List;

import mal.domain.SnsProvider;

public interface SnsProviderDAO {

	public List selectAll();
	public SnsProvider selectByName(String name);
}
