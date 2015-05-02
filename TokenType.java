package analyser;

public enum TokenType {
        // Token types cannot have underscores
        VAR("[A-z]{1}"), 
        ASSIGN("="),
        OPP("[+-]"),
        INT("0|[1-9]{1}[0-9]*");

        public final String pattern;

        private TokenType(String pattern) {
            this.pattern = pattern;
        }
}