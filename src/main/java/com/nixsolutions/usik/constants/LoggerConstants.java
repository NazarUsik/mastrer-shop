package com.nixsolutions.usik.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LoggerConstants {
    public final String CREATE_NEW_ENTITY = "Create new {}  ===>  {}";
    public final String UPDATE_ENTITY = "Update {} with ID: {}  ===>  {}";
    public final String DELETE_ENTITY = "Delete {} with ID: {}";

    public final String POST_MAPPING = "Create Method call";
    public final String PUT_MAPPING = "Update Method call";
    public final String DELETE_MAPPING = "Delete Method call";
    public final String GET_MAPPING_WITH_PARAMETER = "Read Method call With parameter: {}";
    public final String SEARCH_MAPPING = "Search Method call With parameter: {}";
}
