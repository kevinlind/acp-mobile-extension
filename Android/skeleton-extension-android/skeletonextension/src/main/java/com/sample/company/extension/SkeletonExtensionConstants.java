package com.sample.company.extension;

class SkeletonExtensionConstants {
    static final String EVENT_TYPE_EXTENSION = "com.sample.acp.eventType.skeletonExtension";
    static final String EVENT_SOURCE_EXTENSION_REQUEST_CONTENT = "com.sample.acp.eventSource.requestContent";
    static final String EVENT_SOURCE_EXTENSION_RESPONSE_CONTENT = "com.sample.acp.eventSource.responseContent";
    static final String EVENT_TYPE_ADOBE_HUB = "com.adobe.eventType.hub";
    static final String EVENT_SOURCE_ADOBE_SHARED_STATE = "com.adobe.eventSource.sharedState";

    static final String EVENT_SETTER_REQUEST_DATA_KEY = "setterdata";
    static final String EVENT_GETTER_RESPONSE_DATA_KEY = "getterdata";


    class SharedState {
        static final String STATE_OWNER = "stateowner";
        static final String CONFIGURATION = "com.adobe.module.configuration";

        private SharedState(){}
    }


    private SkeletonExtensionConstants() {}
}