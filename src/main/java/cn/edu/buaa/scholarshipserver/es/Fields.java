package cn.edu.buaa.scholarshipserver.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "field")
public class Fields {
    @Id
    @Field(type = FieldType.Auto)
    private Integer Id;
    @Field(type = FieldType.Auto)
    private Integer FieldsId;
    @Field(type = FieldType.Auto)
    private String RankNumber;
    @Field(type = FieldType.Auto)
    private String NormalizedName;
    @Field(type = FieldType.Auto)
    private String DisplayName;
    @Field(type = FieldType.Auto)
    private String MainType;
    @Field(type = FieldType.Auto)
    private Integer Level;
    @Field(type = FieldType.Auto)
    private Integer PaperCount;
    @Field(type = FieldType.Auto)
    private Integer PaperFamilyCount;
    @Field(type = FieldType.Auto)
    private Integer CitationCount;
    @Field(type = FieldType.Auto)
    private DateTime CreateDate;
}
