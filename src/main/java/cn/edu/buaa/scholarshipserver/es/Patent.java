package cn.edu.buaa.scholarshipserver.es;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "patent")
public class Patent {
    @Id
    @Field(type = FieldType.Auto)
    private Integer patentId;
    @Field(type = FieldType.Auto)
    private String Abstract;
    @Field(type = FieldType.Auto)
    private Date applicationDate;
    @Field(type = FieldType.Auto)
    private String agency;
    @Field(type = FieldType.Auto)
    private String applicationNumber;
    @Field(type = FieldType.Auto)
    private String agent;
    @Field(type = FieldType.Auto)
    private String content;
    @Field(type = FieldType.Auto)
    private String province;
    @Field(type = FieldType.Auto)
    private String location;
    @Field(type = FieldType.Auto)
    private String classificationNumber;
    @Field(type = FieldType.Auto)
    private String mainClassificationNumber;
    @Field(type = FieldType.Auto)
    private String inventor;
    @Field(type = FieldType.Auto)
    private Date publishDate;
    @Field(type = FieldType.Auto)
    private String applicant;
    @Field(type = FieldType.Auto)
    private String currentObligee;
    @Field(type = FieldType.Auto)
    private String publishNumber;
    @Field(type = FieldType.Auto)
    private String title;
    @Field(type = FieldType.Auto)
    private String state;
}
