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
@Document(indexName = "project_scholar")
public class Project_Scholar {
        @Id
        @Field(type = FieldType.Auto)
        private String id;
        @Field(type = FieldType.Auto)
        private Long projectId;
        @Field(type = FieldType.Auto)
        private Integer scholarId;
}
