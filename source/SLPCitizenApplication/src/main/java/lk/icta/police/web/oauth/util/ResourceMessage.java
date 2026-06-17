package lk.icta.police.web.oauth.util;

import java.util.ResourceBundle;

/**
 * Resource messages interface.
 *
 * @author GKuhajeyan
 *
 */
public interface ResourceMessage {
    /**
     *
     * @return config messages
     */
    ResourceBundle getConfigMessages();

    /**
     *
     * @return logger messages
     */
    ResourceBundle getLoggerMessages();

    /**
     *
     * @return application messages
     */
    ResourceBundle getAppMessages();

    /**
     *
     * @return display messages for citizens portlet
     */
    ResourceBundle getDisplayMessages();

    /**
     *
     * @return display messages for alert portlet
     */
    ResourceBundle getAlertDisplayMessages();
}
