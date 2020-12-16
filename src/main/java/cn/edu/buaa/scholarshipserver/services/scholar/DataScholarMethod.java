package cn.edu.buaa.scholarshipserver.services.scholar;

import cn.edu.buaa.scholarshipserver.dao.DataScholarDao;
import cn.edu.buaa.scholarshipserver.es.DataScholar;
import cn.edu.buaa.scholarshipserver.es.Scholar;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DataScholarMethod {
    @Autowired
    private DataScholarDao dataScholarDao;
    public DataScholar getDataScholarByAuthorId(Long authorId){
        return dataScholarDao.findByAuthorId(authorId);
    }
    public List<DataScholar> getDataScholarByNormalizedName(String normalizedName){
        return dataScholarDao.findByNormalizedName(normalizedName);
    }
    public List<DataScholar> getDataScholarByScholarId(Integer scholarId){
        return dataScholarDao.findByScholarId(scholarId);
    }
    public void updateDataScholar(DataScholar dataScholar){
        dataScholarDao.save(dataScholar);
    }
    public List<DataScholar> Search(String Name, Integer OrderType, Integer pageNumber, AtomicInteger page){
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //builder下有must、should以及mustNot 相当于sql中的and、or以及not

        //设置模糊搜索
        builder.must(QueryBuilders.fuzzyQuery("normalizedName", Name));
        //builder.must(QueryBuilders.fuzzyQuery("organization", Institution));
        //设置要查询博客的标题中含有关键字
        //builder.must(new QueryStringQueryBuilder("man").field("springdemo"));

        //排序
        FieldSortBuilder sort = SortBuilders.fieldSort("HIndex").order(SortOrder.DESC);;
        if(OrderType==2){
            sort = SortBuilders.fieldSort("paperCount").order(SortOrder.DESC);
        }
        else if(OrderType==3){
            sort = SortBuilders.fieldSort("citationCount").order(SortOrder.DESC);
        }
        //设置分页(从第一页开始，一页显示10条)
        //注意开始是从0开始，有点类似sql中的方法limit 的查询
        Pageable pageable = PageRequest.of(pageNumber,6);

        //2.构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        nativeSearchQueryBuilder.withQuery(builder);
        //将分页设置到构建中
        nativeSearchQueryBuilder.withPageable(pageable);
        //将排序设置到构建中
        nativeSearchQueryBuilder.withSort(sort);
        //生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        //3.执行方法1
        Page<DataScholar> pages = dataScholarDao.search(query);
        //获取查询到的数据内容（返回给前端）
        //获取总条数(用于前端分页)
        page.set( (int) pages.getTotalElements());
        return pages.getContent();
    }
}
