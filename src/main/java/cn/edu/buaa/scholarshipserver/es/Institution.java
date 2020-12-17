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
@Document(indexName = "institution")
public class Institution {
        @Id
        @Field(type = FieldType.Auto)
        private Long institutionId;
        @Field(type = FieldType.Auto)
        private String institutionName;
        @Field(type = FieldType.Auto)
        private Double natureIndex;
}
