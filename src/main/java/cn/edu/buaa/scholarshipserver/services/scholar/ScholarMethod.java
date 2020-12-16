package cn.edu.buaa.scholarshipserver.services.scholar;

import cn.edu.buaa.scholarshipserver.dao.ScholarDao;
import cn.edu.buaa.scholarshipserver.es.Scholar;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.models.auth.In;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScholarMethod {
        @Autowired
        private ScholarDao scholarDao;

        public Scholar getScholarById(Integer id){
                return  scholarDao.findByScholarId(id);
        }

        public Scholar updateScholar(Scholar scholar){
                return scholarDao.save(scholar);
        }

        public List<Scholar> getScholarByName(String Name, Integer OrderType, Integer pageNumber,Integer page){

                BoolQueryBuilder builder = QueryBuilders.boolQuery();
                //builder下有must、should以及mustNot 相当于sql中的and、or以及not

                //设置模糊搜索
                builder.must(QueryBuilders.fuzzyQuery("name", Name));

                //设置要查询博客的标题中含有关键字
                //builder.must(new QueryStringQueryBuilder("man").field("springdemo"));

                //排序
                FieldSortBuilder sort = SortBuilders.fieldSort("hIndex").order(SortOrder.DESC);;
                if(OrderType==2){
                        sort = SortBuilders.fieldSort("papers").order(SortOrder.DESC);
                }
                else if(OrderType==3){
                        sort = SortBuilders.fieldSort("citations").order(SortOrder.DESC);
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
                Page<Scholar> pages = scholarDao.search(query);


                //获取总条数(用于前端分页)
                page = (int) pages.getTotalElements();
                return pages.getContent();
        }

        public List<Scholar> getScholarByOrganization(String Organization){
                return  scholarDao.findByOrganization(Organization);
        }

        public List<Scholar> getScholarByNameAndOrganization(String Name,String Organization,Integer OrderType, Integer pageNumber,Integer page){
                BoolQueryBuilder builder = QueryBuilders.boolQuery();
                //builder下有must、should以及mustNot 相当于sql中的and、or以及not

                //设置模糊搜索
                builder.must(QueryBuilders.fuzzyQuery("name", Name));
                builder.must(QueryBuilders.fuzzyQuery("organization", Organization));
                //设置要查询博客的标题中含有关键字
                //builder.must(new QueryStringQueryBuilder("man").field("springdemo"));

                //排序
                FieldSortBuilder sort = SortBuilders.fieldSort("hIndex").order(SortOrder.DESC);;
                if(OrderType==2){
                        sort = SortBuilders.fieldSort("papers").order(SortOrder.DESC);
                }
                else if(OrderType==3){
                        sort = SortBuilders.fieldSort("citations").order(SortOrder.DESC);
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
                Page<Scholar> pages = scholarDao.search(query);

                //获取查询到的数据内容（返回给前端）
                //获取总条数(用于前端分页)
                page = (int) pages.getTotalElements();
                return pages.getContent();
        }
        /*
        public ResponseEntity<Response> updateScholar(Integer id){
                Scholar scholar =new Scholar();
                scholarDao.save(scholar);

                return  ResponseEntity.ok(new Response(1));
        }
        public ResponseEntity<Response> getScholarByUserName(String username){
                List<Scholar> scholarList = new ArrayList<>();
                scholarList = scholarDao.findByName(username);
                return ResponseEntity.ok(new Response(scholarList));
        }*/
}



