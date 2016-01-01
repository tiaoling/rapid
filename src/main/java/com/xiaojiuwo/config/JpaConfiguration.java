package com.xiaojiuwo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuhaibao on 16/1/1.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.jpa")
public class JpaConfiguration {


    private String generate_ddl;

    private Hibernate hibernate;
    public static class Hibernate{


        private String ddl_auto;
        private String naming_strategy;
        private String database;
        private String show_sql;
        private String format_sql;
        private String dialect;

        public String getDialect() {
            return dialect;
        }

        public void setDialect(String dialect) {
            this.dialect = dialect;
        }

        public String getDdl_auto() {
            return ddl_auto;
        }

        public void setDdl_auto(String ddl_auto) {
            this.ddl_auto = ddl_auto;
        }

        public String getNaming_strategy() {
            return naming_strategy;
        }

        public void setNaming_strategy(String naming_strategy) {
            this.naming_strategy = naming_strategy;
        }

        public String getShow_sql() {
            return show_sql;
        }

        public void setShow_sql(String show_sql) {
            this.show_sql = show_sql;
        }

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public String getFormat_sql() {
            return format_sql;
        }

        public void setFormat_sql(String format_sql) {
            this.format_sql = format_sql;
        }
    }


    public String getGenerate_ddl() {
        return generate_ddl;
    }

    public void setGenerate_ddl(String generate_ddl) {
        this.generate_ddl = generate_ddl;
    }

    public Hibernate getHibernate() {
        return hibernate;
    }

    public void setHibernate(Hibernate hibernate) {
        this.hibernate = hibernate;
    }
}
