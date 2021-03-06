package org.zalando.stups.fullstop;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.cloudtrail.processinglibrary.configuration.ProcessingConfiguration;
import com.amazonaws.services.cloudtrail.processinglibrary.configuration.PropertiesFileConfiguration;
import com.amazonaws.services.cloudtrail.processinglibrary.model.CloudTrailEventMetadata;
import com.amazonaws.services.cloudtrail.processinglibrary.utils.LibraryUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author jbellmann
 */
public class ExtPropertiesFileConfiguration implements ProcessingConfiguration {

    /* configuration file property names */
    public static final String ACCESS_KEY = "accessKey";

    public static final String SECRET_KEY = "secretKey";

    public static final String SQS_URL = "sqsUrl";

    public static final String SQS_REGION = "sqsRegion";

    public static final String VISIBILITY_TIMEOUT = "visibilityTimeout";

    public static final String S3_REGION = "s3Region";

    public static final String THREAD_COUNT = "threadCount";

    public static final String THREAD_TERMINATION_DELAY_SECONDS = "threadTerminationDelaySeconds";

    public static final String MAX_EVENTS_PER_EMIT = "maxEventsPerEmit";

    public static final String ENABLE_RAW_EVENT_INFO = "enableRawEventInfo";

    private static final String ERROR_CREDENTIALS_PROVIDER_NULL = "CredentialsProvider is null. Either put your "
            + "access key and secret key in the configuration file in your class path, or spcify it in the "
            + "ProcessingConfiguration object.";

    /**
     * The <a href=
     * "http://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/auth/AWSCredentialsProvider.html" >AWS
     * credentials provider</a> used to obtain credentials.
     */
    private final AWSCredentialsProvider awsCredentialsProvider;

    /**
     * The SQS Queue URL used to receive events.
     * <p>
     * <p>The Queue must be subscribed to AWS CloudTrail.
     */
    private String sqsUrl = null;

    /**
     * The SQS region to use.
     * <p>
     * <p>If not specified, the default SQS region ( {@value ProcessingConfiguration#DEFAULT_SQS_REGION}) will be used.
     */
    private String sqsRegion = DEFAULT_SQS_REGION;

    /**
     * A period of time, in seconds, during which Amazon SQS prevents other consuming components from receiving and
     * processing messages that are currently being processed by the CloudTrail Processing Library on your behalf.
     */
    private int visibilityTimeout = DEFAULT_VISIBILITY_TIMEOUT;

    /**
     * The S3 endpoint specific to a region.
     * <p>
     * <p>If not specified, the default S3 region will be used.
     */
    private String s3Region = DEFAULT_S3_REGION;

    /**
     * The number of threads used to download log files from S3 in parallel.
     * <p>
     * <p>Callbacks can be invoked from any thread.
     */
    private int threadCount = DEFAULT_THREAD_COUNT;

    /**
     * The time allowed, in seconds, for threads to shut down after AWSCloudTrailEventProcessingExecutor.stop() is
     * called.
     * <p>
     * <p>Any threads still running beyond this time will be forcibly terminated.
     */
    private int threadTerminationDelaySeconds = DEFAULT_THREAD_TERMINATION_DELAY_SECONDS;

    /**
     * The maximum number of AWSCloudTrailClientEvents sent to a single invocation of processEvents().
     */
    private int maxEventsPerEmit = DEFAULT_MAX_EVENTS_PER_EMIT;

    /**
     * Whether to include raw event information in {@link CloudTrailEventMetadata}.
     */
    private boolean enableRawEventInfo = DEFAULT_ENABLE_RAW_EVENT_INFO;

    public ExtPropertiesFileConfiguration(final Properties prop) {
        this(prop, new DefaultAWSCredentialsProviderChain());
    }

    /**
     * Creates a {@link PropertiesFileConfiguration} from values provided in a classpath properties file.
     *
     * @param prop               the classpath properties file to load.
     * @param credentialProvider credential provider.
     */
    public ExtPropertiesFileConfiguration(final Properties prop, final AWSCredentialsProviderChain credentialProvider) {

        this.sqsUrl = prop.getProperty(SQS_URL);
        LibraryUtils.checkArgumentNotNull(this.sqsUrl, "Cannot find SQS URL in properties file.");

        final String accessKey = prop.getProperty(ACCESS_KEY);
        final String secretKey = prop.getProperty(SECRET_KEY);

        if (accessKey != null && secretKey != null) {
            this.awsCredentialsProvider = new SimplePropertiesCredentials(prop);
        }
        else {
            this.awsCredentialsProvider = credentialProvider;
        }

        this.s3Region = prop.getProperty(S3_REGION);
        this.visibilityTimeout = this.getIntProperty(prop, VISIBILITY_TIMEOUT);

        this.sqsRegion = prop.getProperty(SQS_REGION);

        this.threadCount = this.getIntProperty(prop, THREAD_COUNT);
        this.threadTerminationDelaySeconds = this.getIntProperty(prop, THREAD_TERMINATION_DELAY_SECONDS);

        this.maxEventsPerEmit = this.getIntProperty(prop, MAX_EVENTS_PER_EMIT);
        this.enableRawEventInfo = this.getBooleanProperty(prop, ENABLE_RAW_EVENT_INFO);
    }

    public static ExtPropertiesFileConfiguration fromClasspath(final String propertiesFile) {

        // load properties from configuration properties file
        final Properties prop = ExtPropertiesFileConfiguration.loadProperty(propertiesFile);
        return new ExtPropertiesFileConfiguration(prop);
    }

    /**
     * Load properties from a classpath property file.
     *
     * @param propertiesFile the classpath properties file to read.
     * @return a <a href= "http://docs.oracle.com/javase/7/docs/api/java/util/Properties.html" >Properties</a> object
     * containing the properties set in the file.
     */
    protected static Properties loadProperty(final String propertiesFile) {
        final Properties prop = new Properties();
        try {
            final InputStream in = ExtPropertiesFileConfiguration.class.getResourceAsStream(propertiesFile);
            prop.load(in);
            in.close();
        }
        catch (final IOException e) {
            throw new IllegalStateException("Cannot load property file at " + propertiesFile, e);
        }

        return prop;
    }

    /**
     * {@inheritDoc}
     */
    public AWSCredentialsProvider getAwsCredentialsProvider() {
        return awsCredentialsProvider;
    }

    /**
     * {@inheritDoc}
     */
    public String getSqsUrl() {
        return sqsUrl;
    }

    /**
     * {@inheritDoc}
     */
    public String getSqsRegion() {
        return sqsRegion;
    }

    /**
     * {@inheritDoc}
     */
    public int getVisibilityTimeout() {
        return visibilityTimeout;
    }

    /**
     * {@inheritDoc}
     */
    public String getS3Region() {
        return s3Region;
    }

    /**
     * {@inheritDoc}
     */
    public int getThreadCount() {
        return threadCount;
    }

    /**
     * {@inheritDoc}
     */
    public int getThreadTerminationDelaySeconds() {
        return threadTerminationDelaySeconds;
    }

    /**
     * {@inheritDoc}
     */
    public int getMaxEventsPerEmit() {
        return maxEventsPerEmit;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEnableRawEventInfo() {
        return enableRawEventInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate() {
        LibraryUtils.checkArgumentNotNull(this.getAwsCredentialsProvider(), ERROR_CREDENTIALS_PROVIDER_NULL);
        LibraryUtils.checkArgumentNotNull(this.getSqsUrl(), "SQS URL is null.");
        LibraryUtils.checkArgumentNotNull(this.getSqsRegion(), "SQS Region is null.");
        LibraryUtils.checkArgumentNotNull(this.getVisibilityTimeout(), "Visibility Timeout is null.");
        LibraryUtils.checkArgumentNotNull(this.getS3Region(), "S3 Region is null.");
        LibraryUtils.checkArgumentNotNull(this.getThreadCount(), "Thread Count is null.");
        LibraryUtils.checkArgumentNotNull(
                this.getThreadTerminationDelaySeconds(),
                "Thread Termination Delay Seconds is null.");
        LibraryUtils.checkArgumentNotNull(this.getMaxEventsPerEmit(), "Maximum Events Per Emit is null.");
        LibraryUtils.checkArgumentNotNull(this.isEnableRawEventInfo(), "Is Enable Raw Event Information is null.");
    }

    /**
     * Convert a string representation of a property to an integer type.
     *
     * @param prop the property class
     * @param name a name to evaluate in the property file.
     * @return an integer representation of the value associated with the property name.
     */
    private int getIntProperty(final Properties prop, final String name) {
        final String propertyValue = prop.getProperty(name);
        return Integer.parseInt(propertyValue);
    }

    /**
     * Convert a string representation of a property to a boolean type.
     *
     * @param prop the property class
     * @param name a name to evaluate in the property file.
     * @return a boolean representation of the value associated with the property name.
     */
    private Boolean getBooleanProperty(final Properties prop, final String name) {
        final String propertyValue = prop.getProperty(name);
        return Boolean.parseBoolean(propertyValue);
    }

}
