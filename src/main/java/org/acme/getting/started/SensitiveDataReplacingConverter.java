package org.acme.getting.started;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.regex.Pattern;

import ch.qos.logback.core.pattern.CompositeConverter;

public class SensitiveDataReplacingConverter<EVENT> extends CompositeConverter<EVENT> {

    private static final int REQUIRED_OPTIONS = 2;

    private List<Pattern> patterns;

    private String replacement;

    @Override
    public void start() {
        final List<String> optionList = getOptionList();
        if (optionList == null || optionList.size() < REQUIRED_OPTIONS) {
            addError("At least two options are expected whereas you have declared none");
        } else {
            replacement = getFirstOption();
            patterns = optionList.stream()
                    .skip(1)
                    .map(Pattern::compile)
                    .collect(toList());
            super.start();
        }
    }

    @Override
    protected String transform(EVENT event, String in) {
        String out = in;
        if (started) {
            for (Pattern p : patterns) {
                out = p.matcher(out).replaceAll(replacement);
            }
        }
        return out;
    }
}