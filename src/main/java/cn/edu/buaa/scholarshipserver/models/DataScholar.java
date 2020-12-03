package cn.edu.buaa.scholarshipserver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataScholar {
    private int AuthorId;
    private String NormalizedName;
    private String DisplayName;
    private int LastKnownAffiliationId;
    private int PaperCount;
    private int PaperFamilyCount;
    private int CitationCount;
}
