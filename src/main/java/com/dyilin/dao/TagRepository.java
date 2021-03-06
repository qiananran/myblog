package com.dyilin.dao;

import com.dyilin.pojo.Tag;
import com.dyilin.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: myblog
 * @Author: Dyilin
 * @Description:
 * @create: 2020-12-08 21:33
 */
public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);

    @Query(value = "SELECT t.id,t.name,count(tt.blogs_id) as blog_number FROM t_tag t LEFT JOIN t_blog_tags tt ON t.id = tt.tags_id LEFT JOIN t_blog b ON tt.blogs_id = b.id WHERE b.published = true GROUP BY t.id ORDER BY blog_number DESC", nativeQuery = true)
    List<Object[]> findListTop();

    @Query(value = "select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
