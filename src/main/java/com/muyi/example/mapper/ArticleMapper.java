package com.muyi.example.mapper;

import com.muyi.example.entity.Article;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

/**
 * @author 历川
 * @version 1.0
 * @description 文章mapper
 * @date 2023/8/7 11:48
 */
@Mapper
public interface ArticleMapper {
    
    /**
     * 添加文章
     * @param article 文章
     * @return 影响行数
     */
    @Insert("insert into article (content, create_time, update_time, delete_status) " +
            "values" +
            "(#{content},#{createTime},#{updateTime},#{deleteStatus})")
    int addArticle(Article article);
    /**
     * 查询所有文章
     * @return 文章列表
     */
    @Select("select * from article")
    List<Article> listArticle();
    
    /**
     * 修改文章内容
     * @param article 文章内容
     * @return 是否成功
     */
    boolean updateArticle(@Param("article") Article article, @Param("updateTime") String updateTime);
    
    /**
     * 根据id删除文章
     * @param articleId 文章id
     * @return 影响行数
     */
    @Update("update article set delete_status = '2' where id = #{articleId}")
    int deleteArticleById(@Param("articleId") int articleId);
}
