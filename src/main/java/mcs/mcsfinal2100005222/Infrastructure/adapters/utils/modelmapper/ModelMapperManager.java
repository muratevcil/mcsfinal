package mcs.mcsfinal2100005222.Infrastructure.adapters.utils.modelmapper;


import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ModelMapperManager implements ModelMapperService {
	private ModelMapper modelMapper;
	@Autowired
	public ModelMapperManager(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	public ModelMapperManager() {

	}
	@Override
	public ModelMapper forResponse() {
		this.modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)

		.setAmbiguityIgnored(true)  // Ambiguity = Belirsizlik. Mapping objesinde aynı isimlerdeki verilerin belirsizlik yaratması konusunda hata vermesini engellemek için Ignored i true yapıyoruz.
		.setMatchingStrategy(MatchingStrategies.LOOSE); // Veritabanı nesnesindeki bütün columnları almamıza gerek olmadığını belirtiyoruz.

		/*this.modelMapper.addMappings(new PropertyMap<Product, ProductEntity>() {
			protected void configure() {
				map(source.getCategories(), destination.getProductCategory());
				map(source.get(), destination.getProductEAVList());
			}
		});*/

		return this.modelMapper;
	}

	@Override
	public ModelMapper forRequest() {
		this.modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)
		.setAmbiguityIgnored(true)
		.setMatchingStrategy(MatchingStrategies.LOOSE); // Requestte ise bütün verilerin doğru girilip, veri tabanıyla eşlenmesini kontrol etmek istediğimiz için standart yaptık.
		return this.modelMapper;
	}

}
