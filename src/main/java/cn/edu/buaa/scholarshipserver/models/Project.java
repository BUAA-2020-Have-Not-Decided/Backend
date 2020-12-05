package cn.edu.buaa.scholarshipserver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "buaase2020")
public class Project implements Serializable {
    @Id
    @Field(type= FieldType.Auto)
    private Long projectId;
    @Field(type= FieldType.Auto)
    private String organization;
    @Field(type=FieldType.Auto)
    private String fundProjectCode;
    @Field(type=FieldType.Auto)
    private String source;
    @Field(type=FieldType.Auto)
    private String doi;
    @Field(type=FieldType.Auto)
    private String fieldName;
    @Field(type=FieldType.Auto)
    private String doiUrl;
    @Field(type=FieldType.Auto)
    private String zhAbstract;
    @Field(type=FieldType.Auto)
    private String fundProject;
    @Field(type=FieldType.Auto)
    private String authors;
    @Field(type=FieldType.Auto)
    private String fieldCode;
    @Field(type=FieldType.Auto)
    private String organizationID;
    @Field(type=FieldType.Auto)
    private String supportTypeName;
    @Field(type=FieldType.Auto)
    private String chineseTitle;
    @Field(type=FieldType.Auto)
    private String publishDate;
    @Field(type=FieldType.Auto)
    private String fundProjectNo;
    @Field(type=FieldType.Auto)
    private String achievementID;
    @Field(type=FieldType.Auto)
    private String journal;
    @Field(type=FieldType.Auto)
    private String productType;
    @Field(type=FieldType.Auto)
    private String zhKeyword;
    private static final long serialVersionUID = 1L;


}