package cn.sabercon.common.orm;

import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;

/**
 * @author SaberCon
 * @since 1.0.0
 */
public class CustomRepositoryImpl<T> implements CustomRepository<T> {

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;
    private final PersistenceProvider provider;

    private String incrementUpdateString;

    /**
     * 参考了 {@link SimpleJpaRepository} 的实现
     */
    public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {

        Assert.notNull(entityInformation, "JpaEntityInformation must not be null!");
        Assert.notNull(entityManager, "EntityManager must not be null!");

        this.entityInformation = entityInformation;
        this.em = entityManager;
        this.provider = PersistenceProvider.fromEntityManager(entityManager);
        if (!entityInformation.hasCompositeId() && entityInformation.getIdAttributeNames().iterator().hasNext()) {
            this.incrementUpdateString = String.format("update %s set :field = :field + 1 where %s = :id",
                    entityInformation.getEntityName(), entityInformation.getIdAttributeNames().iterator().next());
        }
    }

    public CustomRepositoryImpl(Class<T> domainClass, EntityManager em) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
    }

    @Override
    @Transactional
    public int increment(Long id, String field) {
        if (ObjectUtils.isEmpty(incrementUpdateString)) {
            return 0;
        }
        var cb = em.getCriteriaBuilder();
        var update = cb.createCriteriaUpdate(entityInformation.getJavaType());
        var root = update.getRoot();
        update.set(root.get(field), cb.sum(root.get(field), 1L)).where(cb.equal(root.get(entityInformation.getIdAttribute()), id));
        return em.createQuery(update).executeUpdate();
    }
}
