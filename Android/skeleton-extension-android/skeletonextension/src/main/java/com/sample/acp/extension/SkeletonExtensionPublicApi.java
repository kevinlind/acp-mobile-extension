package com.sample.acp.extension;

import com.adobe.marketing.mobile.AdobeCallback;
import com.adobe.marketing.mobile.Event;
import com.adobe.marketing.mobile.ExtensionError;
import com.adobe.marketing.mobile.ExtensionErrorCallback;
import com.adobe.marketing.mobile.LoggingMode;
import com.adobe.marketing.mobile.MobileCore;

import java.util.HashMap;
import java.util.Map;

public class SkeletonExtensionPublicApi {
    private static final String LOG_TAG = "Skeleton Extension";

    /**
     * Registers the extension with the Mobile SDK. This method should be called once.
     */
    public static void registerExtension() {
        MobileCore.registerExtension(SkeletonExtension.class, new ExtensionErrorCallback<ExtensionError>() {
            @Override
            public void error(ExtensionError extensionError) {
                MobileCore.log(LoggingMode.ERROR, LOG_TAG, "There was an error registering the SkeletonExtension: " + extensionError.getErrorName());
            }
        });
    }

    /**
     * Example of requesting information from the extension asynchronously. The most common
     * use case is for public getters.
     * @param callback method which receives the returned {@code String} data from the extension
     */
    public static void getRequestFromExtension(final SkeletonExtensionCallback callback) {
        if (callback == null) {
            MobileCore.log(LoggingMode.WARNING, LOG_TAG, "Cannot make request if callback is null.");
            return;
        }

        // create the request event
        final Event requestEvent = new Event.Builder("Get Data Example",
                SkeletonExtensionConstants.EVENT_TYPE_EXTENSION,
                SkeletonExtensionConstants.EVENT_SOURCE_EXTENSION_REQUEST_CONTENT)
                .build();

        if (requestEvent == null) {
            MobileCore.log(LoggingMode.WARNING, LOG_TAG, "An error occurred constructing the data request event.");
            callback.call(null);
            return;
        }

        // dispatch the event and handle the callback
        AdobeCallback<Event> responseEventCallback = new AdobeCallback<Event>() {
            @Override
            public void call(Event event) {
                Map<String, Object> eventData = event.getEventData();
                if (eventData == null || eventData.isEmpty()) {
                    callback.call(null);
                }

                if (eventData.containsKey(SkeletonExtensionConstants.EVENT_GETTER_RESPONSE_DATA_KEY)) {
                    String sampleData = (String) eventData.get(SkeletonExtensionConstants.EVENT_GETTER_RESPONSE_DATA_KEY);
                    callback.call(sampleData);
                } else {
                    callback.call(null);
                }
            }
        };

        ExtensionErrorCallback<ExtensionError> extensionErrorCallback = new ExtensionErrorCallback<ExtensionError>() {
            @Override
            public void error(ExtensionError extensionError) {
                MobileCore.log(LoggingMode.ERROR, LOG_TAG, String.format("An error occurred dispatching event '%s', %s", requestEvent.getName(), extensionError.getErrorName()));
            }
        };

        /* Dispatch request event to the Mobile SDK. When the extension processes the event,
           the responseEventCallback is called with the result data.
         */
        if (MobileCore.dispatchEventWithResponseCallback(requestEvent, responseEventCallback, extensionErrorCallback)) {
            MobileCore.log(LoggingMode.DEBUG, LOG_TAG, String.format("Dispatched event '%s'.", requestEvent.getName()));
        }
    }

    /**
     * Example of sending data to the extension asynchronously. The most common use case
     * is for public setters.
     * @param data the data to send to the extension
     */
    public static void setRequestForExtension(final String data) {

        // create the request event
        Map<String, Object> requestData = new HashMap<>();
        requestData.put(SkeletonExtensionConstants.EVENT_SETTER_REQUEST_DATA_KEY, data);

        final Event requestEvent = new Event.Builder("Set Data Example",
                SkeletonExtensionConstants.EVENT_TYPE_EXTENSION,
                SkeletonExtensionConstants.EVENT_SOURCE_EXTENSION_REQUEST_CONTENT)
                .setEventData(requestData)
                .build();

        if (requestEvent == null) {
            MobileCore.log(LoggingMode.WARNING, LOG_TAG, "An error occurred constructing the update request event.");
            return;
        }

        ExtensionErrorCallback<ExtensionError> extensionErrorCallback = new ExtensionErrorCallback<ExtensionError>() {
            @Override
            public void error(ExtensionError extensionError) {
                MobileCore.log(LoggingMode.ERROR, LOG_TAG, String.format("An error occurred dispatching event '%s', %s", requestEvent.getName(), extensionError.getErrorName()));
            }
        };

        /* Dispatch request event to the Mobile SDK. There is no response callback as this
           is not a request for information.
         */
        if (MobileCore.dispatchEvent(requestEvent, extensionErrorCallback)) {
            MobileCore.log(LoggingMode.DEBUG, LOG_TAG, String.format("Dispatched event '%s'.", requestEvent.getName()));
        }
    }
}
