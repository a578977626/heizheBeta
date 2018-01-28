package com.heizhe.base;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * SimpleJpaRepository的代替品
 * 
 * @author chenxb2
 *
 * @param <T>
 * @param <ID>
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements BaseRepository<T, ID> {

	private final EntityManager entityManager;

	// 父类没有不带参数的构造方法，这里手动构造父类
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	// 通过EntityManager来完成查询
	/**
	 * 这个设计是一个比较通用的设计。是一个同时享受JPa的查询方法，还支持自己拓展的一个设计。
	 * 用BaseImp来实现。
	 * 以后新增的每个实体类都享受此方法。（被我改造加了Class<?> T效果更好了点）
	 * 详见：
	 * @url https://www.jianshu.com/p/73f48095a7bf# 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> listBySQL(String sql ,Class<?> T) {
		return entityManager.createNativeQuery(sql, T).getResultList();
	}
}