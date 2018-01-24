package cn.hdbase.common.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * 
 * <Repository通用接口>
 *
 * @author 陆小凤
 * @version [版本号1.0, 2015年9月9日]
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID>,
    JpaSpecificationExecutor<T>
{
    
}
