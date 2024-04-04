package almacen.tienda;

import org.testcontainers.containers.PostgreSQLContainer;

public class TestContainerConfig {
     PostgreSQLContainer<?> postgreSQLContainer(){
        return new PostgreSQLContainer<>("postgres:15-alpine");
    }
}
