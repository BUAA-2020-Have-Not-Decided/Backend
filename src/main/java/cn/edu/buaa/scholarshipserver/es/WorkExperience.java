package cn.edu.buaa.scholarshipserver.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "workExperience")
public class WorkExperience  {
        @Id
        @Field(type = FieldType.Auto)
        private Integer id;
        @Field(type = FieldType.Auto)
        private Long scholarId;
        @Field(type = FieldType.Auto)
        private String  introduction;
        @Field(type = FieldType.Auto)
        private String  organization;
        @Field(type = FieldType.Auto)
        private Integer yearStart;
        @Field(type = FieldType.Auto)
        private Integer yearEnd;
}
