package cn.sabercon.common.jpa;

import cn.sabercon.common.anno.Tx;
import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

/**
 * @author SaberCon
 * @since 1.0.0
 */
public class BaseJpaRepositoryImpl<T> extends SimpleJpaRepository<T, Long> implements BaseJpaRepository<T> {

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;
    private final PersistenceProvider provider;

    /**
     * 参考了官方示例的实现
     */
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public BaseJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
        this.provider = PersistenceProvider.fromEntityManager(entityManager);
    }

    @Tx
    @Override
    public int increment(Long id, String field) {
        var cb = em.getCriteriaBuilder();
        var update = cb.createCriteriaUpdate(entityInformation.getJavaType());
        var root = update.getRoot();
        update.set(root.<Number>get(field), cb.sum(root.get(field), 1))
                .where(cb.equal(root.get(entityInformation.getIdAttribute()), id));
        return em.createQuery(update).executeUpdate();
    }
}
