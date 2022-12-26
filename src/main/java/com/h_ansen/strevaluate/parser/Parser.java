package com.h_ansen.strevaluate.parser;

import java.util.HashMap;
import java.util.Map;

import com.h_ansen.strevaluate.parser.expression.Expression;
import com.h_ansen.strevaluate.parser.handler.InfixHandler;
import com.h_ansen.strevaluate.parser.handler.InfixOperator;
import com.h_ansen.strevaluate.parser.handler.PrefixGrouping;
import com.h_ansen.strevaluate.parser.handler.PrefixHandler;
import com.h_ansen.strevaluate.parser.handler.PrefixNumber;
import com.h_ansen.strevaluate.parser.handler.PrefixOperator;
import com.h_ansen.strevaluate.tokenizer.Token;
import com.h_ansen.strevaluate.tokenizer.Tokenizer;

public class Parser extends Tokenizer {

    private static final Map<Token, InfixHandler> INFIX_HANDLER_MAP = new HashMap<>();
    private static final Map<Token, PrefixHandler> PREFIX_HANDLER_MAP = new HashMap<>();

    static {
        PREFIX_HANDLER_MAP.put(Token.PLUS, new PrefixOperator(6));
        PREFIX_HANDLER_MAP.put(Token.MINUS, new PrefixOperator(6));
        PREFIX_HANDLER_MAP.put(Token.LEFT_PAREN, new PrefixGrouping());
        PREFIX_HANDLER_MAP.put(Token.NUMBER, new PrefixNumber());

        INFIX_HANDLER_MAP.put(Token.PLUS, new InfixOperator(3, false));
        INFIX_HANDLER_MAP.put(Token.MINUS, new InfixOperator(3, false));
        INFIX_HANDLER_MAP.put(Token.MULTIPLY, new InfixOperator(4, false));
        INFIX_HANDLER_MAP.put(Token.DIVIDE, new InfixOperator(4, false));
        INFIX_HANDLER_MAP.put(Token.POWER, new InfixOperator(5, true));
    }

    public Parser(String input) {
        super(input);
    }

    public Expression parse(int precedence) {
        Token token = nextToken();

        PrefixHandler prefixHandler = PREFIX_HANDLER_MAP.get(token);
        if (prefixHandler == null)
            throw new RuntimeException(
                "Expression begins with invalid token: \'" + token.getData() + "\'");

        Expression left = prefixHandler.parsePrefix(this, token);

        while (precedence < getPrecedence()) {
            token = nextToken();

            InfixHandler infixHandler = INFIX_HANDLER_MAP.get(token);
            left = infixHandler.parseInfix(this, left, token);
        }

        return left;
    }

    public void consume(Token expected, String message) {
        Token t = nextToken();
        if (t != expected)
            throw new RuntimeException(message);   
    }

    private int getPrecedence() {
        InfixHandler infixHandler = INFIX_HANDLER_MAP.get(peekNextToken());
        if (infixHandler != null) return infixHandler.getPrecedence();
        return 0;
    }

}
