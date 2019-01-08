package cn.sy.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("id_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("id_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("id_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("id_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("id_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardImgPathIsNull() {
            addCriterion("id_card_img_path is null");
            return (Criteria) this;
        }

        public Criteria andIdCardImgPathIsNotNull() {
            addCriterion("id_card_img_path is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardImgPathEqualTo(String value) {
            addCriterion("id_card_img_path =", value, "idCardImgPath");
            return (Criteria) this;
        }

        public Criteria andIdCardImgPathNotEqualTo(String value) {
            addCriterion("id_card_img_path <>", value, "idCardImgPath");
            return (Criteria) this;
        }

        public Criteria andIdCardImgPathGreaterThan(String value) {
            addCriterion("id_card_img_path >", value, "idCardImgPath");
            return (Criteria) this;
        }

        public Criteria andIdCardImgPathGreaterThanOrEqualTo(String value) {
            addCriterion("id_card_img_path >=", value, "idCardImgPath");
            return (Criteria) this;
        }

        public Criteria andIdCardImgPathLessThan(String value) {
            addCriterion("id_card_img_path <", value, "idCardImgPath");
            return (Criteria) this;
        }

        public Criteria andIdCardImgPathLessThanOrEqualTo(String value) {
            addCriterion("id_card_img_path <=", value, "idCardImgPath");
            return (Criteria) this;
        }

        public Criteria andIdCardImgPathLike(String value) {
            addCriterion("id_card_img_path like", value, "idCardImgPath");
            return (Criteria) this;
        }

        public Criteria andIdCardImgPathNotLike(String value) {
            addCriterion("id_card_img_path not like", value, "idCardImgPath");
            return (Criteria) this;
        }

        public Criteria andIdCardImgPathIn(List<String> values) {
            addCriterion("id_card_img_path in", values, "idCardImgPath");
            return (Criteria) this;
        }

        public Criteria andIdCardImgPathNotIn(List<String> values) {
            addCriterion("id_card_img_path not in", values, "idCardImgPath");
            return (Criteria) this;
        }

        public Criteria andIdCardImgPathBetween(String value1, String value2) {
            addCriterion("id_card_img_path between", value1, value2, "idCardImgPath");
            return (Criteria) this;
        }

        public Criteria andIdCardImgPathNotBetween(String value1, String value2) {
            addCriterion("id_card_img_path not between", value1, value2, "idCardImgPath");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andOpVerifyStatusIsNull() {
            addCriterion("op_verify_status is null");
            return (Criteria) this;
        }

        public Criteria andOpVerifyStatusIsNotNull() {
            addCriterion("op_verify_status is not null");
            return (Criteria) this;
        }

        public Criteria andOpVerifyStatusEqualTo(Integer value) {
            addCriterion("op_verify_status =", value, "opVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andOpVerifyStatusNotEqualTo(Integer value) {
            addCriterion("op_verify_status <>", value, "opVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andOpVerifyStatusGreaterThan(Integer value) {
            addCriterion("op_verify_status >", value, "opVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andOpVerifyStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("op_verify_status >=", value, "opVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andOpVerifyStatusLessThan(Integer value) {
            addCriterion("op_verify_status <", value, "opVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andOpVerifyStatusLessThanOrEqualTo(Integer value) {
            addCriterion("op_verify_status <=", value, "opVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andOpVerifyStatusIn(List<Integer> values) {
            addCriterion("op_verify_status in", values, "opVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andOpVerifyStatusNotIn(List<Integer> values) {
            addCriterion("op_verify_status not in", values, "opVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andOpVerifyStatusBetween(Integer value1, Integer value2) {
            addCriterion("op_verify_status between", value1, value2, "opVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andOpVerifyStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("op_verify_status not between", value1, value2, "opVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andTmallUserNameIsNull() {
            addCriterion("tmall_user_name is null");
            return (Criteria) this;
        }

        public Criteria andTmallUserNameIsNotNull() {
            addCriterion("tmall_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andTmallUserNameEqualTo(String value) {
            addCriterion("tmall_user_name =", value, "tmallUserName");
            return (Criteria) this;
        }

        public Criteria andTmallUserNameNotEqualTo(String value) {
            addCriterion("tmall_user_name <>", value, "tmallUserName");
            return (Criteria) this;
        }

        public Criteria andTmallUserNameGreaterThan(String value) {
            addCriterion("tmall_user_name >", value, "tmallUserName");
            return (Criteria) this;
        }

        public Criteria andTmallUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("tmall_user_name >=", value, "tmallUserName");
            return (Criteria) this;
        }

        public Criteria andTmallUserNameLessThan(String value) {
            addCriterion("tmall_user_name <", value, "tmallUserName");
            return (Criteria) this;
        }

        public Criteria andTmallUserNameLessThanOrEqualTo(String value) {
            addCriterion("tmall_user_name <=", value, "tmallUserName");
            return (Criteria) this;
        }

        public Criteria andTmallUserNameLike(String value) {
            addCriterion("tmall_user_name like", value, "tmallUserName");
            return (Criteria) this;
        }

        public Criteria andTmallUserNameNotLike(String value) {
            addCriterion("tmall_user_name not like", value, "tmallUserName");
            return (Criteria) this;
        }

        public Criteria andTmallUserNameIn(List<String> values) {
            addCriterion("tmall_user_name in", values, "tmallUserName");
            return (Criteria) this;
        }

        public Criteria andTmallUserNameNotIn(List<String> values) {
            addCriterion("tmall_user_name not in", values, "tmallUserName");
            return (Criteria) this;
        }

        public Criteria andTmallUserNameBetween(String value1, String value2) {
            addCriterion("tmall_user_name between", value1, value2, "tmallUserName");
            return (Criteria) this;
        }

        public Criteria andTmallUserNameNotBetween(String value1, String value2) {
            addCriterion("tmall_user_name not between", value1, value2, "tmallUserName");
            return (Criteria) this;
        }

        public Criteria andTmallVerifyStatusIsNull() {
            addCriterion("tmall_verify_status is null");
            return (Criteria) this;
        }

        public Criteria andTmallVerifyStatusIsNotNull() {
            addCriterion("tmall_verify_status is not null");
            return (Criteria) this;
        }

        public Criteria andTmallVerifyStatusEqualTo(Integer value) {
            addCriterion("tmall_verify_status =", value, "tmallVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andTmallVerifyStatusNotEqualTo(Integer value) {
            addCriterion("tmall_verify_status <>", value, "tmallVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andTmallVerifyStatusGreaterThan(Integer value) {
            addCriterion("tmall_verify_status >", value, "tmallVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andTmallVerifyStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("tmall_verify_status >=", value, "tmallVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andTmallVerifyStatusLessThan(Integer value) {
            addCriterion("tmall_verify_status <", value, "tmallVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andTmallVerifyStatusLessThanOrEqualTo(Integer value) {
            addCriterion("tmall_verify_status <=", value, "tmallVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andTmallVerifyStatusIn(List<Integer> values) {
            addCriterion("tmall_verify_status in", values, "tmallVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andTmallVerifyStatusNotIn(List<Integer> values) {
            addCriterion("tmall_verify_status not in", values, "tmallVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andTmallVerifyStatusBetween(Integer value1, Integer value2) {
            addCriterion("tmall_verify_status between", value1, value2, "tmallVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andTmallVerifyStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("tmall_verify_status not between", value1, value2, "tmallVerifyStatus");
            return (Criteria) this;
        }

        public Criteria andFinIdIsNull() {
            addCriterion("fin_id is null");
            return (Criteria) this;
        }

        public Criteria andFinIdIsNotNull() {
            addCriterion("fin_id is not null");
            return (Criteria) this;
        }

        public Criteria andFinIdEqualTo(Integer value) {
            addCriterion("fin_id =", value, "finId");
            return (Criteria) this;
        }

        public Criteria andFinIdNotEqualTo(Integer value) {
            addCriterion("fin_id <>", value, "finId");
            return (Criteria) this;
        }

        public Criteria andFinIdGreaterThan(Integer value) {
            addCriterion("fin_id >", value, "finId");
            return (Criteria) this;
        }

        public Criteria andFinIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fin_id >=", value, "finId");
            return (Criteria) this;
        }

        public Criteria andFinIdLessThan(Integer value) {
            addCriterion("fin_id <", value, "finId");
            return (Criteria) this;
        }

        public Criteria andFinIdLessThanOrEqualTo(Integer value) {
            addCriterion("fin_id <=", value, "finId");
            return (Criteria) this;
        }

        public Criteria andFinIdIn(List<Integer> values) {
            addCriterion("fin_id in", values, "finId");
            return (Criteria) this;
        }

        public Criteria andFinIdNotIn(List<Integer> values) {
            addCriterion("fin_id not in", values, "finId");
            return (Criteria) this;
        }

        public Criteria andFinIdBetween(Integer value1, Integer value2) {
            addCriterion("fin_id between", value1, value2, "finId");
            return (Criteria) this;
        }

        public Criteria andFinIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fin_id not between", value1, value2, "finId");
            return (Criteria) this;
        }

        public Criteria andFinNameIsNull() {
            addCriterion("fin_name is null");
            return (Criteria) this;
        }

        public Criteria andFinNameIsNotNull() {
            addCriterion("fin_name is not null");
            return (Criteria) this;
        }

        public Criteria andFinNameEqualTo(String value) {
            addCriterion("fin_name =", value, "finName");
            return (Criteria) this;
        }

        public Criteria andFinNameNotEqualTo(String value) {
            addCriterion("fin_name <>", value, "finName");
            return (Criteria) this;
        }

        public Criteria andFinNameGreaterThan(String value) {
            addCriterion("fin_name >", value, "finName");
            return (Criteria) this;
        }

        public Criteria andFinNameGreaterThanOrEqualTo(String value) {
            addCriterion("fin_name >=", value, "finName");
            return (Criteria) this;
        }

        public Criteria andFinNameLessThan(String value) {
            addCriterion("fin_name <", value, "finName");
            return (Criteria) this;
        }

        public Criteria andFinNameLessThanOrEqualTo(String value) {
            addCriterion("fin_name <=", value, "finName");
            return (Criteria) this;
        }

        public Criteria andFinNameLike(String value) {
            addCriterion("fin_name like", value, "finName");
            return (Criteria) this;
        }

        public Criteria andFinNameNotLike(String value) {
            addCriterion("fin_name not like", value, "finName");
            return (Criteria) this;
        }

        public Criteria andFinNameIn(List<String> values) {
            addCriterion("fin_name in", values, "finName");
            return (Criteria) this;
        }

        public Criteria andFinNameNotIn(List<String> values) {
            addCriterion("fin_name not in", values, "finName");
            return (Criteria) this;
        }

        public Criteria andFinNameBetween(String value1, String value2) {
            addCriterion("fin_name between", value1, value2, "finName");
            return (Criteria) this;
        }

        public Criteria andFinNameNotBetween(String value1, String value2) {
            addCriterion("fin_name not between", value1, value2, "finName");
            return (Criteria) this;
        }

        public Criteria andFinCreditIdIsNull() {
            addCriterion("fin_credit_id is null");
            return (Criteria) this;
        }

        public Criteria andFinCreditIdIsNotNull() {
            addCriterion("fin_credit_id is not null");
            return (Criteria) this;
        }

        public Criteria andFinCreditIdEqualTo(String value) {
            addCriterion("fin_credit_id =", value, "finCreditId");
            return (Criteria) this;
        }

        public Criteria andFinCreditIdNotEqualTo(String value) {
            addCriterion("fin_credit_id <>", value, "finCreditId");
            return (Criteria) this;
        }

        public Criteria andFinCreditIdGreaterThan(String value) {
            addCriterion("fin_credit_id >", value, "finCreditId");
            return (Criteria) this;
        }

        public Criteria andFinCreditIdGreaterThanOrEqualTo(String value) {
            addCriterion("fin_credit_id >=", value, "finCreditId");
            return (Criteria) this;
        }

        public Criteria andFinCreditIdLessThan(String value) {
            addCriterion("fin_credit_id <", value, "finCreditId");
            return (Criteria) this;
        }

        public Criteria andFinCreditIdLessThanOrEqualTo(String value) {
            addCriterion("fin_credit_id <=", value, "finCreditId");
            return (Criteria) this;
        }

        public Criteria andFinCreditIdLike(String value) {
            addCriterion("fin_credit_id like", value, "finCreditId");
            return (Criteria) this;
        }

        public Criteria andFinCreditIdNotLike(String value) {
            addCriterion("fin_credit_id not like", value, "finCreditId");
            return (Criteria) this;
        }

        public Criteria andFinCreditIdIn(List<String> values) {
            addCriterion("fin_credit_id in", values, "finCreditId");
            return (Criteria) this;
        }

        public Criteria andFinCreditIdNotIn(List<String> values) {
            addCriterion("fin_credit_id not in", values, "finCreditId");
            return (Criteria) this;
        }

        public Criteria andFinCreditIdBetween(String value1, String value2) {
            addCriterion("fin_credit_id between", value1, value2, "finCreditId");
            return (Criteria) this;
        }

        public Criteria andFinCreditIdNotBetween(String value1, String value2) {
            addCriterion("fin_credit_id not between", value1, value2, "finCreditId");
            return (Criteria) this;
        }

        public Criteria andFinCreditStatusIsNull() {
            addCriterion("fin_credit_status is null");
            return (Criteria) this;
        }

        public Criteria andFinCreditStatusIsNotNull() {
            addCriterion("fin_credit_status is not null");
            return (Criteria) this;
        }

        public Criteria andFinCreditStatusEqualTo(Integer value) {
            addCriterion("fin_credit_status =", value, "finCreditStatus");
            return (Criteria) this;
        }

        public Criteria andFinCreditStatusNotEqualTo(Integer value) {
            addCriterion("fin_credit_status <>", value, "finCreditStatus");
            return (Criteria) this;
        }

        public Criteria andFinCreditStatusGreaterThan(Integer value) {
            addCriterion("fin_credit_status >", value, "finCreditStatus");
            return (Criteria) this;
        }

        public Criteria andFinCreditStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("fin_credit_status >=", value, "finCreditStatus");
            return (Criteria) this;
        }

        public Criteria andFinCreditStatusLessThan(Integer value) {
            addCriterion("fin_credit_status <", value, "finCreditStatus");
            return (Criteria) this;
        }

        public Criteria andFinCreditStatusLessThanOrEqualTo(Integer value) {
            addCriterion("fin_credit_status <=", value, "finCreditStatus");
            return (Criteria) this;
        }

        public Criteria andFinCreditStatusIn(List<Integer> values) {
            addCriterion("fin_credit_status in", values, "finCreditStatus");
            return (Criteria) this;
        }

        public Criteria andFinCreditStatusNotIn(List<Integer> values) {
            addCriterion("fin_credit_status not in", values, "finCreditStatus");
            return (Criteria) this;
        }

        public Criteria andFinCreditStatusBetween(Integer value1, Integer value2) {
            addCriterion("fin_credit_status between", value1, value2, "finCreditStatus");
            return (Criteria) this;
        }

        public Criteria andFinCreditStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("fin_credit_status not between", value1, value2, "finCreditStatus");
            return (Criteria) this;
        }

        public Criteria andFinCreditTimeIsNull() {
            addCriterion("fin_credit_time is null");
            return (Criteria) this;
        }

        public Criteria andFinCreditTimeIsNotNull() {
            addCriterion("fin_credit_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinCreditTimeEqualTo(Date value) {
            addCriterion("fin_credit_time =", value, "finCreditTime");
            return (Criteria) this;
        }

        public Criteria andFinCreditTimeNotEqualTo(Date value) {
            addCriterion("fin_credit_time <>", value, "finCreditTime");
            return (Criteria) this;
        }

        public Criteria andFinCreditTimeGreaterThan(Date value) {
            addCriterion("fin_credit_time >", value, "finCreditTime");
            return (Criteria) this;
        }

        public Criteria andFinCreditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fin_credit_time >=", value, "finCreditTime");
            return (Criteria) this;
        }

        public Criteria andFinCreditTimeLessThan(Date value) {
            addCriterion("fin_credit_time <", value, "finCreditTime");
            return (Criteria) this;
        }

        public Criteria andFinCreditTimeLessThanOrEqualTo(Date value) {
            addCriterion("fin_credit_time <=", value, "finCreditTime");
            return (Criteria) this;
        }

        public Criteria andFinCreditTimeIn(List<Date> values) {
            addCriterion("fin_credit_time in", values, "finCreditTime");
            return (Criteria) this;
        }

        public Criteria andFinCreditTimeNotIn(List<Date> values) {
            addCriterion("fin_credit_time not in", values, "finCreditTime");
            return (Criteria) this;
        }

        public Criteria andFinCreditTimeBetween(Date value1, Date value2) {
            addCriterion("fin_credit_time between", value1, value2, "finCreditTime");
            return (Criteria) this;
        }

        public Criteria andFinCreditTimeNotBetween(Date value1, Date value2) {
            addCriterion("fin_credit_time not between", value1, value2, "finCreditTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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