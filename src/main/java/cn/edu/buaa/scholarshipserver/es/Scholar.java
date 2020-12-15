package cn.edu.buaa.scholarshipserver.es;

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
@Document(indexName = "scholar")
public class Scholar implements Serializable {
        @Id
        @Field(type = FieldType.Auto)
        private String id;
        @Field(type = FieldType.Auto)
        private Integer scholarId;
        @Field(type = FieldType.Auto)
        private Integer institutionId;
        @Field(type = FieldType.Auto)
        private String englishName;
        @Field(type = FieldType.Auto)
        private String name;
        @Field(type = FieldType.Auto)
        private String title;
        @Field(type = FieldType.Auto)
        private String organization;
        @Field(type = FieldType.Auto)
        private String email;
        @Field(type = FieldType.Auto)
        private String phone;
        @Field(type = FieldType.Auto)
        private Integer fans;
        @Field(type = FieldType.Auto)
        private String personalPage;
        @Field(type = FieldType.Auto)
        private String introduction;
        @Field(type = FieldType.Auto)
        private Integer papers;
        @Field(type = FieldType.Auto)
        private Integer citations;
        @Field(type = FieldType.Auto)
        private Integer hIndex;
        @Field(type = FieldType.Auto)
        private Integer gIndex;
        @Field(type = FieldType.Auto)
        private Integer sociability;
        @Field(type = FieldType.Auto)
        private Integer diversity;
        @Field(type = FieldType.Auto)
        private Integer activity;
        @Field(type = FieldType.Auto)
        private String avatarUrl;
        @Field(type = FieldType.Auto)
        private String lastKnownTime;

        private static final long serialVersionUID = 1L;
}
