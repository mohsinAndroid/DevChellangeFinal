package com.devcrewchallange.view;


/**
 * This is base view which contains all the common controls on the xml layouts.
 */
public interface BaseView {


    /***
     * Show Error message from presenter to the view
     * @param resourceId
     */
    void showErrorMessage(int resourceId);

    /***
     * Show error message from api call in presenter to the view.
     * @param message
     */
    void showApiMessage(String message);
}
