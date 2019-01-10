package com.elasticsearch;

import com.alibaba.druid.util.StringUtils;
import com.elasticsearch.domain.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

@Service
public class ElasticsearchServiceImpl {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public Page<Products> demo(Pageable pageAble, String keyword) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        if(StringUtils.isEmpty(keyword)){
            nativeSearchQueryBuilder.withQuery(queryStringQuery(keyword));
        }
        SearchQuery searchQuery = nativeSearchQueryBuilder.withPageable(pageAble).build();
        Page<Products> results = elasticsearchTemplate.queryForPage(searchQuery, Products.class);
        return results;
    }
}
