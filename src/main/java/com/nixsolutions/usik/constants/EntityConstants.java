package com.nixsolutions.usik.constants;


import lombok.experimental.UtilityClass;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@UtilityClass
public class EntityConstants {
    public final String CLIENT = "Client";
    public final String PHOTO = "Photo";
    public final String PRODUCT = "Product";
    public final String PRODUCT_TYPE = "ProductType";
    public final String PURCHASE = "Purchase";
    public final String REVIEW_PRODUCT = "ReviewProduct";
    public final String ROLE = "Role";
    public final String SHOP = "Shop";
    public final String STATUS = "Status";

    public String getClassNameOfGeneric(Class<?> clazz) {
        Type[] types = clazz.getGenericInterfaces();
        if (types.length == 0) {
            return null;
        }
        String[] split = ((ParameterizedType) types[0]).getActualTypeArguments()[0].getTypeName().split("\\.");
        return split[split.length - 1];
    }

    public String getClassName(Class<?> clazz) {
        String[] split = clazz.getName().split("\\.");
        return split[split.length - 1];
    }
}