package cn.edu.buaa.scholarshipserver.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FieldsExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table Fields
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table Fields
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table Fields
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    public FieldsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table Fields
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andFieldsidIsNull() {
            addCriterion("FieldsId is null");
            return (Criteria) this;
        }

        public Criteria andFieldsidIsNotNull() {
            addCriterion("FieldsId is not null");
            return (Criteria) this;
        }

        public Criteria andFieldsidEqualTo(Long value) {
            addCriterion("FieldsId =", value, "fieldsid");
            return (Criteria) this;
        }

        public Criteria andFieldsidNotEqualTo(Long value) {
            addCriterion("FieldsId <>", value, "fieldsid");
            return (Criteria) this;
        }

        public Criteria andFieldsidGreaterThan(Long value) {
            addCriterion("FieldsId >", value, "fieldsid");
            return (Criteria) this;
        }

        public Criteria andFieldsidGreaterThanOrEqualTo(Long value) {
            addCriterion("FieldsId >=", value, "fieldsid");
            return (Criteria) this;
        }

        public Criteria andFieldsidLessThan(Long value) {
            addCriterion("FieldsId <", value, "fieldsid");
            return (Criteria) this;
        }

        public Criteria andFieldsidLessThanOrEqualTo(Long value) {
            addCriterion("FieldsId <=", value, "fieldsid");
            return (Criteria) this;
        }

        public Criteria andFieldsidIn(List<Long> values) {
            addCriterion("FieldsId in", values, "fieldsid");
            return (Criteria) this;
        }

        public Criteria andFieldsidNotIn(List<Long> values) {
            addCriterion("FieldsId not in", values, "fieldsid");
            return (Criteria) this;
        }

        public Criteria andFieldsidBetween(Long value1, Long value2) {
            addCriterion("FieldsId between", value1, value2, "fieldsid");
            return (Criteria) this;
        }

        public Criteria andFieldsidNotBetween(Long value1, Long value2) {
            addCriterion("FieldsId not between", value1, value2, "fieldsid");
            return (Criteria) this;
        }

        public Criteria andRanknumberIsNull() {
            addCriterion("RankNumber is null");
            return (Criteria) this;
        }

        public Criteria andRanknumberIsNotNull() {
            addCriterion("RankNumber is not null");
            return (Criteria) this;
        }

        public Criteria andRanknumberEqualTo(String value) {
            addCriterion("RankNumber =", value, "ranknumber");
            return (Criteria) this;
        }

        public Criteria andRanknumberNotEqualTo(String value) {
            addCriterion("RankNumber <>", value, "ranknumber");
            return (Criteria) this;
        }

        public Criteria andRanknumberGreaterThan(String value) {
            addCriterion("RankNumber >", value, "ranknumber");
            return (Criteria) this;
        }

        public Criteria andRanknumberGreaterThanOrEqualTo(String value) {
            addCriterion("RankNumber >=", value, "ranknumber");
            return (Criteria) this;
        }

        public Criteria andRanknumberLessThan(String value) {
            addCriterion("RankNumber <", value, "ranknumber");
            return (Criteria) this;
        }

        public Criteria andRanknumberLessThanOrEqualTo(String value) {
            addCriterion("RankNumber <=", value, "ranknumber");
            return (Criteria) this;
        }

        public Criteria andRanknumberLike(String value) {
            addCriterion("RankNumber like", value, "ranknumber");
            return (Criteria) this;
        }

        public Criteria andRanknumberNotLike(String value) {
            addCriterion("RankNumber not like", value, "ranknumber");
            return (Criteria) this;
        }

        public Criteria andRanknumberIn(List<String> values) {
            addCriterion("RankNumber in", values, "ranknumber");
            return (Criteria) this;
        }

        public Criteria andRanknumberNotIn(List<String> values) {
            addCriterion("RankNumber not in", values, "ranknumber");
            return (Criteria) this;
        }

        public Criteria andRanknumberBetween(String value1, String value2) {
            addCriterion("RankNumber between", value1, value2, "ranknumber");
            return (Criteria) this;
        }

        public Criteria andRanknumberNotBetween(String value1, String value2) {
            addCriterion("RankNumber not between", value1, value2, "ranknumber");
            return (Criteria) this;
        }

        public Criteria andNormalizednameIsNull() {
            addCriterion("NormalizedName is null");
            return (Criteria) this;
        }

        public Criteria andNormalizednameIsNotNull() {
            addCriterion("NormalizedName is not null");
            return (Criteria) this;
        }

        public Criteria andNormalizednameEqualTo(String value) {
            addCriterion("NormalizedName =", value, "normalizedname");
            return (Criteria) this;
        }

        public Criteria andNormalizednameNotEqualTo(String value) {
            addCriterion("NormalizedName <>", value, "normalizedname");
            return (Criteria) this;
        }

        public Criteria andNormalizednameGreaterThan(String value) {
            addCriterion("NormalizedName >", value, "normalizedname");
            return (Criteria) this;
        }

        public Criteria andNormalizednameGreaterThanOrEqualTo(String value) {
            addCriterion("NormalizedName >=", value, "normalizedname");
            return (Criteria) this;
        }

        public Criteria andNormalizednameLessThan(String value) {
            addCriterion("NormalizedName <", value, "normalizedname");
            return (Criteria) this;
        }

        public Criteria andNormalizednameLessThanOrEqualTo(String value) {
            addCriterion("NormalizedName <=", value, "normalizedname");
            return (Criteria) this;
        }

        public Criteria andNormalizednameLike(String value) {
            addCriterion("NormalizedName like", value, "normalizedname");
            return (Criteria) this;
        }

        public Criteria andNormalizednameNotLike(String value) {
            addCriterion("NormalizedName not like", value, "normalizedname");
            return (Criteria) this;
        }

        public Criteria andNormalizednameIn(List<String> values) {
            addCriterion("NormalizedName in", values, "normalizedname");
            return (Criteria) this;
        }

        public Criteria andNormalizednameNotIn(List<String> values) {
            addCriterion("NormalizedName not in", values, "normalizedname");
            return (Criteria) this;
        }

        public Criteria andNormalizednameBetween(String value1, String value2) {
            addCriterion("NormalizedName between", value1, value2, "normalizedname");
            return (Criteria) this;
        }

        public Criteria andNormalizednameNotBetween(String value1, String value2) {
            addCriterion("NormalizedName not between", value1, value2, "normalizedname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameIsNull() {
            addCriterion("DisplayName is null");
            return (Criteria) this;
        }

        public Criteria andDisplaynameIsNotNull() {
            addCriterion("DisplayName is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaynameEqualTo(String value) {
            addCriterion("DisplayName =", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameNotEqualTo(String value) {
            addCriterion("DisplayName <>", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameGreaterThan(String value) {
            addCriterion("DisplayName >", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameGreaterThanOrEqualTo(String value) {
            addCriterion("DisplayName >=", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameLessThan(String value) {
            addCriterion("DisplayName <", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameLessThanOrEqualTo(String value) {
            addCriterion("DisplayName <=", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameLike(String value) {
            addCriterion("DisplayName like", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameNotLike(String value) {
            addCriterion("DisplayName not like", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameIn(List<String> values) {
            addCriterion("DisplayName in", values, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameNotIn(List<String> values) {
            addCriterion("DisplayName not in", values, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameBetween(String value1, String value2) {
            addCriterion("DisplayName between", value1, value2, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameNotBetween(String value1, String value2) {
            addCriterion("DisplayName not between", value1, value2, "displayname");
            return (Criteria) this;
        }

        public Criteria andMaintypeIsNull() {
            addCriterion("MainType is null");
            return (Criteria) this;
        }

        public Criteria andMaintypeIsNotNull() {
            addCriterion("MainType is not null");
            return (Criteria) this;
        }

        public Criteria andMaintypeEqualTo(String value) {
            addCriterion("MainType =", value, "maintype");
            return (Criteria) this;
        }

        public Criteria andMaintypeNotEqualTo(String value) {
            addCriterion("MainType <>", value, "maintype");
            return (Criteria) this;
        }

        public Criteria andMaintypeGreaterThan(String value) {
            addCriterion("MainType >", value, "maintype");
            return (Criteria) this;
        }

        public Criteria andMaintypeGreaterThanOrEqualTo(String value) {
            addCriterion("MainType >=", value, "maintype");
            return (Criteria) this;
        }

        public Criteria andMaintypeLessThan(String value) {
            addCriterion("MainType <", value, "maintype");
            return (Criteria) this;
        }

        public Criteria andMaintypeLessThanOrEqualTo(String value) {
            addCriterion("MainType <=", value, "maintype");
            return (Criteria) this;
        }

        public Criteria andMaintypeLike(String value) {
            addCriterion("MainType like", value, "maintype");
            return (Criteria) this;
        }

        public Criteria andMaintypeNotLike(String value) {
            addCriterion("MainType not like", value, "maintype");
            return (Criteria) this;
        }

        public Criteria andMaintypeIn(List<String> values) {
            addCriterion("MainType in", values, "maintype");
            return (Criteria) this;
        }

        public Criteria andMaintypeNotIn(List<String> values) {
            addCriterion("MainType not in", values, "maintype");
            return (Criteria) this;
        }

        public Criteria andMaintypeBetween(String value1, String value2) {
            addCriterion("MainType between", value1, value2, "maintype");
            return (Criteria) this;
        }

        public Criteria andMaintypeNotBetween(String value1, String value2) {
            addCriterion("MainType not between", value1, value2, "maintype");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("Level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("Level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("Level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("Level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("Level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("Level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("Level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("Level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("Level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("Level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("Level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("Level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andPapercountIsNull() {
            addCriterion("PaperCount is null");
            return (Criteria) this;
        }

        public Criteria andPapercountIsNotNull() {
            addCriterion("PaperCount is not null");
            return (Criteria) this;
        }

        public Criteria andPapercountEqualTo(Long value) {
            addCriterion("PaperCount =", value, "papercount");
            return (Criteria) this;
        }

        public Criteria andPapercountNotEqualTo(Long value) {
            addCriterion("PaperCount <>", value, "papercount");
            return (Criteria) this;
        }

        public Criteria andPapercountGreaterThan(Long value) {
            addCriterion("PaperCount >", value, "papercount");
            return (Criteria) this;
        }

        public Criteria andPapercountGreaterThanOrEqualTo(Long value) {
            addCriterion("PaperCount >=", value, "papercount");
            return (Criteria) this;
        }

        public Criteria andPapercountLessThan(Long value) {
            addCriterion("PaperCount <", value, "papercount");
            return (Criteria) this;
        }

        public Criteria andPapercountLessThanOrEqualTo(Long value) {
            addCriterion("PaperCount <=", value, "papercount");
            return (Criteria) this;
        }

        public Criteria andPapercountIn(List<Long> values) {
            addCriterion("PaperCount in", values, "papercount");
            return (Criteria) this;
        }

        public Criteria andPapercountNotIn(List<Long> values) {
            addCriterion("PaperCount not in", values, "papercount");
            return (Criteria) this;
        }

        public Criteria andPapercountBetween(Long value1, Long value2) {
            addCriterion("PaperCount between", value1, value2, "papercount");
            return (Criteria) this;
        }

        public Criteria andPapercountNotBetween(Long value1, Long value2) {
            addCriterion("PaperCount not between", value1, value2, "papercount");
            return (Criteria) this;
        }

        public Criteria andPaperfamilycountIsNull() {
            addCriterion("PaperFamilyCount is null");
            return (Criteria) this;
        }

        public Criteria andPaperfamilycountIsNotNull() {
            addCriterion("PaperFamilyCount is not null");
            return (Criteria) this;
        }

        public Criteria andPaperfamilycountEqualTo(Long value) {
            addCriterion("PaperFamilyCount =", value, "paperfamilycount");
            return (Criteria) this;
        }

        public Criteria andPaperfamilycountNotEqualTo(Long value) {
            addCriterion("PaperFamilyCount <>", value, "paperfamilycount");
            return (Criteria) this;
        }

        public Criteria andPaperfamilycountGreaterThan(Long value) {
            addCriterion("PaperFamilyCount >", value, "paperfamilycount");
            return (Criteria) this;
        }

        public Criteria andPaperfamilycountGreaterThanOrEqualTo(Long value) {
            addCriterion("PaperFamilyCount >=", value, "paperfamilycount");
            return (Criteria) this;
        }

        public Criteria andPaperfamilycountLessThan(Long value) {
            addCriterion("PaperFamilyCount <", value, "paperfamilycount");
            return (Criteria) this;
        }

        public Criteria andPaperfamilycountLessThanOrEqualTo(Long value) {
            addCriterion("PaperFamilyCount <=", value, "paperfamilycount");
            return (Criteria) this;
        }

        public Criteria andPaperfamilycountIn(List<Long> values) {
            addCriterion("PaperFamilyCount in", values, "paperfamilycount");
            return (Criteria) this;
        }

        public Criteria andPaperfamilycountNotIn(List<Long> values) {
            addCriterion("PaperFamilyCount not in", values, "paperfamilycount");
            return (Criteria) this;
        }

        public Criteria andPaperfamilycountBetween(Long value1, Long value2) {
            addCriterion("PaperFamilyCount between", value1, value2, "paperfamilycount");
            return (Criteria) this;
        }

        public Criteria andPaperfamilycountNotBetween(Long value1, Long value2) {
            addCriterion("PaperFamilyCount not between", value1, value2, "paperfamilycount");
            return (Criteria) this;
        }

        public Criteria andCitationcountIsNull() {
            addCriterion("CitationCount is null");
            return (Criteria) this;
        }

        public Criteria andCitationcountIsNotNull() {
            addCriterion("CitationCount is not null");
            return (Criteria) this;
        }

        public Criteria andCitationcountEqualTo(Long value) {
            addCriterion("CitationCount =", value, "citationcount");
            return (Criteria) this;
        }

        public Criteria andCitationcountNotEqualTo(Long value) {
            addCriterion("CitationCount <>", value, "citationcount");
            return (Criteria) this;
        }

        public Criteria andCitationcountGreaterThan(Long value) {
            addCriterion("CitationCount >", value, "citationcount");
            return (Criteria) this;
        }

        public Criteria andCitationcountGreaterThanOrEqualTo(Long value) {
            addCriterion("CitationCount >=", value, "citationcount");
            return (Criteria) this;
        }

        public Criteria andCitationcountLessThan(Long value) {
            addCriterion("CitationCount <", value, "citationcount");
            return (Criteria) this;
        }

        public Criteria andCitationcountLessThanOrEqualTo(Long value) {
            addCriterion("CitationCount <=", value, "citationcount");
            return (Criteria) this;
        }

        public Criteria andCitationcountIn(List<Long> values) {
            addCriterion("CitationCount in", values, "citationcount");
            return (Criteria) this;
        }

        public Criteria andCitationcountNotIn(List<Long> values) {
            addCriterion("CitationCount not in", values, "citationcount");
            return (Criteria) this;
        }

        public Criteria andCitationcountBetween(Long value1, Long value2) {
            addCriterion("CitationCount between", value1, value2, "citationcount");
            return (Criteria) this;
        }

        public Criteria andCitationcountNotBetween(Long value1, Long value2) {
            addCriterion("CitationCount not between", value1, value2, "citationcount");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("CreateDate is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("CreateDate is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterion("CreateDate =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterion("CreateDate <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterion("CreateDate >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("CreateDate >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterion("CreateDate <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterion("CreateDate <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterion("CreateDate in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterion("CreateDate not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterion("CreateDate between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterion("CreateDate not between", value1, value2, "createdate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table Fields
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table Fields
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}