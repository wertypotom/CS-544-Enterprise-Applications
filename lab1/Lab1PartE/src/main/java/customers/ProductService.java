package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    @Autowired
    EmailSender emailSender;

    @Autowired
    ProductDAO productDAO;

    public void addProduct(String name) {
        Product product = new Product(name);

        productDAO.save(product);
        emailSender.sendEmail("email@email.com", "Save product with name: " + name + " to our database !");
    }
}
