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
@Document(indexName = "cooperation")
public class Cooperation {
        @Id
        @Field(type = FieldType.Auto)
        private String id;
        @Field(type = FieldType.Auto)
        private Long authorId1;
        @Field(type = FieldType.Auto)
        private Long authorId2;
        @Field(type = FieldType.Auto)
        private Integer times;
}
