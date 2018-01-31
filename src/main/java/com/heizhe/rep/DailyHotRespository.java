package com.heizhe.rep;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.heizhe.base.BaseRepository;
import com.heizhe.entity.DailyHotBasic;

public interface DailyHotRespository extends BaseRepository<DailyHotBasic ,Long>{
	
	DailyHotBasic findById(Long id);
	
	
	/**
	 * 根据Url更新点赞数
	 * @param likeCount
	 * @param answerUrl
	 * @return
	 */
//	@Query("select count(e)>0 from MyEntity e where ...")
//	public boolean existsIfBlaBla(@Param("id") String id);
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update daily_hot_basic t set t.liked_count =?1 where t.answer_url = ?2",nativeQuery = true)
	 int updateLikeCountByUrl( Integer likeCount,String answerUrl);

}
