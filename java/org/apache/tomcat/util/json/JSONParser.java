/* JSONParser.java */
/* Generated By:JavaCC: Do not edit this line. JSONParser.java */
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tomcat.util.json;

/**
 * Basic JSON parser generated by JavaCC. It consumes the input provided through the constructor when
 * {@code parseObject()}, {@code parseList()}, or {@code parse()} are called, and there is no way to directly
 * reset the state.
 */
@SuppressWarnings("all") // Ignore warnings in generated code
public class JSONParser implements JSONParserConstants {

    private boolean nativeNumbers = false;

    public JSONParser(String input) {
        this(new java.io.StringReader(input));
    }

    /**
     * Parses a JSON object into a Java {@code Map}.
     */
    public java.util.LinkedHashMap<String, Object> parseObject() throws ParseException {
        java.util.LinkedHashMap<String, Object> toReturn = object();
        if (!ensureEOF()) {
            throw new IllegalStateException("Expected EOF, but still had content to parse");
        }
        return toReturn;
    }

    /**
     * Parses a JSON array into a Java {@code List}.
     */
    public java.util.ArrayList<Object> parseArray() throws ParseException {
        java.util.ArrayList<Object> toReturn = list();
        if (!ensureEOF()) {
            throw new IllegalStateException("Expected EOF, but still had content to parse");
        }
        return toReturn;
    }

    /**
     * Parses any JSON-parseable object, returning the value.
     */
    public Object parse() throws ParseException {
        Object toReturn = anything();
        if (!ensureEOF()) {
            throw new IllegalStateException("Expected EOF, but still had content to parse");
        }
        return toReturn;
    }

    private static String substringBefore(String str, char delim) {
        int pos = str.indexOf(delim);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    public void setNativeNumbers(boolean value) {
        this.nativeNumbers = value;
    }

    public boolean getNativeNumbers() {
        return this.nativeNumbers;
    }

    final public boolean ensureEOF() throws ParseException {
        jj_consume_token(0);
        {
            if ("" != null) {
                return true;
            }
        }
        throw new Error("Missing return statement in function");
    }

    final public Object anything() throws ParseException {
        Object x;
        switch (jj_nt.kind) {
            case BRACE_OPEN: {
                x = object();
                break;
            }
            case BRACKET_OPEN: {
                x = list();
                break;
            }
            case NUMBER_INTEGER:
            case NUMBER_DECIMAL:
            case TRUE:
            case FALSE:
            case NULL:
            case STRING_SINGLE_EMPTY:
            case STRING_DOUBLE_EMPTY:
            case STRING_SINGLE_NONEMPTY:
            case STRING_DOUBLE_NONEMPTY: {
                x = value();
                break;
            }
            default:
                jj_la1[0] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        {
            if ("" != null) {
                return x;
            }
        }
        throw new Error("Missing return statement in function");
    }

    final public String objectKey() throws ParseException {
        Object o;
        String key;
        switch (jj_nt.kind) {
            case STRING_SINGLE_EMPTY:
            case STRING_DOUBLE_EMPTY:
            case STRING_SINGLE_NONEMPTY:
            case STRING_DOUBLE_NONEMPTY: {
                key = string();
                break;
            }
            case SYMBOL: {
                key = symbol();
                break;
            }
            case NULL: {
                nullValue();
                key = null;
                break;
            }
            case NUMBER_INTEGER:
            case NUMBER_DECIMAL:
            case TRUE:
            case FALSE: {
                switch (jj_nt.kind) {
                    case TRUE:
                    case FALSE: {
                        o = booleanValue();
                        break;
                    }
                    case NUMBER_INTEGER:
                    case NUMBER_DECIMAL: {
                        o = number();
                        break;
                    }
                    default:
                        jj_la1[1] = jj_gen;
                        jj_consume_token(-1);
                        throw new ParseException();
                }
                key = o.toString();
                break;
            }
            default:
                jj_la1[2] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        {
            if ("" != null) {
                return key;
            }
        }
        throw new Error("Missing return statement in function");
    }

    final public java.util.LinkedHashMap<String, Object> object() throws ParseException {
        final java.util.LinkedHashMap<String, Object> map = new java.util.LinkedHashMap<String, Object>();
        String key;
        Object value;
        jj_consume_token(BRACE_OPEN);
        switch (jj_nt.kind) {
            case NUMBER_INTEGER:
            case NUMBER_DECIMAL:
            case TRUE:
            case FALSE:
            case NULL:
            case STRING_SINGLE_EMPTY:
            case STRING_DOUBLE_EMPTY:
            case STRING_SINGLE_NONEMPTY:
            case STRING_DOUBLE_NONEMPTY:
            case SYMBOL: {
                key = objectKey();
                jj_consume_token(COLON);
                value = anything();
                map.put(key, value);
                key = null;
                value = null;
                label_1:
                while (true) {
                    switch (jj_nt.kind) {
                        case COMMA: {
                            ;
                            break;
                        }
                        default:
                            jj_la1[3] = jj_gen;
                            break label_1;
                    }
                    jj_consume_token(COMMA);
                    key = objectKey();
                    jj_consume_token(COLON);
                    value = anything();
                    map.put(key, value);
                    key = null;
                    value = null;
                }
                break;
            }
            default:
                jj_la1[4] = jj_gen;
                ;
        }
        jj_consume_token(BRACE_CLOSE);
        {
            if ("" != null) {
                return map;
            }
        }
        throw new Error("Missing return statement in function");
    }

    final public java.util.ArrayList<Object> list() throws ParseException {
        final java.util.ArrayList<Object> list = new java.util.ArrayList<Object>();
        Object value;
        jj_consume_token(BRACKET_OPEN);
        switch (jj_nt.kind) {
            case BRACE_OPEN:
            case BRACKET_OPEN:
            case NUMBER_INTEGER:
            case NUMBER_DECIMAL:
            case TRUE:
            case FALSE:
            case NULL:
            case STRING_SINGLE_EMPTY:
            case STRING_DOUBLE_EMPTY:
            case STRING_SINGLE_NONEMPTY:
            case STRING_DOUBLE_NONEMPTY: {
                value = anything();
                list.add(value);
                value = null;
                label_2:
                while (true) {
                    switch (jj_nt.kind) {
                        case COMMA: {
                            ;
                            break;
                        }
                        default:
                            jj_la1[5] = jj_gen;
                            break label_2;
                    }
                    jj_consume_token(COMMA);
                    value = anything();
                    list.add(value);
                    value = null;
                }
                break;
            }
            default:
                jj_la1[6] = jj_gen;
                ;
        }
        jj_consume_token(BRACKET_CLOSE);
        list.trimToSize();
        {
            if ("" != null) {
                return list;
            }
        }
        throw new Error("Missing return statement in function");
    }

    final public Object value() throws ParseException {
        Object x;
        switch (jj_nt.kind) {
            case STRING_SINGLE_EMPTY:
            case STRING_DOUBLE_EMPTY:
            case STRING_SINGLE_NONEMPTY:
            case STRING_DOUBLE_NONEMPTY: {
                x = string();
                break;
            }
            case NUMBER_INTEGER:
            case NUMBER_DECIMAL: {
                x = number();
                break;
            }
            case TRUE:
            case FALSE: {
                x = booleanValue();
                break;
            }
            case NULL: {
                x = nullValue();
                break;
            }
            default:
                jj_la1[7] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        {
            if ("" != null) {
                return x;
            }
        }
        throw new Error("Missing return statement in function");
    }

    final public Object nullValue() throws ParseException {
        jj_consume_token(NULL);
        {
            if ("" != null) {
                return null;
            }
        }
        throw new Error("Missing return statement in function");
    }

    final public Boolean booleanValue() throws ParseException {
        Boolean b;
        switch (jj_nt.kind) {
            case TRUE: {
                jj_consume_token(TRUE);
                b = Boolean.TRUE;
                break;
            }
            case FALSE: {
                jj_consume_token(FALSE);
                b = Boolean.FALSE;
                break;
            }
            default:
                jj_la1[8] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        {
            if ("" != null) {
                return b;
            }
        }
        throw new Error("Missing return statement in function");
    }

    final public Number number() throws ParseException {
        Token t;
        switch (jj_nt.kind) {
            case NUMBER_DECIMAL: {
                t = jj_consume_token(NUMBER_DECIMAL);
                if (nativeNumbers) {
                    {
                        if ("" != null) {
                            return Long.valueOf(t.image);
                        }
                    }
                } else {
                    {
                        if ("" != null) {
                            return new java.math.BigDecimal(t.image);
                        }
                    }
                }
                break;
            }
            case NUMBER_INTEGER: {
                t = jj_consume_token(NUMBER_INTEGER);
                if (nativeNumbers) {
                    {
                        if ("" != null) {
                            return Double.valueOf(t.image);
                        }
                    }
                } else {
                    {
                        if ("" != null) {
                            return new java.math.BigInteger(substringBefore(t.image, '.'));
                        }
                    }
                }
                break;
            }
            default:
                jj_la1[9] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        throw new Error("Missing return statement in function");
    }

    final public String string() throws ParseException {
        String s;
        switch (jj_nt.kind) {
            case STRING_DOUBLE_EMPTY:
            case STRING_DOUBLE_NONEMPTY: {
                s = doubleQuoteString();
                break;
            }
            case STRING_SINGLE_EMPTY:
            case STRING_SINGLE_NONEMPTY: {
                s = singleQuoteString();
                break;
            }
            default:
                jj_la1[10] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        {
            if ("" != null) {
                return s;
            }
        }
        throw new Error("Missing return statement in function");
    }

    final public String doubleQuoteString() throws ParseException {
        switch (jj_nt.kind) {
            case STRING_DOUBLE_EMPTY: {
                jj_consume_token(STRING_DOUBLE_EMPTY);
                {
                    if ("" != null) {
                        return "";
                    }
                }
                break;
            }
            case STRING_DOUBLE_NONEMPTY: {
                jj_consume_token(STRING_DOUBLE_NONEMPTY);
                String image = token.image;
                {
                    if ("" != null) {
                        return image.substring(1, image.length() - 1);
                    }
                }
                break;
            }
            default:
                jj_la1[11] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        throw new Error("Missing return statement in function");
    }

    final public String singleQuoteString() throws ParseException {
        switch (jj_nt.kind) {
            case STRING_SINGLE_EMPTY: {
                jj_consume_token(STRING_SINGLE_EMPTY);
                {
                    if ("" != null) {
                        return "";
                    }
                }
                break;
            }
            case STRING_SINGLE_NONEMPTY: {
                jj_consume_token(STRING_SINGLE_NONEMPTY);
                String image = token.image;
                {
                    if ("" != null) {
                        return image.substring(1, image.length() - 1);
                    }
                }
                break;
            }
            default:
                jj_la1[12] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        throw new Error("Missing return statement in function");
    }

    final public String symbol() throws ParseException {
        jj_consume_token(SYMBOL);
        {
            if ("" != null) {
                return token.image;
            }
        }
        throw new Error("Missing return statement in function");
    }

    /**
     * Generated Token Manager.
     */
    public JSONParserTokenManager token_source;
    JavaCharStream jj_input_stream;
    /**
     * Current token.
     */
    public Token token;
    /**
     * Next token.
     */
    public Token jj_nt;
    private int jj_gen;
    final private int[] jj_la1 = new int[13];
    static private int[] jj_la1_0;

    static {
        jj_la1_init_0();
    }

    private static void jj_la1_init_0() {
        jj_la1_0 = new int[]{0xccf8480, 0x78000, 0x1ccf8000, 0x40, 0x1ccf8000, 0x40, 0xccf8480, 0xccf8000, 0x60000, 0x18000, 0xcc00000, 0x8800000, 0x4400000,};
    }

    /**
     * Constructor with InputStream.
     */
    public JSONParser(java.io.InputStream stream) {
        this(stream, null);
    }

    /**
     * Constructor with InputStream and supplied encoding
     */
    public JSONParser(java.io.InputStream stream, String encoding) {
        try {
            jj_input_stream = new JavaCharStream(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        token_source = new JSONParserTokenManager(jj_input_stream);
        token = new Token();
        token.next = jj_nt = token_source.getNextToken();
        jj_gen = 0;
        for (int i = 0; i < 13; i++) {
            jj_la1[i] = -1;
        }
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.InputStream stream) {
        ReInit(stream, null);
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.InputStream stream, String encoding) {
        try {
            jj_input_stream.ReInit(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        token_source.ReInit(jj_input_stream);
        token = new Token();
        token.next = jj_nt = token_source.getNextToken();
        jj_gen = 0;
        for (int i = 0; i < 13; i++) {
            jj_la1[i] = -1;
        }
    }

    /**
     * Constructor.
     */
    public JSONParser(java.io.Reader stream) {
        jj_input_stream = new JavaCharStream(stream, 1, 1);
        token_source = new JSONParserTokenManager(jj_input_stream);
        token = new Token();
        token.next = jj_nt = token_source.getNextToken();
        jj_gen = 0;
        for (int i = 0; i < 13; i++) {
            jj_la1[i] = -1;
        }
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.Reader stream) {
        if (jj_input_stream == null) {
            jj_input_stream = new JavaCharStream(stream, 1, 1);
        } else {
            jj_input_stream.ReInit(stream, 1, 1);
        }
        if (token_source == null) {
            token_source = new JSONParserTokenManager(jj_input_stream);
        }

        token_source.ReInit(jj_input_stream);
        token = new Token();
        token.next = jj_nt = token_source.getNextToken();
        jj_gen = 0;
        for (int i = 0; i < 13; i++) {
            jj_la1[i] = -1;
        }
    }

    /**
     * Constructor with generated Token Manager.
     */
    public JSONParser(JSONParserTokenManager tm) {
        token_source = tm;
        token = new Token();
        token.next = jj_nt = token_source.getNextToken();
        jj_gen = 0;
        for (int i = 0; i < 13; i++) {
            jj_la1[i] = -1;
        }
    }

    /**
     * Reinitialise.
     */
    public void ReInit(JSONParserTokenManager tm) {
        token_source = tm;
        token = new Token();
        token.next = jj_nt = token_source.getNextToken();
        jj_gen = 0;
        for (int i = 0; i < 13; i++) {
            jj_la1[i] = -1;
        }
    }

    private Token jj_consume_token(int kind) throws ParseException {
        Token oldToken = token;
        if ((token = jj_nt).next != null) {
            jj_nt = jj_nt.next;
        } else {
            jj_nt = jj_nt.next = token_source.getNextToken();
        }
        if (token.kind == kind) {
            jj_gen++;
            return token;
        }
        jj_nt = token;
        token = oldToken;
        jj_kind = kind;
        throw generateParseException();
    }


    /**
     * Get the next Token.
     */
    final public Token getNextToken() {
        if ((token = jj_nt).next != null) {
            jj_nt = jj_nt.next;
        } else {
            jj_nt = jj_nt.next = token_source.getNextToken();
        }
        jj_gen++;
        return token;
    }

    /**
     * Get the specific Token.
     */
    final public Token getToken(int index) {
        Token t = token;
        for (int i = 0; i < index; i++) {
            if (t.next != null) {
                t = t.next;
            } else {
                t = t.next = token_source.getNextToken();
            }
        }
        return t;
    }

    private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
    private int[] jj_expentry;
    private int jj_kind = -1;

    /**
     * Generate ParseException.
     */
    public ParseException generateParseException() {
        jj_expentries.clear();
        boolean[] la1tokens = new boolean[29];
        if (jj_kind >= 0) {
            la1tokens[jj_kind] = true;
            jj_kind = -1;
        }
        for (int i = 0; i < 13; i++) {
            if (jj_la1[i] == jj_gen) {
                for (int j = 0; j < 32; j++) {
                    if ((jj_la1_0[i] & (1 << j)) != 0) {
                        la1tokens[j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 29; i++) {
            if (la1tokens[i]) {
                jj_expentry = new int[1];
                jj_expentry[0] = i;
                jj_expentries.add(jj_expentry);
            }
        }
        int[][] exptokseq = new int[jj_expentries.size()][];
        for (int i = 0; i < jj_expentries.size(); i++) {
            exptokseq[i] = jj_expentries.get(i);
        }
        return new ParseException(token, exptokseq, tokenImage);
    }

    private int trace_indent = 0;
    private boolean trace_enabled;

    /**
     * Trace enabled.
     */
    final public boolean trace_enabled() {
        return trace_enabled;
    }

    /**
     * Enable tracing.
     */
    final public void enable_tracing() {
    }

    /**
     * Disable tracing.
     */
    final public void disable_tracing() {
    }

}
