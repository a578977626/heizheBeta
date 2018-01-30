package com.heizhe.rep;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.heizhe.base.BaseRepository;
import com.heizhe.entity.MateriaArticle;

public interface MaterialArticleRespository extends BaseRepository<MateriaArticle,Long>{
	MateriaArticle findById(Long id);
	
	//nativeQuery = true 表示用原生SQL查询,false 为用实体对象查询
	/**
	 * 更新状态与mediaId
	 * @param status
	 * @param mediaId
	 * @param id
	 * @return
	 */
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update materia_article_tb t set t.upload_status =?1 , t.media_id =?2 where t.basic_id = ?3",nativeQuery = true)
	 int updateStatusAndMediaByUrl( String status,String mediaId, Long id);
}
