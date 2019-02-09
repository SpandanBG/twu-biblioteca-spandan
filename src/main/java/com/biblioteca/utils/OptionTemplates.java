package com.biblioteca.utils;

public enum OptionTemplates {

    CUSTOM_VIEW {
        @Override
        public String view(Options options) {
            StringBuilder builder = new StringBuilder();
            builder.append("\n\t\t").append(options.getPrefix()).append("\n");
            options.forEachOption((optionPair) -> {
                builder.append("\t").append(optionPair.getKey());
                builder.append(" - ").append(optionPair.getValue());
                builder.append("\n");
            });
            builder.append(options.getSuffix());
            return builder.toString();
        }
    },

    COMMAND_VIEW {
        @Override
        public String view(Options options) {
            StringBuilder builder = new StringBuilder();
            builder.append("\n\t\tCommand Options\n");
            options.forEachOption((optionPair) -> {
                builder.append("\t").append(optionPair.getKey());
                builder.append(" - ").append(optionPair.getValue());
                builder.append("\n");
            });
            return builder.toString();
        }
    };

    public abstract String view(Options options);
}
