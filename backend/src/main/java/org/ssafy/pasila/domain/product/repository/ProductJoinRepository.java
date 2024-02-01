package org.ssafy.pasila.domain.product.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.product.dto.ProductResponse;
import org.ssafy.pasila.domain.product.entity.Product;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductJoinRepository {
    private final EntityManager em;

    public List<ProductResponse> findAllWithCategory() {
        return em.createQuery(
                        "SELECT new org.ssafy.pasila.domain.product.dto.ProductResponse" +
                                "(p.id, m.id, p.name, p.description, p.createdAt, p.thumbnail, c.id) " +
                                "FROM Product p Join p.category c Join p.member m " ,  ProductResponse.class)
                .getResultList();
    }

    public ProductResponse findById(String productId){
        return em.createQuery("Select new org.ssafy.pasila.domain.product.dto.ProductResponse" +
                        "(p.id, m.id, p.name, p.description, p.createdAt, p.thumbnail, c.id) " +
                        "FROM Product p Join p.category c Join p.member m " +
                        "where p.id = :productId " , ProductResponse.class)
                .setParameter("productId", productId).getSingleResult();
    }


}