package com.fedex.ziptodest.server.actuator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


@Component
public class ServerCustomHealthCheck implements HealthIndicator {

    private static final Logger log = LoggerFactory.getLogger(ServerCustomHealthCheck.class);

  /*  @Autowired
    DataSource datasource;*/
    
   // @Value("${app.status}")
    @Value("true")
    private Boolean appStatus;
    
    @Override
    public Health health() {
    	
    // check db available
      /*  try (Connection connection = datasource.getConnection()) {
        	log.info("Connected DB");
        } catch (SQLException e) {
            log.warn("DB not available");
            return Health.down().withDetail("smoke test", e.getMessage()).build();
        }*/
     //app status
        if(appStatus){
            log.info("In service");
            return Health.up().withDetail("ZipToDest-UI status ","In service").build();
        }else {
        log.info("Not in service");
        return Health.outOfService().withDetail("ZipToDest-UI  status ","Out of service").build();
        }
        
    }
}
