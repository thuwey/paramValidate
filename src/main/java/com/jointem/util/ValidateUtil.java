package com.jointem.util;

import com.jointem.annotation.ListParamAnnonation;
import com.jointem.annotation.NumberParamAnnonation;
import com.jointem.annotation.OtherObjectAnnotation;
import com.jointem.annotation.StringParamAnotation;
import com.jointem.exception.ValidateException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Atuhor wangwei
 * Date 2017/5/17
 * Time 上午9:04
 * Description
 **/
public class ValidateUtil {

    public static void validate(Object obj) {

        if (null == obj) {
            throw new ValidateException("传入的参数为空");
        } else {
            if (obj instanceof List) {
                ((List) obj).forEach((t) -> {
                    for (Class cls = t.getClass(); null != cls; cls = cls.getSuperclass()) {
                        Field[] fields = cls.getDeclaredFields();
                        validateField(fields, t);
                    }
                });
            } else {
                for (Class cls = obj.getClass(); null != cls; cls = cls.getSuperclass()) {
                    Field[] fields = cls.getDeclaredFields();
                    validateField(fields, obj);
                }

            }

        }

    }

    private static void validateField(Field[] fields, Object obj) {
        Field[] arr$ = fields;
        int len$ = fields.length;
        try {
            for (int i$ = 0; i$ < len$; ++i$) {
                Field field = arr$[i$];
                field.setAccessible(true);
                Class fcl = field.getType();
                if (String.class.isAssignableFrom(fcl)) {
                    validateString(field, obj);
                } else if (Collection.class.isAssignableFrom(fcl)) {
                    validateList(field, obj);
                } else if (Number.class.isAssignableFrom(fcl)) {
                    validateNumber(field, obj);
                } else {
                    validateObject(field, obj);
                }
            }
        } catch (IllegalAccessException var3) {
            throw new ValidateException("验证位置错误", var3);
        }

    }

    private static void validateString(Field field, Object obj) throws IllegalAccessException {
        StringParamAnotation sa = (StringParamAnotation) field.getAnnotation(StringParamAnotation.class);
        if (sa != null) {
            if (!sa.canBeNull() && (null == field.get(obj) || "".equals(field.get(obj)))) {
                String message = getMessage("字段" + field.getName() + "为空", sa.message());
                throw new ValidateException(message);
            }

            if (-1 != sa.minlength()) {
                if (null == field.get(obj)) {
                    String message = getMessage("字段" + field.getName() + "为空", sa.message());
                    throw new ValidateException(message);
                }

                if (((String) field.get(obj)).length() < sa.minlength()) {
                    String message = getMessage("字段" + field.getName() + "小于指定字符串长度", sa.message());
                    throw new ValidateException(message);
                }
            }

            if (-1 != sa.maxlength()) {
                if (null == field.get(obj)) {
                    String message = getMessage("字段" + field.getName() + "为空", sa.message());
                    throw new ValidateException(message);
                }

                if (((String) field.get(obj)).length() > sa.maxlength()) {
                    String message = getMessage("字段" + field.getName() + "大于指定字符串长度" + "为空", sa.message());
                    throw new ValidateException(message);
                }
            }
        }

    }

    private static void validateList(Field field, Object obj) throws IllegalAccessException {
        ListParamAnnonation la = (ListParamAnnonation) field.getAnnotation(ListParamAnnonation.class);
        if (la != null) {
            if (!la.canBeNull() && (null == field.get(obj) || ((List) field.get(obj)).equals(Integer.valueOf(0)))) {
                String message = getMessage("字段" + field.getName() + "为空", la.message());
                throw new ValidateException(message);
            }

            if (-1 != la.maxSize()) {
                if (null == field.get(obj)) {
                    String message = getMessage("字段" + field.getName() + "为空", la.message());
                    throw new ValidateException(message);
                }

                if (((List) field.get(obj)).size() > la.maxSize()) {
                    String message = getMessage("字段" + field.getName() + "超过了数组最大容量", la.message());
                    throw new ValidateException(message);
                }
            }

            if (-1 != la.minSize()) {
                if (null == field.get(obj)) {
                    String message = getMessage("字段" + field.getName() + "为空", la.message());
                    throw new ValidateException(message);
                }

                if (((List) field.get(obj)).size() < la.minSize()) {
                    String message = getMessage("字段" + field.getName() + "低于数组最小容量", la.message());
                    throw new ValidateException(message);
                }
            }
        }

    }

    private static void validateNumber(Field field, Object obj) throws IllegalAccessException {
        NumberParamAnnonation na = (NumberParamAnnonation) field.getAnnotation(NumberParamAnnonation.class);
        if (null != na) {
            if (!na.canBeNull() && (null == field.get(obj) || "".equals(field.get(obj)))) {
                String message = getMessage("字段" + field.getName() + "为空", na.message());
                throw new ValidateException(message);
            }

            if (-1 != na.minNum()) {
                if (null == field.get(obj)) {
                    String message = getMessage("字段" + field.getName() + "为空", na.message());
                    throw new ValidateException(message);
                }

                if (((Number) field.get(obj)).intValue() < na.minNum()) {
                    String message = getMessage("字段" + field.getName() + "小于最小数值", na.message());
                    throw new ValidateException(message);
                }
            }

            if (-1 != na.maxNum()) {
                if (null == field.get(obj)) {
                    String message = getMessage("字段" + field.getName() + "为空", na.message());
                    throw new ValidateException(message);
                }

                if (((Number) field.get(obj)).intValue() > na.maxNum()) {
                    String message = getMessage("字段" + field.getName() + "大于最大数值", na.message());
                    throw new ValidateException(message);
                }
            }
        }

    }

    private static void validateObject(Field field, Object obj) throws IllegalAccessException {
        OtherObjectAnnotation la = (OtherObjectAnnotation) field.getAnnotation(OtherObjectAnnotation.class);
        if (null != la && !la.canBeNull() && null == field.get(obj)) {
            String message = getMessage("字段" + field.getName() + "为空", la.message());
            throw new ValidateException(message);
        }
    }


    private static String getMessage(String defalut, String owner) {
        String message = "";
        if ("".equals(owner)) {
            message = defalut;
        } else {
            message = owner;
        }
        return message;
    }

}
