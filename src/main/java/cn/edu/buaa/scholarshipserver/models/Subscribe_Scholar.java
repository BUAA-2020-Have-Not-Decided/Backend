package cn.edu.buaa.scholarshipserver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subscribe_Scholar {
    private int FanID;

    private int ScholarId;

    private DateTime SubscribeDatetime;
}
