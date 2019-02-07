package com.biblioteca.utils;

public enum MenuTemplates {

    COMMAND_VIEW {
        @Override
        public String view(Menu menu) {
            StringBuilder builder = new StringBuilder();
            builder.append("\t\tCommand Menu\n");
            menu.forEachOption((optionPair) -> {
                builder.append("\t").append(optionPair.getKey());
                builder.append(" - ").append(optionPair.getValue());
                builder.append("\n");
            });
            builder.append("Command >> ");
            return builder.toString();
        }
    };

    public abstract String view(Menu menu);
}
