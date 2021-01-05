package net.stapi.mv.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.stapi.mv.jdbc.JDBCConnection;
import net.stapi.mv.jdbc.query.SelectOneObject;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StubConfig {
    private static final Logger logger = LoggerFactory.getLogger(StubConfig.class);
    private JDBCConnection jdbc;
    private List<Source> sources;
    private List<ServiceStubConfig> serviceStubConfigs;

    public void setJdbc(JDBCConnection jdbc) {
        this.jdbc = jdbc;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public void setServiceStubConfigs(List<ServiceStubConfig> serviceStubConfigs) {
        this.serviceStubConfigs = serviceStubConfigs;
    }

    public ServiceStubConfig getServiceStubConfig(String nameService) {
        List<ServiceStubConfig> ssc = serviceStubConfigs.stream().filter(e -> e.getLocalName().equalsIgnoreCase(nameService)).collect(Collectors.toList());
        if (ssc.size() != 1) {
            logger.error("Count service config for {} not valid. Found is {} service.", nameService, ssc.size());
            throw new IndexOutOfBoundsException();
        } else {
            return ssc.get(0);
        }
    }

    public void initServices() throws SQLException {
        HashMap<String, SelectOneObject<String>> queryMap = new HashMap<>();
        for(Source source: sources) {
            SelectOneObject<String> selectOneObject = new SelectOneObject<>();
            selectOneObject.setJDBCConnection(jdbc);
            selectOneObject.setQuery(source.getQuery());
            queryMap.put(source.getSid(), selectOneObject);
        }
        for(ServiceStubConfig service: serviceStubConfigs) {
            initDelay(service, queryMap);
        }
        jdbc.closeConnection();
    }

    private void initDelay(ServiceStubConfig service, HashMap<String, SelectOneObject<String>> queryMap) throws SQLException {
        if (service.getDelayConfig().isEnable()) {
            if (service.getDelayConfig().hasSid()) {
                try {
                    int[] params = Arrays.stream(queryMap.get(service.getDelayConfig().getSid()).getResult(new String[]{service.getRemoteName()}).split(";"))
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    service.getDelayConfig().setParams(params);
                } catch (SQLException e) {
                    queryMap.get(service.getDelayConfig().getSid()).getJDBCConnection().closeConnection();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
