package az.security.bookstore.specification;

import az.security.bookstore.dao.entity.BookEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Locale;


@Component
public class BookSpecification {


    public static Specification<BookEntity> hasName(String name){
        return (root,query,criteriaBuilder) ->
                name == null ? criteriaBuilder.isNotNull(root.get("name"))
                        : criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" +
                        name.toLowerCase(Locale.ROOT) + "%");
    }


    public static Specification<BookEntity> hasStockCount(Integer stockCount){
        return (root,query,criteriaBuilder) ->
                stockCount == null ? criteriaBuilder.isNotNull(root.get("stockCount"))
                        : criteriaBuilder.equal(root.get("stockCount"),stockCount);
    }

    public static Specification<BookEntity> hasPrice(Double price){
        return (root,query,criteriaBuilder) ->
                price == null ? criteriaBuilder.isNotNull(root.get("price"))
                        : criteriaBuilder.equal(root.get("price"), price);
    }



}
