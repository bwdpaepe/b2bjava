package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Bedrijf;
import domein.Product;
import repository.GenericDao;
import service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private GenericDao<Product> productRepoMock;

	@InjectMocks
	private ProductService productService;

	private static final String NAAMBEDRIJF_LEVERANCIER = "Bedrijf A";
	private static final String STRAAT_LEVERANCIER = "Straat A";
	private static final String HUISNUMMER_LEVERANCIER = "A1";
	private static final String POSTCODE_LEVERANCIER = "1234A";
	private static final String STAD_LEVERANCIER = " stad A";
	private static final String LAND_LEVERANCIER = "land A";
	private static final String TELEFOONNUMMER_LEVERANCIER = "0123456789";
	private static final String LOGO_FILENAME_LEVERANCIER = "logo_bedrijf_A";
	private final Bedrijf LEVERANCIER = new Bedrijf(NAAMBEDRIJF_LEVERANCIER, STRAAT_LEVERANCIER, HUISNUMMER_LEVERANCIER,
			POSTCODE_LEVERANCIER, STAD_LEVERANCIER, LAND_LEVERANCIER, TELEFOONNUMMER_LEVERANCIER,
			LOGO_FILENAME_LEVERANCIER);

	private static final String NAAM = "Product A";
	private static final double EENHEIDSPRIJS = 10.0;
	private static final long PRODUCT_ID = 1L;

	private Product product;

	@BeforeEach
	void maakProduct() {
		product = new Product(NAAM, EENHEIDSPRIJS, LEVERANCIER);
	}

	@Test
	void testGetProductById() {
		Mockito.doReturn(product).when(productRepoMock).get(PRODUCT_ID);

		Product product2 = productService.getProductById(PRODUCT_ID);

		Assertions.assertEquals(product.getNaam(), product2.getNaam());
		Assertions.assertEquals(product.getEenheidsprijs(), product2.getEenheidsprijs());

		Mockito.verify(productRepoMock).get(PRODUCT_ID);

	}
}
