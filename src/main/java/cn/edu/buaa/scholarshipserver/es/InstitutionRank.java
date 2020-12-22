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
@Document(indexName = "institution_rank")
public class InstitutionRank {

    @Id
    @Field(type = FieldType.Auto)
    private Long id;
    @Field(type = FieldType.Auto)
    private Long institutionId;
    @Field(type = FieldType.Auto)
    private Integer paperNum;
    @Field(type = FieldType.Auto)
    private Integer firstAuthor;
    @Field(type = FieldType.Auto)
    private Long fieldId;

    private String institutionName;
}
