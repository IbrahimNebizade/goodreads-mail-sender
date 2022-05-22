package com.company.goodreadsmail.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mail {
    private String from;
    private String alias;

    private String to;
    private String subject;
    private String body;

    private boolean html;
    private Template template;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Template {
        private String path;
        private Map<String, Object> params;
    }
}
