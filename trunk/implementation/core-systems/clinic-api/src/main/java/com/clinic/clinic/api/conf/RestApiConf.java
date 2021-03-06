package com.clinic.clinic.api.conf;

/**
 * <p>
 * System configuration settings of REST API web services.
 * These settings are loaded from properties file.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 */
public class RestApiConf {
    private String rootPathPublishResources;
    private String apnsP12Dir;
    private String apnsMode;
    
    private static RestApiConf instance;
    
    /**
     * <p>Constructor of this class.</p>
     */
    public RestApiConf() {
        instance = this;
    }
    
    /**
     * <p>Gets current instance of REST API configuration class.</p>
     *
     * @return {@link RestApiConf} instance contains current settings in REST API web services
     */
    public static RestApiConf getInstance() {
        return instance;
    }

    /**
     * <p>Returns current value of rootPathPublishResources attribute.</p>
     *
     * @return the rootPathPublishResources
     */
    public String getRootPathPublishResources() {
        return rootPathPublishResources;
    }

    /**
     * <p>Sets value of rootPathPublishResources attribute.</p>
     *
     * @param rootPathPublishResources the rootPathPublishResources to set
     */
    public void setRootPathPublishResources(final String rootPathPublishResources) {
        this.rootPathPublishResources = rootPathPublishResources;
    }
    
    public String getApnsP12Dir() {
		return apnsP12Dir;
	}

	public void setApnsP12Dir(String apnsP12Dir) {
		this.apnsP12Dir = apnsP12Dir;
	}

	public String getApnsMode() {
		return apnsMode;
	}

	public void setApnsMode(String apnsMode) {
		this.apnsMode = apnsMode;
	}
	
	public boolean isApnsSandbox() {
		return this.apnsMode.equals("sandbox");
	}

	/*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("rootPathPubRes=").append(rootPathPublishResources);
        return sb.toString();
    }
}
