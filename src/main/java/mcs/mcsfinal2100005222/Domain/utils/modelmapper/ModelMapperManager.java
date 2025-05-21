package mcs.mcsfinal2100005222.Domain.utils.modelmapper;


import mcs.mcsfinal2100005222.Domain.entities.product.ProductEntity;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.Product;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
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
		modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)

		.setAmbiguityIgnored(true)  // Ambiguity = Belirsizlik. Mapping objesinde aynı isimlerdeki verilerin belirsizlik yaratması konusunda hata vermesini engellemek için Ignored i true yapıyoruz.
		.setMatchingStrategy(MatchingStrategies.LOOSE); // Veritabanı nesnesindeki bütün columnları almamıza gerek olmadığını belirtiyoruz.

		return modelMapper;
	}

	@Override
	public ModelMapper forRequest() {
		modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)
		.setAmbiguityIgnored(true)
				.setPropertyCondition(Conditions.isNotNull())
		.setMatchingStrategy(MatchingStrategies.LOOSE); // Requestte ise bütün verilerin doğru girilip, veri tabanıyla eşlenmesini kontrol etmek istediğimiz için standart yaptık.
		return modelMapper;
	}

	/*public void productDomainToDb(){
		TypeMap<ProductEntity,Product> typeMap = modelMapper.createTypeMap(ProductEntity.class, Product.class);
		typeMap.addMappings(mapper->{
			mapper.map(ProductEntity::getProductDescription,Product::setProductDescription);
			mapper.map(ProductEntity::getProductName,Product::setProductName);
			mapper.map(ProductEntity::getProductUuid,Product::setProductUuid);
			mapper.map(ProductEntity::getProductMediaURLs,Product::setProductMediaURLs);
			mapper.map(ProductEntity::getProductEAVList,Product::setProductEAVList);
			mapper.map(ProductEntity::getProductSellerUserUUID,Product::setProductSellerUserUUID);
			mapper.map(ProductEntity::getProductStockQuantity,Product::setProductStockQuantity);
			mapper.map(ProductEntity::getCategories,Product::setProductCategories);

		});
	}*/

	/*public void productEAVDomainToDb(){
		TypeMap<ProductEAVEntity,ProductEAV> productEAVDomainToDb = modelMapper.createTypeMap(ProductEAVEntity.class, ProductEAV.class);
		productEAVDomainToDb.addMappings(mapper->{
			mapper.map(ProductEAVEntity::getAttributeName, ProductEAV::setAttributeName);
			mapper.map(ProductEAVEntity::getAttributeValue, ProductEAV::setAttributeValue);
		});
	}*/

}
