package com.css.utils.hibernate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
 
import javax.persistence.Column;
 
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
 
public class HQLUtils {
 
    /** > 操作 */
    public final static String HQL_SIGN_GT = ">";
    /** < 操作 */
    public final static String HQL_SIGN_LT = "<";
    /** = 操作 */
    public final static String HQL_SIGN_EQ = "=";
    /** >= 操作 */
    public final static String HQL_SIGN_GE = ">=";
    /** <= 操作 */
    public final static String HQL_SIGN_LE = "<=";
    /** != 操作 */
    public final static String HQL_SIGN_NE = "!=";
    /** between 操作 */
    public final static String HQL_SIGN_BETWEEN = "between";
    /** 为 null 操作 */
    public final static String HQL_SIGN_NULL = "null";
    /** 不为 null 操作 */
    public final static String HQL_SIGN_NO_NULL = "notNull";
    /** in 操作 */
    public final static String HQL_SIGN_IN = "in";
    /** like全匹配, 如%val% */
    public final static String HQL_LIKE_ANYWHERE = "%val%";
    /** like右匹配, 如val% */
    public final static String HQL_LIKE_RIGHT = "val%";
    /** like左匹配, 如%val */
    public final static String HQL_LIKE_LEFT = "%val";
    /** 拼接 纯SQL语句 操作 */
    public final static String HQL_ADD_SQL = "sql";
 
    /**
     * 大于号拼接;key>value
     * 
     * @param dc
     * @param key
     * @param value
     */
    public static void addGt(DetachedCriteria dc, String key, Object value) {
        addSign(dc, HQL_SIGN_GT, key, value);
    }
 
    /**
     * 小于号拼接;key<value
     * 
     * @param dc
     * @param key
     * @param value
     */
    public static void addLt(DetachedCriteria dc, String key, Object value) {
        addSign(dc, HQL_SIGN_LT, key, value);
    }
 
    /**
     * 等于号拼接;key=value
     * 
     * @param dc
     * @param key
     * @param value
     */
    public static void addEq(DetachedCriteria dc, String key, Object value) {
        addSign(dc, HQL_SIGN_EQ, key, value);
    }
 
    /**
     * 大于等于号拼接；key>=value
     * 
     * @param dc
     * @param key
     * @param value
     */
    public static void addGe(DetachedCriteria dc, String key, Object value) {
        addSign(dc, HQL_SIGN_GE, key, value);
    }
 
    /**
     * 小于等于号拼接；key<=value
     * 
     * @param dc
     * @param key
     * @param value
     */
    public static void addLe(DetachedCriteria dc, String key, Object value) {
        addSign(dc, HQL_SIGN_LE, key, value);
    }
 
    /**
     * 全like拼接; key like %value%
     * 
     * @param dc
     * @param key
     * @param value
     */
    public static void addAnywhere(DetachedCriteria dc, String key, Object value) {
        addSign(dc, HQL_LIKE_ANYWHERE, key, value);
    }
 
    /**
     * 右like拼接; key like value%
     * 
     * @param dc
     * @param key
     * @param value
     */
    public static void addRight(DetachedCriteria dc, String key, Object value) {
        addSign(dc, HQL_LIKE_RIGHT, key, value);
    }
 
    /**
     * 左like拼接; key like %value
     * 
     * @param dc
     * @param key
     * @param value
     */
    public static void addLeft(DetachedCriteria dc, String key, Object value) {
        addSign(dc, HQL_LIKE_LEFT, key, value);
    }
 
    /**
     * 不等于号拼接; key != value
     * 
     * @param dc
     * @param key
     * @param value
     */
    public static void addNe(DetachedCriteria dc, String key, Object value) {
        addSign(dc, HQL_SIGN_NE, key, value);
    }
 
    /**
     * 区间拼接; key between value1 and value2
     * 
     * @param dc
     * @param key
     * @param value
     */
    public static void addBetween(DetachedCriteria dc, String key,
            Object value1, Object value2) {
        addSign(dc, HQL_SIGN_BETWEEN, key, value1, value2);
    }
 
    /**
     * null值拼接；key is null
     * 
     * @param dc
     * @param key
     */
    public static void addNull(DetachedCriteria dc, String key) {
        addSign(dc, HQL_SIGN_NULL, key);
    }
 
    /**
     * null值拼接；key is not null
     * 
     * @param dc
     * @param key
     */
    public static void addNotNull(DetachedCriteria dc, String key) {
        addSign(dc, HQL_SIGN_NO_NULL, key);
    }
 
    /**
     * in 拼接；key in value1
     * 
     * @param dc
     * @param key
     */
    public static void addIn(DetachedCriteria dc, String key, Object value) {
        addSign(dc, HQL_SIGN_IN, key, value);
    }
 
    /**
     * sql 拼接；原dc的语句后接上key
     * 
     * @param dc
     * @param key
     */
    public static void addSQL(DetachedCriteria dc, String key) {
        addSign(dc, HQL_ADD_SQL, key);
    }
 
    /**
     * 根据符号拼接条件
     * 
     * @param dc
     * @param sign
     *            符号
     * @param key
     * @param value
     */
    @SuppressWarnings("rawtypes")
    public static void addSign(DetachedCriteria dc, String sign, String key,
            Object... value) {
 
        if (dc == null || StringUtils.isBlank(sign) || StringUtils.isBlank(key)) {
            return;
        }
 
        if (HQL_SIGN_EQ.equals(sign)) {
            dc.add(Restrictions.eq(key, value[0]));
 
        } else if (HQL_SIGN_GT.equals(sign)) {
            dc.add(Restrictions.gt(key, value[0]));
 
        } else if (HQL_SIGN_LT.equals(sign)) {
            dc.add(Restrictions.lt(key, value[0]));
 
        } else if (HQL_SIGN_GE.equals(sign)) {
            dc.add(Restrictions.ge(key, value[0]));
 
        } else if (HQL_SIGN_LE.equals(sign)) {
            dc.add(Restrictions.le(key, value[0]));
 
        } else if (HQL_LIKE_ANYWHERE.equals(sign)) {
            dc.add(Restrictions.like(key, String.valueOf(value[0]),
                    MatchMode.ANYWHERE));
 
        } else if (HQL_LIKE_RIGHT.equals(sign)) {
            dc.add(Restrictions.like(key, String.valueOf(value[0]),
                    MatchMode.START));
 
        } else if (HQL_LIKE_LEFT.equals(sign)) {
            dc.add(Restrictions.like(key, String.valueOf(value[0]),
                    MatchMode.END));
 
        } else if (HQL_SIGN_NE.equals(sign)) {
            dc.add(Restrictions.ne(key, value[0]));
 
        } else if (HQL_SIGN_BETWEEN.equals(sign)) {
            dc.add(Restrictions.between(key, value[0], value[1]));
 
        } else if (HQL_SIGN_NULL.equals(sign)) {
            dc.add(Restrictions.isNull(key));
 
        } else if (HQL_SIGN_NO_NULL.equals(sign)) {
            dc.add(Restrictions.isNotNull(key));
 
        } else if (HQL_SIGN_IN.equals(sign)) {
 
            if (value[0] instanceof Collection) {
                dc.add(Restrictions.in(key, (Collection) value[0]));
            } else {
                dc.add(Restrictions.in(key, value));
            }
 
        } else if (HQL_ADD_SQL.equals(sign)) {
            dc.add(Restrictions.sqlRestriction(key));
        }
 
    }
 
    /**
     * 获得与表有对应关系的类属性集合
     * 
     * @param <T>
     * @param claz
     * @return
     */
    public static <T> List<Field> getTableFields(Class<T> claz) {
        List<Field> list = new ArrayList<Field>();
        Field[] fields = claz.getDeclaredFields();
        for (Field field : fields) {
            Column c = field.getAnnotation(Column.class);
            if (c != null && StringUtils.isNotBlank(c.name())) {
                list.add(field);
            }
        }
        return list;
    }
 
}