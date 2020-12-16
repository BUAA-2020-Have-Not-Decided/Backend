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
@Document(indexName = "paper")
public class Paper implements Serializable {
        @Id
        @Field(type = FieldType.Auto)
        private Long paperId;
        @Field(type = FieldType.Auto)
        private String doi;
        @Field(type = FieldType.Auto)
        private String doctype;
        @Field(type = FieldType.Auto)
        private String paperTitle;
        @Field(type = FieldType.Auto)
        private String abtract;
        @Field(type = FieldType.Auto)
        private Long citationCount;
        @Field(type = FieldType.Auto)
        private String date;
        @Field(type = FieldType.Auto)
        private String journal;
        @Field(type = FieldType.Auto)
        private String conference;
        @Field(type = FieldType.Auto)
        private String volume;
        @Field(type = FieldType.Auto)
        private String issue;
        @Field(type = FieldType.Auto)
        private String firstPage;
        @Field(type = FieldType.Auto)
        private String lastPage;
        @Field(type = FieldType.Auto)
        private String sourceUrl;

        private static final long serialVersionUID = 1L;
}
