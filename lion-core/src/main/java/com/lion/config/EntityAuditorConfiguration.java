package com.lion.config;

import com.lion.core.persistence.entity.BaseEntity;
import com.lion.utils.CurrentUserUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @description: 实体自动填充字段装配
 * @author: Mr.Liu
 * @create: 2020-02-21 15:16
 */
@Configuration
@ConditionalOnClass( {JpaRepositoriesAutoConfiguration.class} )
public class EntityAuditorConfiguration  {

    @Bean
    public UserAuditor userAuditor(){
        UserAuditor userAuditor = new UserAuditor();
        return userAuditor;
    }

    class UserAuditor implements AuditorAware<Long> {
        @Override
        public Optional<Long> getCurrentAuditor() {
            Map<String,Object> user = CurrentUserUtil.getCurrentUser(false);
            return Optional.ofNullable(Objects.nonNull(user)&&user.containsKey("id")?Long.valueOf(String.valueOf(user.get("id"))):null);
        }
    }
}