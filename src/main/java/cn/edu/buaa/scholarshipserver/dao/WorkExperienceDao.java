package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.es.WorkExperience;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface WorkExperienceDao extends ElasticsearchRepository<WorkExperience,Long> {
        public List<WorkExperience> findByScholarId(Integer scholarId);
        public WorkExperience findByScholarIdAndAndIntroductionAndOrganizationAndYearStartAndYearEnd(Integer scholarId,String introduction,String organization,Integer yearStart,Integer yearEnd);
}
