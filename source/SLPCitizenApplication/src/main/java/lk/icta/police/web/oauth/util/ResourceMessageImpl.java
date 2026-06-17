package lk.icta.police.web.oauth.util;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;

/**
 * Get the resource bundle for relevant locale.
 *
 * @author GKuhajeyan
 *
 */
public class ResourceMessageImpl implements ResourceMessage, java.io.Serializable {

    private static final long serialVersionUID = 6087307297706809067L;
    protected Locale locale = Locale.getDefault();
    protected static final transient Logger log = Logger.getLogger(ResourceMessageImpl.class);

    /**
     * Constructor.
     *
     * @param locale
     *            locale
     */
    public ResourceMessageImpl(Locale locale) {
        //log.info("Getting ResourceMessageImpl constructor with locale");
        this.locale = locale;
        if (locale != null) {
          //  log.info("locale variant " + locale.getVariant());
          //  log.info("locale display country " + locale.getDisplayCountry());
        }
    }

    /**
     * No argument constructor.
     */
    public ResourceMessageImpl() {
      //  log.info("Getting ResourceMessageImpl constructor");
        if (this.locale != null) {
           // log.info("locale variant " + this.locale.getVariant());
          //  log.info("locale display country " + this.locale.getDisplayCountry());
        }
    }

    /**
     *
     * @return config messages
     */
    public ResourceBundle getConfigMessages() {
        Locale locale = ActionContext.getContext().getLocale();
        ResourceBundle configMessages = ResourceBundle.getBundle("lk.icta.erl.resources.config", locale);
        return configMessages;
    }

    /**
     *
     * @return logger messages
     */
    public ResourceBundle getLoggerMessages() {
        Locale locale = ActionContext.getContext().getLocale();
        ResourceBundle loggerMessages = ResourceBundle.getBundle("lk.icta.resources.logger", locale);
        return loggerMessages;
    }

    /**
     *
     * @return application messages
     */
    public ResourceBundle getAppMessages() {
        Locale locale = ActionContext.getContext().getLocale();
        ResourceBundle appMessages = ResourceBundle.getBundle("lk.icta.erl.resources.app", locale);
        return appMessages;
    }

    /**
     *
     * @return display messages
     */
    public ResourceBundle getDisplayMessages() {
        Locale locale = ActionContext.getContext().getLocale();
        log.info("locale variantttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt " + locale);
        ResourceBundle displayMessages = ResourceBundle.getBundle("lk.icta.resources.global", locale);
        return displayMessages;
    }

    /**
     * {@inheritDoc}
     */
    public ResourceBundle getAlertDisplayMessages() {
        Locale locale = ActionContext.getContext().getLocale();
        ResourceBundle alertDisplayMessages = ResourceBundle.getBundle("lk.icta.erl.resources.display-alert", locale);
        return alertDisplayMessages;
    }

}
