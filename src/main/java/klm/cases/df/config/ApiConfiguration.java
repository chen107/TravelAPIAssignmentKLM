package klm.cases.df.config;

public class ApiConfiguration {

    public static final String USER = "user";
    public static final String PSD = "secret123";

    public static final String END_AIRPORTS = "/airports";
    public static final String END_AIRPORTS_CODE = "/{code}";
    public static final String URI_AIRPORTS_MOCK_SYS = "http://localhost:8080/airports";

    public static final String END_FARES = "/fares";
    public static final String END_FARES_ORIGIN_DEST = "/{origin}/{destination}";
    public static final String URI_FARES_MOCK_SYS = "http://localhost:8080/fares";

    public static final String END_STATISTICS = "/statistics";
    public static final String END_STATISTICS_OK = "/status/ok";
    public static final String END_STATISTICS_4XX = "/status/4xx";
    public static final String END_STATISTICS_5XX = "/status/5xx";
    public static final String END_STATISTICS_ALL = "/status/all";

    public static final String END_HEAD = "http://localhost:";
    public static final String PATH_STATISTICS_OK = "/actuator/metrics/http.server.requests?tag=status:200";
    public static final String PATH_STATISTICS_4XX = "/actuator/metrics/http.server.requests?tag=status:404";
    public static final String PATH_STATISTICS_5XX = "/actuator/metrics/http.server.requests?tag=status:500";
    public static final String PATH_STATISTICS_ALL = "/actuator/metrics/http.server.requests";

    public static final String PAGE = "page";
    public static final String SIZE = "size";
    public static final String ORDERBY = "orderby";

    public static final String ORDERBY_ASC_OP = "asc";
    public static final String ORDERBY_DESC_OP = "desc";

    public static final String JSON_NODE_EMBEDDED = "_embedded";
    public static final String JSON_NODE_LOCATIONS = "locations";
    public static final String JSON_NODE_MEASUREMENTS = "measurements";
    public static final String JSON_NODE_STATISTIC = "statistic";
    public static final String JSON_NODE_VALUE = "value";
    public static final String JSON_NODE_COUNT = "COUNT";
    public static final String JSON_NODE_MAX = "MAX";
    public static final String JSON_NODE_TOTAL_TIME = "TOTAL_TIME";
    public static final String TOTAL_COUNT = "total_count";
    public static final String TOTAL_COUNT_RS_OK = "total_count_request_status_ok";
    public static final String TOTAL_COUNT_RS_4XX = "total_count_request_status_4xx";
    public static final String TOTAL_COUNT_RS_5XX = "total_count_request_status_5xx";
    public static final String TOTAL_COUNT_REQUEST = "total_count_request";
    public static final String MAX_RESPONSE_TIME = "max_response_time";
    public static final String AVE_RESPONSE_TIME = "average_response_time";
    public static final String MAX_TIME = "max_time";
    public static final String AVE_TIME = "average_time";

}
