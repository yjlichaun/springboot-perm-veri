package com.muyi.example.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.muyi.example.entity.Article;
import com.muyi.example.service.ArticleService;
import com.muyi.example.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 历川
 * @version 1.0
 * @description 文章相关controller
 * @date 2023/8/8 16:46
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    
    @Autowired
    ArticleService articleService;
    
    
    /**
     * 获取全部文章
     * @return R
     */
    @SaCheckPermission("article:list")
    @GetMapping("/listArticle")
    public R listArticle() {
        return articleService.listArticle();
    }
    
    
    /**
     * 添加文章
     * @param article 文章
     * @return R
     */
    @SaCheckPermission("article:add")
    @PostMapping("/addArticle")
    public R addArticle(@RequestBody Article article) {
        return articleService.addArticle(article);
    }
    
    
    /**
     * 修改文章
     * @param article 文章
     * @return R
     */
    @SaCheckPermission("article:update")
    @PutMapping("/updateArticle")
    public R updateArticle(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }
    
    /**
     * 删除文章
     * @param articleId 文章id
     */
    @DeleteMapping("/deleteArticle/{articleId}")
    public R deleteArticle(@PathVariable int  articleId) {
        return articleService.deleteArticle(articleId);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
