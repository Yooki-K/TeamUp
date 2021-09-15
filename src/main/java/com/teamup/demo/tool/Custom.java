package com.teamup.demo.tool;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@ConfigurationProperties(prefix = "custom")
public class Custom {
    private String[] labels;

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public String[] getSchools() {
        return schools;
    }

    public void setSchools(String[] schools) {
        this.schools = schools;
    }

    private String[] schools;

    @Override
    public String toString() {
        return "Custom{" +
                "labels=" + Arrays.toString(labels) +
                ", schools=" + Arrays.toString(schools) +
                '}';
    }
}
