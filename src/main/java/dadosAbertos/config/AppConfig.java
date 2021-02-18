package dadosAbertos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
@EnableJpaRepositories(basePackages = "dadosAbertos.repository")
@Configuration
public class AppConfig {
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
      final  LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
      factoryBean.setPersistenceUnitName("dev-PU");
      factoryBean.setPackagesToScan("dadosAbertos.entidades");




      return factoryBean;
  }
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
