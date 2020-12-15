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
@Document(indexName = "subscribe")
public class Subscribe {
    @Id
    @Field(type = FieldType.Auto)
    private Integer subscribeId;
    @Field(type = FieldType.Auto)
    private Integer fanId;
    @Field(type = FieldType.Auto)
    private Long scholarId;
    @Field(type = FieldType.Auto)
    private String SubscribeDatetime;
}
