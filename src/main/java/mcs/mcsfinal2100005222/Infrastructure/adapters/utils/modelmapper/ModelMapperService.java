package mcs.mcsfinal2100005222.Infrastructure.adapters.utils.modelmapper;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	ModelMapper forResponse();
	ModelMapper forRequest();
}
