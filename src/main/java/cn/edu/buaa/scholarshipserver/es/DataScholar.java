package cn.edu.buaa.scholarshipserver.es;

import io.swagger.models.auth.In;
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
@Document(indexName = "data_scholar")
public class DataScholar {
        @Id
        @Field(type = FieldType.Auto)
        private String id;
        @Field(type = FieldType.Auto)
        private Integer scholarId;
        @Field(type = FieldType.Auto)
        private Long authorId;
        @Field(type = FieldType.Auto)
        private String normalizedName;
        @Field(type = FieldType.Auto)
        private String displayName;
        @Field(type = FieldType.Auto)
        private Long lastKnownAffiliationId;
        @Field(type = FieldType.Auto)
        private int paperCount;
        @Field(type = FieldType.Auto)
        private int paperFamilyCount;
        @Field(type = FieldType.Auto)
        private int citationCount;
        @Field(type = FieldType.Auto)
        private int hIndex;
}
