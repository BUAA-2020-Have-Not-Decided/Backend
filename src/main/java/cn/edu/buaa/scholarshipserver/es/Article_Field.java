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
@Document(indexName = "article_field")
public class Article_Field {
    @Id
    @Field(type = FieldType.Auto)
    private String id;
    @Field(type = FieldType.Auto)
    private Integer fieldsId;
    @Field(type = FieldType.Auto)
    private Integer paperId;
}
