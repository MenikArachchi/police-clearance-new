package lk.icta.commonuser.dept.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lk.icta.commonuser.framework.utility.ErrorKeyLoader;
import lk.icta.commonuser.framework.utility.LanguageBasedPopupLoader;

import org.apache.log4j.Logger;

public class ContextLoaderServlet extends HttpServlet {
	
	private static final long serialVersionUID = 9001539355857463L;
	private static final Logger LOGGER = Logger.getLogger(ContextLoaderServlet.class);
	
    @Override
	public void init() throws ServletException {
    	initAppContextObject();
	}
    
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	doAction(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	doAction(req, resp);
    }
    
    protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	initAppContextObject();
	}
    
    private void initAppContextObject(){
    	if (LOGGER.isDebugEnabled()) {
    		LOGGER.debug("initAppContextObject started");
    	}
    	
    	LanguageBasedPopupLoader.loadPopupMessageIntoMaps();
    	ErrorKeyLoader.loadErrorKeysIntoMap();
    	
    	if (LOGGER.isDebugEnabled()) {
    		LOGGER.debug("initAppContextObject finished");
    	}
    }
    
    @Override
    public void destroy() {
    	super.destroy();
    }
}
