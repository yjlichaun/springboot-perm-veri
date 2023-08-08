package com.muyi.example.service.impl;

import com.muyi.example.entity.Article;
import com.muyi.example.mapper.ArticleMapper;
import com.muyi.example.service.ArticleService;
import com.muyi.example.util.CommonUtil;
import com.muyi.example.util.DateUtil;
import com.muyi.example.util.R;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.builder.qual.ReturnsReceiver;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author 历川
 * @version 1.0
 * @description 文章接口实现类
 * @date 2023/8/8 16:49
 */
@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {
    
    @Autowired
    ArticleMapper articleMapper;
    @Override
    public R listArticle() {
        List<Article> list = articleMapper.listArticle();
        if (list == null || list.size() == 0) {
            return R.failed("未查询到文章列表");
        }
        Map<Integer, List<Article>> listAsMap = CommonUtil.getListAsMap(0, list);
        return R.ok(listAsMap,"查询成功");
    }
    
    @Override
    @Transactional
    public R addArticle(Article article) {
        if (article.getContent() == null || ("").equals(article.getContent())){
            return R.failed("文章内容不能为空");
        }
        article.setCreateTime(DateUtil.getDateNow());
        article.setUpdateTime(DateUtil.getDateNow());
        article.setDeleteStatus("1");
        int addCnt = articleMapper.addArticle(article);
        if (addCnt == 0) {
            return R.failed("添加失败！！！");
        }
        return R.ok("添加文章成功！！！");
    }
    
    
    @Override
    @Transactional
    public R updateArticle(Article article) {
        boolean isSuccess = articleMapper.updateArticle(article,DateUtil.getDateNow());
        if (!isSuccess) {
            return R.failed("修改失败！！！");
        }
        return R.ok("修改成功！！！");
    }
    
    @Override
    @Transactional
    public R deleteArticle(int articleId) {
        int deleteCnt = articleMapper.deleteArticleById(articleId);
        if (deleteCnt == 0) {
            return R.failed("没有该文章，删除失败");
        }
        return R.ok("删除成功");
    }
    
}
