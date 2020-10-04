package cn.sabercon.common.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * mongo 数据层通用实现接口
 *
 * @author SaberCon
 * @since 1.0.0
 */
@NoRepositoryBean
public interface MongoRepo<T> extends MongoRepository<T, String> {
}
