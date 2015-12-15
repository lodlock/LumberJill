package com.testfunction.lumberjill;

/**
 * Required interface for enabling / disabling logging.
 *
 * Created by lodlock (iamhunted@gmail.com)
 * On 12/8/2015 12:09 PM
 * For the (com.testfunction.lumberjill) project
 */
public interface LumberJillCallbacks {
    /**
     * Get the debug mode value from implementation. This is recommended
     * to be based on BuildConfig.DEBUG to avoid accidental production logs
     *
     * @return true if debug mode is enabled, otherwise false
     */
    boolean isDebugMode();

    /**
     * Get the default element position in the stack trace elements array.
     * This is needed because the top calling elements in the stack trace
     * will be from LumberJillUtility and not from the actual calling method.
     *
     * Recommended value: 5
     *
     * @return the int for the depth of the element within the stack trace array
     */
    int getDefaultDepth();

    /**
     * Allows for custom log message handling of user defined object types.
     *
     * @param object the object passed to be logged
     * @return a string representation of the object
     */
    String objectToString(Object object);
}