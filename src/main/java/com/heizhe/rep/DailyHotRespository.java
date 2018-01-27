package com.heizhe.rep;


import com.heizhe.base.BaseRepository;
import com.heizhe.entity.DailyHotBasic;

public interface DailyHotRespository extends BaseRepository<DailyHotBasic ,Long>{
	
	DailyHotBasic findById(Long id);
	
//	@Query("select count(e)>0 from MyEntity e where ...")
//	public boolean existsIfBlaBla(@Param("id") String id);


}
