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
//@Document(indexName = )
public class Paper_DataScholar {
        @Id
        @Field(type = FieldType.Auto)
        private Integer id;
        @Field(type = FieldType.Auto)
        private Integer PaperId;
        @Field(type = FieldType.Auto)
        private Integer authorId;
}
