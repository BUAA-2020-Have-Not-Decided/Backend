package cn.edu.buaa.scholarshipserver.controllers.scholarship;

import io.swagger.annotations.ApiModel;

@ApiModel
public class ChangeStarStatus {
    Long paperId;
    Integer type;

    public ChangeStarStatus(Long paperId, Integer type) {
        this.paperId = paperId;
        this.type = type;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
