package com.genezeiniss.teach_to_fish.configuration;

import org.jooq.conf.RenderNameCase;
import org.jooq.conf.Settings;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JooqLiquibaseConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(Conditions.isNotNull())
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        return mapper;
    }

    @Bean
    public Settings settings() {
        Settings settings = new Settings();
        settings.withRenderNameCase(RenderNameCase.LOWER);
        return settings;
    }
}
