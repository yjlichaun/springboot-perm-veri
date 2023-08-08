package com.muyi.example.service;

import com.muyi.example.entity.Article;
import com.muyi.example.util.R;

/**
 * 文章管理接口
 *
 * @author 历川
 * @date 2023-08-08 16:49:31
 */

public interface ArticleService {
    
    /**
     * 获取全部文章列表
     * @return R 统一返回类
     */
    R listArticle();
    
    /**
     * 添加文章
     * @param article 文章
     * @return R
     */
    R addArticle(Article article);
    
    /**
     * 修改文章
     * @param article 文章
     * @return R
     */
    R updateArticle(Article article);
    
    /**
     * 根据文章id删除文章
     * @param articleId 文章id
     * @return R
     */
    R deleteArticle(int articleId);
}

