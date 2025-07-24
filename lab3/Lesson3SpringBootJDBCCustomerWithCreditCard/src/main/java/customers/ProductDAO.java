package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDAO {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void save(Product product) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("productNumber", product.getProductNumber());
        params.put("price", product.getPrice());
        params.put("name", product.getName());

        jdbcTemplate.update(
                "INSERT INTO product (productNumber, price, name) VALUES (:productNumber, :price, :name)",
                params
        );

        // save supplier
        Map<String,Object> supplierParameterscc = new HashMap<String,Object>();
        supplierParameterscc.put("product_number", product.getProductNumber());
        supplierParameterscc.put("name", product.getSupplier().getName());
        supplierParameterscc.put("phone", product.getSupplier().getPhone());
        jdbcTemplate.update(
                "INSERT INTO supplier (name, phone, product_number) VALUES (:name, :phone, :product_number)",
                supplierParameterscc
        );
    }

    public Product findByProductNumber(Long productNumber) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("product_number", productNumber);
        Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE productNumber = :product_number",
                params,
                (rs, rowNum) -> new Product(
                        rs.getLong("productNumber"),
                        rs.getString("price"),
                        rs.getString("name")));

        product.setSupplier(findSupplierForProduct(productNumber));

        return product;
    }

    public Supplier findSupplierForProduct(Long productNumber) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("product_number", productNumber);
        return jdbcTemplate.queryForObject("SELECT * FROM supplier WHERE " + "product_number =:product_number ",
                params,
                (rs, rowNum) -> new Supplier(
                        rs.getString("phone"),
                        rs.getString("name")
                )
        );
    }

    public List<Product> getAllProducts() {
        List<Product> products = jdbcTemplate.query("SELECT * FROM product",
                (rs, rowNum) -> new Product( rs.getLong("productNumber"),
                        rs.getString("price"),
                        rs.getString("name")));

        for (Product product : products) {
            product.setSupplier(findSupplierForProduct(product.getProductNumber()));
        }

        return products;
    }

    public List<Product> findByProductName(String productName) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("productName", productName);

        List<Product> products = jdbcTemplate.query(
                "SELECT * FROM product WHERE name = :productName",
                namedParameters,
                (rs, rowNum) -> new Product(
                        rs.getLong("productNumber"),
                        rs.getString("price"),
                        rs.getString("name")));

        for (Product product : products) {
            product.setSupplier(findSupplierForProduct(product.getProductNumber()));
        }

        return products;
    }

    public void removeProduct(Long productNumber) {
        Map<String, Object> params = new HashMap<>();
        params.put("product_number", productNumber);

        jdbcTemplate.update("DELETE FROM supplier WHERE product_number = :product_number", params);
        jdbcTemplate.update("DELETE FROM product WHERE productNumber = :product_number", params);
    }
}
