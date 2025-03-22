package mcs.mcsfinal2100005222.Domain.utils.modelmapper;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	ModelMapper forResponse();
	ModelMapper forRequest();
}
